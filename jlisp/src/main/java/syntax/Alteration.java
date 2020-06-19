package syntax;

import jlisp.LispCode;
import jlisp.LispObject;
import jlisp.ParseException;
import jlisp.Parser;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Parser which lets each provided parser try evaluating the code until one succeeds
 */
public class Alteration implements Parser {

    private final Collection<Parser> parsers = new LinkedList<>();

    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException {
        ParseException lastError = null;
        int positionToStart = lispCode.getCurrentPosition();
        for (Parser parser : parsers) {
            try {
                parser.parse(lispCode, stack);
                lastError = null;
                break;
            } catch (ParseException pe) {
                lastError = pe;
                lispCode.goTo(positionToStart);
            }
            
        }
        if (lastError != null) {
            throw lastError;
        }
    }

    public void addParser(Parser... parsers) {
        for (Parser parser : parsers) {
            this.parsers.add(parser);
        }

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
