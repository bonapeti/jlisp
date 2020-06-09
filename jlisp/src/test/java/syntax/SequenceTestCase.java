package syntax;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;

import java.util.Stack;

import jlisp.LispCode;
import jlisp.LispObject;
import jlisp.ParseException;
import jlisp.Parser;

import org.junit.jupiter.api.Test;

public class SequenceTestCase {

    @Test
    public void one_parser_fails() {
        Parser parser = mock(Parser.class);
        Stack<LispObject> stack = new Stack<>();
        LispCode lispCode = mock(LispCode.class);
        
        ParseException pe = new ParseException("ParseException");
        doThrow(pe).when(parser).parse(lispCode, stack);
        
        Sequence sequence = new Sequence();
        sequence.addParser(parser);
        
        try {
            sequence.parse(lispCode, stack);
            fail("Should have failed with ParseException");
        } catch (ParseException ape) {
            assertSame(pe, ape);
            assertTrue(stack.isEmpty());
        }
        
    }
    
    @Test
    public void two_parser_fails() {
        LispObject lispObject = mock(LispObject.class);
        
        Parser parser1 = new MockParser(lispObject);
        Parser parser2 = mock(Parser.class);
        
        Stack<LispObject> stack = new Stack<>();
        LispCode lispCode = mock(LispCode.class);
        
        ParseException pe = new ParseException("ParseException");
        doThrow(pe).when(parser2).parse(eq(lispCode), isA(Stack.class));
        
        Sequence sequence = new Sequence();
        sequence.addParser(parser1);
        sequence.addParser(parser2);
        
        try {
            sequence.parse(lispCode, stack);
            fail("Should have failed with ParseException");
        } catch (ParseException ape) {
            assertSame(pe, ape);
            assertTrue(stack.isEmpty());
        }
        
    }
    
    @Test
    public void parse() {
        LispObject expression1 = mock(LispObject.class);
        LispObject expression2 = mock(LispObject.class);
        
        Parser parser1 = new MockParser(expression1);
        Parser parser2 = new MockParser(expression2);
        
        Stack<LispObject> stack = new Stack<>();
        LispCode lispCode = mock(LispCode.class);

        Sequence sequence = new Sequence();
        sequence.addParser(parser1);
        sequence.addParser(parser2);
        
        sequence.parse(lispCode, stack);
        assertSame(expression2, stack.pop());
        assertSame(expression1, stack.pop());
        
        assertTrue(stack.isEmpty());
        
    }

}

class MockParser implements Parser {

    private LispObject lispObject = null;
    
    public MockParser(LispObject lispObject) {
        this.lispObject = lispObject;
    }
    
    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException {
        stack.push(lispObject);
    }
    
}