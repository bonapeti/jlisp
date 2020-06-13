package jlisp;

/**
 * Lisp function
 */
public interface Function {

    LispObject evaluate(List arguments, Environment environment);

}
