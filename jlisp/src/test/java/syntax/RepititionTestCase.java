package syntax;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.Stack;

import jlisp.LispCode;
import jlisp.LispObject;
import jlisp.ParseException;
import jlisp.Parser;

import org.junit.Test;

public class RepititionTestCase {

    @Test
    public void to_string() {
        Parser parser = mock(Parser.class);
        Repitition repition = new Repitition(parser);
        
        assertEquals(parser.toString() + "*",repition.toString());
    }
    
    @Test
    public void one_parser_fails() {
        Parser parser = mock(Parser.class);
        Stack<LispObject> stack = new Stack<>();
        LispCode lispCode = mock(LispCode.class);
        when(lispCode.getCurrentPosition()).thenReturn(5);
        
        ParseException pe = new ParseException("ParseException");
        doThrow(pe).when(parser).parse(lispCode, stack);
        
        Repitition repition = new Repitition(parser);
        
        repition.parse(lispCode, stack);
        assertTrue(stack.isEmpty());
        verify(lispCode).goTo(5);
    }
    
    @Test
    public void two_parser_fails() {
        Parser parser = mock(Parser.class);
        
        Stack<LispObject> stack = new Stack<>();
        LispCode lispCode = mock(LispCode.class);
        when(lispCode.getCurrentPosition()).thenReturn(5).thenReturn(10);

        ParseException pe = new ParseException("ParseException");
        doNothing().doThrow(pe).when(parser).parse(lispCode, stack);
        
        Repitition repition = new Repitition(parser);
        
        repition.parse(lispCode, stack);
        assertTrue(stack.isEmpty());
        verify(lispCode).goTo(10);
    }

}
