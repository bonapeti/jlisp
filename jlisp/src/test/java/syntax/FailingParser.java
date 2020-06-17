package syntax;

import jlisp.LispCode;
import jlisp.LispObject;
import jlisp.ParseException;
import jlisp.Parser;

import java.util.Objects;
import java.util.Stack;

public class FailingParser implements Parser {

    private final String errorMessage;

    public FailingParser(String errorMessage) {
        this.errorMessage = Objects.requireNonNull(errorMessage, "Error message is missing!");
    }

    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException {
        lispCode.goTo(0);
        throw new ParseException(errorMessage);
    }
}
