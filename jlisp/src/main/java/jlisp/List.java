package jlisp;

import java.io.IOException;


public class List implements IList {

	private LispObject head = null;
	private IList tail = null;

	public List(LispObject head) {
		this(head, Lisp.NIL);
	}

	public List(LispObject head, IList tail) {
		this.head = head;
		this.tail = tail;
	}

	@Override
	public LispObject evaluate(Environment environment){

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
		return "(" + head() + " " + tail() + ")";
	}

	@Override
	public LispObject head() {
		return head;
	}

	@Override
	public IList tail() {
		return tail;
	}

	@Override
	public List append(LispObject lispObject) {
		return new List(head(), tail().append(lispObject));
	}

	@Override
	public IList filter(Function1<LispObject, Boolean> f) {
		if (f.apply(head())) {
			return new List(head(), tail().filter(f));
		} else {
			return tail().filter(f);
		}

	}

	@Override
	public IList map(Function1<LispObject, LispObject> f) {
		return new List(f.apply(head()),tail.map(f));
	}

	@Override
	public <R> R foldLeft(R seed,
			Function2<R, R, LispObject> f) {
		return tail().foldLeft(f.apply(seed, head()), f);
	}

	@Override
	public <R> R foldRight(R seed,
			Function2<R, LispObject, R> f) {
		return f.apply(head(), tail().foldRight(seed, f));
	}

	@Override
	public void foreach(VoidFunction f) {
		f.apply(head());
		tail().foreach(f);

	}

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void print(Appendable appendable) throws IOException {
        appendable.append("(");
        head().print(appendable);
        IList next = tail;
        while (!next.isEmpty()) {
            appendable.append(" ");
            next.head().print(appendable);
            next = next.tail();
        }
        appendable.append(")");
    }

	

}
