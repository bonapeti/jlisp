package jlisp;

import static syntax.Grammar.alterationOf;
import static syntax.Grammar.sequenceOf;
import static syntax.Grammar.repititionOf;
import static syntax.Grammar.whitespace;

import java.util.Stack;

public class ListExpressionParser implements Parser {

    private Parser listParser = alterationOf(sequenceOf(whitespace(), new FunctionNameParser(), repititionOf(new FunctionArgumentParser()), whitespace()), new EmptyListParser());

    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) {
        charIterator.expect('(');
        listParser.parse(charIterator, stack);
        charIterator.expect(')');
    }

    @Override
    public String toString() {
        return "list_expression";
    }
}
