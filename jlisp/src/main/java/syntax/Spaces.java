package syntax;

import java.util.Stack;

public class Spaces<T> implements Parser<T> {


    @Override
    public void parse(Code lispCode, Stack<T> stack) throws ParseException{
        lispCode.advanceUntil(c -> Character.isWhitespace(c));

    }

    @Override
    public String toString() {
        return "S";
    }
}
