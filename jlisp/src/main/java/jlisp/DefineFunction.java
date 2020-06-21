package jlisp;


public class DefineFunction implements SpecialForm {
    
    @Override
    public LispObject evaluate(List expressions, Environment environment) {
        
        if (expressions.isEmpty()) {
            throw new EvaluationException("DEFUN: cannot define a function from: " + expressions);
        }
        
        LispObject first = expressions.head();
        if (!(first instanceof Symbol)) {
            throw new EvaluationException("The value " + first.toString() + " is not of type SYMBOL");
        }
        Symbol name = (Symbol)first;
        
        List parameterList = Lisp.NIL;
        List bodies = Lisp.NIL;
        
        List remaining = expressions.tail();
        if (!remaining.isEmpty()) {
            LispObject second = remaining.head();
            assertList(second);
            parameterList = (List)second;
            parameterList.foreach(this::assertListOrSymbol);
            
            
            remaining = remaining.tail();
            if (!remaining.isEmpty()) {
                assertList(remaining);    
                bodies = remaining;
            }
        }
        
        environment.defineFunction(name, new FunctionDefinition(parameterList, bodies));
        
        return name;
        
    }

    private void assertList(LispObject second) {
        if (!(second instanceof List)) {
            throw new EvaluationException("The value " + second.toString() + " is not of type LIST");
        }
    }

    void assertListOrSymbol(Object parameter) throws EvaluationException {
        if ( (!(parameter instanceof ConsCell)) && (!(parameter instanceof Symbol))) {
            throw new EvaluationException("Invalid lambda list element " + parameter.toString() + ". A lambda list may only contain symbols and lists.");
        }
        
    }

    @Override
    public int hashCode() {
        return DefineFunction.class.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        return DefineFunction.class.equals(obj.getClass());
    }
    
    @Override
    public String toString() {
        return "DEFUN";
    }
}
