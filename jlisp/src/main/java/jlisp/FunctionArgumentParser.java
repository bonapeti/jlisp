package jlisp;

import static syntax.Grammar.sequenceOf;
import static syntax.Grammar.whitespace;

import java.util.Stack;

public class FunctionArgumentParser implements Parser {

    private Parser parser = sequenceOf(whitespace(), new AtomExpressionParser());
    
    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) throws ParseException {
        parser.parse(charIterator, stack);
        Expression lastExpression = stack.pop();
        Expression lastLastExpression = stack.peek();
        if (!(lastLastExpression instanceof FunctionCall)) {
            throw new ParseException("Missing function for argument!");
        }
        FunctionCall functionCall = (FunctionCall)lastLastExpression;
        functionCall.addArgument(lastExpression);
    }

}
