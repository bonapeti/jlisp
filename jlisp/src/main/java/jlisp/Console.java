package jlisp;

import jline.console.ConsoleReader;


public class Console {

    public static void main(String[] args) throws Exception {
        
        CommonLisp commonLisp = new CommonLisp();
        
        ConsoleReader console = new ConsoleReader();
        
        console.setPrompt("JLisp: ");
        console.setHistoryEnabled(true);
        
        String line = console.readLine();
        while (line != null) {
            if (":quit".equals(line.trim())) {
                return;
            }
            
            try {
                console.println(commonLisp.evaluate(line).toString());    
            } catch (Exception pe) {
                console.println(pe.getMessage());
            }
            
            line = console.readLine();
        }
        
        
        
    }
}
