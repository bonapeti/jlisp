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
        assertEquals(Lisp.string("a"), Lisp.read("\"a\""));
        assertEquals(Lisp.string("a"), Lisp.read(" \"a\""));
        assertEquals(Lisp.string("a"), Lisp.read(" \"a\" "));
        assertEquals(Lisp.string("b"), Lisp.read(" \"b\" "));
        assertEquals(Lisp.string("ab"), Lisp.read(" \"ab\" "));
    }
    
    @Test
    public void emptyList() {
        assertEquals(new List(), Lisp.read("()"));
        assertEquals(new List(), Lisp.read(" ()"));
        assertEquals(new List(), Lisp.read(" () "));
        assertEquals(new List(), Lisp.read(" ( ) "));
    }
    
    @Test
    public void addition() {
        List expectedList = new List();
        expectedList.add(new Symbol("+"));
        assertEquals(expectedList, Lisp.read("(+)"));
        assertEquals(expectedList, Lisp.read(" (+)"));
        assertEquals(expectedList, Lisp.read(" (+) "));
        assertEquals(expectedList, Lisp.read(" ( +) "));
        assertEquals(expectedList, Lisp.read(" ( + ) "));
        
        List expectedList2 = new List();
        expectedList2.add(new Symbol("+"));
        expectedList2.add(Lisp.fixnum(3));

        assertEquals(expectedList2, Lisp.read(" ( + 3) "));
        
        List expectedList3 = new List();
        expectedList3.add(new Symbol("+"));
        expectedList3.add(Lisp.fixnum(3));
        expectedList3.add(Lisp.fixnum(5));
        
        assertEquals(expectedList3, Lisp.read(" ( + 3 5 ) "));
    }
    
    @Test
    public void defun() {
        List expectedList = new List();
        expectedList.add(new Symbol("defun"));
        expectedList.add(new Symbol("a"));
        assertEquals(expectedList, Lisp.read("(defun a)"));
    }
}
