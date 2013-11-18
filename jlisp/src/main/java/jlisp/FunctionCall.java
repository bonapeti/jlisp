package jlisp;


public class FunctionCall implements SpecialForm {

    private Function function = null;
    
    public FunctionCall(Function function) {
        this.function = function;
    }
    
    @Override
    public LispObject evaluate(IList expressions, final Environment environment) {
        return function.evaluate(expressions.map(new Function1<LispObject, LispObject>() {
			
			@Override
			public LispObject apply(LispObject p) {
				return p.evaluate(environment);
			}
		}), environment);
    }

}
