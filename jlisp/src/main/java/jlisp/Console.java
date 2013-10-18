package jlisp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Console {

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
            sb.append(' ');
        }
        ConsoleEnvironment environment = new ConsoleEnvironment();
        environment.defineFunction("+", new FunctionDefinition() {
            
            @Override
            public Object evaluate(Collection<Object> arguments) {
                int start = 0;
                for (Object argument : arguments) {
                    start = start + ((Number)argument).intValue();
                }
                return start;
            }
        });
        
        try {
            System.out.println(Lisp.read(sb.toString()).evaluate(environment));    
        } catch (Exception pe) {
            System.out.println(pe.getMessage());
        }
        
    }
}

class ConsoleEnvironment implements Environment {

    Map<String,Object> variables = new HashMap<String,Object>();
    Map<String,FunctionDefinition> functions = new HashMap<String,FunctionDefinition>();
    
    @Override
    public boolean isVariableDefined(String name) {
        return variables.containsKey(name);
    }

    @Override
    public Object evaluateFunction(String name, Collection<Object> arguments) {
        if (!functions.containsKey(name)) {
            throw new EvaluationException("The function " + name + " is undefined.");
        }
        return functions.get(name).evaluate(arguments);
    }
    
    public void defineFunction(String name, FunctionDefinition definition) {
        functions.put(name, definition);
    }
}