package jlisp;

public class Symbol implements Expression {

    private String name = null;
    
    public Symbol(String name) {
        this.name = name;
    }

    @Override
    public Object evaluate(Environment environment) {
        if (!environment.isVariableDefined(name)) {
            throw new RuntimeException("Unbound variable '" + name + "'");    
        } else {
            return null;
        }
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.toUpperCase().hashCode());
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
        Symbol other = (Symbol) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equalsIgnoreCase(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name.toUpperCase();
    }

    public String getName() {
        return name.toUpperCase();
    }

    
}
