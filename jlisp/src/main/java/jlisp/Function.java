package jlisp;


public interface Function {

    LispObject evaluate(List arguments, Environment environment);

}
