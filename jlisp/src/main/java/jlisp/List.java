package jlisp;

import java.util.Iterator;

public class List implements IList {

	private Expression head = null;
	private IList tail = null;

	public List(Expression head) {
		this(head, Lisp.NIL);
	}

	public List(Expression head, IList tail) {
		this.head = head;
		this.tail = tail;
	}

	@Override
	public Expression evaluate(Environment environment) {

		Symbol functionName = (Symbol) head();

		return environment.getSpecialForm(functionName).evaluate(tail(),
				environment);
	}

	public int hashCode() {
		return 37 * (head().hashCode() + tail().hashCode());
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || getClass() != other.getClass())
			return false;
		IList that = (IList) other;
		return head().equals(that.head()) && tail().equals(that.tail());
	}

	@Override
	public String toString() {
		return "(" + head() + ", " + tail() + ")";
	}

	@Override
	public Expression head() {
		return head;
	}

	@Override
	public IList tail() {
		return tail;
	}

	@Override
	public List append(Expression expression) {
		return new List(head(), tail().append(expression));
	}

	@Override
	public IList filter(Function2<Expression, Boolean> f) {
		if (f.apply(head())) {
			return new List(head(), tail().filter(f));
		} else {
			return tail().filter(f);
		}

	}

	@Override
	public IList map(Function2<Expression, Expression> f) {
		return new List(f.apply(head()),tail.map(f));
	}

	@Override
	public Expression foldLeft(Expression seed,
			Function3<Expression, Expression, Expression> f) {
		return tail().foldLeft(f.apply(seed, head()), f);
	}

	@Override
	public Expression foldRight(Expression seed,
			Function3<Expression, Expression, Expression> f) {
		return f.apply(head(), tail().foldRight(seed, f));
	}

	@Override
	public void foreach(Function1Void<Expression> f) {
		f.apply(head());
		tail().foreach(f);

	}

	

}
