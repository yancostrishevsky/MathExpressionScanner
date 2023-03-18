package syntax_highlighting;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Scanner {

    private List<Token> tokens;

    public Scanner() {
        tokens = new ArrayList<>();
    }

    public void scan(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        while (line != null) {
            tokenize(line);
            line = reader.readLine();
        }
        reader.close();
    }

    private void tokenize(String line) {
        String[] words = line.split("\\s+");
        for (String word : words) {
            if (isKeyword(word)) {
                tokens.add(new Token(Token.Type.KEYWORD, word));
            } else if (isOperator(word)) {
                tokens.add(new Token(Token.Type.OPERATOR, word));
            } else if (isIdentifier(word)) {
                tokens.add(new Token(Token.Type.IDENTIFIER, word));
            } else if (isLiteral(word)) {
                tokens.add(new Token(Token.Type.LITERAL, word));
            }
        }
    }

    private boolean isKeyword(String word) {
        for (Token.Type type : Token.Type.values()) {
            if (type.isKeyword() && type.getPattern().matcher(word).matches()) {
                return true;
            }
        }
        return false;
    }

    private boolean isOperator(String word) {
        for (Token.Type type : Token.Type.values()) {
            if (type.isOperator() && type.getPattern().matcher(word).matches()) {
                return true;
            }
        }
        return false;
    }

    private boolean isIdentifier(String word) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }

    private boolean isLiteral(String word) {
        Pattern pattern = Pattern.compile("\\d+|\".*\"");
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }

    public void printTokens() {
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    public void saveHTML(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write("<html>\n<head>\n<style>\n");
        for (Token.Type type : Token.Type.values()) {
            writer.write("." + type.name().toLowerCase() + " { color: " + type.getColor() + "; }\n");
        }
        writer.write("</style>\n</head>\n<body>\n");
        for (Token token : tokens) {
            writer.write("<span class=\"" + token.getType().name().toLowerCase() + "\">" + token.getValue() + "</span> ");
        }
        writer.write("\n</body>\n</html>");
        writer.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner();
        try {
            scanner.scan("C:\\Users\\janek\\IdeaProjects\\syntax_highlighting\\src\\syntax_highlighting\\example.java");
            scanner.printTokens();
            scanner.saveHTML("example.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

