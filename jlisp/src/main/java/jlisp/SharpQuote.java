package jlisp;

import java.io.IOException;

public class SharpQuote implements LispObject {

    private Symbol symbol = null;
    private final FunctionForm functionForm = new FunctionForm();
    
    public SharpQuote(Symbol symbol) {
        this.symbol = symbol;
    }
    
    @Override
    public LispObject evaluate(Environment environment) {
        return functionForm.evaluate(new ConsCell(symbol), environment);
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
