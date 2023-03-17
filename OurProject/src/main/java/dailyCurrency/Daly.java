package dailyCurrency;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Daly {
    private boolean setFlagSend = false;
    private boolean block = false;

    public boolean isFlagSend() {
        return setFlagSend;
    }
    public void setFlagSend(boolean setFlagSend) {
        this.setFlagSend = setFlagSend;
    }

    private void evryMinute() {
        ZonedDateTime userDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        int hour = userDateTime.getHour();
        int minute = userDateTime.getSecond();
        if (hour >= 0 && hour <= 23) {
            if (minute <= 2 && !isFlagSend() && !block) {
                setFlagSend(true);
                block = true;
            }
            if (minute > 4) block = false;
        } else block = false;
    }
}
