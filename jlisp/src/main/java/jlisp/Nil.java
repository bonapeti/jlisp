package jlisp;


public final class Nil extends Symbol implements List {

    public Nil() {
        super("nil");
    }

    @Override
    public LispObject evaluate(Environment environment) {
        return this;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Nil.class.hashCode();
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
        return true;
    }

	@Override
	public LispObject head() {
		throw new UnsupportedOperationException("nil.head should not be called");
	}

	@Override
	public List tail() {
		throw new UnsupportedOperationException("nil.tail should not be called");
	}

	@Override
	public ConsCell append(LispObject lispObject) {
		return new ConsCell(lispObject);
	}

	@Override
	public List filter(Function1<LispObject, Boolean> f) {
		return this;
	}

	@Override
	public List map(Function1<LispObject, LispObject> f) {
		return this;
	}

	@Override
	public <R> R foldLeft(R seed,
			Function2<R, R, LispObject> f) {
		return seed;
	}

	@Override
	public <R> R foldRight(R seed,
			Function2<R, LispObject, R> f) {
		return seed;
	}

	@Override
	public void foreach(VoidFunction f) {
		
	}

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isTrue() {
        return false;
    }

    @Override
    public Fixnum length() {
        return new Fixnum(0);
    }

    @Override
    public LispObject first() {
        return this;
    }

    @Override
    public LispObject second() {
        return this;
    }

    @Override
    public LispObject third() {
        return this;
    }

    @Override
    public List rest() {
        return this;
    }

    @Override
    public LispObject car() {
        return this;
    }

    @Override
    public List cdr() {
        return this;
    }
}
