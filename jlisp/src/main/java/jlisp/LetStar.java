package jlisp;

public class LetStar implements SpecialForm {

    private Setf setf = new Setf();
    
    @Override
    public LispObject evaluate(List expressions, final Environment environment) {
        List valuePairs = Lisp.asList(expressions.first());
        
        valuePairs.foreach(new VoidFunction() {
            
            @Override
            public void apply(LispObject object) {
                setf.evaluate(Lisp.asList(object), environment);
                
            }
        });
        return expressions.second().evaluate(environment);
    }

}
