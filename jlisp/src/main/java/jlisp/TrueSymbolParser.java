package jlisp;

import java.util.Stack;

public class TrueSymbolParser implements Parser {

    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException {
        lispCode.expect('t');
        lispCode.checkNext(c -> {
            if (Character.isWhitespace(c) || c == ')') {
return true;
} else {
throw new ParseException("Expecting nothing after 't' but found " + c);
}
        }, new CharacterProcessor() {
            
            @Override
            protected void onEndOfFile(CharPredicate expected) throws ParseException {
            }
        });
        stack.push(Lisp.T);
    }

}
