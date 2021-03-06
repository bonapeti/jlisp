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

            console.println(commonLisp.readAndEvaluate(line));
            
            line = console.readLine();
        }
        
        
        
    }
}
