package jlisp;

import java.util.Stack;

public class SymbolParser implements Parser {

    @Override
    public void parse(CharIterator charIterator, Stack<LispObject> stack) throws ParseException {
        final StringBuilder sb = new StringBuilder();
        charIterator.expect(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                if (Character.isWhitespace(c)) {
                    return false;
                }
                if ( (c == '(') || (c == ')')) {
                    return false;
                }
                if ( c== '\'') {
                    return false;
                }
                return true;
            }
            
            @Override
            public String toString() {
                return "SYMBOL";
            }
        }, sb);
        charIterator.advanceUntil(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                return Character.isLetter(c) || Character.isDigit(c) || c == '-';
            }
        }, sb);
        if ("t".equals(sb.toString())) {
            throw new ParseException("'t' is cannot be used as variable");
        }
        if ("nil".equals(sb.toString())) {
            throw new ParseException("'nil' is cannot be used as variable");
        }
        stack.push(new Symbol(sb.toString()));
    }

    @Override
    public String toString() {
        return "symbol";
    }
}
