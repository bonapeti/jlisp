package jlisp;

import static org.junit.Assert.*;

import org.junit.Test;

public class LispTestCase {

    @Test
    public void integer3() {
        assertEquals(3, Lisp.parse("3"));
    }

}
