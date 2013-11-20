package jlisp;

public class Cond implements SpecialForm {

    @Override
    public LispObject evaluate(List expressions, Environment environment) {

        List cell = expressions;
        List nextClause = Lisp.asList(cell.head());
        while (!nextClause.isEmpty()) {
            LispObject test = nextClause.first();
            if (test.evaluate(environment).isTrue()) {
                return nextClause.second().evaluate(environment);
            }
            cell = cell.tail();
            nextClause = Lisp.asList(cell.head());
        }
        
        return Lisp.NIL;
    }
}
