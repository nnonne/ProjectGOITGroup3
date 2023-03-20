package bankservice;

import enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CurrencyRatePrivatResponse {
    private Currency ccy;
    private Currency base_ccy;
    private BigDecimal buy;
    private BigDecimal sale;
}