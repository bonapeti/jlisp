package jlisp;

public class CharacterProcessor {
    
    public static final CharacterProcessor DO_NOTHING = new CharacterProcessor();

    protected void process(char c) throws ParseException {
        
    }
    
    protected void onEndOfFile(CharPredicate expected)  throws ParseException {
        throw new ParseException("Expected " + expected.toString() + " but found end of file.");
    }
}
