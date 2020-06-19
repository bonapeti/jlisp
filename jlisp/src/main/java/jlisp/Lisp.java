package jlisp;



import java.util.Stack;

import syntax.Alteration;
import syntax.Code;
import syntax.ParseException;
import syntax.Parser;
import syntax.Repitition;
import syntax.Sequence;
import syntax.Spaces;

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
    
    public static Parser sequenceOf(Parser... parsers) {
        Sequence sequence = new Sequence();
        for (Parser parser : parsers) {
            sequence.addParser(parser);
        }
        return sequence;
    }

    /**
     * Creates an alteration parser
     * @param parsers
     * @return
     */
    public static Parser<LispObject> alterationOf(Parser<LispObject>... parsers) {
        Alteration<LispObject> alteration = new Alteration<>();
        for (Parser<LispObject> parser : parsers) {
            alteration.addParser(parser);
        }
        return alteration;
    }
    
    public static Parser<LispObject> whitespace() {
        return new Spaces<>();
    }
    
    public static Parser<LispObject> repititionOf(Parser<LispObject> parser) {
        return new Repitition<>(parser);
    }
    
    private static Parser<LispObject> lispParser = null;
    
    static {
    	Sequence<LispObject> sequence = new Sequence<>();
    	sequence.addParser(new Spaces<LispObject>(), new AtomExpressionParser());
    	lispParser = sequence;
    }
    
    public static LispObject read(String text) {
        if (text == null) {
            throw new ParseException("Premature end of file!");
        }
        if (text.trim().isEmpty()) {
            throw new ParseException("Premature end of file!");
        }
        
        Code lispCode = new Code(text);
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
