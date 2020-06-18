package jlisp;

public interface CharPredicate {

    public static final CharPredicate TRUE = new CharPredicate() {
        @Override
        public boolean assertCharacter(char c) throws ParseException {
            return true;
        }
    };

    boolean assertCharacter(char c) throws ParseException;
}
