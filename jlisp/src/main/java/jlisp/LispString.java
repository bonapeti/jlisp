package jlisp;

public class LispString implements Expression {

    private String value = null;
    
    public LispString(String value) {
        this.value = value;
    }

    @Override
    public Expression evaluate(Environment environment) {
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
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

	@Override
	public String toString() {
		return "\"" + value + "\"";
	}

    
}
