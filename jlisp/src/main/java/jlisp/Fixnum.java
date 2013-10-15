package jlisp;

public class Fixnum implements Expression {

    private Integer value = null;
    
    public Fixnum(Integer value) {
        this.value = value;
    }
    
    public Object evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return "fixnum";
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
}
