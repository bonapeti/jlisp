package syntax;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Parsing sequence of parsers")
public class SequenceTestCase {

    public static final String ERROR_MESSAGE = "ParseException";
    public static final String DUMMY_TEXT = "DUMMY_TEXT";

    private final Sequence<MockLispObject> sequence = new Sequence<>();

    private final FailingParser failingParser = new FailingParser(ERROR_MESSAGE);
    private final Stack<MockLispObject> stack = new Stack<>();
    private Code lispCode = new Code(DUMMY_TEXT);
    private final MockLispObject lispObject = new MockLispObject();
    private final OkParser okParser = new OkParser(lispObject);

    @Test @DisplayName("First parser fails => FAILURE")
    public void one_parser_fails() {

        sequence.addParser(failingParser);
        
        try {
            sequence.parse(lispCode, stack);
            fail("Should have failed with ParseException");
        } catch (ParseException ape) {
            assertEquals(ERROR_MESSAGE, ape.getMessage());
            assertTrue(stack.isEmpty());
            assertTrue(lispCode.isAtBeginning());
        }
        
    }
    
    @Test @DisplayName("First parser succeeds, second parser fails => FAILURE")
    public void two_parser_fails() {

        sequence.addParser(okParser, failingParser);
        
        try {
            sequence.parse(lispCode, stack);
            fail("Should have failed with ParseException");
        } catch (ParseException ape) {
            assertEquals(ERROR_MESSAGE, ape.getMessage());
            assertTrue(stack.isEmpty());
            assertTrue(lispCode.isAtBeginning());
        }
        
    }
    
    @Test @DisplayName("Both parsers succeeds => OK")
    public void parse() {
    	MockLispObject lispObject2 = new MockLispObject();

        OkParser okParser2 = new OkParser(lispObject2);

        sequence.addParser(this.okParser, okParser2);
        
        sequence.parse(lispCode, stack);
        assertSame(lispObject2, stack.pop());
        assertSame(lispObject, stack.pop());
        assertFalse(lispCode.hasMore());
        
    }

}
