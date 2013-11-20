package jlisp;

public class Or implements SpecialForm {

    @Override
    public LispObject evaluate(List expressions, Environment environment) {
        List next = expressions;
        LispObject lastValue = Lisp.NIL;
        
        while (!next.isEmpty()) {
            lastValue = next.head().evaluate(environment);
            if (lastValue.isTrue()) {
                return lastValue;
            }
            next = next.tail();
        }
        
        
        return lastValue;
    }

}
