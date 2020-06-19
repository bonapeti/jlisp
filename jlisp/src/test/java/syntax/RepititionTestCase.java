package syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Parsing repitition")
public class RepititionTestCase {

    public static final String ERROR_MESSAGE = "ParseException";
    public static final String PARSABLE_TEXT = "DUMMY_TEXT";

    private Stack<MockLispObject> stack = new Stack<>();
    private Code lispCode = new Code(PARSABLE_TEXT);
    private FailingParser failingParser = new FailingParser(ERROR_MESSAGE);

    private Repitition<MockLispObject> repition = new Repitition<>(failingParser);

    @Test @DisplayName("Parser fails first => OK")
    public void one_parser_fails() {

        repition.parse(lispCode, stack);

        assertTrue(stack.isEmpty());
        assertTrue(lispCode.isAtBeginning());
    }
    
    @Test @DisplayName("Parser succeeds first, then fails => OK")
    public void two_parser_fails() {
    	MockLispObject objectToParse = new MockLispObject();
        FirstFailsThenOkParser parser = new FirstFailsThenOkParser(objectToParse, PARSABLE_TEXT);
        Code lispCode = new Code(PARSABLE_TEXT + "unparseableText");

        Repitition<MockLispObject> repition = new Repitition<>(parser);
        repition.parse(lispCode, stack);

        assertEquals(objectToParse, stack.pop());
        assertTrue(stack.isEmpty());
        assertEquals(PARSABLE_TEXT.length(), lispCode.getCurrentPosition());
    }

}

class FirstFailsThenOkParser implements  Parser<MockLispObject> {

    private int round = 0;

    private final MockLispObject lispObject;
    private final String stringToAccept;

    FirstFailsThenOkParser(MockLispObject lispObject, String stringToAccept) {
        this.lispObject = lispObject;
        this.stringToAccept = stringToAccept;
    }

    @Override
    public void parse(Code lispCode, Stack<MockLispObject> stack) throws ParseException {

        switch (round) {
            case 0:
                lispCode.expect(stringToAccept);
                stack.push(lispObject);
                round++;
                break;
            default:
                throw new ParseException("Unexpected characters");
        }
    }
}