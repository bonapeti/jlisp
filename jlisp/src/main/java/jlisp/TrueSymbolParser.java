package jlisp;

import java.util.Stack;

import jlisp.LispObject;
import jlisp.Lisp;
import syntax.CharPredicate;
import syntax.CharacterProcessor;
import syntax.Code;
import syntax.ParseException;
import syntax.Parser;

public class TrueSymbolParser implements Parser<LispObject> {

    @Override
    public void parse(Code lispCode, Stack<LispObject> stack) throws ParseException {
        lispCode.expect('t');
        lispCode.checkNext(c -> {
            if (Character.isWhitespace(c) || c == ')') {
				return true;
				} else {
				throw new ParseException("Expecting nothing after 't' but found " + c);
				}
        }, new CharacterProcessor() {
            
            @Override
            protected void onEndOfFile(CharPredicate expected) throws ParseException {
            }
        });
        stack.push(Lisp.T);
    }

}
