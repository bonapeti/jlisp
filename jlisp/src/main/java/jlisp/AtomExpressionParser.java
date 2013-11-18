package jlisp;

import java.util.Stack;

import syntax.Grammar;

public class AtomExpressionParser implements Parser {

    @Override
    public void parse(CharIterator charIterator, Stack<LispObject> stack) throws ParseException {
        Grammar.alterationOf(new FixnumParser(), new StringParser(), new NilParser(), new TrueSymbolParser(), new SymbolParser(), new ListExpressionParser()).parse(charIterator, stack);
    }

}
