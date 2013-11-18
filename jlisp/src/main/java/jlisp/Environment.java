package jlisp;


public interface Environment {
    
    SpecialForm getSpecialForm(Symbol name);
    
    void defineFunction(Symbol name, Function definition);
    
    void bindValue(Symbol symbol, LispObject value);
    
    LispObject getValue(Symbol symbol);
}
