package jlisp;

import java.util.Stack;

import static syntax.Grammar.*;

public class ListExpressionParser implements Parser {

    private final Parser listParser = sequenceOf(repititionOf(sequenceOf(whitespace(), new AtomExpressionParser())), whitespace());

    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) {
        lispCode.expect('(');
        
        Stack<LispObject> listStack = new Stack<>();
        listParser.parse(lispCode, listStack);
        
        lispCode.expect(')');
        
        List list = Lisp.NIL;
        
        for (LispObject listExpression : listStack) {
            list = list.append(listExpression);
        }
        stack.push(list);
    }

    @Override
    public String toString() {
        return "list_expression";
    }
}
