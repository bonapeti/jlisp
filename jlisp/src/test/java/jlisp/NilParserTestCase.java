package jlisp;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class NilParserTestCase {

    @Test
    public void parseNil() {
        Stack<LispObject> stack = new Stack<>();
        NilParser symbolParser = new NilParser();
        LispCode chars = new LispCode("nil");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.NIL,stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }
    
    @Test
    public void parseNilSpace() {
        Stack<LispObject> stack = new Stack<>();
        NilParser symbolParser = new NilParser();
        LispCode chars = new LispCode("nil ");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.NIL,stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }
    
    @Test
    public void parseTSpaceWithClosingList() {
        Stack<LispObject> stack = new Stack<>();
        NilParser symbolParser = new NilParser();
        LispCode chars = new LispCode("nil) ");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.NIL,stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }

}
