package jlisp;

import java.util.Stack;

public class EmptyListParser implements Parser {

    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) throws ParseException {
        charIterator.advanceUntil(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                if (Character.isWhitespace(c)) {
                    return true;
                } else if (c == ')') {
                    return false;
                } else {
                    throw new ParseException("Expected white space but found " + c);
                }
            }
        });
        stack.push(new EmptyList());
    }

}
