package jlisp;

import java.util.Stack;

public class FixnumParser implements Parser {
    
    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException{
        final StringBuilder sb = new StringBuilder();
        lispCode.expect(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
            	if (c == '-') {
            		return true;
            	}
            	
                if (!Character.isDigit(c)) {
                    throw new ParseException("Expected number but found " + c);
                } else {
                    return true;
                }
            }
        }, sb);
        lispCode.collectUntil(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                return Character.isDigit(c);
            }
        }, sb);
        stack.push(Fixnum.as(Integer.parseInt(sb.toString())));
    }
    
    @Override
    public String toString() {
        return "number";
    }
}
