package jlisp;

import static syntax.Grammar.repititionOf;
import static syntax.Grammar.sequenceOf;
import static syntax.Grammar.whitespace;

import java.util.Stack;

public class ListExpressionParser implements Parser {

    private Parser listParser = sequenceOf(repititionOf(sequenceOf(whitespace(), new AtomExpressionParser())), whitespace());

    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) {
        charIterator.expect('(');
        
        Stack<Expression> listStack = new Stack<Expression>();
        listParser.parse(charIterator, listStack);
        
        charIterator.expect(')');
        
        List list = new List();
        
        for (Expression listExpression : listStack) {
            list.add(listExpression);
        }
        stack.push(list);
    }

    @Override
    public String toString() {
        return "list_expression";
    }
}
