package jlisp;

public interface CharacterProcessor {

    CharacterProcessor DO_NOTHING = new CharacterProcessor() {
        
        @Override
        public void process(char c) {
        }
    };
    
    void process(char c);
}
