package jlisp;

public class EmptyList implements Expression {

    public EmptyList() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Object evaluate(Environment environment) {
        return Lisp.NIL;
    }

    @Override
    public int hashCode() {
        return EmptyList.class.hashCode();
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
}
