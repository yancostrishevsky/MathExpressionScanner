package TKK;


public class Scanner {
    private static final int TOK_EOF = 0; // koniec pliku
    private static final int TOK_NUM = 1; // liczba całkowita
    private static final int TOK_IDENT = 2; // identyfikator
    private static final int TOK_ADD = 3; // dodawanie
    private static final int TOK_SUB = 4; // odejmowanie
    private static final int TOK_MUL = 5; // mnożenie
    private static final int TOK_DIV = 6; // dzielenie
    private static final int TOK_LPAREN = 7; // otwierający nawias
    private static final int TOK_RPAREN = 8; // zamykający nawias

    private final String input; // wejście do zeskanowania
    private int currentPosition; // bieżąca pozycja w wejściu
    private int currentColumn; // bieżąca kolumna w wejściu

    public Scanner(String input) {
        this.input = input;
        this.currentPosition = 0;
        this.currentColumn = 1;
    }

    // Metoda zwracająca bieżący znak, lub '\0' jeśli pozycja przekracza długość wejścia
    private char getCurrentChar() {
        if (currentPosition < input.length()) {
            return input.charAt(currentPosition);
        } else {
            return '\0';
        }
    }

    // Metoda przesuwająca bieżącą pozycję o jeden znak do przodu
    private void advance() {
        currentPosition++;
        currentColumn++;
    }

    // Metoda pomijająca białe znaki (spacje, tabulatory, nowe linie) na początku wejścia
    private void skipWhitespace() {
        while (getCurrentChar() != '\0') {
            if (getCurrentChar() == ' ' || getCurrentChar() == '\t' || getCurrentChar() == '\n') {
                advance();
            } else {
                break;
            }
        }
    }

    // Metoda skanująca i zwracająca kolejny token
    public Token nextToken() throws ScanException {
        skipWhitespace();
        char currentChar = getCurrentChar();
        if (currentChar == '\0') {
            return new Token(TOK_EOF, "");
        } else if (Character.isDigit(currentChar)) {
            // Skanujemy liczbę całkowitą
            StringBuilder sb = new StringBuilder();
            do {
                sb.append(currentChar);
                advance();
                currentChar = getCurrentChar();
            } while (Character.isDigit(currentChar));
            return new Token(TOK_NUM, sb.toString());
        } else if (Character.isLetter(currentChar)) {
            // Skanujemy identyfikator
            StringBuilder sb = new StringBuilder();
            do {
                sb.append(currentChar);
                advance();
                currentChar = getCurrentChar();
            } while (Character.isLetterOrDigit(currentChar));
            return new Token(TOK_IDENT, sb.toString());
        } else {
            // Skanujemy operator lub nawias
            switch (currentChar) {
                case '+' -> {
                    advance();
                    return new Token(TOK_ADD, "+");
                }
                case '-' -> {
                    advance();
                    return new Token(TOK_SUB, "-");
                }
                case '*' -> {
                    advance();
                    return new Token(TOK_MUL, "*");
                }
                case '/' -> {
                    advance();
                    return new Token(TOK_DIV, "/");
                }
                case '(' -> {
                    advance();
                    return new Token(TOK_LPAREN, "(");
                }
                case ')' -> {
                    advance();
                    return new Token(TOK_RPAREN, ")");
                }
                default ->
                        // W przypadku nieznanych znaków rzuca wyjątek z lokalizacją błędu
                        throw new ScanException("Nieznany znak: " + currentChar, currentColumn);
            }
        }
    }

    public static int getTokEof() {
        return TOK_EOF;
    }

}