package syntax;

import java.util.Stack;

import jlisp.LispObject;

import jlisp.LispCode;
import jlisp.ParseException;
import jlisp.Parser;

public class Repitition implements Parser {

    private Parser parser = null;
    
    public Repitition(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void parse(LispCode lispCode, Stack<LispObject> stack) throws ParseException {
        
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
