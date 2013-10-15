package syntax;

import java.util.Iterator;
import java.util.Stack;

import jlisp.CharIterator;
import jlisp.Expression;
import jlisp.ParseException;
import jlisp.Parser;

public class Repitition implements Parser {

    private Parser parser = null;
    
    public Repitition(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void parse(CharIterator charIterator, Stack<Expression> stack) throws ParseException {
        
        while (true) {
            int position = charIterator.getCurrentPosition();
            try {
                parser.parse(charIterator, stack);    
            } catch (ParseException pe) {
                charIterator.setCurrentPosition(position);
                break;
            }
            
        }

    }
    
    @Override
    public String toString() {
        return parser.toString() + "*";
    }

}
