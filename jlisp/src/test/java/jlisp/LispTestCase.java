package jlisp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LispTestCase {
    
    @Test
    public void fixnums() {
        assertEquals(Lisp.fixnum(3), Lisp.parse("3"));
        assertEquals(Lisp.fixnum(3), Lisp.parse(" 3"));
        assertEquals(Lisp.fixnum(3), Lisp.parse(" 3 "));
        assertEquals(Lisp.fixnum(4), Lisp.parse(" 4 "));
        assertEquals(Lisp.fixnum(34), Lisp.parse(" 34 "));
    }
    
    @Test
    public void strings() {
        assertEquals(Lisp.string("a"), Lisp.parse("'a'"));
        assertEquals(Lisp.string("a"), Lisp.parse(" 'a'"));
        assertEquals(Lisp.string("a"), Lisp.parse(" 'a' "));
        assertEquals(Lisp.string("b"), Lisp.parse(" 'b' "));
        assertEquals(Lisp.string("ab"), Lisp.parse(" 'ab' "));
    }
    
    @Test
    public void emptyList() {
        assertEquals(Lisp.NIL, Lisp.parse("()"));
        assertEquals(Lisp.NIL, Lisp.parse(" ()"));
        assertEquals(Lisp.NIL, Lisp.parse(" () "));
        assertEquals(Lisp.NIL, Lisp.parse(" ( ) "));
    }
    
    

}
