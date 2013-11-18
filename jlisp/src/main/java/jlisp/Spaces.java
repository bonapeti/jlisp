package jlisp;

import java.util.Stack;

public class Spaces implements Parser {


    @Override
    public void parse(CharIterator charIterator, Stack<LispObject> stack) throws ParseException{
        charIterator.advanceUntil(new CharPredicate() {
            
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
