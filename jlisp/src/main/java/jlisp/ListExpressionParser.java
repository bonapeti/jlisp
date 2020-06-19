package jlisp;

import java.util.Stack;

import syntax.Code;
import syntax.Parser;

public class ListExpressionParser implements Parser<LispObject> {

    private final Parser<LispObject> listParser = Lisp.sequenceOf(Lisp.repititionOf(Lisp.sequenceOf(Lisp.whitespace(), new AtomExpressionParser())), Lisp.whitespace());

    @Override
    public void parse(Code lispCode, Stack<LispObject> stack) {
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
