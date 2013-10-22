package syntax;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import jlisp.CharIterator;
import jlisp.ParseException;
import jlisp.Parser;
import jlisp.Expression;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Parser> iParser = parsers.iterator();
        while (iParser.hasNext()) {
            sb.append(iParser.next().toString());
            if (iParser.hasNext()) {
                sb.append(" ");
            }
        }
        
        return sb.toString();
    }
}
