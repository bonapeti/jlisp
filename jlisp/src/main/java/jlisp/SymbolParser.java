package jlisp;

import java.util.Stack;

import jlisp.LispObject;
import jlisp.Symbol;
import syntax.CharPredicate;
import syntax.CharacterProcessor;
import syntax.Code;
import syntax.ParseException;
import syntax.Parser;

public class SymbolParser implements Parser<LispObject> {

    @Override
    public void parse(Code lispCode, Stack<LispObject> stack) throws ParseException {
        final StringBuilder sb = new StringBuilder();
        lispCode.expect(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                if (Character.isWhitespace(c)) {
                    return false;
                }
                if ( (c == '(') || (c == ')')) {
                    return false;
                }
                return c != '\'';
            }
            
            @Override
            public String toString() {
                return "SYMBOL";
            }
        }, sb);
        lispCode.collectUntil(c -> Character.isLetter(c) || Character.isDigit(c) || c == '-' || c == '*', sb);
        lispCode.checkNext(c -> {
            if (Character.isWhitespace(c) || c == ')') {
                return true;
            } else {
                throw new ParseException("Expecting nothing after '" +  sb.toString() +"' but found " + c);
            }
        }, new CharacterProcessor() {
            
            @Override
            protected void onEndOfFile(CharPredicate expected) throws ParseException {
            }
        });
        if ("t".equals(sb.toString())) {
            throw new ParseException("'t' is cannot be used as variable");
        }
        if ("nil".equals(sb.toString())) {
            throw new ParseException("'nil' is cannot be used as variable");
        }
        stack.push(new Symbol(sb.toString()));
    }

    @Override
    public String toString() {
        return "symbol";
    }
}
