package jlisp;

import java.util.HashMap;
import java.util.Map;

public class CallEnvironment implements Environment {

    private Environment parent = null;
    
    Map<Symbol, LispObject> values = new HashMap<>();
    
    public CallEnvironment(Environment parent) {
        this.parent = parent;
    }

    @Override
    public SpecialForm getSpecialForm(Symbol name) {
        return parent.getSpecialForm(name);
    }

    @Override
    public void defineFunction(Symbol name, Function definition) {
        throw new UnsupportedOperationException("Function cannot be defined in function call context!");
    }

    @Override
    public void bindValue(Symbol symbol, LispObject value) {
        values.put(symbol, value);
    }

    @Override
    public LispObject getValue(Symbol symbol) {
    	LispObject value = values.get(symbol);
        if (value == null) {
            return parent.getValue(symbol);
        }
        return value;
    }

}
