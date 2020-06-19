package jlisp;


public class FunctionDefinition implements Function {

    private final List parameters;
    private final List bodies;
    
    public FunctionDefinition(List parameters, List bodies) {
        this.parameters = parameters;
        this.bodies = bodies;
    }
    
    @Override
    public LispObject evaluate(List arguments, Environment environment) {
        
        final CallEnvironment callEnvironment = new CallEnvironment(environment);
        
        
        parameters.foldLeft(arguments, (Function2<LispObject, LispObject, LispObject>) (argumentsExpression, parameter) -> {
            Symbol parameterSymbol = (Symbol)parameter;

            List arguments1 = (List)argumentsExpression;

            if (arguments1.isEmpty()) {
                throw new EvaluationException("Too few arguments (0 instead of at least 1) given");
            }

            callEnvironment.bindValue(parameterSymbol, arguments1.head());
            return arguments1.tail();
        });
        
        return bodies.foldLeft(Lisp.NIL, (Function2<LispObject, LispObject, LispObject>) (seed, body) -> body.evaluate(callEnvironment));
    }

    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
        result = prime * result + ((bodies == null) ? 0 : bodies.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FunctionDefinition other = (FunctionDefinition) obj;
        if (parameters == null) {
            if (other.parameters != null)
                return false;
        } else if (!parameters.equals(other.parameters))
            return false;
        if (bodies == null) {
            return other.bodies == null;
        } else return bodies.equals(other.bodies);
    }

    @Override
    public String toString() {
        return  "FunctionDefinition [parameters=" +
                parameters +
                ", bodies=" +
                bodies +
                "]";
    }

}
