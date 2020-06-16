package jlisp;

import java.io.IOException;

public class Quote implements LispObject {

    private final LispObject target;
    private final QuoteFunction quoteFunction = new QuoteFunction();
    
    public Quote(LispObject target) {
        this.target = target;
    }
    
    @Override
    public LispObject evaluate(Environment environment) {
        return quoteFunction.evaluate(new ConsCell(target), environment);
    }

    @Override
    public void print(Appendable appendable) throws IOException {
        appendable.append('\'');
        target.print(appendable);
    }

    @Override
    public boolean isTrue() {
        return target.isTrue();
    }

    @Override
    public String toString() {
        return "'" + target.toString();
    }
}
