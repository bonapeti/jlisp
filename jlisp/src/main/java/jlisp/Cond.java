package jlisp;

public class Cond implements SpecialForm {

    @Override
    public LispObject evaluate(List expressions, Environment environment) {

        List next = expressions;
        
        while (!next.isEmpty()) {
            List nextClause = List.of(next.head());
            
            LispObject test = nextClause.first();
            if (test.evaluate(environment).isTrue()) {
                return nextClause.second().evaluate(environment);
            }
            next = next.tail();
        }
        
        return Lisp.NIL;
    }
}
