package jlisp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EvaluationTestCase {

	private CommonLisp commonLisp = null;
	
	@Before
	public void setup() {
		commonLisp = new CommonLisp();
	}
	
	@Test
    public void t() {
		assertEquals(Lisp.T, commonLisp.evaluate(" t "));
    }
	
	@Test
    public void nil() {
		assertEquals(Lisp.NIL, commonLisp.evaluate(" nil "));
    }
	
	@Test
    public void numberp() {
		assertEquals(Lisp.T, commonLisp.evaluate(" (numberp 4) "));
		assertEquals(Lisp.NIL, commonLisp.evaluate(" (numberp t) "));
		assertEquals(Lisp.NIL, commonLisp.evaluate(" (numberp nil) "));
    }
	
	@Test
    public void defun_and_eval() {
        commonLisp.evaluate("(defun a (b) (+ b 10) (+ b 34))");

        assertEquals(new Fixnum(37), commonLisp.evaluate("(a 3)"));
        
        
        
    }

}
