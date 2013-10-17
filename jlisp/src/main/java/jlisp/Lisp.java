package jlisp;

import java.util.Stack;

import syntax.Grammar;


public class Lisp {

    public static final Expression NIL = new Expression() {

        public Object evaluate() {
            return NIL;
        }
        
        public String toString() { return "NIL"; }
    };

    public static Fixnum fixnum(int value) {
        return new Fixnum(value);
    }
    
    public static LispString string(String value) {
        return new LispString(value);
    }
    
    public static Parser ATOM = Grammar.alterationOf(new NilParser(), new FixnumParser(), new TrueSymbolParser(), new StringParser(), new VariableReferenceParser(), new ListExpressionParser()); 
    
    private static Parser lispParser = null;
    
    static {

        lispParser = Grammar.sequenceOf(Grammar.whitespace(), ATOM);
    }
    
    public static Expression read(String text) {
        CharIterator charIterator = new CharIterator(text);
        Stack<Expression> stack = new Stack<Expression>();
        
        lispParser.parse(charIterator, stack);
        
        if (stack.isEmpty()) {
            throw new ParseException("Unknown expression '" + text + "'");
        }
        return stack.pop();
    }

    public static Object t() {
        return new TrueSymbol();
    }

}
