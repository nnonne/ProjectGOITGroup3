package bankService;

import java.time.LocalDateTime;
import java.util.List;

public class HourCurrencyRatesUpdate implements Runnable{
        public static CurrencyRetrievalNBUService currencyRetrievalNBUService = new CurrencyRetrievalNBUService();
        public static CurrencyRetrievalMonoService currencyRetrievalMonoService = new CurrencyRetrievalMonoService();
        public static CurrencyRetrievalPrivatService currencyRetrievalPrivatService = new CurrencyRetrievalPrivatService();
        public static List<CurrencyRateDto> currencyRateDtoMonoList;
        public static List<CurrencyRateDto> currencyRateDtoPrivatList;
        public static List<CurrencyRateDto> currencyRateDtoNBUList;
        @Override
        public void run() {
            if (LocalDateTime.now().toLocalTime().getMinute() == 0) {
                currencyRateDtoMonoList = currencyRetrievalMonoService.getCurrencyRates();
                currencyRateDtoPrivatList = currencyRetrievalPrivatService.getCurrencyRates();
                currencyRateDtoNBUList = currencyRetrievalNBUService.getCurrencyRates();
            }
        }
    }

