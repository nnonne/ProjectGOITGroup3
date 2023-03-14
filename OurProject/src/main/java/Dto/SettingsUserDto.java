package Dto;

import Enums.BankName;
import Enums.Currency;
import Enums.DigitsAfterDecimalPoint;
import Enums.NotificationTime;
import lombok.Data;
import java.util.List;

@Data

public class SettingsUserDto {
    private String idUser;
    private BankName bankName;
    private List<Currency> currency;
    private NotificationTime hourOfAwakening;
    private DigitsAfterDecimalPoint decimalPoint;
}
