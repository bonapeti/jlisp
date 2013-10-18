package jlisp;

import java.util.Collection;

public interface FunctionDefinition {

    Object evaluate(Collection<Object> arguments);

}
