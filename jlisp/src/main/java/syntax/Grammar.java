package syntax;

import jlisp.Parser;
import jlisp.Spaces;

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
