package syntax;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Sequence<T> implements Parser<T> {

    private final Collection<Parser<T>> parsers = new LinkedList<>();

    @Override
    public void parse(Code lispCode, Stack<T> stack) throws ParseException {
        Stack<T> subStack = new Stack<>();
        for (Parser<T> parser : parsers) {
            parser.parse(lispCode, subStack);
        }
        stack.addAll(subStack);
    }
    
    public void addParser(Parser<T>... parser) {
        parsers.addAll(java.util.Arrays.asList(parser));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        Iterator<Parser<T>> iParser = parsers.iterator();
        while (iParser.hasNext()) {
            sb.append(iParser.next().toString());
            if (iParser.hasNext()) {
                sb.append(" ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
