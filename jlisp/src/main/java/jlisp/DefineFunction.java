package jlisp;


public class DefineFunction implements SpecialForm {
    
    @Override
    public Expression evaluate(IList expressions, Environment environment) {
        
        if (expressions.isEmpty()) {
            throw new EvaluationException("DEFUN: cannot define a function from: " + expressions);
        }
        
        Expression first = expressions.head();
        if (!(first instanceof Symbol)) {
            throw new EvaluationException("The value " + first.toString() + " is not of type SYMBOL");
        }
        Symbol name = (Symbol)first;
        
        IList parameterList = Lisp.NIL;
        IList bodies = Lisp.NIL;
        
        IList remaining = expressions.tail();
        if (!remaining.isEmpty()) {
            Expression second = remaining.head();
            assertList(second);
            parameterList = (IList)second;
            parameterList.foreach(new Function1Void<Expression>() {
                
                @Override
                public void apply(Expression e) {
                    assertListOrSymbol(e);
                }
            });
            
            
            remaining = remaining.tail();
            if (!remaining.isEmpty()) {
                assertList(remaining);    
                bodies = (IList)remaining;
            }
        }
        
        environment.defineFunction(name, new FunctionDefinition(parameterList, bodies));
        
        return name;
        
    }

    private void assertList(Expression second) {
        if (!(second instanceof IList)) {
            throw new EvaluationException("The value " + second.toString() + " is not of type LIST");
        }
    }

    void assertListOrSymbol(Object parameter) throws EvaluationException {
        if ( (!(parameter instanceof List)) && (!(parameter instanceof Symbol))) {
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
