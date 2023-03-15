package task1;

import java.math.BigDecimal;

public class CurrencyRateDto {
    private final BankName bankName;
    private final Currency currency;
    private BigDecimal buyRate;
    private BigDecimal sellRate;

    public CurrencyRateDto(BankName bankName, Currency currency, BigDecimal buyRate, BigDecimal sellRate) {
        this.bankName = bankName;
        this.currency = currency;
        this.buyRate = buyRate;
        this.sellRate = sellRate;
    }

    public BankName getBankName() {
        return bankName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getBuyRate() {
        return buyRate;
    }

    public BigDecimal getSellRate() {
        return sellRate;
    }

    @Override
    public String toString() {
        return "CurrencyRateDto{" +
                "bankName=" + bankName +
                ", currency=" + currency +
                ", buyRate=" + buyRate +
                ", sellRate=" + sellRate +
                '}';
    }
}
