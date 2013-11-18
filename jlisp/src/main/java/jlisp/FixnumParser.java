package jlisp;

import java.util.Stack;

public class FixnumParser implements Parser {
    
    @Override
    public void parse(CharIterator charIterator, Stack<LispObject> stack) throws ParseException{
        final StringBuilder sb = new StringBuilder();
        charIterator.expect(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                if (!Character.isDigit(c)) {
                    throw new ParseException("Expected number but found " + c);
                } else {
                    return true;
                }
            }
        }, sb);
        charIterator.advanceUntil(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                return Character.isDigit(c);
            }
        }, sb);
        stack.push(new Fixnum(Integer.parseInt(sb.toString())));
    }
    
    @Override
    public String toString() {
        return "number";
    }
}
