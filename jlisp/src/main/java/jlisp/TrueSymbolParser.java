package jlisp;

import java.util.Stack;

public class TrueSymbolParser implements Parser {

    public TrueSymbolParser() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) throws ParseException {
        charIterator.expect('t');
        charIterator.expectWhitespaceOrEndOfFile();
        stack.push(new TrueSymbol());
    }

}
