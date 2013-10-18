package jlisp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LispTestCase {
    
    @Test
    public void nil() {
        assertEquals(Lisp.NIL, Lisp.read("nil"));
        assertEquals(Lisp.NIL, Lisp.read(" nil"));
        assertEquals(Lisp.NIL, Lisp.read(" nil "));
    }
    
    @Test
    public void truthy() {
        assertEquals(Lisp.t(), Lisp.read("t"));
        assertEquals(Lisp.t(), Lisp.read(" t"));
        assertEquals(Lisp.t(), Lisp.read(" t "));
    }
    
    @Test
    public void fixnums() {
        assertEquals(Lisp.fixnum(3), Lisp.read("3"));
        assertEquals(Lisp.fixnum(3), Lisp.read(" 3"));
        assertEquals(Lisp.fixnum(3), Lisp.read(" 3 "));
        assertEquals(Lisp.fixnum(4), Lisp.read(" 4 "));
        assertEquals(Lisp.fixnum(34), Lisp.read(" 34 "));
    }
    
    @Test
    public void strings() {
        assertEquals(Lisp.string("a"), Lisp.read("'a'"));
        assertEquals(Lisp.string("a"), Lisp.read(" 'a'"));
        assertEquals(Lisp.string("a"), Lisp.read(" 'a' "));
        assertEquals(Lisp.string("b"), Lisp.read(" 'b' "));
        assertEquals(Lisp.string("ab"), Lisp.read(" 'ab' "));
    }
    
    @Test
    public void emptyList() {
        assertEquals(new EmptyList(), Lisp.read("()"));
        assertEquals(new EmptyList(), Lisp.read(" ()"));
        assertEquals(new EmptyList(), Lisp.read(" () "));
        assertEquals(new EmptyList(), Lisp.read(" ( ) "));
    }
    
    @Test
    public void addition() {
        assertEquals(new FunctionCall("+"), Lisp.read("(+)"));
        assertEquals(new FunctionCall("+"), Lisp.read(" (+)"));
        assertEquals(new FunctionCall("+"), Lisp.read(" (+) "));
        assertEquals(new FunctionCall("+"), Lisp.read(" ( +) "));
        assertEquals(new FunctionCall("+"), Lisp.read(" ( + ) "));
        
        FunctionCall plus = new FunctionCall("+");
        plus.addArgument(Lisp.fixnum(3));
        assertEquals(plus, Lisp.read(" ( + 3) "));
        
        FunctionCall plus2 = new FunctionCall("+");
        plus2.addArgument(Lisp.fixnum(3));
        plus2.addArgument(Lisp.fixnum(5));
        assertEquals(plus2, Lisp.read(" ( + 3 5 ) "));
    }
    
    

}
