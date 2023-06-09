package TKK;

public class ScanException extends Exception {
    private final int column;

    public ScanException(String message, int column) {
        super(message);
        this.column = column;
    }

    public int getColumn() {
        return column;
    }
}