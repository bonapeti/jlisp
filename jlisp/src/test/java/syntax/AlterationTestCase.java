package syntax;



import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.Stack;

import jlisp.LispCode;
import jlisp.LispObject;
import jlisp.ParseException;
import jlisp.Parser;
import org.junit.jupiter.api.Test;


@SuppressWarnings("ALL")
public class AlterationTestCase {

    @Test
    public void one_parser_fails() {
        Parser parser = mock(Parser.class);
        Stack<LispObject> stack = new Stack<>();
        LispCode lispCode = mock(LispCode.class);
        when(lispCode.getCurrentPosition()).thenReturn(5);
        
        ParseException pe = new ParseException("ParseException");
        doThrow(pe).when(parser).parse(lispCode, stack);
        
        Alteration alteration = new Alteration();
        alteration.addParser(parser);
        
        try {
            alteration.parse(lispCode, stack);
            fail("Should have failed with ParseException");
        } catch (ParseException ape) {
            assertSame(pe, ape);
            assertTrue(stack.isEmpty());
            verify(lispCode).goTo(5);
        }
        
    }
    
    @Test
    public void first_parses() {
        LispObject lispObject = mock(LispObject.class);
        
        Parser parser1 = new MockParser(lispObject);
        Parser parser2 = mock(Parser.class);
        
        Stack<LispObject> stack = new Stack<>();
        LispCode lispCode = mock(LispCode.class);
        
        when(lispCode.getCurrentPosition()).thenReturn(5);
        
        ParseException pe = new ParseException("ParseException");
        doThrow(pe).when(parser2).parse(eq(lispCode), isA(Stack.class));
        
        Alteration alteration = new Alteration();
        alteration.addParser(parser1);
        alteration.addParser(parser2);
        
        alteration.parse(lispCode, stack);
        assertSame(lispObject, stack.pop());
        assertTrue(stack.isEmpty());
        verifyZeroInteractions(parser2);
    }
    
    @Test
    public void first_fails() {
        LispObject lispObject = mock(LispObject.class);
        
        Parser parser1 = mock(Parser.class);
        Parser parser2 = new MockParser(lispObject);
        
        Stack<LispObject> stack = new Stack<>();
        LispCode lispCode = mock(LispCode.class);
        
        ParseException pe = new ParseException("ParseException");
        doThrow(pe).when(parser1).parse(eq(lispCode), isA(Stack.class));
        
        Alteration alteration = new Alteration();
        alteration.addParser(parser1);
        alteration.addParser(parser2);
        
        alteration.parse(lispCode, stack);
        assertSame(lispObject, stack.pop());
        assertTrue(stack.isEmpty());
        
    }

}
