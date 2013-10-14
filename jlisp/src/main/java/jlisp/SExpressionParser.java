package jlisp;

import java.util.Stack;

public class SExpressionParser implements Parser {



    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) {
        charIterator.expect('(');
        charIterator.advanceUntilEnd();
        stack.push(Lisp.NIL);
    }

}
