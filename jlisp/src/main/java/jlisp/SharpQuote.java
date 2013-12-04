package jlisp;

import java.io.IOException;

public class SharpQuote implements LispObject {

    private Symbol symbol = null;
    
    public SharpQuote(Symbol symbol) {
        this.symbol = symbol;
    }
    
    @Override
    public LispObject evaluate(Environment environment) {
        SpecialForm specialForm = environment.getSpecialForm(symbol);
        if (!(specialForm instanceof FunctionCall)) {
            throw new EvaluationException(symbol + " is not an ordinary function");
        }
        return (FunctionCall)specialForm;
    }

    @Override
    public void print(Appendable appendable) throws IOException {
        appendable.append("#'");
        symbol.print(appendable);
    }

    @Override
    public boolean isTrue() {
        return true;
    }

}
