package jlisp;

public interface List extends LispObject {

    boolean isEmpty();
    
	LispObject head();
	
	List tail();
	
	ConsCell append(LispObject lispObject);
	
	List filter(Function1<LispObject,LispObject> f);
	
	List map(Function1<LispObject,LispObject> f);
	
	<R> R foldLeft(R seed, Function2<R,R,LispObject> f);
	
	<R> R foldRight(R seed, Function2<R, LispObject, R> f);
	
	void foreach(VoidFunction f);
	
    Fixnum length();
    
    LispObject first();
    
    LispObject second();
    
    LispObject third();
    
    List rest();
    
    LispObject car();
    
    List cdr();
    
    LispObject findFirst(Function1<List,Boolean> predicate, Function1<List, LispObject> valueOf);
}
