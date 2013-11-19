package jlisp;

import java.io.IOException;

public class Quote implements LispObject {

    private LispObject target = null;
    
    public Quote(LispObject target) {
        this.target = target;
    }
    
    @Override
    public LispObject evaluate(Environment environment) {
        return target;
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

}
