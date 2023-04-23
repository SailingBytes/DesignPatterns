package exercise13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//interface Element {
//    int eval();
//}
//
//class Integer implements Element {
//    int value;
//
//    public Integer(int value) {
//        this.value = value;
//    }
//
//    @Override
//    public int eval() {
//        return value;
//    }
//}

//class BinaryOp

class Token {
    enum Type {
        PLUS, MINUS, INTEGER, LPARAN, RPARAN, LETTER
    }

    Type type;
    String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return " ' " + value + " '";
    }
}

class ExpressionProcessor
{
    public Map<Character, java.lang.Integer> variables = new HashMap<>();

    private int toInteger(String input) {
        try {
            return java.lang.Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean isLetter(char input) {
        return Character.isLetter(input);
    }

    private boolean processOtherTypes(String expression, int index, List<Token> tokens) {
        char current = expression.charAt(index);
        boolean isLastChar = index == expression.length()-1;

        boolean result = true;

        if(isLastChar) {
            if(toInteger("" + current) >= 0) {
                tokens.add(new Token(Token.Type.INTEGER, "" + current));
            } else if(isLetter(current)){
                tokens.add(new Token(Token.Type.LETTER, "" + current));
            } else {
                return false;
            }
        } else {
            if(isLetter(expression.charAt(index+1))) {
                return false;
            } else {
                lex(expression, index+1, tokens);
            }
        }

        return result;
    }

    private List<Token> lex(String expression, int index, List<Token> tokens) {
        boolean parsingSuccessful = true;

        for (int i = 0; i < expression.length(); ++i) {
            char current = expression.charAt(i);
            switch (current) {
                case '+':
                    tokens.add(new Token(Token.Type.PLUS, ""+current));
                    break;
                case '-':
                    tokens.add(new Token(Token.Type.MINUS, ""+current));
                    break;
                case '(':
                    tokens.add(new Token(Token.Type.LPARAN, ""+current));
                    break;
                case ')':
                    tokens.add(new Token(Token.Type.RPARAN, ""+current));
                    break;
                default:
                    parsingSuccessful = processOtherTypes(expression, i, tokens);
                    if (parsingSuccessful) {
                        return tokens;
                    } else {
                        tokens = new ArrayList<>();
                    }
                    break;

            }
        }
        return tokens;
    }

    private int parse(List<Token> tokens) {
        return 0;
    }


    public int calculate(String expression)
    {

        List<Token> tokens = new ArrayList<>();
        tokens = lex(expression, 0, tokens);

        System.out.println(tokens);

        if (tokens.isEmpty()) {
            return 0;
        } else {
            return parse(tokens);
        }
    }
}

public class Interpreter {
    public static void main(String[] args) {
        new ExpressionProcessor().calculate("1+1");
    }
}
