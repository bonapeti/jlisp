package jlisp;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class SymbolTestCase {

    @Test
    public void evaluate() {
        
        Symbol symbol = new Symbol("symbol");
        
        Expression value = mock(Expression.class);
        
        Environment environment = mock(Environment.class);
        when(environment.getValue(symbol)).thenReturn(value);
        
        assertSame(value, symbol.evaluate(environment));
        
        
    }
    
    @Test
    public void equal() {
        assertEquals(new Symbol("name"), new Symbol("name"));
        assertEquals(new Symbol("name"), new Symbol("NAME"));
        assertFalse(new Symbol("name").equals(new Symbol("name1")));
    }

    

}
