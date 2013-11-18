package jlisp;

/**
 * LISP S-expression
 * @author bonapeti
 *
 */
public interface Expression {

	Expression evaluate(Environment environment);
}
