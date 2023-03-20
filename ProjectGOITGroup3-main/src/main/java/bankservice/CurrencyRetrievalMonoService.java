package bankservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;


public class CurrencyRetrievalMonoService implements CurrencyRetrievalService {

    private static final String URL = "https://api.monobank.ua/bank/currency";
    @Override
    public List<CurrencyRate> getCurrencyRates() {
        try {
            String response = Jsoup.connect(URL).ignoreContentType(true).get().body().text();
            List<CurrencyRateMonoResponse> responseDtos = convertResponseToList(response);
            return responseDtos.stream()
                    .filter(dto ->  840!=dto.getCurrencyCodeB() )
                    .map(dto -> new CurrencyRate(enums.BankName.MONOBANK,
                            dto.getCurrencyCodeA(), dto.getRateBuy(),
                            dto.getRateSell()))
                    .filter(dto ->  enums.Currency.UNKNOWN !=dto.getCurrency() )
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<CurrencyRateMonoResponse> convertResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, CurrencyRateMonoResponse.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}