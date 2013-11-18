package syntax;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import jlisp.CharIterator;
import jlisp.ParseException;
import jlisp.Parser;
import jlisp.LispObject;
public class Alteration implements Parser {

    private Collection<Parser> parsers = new LinkedList<Parser>();

    @Override
    public void parse(CharIterator charIterator, Stack<LispObject> stack) throws ParseException {
        ParseException lastError = null;
        int position = charIterator.getCurrentPosition();
        for (Parser parser : parsers) {
            try {
                parser.parse(charIterator, stack);
                lastError = null;
                break;
            } catch (ParseException pe) {
                lastError = pe;
                charIterator.setCurrentPosition(position);
            }
            
        }
        if (lastError != null) {
            throw lastError;
        }
    }
    
    public void addParser(Parser parser) {
        parsers.add(parser);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        Iterator<Parser> iParser = parsers.iterator();
        while (iParser.hasNext()) {
            sb.append(iParser.next().toString());
            if (iParser.hasNext()) {
                sb.append(" | ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
