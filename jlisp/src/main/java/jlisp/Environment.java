package jlisp;

import java.util.Collection;

public interface Environment {

    boolean isVariableDefined(String name);
    
    Object evaluateFunction(String name, Collection<Object> arguments);
}
