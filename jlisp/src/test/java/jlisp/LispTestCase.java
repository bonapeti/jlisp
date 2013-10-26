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
    public void add_3() {
    	List expectedList = new List();
        expectedList.add(new Symbol("+"));
        expectedList.add(Lisp.fixnum(3));

        assertEquals(expectedList, Lisp.read(" ( + 3) "));
    }
    
    @Test
    public void add_3_5() {
    	List expectedList = new List();
        expectedList.add(new Symbol("+"));
        expectedList.add(Lisp.fixnum(3));
        expectedList.add(Lisp.fixnum(5));
        
        assertEquals(expectedList, Lisp.read(" ( + 3 5 ) "));
    }
    
    @Test
    public void add_3_5_and_add_7_2() {
    	List expectedList = new List();
        expectedList.add(new Symbol("+"));
        expectedList.add(Lisp.fixnum(3));
        expectedList.add(Lisp.fixnum(5));
        
        List expectedList1 = new List();
        expectedList1.add(new Symbol("+"));
        expectedList1.add(Lisp.fixnum(7));
        expectedList1.add(Lisp.fixnum(1));
        expectedList.add(expectedList1);
        
        assertEquals(expectedList, Lisp.read(" ( + 3 5 (+ 7 1)) "));
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
    }
    
    @Test
    public void defun() {
        List expectedList = new List();
        expectedList.add(new Symbol("defun"));
        expectedList.add(new Symbol("a"));
        assertEquals(expectedList, Lisp.read("(defun a)"));
    }
}
