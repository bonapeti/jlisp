package jlisp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LispTestCase {
    
    @Test
    public void fixnums() {
        assertEquals(Fixnum.of(3), Lisp.parse("3"));
        assertEquals(Fixnum.of(3), Lisp.parse(" 3"));
        assertEquals(Fixnum.of(3), Lisp.parse(" 3 "));
        assertEquals(Fixnum.of(4), Lisp.parse(" 4 "));
        assertEquals(Fixnum.of(34), Lisp.parse(" 34 "));
    }
    
    @Test
    public void emptyList() {
        assertEquals(Lisp.NIL, Lisp.parse("()"));
        assertEquals(Lisp.NIL, Lisp.parse(" ()"));
        assertEquals(Lisp.NIL, Lisp.parse(" () "));
        assertEquals(Lisp.NIL, Lisp.parse(" ( ) "));
    }
    
    

}
