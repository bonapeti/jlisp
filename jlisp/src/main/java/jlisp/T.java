package jlisp;

public final class T extends Symbol {

    public T() {
        super("t");
    }

    @Override
    public LispObject evaluate(Environment environment) {
        return this;
    }

    @Override
    public int hashCode() {
        return T.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        return getClass() == obj.getClass();
    }
}
