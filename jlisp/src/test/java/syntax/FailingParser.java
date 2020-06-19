package syntax;

import java.util.Objects;
import java.util.Stack;

public class FailingParser implements Parser<MockLispObject> {

    private final String errorMessage;

    public FailingParser(String errorMessage) {
        this.errorMessage = Objects.requireNonNull(errorMessage, "Error message is missing!");
    }

    @Override
    public void parse(Code lispCode, Stack<MockLispObject> stack) throws ParseException {
        lispCode.goTo(0);
        throw new ParseException(errorMessage);
    }
}
