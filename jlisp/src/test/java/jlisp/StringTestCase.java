package jlisp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class StringTestCase {

	@Test
	public void evaluate() {
		LispString string = new LispString("a");
		assertSame(string, string.evaluate(mock(Environment.class)));
	}

	@Test
	public void equal() {
		assertEquals(new LispString("5"), new LispString("5"));
		assertNotEquals(new LispString("7"), new LispString("5"));
	}

}
