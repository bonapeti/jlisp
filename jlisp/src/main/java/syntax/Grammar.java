package syntax;

import jlisp.Parser;
import jlisp.Spaces;

/**
 * Contains Lisp's syntax in Java code
 */
public class Grammar {

    private Grammar() {

    }
    
    public static Parser sequenceOf(Parser... parsers) {
        Sequence sequence = new Sequence();
        for (Parser parser : parsers) {
            sequence.addParser(parser);
        }
        return sequence;
    }

    /**
     * Creates an alteration parser
     * @param parsers
     * @return
     */
    public static Parser alterationOf(Parser... parsers) {
        Alteration alteration = new Alteration();
        for (Parser parser : parsers) {
            alteration.addParser(parser);
        }
        return alteration;
    }
    
    public static Parser whitespace() {
        return new Spaces();
    }
    
    public static Parser repititionOf(Parser parser) {
        return new Repitition(parser);
    }

}
