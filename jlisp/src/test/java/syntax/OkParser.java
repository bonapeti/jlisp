package syntax;

import jlisp.LispCode;
import jlisp.LispObject;
import jlisp.ParseException;
import jlisp.Parser;

import java.util.Stack;

public class OkParser implements Parser {

    private final LispObject lispElement;

    public OkParser(LispObject lispElement) {
        this.lispElement = lispElement;
    }

    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException {
        stack.push(lispElement);
        lispCode.advanceToEnd();
    }
}
