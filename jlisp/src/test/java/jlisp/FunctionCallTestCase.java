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
        LispObject value = mock(LispObject.class);
        
        Environment environment = mock(Environment.class);
        
        IList expressions = mock(IList.class);
        IList evaluatedExpressions = mock(IList.class);
        
        when(expressions.map(isA(Function1.class))).thenReturn(evaluatedExpressions);
        when(function.evaluate(eq(expressions), eq(environment))).thenReturn(value);
        
        FunctionCall functionCall = new FunctionCall(function);
        
        LispObject lispObject = mock(LispObject.class);
        when(expressions.head()).thenReturn(lispObject);
        
        LispObject argument = mock(LispObject.class);
        
        when(lispObject.evaluate(environment)).thenReturn(argument);
        
        LispObject result = mock(LispObject.class);
        
        when(function.evaluate(evaluatedExpressions, environment)).thenReturn(result);
        
        assertSame(result, functionCall.evaluate(expressions, environment));
        
        
        
    }

}
