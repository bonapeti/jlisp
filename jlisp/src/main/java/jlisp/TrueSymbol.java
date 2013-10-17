package jlisp;

public class TrueSymbol implements Expression {

    public TrueSymbol() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Object evaluate() {
        // TODO Auto-generated method stub
        return null;
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
