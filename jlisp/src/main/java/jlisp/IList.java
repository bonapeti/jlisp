package jlisp;

public interface IList extends Expression {

    boolean isEmpty();
    
	Expression head();
	
	IList tail();
	
	List append(Expression expression);
	
	IList filter(Function2<Expression,Boolean> f);
	
	IList map(Function2<Expression,Expression> f);
	
	Expression foldLeft(Expression seed, Function3<Expression,Expression,Expression> f);
	
	Expression foldRight(Expression seed, Function3<Expression,Expression,Expression> f);
	
	void foreach(Function1Void<Expression> f);

}
