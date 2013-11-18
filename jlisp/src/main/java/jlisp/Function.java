package jlisp;


public interface Function {

    LispObject evaluate(IList arguments, Environment environment);

}
