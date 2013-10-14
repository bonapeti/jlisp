package jlisp;

import java.util.Stack;


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
    
    private static Parser lispParser = null;
    
    static {
        Sequence parser = new Sequence();
        
        parser.addParser(new Spaces());
        
        Alteration alteration = new Alteration();
        alteration.addParser(new FixnumParser());
        alteration.addParser(new SExpressionParser());
        alteration.addParser(new StringParser());
        alteration.addParser(new VariableReferenceParser());
        parser.addParser(alteration);
        
        lispParser = parser;
    }
    
    public static Expression parse(String text) {
        CharIterator charIterator = new CharIterator(text);
        Stack<Expression> stack = new Stack<Expression>();
        
        lispParser.parse(charIterator, stack);
        
        if (stack.isEmpty()) {
            throw new ParseException("Unknown expression '" + text + "'");
        }
        return stack.pop();
    }

}
