package jlisp;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

public class Sequence implements Parser {

    private Collection<Parser> parsers = new LinkedList<Parser>();

    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) throws ParseException {
        for (Parser parser : parsers) {
            parser.parse(charIterator, stack);
        }
    }
    
    public void addParser(Parser parser) {
        parsers.add(parser);
    }

}
