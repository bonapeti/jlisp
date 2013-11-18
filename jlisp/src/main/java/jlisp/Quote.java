package jlisp;

public class Quote implements SpecialForm {

    @Override
    public Expression evaluate(IList expressions, Environment environment) {
        return expressions;
    }

}
