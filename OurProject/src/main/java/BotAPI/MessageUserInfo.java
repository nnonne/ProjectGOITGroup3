package BotAPI;

import BankService.CurrencyRateDto;
import BankService.*;
import Dto.SettingsUserDto;

import java.util.List;
import java.util.stream.Collectors;

public class MessageUserInfo {
    private static String RESPONSE_TEMPLATE = "Курс в банку: cur1/UAH\n Покупка: покуп\n Продажа: прод\n";

    public static String showInfo(SettingsUserDto settingsUserDto) {
        String bankName;
        List<CurrencyRateDto> currencyRateDtoList;
        switch (String.valueOf(settingsUserDto.getBankName())) {
            case "NBU":
                CurrencyRertrievalNBUService currencyRertrievalNBUService = new CurrencyRertrievalNBUService();
                currencyRateDtoList = currencyRertrievalNBUService.getCurrencyRates();
                bankName = "НБУ";
                break;
            case "MONOBANK":
                CurrencyRertrievalMonoService currencyRertrievalMonoService = new CurrencyRertrievalMonoService();
                currencyRateDtoList = currencyRertrievalMonoService.getCurrencyRates();
                bankName = "MonoBank";
                break;
            case "PRIVATBANK":
            default:
                CurrencyRertrievalPrivatService currencyRertrievalPrivatService = new CurrencyRertrievalPrivatService();
                currencyRateDtoList = currencyRertrievalPrivatService.getCurrencyRates();
                bankName = "PrivateBank";
                break;
        }
        String res;
        String formatDecimalPoint;
        switch (settingsUserDto.getDecimalPoint()){
            case TWO:
                formatDecimalPoint= "%.2f";
                break;
            case THREE:
                formatDecimalPoint = "%.3f";
                break;
            case FOUR:
            default:
                formatDecimalPoint = "%.4f";
        }
        res = currencyRateDtoList.stream()
                .filter(item -> settingsUserDto.getCurrency().contains(item.getCurrency()))
                .map(item -> RESPONSE_TEMPLATE
                        .replace("cur1", item.getCurrency().toString())
                        .replace("покуп", String.format(formatDecimalPoint,item.getBuyRate()))
                        .replace("прод", String.format(formatDecimalPoint,item.getSellRate()))
                        .replace("банку", bankName))
                .collect(Collectors.joining());
        return res;
    }
}
