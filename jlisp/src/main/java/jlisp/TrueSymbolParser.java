package jlisp;

import java.util.Stack;

public class TrueSymbolParser implements Parser {

    public TrueSymbolParser() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) throws ParseException {
        charIterator.expect('t');
        charIterator.expect(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                if (Character.isWhitespace(c)) {
                    return true;
                } else {
                    throw new ParseException("Expecting whitespace but found " + c);
                }
            }
        }, new CharacterProcessor() {
            
            @Override
            protected void onEndOfFile(CharPredicate expected) throws ParseException {
            }
        });
        stack.push(new TrueSymbol());
    }

}
