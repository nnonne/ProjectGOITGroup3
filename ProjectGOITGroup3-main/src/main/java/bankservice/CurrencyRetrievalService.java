package bankservice;

import java.util.List;

public interface CurrencyRetrievalService {
    List<CurrencyRate> getCurrencyRates();
}