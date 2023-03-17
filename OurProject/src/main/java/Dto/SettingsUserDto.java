package Dto;

import Enums.BankName;
import Enums.Currency;
import Enums.DigitsAfterDecimalPoint;
import Enums.NotificationTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class SettingsUserDto {
    private String idUser;
    private BankName bankName;
    private List<Currency> currency;
    private NotificationTime notificationTime;
    private DigitsAfterDecimalPoint decimalPoint;

}