package jlisp;

import java.util.Collection;

public interface Function {

    Expression evaluate(IList arguments, Environment environment);

}
