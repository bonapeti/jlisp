package jlisp;


public class Console {

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
        }
        try {
            System.out.println(Lisp.read(sb.toString()).evaluate());    
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }
        
    }
}
