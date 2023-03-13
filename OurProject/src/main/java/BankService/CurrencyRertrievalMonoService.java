package BankService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;


public class CurrencyRertrievalMonoService implements CurrencyRertrievalService {

    private static final String URL = "https://api.monobank.ua/bank/currency";
    @Override
    public List<CurrencyRateDto> getCurrencyRates() {
        try {
            String response = Jsoup.connect(URL).ignoreContentType(true).get().body().text();
            List<CurrencyRateMonoResponseDto> responseDtos = convertResponseToList(response);
            return responseDtos.stream()
                    .filter(dto -> dto.getCurrencyCodeB() != 840)
                    .map(dto -> new CurrencyRateDto(Enums.BankName.MONOBANK, dto.getCurrencyCodeA(), dto.getRateBuy(), dto.getRateSell()))
                    .filter(dto -> dto.getCurrency() != Enums.Currency.UNKNOWN)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<CurrencyRateMonoResponseDto> convertResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, CurrencyRateMonoResponseDto.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}