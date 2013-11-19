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
		assertEvaluation(" t ", "T");
    }
	
	@Test
    public void nil() {
	    assertEvaluation(" nil ", "NIL");
    }
	
	@Test
    public void list_1_2() {
        assertEvaluation(" (list 1 2 3) ", "(1 2 3)");
    }
	
	@Test
    public void numberp() {
	    assertEvaluation(" (numberp 4) ", "T");
	    assertEvaluation(" (numberp t) ", "NIL");
	    assertEvaluation(" (numberp nil) ", "NIL");
    }
	
	@Test
    public void oddp() {
        assertEvaluation(" (oddp 28) ", "NIL");
        assertEvaluation(" (oddp 27) ", "T");
    }
	
	@Test
    public void evenp() {
        assertEvaluation(" (evenp 28) ", "T");
        assertEvaluation(" (evenp 27) ", "NIL");
    }
	
	@Test
    public void not() {
        assertEvaluation(" (not t) ", "NIL");
        assertEvaluation(" (not nil) ", "T");
    }
	
	@Test
    public void length() {
	    assertEvaluation(" (length ()) ", "0");
	    assertEvaluation(" (length (list 1)) ", "1");
	    assertEvaluation(" (length (list 1 3 4)) ", "3");
    }
	
	@Test
    public void first() {
        assertEvaluation(" (first (list 1)) ", "1");
        assertEvaluation(" (first (list 1 3 4)) ", "1");
    }
	
	@Test
    public void second() {
        assertEvaluation(" (second (list 1)) ", "NIL");
        assertEvaluation(" (second (list 1 2)) ", "2");
    }
	
	@Test
    public void third() {
        assertEvaluation(" (third (list 1)) ", "NIL");
        assertEvaluation(" (third (list 1 2)) ", "NIL");
        assertEvaluation(" (third (list 1 2 3)) ", "3");
    }

	@Test
    public void rest() {
        assertEvaluation(" (rest (list 1)) ", "NIL");
        assertEvaluation(" (rest (list 1 2)) ", "(2)");
        assertEvaluation(" (rest (list 1 2 3)) ", "(2 3)");
    }
	
	@Test
    public void cons() {
        assertEvaluation(" (cons 1 (list 2)) ", "(1 2)");
    }
	
	@Test
    public void listp() {
        assertEvaluation(" (listp 1) ", "NIL");
        assertEvaluation(" (listp (list 1)) ", "T");
    }
	
	@Test
    public void car() {
        assertEvaluation(" (car (list 1)) ", "1");
    }
	
	@Test
    public void defun_and_eval() {
        commonLisp.evaluate("(defun a (b) (+ b 10) (+ b 34))");

        assertEvaluation("(a 3)", "37");
    }

	void assertEvaluation(String line, String expectedReply) {
	    try {
            StringBuilder reply = new StringBuilder();
            commonLisp.evaluate(line).print(reply);
            assertEquals(expectedReply, reply.toString());    
        } catch (Exception pe) {
            assertEquals(expectedReply, pe.getMessage());
        }
	}
}
