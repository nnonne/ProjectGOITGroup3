package BotAPI;


import Enums.BankName;
import Enums.Currency;
import Enums.DigitsAfterDecimalPoint;
import Enums.NotificationTime;

public class EnumsGetUser {
    String value1;
    String value2;
    String value3;

    public EnumsGetUser(Currency value1, Currency value2) {
        this.value1 = String.valueOf(value1);
        this.value2 = String.valueOf(value2);

    }
    public EnumsGetUser(BankName value1, BankName value2, BankName value3) {
        this.value1 = String.valueOf(value1);
        this.value2 = String.valueOf(value2);
        this.value3 = String.valueOf(value3);
    }

    public EnumsGetUser(DigitsAfterDecimalPoint value1) {
        this.value1 = String.valueOf(value1);


    }
    public EnumsGetUser(NotificationTime value1) {
        this.value1 = String.valueOf(value1);


    }
    public EnumsGetUser(String value1) {
        this.value1 = value1;


    }



    @Override
    public String toString() {
        return "EnumsGetUser{" +
                "value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                ", value3='" + value3 + '\'' +
                '}';
    }
}
