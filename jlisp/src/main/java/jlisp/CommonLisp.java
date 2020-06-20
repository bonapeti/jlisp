package jlisp;

import java.io.IOException;

import static jlisp.Fixnum.of;

/**
 * Main class to evaluate Lisp expressions
 *
 *
 */
public class CommonLisp {

	private final GlobalEnvironment environment = new GlobalEnvironment();

	public static LispObject not(LispObject argument) {
	    return argument.isTrue() ? Lisp.NIL : Lisp.T;
	}
	
	public static LispObject equal(LispObject argument1, LispObject argument2) {
	    if (argument1.equals(argument2)) {
            return Lisp.T;
        } else {
            return Lisp.NIL;
        }
    }

    public static LispObject isOdd(Number argument) {
        return argument.intValue() % 2 != 0 ? Lisp.T : Lisp.NIL;
    }
	
	public static LispObject isEven(Number argument) {
        return not(isOdd(argument));
    }
	
	public static List firstArgumentAsList(List arguments) {
	    return List.of(arguments.head());
	}
	
	public static LispObject callFunction(FunctionCall function, List arguments, Environment environment) {
	    return function.evaluate(arguments, environment);
	}
	
	public static LispObject member(final LispObject element, List list) {
        return list.findFirst(p -> equal(element, p.head()).isTrue(), p -> p);
    }
	
	public static List intersection(List list1, final List list2) {
	    return list1.filter(firstArgElement -> member(firstArgElement, list2));
	}
	
	public static LispObject isSubset(final List list1, final List list2) {
	    return difference(list1, list2).isEmpty() ? Lisp.T : Lisp.NIL;
	}
	
	public static List union(final List list1, final List list2) {
	    return list2.foldLeft(list1, (p1, p2) -> {
            if (p1.findFirst(element -> equal(p2, element.car()).isTrue(), p -> Lisp.T).isTrue()) {
                return p1;
            } else {
                return p1.append(p2);
            }
        });
    }
	
