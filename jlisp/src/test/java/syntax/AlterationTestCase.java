package syntax;



import static org.junit.jupiter.api.Assertions.*;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Parsing alternatives")
public class AlterationTestCase {

    public static final String ERROR_MESSAGE = "ParseException";
    private final Alteration alteration = new Alteration();

    @Test @DisplayName("First fails => FAILURE")
    public void one_parser_fails() {
        FailingParser failingParser = new FailingParser(ERROR_MESSAGE);

        Stack<LispObject> stack = new Stack<>();

        LispCode lispCode = mock(LispCode.class);
        when(lispCode.getCurrentPosition()).thenReturn(5);

        alteration.addParser(failingParser);
        
        try {
            alteration.parse(lispCode, stack);
            fail("Should have failed with ParseException");
        } catch (ParseException parsingError) {
            assertEquals(ERROR_MESSAGE, parsingError.getMessage());
            assertTrue(stack.isEmpty());
            verify(lispCode).goTo(5);
        }
        
    }
    
    @Test @DisplayName("First succeeds => OK")
    public void first_parses() {
        LispObject lispObject = mock(LispObject.class);

        OkParser okParser = new OkParser(lispObject);
        FailingParser failingParser = new FailingParser(ERROR_MESSAGE);
        
        Stack<LispObject> stack = new Stack<>();
        LispCode lispCode = mock(LispCode.class);
        
        when(lispCode.getCurrentPosition()).thenReturn(5);

        alteration.addParser(okParser);
        alteration.addParser(failingParser);
        
        alteration.parse(lispCode, stack);

        assertSame(lispObject, stack.pop());
    }
    
    @Test @DisplayName("First fails, second succeeds => OK")
    public void first_fails() {
        LispObject lispObject = mock(LispObject.class);

        FailingParser failingParser = new FailingParser(ERROR_MESSAGE);
        OkParser okParser = new OkParser(lispObject);
        
        Stack<LispObject> stack = new Stack<>();
        LispCode lispCode = mock(LispCode.class);

        alteration.addParser(failingParser);
        alteration.addParser(okParser);
        
        alteration.parse(lispCode, stack);

        assertSame(lispObject, stack.pop());
    }

}
