package exercise13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Token2 {
    enum Type2 {
        PLUS, MINUS, INTEGER, LPARAN, RPARAN, LETTER
    }

    Type2 type;
    String value;

    public Token2(Type2 type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "' " + value + " '";
    }
}

class ExpressionProcessor2 {
    public Map<Character, java.lang.Integer> variables = new HashMap<>();

    private List<Token2> lex(String expression) {
        List<Token2> tokens = new ArrayList<>();

        for (int currentIndex = 0; currentIndex < expression.length(); ++currentIndex) {
            char current = expression.charAt(currentIndex);
            switch (current) {
                case '+':
                    addTokenToList(tokens, Token2.Type2.PLUS, "" + current);
                    break;
                case '-':
                    addTokenToList(tokens, Token2.Type2.MINUS, "" + current);
                    break;
                case '(':
                    addTokenToList(tokens, Token2.Type2.LPARAN, "" + current);
                    break;
                case ')':
                    addTokenToList(tokens, Token2.Type2.RPARAN, "" + current);
                    break;
                default:
                    boolean isLastChar = (currentIndex == (expression.length() - 1));

                    if (Character.isLetter(current) && !isLastChar && Character.isLetter(expression.charAt(currentIndex + 1))) {
                        return emptyList();
                    } else if (Character.isLetter(current)) {
                        addTokenToList(tokens, Token2.Type2.LETTER, "" + current);
                        break;
                    } else {
                        StringBuilder sb = new StringBuilder();

                        for (int j = currentIndex; j < expression.length(); j++) {
                            isLastChar = (j == (expression.length() - 1));

                            char innerCurrent = expression.charAt(j);

                            if (Character.isDigit(innerCurrent)) {
                                sb.append(innerCurrent);
                                currentIndex = j;

                                if (isLastChar) {
                                    addTokenToList(tokens, Token2.Type2.INTEGER, sb.toString());
                                }
                            } else {
                                addTokenToList(tokens, Token2.Type2.INTEGER, sb.toString());
                                break;
                            }
                        }
                    }
                    break;
            }
        }
        return tokens;
    }

    private List<Token2> emptyList() {
        return new ArrayList<>();
    }

    private boolean addTokenToList(List<Token2> tokens, Token2.Type2 type, String token) {
//        System.out.println("Token to add = " + type + " : " + token);
        return tokens.add(new Token2(type, token));
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    interface Element2 {
        int eval();
    }

    class Invalid implements Element2 {
        @Override
        public int eval() {
            return 0;
        }
    }

    class Integer2 implements Element2 {
        int value;

        public Integer2(int value) {
            this.value = value;
        }

        @Override
        public int eval() {
            return value;
        }
    }

    class BinaryOperation2 implements Element2 {

        public enum Type {
            ADDITION,
            SUBTRACTION
        }

        Type type;
        Element2 left, right;

        @Override
        public int eval() {
            switch (type) {
                case ADDITION:
                    return left.eval() + right.eval();
                case SUBTRACTION:
                    return left.eval() - right.eval();
                default:
                    return 0;
            }
        }
    }

    private int parse(List<Token2> tokens) {
        Element2 result = parsingHelper(tokens);

        if (result instanceof Invalid) return 0;

        return result.eval();
    }

    private Element2 parsingHelper(List<Token2> tokens) {
//        tokens.forEach(s -> System.out.println("Token: = " + s));

        BinaryOperation2 result = new BinaryOperation2();
        boolean hasLeftSide = false;
        boolean hasRightSide = false;

        for (int i = 0; i < tokens.size(); i++) {
            Token2 current = tokens.get(i);

            switch (current.type) {
                case PLUS:
                    result.type = BinaryOperation2.Type.ADDITION;
                    break;
                case MINUS:
                    result.type = BinaryOperation2.Type.SUBTRACTION;
                    break;
                case INTEGER:
                    Integer2 inti = new Integer2(java.lang.Integer.parseInt(current.value));
                    if (!hasLeftSide) {
                        result.left = inti;
                        hasLeftSide = true;
                    } else if (!hasRightSide) {
                        result.right = inti;
                        hasRightSide = true;
                    } else {
                        result.left = new Integer2(result.eval());
                        result.right = inti;
                    }
                    break;
                case LETTER:
                    java.lang.Integer inti2 = variables.get(current.value.charAt(0));
                    if (inti2 == null) {
                        return new Invalid();
                    } else {
                        Integer2 vari = new Integer2(inti2);
                        if (!hasLeftSide) {
                            result.left = vari;
                            hasLeftSide = true;
                        } else if (!hasRightSide) {
                            result.right = vari;
                            hasRightSide = true;
                        } else {
                            result.left = new Integer2(result.eval());
                            result.right = vari;
                        }
                    }
                    break;
                case LPARAN:
                    int j = i;
                    for (; j < tokens.size(); ++j) {
                        if (tokens.get(j).type.equals(Token2.Type2.RPARAN))
                            break;
                    }

                    List<Token2> subTokens = tokens.stream()
                            .skip(i + 1) // skip the parentheses
                            .limit(j - i - 1) // take x items
                            .collect(Collectors.toList());

                    Element2 element2 = parsingHelper(subTokens);

                    if (!hasLeftSide) {
                        result.left = element2;
                        hasLeftSide = true;
                    } else if (!hasRightSide) {
                        result.right = element2;
                        hasRightSide = true;
                    } else {
                        result.left = new Integer2(result.eval());
                        result.right = element2;
                    }
                    i = j;
            }
        }
        return result;
    }


    public int calculate(String expression) {
        List<Token2> tokens = lex(expression);
        System.out.println("Tokens = " + tokens);
        System.out.println("________________________________");

        if (tokens.isEmpty()) {
            return 0;
        } else {
            int result = parse(tokens);
            System.out.println("______________________________");
            System.out.println("result = " + result);
            return result;
        }
    }
}

public class Interpreter_2 {
    public static void main(String[] args) {
        new ExpressionProcessor2().calculate("1+1");
        new ExpressionProcessor2().calculate("1+2");
        new ExpressionProcessor2().calculate("1+x");
        new ExpressionProcessor2().calculate("1+xy");

        new ExpressionProcessor2().calculate("1+2+3");
        new ExpressionProcessor2().calculate("1+2+xy");
        new ExpressionProcessor2().calculate("10-2-x");
        new ExpressionProcessor2().calculate("(10-2)-x");
        new ExpressionProcessor2().calculate("10-(2-x)");
    }
}
