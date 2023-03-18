package settings;

import enums.BankName;
import enums.Currency;
import enums.DigitsAfterDecimalPoint;
import enums.NotificationTime;
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