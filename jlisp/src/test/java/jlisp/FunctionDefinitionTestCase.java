package jlisp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FunctionDefinitionTestCase {

    @Test
    public void evaluate_no_body() {
        List parameterList = Lisp.NIL;
        List bodies = Lisp.NIL;
        FunctionDefinition function = new FunctionDefinition(parameterList, bodies);
        Environment environment = mock(Environment.class);
        assertEquals(Lisp.NIL, function.evaluate(Lisp.NIL, environment));
    }

    @Test
    public void evaluate_with_body() {
        List parameterList = Lisp.NIL;
        List bodies = Lisp.NIL;
        
        Environment environment = mock(Environment.class);
        List body = mock(List.class);
        LispObject value = mock(LispObject.class);
        when(body.evaluate(isA(Environment.class))).thenReturn(value);
        bodies = bodies.append(body);
        
        FunctionDefinition function = new FunctionDefinition(parameterList, bodies);
        
        assertEquals(value, function.evaluate(Lisp.NIL, environment));
    }
    
    @Test
    public void evaluate_with_1_missing_parameter() {
    	List parameterList = Lisp.NIL;
        parameterList = parameterList.append(new Symbol("b"));
        
        List bodies = Lisp.NIL;
        
        Environment environment = mock(Environment.class);
        List body = mock(ConsCell.class);
        LispObject value = mock(LispObject.class);
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
        List parameterList = Lisp.NIL;
        parameterList = parameterList.append(new Symbol("b"));
        
        List bodies = Lisp.NIL;
        
        Environment environment = mock(Environment.class);
        List body = mock(List.class);
        LispObject returnValue = mock(LispObject.class);
        when(body.evaluate(isA(Environment.class))).thenReturn(returnValue);
        bodies = bodies.append(body);
        
        FunctionDefinition function = new FunctionDefinition(parameterList, bodies);
        
        List arguments = Lisp.NIL;
        LispObject argument = mock(LispObject.class);
        arguments = arguments.append(argument);
        
        assertEquals(returnValue, function.evaluate(arguments, environment));
        
    }
}
