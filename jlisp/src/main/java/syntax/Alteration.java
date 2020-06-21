package syntax;

import java.util.*;

/**
 * Parser which lets each provided parser try evaluating the code until one succeeds
 */
public class Alteration<T> implements Parser<T> {

    private final Collection<Parser<T>> parsers = new LinkedList<>();

    @Override
    public void parse(Code lispCode, Stack<T> stack) throws ParseException {
        ParseException lastError = null;
        int positionToStart = lispCode.getCurrentPosition();
        for (Parser<T> parser : parsers) {
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

    public void addParser(Parser<T>... parsers) {
        this.parsers.addAll(Arrays.asList(parsers));

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        Iterator<Parser<T>> iParser = parsers.iterator();
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
