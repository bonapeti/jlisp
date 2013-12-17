package jlisp;

public class FunctionForm implements SpecialForm {

    @Override
    public LispObject evaluate(List expressions, Environment environment) {
        Symbol symbol = Lisp.asSymbol(expressions.car());
        SpecialForm specialForm = environment.getSpecialForm(symbol);
        if (!(specialForm instanceof FunctionCall)) {
            throw new EvaluationException(symbol + " is not an ordinary function");
        }
        return (FunctionCall)specialForm;
    }

}
