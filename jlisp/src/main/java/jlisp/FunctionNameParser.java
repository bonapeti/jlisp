package jlisp;

import java.util.Stack;

public class FunctionNameParser implements Parser {


    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) throws ParseException {
        StringBuilder stringBuilder = new StringBuilder();
        charIterator.advanceUntil(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                if (c == ')') {
                    return false;
                }
                return !Character.isWhitespace(c);
            }
        }, stringBuilder);
        if (stringBuilder.toString().isEmpty()) {
            throw new ParseException("Missing function name!");
        }
        stack.push(new FunctionCall(stringBuilder.toString()));
    }

}
