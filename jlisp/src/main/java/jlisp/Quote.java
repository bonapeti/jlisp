package jlisp;

public class Quote implements SpecialForm {

    @Override
    public LispObject evaluate(IList expressions, Environment environment) {
        return expressions.first();
    }

}
