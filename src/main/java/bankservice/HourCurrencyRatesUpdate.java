package bankservice;

import java.util.List;

public class HourCurrencyRatesUpdate implements Runnable{
        public static CurrencyRetrievalNBUService currencyRetrievalNBUService = new CurrencyRetrievalNBUService();
        public static CurrencyRetrievalMonoService currencyRetrievalMonoService = new CurrencyRetrievalMonoService();
        public static CurrencyRetrievalPrivatService currencyRetrievalPrivatService = new CurrencyRetrievalPrivatService();
        public static List<CurrencyRate> currencyRateMonoList;
        public static List<CurrencyRate> currencyRatePrivatList;
        public static List<CurrencyRate> currencyRateNBUList;

        @Override
        public void run() {
                currencyRateMonoList = currencyRetrievalMonoService.getCurrencyRates();
                currencyRatePrivatList = currencyRetrievalPrivatService.getCurrencyRates();
                currencyRateNBUList = currencyRetrievalNBUService.getCurrencyRates();
        }
    }

