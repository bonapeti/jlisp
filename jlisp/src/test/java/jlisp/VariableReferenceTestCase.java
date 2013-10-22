package jlisp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Stack;

import org.junit.Test;

public class VariableReferenceTestCase {

    @Test(expected=ParseException.class)
    public void empty() {
        SymbolParser parser = new SymbolParser();
        parser.parse(new CharIterator(""), new Stack<Expression>());
    }
    
    @Test(expected=ParseException.class)
    public void space() {
        SymbolParser parser = new SymbolParser();
        parser.parse(new CharIterator(" "), new Stack<Expression>());
    }
    
    @Test(expected=ParseException.class)
    public void spaceA() {
        SymbolParser parser = new SymbolParser();
        parser.parse(new CharIterator(" a"), new Stack<Expression>());
    }
    
    @Test
    public void a() {
        SymbolParser parser = new SymbolParser();
        Stack<Expression> stack = new Stack<Expression>();
        parser.parse(new CharIterator("a"), stack);
        assertFalse(stack.isEmpty());
        assertEquals(new Symbol("a"), stack.pop());
    }
    
    @Test
    public void aSpace() {
        SymbolParser parser = new SymbolParser();
        Stack<Expression> stack = new Stack<Expression>();
        parser.parse(new CharIterator("a "), stack);
        assertFalse(stack.isEmpty());
        assertEquals(new Symbol("a"), stack.pop());
    }

}
