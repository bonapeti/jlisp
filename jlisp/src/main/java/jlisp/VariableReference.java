package jlisp;

public class VariableReference implements Expression {

    private String variableName = null;
    
    public VariableReference(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public Object evaluate() {
        throw new RuntimeException("Unbound variable '" + variableName + "'");
    }

}
