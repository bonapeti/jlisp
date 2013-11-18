package jlisp;


public interface Environment {
    
    SpecialForm getSpecialForm(Symbol name);
    
    void defineFunction(Symbol name, Function definition);
    
    void bindValue(Symbol symbol, Expression value);
    
    Expression getValue(Symbol symbol);
}
