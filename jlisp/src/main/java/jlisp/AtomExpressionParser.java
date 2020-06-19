package jlisp;

import java.util.Stack;

import syntax.Code;
import syntax.ParseException;
import syntax.Parser;

public class AtomExpressionParser implements Parser<LispObject> {

    @Override
    public void parse(Code lispCode, Stack<LispObject> stack) throws ParseException {
        Lisp.alterationOf(new QuoteParser(), new SharpQuoteParser(), new FixnumParser(), new StringParser(), new NilParser(), new TrueSymbolParser(), new SymbolParser(), new ListExpressionParser()).parse(lispCode, stack);
    }

}

class QuoteParser implements Parser<LispObject> {

    @Override
    public void parse(Code lispCode, Stack<LispObject> stack) throws ParseException {
        lispCode.expect('\'');
        Stack<LispObject> atomStack = new Stack<>();
        new AtomExpressionParser().parse(lispCode, atomStack);
        stack.push(new Quote(atomStack.pop()));
    }
    
    
}

class SharpQuoteParser implements Parser<LispObject> {

    @Override
    public void parse(Code lispCode, Stack<LispObject> stack) throws ParseException {
        lispCode.expect("#'");
        Stack<LispObject> atomStack = new Stack<>();
        new SymbolParser().parse(lispCode, atomStack);
        stack.push(new SharpQuote((Symbol)atomStack.pop()));
    }
    
    
}