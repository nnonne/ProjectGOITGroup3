package Enums;

public enum NotificationTime {
    NINE ("9:00"),
    TEN ("10:00"),
    ELEVEN ("11:00"),
    TWELVE ("12:00"),
    THIRTEEN ("13:00"),
    FOURTEEN ("14:00"),
    FIFTEEN ("15:00"),
    SIXTEEN ("16:00"),
    SEVENTEEN ("17:00"),
    EIGHTEEN ("18:00"),
    OFFNOTIFICATIONS ("Вимкнути сповіщення");

    String time;

    NotificationTime(String time) {
        this.time = time;
    }
}
