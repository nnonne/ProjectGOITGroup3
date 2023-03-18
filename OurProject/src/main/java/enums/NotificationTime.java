package enums;

public enum NotificationTime {
    NINE ("9:00",9),
    TEN ("10:00",10),
    ELEVEN ("11:00",11),
    TWELVE ("12:00",12),
    THIRTEEN ("13:00",13),
    FOURTEEN ("14:00",14),
    FIFTEEN ("15:00",15),
    SIXTEEN ("16:00",16),
    SEVENTEEN ("17:00",17),
    EIGHTEEN ("18:00",18),
    OFFNOTIFICATIONS ("Вимкнути сповіщення",-1);

    private final String time;
    int intVal;

    NotificationTime(String time) {
        this.time = time;
    }
    NotificationTime(String time, int intVal) {
        this.time = time;
        this.intVal = intVal;
    }

    public String getValue() {
        return time;
    }

    public int getIntValue() {
        return intVal;
    }

    public static NotificationTime getByValue(String value) {
        for (NotificationTime notificationTime : NotificationTime.values()) {
            if (value.equals(notificationTime.getValue()))
                return notificationTime;
        }
        throw new IllegalArgumentException("No NotificationTime with value " + value);
    }
}
