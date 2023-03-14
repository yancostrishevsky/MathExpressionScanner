package TKK;

import javax.swing.*;

public class ScannerGUI {
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
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

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
                } while (token.getCode() != Scanner.getTokEof());
            } catch (ScanException ex) {
                // W przypadku błędu skanowania wypisujemy komunikat z lokalizacją błędu
                JOptionPane.showMessageDialog(frame, ex.getMessage() + " w kolumnie " + ex.getColumn());
            }
        });


    }
}