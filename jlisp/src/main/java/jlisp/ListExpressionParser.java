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
        stack.push(new List());
        
        listParser.parse(charIterator, stack);
        
        charIterator.expect(')');
        
        Stack<Expression> listStack = new Stack<Expression>();
        Expression expression = stack.peek();
        while (! (expression instanceof List)) {
            expression = stack.pop();
            listStack.push(expression);
            expression = stack.peek();
        }
        
        List lispList = (List)stack.peek();
        
        while (!listStack.isEmpty()) {
            Expression listExpression = listStack.pop();
            lispList.add(listExpression);
        }
    }

    @Override
    public String toString() {
        return "list_expression";
    }
}
