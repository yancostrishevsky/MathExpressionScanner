package TKK;
public class Token {
    private int code; // kod tokenu
    private String value; // wartość tokenu



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

    public String codeToString(){
        String tokenValue;

        switch (this.code) {
            case 0:
                tokenValue = "koniec pliku";
                break;
            case 1:
                tokenValue = "liczba całkowita";
                break;
            case 2:
                tokenValue = "identyfikator";
                break;
            case 3:
                tokenValue = "dodawanie";
                break;
            case 4:
                tokenValue = "odejmowanie";
                break;
            case 5:
                tokenValue = "mnożenie";
                break;
            case 6:
                tokenValue = "dzielenie";
                break;
            case 7:
                tokenValue = "otwierający nawias";
                break;
            case 8:
                tokenValue = "zamykający nawias";
                break;
            default:
                tokenValue = "UNKNOWN_TOKEN_TYPE";
                break;
        }
        return tokenValue;
    }
}