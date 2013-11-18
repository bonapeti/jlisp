package jlisp;


public class FunctionCall implements SpecialForm {

    private Function function = null;
    
    public FunctionCall(Function function) {
        this.function = function;
    }
    
    @Override
    public Expression evaluate(IList expressions, final Environment environment) {
        return function.evaluate(expressions.map(new Function2<Expression, Expression>() {
			
			@Override
			public Expression apply(Expression p) {
				return p.evaluate(environment);
			}
		}), environment);
    }

}
