package dailyCurrency;

import BotAPI.MessageUserInfo;
import Enums.NotificationTime;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static Settings.UserSettings.getUserById;
import static Settings.UserSettings.getUserByNotificationTime;

public class DailyCurrencyNotification {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private boolean flag = false;
    private boolean block = false;
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void start() throws InterruptedException {
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                evryMinute();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void evryMinute() {
        ZonedDateTime userDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        int hour = userDateTime.getHour();
        int minute = userDateTime.getSecond();
        if (hour >= 0 && hour <= 23) {
            System.out.println("minute = " + minute);
            if (minute <= 1 && !isFlag() && !block) {
                setFlag(true);
                block = true;
                evryHours();
            }
            if (minute > 2) block = false;
        } else block = false;
    }
    private void evryHours() {
        Map<String, NotificationTime> userByNotificationTime = getUserByNotificationTime();
        System.out.println("userByNotificationTime = " + userByNotificationTime);
        setFlag(false);
    }
}
