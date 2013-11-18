package syntax;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import jlisp.CharIterator;
import jlisp.ParseException;
import jlisp.Parser;
import jlisp.LispObject;

public class Sequence implements Parser {

    private Collection<Parser> parsers = new LinkedList<Parser>();

    @Override
    public void parse(CharIterator charIterator, Stack<LispObject> stack) throws ParseException {
        Stack<LispObject> subStack = new Stack<LispObject>();
        for (Parser parser : parsers) {
            parser.parse(charIterator, subStack);
        }
        for (LispObject lispObject : subStack) {
            stack.add(lispObject);
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
                sb.append(" ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
