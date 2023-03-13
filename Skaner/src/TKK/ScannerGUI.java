package TKK;
import javax.swing.*;

public class ScannerGUI {
    public static final int TOK_EOF = 0; // koniec pliku
    public static final int TOK_NUM = 1; // liczba całkowita
    public static final int TOK_IDENT = 2; // identyfikator
    public static final int TOK_ADD = 3; // dodawanie
    public static final int TOK_SUB = 4; // odejmowanie
    public static final int TOK_MUL = 5; // mnożenie
    public static final int TOK_DIV = 6; // dzielenie
    public static final int TOK_LPAREN = 7; // otwierający nawias
    public static final int TOK_RPAREN = 8; // zamykający nawias

    public static void main(String[] args) {
        // Tworzymy okno dialogowe z polem tekstowym i przyciskiem
        JFrame frame = new JFrame("Scanner");
        JTextField inputField = new JTextField(20);
        JButton scanButton = new JButton("Skanuj");
        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(scanButton);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        // Obsługujemy kliknięcie przycisku
        scanButton.addActionListener(e -> {
            // Pobieramy wyrażenie z pola tekstowego
            String input = inputField.getText();
            // Tworzymy skaner dla wyrażenia
            Scanner scanner = new Scanner(input);
            // Skanujemy i wypisujemy tokeny
            try {
                Token token;
                do {
                    token = scanner.nextToken();
                    JOptionPane.showMessageDialog(frame, token.toString());
                } while (token.getCode() != TOK_EOF);
            } catch (ScanException ex) {
                // W przypadku błędu skanowania wypisujemy komunikat z lokalizacją błędu
                JOptionPane.showMessageDialog(frame, ex.getMessage() + " w kolumnie " + ex.getColumn());
            }
        });
    }




}