	public static List difference(List list1, final List list2) {
        return list1.filter(firstArgElement -> not(member(firstArgElement, list2)));
    }
	
	
	public CommonLisp() {

	    environment.defineFunction("<", (arguments, environment) -> of(arguments.first()).smallerThan(of(arguments.second())));
	    environment.defineFunction(">", (arguments, environment) -> of(arguments.first()).greaterThan(of(arguments.second())));
	    environment.defineFunction("eval", (arguments, environment) -> arguments.first().evaluate(environment));
	    environment.defineFunction("equal", (arguments, environment) -> equal(arguments.first(),arguments.second()));
	    environment.defineFunction("atomp", (arguments, environment) -> {
            if (!(arguments.head() instanceof ConsCell)) {
                return Lisp.T;
            } else {
                return Lisp.NIL;
            }
        });
	    environment.defineFunction("consp", (arguments, environment) -> {
            if (arguments.head() instanceof ConsCell) {
                return Lisp.T;
            } else {
                return Lisp.NIL;
            }
        });
	    environment.defineFunction("listp", (arguments, environment) -> List.isList(arguments.car()));
	    environment.defineFunction("cons", (arguments, environment) -> {
            if (List.isList(arguments.second()).isTrue()) {
                return new ConsCell(arguments.head(), List.of(arguments.second()));
            } else {
                return new ConsCell(arguments.head(),new NotNilListEnd(arguments.second()));
            }
        });
	    environment.defineFunction("car", (arguments, environment) -> firstArgumentAsList(arguments).car());
	    environment.defineFunction("cdr", (arguments, environment) -> firstArgumentAsList(arguments).cdr());
	    environment.defineFunction("rest", (arguments, environment) -> firstArgumentAsList(arguments).tail());
	    environment.defineFunction("first", (arguments, environment) -> firstArgumentAsList(arguments).first());
	    environment.defineFunction("second", (arguments, environment) -> firstArgumentAsList(arguments).second());
	    environment.defineFunction("third", (arguments, environment) -> firstArgumentAsList(arguments).third());
	    environment.defineFunction("length", (arguments, environment) -> List.of(arguments.head()).length());
	    environment.defineFunction("not", (arguments, environment) -> not(arguments.head()));
	    environment.defineFunction("oddp", (arguments, environment) -> isOdd((Number)arguments.head()));
	    environment.defineFunction("evenp", (arguments, environment) -> isEven((Number)arguments.head()));
		environment.defineFunction("numberp", (arguments, environment) -> {
            if (arguments.head() instanceof Number) {
                return Lisp.T;
            } else {
                return Lisp.NIL;
            }
        });
		environment.defineFunction("symbolp", (arguments, environment) -> {
            if (arguments.head() instanceof Symbol) {
                return Lisp.T;
            } else {
                return Lisp.NIL;
            }
        });
		environment.defineFunction("zerop", (arguments, environment) -> {
            if (0 == ((Number)arguments.head()).intValue()) {
                return Lisp.T;
            } else {
                return Lisp.NIL;
            }
        });
		environment.defineFunction("list", (arguments, environment) -> arguments);
		environment.defineFunction("format", (arguments, environment) -> {
    try {
arguments.tail().head().print(System.out);
System.out.println();
return Lisp.NIL;
} catch (IOException e) {
throw new EvaluationException(e.getMessage());
}

});
		environment.defineFunction("+", (arguments, environment) -> arguments.foldLeft(Fixnum.ZERO, (Function2<LispObject, LispObject, LispObject>) (p1, p2) -> Fixnum.as( ((Number)p1).intValue() + ((Number) p2).intValue())));
		environment.defineFunction("*", (arguments, environment) -> arguments.foldLeft(Fixnum.ONE, (Function2<LispObject, LispObject, LispObject>) (p1, p2) -> Fixnum.as( ((Number)p1).intValue() * ((Number) p2).intValue())));
		environment.defineFunction("/", (arguments, environment) -> arguments.tail().foldLeft(arguments.car(), (p1, p2) -> Fixnum.as( ((Number)p1).intValue() / ((Number) p2).intValue())));
		environment.defineFunction("-", (arguments, environment) -> arguments.tail().foldLeft(arguments.car(), (p1, p2) -> Fixnum.as( ((Number)p1).intValue() - ((Number) p2).intValue())));
		environment.defineFunction("abs", (arguments, environment) -> of(arguments.first()).absoluteValue());
		environment.defineFunction("append", (arguments, environment) -> arguments.foldLeft(Lisp.NIL, (Function2<LispObject, LispObject, LispObject>) (p1, p2) -> List.of(p2).foldLeft(p1, new Function2<LispObject,LispObject,LispObject>() {

            @Override
            public LispObject apply(LispObject p3, LispObject p4) {
                return List.of(p3).append(p4);
            }
        })));
		environment.defineFunction("reverse", (arguments, environment) -> List.of(arguments.first()).foldRight(Lisp.NIL, (Function2<LispObject, LispObject, LispObject>) (p1, p2) -> List.of(p2).append(p1)));
		environment.defineFunction("last", (arguments, environment) -> List.of(arguments.first()).findFirst(p -> p.tail().isEmpty(), p -> p));
		environment.defineFunction("remove", (arguments, environment) -> {
    final LispObject firstArgument = arguments.first();
    return List.of(arguments.second()).filter(p -> not(equal(firstArgument,p)));
});
		environment.defineFunction("member", (arguments, environment) -> member(arguments.first(), List.of(arguments.second())));
		environment.defineFunction("intersection", (arguments, environment) -> intersection(List.of(arguments.first()), List.of(arguments.second())));
        environment.defineFunction("union", (arguments, environment) -> union(List.of(arguments.first()), List.of(arguments.second())));
		environment.defineFunction("set-difference", (arguments, environment) -> difference(List.of(arguments.first()), List.of(arguments.second())));
		environment.defineFunction("subsetp", (arguments, environment) -> isSubset(List.of(arguments.first()), List.of(arguments.second())));
		environment.defineFunction("funcall", (arguments, environment) -> callFunction(Lisp.asFunction(arguments.car()), arguments.cdr(), environment));
		environment.defineFunction("mapcar", (arguments, environment) -> {
    final FunctionCall functionCall = Lisp.asFunction(arguments.car());
    List list = List.of(arguments.second());
    return list.map(p -> callFunction(functionCall, new ConsCell(p), environment));
});
		
		
		environment.defineSpecialForm("defun", new DefineFunction());
		environment.defineSpecialForm("quote", new QuoteFunction());
		environment.defineSpecialForm("if", new IfFunction());
		environment.defineSpecialForm("cond", new Cond());
		environment.defineSpecialForm("and", new And());
		environment.defineSpecialForm("or", new Or());
		environment.defineSpecialForm("setf", new Setf());
		environment.defineSpecialForm("let*", new LetStar());
		environment.defineSpecialForm("function", new FunctionForm());
	}

    /**
     * Evaluates a Lisp code
     * @param lispCode Lisp code
     * @return evaluated List object
     */
	public String readAndEvaluate(String lispCode) {
        StringBuilder reply = new StringBuilder();
        try {
            Lisp.read(lispCode).evaluate(environment).print(reply);
            return  reply.toString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
