package jlisp;

public interface IList extends LispObject {

    boolean isEmpty();
    
	LispObject head();
	
	IList tail();
	
	List append(LispObject lispObject);
	
	IList filter(Function1<LispObject,Boolean> f);
	
	IList map(Function1<LispObject,LispObject> f);
	
	<R> R foldLeft(R seed, Function2<R,R,LispObject> f);
	
	<R> R foldRight(R seed, Function2<R, LispObject, R> f);
	
	void foreach(VoidFunction f);

    Fixnum length();
    
    LispObject first();

}
