package jlisp;


import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;

public class FunctionCallTestCase {

    @Test
    public void evaluate() {
        Function function = mock(Function.class);
        Expression value = mock(Expression.class);
        
        Environment environment = mock(Environment.class);
        
        IList list = mock(IList.class);
        
        when(function.evaluate(eq(list), eq(environment))).thenReturn(value);
        
        FunctionCall functionCall = new FunctionCall(function);
        
        IList expressions = mock(IList.class);
        Expression expression = mock(Expression.class);
        when(expressions.head()).thenReturn(expression);
        
        Expression argument = mock(Expression.class);
        
        when(expression.evaluate(environment)).thenReturn(argument);
        
        assertSame(value, functionCall.evaluate(expressions, environment));
        
        
    }

}
