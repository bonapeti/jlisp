package jlisp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.Test;

public class SymbolParserTestCase {

    @Test
    public void to_string() {
        SymbolParser symbolParser = new SymbolParser();
        assertEquals("symbol",symbolParser.toString());
    }
    
    @Test
    public void parseEmpty() {
        Stack<LispObject> stack = new Stack<>();
        SymbolParser symbolParser = new SymbolParser();
        LispCode chars = new LispCode("");
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
        Stack<LispObject> stack = new Stack<>();
        SymbolParser symbolParser = new SymbolParser();
        LispCode chars = new LispCode("arg ");
        symbolParser.parse(chars, stack);
        assertEquals(new Symbol("arg"),stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }
    
    @Test
    public void parseArg1() {
        Stack<LispObject> stack = new Stack<>();
        SymbolParser symbolParser = new SymbolParser();
        symbolParser.parse(new LispCode("arg1"), stack);
        assertEquals(new Symbol("arg1"),stack.pop());
    }
    
    @Test
    public void parseHelloHyphenWorld() {
        Stack<LispObject> stack = new Stack<>();
        SymbolParser symbolParser = new SymbolParser();
        symbolParser.parse(new LispCode("hello-world"), stack);
        assertEquals(new Symbol("hello-world"),stack.pop());
    }

    
    
    @Test
    public void space() {
        SymbolParser parser = new SymbolParser();
        assertThrows(ParseException.class, () -> parser.parse(new LispCode(" "), new Stack<LispObject>()));
    }
    
    @Test
    public void spaceA() {
        SymbolParser parser = new SymbolParser();
        assertThrows(ParseException.class, () -> parser.parse(new LispCode(" a"), new Stack<LispObject>()));
    }
    
    @Test
    public void quoted() {
        SymbolParser parser = new SymbolParser();
        assertThrows(ParseException.class, () -> parser.parse(new LispCode("'a"), new Stack<LispObject>()));
    }
    
    @Test
    public void a() {
        SymbolParser parser = new SymbolParser();
        Stack<LispObject> stack = new Stack<>();
        parser.parse(new LispCode("a"), stack);
        assertFalse(stack.isEmpty());
        assertEquals(new Symbol("a"), stack.pop());
    }
    
    @Test
    public void aSpace() {
        SymbolParser parser = new SymbolParser();
        Stack<LispObject> stack = new Stack<>();
        parser.parse(new LispCode("a "), stack);
        assertFalse(stack.isEmpty());
        assertEquals(new Symbol("a"), stack.pop());
    }
    
    @Test
    public void letStar() {
        SymbolParser parser = new SymbolParser();
        Stack<LispObject> stack = new Stack<>();
        parser.parse(new LispCode("let* "), stack);
        assertFalse(stack.isEmpty());
        assertEquals(new Symbol("let*"), stack.pop());
    }
}
