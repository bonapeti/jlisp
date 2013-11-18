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
