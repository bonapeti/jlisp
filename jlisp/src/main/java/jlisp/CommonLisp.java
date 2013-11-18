package jlisp;

import java.io.IOException;


public class CommonLisp {

	private GlobalEnvironment environment = new GlobalEnvironment();

	public CommonLisp() {

		environment.defineFunction("numberp", new Function() {

			@Override
			public LispObject evaluate(IList arguments,
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
            public LispObject evaluate(IList arguments,
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
            public LispObject evaluate(IList arguments,
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
			public LispObject evaluate(IList arguments,
					Environment environment){
				return arguments;
			}
		});
		environment.defineFunction("format", new Function() {

			@Override
			public LispObject evaluate(IList arguments,
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
			public LispObject evaluate(IList arguments,
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
			public LispObject evaluate(IList arguments,
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
		environment.defineSpecialForm("quote", new Quote());
	}
	
	public LispObject evaluate(String line) {
		return Lisp.read(line).evaluate(environment);
	}
}
