package jlisp;

import java.util.Stack;

import jlisp.Fixnum;
import jlisp.LispObject;
import syntax.Code;
import syntax.ParseException;
import syntax.Parser;

public class FixnumParser implements Parser<LispObject>  {
    
    @Override
    public void parse(Code lispCode, Stack<LispObject> stack) throws ParseException{
        final StringBuilder sb = new StringBuilder();
        lispCode.expect(c -> {
            if (c == '-') {
                return true;
            }

            if (!Character.isDigit(c)) {
                throw new ParseException("Expected number but found " + c);
            } else {
                return true;
            }
        }, sb);
        lispCode.collectUntil(Character::isDigit, sb);
        stack.push(Fixnum.as(Integer.parseInt(sb.toString())));
    }
    
    @Override
    public String toString() {
        return "number";
    }
}
