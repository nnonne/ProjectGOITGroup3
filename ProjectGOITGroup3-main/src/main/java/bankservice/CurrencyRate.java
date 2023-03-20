package bankservice;

import enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRate {
    private enums.BankName bankName;
    private enums.Currency currency;
    private BigDecimal buyRate;
    private BigDecimal sellRate;

    public CurrencyRate(BankName bankName, int currencyCodeA, BigDecimal rateBuy, BigDecimal rateSell) {
        this.bankName = bankName;
        switch (currencyCodeA){
            case 840:
                currency = Currency.USD;
                break;
            case 978:
                currency = Currency.EUR;
                break;
            default:
                currency = Currency.UNKNOWN;
                break;
        }
        buyRate = rateBuy;
        sellRate = rateSell;
    }

    public CurrencyRate(BankName bankName, int currency, BigDecimal sellRate) {
        this.bankName = bankName;
        switch (currency){
            case 840:
                this.currency = Currency.USD;
                break;
            case 978:
                this.currency = Currency.EUR;
                break;
            default:
                this.currency = Currency.UNKNOWN;
                break;
        }
        this.sellRate = sellRate;
        this.buyRate = BigDecimal.ZERO;
    }
}