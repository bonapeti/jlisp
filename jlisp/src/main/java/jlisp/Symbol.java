package jlisp;

import java.io.IOException;

public class Symbol implements LispObject {

    private String name = null;
    
    public Symbol(String name) {
        this.name = name;
    }

    @Override
    public LispObject evaluate(Environment environment) {
    	return environment.getValue(this);
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

    @Override
    public void print(Appendable appendable) throws IOException {
        appendable.append(name.toUpperCase());
    }

    @Override
    public boolean isTrue() {
        return true;
    }

}
