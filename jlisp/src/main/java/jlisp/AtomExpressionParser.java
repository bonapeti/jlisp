package jlisp;

import java.util.Stack;

import syntax.Grammar;

public class AtomExpressionParser implements Parser {

    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) throws ParseException {
        Grammar.alterationOf(new NilParser(), new FixnumParser(), new TrueSymbolParser(), new StringParser(), new SymbolParser(), new ListExpressionParser()).parse(charIterator, stack);
    }

}
