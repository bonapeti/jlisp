package jlisp;

public interface CharPredicate {

    CharPredicate TRUE = c -> true;

    boolean assertCharacter(char c) throws ParseException;
}
