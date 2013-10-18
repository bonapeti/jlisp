package jlisp;

public class VariableReference implements Expression {

    private String variableName = null;
    
    public VariableReference(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public Object evaluate(Environment environment) {
        if (!environment.isVariableDefined(variableName)) {
            throw new RuntimeException("Unbound variable '" + variableName + "'");    
        } else {
            return null;
        }
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((variableName == null) ? 0 : variableName.hashCode());
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
        VariableReference other = (VariableReference) obj;
        if (variableName == null) {
            if (other.variableName != null)
                return false;
        } else if (!variableName.equals(other.variableName))
            return false;
        return true;
    }

    
}
