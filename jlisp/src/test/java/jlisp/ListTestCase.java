package jlisp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class ListTestCase {

    @Test
    public void emptyList_evaluates_to_NIL() {
        Environment environment = mock(Environment.class);
        assertEquals(Lisp.NIL,Lisp.NIL.evaluate(environment));
        
    }
    
    @Test
    public void to_string() {
        assertEquals("()",Lisp.NIL.toString());
        
        Expression firstExpression = mock(Expression.class);
        List list = new List(firstExpression);

        assertEquals("(" + firstExpression.toString() + ")",list.toString());
        
        Expression secondExpression = mock(Expression.class);
        list = list.append(secondExpression);
        
        assertEquals("(" + firstExpression.toString() + " " + secondExpression.toString() + ")",list.toString());
    }
    
    @Test
    public void emptyList_equals() {
        assertEquals(Lisp.NIL,Lisp.NIL);
    }
    
    @Test
    public void first_expression_is_not_symbol() {
        
    	Expression firstExpression = mock(Expression.class);
    	List list = new List(firstExpression);
        
        
        Environment environment = mock(Environment.class);
        try {
            list.evaluate(environment);
            fail("Expected ClassCastException because expression is not a symbol");
        } catch (ClassCastException cce) {
            
        }
    }
    
    @Test
    public void evaluate_with_no_arguments() {
        
        
        Symbol symbol = new Symbol("functionName");
        List list = new List(symbol);
        
        Environment environment = mock(Environment.class);
        
        Expression evaluationValue = mock(Expression.class);
        
        SpecialForm specialForm = mock(SpecialForm.class);
        
        when(environment.getSpecialForm(eq(symbol))).thenReturn(specialForm);
        when(specialForm.evaluate(Lisp.NIL, environment)).thenReturn(evaluationValue);
        
        assertSame(evaluationValue, list.evaluate(environment));
        
        
    }
    
    @Test
    public void evaluate_with_arguments() {
        
        
        Symbol symbol = new Symbol("functionName");
        List list = new List(symbol);
        list = list.append(new Fixnum(1));
        
        Environment environment = mock(Environment.class);
        
        Expression evaluationValue = mock(Expression.class);
        List arguments = new List(new Fixnum(1));
        
        SpecialForm specialForm = mock(SpecialForm.class);
        
        when(environment.getSpecialForm(eq(symbol))).thenReturn(specialForm);
        when(specialForm.evaluate(arguments, environment)).thenReturn(evaluationValue);
        
        
        assertSame(evaluationValue, list.evaluate(environment));
    }

}
