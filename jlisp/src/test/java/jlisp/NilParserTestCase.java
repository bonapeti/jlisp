package jlisp;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class NilParserTestCase {

    @Test
    public void parseNil() {
        Stack<Expression> stack = new Stack<Expression>();
        NilParser symbolParser = new NilParser();
        CharIterator chars = new CharIterator("nil");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.NIL,stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }
    
    @Test
    public void parseNilSpace() {
        Stack<Expression> stack = new Stack<Expression>();
        NilParser symbolParser = new NilParser();
        CharIterator chars = new CharIterator("nil ");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.NIL,stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }
    
    @Test
    public void parseTSpaceWithClosingList() {
        Stack<Expression> stack = new Stack<Expression>();
        NilParser symbolParser = new NilParser();
        CharIterator chars = new CharIterator("nil) ");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.NIL,stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }

}