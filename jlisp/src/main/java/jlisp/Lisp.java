package jlisp;

import java.util.Stack;


public class Lisp {

    public static final Expression NIL = new Expression() {

        public Object evaluate() {
            return NIL;
        }
        
        public String toString() { return "NIL"; }
    };

    private static Parser lispParser = null;
    
    static {
        Sequence parser = new Sequence();
        
        parser.addParser(new Spaces());
        
        Alteration alteration = new Alteration();
        alteration.addParser(new FixnumParser());
        alteration.addParser(new SExpressionParser());
        parser.addParser(alteration);
        
        lispParser = parser;
    }
    
    public static Expression parse(String text) {
        CharIterator charIterator = new CharIterator(text);
        Stack<Expression> stack = new Stack<Expression>();
        
        lispParser.parse(charIterator, stack);
        
        assert stack.size() == 1 : "Stack should contain only 1 element";
        return stack.pop();
    }

}
