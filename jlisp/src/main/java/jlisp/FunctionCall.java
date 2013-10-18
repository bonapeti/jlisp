package jlisp;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class FunctionCall implements Expression {

    private String name = null;
    private List<Expression> arguments = new LinkedList<Expression>();
    
    public FunctionCall(String name) {
        this.name = name;
    }

    @Override
    public Object evaluate(Environment environment) {
        Collection<Object> actualArguments = new LinkedList<Object>();
        for (Expression argument : arguments) {
            actualArguments.add(argument.evaluate(environment));
        }
        return environment.evaluateFunction(name, actualArguments);
    }

    public void addArgument(Expression expression) {
        arguments.add(expression);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FunctionCall [name=");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((arguments == null) ? 0 : arguments.hashCode());
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
        FunctionCall other = (FunctionCall) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (arguments == null) {
            if (other.arguments != null)
                return false;
        } else if (!arguments.equals(other.arguments))
            return false;
        return true;
    }

    
}
