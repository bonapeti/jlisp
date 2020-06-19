package syntax;

import java.util.Stack;

public class Repitition<T> implements Parser<T> {

    private final Parser<T> parser;
    
    public Repitition(Parser<T> parser) {
        this.parser = parser;
    }

    @Override
    public void parse(Code lispCode, Stack<T> stack) throws ParseException {
        
        while (true) {
            int position = lispCode.getCurrentPosition();
            try {
                parser.parse(lispCode, stack);
            } catch (ParseException pe) {
                lispCode.goTo(position);
                break;
            }
            
        }

    }
    
    @Override
    public String toString() {
        return parser.toString() + "*";
    }

}
