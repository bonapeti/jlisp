package jlisp;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SymbolTestCase {

    @Test
    public void evaluate() {
        
        Symbol symbol = new Symbol("symbol");
        
        LispObject value = mock(LispObject.class);
        
        Environment environment = mock(Environment.class);
        when(environment.getValue(symbol)).thenReturn(value);
        
        assertSame(value, symbol.evaluate(environment));
        
        
    }
    
    @Test
    public void equal() {
        assertEquals(new Symbol("name"), new Symbol("name"));
        assertEquals(new Symbol("name"), new Symbol("NAME"));
        assertNotEquals(new Symbol("name"), new Symbol("name1"));
    }

    

}
