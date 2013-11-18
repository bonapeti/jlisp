package jlisp;

import static syntax.Grammar.repititionOf;
import static syntax.Grammar.sequenceOf;
import static syntax.Grammar.whitespace;

import java.util.Stack;

public class ListExpressionParser implements Parser {

    private Parser listParser = sequenceOf(repititionOf(sequenceOf(whitespace(), new AtomExpressionParser())), whitespace());

    @Override
    public void parse(CharIterator charIterator, Stack<LispObject> stack) {
        charIterator.expect('(');
        
        Stack<LispObject> listStack = new Stack<LispObject>();
        listParser.parse(charIterator, listStack);
        
        charIterator.expect(')');
        
        IList list = Lisp.NIL;
        
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
