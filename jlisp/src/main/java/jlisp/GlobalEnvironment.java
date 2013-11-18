package jlisp;

import java.util.HashMap;
import java.util.Map;

public class GlobalEnvironment implements Environment {

    Map<Symbol,SpecialForm> specialForms = new HashMap<Symbol,SpecialForm>();
    Map<Symbol, Expression> values = new HashMap<Symbol, Expression>();

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
        defineSpecialForm(name, new FunctionCall(definition));
    }
    
    public void defineFunction(String name, Function definition) {
        defineFunction(new Symbol(name), definition);
    }

    @Override
    public void bindValue(Symbol symbol, Expression value) {
        values.put(symbol, value);
    }

    @Override
    public Expression getValue(Symbol symbol) {
    	Expression value = values.get(symbol);
        if (value == null) {
            throw new EvaluationException("Variable " + symbol.toString() + " has no value");
        }
        return value;
    }
}