package jlisp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Test;

public class AndTestCase {

    private final And and = new And();
    
    @Test
    public void nil() {
        assertEquals(Lisp.T,and.evaluate(Lisp.NIL, mock(Environment.class)));
    }
    
    @Test
    public void t() {
        assertEquals(Lisp.T,and.evaluate(new ConsCell(Lisp.T), mock(Environment.class)));
    }
    
    @Test
    public void expressionTrue() {
        Environment environment = mock(Environment.class);
        LispObject test = mock(LispObject.class);
        when(test.evaluate(environment)).thenReturn(Lisp.T);
        assertEquals(Lisp.T,and.evaluate(new ConsCell(test), environment));
    }
    
    @Test
    public void expressionFalse() {
        Environment environment = mock(Environment.class);
        LispObject test = mock(LispObject.class);
        when(test.evaluate(environment)).thenReturn(Lisp.NIL);
        assertEquals(Lisp.NIL,and.evaluate(new ConsCell(test), environment));
    }
    
    @Test
    public void expressionTrueExpressionTrue() {
        Environment environment = mock(Environment.class);
        LispObject test1 = mock(LispObject.class);
        LispObject test2 = mock(LispObject.class);
        when(test1.evaluate(environment)).thenReturn(Lisp.T);
        when(test2.evaluate(environment)).thenReturn(Lisp.T);
        assertEquals(Lisp.T,and.evaluate(new ConsCell(test1).append(test2), environment));
    }
    
    @Test
    public void expressionTrueExpressionFalse() {
        Environment environment = mock(Environment.class);
        LispObject test1 = mock(LispObject.class);
        LispObject test2 = mock(LispObject.class);
        when(test1.evaluate(environment)).thenReturn(Lisp.T);
        when(test2.evaluate(environment)).thenReturn(Lisp.NIL);
        assertEquals(Lisp.NIL,and.evaluate(new ConsCell(test1).append(test2), environment));
    }
    
    @Test
    public void expressionFalseExpressionTrue() {
        Environment environment = mock(Environment.class);
        LispObject test1 = mock(LispObject.class);
        LispObject test2 = mock(LispObject.class);
        when(test1.evaluate(environment)).thenReturn(Lisp.NIL);
        
        assertEquals(Lisp.NIL,and.evaluate(new ConsCell(test1).append(test2), environment));
        
        verifyZeroInteractions(test2);
    }

}
