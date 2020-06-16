package jlisp;

import java.io.IOException;

public class NotNilListEnd implements List {

    private final LispObject lispObject;
    
    public NotNilListEnd(LispObject lispObject) {
        this.lispObject = lispObject;
    }
    
    @Override
    public LispObject evaluate(Environment environment) {
        return lispObject.evaluate(environment);
    }

    @Override
    public void print(Appendable appendable) throws IOException {
        appendable.append(". ");
        lispObject.print(appendable);
    }

    @Override
    public boolean isTrue() {
        return lispObject.isTrue();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public LispObject head() {
        return this;
    }

    @Override
    public List tail() {
        return Lisp.NIL;
    }

    @Override
    public ConsCell append(LispObject lispObject) {
        throw new UnsupportedOperationException("NotNilListEnd.append is not supported");
    }

    @Override
    public List filter(Function1<LispObject, LispObject> f) {
        throw new UnsupportedOperationException("NotNilListEnd.filter is not supported");
    }

    @Override
    public List map(Function1<LispObject, LispObject> f) {
        throw new UnsupportedOperationException("NotNilListEnd.map is not supported");
    }

    @Override
    public <R> R foldLeft(R seed, Function2<R, R, LispObject> f) {
        return seed;
    }

    @Override
    public <R> R foldRight(R seed, Function2<R, LispObject, R> f) {
        return seed;
    }

    @Override
    public void foreach(VoidFunction f) {
        f.apply(head());
    }

    @Override
    public Fixnum length() {
        throw new UnsupportedOperationException("NotNilListEnd.length is not supported");
    }

    @Override
    public LispObject first() {
        return head();
    }

    @Override
    public LispObject second() {
        return Lisp.NIL;
    }

    @Override
    public LispObject third() {
        return Lisp.NIL;
    }

    @Override
    public List rest() {
        return Lisp.NIL;
    }

    @Override
    public LispObject car() {
        return head();
    }

    @Override
    public List cdr() {
        return Lisp.NIL;
    }

    @Override
    public LispObject findFirst(Function1<List, Boolean> predicate, Function1<List, LispObject> valueOf) {
        throw new UnsupportedOperationException("NotNilListEnd.findFirst is not supported");
    }



}
