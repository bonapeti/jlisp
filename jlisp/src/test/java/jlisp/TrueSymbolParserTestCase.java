package jlisp;

import org.junit.jupiter.api.Test;

import syntax.Code;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrueSymbolParserTestCase {

	

	@Test
    public void parseT() {
        Stack<LispObject> stack = new Stack<>();
        TrueSymbolParser symbolParser = new TrueSymbolParser();
        Code chars = new Code("t");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.T,stack.pop());
        assertEquals(1, chars.getCurrentPosition());
    }
    
    @Test
    public void parseTSpace() {
        Stack<LispObject> stack = new Stack<>();
        TrueSymbolParser symbolParser = new TrueSymbolParser();
        Code chars = new Code("t ");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.T,stack.pop());
        assertEquals(1, chars.getCurrentPosition());
    }
    
    @Test
    public void parseTSpaceWithClosingList() {
        Stack<LispObject> stack = new Stack<>();
        TrueSymbolParser symbolParser = new TrueSymbolParser();
        Code chars = new Code("t) ");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.T,stack.pop());
        assertEquals(1, chars.getCurrentPosition());
    }
}
