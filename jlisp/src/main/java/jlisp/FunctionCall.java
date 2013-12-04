package jlisp;

import java.io.IOException;


public class FunctionCall implements SpecialForm, LispObject {

    private Symbol name = null;
    private Function function = null;
    
    public FunctionCall(Symbol name, Function function) {
        this.name = name;
        this.function = function;
    }
    
    @Override
    public LispObject evaluate(List expressions, final Environment environment) {
        return function.evaluate(expressions.map(new Function1<LispObject, LispObject>() {
			
			@Override
			public LispObject apply(LispObject p) {
				return p.evaluate(environment);
			}
		}), environment);
    }

    @Override
    public LispObject evaluate(Environment environment) {
        return this;
    }

    @Override
    public void print(Appendable appendable) throws IOException {
        appendable.append("#<Function " + name + " {" + function.hashCode() + "}>");
    }

    @Override
    public boolean isTrue() {
        return true;
    }

}
