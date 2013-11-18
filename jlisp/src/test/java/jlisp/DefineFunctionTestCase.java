package jlisp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Test;

public class DefineFunctionTestCase {

    @Test
    public void evaluate_empty() {
        Environment environment = mock(Environment.class);
        DefineFunction defun = new DefineFunction();
        assertEquals(Lisp.NIL, defun.evaluate(Lisp.NIL, environment));
        
        verifyZeroInteractions(environment);
    }
    
    @Test
    public void invalid_name() {
        Environment environment = mock(Environment.class);
        DefineFunction defun = new DefineFunction();
        
        List arguments = new List(new Fixnum(1));
        
        try {
            defun.evaluate(arguments, environment);
            fail("Should have failed with type error");
        } catch (EvaluationException ee) {
            assertEquals("The value 1 is not of type SYMBOL", ee.getMessage());
        }
        
    }
    
    @Test
    public void evaluate_name() {
        Environment environment = mock(Environment.class);
        DefineFunction defun = new DefineFunction();
        
        Symbol functionName = new Symbol("funcationName");
        List arguments = new List(functionName);
        
        assertEquals(functionName, defun.evaluate(arguments, environment));
        
        verify(environment).defineFunction(functionName, new FunctionDefinition(Lisp.NIL, Lisp.NIL));
    }
    
    @Test
    public void invalid_parameter_list() {
        Environment environment = mock(Environment.class);
        DefineFunction defun = new DefineFunction();
        
        
        Symbol functionName = new Symbol("funcationName");
        List arguments = new List(functionName);
        arguments = arguments.append(new Symbol("b"));
        
        try {
            defun.evaluate(arguments, environment);
            fail("Should have failed with type error");
        } catch (EvaluationException ee) {
            assertEquals("The value B is not of type LIST", ee.getMessage());
        }
        
    }

    @Test
    public void empty_parameter_list() {
        Environment environment = mock(Environment.class);
        DefineFunction defun = new DefineFunction();
        
        Symbol functionName = new Symbol("funcationName");
        List arguments = new List(functionName);
        arguments = arguments.append(Lisp.NIL);
        
        assertEquals(functionName, defun.evaluate(arguments, environment));
        
        verify(environment).defineFunction(functionName, new FunctionDefinition(Lisp.NIL, Lisp.NIL));
    }
    
    @Test
    public void number_as_parameter_list() {
        Environment environment = mock(Environment.class);
        DefineFunction defun = new DefineFunction();
        
        Symbol functionName = new Symbol("funcationName");
        List arguments = new List(functionName);
        List parameter_list = new List(new Fixnum(23));
        
        arguments = arguments.append(parameter_list);
        arguments = arguments.append(Lisp.NIL);
        
        try {
            assertEquals(functionName, defun.evaluate(arguments, environment));
            fail("Should have failed with 'Invalid lambda list element 23. A lambda list may only contain symbols and lists.'");
        } catch (EvaluationException ee) {
            assertEquals("Invalid lambda list element 23. A lambda list may only contain symbols and lists.", ee.getMessage());
        }
        
    }
    
    @Test
    public void symbol_as_parameter_list() {
        Environment environment = mock(Environment.class);
        DefineFunction defun = new DefineFunction();
        
        
        Symbol functionName = new Symbol("funcationName");
        List arguments = new List(functionName);
        
        List parameter_list = new List(new Symbol("b"));
        
        arguments = arguments.append(parameter_list);
        arguments = arguments.append(Lisp.NIL);
        
        assertEquals(functionName, defun.evaluate(arguments, environment));
        
        verify(environment).defineFunction(functionName, new FunctionDefinition(parameter_list, new List(Lisp.NIL)));
        
    }
    
    @Test
    public void one_body() {
        Environment environment = mock(Environment.class);
        DefineFunction defun = new DefineFunction();
        
        
        Symbol functionName = new Symbol("funcationName");
        List arguments = new List(functionName);
        
        List parameter_list = new List(new Symbol("b"));
        
        arguments = arguments.append(parameter_list);
        
        assertEquals(functionName, defun.evaluate(arguments, environment));
        
        verify(environment).defineFunction(functionName, new FunctionDefinition(parameter_list, new List(Lisp.NIL)));
    }
}

