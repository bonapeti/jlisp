package jlisp;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class List implements Expression {
    
    private java.util.List<Expression> list = new LinkedList<Expression>();
    
    @Override
    public Object evaluate(Environment environment) {
        
        if (list.isEmpty()) {
            return Lisp.NIL;
        }
        
        Iterator<Expression> iExpressions = list.iterator();
        
        Symbol functionNameSymbol = (Symbol)iExpressions.next();
        String functionName = functionNameSymbol.getName();
        
        Collection<Object> actualArguments = new LinkedList<Object>();
        
        while (iExpressions.hasNext()) {
            Expression argument = iExpressions.next();
            actualArguments.add(argument.evaluate(environment));
        }
        return environment.evaluateFunction(functionName, actualArguments);
    }

    public void add(Expression expression) {
        list.add(expression);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((list == null) ? 0 : list.hashCode());
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
        List other = (List) obj;
        if (list == null) {
            if (other.list != null)
                return false;
        } else if (!list.equals(other.list))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("List [");
        builder.append(list);
        builder.append("]");
        return builder.toString();
    }

    
}
