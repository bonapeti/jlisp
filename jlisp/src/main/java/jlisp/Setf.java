package jlisp;

public class Setf implements SpecialForm {

    @Override
    public LispObject evaluate(List expressions, Environment environment) {
        List next = expressions;
        LispObject lastValue = Lisp.NIL;
        
        while (!next.isEmpty()) {
            Symbol symbol = Lisp.asSymbol(next.head());
            next = next.tail();
            
            if (next.isEmpty()) {
                throw new EvaluationException("SETF called with an odd number of arguments");
            }
            
            lastValue = next.head().evaluate(environment);
            
            environment.bindValue(symbol, lastValue);
            
            next = next.tail();
        }
        
        
        return lastValue;

    }

}
