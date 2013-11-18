package jlisp;

import java.util.Stack;

public class StringParser implements Parser {

    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) throws ParseException {
        StringBuilder sb = new StringBuilder();
        charIterator.expect('\"');
        charIterator.advanceUntil(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                return c != '\"';
            }
        }, sb);
        charIterator.expect('\"');
        stack.push(Lisp.string(sb.toString()));
    }

    @Override
    public String toString() {
        return "string";
    }
}
