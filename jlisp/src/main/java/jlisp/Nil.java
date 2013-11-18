package jlisp;


public final class Nil extends Symbol implements IList {

    public Nil() {
        super("nil");
    }

    @Override
    public Expression evaluate(Environment environment) {
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
	public Expression head() {
		throw new UnsupportedOperationException("nil.head should not be called");
	}

	@Override
	public IList tail() {
		throw new UnsupportedOperationException("nil.tail should not be called");
	}

	@Override
	public List append(Expression expression) {
		return new List(expression);
	}

	@Override
	public IList filter(Function2<Expression, Boolean> f) {
		return this;
	}

	@Override
	public IList map(Function2<Expression, Expression> f) {
		return this;
	}

	@Override
	public Expression foldLeft(Expression seed,
			Function3<Expression, Expression, Expression> f) {
		return seed;
	}

	@Override
	public Expression foldRight(Expression seed,
			Function3<Expression, Expression, Expression> f) {
		return seed;
	}

	@Override
	public void foreach(Function1Void<Expression> f) {
		
	}

    @Override
    public boolean isEmpty() {
        return true;
    }

}
