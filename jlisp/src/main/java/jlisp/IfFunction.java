package jlisp;

public class IfFunction implements SpecialForm {

    @Override
    public LispObject evaluate(List expressions, Environment environment) {
        LispObject test = expressions.first().evaluate(environment);
        if (test.isTrue()) {
            return expressions.second().evaluate(environment);
        } else {
            return expressions.third().evaluate(environment);
        }
    }

}
