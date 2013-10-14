package jlisp;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

public class Alteration implements Parser {

    private Collection<Parser> parsers = new LinkedList<Parser>();

    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) throws ParseException {
        int position = charIterator.getCurrentPosition();
        for (Parser parser : parsers) {
            try {
                parser.parse(charIterator, stack);
                break;
            } catch (ParseException pe) {
                charIterator.setCurrentPosition(position);
            }
            
        }

    }
    
    public void addParser(Parser parser) {
        parsers.add(parser);
    }

}
