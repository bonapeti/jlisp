package jlisp;


public interface SpecialForm {

	LispObject evaluate(List expressions, Environment environment);
}
