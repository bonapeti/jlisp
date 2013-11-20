package jlisp;

public class And implements SpecialForm {

    @Override
    public LispObject evaluate(List expressions, final Environment environment) {
        List next = expressions;
        LispObject lastValue = Lisp.T;
        
        while (!next.isEmpty()) {
            lastValue = next.head().evaluate(environment);
            if (!lastValue.isTrue()) {
                return Lisp.NIL;
            }
            next = next.tail();
        }
        
        
        return lastValue;
    }

}
