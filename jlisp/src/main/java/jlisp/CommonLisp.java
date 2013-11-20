package jlisp;

import java.io.IOException;
import static jlisp.Lisp.asFixnum;


public class CommonLisp {

	private GlobalEnvironment environment = new GlobalEnvironment();

	private LispObject not(LispObject argument) {
	    return argument.isTrue() ? Lisp.NIL : Lisp.T;
	}
	
	private LispObject isOdd(Number argument) {
        return argument.intValue() % 2 != 0 ? Lisp.T : Lisp.NIL;
    }
	
	private LispObject isEven(Number argument) {
        return not(isOdd(argument));
    }
	
	private List firstArgumentAsList(List arguments) {
	    LispObject firstArgument = arguments.head();
	    
        if (!(firstArgument instanceof List)) {
            throw new EvaluationException(firstArgument.toString() + " is not a list");
            
        }
        return (List)firstArgument;
	}
	
	
	
	public CommonLisp() {

	    environment.defineFunction("<", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return asFixnum(arguments.first()).smallerThan(asFixnum(arguments.second()));
            }
        });
	    environment.defineFunction(">", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return asFixnum(arguments.first()).greaterThan(asFixnum(arguments.second()));
            }
        });
	    environment.defineFunction("eval", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return arguments.first().evaluate(environment);
            }
        });
	    environment.defineFunction("equal", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                if (arguments.first().equals(arguments.second())) {
                    return Lisp.T;
                } else {
                    return Lisp.NIL;
                }
            }
        });
	    environment.defineFunction("atomp", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                if (!(arguments.head() instanceof ConsCell)) {
                    return Lisp.T;
                } else {
                    return Lisp.NIL;
                }
            }
        });
	    environment.defineFunction("consp", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                if (arguments.head() instanceof ConsCell) {
                    return Lisp.T;
                } else {
                    return Lisp.NIL;
                }
            }
        });
	    environment.defineFunction("listp", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                if (arguments.head() instanceof List) {
                    return Lisp.T;
                } else {
                    return Lisp.NIL;
                }
            }
        });
	    environment.defineFunction("cons", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return new ConsCell(arguments.head(),(List)arguments.second());
            }
        });
	    environment.defineFunction("car", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return firstArgumentAsList(arguments).car();
            }
        });
	    environment.defineFunction("cdr", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return firstArgumentAsList(arguments).cdr();
            }
        });
	    environment.defineFunction("rest", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return firstArgumentAsList(arguments).tail();
            }
        });
	    environment.defineFunction("first", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return firstArgumentAsList(arguments).first();
            }
        });
	    environment.defineFunction("second", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return firstArgumentAsList(arguments).second();
            }
        });
	    environment.defineFunction("third", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return firstArgumentAsList(arguments).third();
            }
        });
	    environment.defineFunction("length", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return ((List)arguments.head()).length();
            }
        });
	    environment.defineFunction("not", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return not(arguments.head());
            }
        });
	    environment.defineFunction("oddp", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return isOdd((Number)arguments.head());
            }
        });
	    environment.defineFunction("evenp", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                return isEven((Number)arguments.head());
            }
        });
		environment.defineFunction("numberp", new Function() {

			@Override
			public LispObject evaluate(List arguments,
					Environment environment) {
				if (arguments.head() instanceof Number) {
					return Lisp.T;
				} else {
					return Lisp.NIL;
				}
			}
		});
		environment.defineFunction("symbolp", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                if (arguments.head() instanceof Symbol) {
                    return Lisp.T;
                } else {
                    return Lisp.NIL;
                }
            }
        });
		environment.defineFunction("zerop", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment) {
                if (0 == ((Number)arguments.head()).intValue()) {
                    return Lisp.T;
                } else {
                    return Lisp.NIL;
                }
            }
        });
		environment.defineFunction("list", new Function() {

			@Override
			public LispObject evaluate(List arguments,
					Environment environment){
				return arguments;
			}
		});
		environment.defineFunction("format", new Function() {

			@Override
			public LispObject evaluate(List arguments,
					Environment environment){
				try {
                    arguments.tail().head().print(System.out);
                    System.out.println();
                    return Lisp.NIL;
                } catch (IOException e) {
                    throw new EvaluationException(e.getMessage());
                }
				
			}
		});
		environment.defineFunction("+", new Function() {

			@Override
			public LispObject evaluate(List arguments,
					Environment environment){
				return arguments.foldLeft(new Fixnum(0), new Function2<LispObject,LispObject,LispObject>() {

					@Override
					public LispObject apply(LispObject p1, LispObject p2) {
						return new Fixnum( ((Number)p1).intValue() + ((Number) p2).intValue());
					}
					
				});
			}
		});
		environment.defineFunction("*", new Function() {

			@Override
			public LispObject evaluate(List arguments,
					Environment environment){
				return arguments.foldLeft(new Fixnum(1), new Function2<LispObject,LispObject,LispObject>() {

					@Override
					public LispObject apply(LispObject p1, LispObject p2) {
						return new Fixnum( ((Number)p1).intValue() * ((Number) p2).intValue());
					}
					
				});
			}
		});
		environment.defineSpecialForm("defun", new DefineFunction());
		environment.defineSpecialForm("quote", new QuoteFunction());
		environment.defineSpecialForm("if", new IfFunction());
		environment.defineSpecialForm("cond", new Cond());
	}
	
	public LispObject evaluate(String line) {
		return Lisp.read(line).evaluate(environment);
	}
}
