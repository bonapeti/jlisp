package jlisp;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class SymbolParserTestCase {

    @Test
    public void to_string() {
        SymbolParser symbolParser = new SymbolParser();
        assertEquals("symbol",symbolParser.toString());
    }
    
    @Test
    public void parseEmpty() {
        Stack<LispObject> stack = new Stack<LispObject>();
        SymbolParser symbolParser = new SymbolParser();
        CharIterator chars = new CharIterator("");
        try {
            symbolParser.parse(chars, stack);
            fail("Should have failed with 'Expected SYMBOL but found end of file.'");
        } catch (ParseException pe) {
            assertEquals("Expected SYMBOL but found end of file.", pe.getMessage());
            assertEquals(0, chars.getCurrentPosition());
        }
    }

    
    @Test
    public void parseArg() {
        Stack<LispObject> stack = new Stack<LispObject>();
        SymbolParser symbolParser = new SymbolParser();
        CharIterator chars = new CharIterator("arg ");
        symbolParser.parse(chars, stack);
        assertEquals(new Symbol("arg"),stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }
    
    @Test
    public void parseArg1() {
        Stack<LispObject> stack = new Stack<LispObject>();
        SymbolParser symbolParser = new SymbolParser();
        symbolParser.parse(new CharIterator("arg1"), stack);
        assertEquals(new Symbol("arg1"),stack.pop());
    }
    
    @Test
    public void parseHelloHyphenWorld() {
        Stack<LispObject> stack = new Stack<LispObject>();
        SymbolParser symbolParser = new SymbolParser();
        symbolParser.parse(new CharIterator("hello-world"), stack);
        assertEquals(new Symbol("hello-world"),stack.pop());
    }

    
    
    @Test(expected=ParseException.class)
    public void space() {
        SymbolParser parser = new SymbolParser();
        parser.parse(new CharIterator(" "), new Stack<LispObject>());
    }
    
    @Test(expected=ParseException.class)
    public void spaceA() {
        SymbolParser parser = new SymbolParser();
        parser.parse(new CharIterator(" a"), new Stack<LispObject>());
    }
    
    @Test(expected=ParseException.class)
    public void quoted() {
        SymbolParser parser = new SymbolParser();
        parser.parse(new CharIterator("'a"), new Stack<LispObject>());
    }
    
    @Test
    public void a() {
        SymbolParser parser = new SymbolParser();
        Stack<LispObject> stack = new Stack<LispObject>();
        parser.parse(new CharIterator("a"), stack);
        assertFalse(stack.isEmpty());
        assertEquals(new Symbol("a"), stack.pop());
    }
    
    @Test
    public void aSpace() {
        SymbolParser parser = new SymbolParser();
        Stack<LispObject> stack = new Stack<LispObject>();
        parser.parse(new CharIterator("a "), stack);
        assertFalse(stack.isEmpty());
        assertEquals(new Symbol("a"), stack.pop());
    }
    
    @Test
    public void letStar() {
        SymbolParser parser = new SymbolParser();
        Stack<LispObject> stack = new Stack<LispObject>();
        parser.parse(new CharIterator("let* "), stack);
        assertFalse(stack.isEmpty());
        assertEquals(new Symbol("let*"), stack.pop());
    }
}
