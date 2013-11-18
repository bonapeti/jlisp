package jlisp;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

import org.junit.Test;

public class FixNumTestCase {

	@Test
	public void evaluate() {
		Fixnum fixNum = new Fixnum(5);
		assertSame(fixNum, fixNum.evaluate(mock(Environment.class)));
	}
	
	@Test
	public void equal() {
		assertEquals(new Fixnum(5), new Fixnum(5));
		assertFalse(new Fixnum(7).equals(new Fixnum(5)));
	}

}
