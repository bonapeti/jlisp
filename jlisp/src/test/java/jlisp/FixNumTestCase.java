package jlisp;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

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
