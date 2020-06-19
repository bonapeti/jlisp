package jlisp;

import java.util.Stack;

public class NilParser implements Parser {

    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException {
        lispCode.expect("nil");
        lispCode.checkNext(c -> {
            if (Character.isWhitespace(c) || c == ')') {
                return true;
            } else {
                throw new ParseException("Expecting nothing after 'nil' but found " + c);
            }
        }, new CharacterProcessor() {
            
            @Override
            protected void onEndOfFile(CharPredicate expected) throws ParseException {
            }
        });
        stack.push(Lisp.NIL);
    }

}
