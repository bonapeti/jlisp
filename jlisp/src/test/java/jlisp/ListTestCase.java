package jlisp;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("List tests")
public class ListTestCase {

    @Test @DisplayName("Empty list should be evaluated as NIL")
    public void emptyList_evaluates_to_NIL() {
        Environment environment = mock(Environment.class);
        assertEquals(Lisp.NIL,Lisp.NIL.evaluate(environment));
        
    }
    
    @Test
    public void emptyList_equals() {
        assertEquals(Lisp.NIL,Lisp.NIL);
    }
    
    @Test @DisplayName("Evaluation should fail if first argument is not a symbol")
    public void first_expression_is_not_symbol() {
        
    	LispObject firstExpression = mock(LispObject.class);
    	ConsCell list = new ConsCell(firstExpression);
        
        
        Environment environment = mock(Environment.class);
        try {
            list.evaluate(environment);
            fail("Expected EvaluationException because expression is not a symbol");
        } catch (EvaluationException cce) {
            
        }
    }
    
    @Test
    public void evaluate_with_no_arguments() {
        
        
        Symbol symbol = new Symbol("functionName");
        ConsCell list = new ConsCell(symbol);
        
        Environment environment = mock(Environment.class);
        
        LispObject evaluationValue = mock(LispObject.class);
        
        SpecialForm specialForm = mock(SpecialForm.class);
        
        when(environment.getSpecialForm(eq(symbol))).thenReturn(specialForm);
        when(specialForm.evaluate(Lisp.NIL, environment)).thenReturn(evaluationValue);
        
        assertSame(evaluationValue, list.evaluate(environment));
        
        
    }
    
    @Test
    public void evaluate_with_arguments() {
        
        
        Symbol symbol = new Symbol("functionName");
        ConsCell list = new ConsCell(symbol);
        list = list.append(Fixnum.as(1));
        
        Environment environment = mock(Environment.class);
        
        LispObject evaluationValue = mock(LispObject.class);
        ConsCell arguments = new ConsCell(Fixnum.as(1));
        
        SpecialForm specialForm = mock(SpecialForm.class);
        
        when(environment.getSpecialForm(eq(symbol))).thenReturn(specialForm);
        when(specialForm.evaluate(arguments, environment)).thenReturn(evaluationValue);
        
        
        assertSame(evaluationValue, list.evaluate(environment));
    }
    
    @Test
    public void append() {
        ConsCell list = new ConsCell(new Symbol("sym"));
        assertEquals(new Symbol("sym"), list.head());
        assertEquals(Lisp.NIL, list.tail());
        
        list = list.append(new Symbol("sym2"));
        assertEquals(new Symbol("sym"), list.head());
        List tail = list.tail();
        assertNotEquals(Lisp.NIL, tail);
        assertEquals(new Symbol("sym2"), tail.head());
        assertEquals(Lisp.NIL, tail.tail());
    }

}
