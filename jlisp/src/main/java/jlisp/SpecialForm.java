package jlisp;


public interface SpecialForm {

	LispObject evaluate(IList expressions, Environment environment);
}
