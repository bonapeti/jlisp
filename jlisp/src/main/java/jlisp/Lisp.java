package jlisp;

import java.util.Stack;

import syntax.Grammar;


public class Lisp {

    public static final Expression NIL = new Expression() {

        public Object evaluate(Environment environment) {
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
    
    private static Parser lispParser = null;
    
    static {

        lispParser = Grammar.sequenceOf(Grammar.whitespace(), new AtomExpressionParser());
    }
    
    public static Expression read(String text) {
        if (text == null) {
            throw new ParseException("Premature end of file!");
        }
        if (text.trim().isEmpty()) {
            throw new ParseException("Premature end of file!");
        }
        
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
