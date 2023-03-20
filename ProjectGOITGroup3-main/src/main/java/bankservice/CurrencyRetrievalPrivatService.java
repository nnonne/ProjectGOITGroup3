package bankservice;

import enums.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyRetrievalPrivatService implements CurrencyRetrievalService {

    private static final String URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=11";
    @Override
    public List<CurrencyRate> getCurrencyRates() {
        try {
            String response = Jsoup.connect(URL).ignoreContentType(true).get().body().text();
            List<CurrencyRatePrivatResponse> responseDtos = convertResponseToList(response);
            return responseDtos.stream()
                    .map(dto -> new CurrencyRate(BankName.PRIVATBANK,
                            dto.getCcy(), dto.getBuy(), dto.getSale()))
                    .filter(dto ->  Currency.UNKNOWN!=dto.getCurrency() )
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<CurrencyRatePrivatResponse> convertResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, CurrencyRatePrivatResponse.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}