package syntax;



import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import jlisp.LispCode;
import jlisp.LispObject;
import jlisp.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Parsing alternatives")
public class AlterationTestCase {

    public static final String ERROR_MESSAGE = "ParseException";
    public static final String DUMMY_TEXT = "DUMMY_TEXT";

    private final Alteration alteration = new Alteration();


    private final Stack<LispObject> stack = new Stack<>();
    private LispCode lispCode = new LispCode(DUMMY_TEXT);
    private LispObject objectToParse = new MockLispObject();
    private OkParser okParser = new OkParser(objectToParse);
    private FailingParser failingParser = new FailingParser(ERROR_MESSAGE);

    @Test @DisplayName("First fails => FAILURE")
    public void one_parser_fails() {

        alteration.addParsers(failingParser);
        
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

        alteration.addParsers(okParser, failingParser);
        
        alteration.parse(lispCode, stack);

        assertSame(objectToParse, stack.pop());
        assertFalse(lispCode.hasMore());
    }
    
    @Test @DisplayName("First fails, second succeeds => OK")
    public void first_fails() {

        alteration.addParsers(failingParser, okParser);

        alteration.parse(lispCode, stack);

        assertSame(objectToParse, stack.pop());
        assertFalse(lispCode.hasMore());
    }

}
