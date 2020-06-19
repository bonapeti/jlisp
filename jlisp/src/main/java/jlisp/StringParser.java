package jlisp;

import java.util.Stack;

public class StringParser implements Parser {

    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException {
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
