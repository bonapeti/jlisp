package syntax;

import java.util.Stack;

public class Spaces<T> implements Parser<T> {


    @Override
    public void parse(Code lispCode, Stack<T> stack) throws ParseException{
        lispCode.advanceUntil(Character::isWhitespace);

    }

    @Override
    public String toString() {
        return "S";
    }
}
