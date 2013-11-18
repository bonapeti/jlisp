package jlisp;


public interface Function {

    Expression evaluate(IList arguments, Environment environment);

}
