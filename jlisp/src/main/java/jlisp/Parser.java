package jlisp;

import java.util.Stack;

public interface Parser {
    
    void parse(CharIterator charIterator, Stack<LispObject> stack) throws ParseException;
}
