package jlisp;

import java.util.Stack;

public class Spaces implements Parser {


    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException{
        lispCode.advanceUntil(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                return Character.isWhitespace(c);
            }
        });

    }

    @Override
    public String toString() {
        return "S";
    }
}
