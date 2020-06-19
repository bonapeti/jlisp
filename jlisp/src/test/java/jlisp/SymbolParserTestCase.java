package jlisp;

import org.junit.jupiter.api.Test;

import syntax.Code;
import syntax.ParseException;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class SymbolParserTestCase {

    private static final String EXPECTED_SYMBOL_BUT_FOUND_END_OF_FILE = "Expected SYMBOL but found end of file.";
    private final Stack<LispObject> stack = new Stack<>();
    private final SymbolParser parser = new SymbolParser();

    @Test
    public void parseEmpty() {
        Code chars = new Code("");
        try {
            parser.parse(chars, stack);
            fail("Should have failed with '" + EXPECTED_SYMBOL_BUT_FOUND_END_OF_FILE + "'");
        } catch (ParseException pe) {
            assertEquals(EXPECTED_SYMBOL_BUT_FOUND_END_OF_FILE, pe.getMessage());
            assertTrue(chars.isAtBeginning());
        }
    }

    
    @Test
    public void parseArg() {

        Code chars = new Code("arg");
        parser.parse(chars, stack);
        assertEquals(new Symbol("arg"),stack.pop());
        assertFalse(chars.hasMore());
    }

    @Test
    public void space() {
        assertThrows(ParseException.class, () -> parser.parse(new Code(" "), stack));
    }
    
    @Test
    public void spaceA() {
        assertThrows(ParseException.class, () -> parser.parse(new Code(" a"), stack));
    }
    
    @Test
    public void quoted() {
        assertThrows(ParseException.class, () -> parser.parse(new Code("'a"), stack));
    }

    @Test
    public void aSpace() {

        Code chars = new Code("a ");
        parser.parse(chars, stack);

        assertEquals(new Symbol("a"), stack.pop());
        assertTrue(stack.isEmpty());

        assertTrue(chars.hasMore());
    }

}
