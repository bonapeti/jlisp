package jlisp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import syntax.Code;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Parsing nil")
public class NilParserTestCase {

    Stack<LispObject> stack = new Stack<>();
    NilParser symbolParser = new NilParser();

    @Test @DisplayName("Parsing 'nil'")
    public void parseNil() {

        Code chars = new Code("nil");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.NIL,stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }
    
    @Test @DisplayName("Parsing 'nil '")
    public void parseNilSpace() {

        Code chars = new Code("nil ");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.NIL,stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }
    
    @Test @DisplayName("Parsing 'nil) '")
    public void parseTSpaceWithClosingList() {

        Code chars = new Code("nil) ");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.NIL,stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }

}
