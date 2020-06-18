package jlisp;

import java.util.Objects;

public class LispCode {

    private final String text;
    private int index = 0;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (i == index) {
                sb.append('[');
            }
            sb.append(text.charAt(i));
            if (i == index) {
                sb.append(']');
            }
        }
        return sb.toString();
    }
    
    public LispCode(String text) {
        this.text = Objects.requireNonNull(text, "Lisp code is missing!");
    }
    
    public boolean hasMore() {
        return index < text.length();
    }
    
    /**
     * Returns the 0 based current position
     * @return
     */
    public int getCurrentPosition() {
        return index;
    }
    
    public void goTo(int position) {
        this.index = position;
    }

    public boolean isAtBeginning() { return this.index == 0; }

    public void advanceToEnd() {
        collectUntil(CharPredicate.TRUE, CharacterProcessor.DO_NOTHING);
    }
    /**
     * Advances in the character stream until the predicate returns true
     * @param charPredicate
     */
    public void advanceUntil(CharPredicate charPredicate) {
        collectUntil(charPredicate, CharacterProcessor.DO_NOTHING);
    }
    
    public void collectUntil(CharPredicate charPredicate, final StringBuilder stringBuilder) {
        collectUntil(charPredicate, new CharacterProcessor() {
            
            @Override
            public void process(char c) {
                stringBuilder.append(c);
            }
        });
    }
    
    public void collectUntil(CharPredicate charPredicate, CharacterProcessor charProcessor) {
        while (hasMore()) {
            char c = text.charAt(index);
            if (charPredicate.assertCharacter(c)) {
                charProcessor.process(c);
                index++;
            } else {
                break;
            }
        }
    }
    
    public void expect(CharPredicate charPredicate, final StringBuilder stringBuilder) throws ParseException {
        expect(charPredicate, new CharacterProcessor() {
            
            @Override
            public void process(char c) {
                stringBuilder.append(c);
            }
        });
    }
    
    public void expectWhitespaceOrEndOfFile() throws ParseException {
        expect(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                if (Character.isWhitespace(c)) {
                    return true;
                } else {
                    throw new ParseException("Expecting whitespace but found " + c);
                }
            }
        }, new CharacterProcessor() {
            
            @Override
            protected void onEndOfFile(CharPredicate expected) throws ParseException {
            }
        });
    }
    
    public void checkNext(CharPredicate charPredicate, CharacterProcessor charProcessor) throws ParseException {
        if (hasMore()) {
            char current = text.charAt(index);
            if (!charPredicate.assertCharacter(current)) {
                throw new ParseException("Expected " + charPredicate.toString() + " but found " + current);
            }
        } else {
            charProcessor.onEndOfFile(charPredicate);
        }
    }
    
    public void expect(CharPredicate charPredicate, CharacterProcessor charProcessor) throws ParseException {
        if (hasMore()) {
            char current = text.charAt(index);
            if (charPredicate.assertCharacter(current)) {
                charProcessor.process(current);
                index++;
            } else {
                throw new ParseException("Expected " + charPredicate.toString() + " but found " + current);
            }
        } else {
            charProcessor.onEndOfFile(charPredicate);
        }
    }

    public void expect(final String expectedString) throws ParseException {
        for (int i = 0; i < expectedString.length(); i++) {
            expect(expectedString.charAt(i));
        }
    }
    
    public void expect(final char expectedCharacher) throws ParseException {
        expect(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) throws ParseException {
                return (text.charAt(index) == expectedCharacher);
            }
            
            @Override
            public String toString() {
                return "'" + expectedCharacher + "'";
            }
        }, CharacterProcessor.DO_NOTHING);
    }

}
