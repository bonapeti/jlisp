package jlisp;

/**
 * (and form1 form2 ... ) evaluates each form, one at a time, from left to right. If any form evaluates to nil, the value nil is immediately returned without evaluating the remaining forms. If every form but the last evaluates to a non-nil value, and returns whatever the last form returns.
 */
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
