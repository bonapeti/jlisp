package jlisp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Parsing nil")
public class NilParserTestCase {

    Stack<LispObject> stack = new Stack<>();
    NilParser symbolParser = new NilParser();

    @Test @DisplayName("Parsing 'nil'")
    public void parseNil() {

        LispCode chars = new LispCode("nil");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.NIL,stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }
    
    @Test @DisplayName("Parsing 'nil '")
    public void parseNilSpace() {

        LispCode chars = new LispCode("nil ");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.NIL,stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }
    
    @Test @DisplayName("Parsing 'nil) '")
    public void parseTSpaceWithClosingList() {

        LispCode chars = new LispCode("nil) ");
        symbolParser.parse(chars, stack);
        assertEquals(Lisp.NIL,stack.pop());
        assertEquals(3, chars.getCurrentPosition());
    }

}
