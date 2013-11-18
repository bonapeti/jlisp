package jlisp;

import java.util.Stack;

public class NilParser implements Parser {

    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) throws ParseException {
        charIterator.expect("nil");
        charIterator.checkNext(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                if (Character.isWhitespace(c) || c == ')') {
                    return true;
                } else {
                    throw new ParseException("Expecting nothing after 'nil' but found " + c);
                }
            }
        }, new CharacterProcessor() {
            
            @Override
            protected void onEndOfFile(CharPredicate expected) throws ParseException {
            }
        });
        stack.push(Lisp.NIL);
    }

}
