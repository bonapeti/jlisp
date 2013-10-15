package jlisp;

import java.util.Stack;

public class VariableReferenceParser implements Parser {

    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) throws ParseException {
        final StringBuilder sb = new StringBuilder();
        charIterator.expect(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                if (Character.isLetter(c)) {
                    return true;
                } else {
                    throw new ParseException("Expecting variable but found '" + c + "'");
                }
            }
        }, sb);
        stack.push(new VariableReference(sb.toString()));
    }

    @Override
    public String toString() {
        return "variable_reference";
    }
}
