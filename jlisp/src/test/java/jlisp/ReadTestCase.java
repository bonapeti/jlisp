package jlisp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ReadTestCase {
    
    @Test
    public void nil() {
        assertEquals(Lisp.NIL, Lisp.read("nil"));
        assertEquals(Lisp.NIL, Lisp.read(" nil"));
        assertEquals(Lisp.NIL, Lisp.read(" nil "));
    }
    
    @Test
    public void truthy() {
        assertEquals(Lisp.T, Lisp.read("t"));
        assertEquals(Lisp.T, Lisp.read(" t"));
        assertEquals(Lisp.T, Lisp.read(" t "));
    }
    
    @Test
    public void t_in_list() {
        assertEquals(new ConsCell(Lisp.T), Lisp.read("(t)"));
    }
    
    @Test
    public void nil_in_list() {
        assertEquals(new ConsCell(Lisp.NIL), Lisp.read("(nil)"));
    }
    
    @Test
    public void symbols() {
        assertEquals(new Symbol("arh"), Lisp.read("arh"));
        assertEquals(new Symbol("arg1"), Lisp.read("arg1"));
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
        assertEquals(Lisp.NIL, Lisp.read("()"));
        assertEquals(Lisp.NIL, Lisp.read(" ()"));
        assertEquals(Lisp.NIL, Lisp.read(" () "));
        assertEquals(Lisp.NIL, Lisp.read(" ( ) "));
    }
    
    
    @Test
    public void add_3() {
    	ConsCell expectedList = new ConsCell(new Symbol("+"));
        expectedList = expectedList.append(Lisp.fixnum(3));

        assertEquals(expectedList, Lisp.read(" ( + 3) "));
    }
    
    @Test
    public void add_3_5() {
    	ConsCell expectedList = new ConsCell(new Symbol("+"));
        expectedList = expectedList.append(Lisp.fixnum(3));
        expectedList = expectedList.append(Lisp.fixnum(5));
        
        assertEquals(expectedList, Lisp.read(" ( + 3 5 ) "));
    }
    
    @Test
    public void add_3_5_and_add_7_2() {
    	ConsCell expectedList = new ConsCell(new Symbol("+"));
        expectedList = expectedList.append(Lisp.fixnum(3));
        expectedList = expectedList.append(Lisp.fixnum(5));
        
        ConsCell expectedList1 = new ConsCell(new Symbol("+"));
        expectedList1 = expectedList1.append(Lisp.fixnum(7));
        expectedList1 = expectedList1.append(Lisp.fixnum(1));
        
        expectedList = expectedList.append(expectedList1);
        
        assertEquals(expectedList, Lisp.read(" ( + 3 5 (+ 7 1)) "));
    }
    
    
    @Test
    public void addition() {
        ConsCell expectedList = new ConsCell(new Symbol("+"));
        
        assertEquals(expectedList, Lisp.read("(+)"));
        assertEquals(expectedList, Lisp.read(" (+)"));
        assertEquals(expectedList, Lisp.read(" (+) "));
        assertEquals(expectedList, Lisp.read(" ( +) "));
        assertEquals(expectedList, Lisp.read(" ( + ) "));
    }
    
    @Test
    public void defun() {
        ConsCell expectedList = new ConsCell(new Symbol("defun"));
        expectedList = expectedList.append(new Symbol("a"));
        assertEquals(expectedList, Lisp.read("(defun a)"));
    }
    
    @Test
    public void defun_empty_parameterlist() {
        ConsCell expectedList = new ConsCell(new Symbol("defun"));
        expectedList = expectedList.append(new Symbol("a"));
        expectedList = expectedList.append(Lisp.NIL);
        assertEquals(expectedList, Lisp.read("(defun a ())"));
    }
    
    @Test
    public void first_list_1() {
        
        ConsCell innerList = new ConsCell(new Symbol("list"));
        innerList = innerList.append(new Fixnum(1));
        
        ConsCell expectedList = new ConsCell(new Symbol("first"));
        expectedList = expectedList.append(innerList);
        
        assertEquals(expectedList, Lisp.read("(first (list 1))"));
    }
    
    
    
    
    
}
