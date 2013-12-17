package jlisp;

import java.io.IOException;
import static jlisp.Lisp.asFixnum;


public class CommonLisp {

	private GlobalEnvironment environment = new GlobalEnvironment();

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
	    return Lisp.asList(arguments.head());
	}
	
	public static LispObject member(final LispObject element, List list) {
        return list.findFirst(new Function1<List, Boolean>() {
            
            @Override
            public Boolean apply(List p) {
                return equal(element, p.head()).isTrue();
            }
        }, new Function1<List, LispObject>() {
            
            @Override
            public LispObject apply(List p) {
                return p;
            }
        });
    }
	
	public static List intersection(List list1, final List list2) {
	    return list1.filter(new Function1<LispObject, LispObject>() {
            
            @Override
            public LispObject apply(LispObject firstArgElement) {
                return member(firstArgElement, list2);
            }
        });
	}
	
	public static LispObject isSubset(final List list1, final List list2) {
	    return difference(list1, list2).isEmpty() ? Lisp.T : Lisp.NIL;
	}
	
	public static List union(final List list1, final List list2) {
	    return list2.foldLeft(list1, new Function2<List,List, LispObject>() {

            @Override
            public List apply(List p1, final LispObject p2) {
                if (p1.findFirst(new Function1<List, Boolean>() {
                    
                    @Override
                    public Boolean apply(List element) {
                        return equal(p2, element.car()).isTrue();
                    }
                }, new Function1<List, LispObject>() {
                    
                    @Override
                    public LispObject apply(List p) {
                        return Lisp.T;
                    }
                }).isTrue()) {
                    return p1;
                } else {
                    return p1.append(p2);
                }
            }
	        
	    });
    }
	
	public static List difference(List list1, final List list2) {
        return list1.filter(new Function1<LispObject, LispObject>() {
            
            @Override
            public LispObject apply(LispObject firstArgElement) {
                return not(member(firstArgElement, list2));
            }
        });
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
                return equal(arguments.first(),arguments.second());
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
                return new ConsCell(arguments.head(),Lisp.asList(arguments.second()));
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
		environment.defineFunction("/", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment){
                return arguments.tail().foldLeft(arguments.car(), new Function2<LispObject,LispObject,LispObject>() {

                    @Override
                    public LispObject apply(LispObject p1, LispObject p2) {
                        return new Fixnum( ((Number)p1).intValue() / ((Number) p2).intValue());
                    }
                    
                });
            }
        });
		environment.defineFunction("-", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment){
                return arguments.tail().foldLeft(arguments.car(), new Function2<LispObject,LispObject,LispObject>() {

                    @Override
                    public LispObject apply(LispObject p1, LispObject p2) {
                        return new Fixnum( ((Number)p1).intValue() - ((Number) p2).intValue());
                    }
                    
                });
            }
        });
		environment.defineFunction("append", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment){
                return arguments.foldLeft(Lisp.NIL, new Function2<LispObject,LispObject,LispObject>() {

                    @Override
                    public LispObject apply(LispObject p1, LispObject p2) {
                        return Lisp.asList(p2).foldLeft(p1, new Function2<LispObject,LispObject,LispObject>() {

                            @Override
                            public LispObject apply(LispObject p3, LispObject p4) {
                                return Lisp.asList(p3).append(p4);
                            }
                        });
                        
                    }
                    
                });
            }
        });
		environment.defineFunction("reverse", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment){
                return Lisp.asList(arguments.first()).foldRight(Lisp.NIL, new Function2<LispObject,LispObject,LispObject>() {

                    @Override
                    public LispObject apply(LispObject p1, LispObject p2) {
                        return Lisp.asList(p2).append(p1);
                    }
                    
                });
            }
        });
		environment.defineFunction("last", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment){
                return Lisp.asList(arguments.first()).findFirst(new Function1<List, Boolean>() {
                    
                    @Override
                    public Boolean apply(List p) {
                        return p.tail().isEmpty();
                    }
                }, new Function1<List, LispObject>() {
                    
                    @Override
                    public LispObject apply(List p) {
                        return p;
                    }
                });
            }
        });
		environment.defineFunction("remove", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment){
                final LispObject element = arguments.first();
                return Lisp.asList(arguments.second()).filter(new Function1<LispObject, LispObject>() {
                    
                    @Override
                    public LispObject apply(LispObject p) {
                        return not(equal(element,p));
                    }
                });
            }
        });
		environment.defineFunction("member", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment){
                return member(arguments.first(), Lisp.asList(arguments.second()));
            }
        });
		environment.defineFunction("intersection", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment){
                return intersection(Lisp.asList(arguments.first()), Lisp.asList(arguments.second()));
            }
        });
        environment.defineFunction("union", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment){
                
                return union(Lisp.asList(arguments.first()), Lisp.asList(arguments.second()));
            }
        });		
		environment.defineFunction("set-difference", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment){
                return difference(Lisp.asList(arguments.first()), Lisp.asList(arguments.second()));
            }
        });
		environment.defineFunction("subsetp", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment){
                return isSubset(Lisp.asList(arguments.first()), Lisp.asList(arguments.second()));
            }
        });
		environment.defineFunction("funcall", new Function() {

            @Override
            public LispObject evaluate(List arguments,
                    Environment environment){
                FunctionCall functionCall = (FunctionCall)arguments.car();
                return functionCall.evaluate(arguments.cdr(),environment);
            }
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
	
	public LispObject evaluate(String line) {
		return Lisp.read(line).evaluate(environment);
	}
}
