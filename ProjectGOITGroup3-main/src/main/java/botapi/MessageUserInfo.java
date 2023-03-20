package botapi;

import bankservice.*;
import enums.BankName;
import enums.DigitsAfterDecimalPoint;
import settings.SettingsUserDto;

import java.util.List;
import java.util.stream.Collectors;

public class MessageUserInfo {
    private static final String RESPONSE_TEMPLATE = "Курс в банку: cur1/UAH\n Купівля: покуп\n Продаж: прод\n";
    private static List<CurrencyRate> currencyRateList;
    public static String showInfo(SettingsUserDto settingsUserDto) {
        String res;
        String bankName = formatBankName(settingsUserDto.getBankName());
        String formatDecimalPoint = formatDecimalPoint(settingsUserDto.getDecimalPoint());
        res = currencyRateList.stream()
                .filter(item -> settingsUserDto.getCurrency().contains(item.getCurrency()))
                .map(item -> RESPONSE_TEMPLATE
                        .replace("cur1", item.getCurrency().toString())
                        .replace("покуп", String.format(formatDecimalPoint,item.getBuyRate()))
                        .replace("прод", String.format(formatDecimalPoint,item.getSellRate()))
                        .replace("банку", bankName))
                .collect(Collectors.joining());
        return res;
    }

    private static String formatDecimalPoint(DigitsAfterDecimalPoint digitsAfterDecimalPoint){
        switch (digitsAfterDecimalPoint){
            case TWO:
                return "%.2f";
            case THREE:
                return "%.3f";
            case FOUR:
            default:
                return "%.4f";
        }
    }

    private static String formatBankName(BankName bankName){
        switch (String.valueOf(bankName)) {
            case "NBU":
                currencyRateList = HourCurrencyRatesUpdate.currencyRateNBUList;
                return "НБУ";
            case "MONOBANK":
                currencyRateList = HourCurrencyRatesUpdate.currencyRateMonoList;
                return "MonoBank";
            case "PRIVATBANK":
            default:
                currencyRateList = HourCurrencyRatesUpdate.currencyRatePrivatList;
                return "PrivatBank";
        }
    }
}
