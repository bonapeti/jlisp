package jlisp;

import java.io.IOException;
import java.util.Objects;

public class Fixnum extends Number implements LispObject {

    public static Fixnum ZERO = new Fixnum(0);
    public static Fixnum ONE = new Fixnum(1);
    
    private final Integer value;
    
    private Fixnum(Integer value) {
        this.value = value;
    }

    public static Fixnum as(Integer value) {
        return new Fixnum(Objects.requireNonNull(value, "Fixnum value is missing"));
    }

    static Fixnum of(LispObject object) {
        if (!(object instanceof Fixnum)) {
            throw new EvaluationException(object.toString() + " is not a number");
        }
        return (Fixnum)object;
    }

    public Fixnum next() {
        return new Fixnum(value + 1);
    }
    
    public LispObject evaluate(Environment environment) {
        return this;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        Fixnum other = (Fixnum) obj;
        if (value == null) {
            return other.value == null;
        } else return value.equals(other.value);
    }

	@Override
	public int intValue() {
		return value;
	}

	@Override
	public long longValue() {
		return value.longValue();
	}

	@Override
	public float floatValue() {
		return value.floatValue();
	}

	@Override
	public double doubleValue() {
		return value.doubleValue();
	}

    @Override
    public void print(Appendable appendable) throws IOException {
        appendable.append(value.toString());
    }

    @Override
    public boolean isTrue() {
        return true;
    }
    
    public LispObject smallerThan(Fixnum other) {
        if (value.compareTo(other.value) < 0) {
            return Lisp.T;
        } else {
            return Lisp.NIL;
        }
    }
    
    public LispObject greaterThan(Fixnum other) {
        if (value.compareTo(other.value) > 0) {
            return Lisp.T;
        } else {
            return Lisp.NIL;
        }
    }
    
    public Fixnum absoluteValue() {
    	return new Fixnum(Math.abs(value));
    }
}
