package jlisp;

public class LetStar implements SpecialForm {

    private final Setf setf = new Setf();
    
    @Override
    public LispObject evaluate(List expressions, final Environment environment) {
        List valuePairs = List.of(expressions.first());
        
        valuePairs.foreach(object -> setf.evaluate(List.of(object), environment));
        return expressions.second().evaluate(environment);
    }

}
