package jlisp;


import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class FunctionCallTestCase {

    @Test
    public void evaluate() {
        Function function = mock(Function.class);
        Expression value = mock(Expression.class);
        
        Environment environment = mock(Environment.class);
        
        IList expressions = mock(IList.class);
        IList evaluatedExpressions = mock(IList.class);
        
        when(expressions.map(isA(Function2.class))).thenReturn(evaluatedExpressions);
        when(function.evaluate(eq(expressions), eq(environment))).thenReturn(value);
        
        FunctionCall functionCall = new FunctionCall(function);
        
        Expression expression = mock(Expression.class);
        when(expressions.head()).thenReturn(expression);
        
        Expression argument = mock(Expression.class);
        
        when(expression.evaluate(environment)).thenReturn(argument);
        
        Expression result = mock(Expression.class);
        
        when(function.evaluate(evaluatedExpressions, environment)).thenReturn(result);
        
        assertSame(result, functionCall.evaluate(expressions, environment));
        
        
        
    }

}
