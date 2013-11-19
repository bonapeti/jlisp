package jlisp;

public class QuoteFunction implements SpecialForm {

    @Override
    public LispObject evaluate(IList expressions, Environment environment) {
        return expressions.first();
    }

}
