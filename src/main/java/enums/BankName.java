package enums;

public enum BankName {
    PRIVATBANK("Приватбанк"),
    MONOBANK("Монобанк"),
    NBU("НБУ");

    String bankName;

    BankName(String bankName) {
        this.bankName = bankName;
    }

    public String getValue() {
        return bankName;
    }

    public static BankName getByValue(String value) {
        for (BankName bankName : BankName.values()) {
            if (value.equals(bankName.getValue()))
                return bankName;
        }
        throw new IllegalArgumentException("No BankNames with value " + value);
    }
}
