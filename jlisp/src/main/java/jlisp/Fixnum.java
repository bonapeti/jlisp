package jlisp;

import java.io.IOException;

public class Fixnum extends Number implements LispObject {

    private Integer value = null;
    
    public Fixnum(Integer value) {
        this.value = value;
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
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

	@Override
	public int intValue() {
		return value.intValue();
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
}
