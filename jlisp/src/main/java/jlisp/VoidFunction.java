package jlisp;

public interface VoidFunction {

    public static VoidFunction DO_NOTHING = new VoidFunction() {
        
        @Override
        public void apply(LispObject object) {
        }
    };
    
	void apply(LispObject object);
}
