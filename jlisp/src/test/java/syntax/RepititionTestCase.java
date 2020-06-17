package syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Stack;

import jlisp.LispCode;
import jlisp.LispObject;
import jlisp.ParseException;
import jlisp.Parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Parsing repitition")
public class RepititionTestCase {

    public static final String ERROR_MESSAGE = "ParseException";
    public static final String PARSABLE_TEXT = "DUMMY_TEXT";

    private Stack<LispObject> stack = new Stack<>();
    private LispCode lispCode = new LispCode(PARSABLE_TEXT);
    private FailingParser failingParser = new FailingParser(ERROR_MESSAGE);

    private Repitition repition = new Repitition(failingParser);

    @Test @DisplayName("Parser fails first => OK")
    public void one_parser_fails() {

        repition.parse(lispCode, stack);

        assertTrue(stack.isEmpty());
        assertTrue(lispCode.isAtBeginning());
    }
    
    @Test @DisplayName("Parser succeeds first, then fails => OK")
    public void two_parser_fails() {
        LispObject objectToParse = new MockLispObject();
        Parser parser = new FirstFailsThenOkParser(objectToParse, PARSABLE_TEXT);
        LispCode lispCode = new LispCode(PARSABLE_TEXT + "unparseableText");

        Repitition repition = new Repitition(parser);
        repition.parse(lispCode, stack);

        assertEquals(objectToParse, stack.pop());
        assertTrue(stack.isEmpty());
        assertEquals(PARSABLE_TEXT.length(), lispCode.getCurrentPosition());
    }

}

class FirstFailsThenOkParser implements  Parser {

    private int round = 0;

    private final LispObject lispObject;
    private final String stringToAccept;

    FirstFailsThenOkParser(LispObject lispObject, String stringToAccept) {
        this.lispObject = lispObject;
        this.stringToAccept = stringToAccept;
    }

    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException {

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