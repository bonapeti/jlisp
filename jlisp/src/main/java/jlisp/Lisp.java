package jlisp;

import syntax.Grammar;

import java.util.Stack;

/**
 * Lisp contains the semantic elements of Lisp language in Java, e.g: nil, t, etc
 */
public class Lisp {

    /**
     * The NIL object
     */
    public static final Nil NIL = new Nil();

    /**
     * The TRUTH object
     */
    public static final T T = new T();
    

    public static Fixnum fixnum(int value) {
        return Fixnum.as(value);
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
        
        LispCode lispCode = new LispCode(text);
        Stack<LispObject> stack = new Stack<>();
        
        lispParser.parse(lispCode, stack);
        
        if (stack.isEmpty()) {
            throw new ParseException("Unknown expression '" + text + "'");
        }
        return (LispObject)stack.pop();
    }

    public static FunctionCall asFunction(LispObject lispObject) {
        if (!(lispObject instanceof FunctionCall)) {
            throw new EvaluationException(lispObject.toString() + " is not a FUNCTION");
        }
        return (FunctionCall)lispObject;
    }

}
