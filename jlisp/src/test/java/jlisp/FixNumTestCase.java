package jlisp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class FixNumTestCase {

	@Test
	public void evaluate() {
		Fixnum fixNum = Fixnum.as(5);
		assertSame(fixNum, fixNum.evaluate(mock(Environment.class)));
	}
	
	@Test
	public void equal() {
		assertEquals(Fixnum.as(5), Fixnum.as(5));
		assertNotEquals(Fixnum.as(7), Fixnum.as(5));
	}

}
