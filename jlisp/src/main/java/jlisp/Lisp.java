package jlisp;

import java.util.Stack;

import syntax.Grammar;


public class Lisp {

    public static final Nil NIL = new Nil();
    
    public static final T T = new T();
    

    public static Fixnum fixnum(int value) {
        return new Fixnum(value);
    }
    
    public static LispString string(String value) {
        return new LispString(value);
    }
    
    public static List nil() {
        return NIL;
    }
    
    private static Parser lispParser = null;
    
    static {
    	lispParser = Grammar.sequenceOf(Grammar.whitespace(), new AtomExpressionParser());
    }
    
    public static LispObject read(String text) {
        if (text == null) {
            throw new ParseException("Premature end of file!");
        }
        if (text.trim().isEmpty()) {
            throw new ParseException("Premature end of file!");
        }
        
        CharIterator charIterator = new CharIterator(text);
        Stack<LispObject> stack = new Stack<LispObject>();
        
        lispParser.parse(charIterator, stack);
        
        if (stack.isEmpty()) {
            throw new ParseException("Unknown expression '" + text + "'");
        }
        return (LispObject)stack.pop();
    }
}
