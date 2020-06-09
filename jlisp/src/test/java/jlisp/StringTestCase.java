package jlisp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class StringTestCase {

	@Test
	public void evaluate() {
		LispString string = new LispString("a");
		assertSame(string, string.evaluate(mock(Environment.class)));
	}
	
	@Test
    public void to_string() {
        LispString string = new LispString("a");
        assertEquals("\"a\"", string.toString());
    }
	
	@Test
	public void equal() {
		assertEquals(new LispString("5"), new LispString("5"));
		assertNotEquals(new LispString("7"), new LispString("5"));
	}

}
