import java.io.*;
import org.antlr.runtime.*;
import org.antlr.runtime.debug.DebugEventSocketProxy;

import se.raddo.raddose3D.parser.*;


public class __Test__ {

    public static void main(String args[]) throws Exception {
        InputfileLexer lex = new InputfileLexer(new ANTLRFileStream("C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\output\\__Test___input.txt", "UTF8"));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        InputfileParser g = new InputfileParser(tokens, 49100, null);
        try {
            g.configfile();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }
    }
}