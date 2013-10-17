package syntax;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.util.Stack;

import jlisp.CharIterator;
import jlisp.Expression;
import jlisp.ParseException;
import jlisp.Parser;

import org.junit.Test;

public class RepititionTestCase {

    @Test
    public void testParse() {
        Parser parser = mock(Parser.class);
        Stack<Expression> stack = new Stack<Expression>();
        CharIterator charIterator = mock(CharIterator.class);
        
        ParseException pe = new ParseException("ParseException");
        doThrow(pe).when(parser).parse(charIterator, stack);
        
        Grammar.repititionOf(parser).parse(charIterator, stack);
    }

}
