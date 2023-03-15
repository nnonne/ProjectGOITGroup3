package Enums;

public enum BankName {
    PRIVATBANK ("Приватбанк"), MONOBANK ("Монобанк"), NBU ("НБУ");

    String bankName;

    BankName(String bankName) {
        this.bankName = bankName;
    }
}
