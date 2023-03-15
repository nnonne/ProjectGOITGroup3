package task1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRateDtoTest {
    public static void main(String[] args) {

        List<CurrencyRateDto> privat = new ArrayList<>();
        privat.add(new CurrencyRateDto(
                BankName.PrivatBank, Currency.USD, BigDecimal.valueOf(38.45678), BigDecimal.valueOf(37.43454)));
        privat.add(new CurrencyRateDto(
                BankName.PrivatBank, Currency.EUR, BigDecimal.valueOf(40.85379), BigDecimal.valueOf(39.75374)));
        List<CurrencyRateDto> mono = new ArrayList<>();
        mono.add(new CurrencyRateDto(
                BankName.Mono,Currency.USD,BigDecimal.valueOf(38.42636),BigDecimal.valueOf(37.82684)));
        mono.add(new CurrencyRateDto(
                BankName.Mono,Currency.EUR,BigDecimal.valueOf(40.73684),BigDecimal.valueOf(39.27375)));
        System.out.println("Not format " + privat);
        System.out.println("Not format " + mono);
        System.out.println("-----------------------------------------------------------");

        int countDigits = 2;
        List<CurrencyRateDto> formattedListPrivat = digitsAfterDecimalPoint(privat,countDigits);
        List<CurrencyRateDto> formattedListMono = digitsAfterDecimalPoint(mono,countDigits);
        System.out.println("Format " + formattedListPrivat);
        System.out.println("Format " + formattedListMono);
    }

    public static List<CurrencyRateDto> digitsAfterDecimalPoint(List<CurrencyRateDto> currencyRateDtoList, int countDigits) {
        List<CurrencyRateDto> formatted = new ArrayList<>();
        for (CurrencyRateDto rate : currencyRateDtoList) {
            BigDecimal roundedSellRate = rate.getSellRate().setScale(countDigits, RoundingMode.HALF_UP);
            BigDecimal roundedBuyRate = rate.getBuyRate().setScale(countDigits,RoundingMode.HALF_UP);
            formatted.add(new CurrencyRateDto(rate.getBankName(), rate.getCurrency(), roundedBuyRate, roundedSellRate));
        }
        return formatted;
    }
}
