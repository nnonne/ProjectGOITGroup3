package BankService;

import java.util.List;

public interface CurrencyRetrievalService {
    List<CurrencyRateDto> getCurrencyRates();
}