package bankservice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CurrencyRateMonoResponse {
    private int currencyCodeA;
    private int currencyCodeB;
    private BigDecimal rateSell;
    private BigDecimal rateBuy;
}