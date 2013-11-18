package jlisp;


public class CommonLisp {

	private GlobalEnvironment environment = new GlobalEnvironment();

	public CommonLisp() {

		environment.defineFunction("numberp", new Function() {

			@Override
			public Expression evaluate(IList arguments,
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
            public Expression evaluate(IList arguments,
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
            public Expression evaluate(IList arguments,
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
			public Expression evaluate(IList arguments,
					Environment environment) {
				return arguments;
			}
		});
		environment.defineFunction("format", new Function() {

			@Override
			public Expression evaluate(IList arguments,
					Environment environment) {
				System.out.println(arguments.tail().head());
				return Lisp.NIL;
			}
		});
		environment.defineFunction("+", new Function() {

			@Override
			public Expression evaluate(IList arguments,
					Environment environment) {
				return arguments.foldLeft(new Fixnum(0), new Function3<Expression,Expression,Expression>() {

					@Override
					public Expression apply(Expression p1, Expression p2) {
						return new Fixnum( ((Number)p1).intValue() + ((Number) p2).intValue());
					}
					
				});
			}
		});
		environment.defineFunction("*", new Function() {

			@Override
			public Expression evaluate(IList arguments,
					Environment environment) {
				return arguments.foldLeft(new Fixnum(1), new Function3<Expression,Expression,Expression>() {

					@Override
					public Expression apply(Expression p1, Expression p2) {
						return new Fixnum( ((Number)p1).intValue() * ((Number) p2).intValue());
					}
					
				});
			}
		});
		environment.defineSpecialForm("defun", new DefineFunction());
		environment.defineSpecialForm("quote", new Quote());
	}
	
	public Object evaluate(String line) {
		return Lisp.read(line).evaluate(environment);
	}
}
