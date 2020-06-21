package syntax;

import java.util.Stack;

/**
 * Thing which can go through the characters of the Lisp code and place the evaluated Lisp object on the stack
 */
public interface Parser<T> {

    /**
     * Goes through the characters of the Lisp code and place the evaluated Lisp object on the stack
     * @param lispCode
     * @param stack
     * @throws ParseException
     */
    void parse(Code lispCode, Stack<T> stack) throws ParseException;
}
