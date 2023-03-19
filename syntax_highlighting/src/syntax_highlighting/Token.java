package syntax_highlighting;
import java.util.regex.Pattern;

public class Token {
    public enum Type {
        KEYWORD(Pattern.compile("if|else|for|while|do|switch|case|break|continue|return")),
        OPERATOR(Pattern.compile("\\+|-|\\*|/|%|=|==|!=|>|>=|<|<=|&&|\\|\\|")),
        IDENTIFIER(Pattern.compile("[a-zA-Z_$][a-zA-Z_$0-9]*")),
        LITERAL(Pattern.compile("\\d+|\".*\"|\\(.*\\)|"));

        private final Pattern pattern;

        Type(Pattern pattern) {
            this.pattern = pattern;
        }

        public Pattern getPattern() {
            return pattern;
        }

        public boolean isKeyword() {
            return this == KEYWORD;
        }

        public boolean isOperator() {
            return this == OPERATOR;
        }

        public String getColor() {
            switch (this) {
                case KEYWORD:
                    return "blue";
                case OPERATOR:
                    return "purple";
                case IDENTIFIER:
                    return "black";
                case LITERAL:
                    return "green";
                default:
                    return "black";
            }
        }
    }

    private final Type type;
    private final String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return type + ": " + value;
    }
}
