package jlisp;

public interface VoidFunction {

    VoidFunction DO_NOTHING = object -> {
    };
    
	void apply(LispObject object);
}
