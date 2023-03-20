package bankservice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CurrencyRateNBUResponse {
    private int r030;
    private BigDecimal rate;
}