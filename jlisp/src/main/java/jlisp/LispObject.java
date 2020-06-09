package jlisp;

import java.io.IOException;

/**
 * LISP S-expression, it can be evaluated in a given {@link Environment}
 * @author bonapeti
 *
 */
public interface LispObject {

	/**
	 * Evaluates this Lisp object in the given environment
	 * @param environment
	 * @return the result of the evaluation
	 */
	LispObject evaluate(Environment environment);

	/**
	 * Readable representaion of Lisp object
	 * @param appendable
	 * @throws IOException
	 */
	void print(Appendable appendable) throws IOException;

	/**
	 * All List object has a Truth value, this returns it
	 * @return the truth value of the List object
	 */
	boolean isTrue();
}
