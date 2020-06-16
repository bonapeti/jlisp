package syntax;

import jlisp.Environment;
import jlisp.LispObject;

import java.io.IOException;

public class MockLispObject implements LispObject {

    @Override
    public LispObject evaluate(Environment environment) {
        return null;
    }

    @Override
    public void print(Appendable appendable) throws IOException {

    }

    @Override
    public boolean isTrue() {
        return true;
    }
}
