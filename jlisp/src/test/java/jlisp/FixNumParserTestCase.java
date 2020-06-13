package jlisp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Parsing fixnum type")
public class FixNumParserTestCase {

    Stack<LispObject> stack = new Stack<>();
    FixnumParser parser = new FixnumParser();

	@Test @DisplayName("Parsing \"1\" should result 1 as number")
    public void parse1() {

        LispCode chars = new LispCode("1");
        parser.parse(chars, stack);

        assertEquals(Fixnum.as(1),stack.pop());
        assertEquals(1, chars.getCurrentPosition());
    }
	
	@Test @DisplayName("Parsing \"-1\" should result -1 as number")
    public void parseMinus1() {

        LispCode chars = new LispCode("-1");
        parser.parse(chars, stack);

        assertEquals(Fixnum.as(-1),stack.pop());
        assertEquals(2, chars.getCurrentPosition());
    }
}
