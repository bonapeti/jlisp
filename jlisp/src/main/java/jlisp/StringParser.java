package jlisp;

import java.util.Stack;

import syntax.Code;
import syntax.ParseException;
import syntax.Parser;

public class StringParser implements Parser<LispObject> {

    @Override
    public void parse(Code lispCode, Stack<LispObject> stack) throws ParseException {
        StringBuilder sb = new StringBuilder();
        lispCode.expect('\"');
        lispCode.collectUntil(c -> c != '\"', sb);
        lispCode.expect('\"');
        stack.push(Lisp.string(sb.toString()));
    }

    @Override
    public String toString() {
        return "string";
    }
}
