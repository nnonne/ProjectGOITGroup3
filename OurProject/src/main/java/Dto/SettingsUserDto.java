package Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import Enums.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingsUserDto {
    private String idUser;
    private BankName bankName;
    private List<Currency> currency;
    private NotificationTime notificationTime;
    private DigitsAfterDecimalPoint decimalPoint;
}
