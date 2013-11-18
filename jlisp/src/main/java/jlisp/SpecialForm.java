package jlisp;


public interface SpecialForm {

	Expression evaluate(IList expressions, Environment environment);
}
