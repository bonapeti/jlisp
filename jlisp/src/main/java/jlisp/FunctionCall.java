package jlisp;

import java.io.IOException;


public class FunctionCall implements SpecialForm, LispObject {

    private final Symbol name;
    private final Function function;
    
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
        appendable.append("#<Function ").append(String.valueOf(name)).append(">");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {
            print(sb);
            return sb.toString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
    
    @Override
    public boolean isTrue() {
        return true;
    }

}
