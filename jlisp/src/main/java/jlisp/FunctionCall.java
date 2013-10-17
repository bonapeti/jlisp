package jlisp;

public class FunctionCall implements Expression {

    private String name = null;
    
    public FunctionCall(String name) {
        this.name = name;
    }

    @Override
    public Object evaluate() {
        return null;
    }

    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FunctionCall [name=");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }

    
}
