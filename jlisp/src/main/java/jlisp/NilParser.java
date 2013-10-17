package jlisp;

import java.util.Stack;

public class NilParser implements Parser {

    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) throws ParseException {
        charIterator.expect("nil");
        charIterator.expectWhitespaceOrEndOfFile();
        stack.push(Lisp.NIL);
    }

}
