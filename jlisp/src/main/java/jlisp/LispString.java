package jlisp;

import java.io.IOException;

public class LispString implements LispObject {

    private final String value;
    
    public LispString(String value) {
        this.value = value;
    }

    @Override
    public LispObject evaluate(Environment environment) {
        return this;
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
        LispString other = (LispString) obj;
        if (value == null) {
            return other.value == null;
        } else return value.equals(other.value);
    }

	@Override
	public String toString() {
		return "\"" + value + "\"";
	}

	@Override
    public void print(Appendable appendable) throws IOException {
	    appendable.append(value);
    }

    @Override
    public boolean isTrue() {
        return true;
    }

    
}
