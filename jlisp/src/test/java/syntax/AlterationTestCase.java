package syntax;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.Stack;

import jlisp.CharIterator;
import jlisp.Expression;
import jlisp.ParseException;
import jlisp.Parser;

import org.junit.Test;

public class AlterationTestCase {

    @Test
    public void to_string() {
        Parser parser = mock(Parser.class);
        Alteration alteration = new Alteration();
        alteration.addParser(parser);
        
        assertEquals("(" + parser.toString() + ")",alteration.toString());
        
        Parser parser2 = mock(Parser.class);
        alteration.addParser(parser2);
        
        assertEquals("(" + parser.toString() + " | " + parser2.toString() + ")",alteration.toString());
    }
    
    @Test
    public void one_parser_fails() {
        Parser parser = mock(Parser.class);
        Stack<Expression> stack = new Stack<Expression>();
        CharIterator charIterator = mock(CharIterator.class);
        when(charIterator.getCurrentPosition()).thenReturn(5);
        
        ParseException pe = new ParseException("ParseException");
        doThrow(pe).when(parser).parse(charIterator, stack);
        
        Alteration alteration = new Alteration();
        alteration.addParser(parser);
        
        try {
            alteration.parse(charIterator, stack);
            fail("Should have failed with ParseException");
        } catch (ParseException ape) {
            assertSame(pe, ape);
            assertTrue(stack.isEmpty());
            verify(charIterator).setCurrentPosition(5);
        }
        
    }
    
    @Test
    public void first_parses() {
        Expression expression = mock(Expression.class);
        
        Parser parser1 = new MockParser(expression);
        Parser parser2 = mock(Parser.class);
        
        Stack<Expression> stack = new Stack<Expression>();
        CharIterator charIterator = mock(CharIterator.class);
        
        when(charIterator.getCurrentPosition()).thenReturn(5);
        
        ParseException pe = new ParseException("ParseException");
        doThrow(pe).when(parser2).parse(eq(charIterator), isA(Stack.class));
        
        Alteration alteration = new Alteration();
        alteration.addParser(parser1);
        alteration.addParser(parser2);
        
        alteration.parse(charIterator, stack);
        assertSame(expression, stack.pop());
        assertTrue(stack.isEmpty());
        verifyZeroInteractions(parser2);
    }
    
    @Test
    public void first_fails() {
        Expression expression = mock(Expression.class);
        
        Parser parser1 = mock(Parser.class);
        Parser parser2 = new MockParser(expression);
        
        Stack<Expression> stack = new Stack<Expression>();
        CharIterator charIterator = mock(CharIterator.class);
        
        ParseException pe = new ParseException("ParseException");
        doThrow(pe).when(parser1).parse(eq(charIterator), isA(Stack.class));
        
        Alteration alteration = new Alteration();
        alteration.addParser(parser1);
        alteration.addParser(parser2);
        
        alteration.parse(charIterator, stack);
        assertSame(expression, stack.pop());
        assertTrue(stack.isEmpty());
        
    }

}
