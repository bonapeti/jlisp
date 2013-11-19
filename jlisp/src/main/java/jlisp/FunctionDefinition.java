package jlisp;


public class FunctionDefinition implements Function {

    private List parameters = null;
    private List bodies = null;
    
    public FunctionDefinition(List parameters, List bodies) {
        this.parameters = parameters;
        this.bodies = bodies;
    }
    
    @Override
    public LispObject evaluate(List arguments, Environment environment) {
        
        final CallEnvironment callEnvironment = new CallEnvironment(environment);
        
        
        parameters.foldLeft(arguments, new Function2<LispObject, LispObject, LispObject>() {
			
			@Override
			public LispObject apply(LispObject argumentsExpression, LispObject parameter) {
				Symbol parameterSymbol = (Symbol)parameter;

				List arguments = (List)argumentsExpression;
				
				if (arguments.isEmpty()) {
				    throw new EvaluationException("Too few arguments (0 instead of at least 1) given");
				}
				
				callEnvironment.bindValue(parameterSymbol, arguments.head());
				return arguments.tail();
			}
		});
        
        return bodies.foldLeft(Lisp.NIL, new Function2<LispObject, LispObject, LispObject>() {
			
			@Override
			public LispObject apply(LispObject seed, LispObject body) {
				return body.evaluate(callEnvironment);
			}
		});
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
            if (other.bodies != null)
                return false;
        } else if (!bodies.equals(other.bodies))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FunctionDefinition [parameters=");
        builder.append(parameters);
        builder.append(", bodies=");
        builder.append(bodies);
        builder.append("]");
        return builder.toString();
    }

}
