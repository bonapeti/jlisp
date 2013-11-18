package jlisp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;

public class FunctionDefinitionTestCase {

    @Test
    public void evaluate_no_body() {
        IList parameterList = Lisp.NIL;
        IList bodies = Lisp.NIL;
        FunctionDefinition function = new FunctionDefinition(parameterList, bodies);
        Environment environment = mock(Environment.class);
        assertEquals(Lisp.NIL, function.evaluate(Lisp.NIL, environment));
    }

    @Test
    public void evaluate_with_body() {
        IList parameterList = Lisp.NIL;
        IList bodies = Lisp.NIL;
        
        Environment environment = mock(Environment.class);
        IList body = mock(IList.class);
        Expression value = mock(Expression.class);
        when(body.evaluate(isA(Environment.class))).thenReturn(value);
        bodies = bodies.append(body);
        
        FunctionDefinition function = new FunctionDefinition(parameterList, bodies);
        
        assertEquals(value, function.evaluate(Lisp.NIL, environment));
    }
    
    @Test
    public void evaluate_with_1_missing_parameter() {
    	IList parameterList = Lisp.NIL;
        parameterList = parameterList.append(new Symbol("b"));
        
        IList bodies = Lisp.NIL;
        
        Environment environment = mock(Environment.class);
        IList body = mock(List.class);
        Expression value = mock(Expression.class);
        when(body.evaluate(environment)).thenReturn(value);
        
        bodies = bodies.append(body);
        
        FunctionDefinition function = new FunctionDefinition(parameterList, bodies);
        
        try {
            function.evaluate(Lisp.NIL, environment);
            fail("Too few arguments (0 instead of at least 1) given");
        } catch (EvaluationException ee) {
            assertEquals("Too few arguments (0 instead of at least 1) given", ee.getMessage());
        }
        
    }
    
    @Test
    public void evaluate_with_1_arguments() {
        IList parameterList = Lisp.NIL;
        parameterList = parameterList.append(new Symbol("b"));
        
        IList bodies = Lisp.NIL;
        
        Environment environment = mock(Environment.class);
        IList body = mock(IList.class);
        Expression returnValue = mock(Expression.class);
        when(body.evaluate(isA(Environment.class))).thenReturn(returnValue);
        bodies = bodies.append(body);
        
        FunctionDefinition function = new FunctionDefinition(parameterList, bodies);
        
        IList arguments = Lisp.NIL;
        Expression argument = mock(Expression.class);
        arguments = arguments.append(argument);
        
        assertEquals(returnValue, function.evaluate(arguments, environment));
        
    }
}
