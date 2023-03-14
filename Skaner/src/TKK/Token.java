package TKK;

public class Token {
    private final int code; // kod tokenu
    private final String value; // wartość tokenu

    public Token(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "(" + this.codeToString() + ", " + value + ")";
    }

    public String codeToString() {

        return switch (this.code) {
            case 0 -> "koniec pliku";
            case 1 -> "liczba całkowita";
            case 2 -> "identyfikator";
            case 3 -> "dodawanie";
            case 4 -> "odejmowanie";
            case 5 -> "mnożenie";
            case 6 -> "dzielenie";
            case 7 -> "otwierający nawias";
            case 8 -> "zamykający nawias";
            default -> "UNKNOWN_TOKEN_TYPE";
        };
    }
}