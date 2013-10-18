package jlisp;

public class TrueSymbol implements Expression {

    public TrueSymbol() {
    }

    @Override
    public Object evaluate(Environment environment) {
        return this;
    }

    @Override
    public int hashCode() {
        return TrueSymbol.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "t";
    }
}
