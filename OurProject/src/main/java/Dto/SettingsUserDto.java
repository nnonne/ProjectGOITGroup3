package Dto;

import lombok.Data;
import Enums.*;

import java.util.List;

@Data

public class SettingsUserDto {
    private String idUser;
    private BankName bankName;
    private List<Currency> currency;
    private NotificationTime hourOfAwakening;
    private DigitsAfterDecimalPoint decimalPoint;
}
