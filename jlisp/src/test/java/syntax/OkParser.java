package syntax;

import java.util.Stack;

public class OkParser implements Parser<MockLispObject> {

    private final MockLispObject lispElement;

    public OkParser(MockLispObject lispElement) {
        this.lispElement = lispElement;
    }

    @Override
    public void parse(Code lispCode, Stack<MockLispObject> stack) throws ParseException {
        stack.push(lispElement);
        lispCode.advanceToEnd();
    }
}
