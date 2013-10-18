package jlisp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Stack;

import org.junit.Test;

public class VariableReferenceTestCase {

    @Test(expected=ParseException.class)
    public void empty() {
        VariableReferenceParser parser = new VariableReferenceParser();
        parser.parse(new CharIterator(""), new Stack<Expression>());
    }
    
    @Test(expected=ParseException.class)
    public void space() {
        VariableReferenceParser parser = new VariableReferenceParser();
        parser.parse(new CharIterator(" "), new Stack<Expression>());
    }
    
    @Test(expected=ParseException.class)
    public void spaceA() {
        VariableReferenceParser parser = new VariableReferenceParser();
        parser.parse(new CharIterator(" a"), new Stack<Expression>());
    }
    
    @Test
    public void a() {
        VariableReferenceParser parser = new VariableReferenceParser();
        Stack<Expression> stack = new Stack<Expression>();
        parser.parse(new CharIterator("a"), stack);
        assertFalse(stack.isEmpty());
        assertEquals(new VariableReference("a"), stack.pop());
    }
    
    @Test
    public void aSpace() {
        VariableReferenceParser parser = new VariableReferenceParser();
        Stack<Expression> stack = new Stack<Expression>();
        parser.parse(new CharIterator("a "), stack);
        assertFalse(stack.isEmpty());
        assertEquals(new VariableReference("a"), stack.pop());
    }

}
