package jlisp;

public class CharIterator {

    private String text = null;
    private int index = 0;
    
    public CharIterator(String text) {
        this.text = text;
    }
    
    public boolean hasMore() {
        return index < text.length();
    }
    
    public int getCurrentPosition() {
        return index;
    }
    
    public void setCurrentPosition(int position) {
        this.index = position;
    }
    
    public void advanceUntilEnd() {
        advanceUntil(new CharPredicate() {
            
            @Override
            public boolean assertCharacter(char c) {
                return true;
            }
        });
    }
    
    public void advanceUntil(CharPredicate charPredicate) {
        advanceUntil(charPredicate, CharacterProcessor.DO_NOTHING);
    }
    
    public void advanceUntil(CharPredicate charPredicate, final StringBuilder stringBuilder) {
        advanceUntil(charPredicate, new CharacterProcessor() {
            
            @Override
            public void process(char c) {
                stringBuilder.append(c);
            }
        });
    }
    
    public void advanceUntil(CharPredicate charPredicate, CharacterProcessor charProcessor) {
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
            throw new ParseException("Expected " + charPredicate.toString() + " but found end of file!");
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
