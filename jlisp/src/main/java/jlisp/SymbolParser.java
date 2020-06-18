package jlisp;

import java.util.Stack;

public class SymbolParser implements Parser {

    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException {
        final StringBuilder sb = new StringBuilder();
        lispCode.expect(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                if (Character.isWhitespace(c)) {
                    return false;
                }
                if ( (c == '(') || (c == ')')) {
                    return false;
                }
                return c != '\'';
            }
            
            @Override
            public String toString() {
                return "SYMBOL";
            }
        }, sb);
        lispCode.collectUntil(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                return Character.isLetter(c) || Character.isDigit(c) || c == '-' || c == '*';
            }
        }, sb);
        lispCode.checkNext(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                if (Character.isWhitespace(c) || c == ')') {
                    return true;
                } else {
                    throw new ParseException("Expecting nothing after '" +  sb.toString() +"' but found " + c);
                }
            }
        }, new CharacterProcessor() {
            
            @Override
            protected void onEndOfFile(CharPredicate expected) throws ParseException {
            }
        });
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
