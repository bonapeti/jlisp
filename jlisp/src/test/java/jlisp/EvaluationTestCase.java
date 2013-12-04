package jlisp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
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
        assertEvaluation(" (cons 'rice '(and beans)) ", "(RICE AND BEANS)");
        assertEvaluation(" (cons '(here today) '(gone tomorrow)) ", "((HERE TODAY) GONE TOMORROW)");
        
        //assertEvaluation(" (cons 'a 'b) ", "(A . B)");
    }
	
	@Test
    public void list() {
        assertEvaluation(" (list 'rice '(and beans)) ", "(RICE (AND BEANS))");
        assertEvaluation(" (list '(here today) '(gone tomorrow)) ", "((HERE TODAY) (GONE TOMORROW))");
        
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
    public void quote() {
        assertEvaluation(" (quote foo) ", "FOO");
        assertEvaluation(" (quote (hello world)) ", "(HELLO WORLD)");
        
        assertEvaluation(" 'foo ", "FOO");
    }
	
	@Test
    public void equal() {
        assertEvaluation(" (equal 1 1) ", "T");
        assertEvaluation(" (quote (hello world)) ", "(HELLO WORLD)");
        
        assertEvaluation(" 'foo ", "FOO");
    }
	
	@Test
    public void eval() {
        assertEvaluation(" (eval '(+ 2 2)) ", "4");
    }
	
	@Test
    public void ifTest() {
        assertEvaluation(" (if t 1 2) ", "1");
        assertEvaluation(" (if (oddp 2) 1 2) ", "2");
    }
	
	@Test
    public void smaller() {
        assertEvaluation(" (< 1 2) ", "T");
        assertEvaluation(" (< 1 1) ", "NIL");
        assertEvaluation(" (< 2 1) ", "NIL");
    }
	
	@Test
    public void cond() {
	    assertEvaluation(" (cond) ", "NIL");
	    
	    commonLisp.evaluate("(defun compare (x y) (cond " +
	    		"((equal x y) 'numbers-are-the-same)" +
	    		"((< x y) 'first-is-smaller)" +
	    		"((> x y) 'first-is-bigger)" +
	    		" ))");
	    
	    
        assertEvaluation(" (compare 3 5) ", "first-is-smaller");
        assertEvaluation(" (compare 7 2) ", "first-is-bigger");
        assertEvaluation(" (compare 4 4) ", "numbers-are-the-same");
    }
	
	@Test
    public void and() {
        assertEvaluation(" (and) ", "T");
        assertEvaluation(" (and t) ", "T");
        assertEvaluation(" (and nil) ", "nil");
        assertEvaluation(" (and t nil) ", "NIL");
        assertEvaluation(" (and 1 2) ", "2");
    }
	
	@Test
    public void or() {
        assertEvaluation(" (or) ", " nil ");
        assertEvaluation(" (or t) ", "T");
        assertEvaluation(" (or nil) ", "nil");
        assertEvaluation(" (or t nil) ", "t");
        assertEvaluation(" (or nil t) ", "t");
    }
	
	@Test
    public void setf() {
        assertEvaluation(" (setf) ", " nil ");
        
        assertEvaluation(" (setf a 3) ", " 3 ");
        assertEvaluation(" a ", " 3 ");
    }
	
	@Test
    public void letStar() {
	    commonLisp.evaluate(" (defun average (x y)" +
        		            "  (let* ((sum (+ x y))) " +
        		            "  (list x y 'average 'is (/ sum 2))" +
        		            ")) ");
        
        assertEvaluation(" (average 3 7) ", " (3 7 AVERAGE IS 5) ");
        
        commonLisp.evaluate(" (defun price-change (old new)" +
                "  (let* ( (diff (- new old)) " +
                "         (propotion (/ diff old))" +
                "         (percentage (* propotion 100))) " +
                "  (list 'widget 'changed 'by percentage 'percent)" +
                ")) ");

        assertEvaluation(" (price-change 100 500) ", " (WIDGET CHANGED BY 400 PERCENT) ");
    }
	
	@Test
	public void append() {
        assertEvaluation("(append '(friends romans) '(and countrymen))","(FRIENDS ROMANS AND COUNTRYMEN)");
        assertEvaluation("(append '(l m n o) '(p q r))","(L M N O P Q R)");
        assertEvaluation("(append '(april showers) nil)","(APRIL SHOWERS)");
        assertEvaluation("(append nil '(bring may flowers))","(BRING MAY FLOWERS)");
        assertEvaluation("(append nil nil)","NIL");
        assertEvaluation("(append '((a 1) (b 2)) '((c 3) (d 4)))","((A 1) (B 2) (C 3) (D 4))");
        assertEvaluation("(setf who '(only the good))","(ONLY THE GOOD)");
        assertEvaluation("(append who '(die young))","(ONLY THE GOOD DIE YOUNG)");
        assertEvaluation("who","(ONLY THE GOOD)");
        assertEvaluation("(append 'a '(b c d))","A is not a LIST");
        assertEvaluation("(append '(a b c) '(d))","(A B C D)");
        
        assertEvaluation("(append '(here today) '(gone tomorrow))","(HERE TODAY GONE TOMORROW)");
	}
	

    @Test
    public void reverse() {
        assertEvaluation("(reverse '(one two three four five))","(FIVE FOUR THREE TWO ONE)");
        assertEvaluation("(reverse '(l i v e))","(E V I L)");
        assertEvaluation("(reverse 'live)","LIVE is not a LIST");
    }
	
    
    @Test @Ignore
    public void nthcdr() {
        assertEvaluation("(nthcdr 0 '(a b c))","(a b c)");
    }
    
    @Test
    public void last() {
        assertEvaluation("(last '(all is forgiven))","(forgiven)");
        assertEvaluation("(last nil)"," nil ");
        assertEvaluation("(last 'nevermore)","NEVERMORE is not a LIST");
    }
    
    @Test
    public void remove() {
        assertEvaluation("(remove 'a '(b a n a n a))","(b n n)");
        assertEvaluation("(remove 1 '(3 1 4 1 5 9))","(3 4 5 9)");
    }
    
    @Test
    public void member() {
        commonLisp.evaluate("(setf ducks '(huey dewey louie))");
        assertEvaluation("(member 'huey ducks)","(HUEY DEWEY LOUIE)");
        assertEvaluation("(member 'dewey ducks)","(DEWEY LOUIE)");
        assertEvaluation("(member 'louie ducks)","(LOUIE)");
        assertEvaluation("(member 'mickey ducks)","nil");
    }
    
    @Test
    public void intersection() {
        assertEvaluation("(intersection '(fred john mary) '(sue mary fred))","(FRED MARY)");
        assertEvaluation("(intersection '(a s d f g) '(v w s r a))","(A S)");
        assertEvaluation("(intersection '(foo bar baz) '(xam gorp bletch))"," nil ");
    }
    
    @Test
    public void union() {
        assertEvaluation("(union '(finger hand arm) '(toe finger foot leg))","(FINGER HAND ARM TOE FOOT LEG)");
        assertEvaluation("(union '(fred john mary) '(sue mary fred))","(FRED JOHN MARY SUE)");
        assertEvaluation("(union '(a s d f g) '(v w s r a))","(A S D F G V W R)");
    }
    
    @Test
    public void setDifference() {
        assertEvaluation("(set-difference '(alpha bravo charlie delta) '(bravo charlie))","(ALPHA DELTA)");
        assertEvaluation("(set-difference '(alpha bravo charlie delta) '(echo alpha foxtrot))","(BRAVO CHARLIE DELTA)");
        assertEvaluation("(set-difference '(alpha bravo) '(bravo alpha))","nil");
    }
    
    @Test
    public void subsetp() {
        assertEvaluation("(subsetp '(a i) '(a e i o u))","t");
        assertEvaluation("(subsetp '(a x) '(a e i o u))","nil");
    }
    
    @Test
    @Ignore
    public void sharpQuote() {
        assertEvaluation("(defun double (x) (* x 2))","DOUBLE");
        assertEvaluation("(double 2)","4");
        assertEvaluation("#'double","#<Function DOUBLE {}>");
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
            String replyString = reply.toString().trim();
            String expectedString = expectedReply.trim();
            assertTrue("Expected: " + expectedString + " but was: " + replyString, expectedString.equalsIgnoreCase(replyString));    
        } catch (Exception pe) {
            assertEquals(expectedReply, pe.getMessage());
        }
	}
}
