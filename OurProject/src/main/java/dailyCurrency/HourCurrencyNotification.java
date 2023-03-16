package dailyCurrency;

import BotAPI.MessageUserInfo;
import Dto.SettingsUserDto;
import Enums.NotificationTime;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static Settings.UserSettings.getUsersSettingsFromJson;

public class HourCurrencyNotification {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

        service.scheduleAtFixedRate(new TaskTime(), 0, 2, TimeUnit.SECONDS);
    }

    public static class TaskTime implements Runnable{
        @Override
        public void run() {
            int time = 0;
            String hour= "TEN";
            if (LocalDateTime.now().toLocalTime().getMinute() == 0){
                time = LocalDateTime.now().toLocalTime().getHour();
                System.out.println("datetime = " + time);
                switch (time){
                   case 10: hour = "TEN";
                    default: hour = "ELEVEN";
                }
            };
            Map<String, SettingsUserDto> allUsersMapNotification = getUsersSettingsFromJson();
            String finalHour = "ELEVEN";
            List<SettingsUserDto> allUsersTime = allUsersMapNotification.entrySet().stream().map(allUsers -> allUsers.getValue())
                    .filter(allUsers -> allUsers.getNotificationTime()
                            .equals(NotificationTime.ELEVEN)).peek(System.out::println).collect(Collectors.toList());

            for (SettingsUserDto allUser: allUsersTime) {
                System.out.println("MessageUserInfo: " + MessageUserInfo.showInfo(allUser));
            }
        }
    }

}
