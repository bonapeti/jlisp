package jlisp;

import java.util.HashMap;
import java.util.Map;

/**
 * The main environment (name value pairs) during evaluation
 */
public class GlobalEnvironment implements Environment {

    final Map<Symbol,SpecialForm> specialForms = new HashMap<>();
    final Map<Symbol, LispObject> values = new HashMap<>();

    @Override
    public SpecialForm getSpecialForm(Symbol name) {
        if (!specialForms.containsKey(name)) {
            throw new EvaluationException("The function " + name + " is undefined.");
        }
        return specialForms.get(name);
    }
    
    public void defineSpecialForm(String name, SpecialForm specialForm) {
        specialForms.put(new Symbol(name), specialForm);
    }
    
    public void defineSpecialForm(Symbol name, SpecialForm specialForm) {
        specialForms.put(name, specialForm);
    }

    @Override
    public void defineFunction(Symbol name, Function definition) {
        defineSpecialForm(name, new FunctionCall(name, definition));
    }
    
    public void defineFunction(String name, Function definition) {
        defineFunction(new Symbol(name), definition);
    }

    @Override
    public void bindValue(Symbol symbol, LispObject value) {
        values.put(symbol, value);
    }

    @Override
    public LispObject getValue(Symbol symbol) {
    	LispObject value = values.get(symbol);
        if (value == null) {
            throw new EvaluationException("Variable " + symbol.toString() + " has no value");
        }
        return value;
    }
}