package syntax;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Parsing alternatives")
public class AlterationTestCase {

    public static final String ERROR_MESSAGE = "ParseException";
    public static final String DUMMY_TEXT = "DUMMY_TEXT";

    private final Alteration<MockLispObject> alteration = new Alteration<>();


    private final Stack<MockLispObject> stack = new Stack<>();
    private Code lispCode = new Code(DUMMY_TEXT);
    private MockLispObject objectToParse = new MockLispObject();
    private OkParser okParser = new OkParser(objectToParse);
    private FailingParser failingParser = new FailingParser(ERROR_MESSAGE);

    @Test @DisplayName("First fails => FAILURE")
    public void one_parser_fails() {

        alteration.addParser(failingParser);
        
        try {
            alteration.parse(lispCode, stack);
            fail("Should have failed with ParseException");
        } catch (ParseException parsingError) {
            assertEquals(ERROR_MESSAGE, parsingError.getMessage());
            assertTrue(stack.isEmpty());
            assertTrue(lispCode.isAtBeginning());
        }
        
    }

    @Test @DisplayName("First succeeds => OK")
    public void first_parses() {

        alteration.addParser(okParser, failingParser);
        
        alteration.parse(lispCode, stack);

        assertSame(objectToParse, stack.pop());
        assertFalse(lispCode.hasMore());
    }
    
    @Test @DisplayName("First fails, second succeeds => OK")
    public void first_fails() {

        alteration.addParser(failingParser, okParser);

        alteration.parse(lispCode, stack);

        assertSame(objectToParse, stack.pop());
        assertFalse(lispCode.hasMore());
    }

}
