package Dto;

import lombok.Data;

@Data

public class SettingsUserDto {
    private String idUser;
    private BankName bankName;
    private Currency currency;
    private NotificationTime hourOfAwakening;
    private int decimalPoint;
}
