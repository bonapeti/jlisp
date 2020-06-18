package jlisp;

/**
 * (or form1 form2 ... ) evaluates each form, one at a time, from left to right. If any form other than the last evaluates to something other than nil, or immediately returns that non-nil value without evaluating the remaining forms. If every form but the last evaluates to nil, or returns whatever evaluation of the last of the forms returns.
 */
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
