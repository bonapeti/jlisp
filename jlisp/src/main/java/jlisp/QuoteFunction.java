package jlisp;

public class QuoteFunction implements SpecialForm {

    @Override
    public LispObject evaluate(List expressions, Environment environment) {
        return expressions.first();
    }

}
