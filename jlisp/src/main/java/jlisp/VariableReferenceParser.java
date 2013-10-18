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
        charIterator.advanceUntil(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                return Character.isLetter(c);
            }
        }, sb);
        if ("t".equals(sb.toString())) {
            throw new ParseException("'t' is cannot be used as variable");
        }
        if ("nil".equals(sb.toString())) {
            throw new ParseException("'nil' is cannot be used as variable");
        }
        stack.push(new VariableReference(sb.toString()));
    }

    @Override
    public String toString() {
        return "variable_reference";
    }
}
