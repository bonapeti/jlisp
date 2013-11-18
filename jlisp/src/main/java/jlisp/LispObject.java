package jlisp;

import java.io.IOException;

/**
 * LISP S-expression
 * @author bonapeti
 *
 */
public interface LispObject {

	LispObject evaluate(Environment environment);
	
	void print(Appendable appendable) throws IOException;
}
