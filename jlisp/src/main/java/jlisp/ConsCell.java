package jlisp;

import java.io.IOException;

/**
 * Lisp cons cell.
 */
public class ConsCell implements List {

	private final LispObject head;
	private final List tail;

	public ConsCell(LispObject head) {
		this(head, Lisp.NIL);
	}

	public ConsCell(LispObject head, List tail) {
		this.head = head;
		this.tail = tail;
	}

	@Override
	public LispObject evaluate(Environment environment){

		Symbol functionName = Symbol.of(head());

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
		List that = (List) other;
		return head().equals(that.head()) && tail().equals(that.tail());
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    try {
            print(sb);
            return sb.toString();
        } catch (IOException e) {
            return e.getMessage();
        }
	    
	}

	@Override
	public LispObject head() {
		return head;
	}

	@Override
	public List tail() {
		return tail;
	}

	@Override
	public ConsCell append(LispObject lispObject) {
		return new ConsCell(head(), tail().append(lispObject));
	}

	@Override
	public List filter(Function1<LispObject, LispObject> f) {
		if (f.apply(head()).isTrue()) {
			return new ConsCell(head(), tail().filter(f));
		} else {
			return tail().filter(f);
		}

	}

	@Override
	public List map(Function1<LispObject, LispObject> f) {
		return new ConsCell(f.apply(head()),tail.map(f));
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
        List next = tail;
        while (!next.isEmpty()) {
            appendable.append(" ");
            next.head().print(appendable);
            next = next.tail();
        }
        appendable.append(")");
    }

    @Override
    public boolean isTrue() {
        return true;
    }

    @Override
    public Fixnum length() {
        return foldLeft(Fixnum.ZERO, (p1, p2) -> p1.next());
    }

    @Override
    public LispObject first() {
        return head();
    }

    @Override
    public LispObject second() {
        return tail().first();
    }

    @Override
    public LispObject third() {
        return tail().second();
    }

    @Override
    public List rest() {
        return tail();
    }

    @Override
    public LispObject car() {
        return head();
    }

    @Override
    public List cdr() {
        return tail();
    }

    @Override
    public LispObject findFirst(Function1<List,Boolean> predicate, Function1<List, LispObject> valueOf) {
        if (predicate.apply(this)) {
            return valueOf.apply(this);
        }
        return tail().findFirst(predicate,valueOf);
    }
}
