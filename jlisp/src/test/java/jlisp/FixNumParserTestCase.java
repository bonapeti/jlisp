package jlisp;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class FixNumParserTestCase {

	@Test
    public void parse1() {
        Stack<LispObject> stack = new Stack<>();
        FixnumParser parser = new FixnumParser();
        LispCode chars = new LispCode("1");
        parser.parse(chars, stack);
        assertEquals(new Fixnum(1),stack.pop());
        assertEquals(1, chars.getCurrentPosition());
    }
	
	@Test
    public void parseMinus1() {
        Stack<LispObject> stack = new Stack<>();
        FixnumParser parser = new FixnumParser();
        LispCode chars = new LispCode("-1");
        parser.parse(chars, stack);
        assertEquals(new Fixnum(-1),stack.pop());
        assertEquals(2, chars.getCurrentPosition());
    }
}
