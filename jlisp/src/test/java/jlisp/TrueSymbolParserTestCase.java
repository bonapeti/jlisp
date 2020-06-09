package jlisp;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class TrueSymbolParserTestCase {

	

	@Test
    public void parseT() {
        Stack<LispObject> stack = new Stack<>();
        TrueSymbolParser symbolParser = new TrueSymbolParser();
        LispCode chars = new LispCode("t");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.T,stack.pop());
        assertEquals(1, chars.getCurrentPosition());
    }
    
    @Test
    public void parseTSpace() {
        Stack<LispObject> stack = new Stack<>();
        TrueSymbolParser symbolParser = new TrueSymbolParser();
        LispCode chars = new LispCode("t ");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.T,stack.pop());
        assertEquals(1, chars.getCurrentPosition());
    }
    
    @Test
    public void parseTSpaceWithClosingList() {
        Stack<LispObject> stack = new Stack<>();
        TrueSymbolParser symbolParser = new TrueSymbolParser();
        LispCode chars = new LispCode("t) ");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.T,stack.pop());
        assertEquals(1, chars.getCurrentPosition());
    }
}
