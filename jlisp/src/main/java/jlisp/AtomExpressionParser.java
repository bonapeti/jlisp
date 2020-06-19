package jlisp;

import syntax.Grammar;

import java.util.Stack;

public class AtomExpressionParser implements Parser {

    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException {
        Grammar.alterationOf(new QuoteParser(), new SharpQuoteParser(), new FixnumParser(), new StringParser(), new NilParser(), new TrueSymbolParser(), new SymbolParser(), new ListExpressionParser()).parse(lispCode, stack);
    }

}

class QuoteParser implements Parser {

    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException {
        lispCode.expect('\'');
        Stack<LispObject> atomStack = new Stack<>();
        new AtomExpressionParser().parse(lispCode, atomStack);
        stack.push(new Quote(atomStack.pop()));
    }
    
    
}

class SharpQuoteParser implements Parser {

    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException {
        lispCode.expect("#'");
        Stack<LispObject> atomStack = new Stack<>();
        new SymbolParser().parse(lispCode, atomStack);
        stack.push(new SharpQuote((Symbol)atomStack.pop()));
    }
    
    
}