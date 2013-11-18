package jlisp;

import java.util.Collection;
import java.util.Iterator;

public class FunctionDefinition implements Function {

    private IList parameters = null;
    private IList bodies = null;
    
    public FunctionDefinition(IList parameters, IList bodies) {
        this.parameters = parameters;
        this.bodies = bodies;
    }
    
    @Override
    public Expression evaluate(IList arguments, Environment environment) {
        
        final CallEnvironment callEnvironment = new CallEnvironment(environment);
        
        
        parameters.foldLeft(arguments, new Function3<Expression, Expression, Expression>() {
			
			@Override
			public Expression apply(Expression argumentsExpression, Expression parameter) {
				Symbol parameterSymbol = (Symbol)parameter;

				IList arguments = (IList)argumentsExpression;
				
				callEnvironment.bindValue(parameterSymbol, arguments.head());
				return arguments.tail();
			}
		});
        
        return bodies.foldLeft(Lisp.NIL, new Function3<Expression, Expression, Expression>() {
			
			@Override
			public Expression apply(Expression seed, Expression body) {
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
