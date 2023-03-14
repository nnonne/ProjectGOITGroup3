package BankService;

public class BankServiceMainTest {
    public static void main(String[] args) {

        CurrencyRertrievalMonoService currencyRertrievalMonoService = new CurrencyRertrievalMonoService();
        System.out.println(currencyRertrievalMonoService.getCurrencyRates());

        CurrencyRertrievalPrivatService currencyRertrievalPrivatService = new CurrencyRertrievalPrivatService();
        System.out.println(currencyRertrievalPrivatService.getCurrencyRates());

        CurrencyRertrievalNBUService currencyRertrievalNBUService = new CurrencyRertrievalNBUService();
        System.out.println(currencyRertrievalNBUService.getCurrencyRates());
    }
}
