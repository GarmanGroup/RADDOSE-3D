// $ANTLR 3.4 C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g 2018-02-09 13:13:10

package se.raddo.raddose3D.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class InputfileLexer extends Lexer {
    public static final int EOF=-1;
    public static final int ABSCOEFCALC=4;
    public static final int ANGLEL=5;
    public static final int ANGLEP=6;
    public static final int ANGULARRESOLUTION=7;
    public static final int AVERAGE=8;
    public static final int BEAM=9;
    public static final int CALCULATEFLESCAPE=10;
    public static final int CALCULATEPEESCAPE=11;
    public static final int CIF=12;
    public static final int CIFNAME=13;
    public static final int CIRCULAR=14;
    public static final int COLLIMATION=15;
    public static final int COMMENT=16;
    public static final int CONTAINERDENSITY=17;
    public static final int CONTAINERMATERIALELEMENTS=18;
    public static final int CONTAINERMATERIALMIXTURE=19;
    public static final int CONTAINERMATERIALTYPE=20;
    public static final int CONTAINERTHICKNESS=21;
    public static final int CRYOSOLUTION=22;
    public static final int CRYSTAL=23;
    public static final int DDM=24;
    public static final int DECAYPARAM=25;
    public static final int DEFAULT=26;
    public static final int DIFFRACTIONDECAYMODEL=27;
    public static final int DIMENSION=28;
    public static final int DUMMY=29;
    public static final int ELEMENT=30;
    public static final int ELEMENTAL=31;
    public static final int ENERGY=32;
    public static final int EXPONENT=33;
    public static final int EXPOSURETIME=34;
    public static final int FILE=35;
    public static final int FLOAT=36;
    public static final int FLRESOLUTION=37;
    public static final int FLUX=38;
    public static final int FWHM=39;
    public static final int HORIZONTAL=40;
    public static final int KEV=41;
    public static final int LEAL=42;
    public static final int LINEAR=43;
    public static final int MATERIALELEMENTS=44;
    public static final int MATERIALMIXTURE=45;
    public static final int MATERIALTYPE=46;
    public static final int MIXTURE=47;
    public static final int MODELFILE=48;
    public static final int NONE=49;
    public static final int NUMDNA=50;
    public static final int NUMMONOMERS=51;
    public static final int NUMRESIDUES=52;
    public static final int NUMRNA=53;
    public static final int OILBASED=54;
    public static final int PDB=55;
    public static final int PDBNAME=56;
    public static final int PERESOLUTION=57;
    public static final int PIXELSIZE=58;
    public static final int PIXELSPERMICRON=59;
    public static final int PROTEINCONC=60;
    public static final int PROTEINCONCENTRATION=61;
    public static final int PROTEINHEAVYATOMS=62;
    public static final int RDFORTAN=63;
    public static final int RDJAVA=64;
    public static final int RECTANGULAR=65;
    public static final int ROTAXBEAMOFFSET=66;
    public static final int SAXS=67;
    public static final int SAXSSEQ=68;
    public static final int SEQFILE=69;
    public static final int SEQUENCE=70;
    public static final int SEQUENCEFILE=71;
    public static final int SIMPLE=72;
    public static final int SMALLMOLE=73;
    public static final int SMALLMOLEATOMS=74;
    public static final int SOLVENTFRACTION=75;
    public static final int SOLVENTHEAVYCONC=76;
    public static final int STARTOFFSET=77;
    public static final int STRING=78;
    public static final int TRANSLATEPERDEGREE=79;
    public static final int TYPE=80;
    public static final int UNITCELL=81;
    public static final int VERTICAL=82;
    public static final int WEDGE=83;
    public static final int WIREFRAMETYPE=84;
    public static final int WS=85;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public InputfileLexer() {} 
    public InputfileLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InputfileLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g"; }

    // $ANTLR start "CRYSTAL"
    public final void mCRYSTAL() throws RecognitionException {
        try {
            int _type = CRYSTAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:212:9: ( ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'Y' | 'y' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:212:11: ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'Y' | 'y' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CRYSTAL"

    // $ANTLR start "TYPE"
    public final void mTYPE() throws RecognitionException {
        try {
            int _type = TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:271:6: ( ( 'T' | 't' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:271:8: ( 'T' | 't' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TYPE"

    // $ANTLR start "DIFFRACTIONDECAYMODEL"
    public final void mDIFFRACTIONDECAYMODEL() throws RecognitionException {
        try {
            int _type = DIFFRACTIONDECAYMODEL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:275:23: ( ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'L' | 'l' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:275:25: ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIFFRACTIONDECAYMODEL"

    // $ANTLR start "DDM"
    public final void mDDM() throws RecognitionException {
        try {
            int _type = DDM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:276:5: ( ( 'D' | 'd' ) ( 'D' | 'd' ) ( 'M' | 'm' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:276:7: ( 'D' | 'd' ) ( 'D' | 'd' ) ( 'M' | 'm' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DDM"

    // $ANTLR start "SIMPLE"
    public final void mSIMPLE() throws RecognitionException {
        try {
            int _type = SIMPLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:282:8: ( ( 'S' | 's' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'P' | 'p' ) ( 'L' | 'l' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:282:10: ( 'S' | 's' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'P' | 'p' ) ( 'L' | 'l' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIMPLE"

    // $ANTLR start "LINEAR"
    public final void mLINEAR() throws RecognitionException {
        try {
            int _type = LINEAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:283:8: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:283:10: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LINEAR"

    // $ANTLR start "LEAL"
    public final void mLEAL() throws RecognitionException {
        try {
            int _type = LEAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:284:6: ( ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:284:8: ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LEAL"

    // $ANTLR start "DECAYPARAM"
    public final void mDECAYPARAM() throws RecognitionException {
        try {
            int _type = DECAYPARAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:288:13: ( ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'M' | 'm' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:288:15: ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'M' | 'm' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DECAYPARAM"

    // $ANTLR start "ABSCOEFCALC"
    public final void mABSCOEFCALC() throws RecognitionException {
        try {
            int _type = ABSCOEFCALC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:292:13: ( ( ( 'A' | 'a' ) ( 'B' | 'b' ) ( 'S' | 's' ) )? ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'C' | 'c' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:292:15: ( ( 'A' | 'a' ) ( 'B' | 'b' ) ( 'S' | 's' ) )? ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'C' | 'c' )
            {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:292:15: ( ( 'A' | 'a' ) ( 'B' | 'b' ) ( 'S' | 's' ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='A'||LA1_0=='a') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:292:16: ( 'A' | 'a' ) ( 'B' | 'b' ) ( 'S' | 's' )
                    {
                    if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ABSCOEFCALC"

    // $ANTLR start "DUMMY"
    public final void mDUMMY() throws RecognitionException {
        try {
            int _type = DUMMY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:306:7: ( ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'M' | 'm' ) ( 'Y' | 'y' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:306:9: ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'M' | 'm' ) ( 'Y' | 'y' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DUMMY"

    // $ANTLR start "DEFAULT"
    public final void mDEFAULT() throws RecognitionException {
        try {
            int _type = DEFAULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:307:9: ( ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'A' | 'a' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'T' | 't' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:307:11: ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'A' | 'a' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DEFAULT"

    // $ANTLR start "AVERAGE"
    public final void mAVERAGE() throws RecognitionException {
        try {
            int _type = AVERAGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:308:9: ( ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'G' | 'g' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:308:11: ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'G' | 'g' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AVERAGE"

    // $ANTLR start "RDFORTAN"
    public final void mRDFORTAN() throws RecognitionException {
        try {
            int _type = RDFORTAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:309:10: ( ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'V' | 'v' ) ( '2' | '3' )? )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:309:12: ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'V' | 'v' ) ( '2' | '3' )?
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:309:39: ( '2' | '3' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0 >= '2' && LA2_0 <= '3')) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:
                    {
                    if ( (input.LA(1) >= '2' && input.LA(1) <= '3') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RDFORTAN"

    // $ANTLR start "RDJAVA"
    public final void mRDJAVA() throws RecognitionException {
        try {
            int _type = RDJAVA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:310:8: ( ( 'R' | 'r' ) ( 'D' | 'd' ) ( '3' ) ( 'D' | 'd' )? )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:310:10: ( 'R' | 'r' ) ( 'D' | 'd' ) ( '3' ) ( 'D' | 'd' )?
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:310:28: ( '3' )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:310:29: '3'
            {
            match('3'); 

            }


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:310:33: ( 'D' | 'd' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='D'||LA3_0=='d') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:
                    {
                    if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RDJAVA"

    // $ANTLR start "PDB"
    public final void mPDB() throws RecognitionException {
        try {
            int _type = PDB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:311:5: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:311:7: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PDB"

    // $ANTLR start "SAXS"
    public final void mSAXS() throws RecognitionException {
        try {
            int _type = SAXS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:312:6: ( ( 'S' | 's' ) ( 'A' | 'a' ) ( 'X' | 'x' ) ( 'S' | 's' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:312:8: ( 'S' | 's' ) ( 'A' | 'a' ) ( 'X' | 'x' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SAXS"

    // $ANTLR start "SEQUENCE"
    public final void mSEQUENCE() throws RecognitionException {
        try {
            int _type = SEQUENCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:313:10: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:313:12: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Q'||input.LA(1)=='q' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEQUENCE"

    // $ANTLR start "SAXSSEQ"
    public final void mSAXSSEQ() throws RecognitionException {
        try {
            int _type = SAXSSEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:314:9: ( ( 'S' | 's' ) ( 'A' | 'a' ) ( 'X' | 'x' ) ( 'S' | 's' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:314:11: ( 'S' | 's' ) ( 'A' | 'a' ) ( 'X' | 'x' ) ( 'S' | 's' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'Q' | 'q' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Q'||input.LA(1)=='q' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SAXSSEQ"

    // $ANTLR start "SMALLMOLE"
    public final void mSMALLMOLE() throws RecognitionException {
        try {
            int _type = SMALLMOLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:315:11: ( ( 'S' | 's' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:315:13: ( 'S' | 's' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SMALLMOLE"

    // $ANTLR start "CIF"
    public final void mCIF() throws RecognitionException {
        try {
            int _type = CIF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:316:5: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' ) ( 'S' | 's' ) ( 'M' | 'm' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:316:7: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' ) ( 'S' | 's' ) ( 'M' | 'm' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CIF"

    // $ANTLR start "DIMENSION"
    public final void mDIMENSION() throws RecognitionException {
        try {
            int _type = DIMENSION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:330:11: ( ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'S' | 's' )? )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:330:13: ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'S' | 's' )?
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:330:94: ( 'S' | 's' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='S'||LA4_0=='s') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:
                    {
                    if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIMENSION"

    // $ANTLR start "ANGLEP"
    public final void mANGLEP() throws RecognitionException {
        try {
            int _type = ANGLEP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:335:8: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'P' | 'p' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:335:10: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'P' | 'p' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ANGLEP"

    // $ANTLR start "ANGLEL"
    public final void mANGLEL() throws RecognitionException {
        try {
            int _type = ANGLEL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:340:8: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'L' | 'l' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:340:10: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ANGLEL"

    // $ANTLR start "PIXELSPERMICRON"
    public final void mPIXELSPERMICRON() throws RecognitionException {
        try {
            int _type = PIXELSPERMICRON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:344:17: ( ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:344:19: ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PIXELSPERMICRON"

    // $ANTLR start "UNITCELL"
    public final void mUNITCELL() throws RecognitionException {
        try {
            int _type = UNITCELL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:357:10: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:357:12: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNITCELL"

    // $ANTLR start "PROTEINCONC"
    public final void mPROTEINCONC() throws RecognitionException {
        try {
            int _type = PROTEINCONC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:361:12: ( ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'C' | 'c' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:361:14: ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'C' | 'c' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PROTEINCONC"

    // $ANTLR start "PROTEINCONCENTRATION"
    public final void mPROTEINCONCENTRATION() throws RecognitionException {
        try {
            int _type = PROTEINCONCENTRATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:362:21: ( ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:362:23: ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PROTEINCONCENTRATION"

    // $ANTLR start "NUMMONOMERS"
    public final void mNUMMONOMERS() throws RecognitionException {
        try {
            int _type = NUMMONOMERS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:366:12: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:366:14: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMMONOMERS"

    // $ANTLR start "NUMRESIDUES"
    public final void mNUMRESIDUES() throws RecognitionException {
        try {
            int _type = NUMRESIDUES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:370:13: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'S' | 's' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:370:15: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMRESIDUES"

    // $ANTLR start "NUMRNA"
    public final void mNUMRNA() throws RecognitionException {
        try {
            int _type = NUMRNA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:374:8: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'R' | 'r' ) ( 'N' | 'n' ) ( 'A' | 'a' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:374:10: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'R' | 'r' ) ( 'N' | 'n' ) ( 'A' | 'a' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMRNA"

    // $ANTLR start "NUMDNA"
    public final void mNUMDNA() throws RecognitionException {
        try {
            int _type = NUMDNA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:378:8: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'D' | 'd' ) ( 'N' | 'n' ) ( 'A' | 'a' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:378:10: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'D' | 'd' ) ( 'N' | 'n' ) ( 'A' | 'a' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMDNA"

    // $ANTLR start "PROTEINHEAVYATOMS"
    public final void mPROTEINHEAVYATOMS() throws RecognitionException {
        try {
            int _type = PROTEINHEAVYATOMS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:386:19: ( ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'Y' | 'y' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'S' | 's' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:386:21: ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'Y' | 'y' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PROTEINHEAVYATOMS"

    // $ANTLR start "ELEMENT"
    public final void mELEMENT() throws RecognitionException {
        try {
            int _type = ELEMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:387:9: ( ( 'A' .. 'Z' | 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' )? )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:387:11: ( 'A' .. 'Z' | 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' )?
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:387:32: ( 'A' .. 'Z' | 'a' .. 'z' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0 >= 'A' && LA5_0 <= 'Z')||(LA5_0 >= 'a' && LA5_0 <= 'z')) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:
                    {
                    if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ELEMENT"

    // $ANTLR start "SMALLMOLEATOMS"
    public final void mSMALLMOLEATOMS() throws RecognitionException {
        try {
            int _type = SMALLMOLEATOMS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:395:16: ( ( 'S' | 's' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'S' | 's' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:395:18: ( 'S' | 's' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SMALLMOLEATOMS"

    // $ANTLR start "SOLVENTHEAVYCONC"
    public final void mSOLVENTHEAVYCONC() throws RecognitionException {
        try {
            int _type = SOLVENTHEAVYCONC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:403:18: ( ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'Y' | 'y' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'C' | 'c' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:403:20: ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'Y' | 'y' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'C' | 'c' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SOLVENTHEAVYCONC"

    // $ANTLR start "SOLVENTFRACTION"
    public final void mSOLVENTFRACTION() throws RecognitionException {
        try {
            int _type = SOLVENTFRACTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:407:17: ( ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:407:19: ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SOLVENTFRACTION"

    // $ANTLR start "PDBNAME"
    public final void mPDBNAME() throws RecognitionException {
        try {
            int _type = PDBNAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:411:9: ( ( 'P' | 'p' ) ( 'D' | 'd' ) ( 'B' | 'b' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:411:11: ( 'P' | 'p' ) ( 'D' | 'd' ) ( 'B' | 'b' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PDBNAME"

    // $ANTLR start "CIFNAME"
    public final void mCIFNAME() throws RecognitionException {
        try {
            int _type = CIFNAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:415:9: ( ( 'C' | 'c' ) ( 'I' | 'i' ) ( 'F' | 'f' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:415:11: ( 'C' | 'c' ) ( 'I' | 'i' ) ( 'F' | 'f' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CIFNAME"

    // $ANTLR start "WIREFRAMETYPE"
    public final void mWIREFRAMETYPE() throws RecognitionException {
        try {
            int _type = WIREFRAMETYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:419:15: ( ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'Y' | 'y' |) ( 'P' | 'p' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:419:17: ( 'W' | 'w' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'Y' | 'y' |) ( 'P' | 'p' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:419:107: ( 'Y' | 'y' |)
            int alt6=3;
            switch ( input.LA(1) ) {
            case 'Y':
                {
                alt6=1;
                }
                break;
            case 'y':
                {
                alt6=2;
                }
                break;
            case 'P':
            case 'p':
                {
                alt6=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:419:108: 'Y'
                    {
                    match('Y'); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:419:112: 'y'
                    {
                    match('y'); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:419:116: 
                    {
                    }
                    break;

            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WIREFRAMETYPE"

    // $ANTLR start "MODELFILE"
    public final void mMODELFILE() throws RecognitionException {
        try {
            int _type = MODELFILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:423:11: ( ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:423:13: ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MODELFILE"

    // $ANTLR start "CALCULATEPEESCAPE"
    public final void mCALCULATEPEESCAPE() throws RecognitionException {
        try {
            int _type = CALCULATEPEESCAPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:428:2: ( ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'P' | 'p' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:428:5: ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'P' | 'p' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CALCULATEPEESCAPE"

    // $ANTLR start "CONTAINERMATERIALTYPE"
    public final void mCONTAINERMATERIALTYPE() throws RecognitionException {
        try {
            int _type = CONTAINERMATERIALTYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:432:23: ( ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'T' | 't' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:432:25: ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'T' | 't' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONTAINERMATERIALTYPE"

    // $ANTLR start "MATERIALTYPE"
    public final void mMATERIALTYPE() throws RecognitionException {
        try {
            int _type = MATERIALTYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:433:14: ( ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'T' | 't' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:433:16: ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'T' | 't' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MATERIALTYPE"

    // $ANTLR start "NONE"
    public final void mNONE() throws RecognitionException {
        try {
            int _type = NONE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:439:7: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:439:9: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NONE"

    // $ANTLR start "MIXTURE"
    public final void mMIXTURE() throws RecognitionException {
        try {
            int _type = MIXTURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:440:9: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:440:11: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MIXTURE"

    // $ANTLR start "ELEMENTAL"
    public final void mELEMENTAL() throws RecognitionException {
        try {
            int _type = ELEMENTAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:441:11: ( ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:441:13: ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ELEMENTAL"

    // $ANTLR start "CONTAINERTHICKNESS"
    public final void mCONTAINERTHICKNESS() throws RecognitionException {
        try {
            int _type = CONTAINERTHICKNESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:445:19: ( ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'H' | 'h' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'K' | 'k' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:445:21: ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'H' | 'h' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'K' | 'k' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONTAINERTHICKNESS"

    // $ANTLR start "CONTAINERMATERIALMIXTURE"
    public final void mCONTAINERMATERIALMIXTURE() throws RecognitionException {
        try {
            int _type = CONTAINERMATERIALMIXTURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:449:25: ( ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:449:27: ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONTAINERMATERIALMIXTURE"

    // $ANTLR start "MATERIALMIXTURE"
    public final void mMATERIALMIXTURE() throws RecognitionException {
        try {
            int _type = MATERIALMIXTURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:450:16: ( ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:450:18: ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'T' | 't' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MATERIALMIXTURE"

    // $ANTLR start "CONTAINERMATERIALELEMENTS"
    public final void mCONTAINERMATERIALELEMENTS() throws RecognitionException {
        try {
            int _type = CONTAINERMATERIALELEMENTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:458:26: ( ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'S' | 's' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:458:28: ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONTAINERMATERIALELEMENTS"

    // $ANTLR start "MATERIALELEMENTS"
    public final void mMATERIALELEMENTS() throws RecognitionException {
        try {
            int _type = MATERIALELEMENTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:459:17: ( ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'S' | 's' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:459:19: ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'S' | 's' )
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MATERIALELEMENTS"

    // $ANTLR start "CONTAINERDENSITY"
    public final void mCONTAINERDENSITY() throws RecognitionException {
        try {
            int _type = CONTAINERDENSITY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:463:17: ( ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'Y' | 'y' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:463:19: ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'Y' | 'y' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONTAINERDENSITY"

    // $ANTLR start "SEQUENCEFILE"
    public final void mSEQUENCEFILE() throws RecognitionException {
        try {
            int _type = SEQUENCEFILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:467:14: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:467:17: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Q'||input.LA(1)=='q' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEQUENCEFILE"

    // $ANTLR start "SEQFILE"
    public final void mSEQFILE() throws RecognitionException {
        try {
            int _type = SEQFILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:468:9: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:468:11: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'Q' | 'q' ) ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Q'||input.LA(1)=='q' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEQFILE"

    // $ANTLR start "CALCULATEFLESCAPE"
    public final void mCALCULATEFLESCAPE() throws RecognitionException {
        try {
            int _type = CALCULATEFLESCAPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:473:2: ( ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'P' | 'p' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:473:5: ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'P' | 'p' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CALCULATEFLESCAPE"

    // $ANTLR start "FLRESOLUTION"
    public final void mFLRESOLUTION() throws RecognitionException {
        try {
            int _type = FLRESOLUTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:477:14: ( ( 'F' | 'f' ) ( 'L' | 'l' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:477:16: ( 'F' | 'f' ) ( 'L' | 'l' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLRESOLUTION"

    // $ANTLR start "PERESOLUTION"
    public final void mPERESOLUTION() throws RecognitionException {
        try {
            int _type = PERESOLUTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:481:14: ( ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:481:16: ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PERESOLUTION"

    // $ANTLR start "CRYOSOLUTION"
    public final void mCRYOSOLUTION() throws RecognitionException {
        try {
            int _type = CRYOSOLUTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:489:14: ( ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'Y' | 'y' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:489:16: ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'Y' | 'y' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CRYOSOLUTION"

    // $ANTLR start "OILBASED"
    public final void mOILBASED() throws RecognitionException {
        try {
            int _type = OILBASED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:493:10: ( ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'B' | 'b' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'D' | 'd' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:493:12: ( 'O' | 'o' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'B' | 'b' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'D' | 'd' )
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OILBASED"

    // $ANTLR start "BEAM"
    public final void mBEAM() throws RecognitionException {
        try {
            int _type = BEAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:509:6: ( ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'M' | 'm' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:509:8: ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'M' | 'm' )
            {
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BEAM"

    // $ANTLR start "FLUX"
    public final void mFLUX() throws RecognitionException {
        try {
            int _type = FLUX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:527:6: ( ( 'F' | 'f' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'X' | 'x' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:527:8: ( 'F' | 'f' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'X' | 'x' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLUX"

    // $ANTLR start "FWHM"
    public final void mFWHM() throws RecognitionException {
        try {
            int _type = FWHM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:531:6: ( ( 'F' | 'f' ) ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'M' | 'm' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:531:8: ( 'F' | 'f' ) ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'M' | 'm' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FWHM"

    // $ANTLR start "ENERGY"
    public final void mENERGY() throws RecognitionException {
        try {
            int _type = ENERGY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:537:8: ( ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'G' | 'g' ) ( 'Y' | 'y' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:537:10: ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'G' | 'g' ) ( 'Y' | 'y' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ENERGY"

    // $ANTLR start "KEV"
    public final void mKEV() throws RecognitionException {
        try {
            int _type = KEV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:538:5: ( ( 'K' | 'k' ) ( 'E' | 'e' ) ( 'V' | 'v' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:538:7: ( 'K' | 'k' ) ( 'E' | 'e' ) ( 'V' | 'v' )
            {
            if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEV"

    // $ANTLR start "FILE"
    public final void mFILE() throws RecognitionException {
        try {
            int _type = FILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:543:6: ( ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:543:8: ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FILE"

    // $ANTLR start "PIXELSIZE"
    public final void mPIXELSIZE() throws RecognitionException {
        try {
            int _type = PIXELSIZE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:551:2: ( ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'Z' | 'z' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:551:5: ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'Z' | 'z' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Z'||input.LA(1)=='z' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PIXELSIZE"

    // $ANTLR start "COLLIMATION"
    public final void mCOLLIMATION() throws RecognitionException {
        try {
            int _type = COLLIMATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:566:13: ( ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:566:15: ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLLIMATION"

    // $ANTLR start "RECTANGULAR"
    public final void mRECTANGULAR() throws RecognitionException {
        try {
            int _type = RECTANGULAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:567:13: ( ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:567:15: ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' )
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RECTANGULAR"

    // $ANTLR start "CIRCULAR"
    public final void mCIRCULAR() throws RecognitionException {
        try {
            int _type = CIRCULAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:568:11: ( ( 'C' | 'c' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:568:13: ( 'C' | 'c' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CIRCULAR"

    // $ANTLR start "HORIZONTAL"
    public final void mHORIZONTAL() throws RecognitionException {
        try {
            int _type = HORIZONTAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:569:13: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'Z' | 'z' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:569:15: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'Z' | 'z' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='Z'||input.LA(1)=='z' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HORIZONTAL"

    // $ANTLR start "VERTICAL"
    public final void mVERTICAL() throws RecognitionException {
        try {
            int _type = VERTICAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:570:11: ( ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:570:13: ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' )
            {
            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VERTICAL"

    // $ANTLR start "WEDGE"
    public final void mWEDGE() throws RecognitionException {
        try {
            int _type = WEDGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:596:7: ( ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'G' | 'g' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:596:9: ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'G' | 'g' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WEDGE"

    // $ANTLR start "EXPOSURETIME"
    public final void mEXPOSURETIME() throws RecognitionException {
        try {
            int _type = EXPOSURETIME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:612:14: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:612:16: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPOSURETIME"

    // $ANTLR start "ANGULARRESOLUTION"
    public final void mANGULARRESOLUTION() throws RecognitionException {
        try {
            int _type = ANGULARRESOLUTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:616:19: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:616:21: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ANGULARRESOLUTION"

    // $ANTLR start "STARTOFFSET"
    public final void mSTARTOFFSET() throws RecognitionException {
        try {
            int _type = STARTOFFSET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:622:13: ( ( 'S' | 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:622:15: ( 'S' | 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STARTOFFSET"

    // $ANTLR start "TRANSLATEPERDEGREE"
    public final void mTRANSLATEPERDEGREE() throws RecognitionException {
        try {
            int _type = TRANSLATEPERDEGREE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:628:20: ( ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'E' | 'e' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:628:22: ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TRANSLATEPERDEGREE"

    // $ANTLR start "ROTAXBEAMOFFSET"
    public final void mROTAXBEAMOFFSET() throws RecognitionException {
        try {
            int _type = ROTAXBEAMOFFSET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:632:17: ( ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'X' | 'x' ) ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:632:19: ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'X' | 'x' ) ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' )
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ROTAXBEAMOFFSET"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:637:5: ( ( '+' | '-' )? ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ ) ( EXPONENT )? )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:637:7: ( '+' | '-' )? ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ ) ( EXPONENT )?
            {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:637:7: ( '+' | '-' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='+'||LA7_0=='-') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:638:5: ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ )
            int alt12=3;
            alt12 = dfa12.predict(input);
            switch (alt12) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:638:7: ( '0' .. '9' )+
                    {
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:638:7: ( '0' .. '9' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0 >= '0' && LA8_0 <= '9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:639:7: ( '0' .. '9' )+ '.' ( '0' .. '9' )*
                    {
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:639:7: ( '0' .. '9' )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0 >= '0' && LA9_0 <= '9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt9 >= 1 ) break loop9;
                                EarlyExitException eee =
                                    new EarlyExitException(9, input);
                                throw eee;
                        }
                        cnt9++;
                    } while (true);


                    match('.'); 

                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:639:23: ( '0' .. '9' )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0 >= '0' && LA10_0 <= '9')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:640:19: '.' ( '0' .. '9' )+
                    {
                    match('.'); 

                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:640:23: ( '0' .. '9' )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0 >= '0' && LA11_0 <= '9')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt11 >= 1 ) break loop11;
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
                    } while (true);


                    }
                    break;

            }


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:641:7: ( EXPONENT )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='E'||LA13_0=='e') ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:641:7: EXPONENT
                    {
                    mEXPONENT(); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:645:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:645:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:645:22: ( '+' | '-' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='+'||LA14_0=='-') ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:645:33: ( '0' .. '9' )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0 >= '0' && LA15_0 <= '9')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:647:5: ( ( '#' | '//' | '!' ) (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:647:9: ( '#' | '//' | '!' ) (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:647:9: ( '#' | '//' | '!' )
            int alt16=3;
            switch ( input.LA(1) ) {
            case '#':
                {
                alt16=1;
                }
                break;
            case '/':
                {
                alt16=2;
                }
                break;
            case '!':
                {
                alt16=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }

            switch (alt16) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:647:10: '#'
                    {
                    match('#'); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:647:16: '//'
                    {
                    match("//"); 



                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:647:23: '!'
                    {
                    match('!'); 

                    }
                    break;

            }


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:647:28: (~ ( '\\n' | '\\r' ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0 >= '\u0000' && LA17_0 <= '\t')||(LA17_0 >= '\u000B' && LA17_0 <= '\f')||(LA17_0 >= '\u000E' && LA17_0 <= '\uFFFF')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:647:42: ( '\\r' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='\r') ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:647:42: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:650:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:650:9: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:665:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '$' | '-' | '_' | ':' | '\\\\' )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:665:4: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '$' | '-' | '_' | ':' | '\\\\' )+
            {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:665:4: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '$' | '-' | '_' | ':' | '\\\\' )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0=='$'||(LA19_0 >= '-' && LA19_0 <= '.')||(LA19_0 >= '0' && LA19_0 <= ':')||(LA19_0 >= 'A' && LA19_0 <= 'Z')||LA19_0=='\\'||LA19_0=='_'||(LA19_0 >= 'a' && LA19_0 <= 'z')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= ':')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='\\'||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING"

    public void mTokens() throws RecognitionException {
        // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:8: ( CRYSTAL | TYPE | DIFFRACTIONDECAYMODEL | DDM | SIMPLE | LINEAR | LEAL | DECAYPARAM | ABSCOEFCALC | DUMMY | DEFAULT | AVERAGE | RDFORTAN | RDJAVA | PDB | SAXS | SEQUENCE | SAXSSEQ | SMALLMOLE | CIF | DIMENSION | ANGLEP | ANGLEL | PIXELSPERMICRON | UNITCELL | PROTEINCONC | PROTEINCONCENTRATION | NUMMONOMERS | NUMRESIDUES | NUMRNA | NUMDNA | PROTEINHEAVYATOMS | ELEMENT | SMALLMOLEATOMS | SOLVENTHEAVYCONC | SOLVENTFRACTION | PDBNAME | CIFNAME | WIREFRAMETYPE | MODELFILE | CALCULATEPEESCAPE | CONTAINERMATERIALTYPE | MATERIALTYPE | NONE | MIXTURE | ELEMENTAL | CONTAINERTHICKNESS | CONTAINERMATERIALMIXTURE | MATERIALMIXTURE | CONTAINERMATERIALELEMENTS | MATERIALELEMENTS | CONTAINERDENSITY | SEQUENCEFILE | SEQFILE | CALCULATEFLESCAPE | FLRESOLUTION | PERESOLUTION | CRYOSOLUTION | OILBASED | BEAM | FLUX | FWHM | ENERGY | KEV | FILE | PIXELSIZE | COLLIMATION | RECTANGULAR | CIRCULAR | HORIZONTAL | VERTICAL | WEDGE | EXPOSURETIME | ANGULARRESOLUTION | STARTOFFSET | TRANSLATEPERDEGREE | ROTAXBEAMOFFSET | FLOAT | COMMENT | WS | STRING )
        int alt20=81;
        alt20 = dfa20.predict(input);
        switch (alt20) {
            case 1 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:10: CRYSTAL
                {
                mCRYSTAL(); 


                }
                break;
            case 2 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:18: TYPE
                {
                mTYPE(); 


                }
                break;
            case 3 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:23: DIFFRACTIONDECAYMODEL
                {
                mDIFFRACTIONDECAYMODEL(); 


                }
                break;
            case 4 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:45: DDM
                {
                mDDM(); 


                }
                break;
            case 5 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:49: SIMPLE
                {
                mSIMPLE(); 


                }
                break;
            case 6 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:56: LINEAR
                {
                mLINEAR(); 


                }
                break;
            case 7 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:63: LEAL
                {
                mLEAL(); 


                }
                break;
            case 8 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:68: DECAYPARAM
                {
                mDECAYPARAM(); 


                }
                break;
            case 9 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:79: ABSCOEFCALC
                {
                mABSCOEFCALC(); 


                }
                break;
            case 10 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:91: DUMMY
                {
                mDUMMY(); 


                }
                break;
            case 11 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:97: DEFAULT
                {
                mDEFAULT(); 


                }
                break;
            case 12 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:105: AVERAGE
                {
                mAVERAGE(); 


                }
                break;
            case 13 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:113: RDFORTAN
                {
                mRDFORTAN(); 


                }
                break;
            case 14 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:122: RDJAVA
                {
                mRDJAVA(); 


                }
                break;
            case 15 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:129: PDB
                {
                mPDB(); 


                }
                break;
            case 16 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:133: SAXS
                {
                mSAXS(); 


                }
                break;
            case 17 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:138: SEQUENCE
                {
                mSEQUENCE(); 


                }
                break;
            case 18 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:147: SAXSSEQ
                {
                mSAXSSEQ(); 


                }
                break;
            case 19 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:155: SMALLMOLE
                {
                mSMALLMOLE(); 


                }
                break;
            case 20 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:165: CIF
                {
                mCIF(); 


                }
                break;
            case 21 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:169: DIMENSION
                {
                mDIMENSION(); 


                }
                break;
            case 22 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:179: ANGLEP
                {
                mANGLEP(); 


                }
                break;
            case 23 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:186: ANGLEL
                {
                mANGLEL(); 


                }
                break;
            case 24 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:193: PIXELSPERMICRON
                {
                mPIXELSPERMICRON(); 


                }
                break;
            case 25 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:209: UNITCELL
                {
                mUNITCELL(); 


                }
                break;
            case 26 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:218: PROTEINCONC
                {
                mPROTEINCONC(); 


                }
                break;
            case 27 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:230: PROTEINCONCENTRATION
                {
                mPROTEINCONCENTRATION(); 


                }
                break;
            case 28 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:251: NUMMONOMERS
                {
                mNUMMONOMERS(); 


                }
                break;
            case 29 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:263: NUMRESIDUES
                {
                mNUMRESIDUES(); 


                }
                break;
            case 30 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:275: NUMRNA
                {
                mNUMRNA(); 


                }
                break;
            case 31 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:282: NUMDNA
                {
                mNUMDNA(); 


                }
                break;
            case 32 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:289: PROTEINHEAVYATOMS
                {
                mPROTEINHEAVYATOMS(); 


                }
                break;
            case 33 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:307: ELEMENT
                {
                mELEMENT(); 


                }
                break;
            case 34 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:315: SMALLMOLEATOMS
                {
                mSMALLMOLEATOMS(); 


                }
                break;
            case 35 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:330: SOLVENTHEAVYCONC
                {
                mSOLVENTHEAVYCONC(); 


                }
                break;
            case 36 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:347: SOLVENTFRACTION
                {
                mSOLVENTFRACTION(); 


                }
                break;
            case 37 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:363: PDBNAME
                {
                mPDBNAME(); 


                }
                break;
            case 38 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:371: CIFNAME
                {
                mCIFNAME(); 


                }
                break;
            case 39 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:379: WIREFRAMETYPE
                {
                mWIREFRAMETYPE(); 


                }
                break;
            case 40 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:393: MODELFILE
                {
                mMODELFILE(); 


                }
                break;
            case 41 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:403: CALCULATEPEESCAPE
                {
                mCALCULATEPEESCAPE(); 


                }
                break;
            case 42 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:421: CONTAINERMATERIALTYPE
                {
                mCONTAINERMATERIALTYPE(); 


                }
                break;
            case 43 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:443: MATERIALTYPE
                {
                mMATERIALTYPE(); 


                }
                break;
            case 44 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:456: NONE
                {
                mNONE(); 


                }
                break;
            case 45 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:461: MIXTURE
                {
                mMIXTURE(); 


                }
                break;
            case 46 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:469: ELEMENTAL
                {
                mELEMENTAL(); 


                }
                break;
            case 47 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:479: CONTAINERTHICKNESS
                {
                mCONTAINERTHICKNESS(); 


                }
                break;
            case 48 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:498: CONTAINERMATERIALMIXTURE
                {
                mCONTAINERMATERIALMIXTURE(); 


                }
                break;
            case 49 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:523: MATERIALMIXTURE
                {
                mMATERIALMIXTURE(); 


                }
                break;
            case 50 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:539: CONTAINERMATERIALELEMENTS
                {
                mCONTAINERMATERIALELEMENTS(); 


                }
                break;
            case 51 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:565: MATERIALELEMENTS
                {
                mMATERIALELEMENTS(); 


                }
                break;
            case 52 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:582: CONTAINERDENSITY
                {
                mCONTAINERDENSITY(); 


                }
                break;
            case 53 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:599: SEQUENCEFILE
                {
                mSEQUENCEFILE(); 


                }
                break;
            case 54 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:612: SEQFILE
                {
                mSEQFILE(); 


                }
                break;
            case 55 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:620: CALCULATEFLESCAPE
                {
                mCALCULATEFLESCAPE(); 


                }
                break;
            case 56 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:638: FLRESOLUTION
                {
                mFLRESOLUTION(); 


                }
                break;
            case 57 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:651: PERESOLUTION
                {
                mPERESOLUTION(); 


                }
                break;
            case 58 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:664: CRYOSOLUTION
                {
                mCRYOSOLUTION(); 


                }
                break;
            case 59 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:677: OILBASED
                {
                mOILBASED(); 


                }
                break;
            case 60 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:686: BEAM
                {
                mBEAM(); 


                }
                break;
            case 61 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:691: FLUX
                {
                mFLUX(); 


                }
                break;
            case 62 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:696: FWHM
                {
                mFWHM(); 


                }
                break;
            case 63 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:701: ENERGY
                {
                mENERGY(); 


                }
                break;
            case 64 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:708: KEV
                {
                mKEV(); 


                }
                break;
            case 65 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:712: FILE
                {
                mFILE(); 


                }
                break;
            case 66 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:717: PIXELSIZE
                {
                mPIXELSIZE(); 


                }
                break;
            case 67 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:727: COLLIMATION
                {
                mCOLLIMATION(); 


                }
                break;
            case 68 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:739: RECTANGULAR
                {
                mRECTANGULAR(); 


                }
                break;
            case 69 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:751: CIRCULAR
                {
                mCIRCULAR(); 


                }
                break;
            case 70 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:760: HORIZONTAL
                {
                mHORIZONTAL(); 


                }
                break;
            case 71 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:771: VERTICAL
                {
                mVERTICAL(); 


                }
                break;
            case 72 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:780: WEDGE
                {
                mWEDGE(); 


                }
                break;
            case 73 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:786: EXPOSURETIME
                {
                mEXPOSURETIME(); 


                }
                break;
            case 74 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:799: ANGULARRESOLUTION
                {
                mANGULARRESOLUTION(); 


                }
                break;
            case 75 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:817: STARTOFFSET
                {
                mSTARTOFFSET(); 


                }
                break;
            case 76 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:829: TRANSLATEPERDEGREE
                {
                mTRANSLATEPERDEGREE(); 


                }
                break;
            case 77 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:848: ROTAXBEAMOFFSET
                {
                mROTAXBEAMOFFSET(); 


                }
                break;
            case 78 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:864: FLOAT
                {
                mFLOAT(); 


                }
                break;
            case 79 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:870: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 80 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:878: WS
                {
                mWS(); 


                }
                break;
            case 81 :
                // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:1:881: STRING
                {
                mSTRING(); 


                }
                break;

        }

    }


    protected DFA12 dfa12 = new DFA12(this);
    protected DFA20 dfa20 = new DFA20(this);
    static final String DFA12_eotS =
        "\1\uffff\1\3\3\uffff";
    static final String DFA12_eofS =
        "\5\uffff";
    static final String DFA12_minS =
        "\2\56\3\uffff";
    static final String DFA12_maxS =
        "\2\71\3\uffff";
    static final String DFA12_acceptS =
        "\2\uffff\1\3\1\1\1\2";
    static final String DFA12_specialS =
        "\5\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\4\1\uffff\12\1",
            "",
            "",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "638:5: ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ )";
        }
    }
    static final String DFA20_eotS =
        "\1\uffff\24\41\1\33\1\32\1\33\4\uffff\5\41\1\uffff\53\41\1\33\2"+
        "\32\4\33\1\u008e\6\33\1\u0095\16\33\1\u00a7\1\u00a9\2\33\1\u00ae"+
        "\4\33\1\u00b3\17\33\1\u00c5\3\33\2\32\5\33\1\uffff\2\33\1\u00cf"+
        "\3\33\1\uffff\4\33\1\u00d8\6\33\1\u00df\4\33\1\u00a7\1\uffff\1\u00a9"+
        "\1\uffff\4\33\1\uffff\4\33\1\uffff\5\33\1\u00f2\6\33\1\u00f9\1\u00fa"+
        "\1\u00fb\1\33\1\u00fd\1\uffff\11\33\1\uffff\5\33\1\u010c\2\33\1"+
        "\uffff\6\33\1\uffff\6\33\1\u011b\13\33\1\uffff\1\33\1\u0128\4\33"+
        "\3\uffff\1\33\1\uffff\16\33\1\uffff\1\u013c\6\33\1\u0143\1\33\1"+
        "\u0145\1\u0146\3\33\1\uffff\2\33\1\u014c\6\33\1\u0154\1\u0155\1"+
        "\33\1\uffff\7\33\1\u015e\12\33\1\u0169\1\uffff\1\u016a\1\33\1\u016c"+
        "\3\33\1\uffff\1\u0171\2\uffff\5\33\1\uffff\7\33\2\uffff\3\33\1\u0182"+
        "\4\33\1\uffff\1\33\1\u0188\2\33\1\u018b\5\33\2\uffff\1\u0192\1\uffff"+
        "\4\33\1\uffff\12\33\1\u01a1\5\33\1\uffff\1\33\1\u01aa\1\33\1\u01ac"+
        "\1\33\1\uffff\2\33\1\uffff\3\33\1\u01b7\2\33\1\uffff\1\u01bb\7\33"+
        "\1\u01c3\1\33\1\u01c5\3\33\1\uffff\3\33\1\u01cc\4\33\1\uffff\1\33"+
        "\1\uffff\11\33\1\u01b7\1\uffff\1\u01db\2\33\1\uffff\7\33\1\uffff"+
        "\1\33\1\uffff\6\33\1\uffff\4\33\1\u01f2\4\33\1\u01f7\4\33\1\uffff"+
        "\4\33\1\u0200\1\33\1\u0202\3\33\1\u0207\2\33\1\u020a\1\u020b\7\33"+
        "\1\uffff\1\u0211\3\33\1\uffff\4\33\1\u0219\3\33\1\uffff\1\33\1\uffff"+
        "\1\33\1\u021f\2\33\1\uffff\1\33\1\u0223\2\uffff\1\u0224\1\u0225"+
        "\2\33\1\u0228\1\uffff\7\33\1\uffff\5\33\1\uffff\3\33\3\uffff\2\33"+
        "\1\uffff\7\33\1\u0241\20\33\1\uffff\1\33\1\u0253\1\33\1\u0255\1"+
        "\u0256\2\33\1\u0259\3\33\1\u025d\4\33\1\u0262\1\uffff\1\33\2\uffff"+
        "\2\33\1\uffff\1\u0266\2\33\1\uffff\1\u026b\1\u026c\2\33\1\uffff"+
        "\1\u026f\1\33\1\u0271\1\uffff\3\33\1\u0275\2\uffff\1\u0276\1\33"+
        "\1\uffff\1\33\1\uffff\3\33\2\uffff\6\33\1\u0282\1\u0283\2\33\1\u0286"+
        "\2\uffff\2\33\1\uffff\2\33\1\u028b\1\33\1\uffff\1\u028d\1\uffff";
    static final String DFA20_eofS =
        "\u028e\uffff";
    static final String DFA20_minS =
        "\1\11\24\44\1\56\1\44\1\60\4\uffff\5\44\1\uffff\53\44\1\53\2\44"+
        "\1\117\1\106\1\124\1\114\1\44\2\103\1\105\1\116\1\106\1\105\1\44"+
        "\2\101\1\115\1\120\1\123\1\106\1\114\1\126\1\122\1\105\1\114\1\103"+
        "\1\122\1\114\2\44\1\124\1\101\1\44\1\115\1\122\1\105\1\124\1\44"+
        "\1\105\1\124\1\104\2\105\1\107\2\105\1\124\1\105\1\130\1\115\1\105"+
        "\1\102\1\115\1\44\1\111\1\124\1\60\2\44\1\124\1\123\1\103\1\101"+
        "\1\111\1\uffff\2\125\1\44\1\123\1\122\1\116\1\uffff\1\131\1\125"+
        "\1\131\1\114\1\44\1\105\1\111\1\114\1\105\1\124\1\101\1\44\1\117"+
        "\1\101\1\105\1\114\1\44\1\uffff\1\44\1\uffff\1\101\1\130\1\115\1"+
        "\123\1\uffff\1\105\1\107\1\114\1\105\1\uffff\1\123\1\103\1\117\1"+
        "\105\1\116\1\44\1\106\1\105\1\114\1\122\1\125\1\123\3\44\1\101\1"+
        "\44\1\uffff\1\132\1\111\1\101\1\117\1\101\1\111\1\115\2\114\1\uffff"+
        "\1\114\1\101\1\123\1\120\1\114\1\44\2\105\1\uffff\1\116\1\114\1"+
        "\115\1\116\1\117\1\122\1\uffff\1\105\1\107\1\114\1\101\1\116\1\102"+
        "\1\44\1\125\1\116\1\131\1\123\1\111\1\117\1\105\1\116\1\123\2\101"+
        "\1\uffff\1\122\1\44\1\106\1\111\1\122\1\117\3\uffff\1\123\1\uffff"+
        "\1\117\1\103\3\114\1\116\4\101\1\103\1\111\1\101\1\124\1\uffff\1"+
        "\44\1\121\1\103\1\105\1\117\1\124\1\106\1\44\1\105\2\44\1\122\1"+
        "\107\1\105\1\uffff\1\122\1\124\1\44\1\111\1\116\2\114\1\117\1\111"+
        "\2\44\1\101\1\uffff\1\111\1\101\1\105\1\114\1\105\1\116\1\101\1"+
        "\44\1\125\1\103\1\105\1\124\1\122\3\124\1\117\1\122\1\44\1\uffff"+
        "\1\44\1\105\1\44\1\114\2\106\1\uffff\1\44\2\uffff\1\122\1\125\1"+
        "\101\1\105\1\101\1\uffff\1\105\1\132\1\103\1\125\1\114\1\115\1\104"+
        "\2\uffff\1\115\2\114\1\44\1\125\1\104\1\124\1\114\1\uffff\1\124"+
        "\1\44\1\122\1\111\1\44\2\105\1\111\1\116\1\101\2\uffff\1\44\1\uffff"+
        "\2\105\1\122\1\123\1\uffff\1\105\1\114\1\115\1\124\1\114\1\122\1"+
        "\105\1\117\1\105\1\124\1\44\1\105\1\125\3\105\1\uffff\1\124\1\44"+
        "\1\101\1\44\1\111\1\uffff\1\104\1\117\1\uffff\1\106\1\120\1\117"+
        "\1\44\1\115\1\111\1\uffff\1\44\2\101\1\105\1\123\1\101\1\117\1\111"+
        "\1\44\1\115\1\44\1\116\1\101\1\111\1\uffff\1\122\1\105\1\124\1\44"+
        "\1\131\1\111\1\114\1\111\1\uffff\1\114\1\uffff\1\117\1\101\1\110"+
        "\1\105\1\116\1\105\1\114\1\105\1\116\1\44\1\uffff\1\44\1\114\1\124"+
        "\1\uffff\1\126\1\103\1\124\1\117\1\122\1\106\1\115\1\uffff\1\111"+
        "\1\uffff\1\103\1\126\1\117\2\123\1\120\1\uffff\1\120\1\130\1\105"+
        "\1\117\1\44\1\116\1\124\1\111\1\116\1\44\2\105\1\122\1\104\1\uffff"+
        "\1\105\1\117\1\131\1\124\1\44\1\114\1\44\1\106\1\105\1\103\1\44"+
        "\1\131\1\116\2\44\2\120\2\105\1\124\1\115\1\116\1\uffff\1\44\1\105"+
        "\1\103\1\123\1\uffff\2\123\1\104\1\105\1\44\1\115\1\103\1\111\1"+
        "\uffff\1\125\1\uffff\1\123\1\44\1\122\1\116\1\uffff\1\101\1\44\2"+
        "\uffff\2\44\1\125\1\105\1\44\1\uffff\1\122\1\113\1\111\2\103\1\105"+
        "\1\103\1\uffff\1\123\2\117\1\124\1\105\1\uffff\1\117\2\124\3\uffff"+
        "\1\122\1\116\1\uffff\1\111\1\116\1\124\2\101\1\107\1\101\1\44\2"+
        "\116\1\111\1\124\1\116\1\122\1\117\1\105\1\124\1\101\1\105\1\131"+
        "\2\120\1\122\1\131\1\uffff\1\103\1\44\1\117\2\44\1\101\1\115\1\44"+
        "\1\123\1\114\1\123\1\44\3\105\1\115\1\44\1\uffff\1\116\2\uffff\1"+
        "\124\1\123\1\uffff\1\44\1\105\1\123\1\uffff\2\44\1\105\1\117\1\uffff"+
        "\1\44\1\111\1\44\1\uffff\1\131\1\111\1\114\1\44\2\uffff\1\44\1\104"+
        "\1\uffff\1\117\1\uffff\1\120\1\130\1\105\2\uffff\1\105\1\116\1\105"+
        "\1\124\1\115\1\114\2\44\1\125\1\105\1\44\2\uffff\1\122\1\116\1\uffff"+
        "\1\105\1\124\1\44\1\123\1\uffff\1\44\1\uffff";
    static final String DFA20_maxS =
        "\25\172\1\71\1\172\1\71\4\uffff\5\172\1\uffff\53\172\1\71\2\172"+
        "\1\163\1\146\1\164\1\154\1\172\2\143\1\145\1\156\1\146\1\145\1\172"+
        "\2\141\1\155\1\160\1\163\1\165\1\154\1\166\1\162\1\145\1\154\1\143"+
        "\1\162\1\165\2\172\1\164\1\141\1\172\1\155\1\162\1\145\1\164\1\172"+
        "\1\145\1\164\1\162\2\145\1\147\2\145\1\164\1\145\1\170\1\155\1\145"+
        "\1\142\1\155\1\172\1\151\1\164\1\71\2\172\1\164\1\163\1\143\1\141"+
        "\1\151\1\uffff\2\165\1\172\1\163\1\162\1\156\1\uffff\1\171\1\165"+
        "\1\171\1\154\1\172\1\145\1\151\1\154\1\145\1\164\1\141\1\172\1\157"+
        "\1\141\1\145\1\154\1\172\1\uffff\1\172\1\uffff\1\141\1\170\1\155"+
        "\1\163\1\uffff\1\145\1\147\1\154\1\145\1\uffff\1\163\1\143\1\157"+
        "\2\156\1\172\1\146\1\145\1\154\1\162\1\165\1\163\3\172\1\141\1\172"+
        "\1\uffff\1\172\1\151\1\141\1\157\1\141\1\151\1\155\2\154\1\uffff"+
        "\1\154\1\141\1\163\1\160\1\154\1\172\2\145\1\uffff\1\156\1\154\1"+
        "\155\1\156\1\157\1\162\1\uffff\1\145\1\147\1\160\1\141\1\156\1\142"+
        "\1\172\1\165\1\156\1\171\1\163\1\151\1\157\1\145\1\156\1\163\2\141"+
        "\1\uffff\1\162\1\172\1\146\1\151\1\162\1\157\3\uffff\1\163\1\uffff"+
        "\1\157\1\143\3\154\1\156\4\141\1\143\1\151\1\141\1\164\1\uffff\1"+
        "\172\1\161\1\143\1\145\1\157\1\164\1\146\1\172\1\145\2\172\1\162"+
        "\1\147\1\145\1\uffff\1\162\1\164\1\172\1\160\1\156\2\154\1\157\1"+
        "\151\2\172\1\141\1\uffff\1\151\1\141\1\145\1\154\1\145\1\156\1\141"+
        "\1\172\1\165\1\143\1\145\1\164\1\162\3\164\1\157\1\162\1\172\1\uffff"+
        "\1\172\1\145\1\172\1\154\1\150\1\146\1\uffff\1\172\2\uffff\1\162"+
        "\1\165\1\141\1\145\1\141\1\uffff\1\145\1\172\1\150\1\165\1\154\1"+
        "\155\1\144\2\uffff\1\155\2\154\1\172\1\165\1\144\1\164\1\154\1\uffff"+
        "\1\164\1\172\1\162\1\151\1\172\2\145\1\151\1\156\1\141\2\uffff\1"+
        "\172\1\uffff\2\145\1\162\1\163\1\uffff\1\145\1\154\1\155\1\164\1"+
        "\154\1\162\1\145\1\157\1\145\1\164\1\172\1\145\1\165\2\145\1\164"+
        "\1\uffff\1\164\1\172\1\141\1\172\1\151\1\uffff\1\164\1\157\1\uffff"+
        "\2\160\1\157\1\172\1\155\1\151\1\uffff\1\172\2\141\1\145\1\163\1"+
        "\141\1\157\1\151\1\172\1\155\1\172\1\156\1\141\1\151\1\uffff\1\162"+
        "\1\145\1\164\1\172\1\171\1\151\1\154\1\151\1\uffff\1\154\1\uffff"+
        "\1\157\1\141\1\150\1\145\1\156\1\145\1\154\1\145\1\156\1\172\1\uffff"+
        "\1\172\1\154\1\164\1\uffff\1\166\1\143\1\164\1\157\1\162\1\146\1"+
        "\155\1\uffff\1\151\1\uffff\1\143\1\166\1\157\2\163\1\171\1\uffff"+
        "\1\160\1\170\1\145\1\157\1\172\1\156\1\164\1\151\1\156\1\172\2\145"+
        "\1\162\1\144\1\uffff\1\145\1\157\1\171\1\164\1\172\1\154\1\172\1"+
        "\146\1\145\1\143\1\172\1\171\1\156\2\172\2\160\2\145\1\164\1\155"+
        "\1\156\1\uffff\1\172\1\145\1\143\1\163\1\uffff\2\163\1\144\1\145"+
        "\1\172\1\155\1\143\1\151\1\uffff\1\165\1\uffff\1\163\1\172\1\162"+
        "\1\156\1\uffff\1\141\1\172\2\uffff\2\172\1\165\1\145\1\172\1\uffff"+
        "\1\162\1\153\1\151\2\143\1\145\1\143\1\uffff\1\163\2\157\1\164\1"+
        "\145\1\uffff\1\157\2\164\3\uffff\1\162\1\156\1\uffff\1\151\1\156"+
        "\1\164\2\141\1\147\1\141\1\172\2\156\1\151\1\164\1\156\1\162\1\157"+
        "\1\145\1\164\1\141\1\145\1\171\2\160\1\162\1\171\1\uffff\1\143\1"+
        "\172\1\157\2\172\1\141\1\155\1\172\1\163\1\154\1\163\1\172\3\145"+
        "\1\155\1\172\1\uffff\1\156\2\uffff\1\164\1\163\1\uffff\1\172\1\164"+
        "\1\163\1\uffff\2\172\1\145\1\157\1\uffff\1\172\1\151\1\172\1\uffff"+
        "\1\171\1\151\1\154\1\172\2\uffff\1\172\1\144\1\uffff\1\157\1\uffff"+
        "\1\160\1\170\1\145\2\uffff\1\145\1\156\1\145\1\164\1\155\1\154\2"+
        "\172\1\165\1\145\1\172\2\uffff\1\162\1\156\1\uffff\1\145\1\164\1"+
        "\172\1\163\1\uffff\1\172\1\uffff";
    static final String DFA20_acceptS =
        "\30\uffff\1\117\1\120\1\116\1\121\5\uffff\1\41\154\uffff\1\46\6"+
        "\uffff\1\4\21\uffff\1\15\1\uffff\1\16\4\uffff\1\17\4\uffff\1\45"+
        "\21\uffff\1\100\11\uffff\1\2\10\uffff\1\20\6\uffff\1\7\22\uffff"+
        "\1\54\6\uffff\1\75\1\76\1\101\1\uffff\1\74\16\uffff\1\12\16\uffff"+
        "\1\24\14\uffff\1\110\23\uffff\1\5\6\uffff\1\6\1\uffff\1\26\1\27"+
        "\5\uffff\1\77\7\uffff\1\36\1\37\10\uffff\1\1\12\uffff\1\13\1\22"+
        "\1\uffff\1\66\4\uffff\1\14\20\uffff\1\55\5\uffff\1\11\2\uffff\1"+
        "\105\6\uffff\1\21\16\uffff\1\31\10\uffff\1\73\1\uffff\1\107\12\uffff"+
        "\1\25\3\uffff\1\23\7\uffff\1\56\1\uffff\1\102\6\uffff\1\50\16\uffff"+
        "\1\10\26\uffff\1\106\4\uffff\1\103\10\uffff\1\113\1\uffff\1\104"+
        "\4\uffff\1\32\2\uffff\1\34\1\35\5\uffff\1\72\7\uffff\1\65\5\uffff"+
        "\1\111\3\uffff\1\71\1\47\1\53\2\uffff\1\70\30\uffff\1\42\21\uffff"+
        "\1\44\1\uffff\1\115\1\30\2\uffff\1\61\3\uffff\1\64\4\uffff\1\43"+
        "\3\uffff\1\63\4\uffff\1\51\1\67\2\uffff\1\112\1\uffff\1\40\3\uffff"+
        "\1\57\1\114\13\uffff\1\33\1\52\2\uffff\1\3\4\uffff\1\60\1\uffff"+
        "\1\62";
    static final String DFA20_specialS =
        "\u028e\uffff}>";
    static final String[] DFA20_transitionS = {
            "\2\31\2\uffff\1\31\22\uffff\1\31\1\30\1\uffff\1\30\1\33\6\uffff"+
            "\1\32\1\uffff\1\25\1\27\1\30\12\26\1\33\6\uffff\1\6\1\20\1\1"+
            "\1\3\1\10\1\16\1\24\1\22\2\24\1\21\1\5\1\15\1\13\1\17\1\11\1"+
            "\24\1\7\1\4\1\2\1\12\1\23\1\14\3\24\1\uffff\1\33\2\uffff\1\33"+
            "\1\uffff\1\6\1\20\1\1\1\3\1\10\1\16\1\24\1\22\2\24\1\21\1\5"+
            "\1\15\1\13\1\17\1\11\1\24\1\7\1\4\1\2\1\12\1\23\1\14\3\24",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\1\37\7\40\1\36\5"+
            "\40\1\35\2\40\1\34\10\40\1\uffff\1\33\2\uffff\1\33\1\uffff\1"+
            "\37\7\40\1\36\5\40\1\35\2\40\1\34\10\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\21\40\1\43\6\40\1"+
            "\42\1\40\1\uffff\1\33\2\uffff\1\33\1\uffff\21\40\1\43\6\40\1"+
            "\42\1\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\3\40\1\45\1\46\3"+
            "\40\1\44\13\40\1\47\5\40\1\uffff\1\33\2\uffff\1\33\1\uffff\3"+
            "\40\1\45\1\46\3\40\1\44\13\40\1\47\5\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\1\51\3\40\1\52\3"+
            "\40\1\50\3\40\1\53\1\40\1\54\4\40\1\55\6\40\1\uffff\1\33\2\uffff"+
            "\1\33\1\uffff\1\51\3\40\1\52\3\40\1\50\3\40\1\53\1\40\1\54\4"+
            "\40\1\55\6\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\4\40\1\57\3\40\1"+
            "\56\21\40\1\uffff\1\33\2\uffff\1\33\1\uffff\4\40\1\57\3\40\1"+
            "\56\21\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\1\40\1\60\13\40\1"+
            "\62\7\40\1\61\4\40\1\uffff\1\33\2\uffff\1\33\1\uffff\1\40\1"+
            "\60\13\40\1\62\7\40\1\61\4\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\3\40\1\63\1\64\11"+
            "\40\1\65\13\40\1\uffff\1\33\2\uffff\1\33\1\uffff\3\40\1\63\1"+
            "\64\11\40\1\65\13\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\13\40\1\67\1\40\1"+
            "\70\11\40\1\66\2\40\1\uffff\1\33\2\uffff\1\33\1\uffff\13\40"+
            "\1\67\1\40\1\70\11\40\1\66\2\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\3\40\1\73\1\74\3"+
            "\40\1\71\10\40\1\72\10\40\1\uffff\1\33\2\uffff\1\33\1\uffff"+
            "\3\40\1\73\1\74\3\40\1\71\10\40\1\72\10\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\15\40\1\75\14\40"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\15\40\1\75\14\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\16\40\1\77\5\40\1"+
            "\76\5\40\1\uffff\1\33\2\uffff\1\33\1\uffff\16\40\1\77\5\40\1"+
            "\76\5\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\4\40\1\101\3\40\1"+
            "\100\21\40\1\uffff\1\33\2\uffff\1\33\1\uffff\4\40\1\101\3\40"+
            "\1\100\21\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\1\103\7\40\1\104"+
            "\5\40\1\102\13\40\1\uffff\1\33\2\uffff\1\33\1\uffff\1\103\7"+
            "\40\1\104\5\40\1\102\13\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\10\40\1\107\2\40"+
            "\1\105\12\40\1\106\3\40\1\uffff\1\33\2\uffff\1\33\1\uffff\10"+
            "\40\1\107\2\40\1\105\12\40\1\106\3\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\10\40\1\110\21\40"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\10\40\1\110\21\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\4\40\1\111\25\40"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\4\40\1\111\25\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\4\40\1\112\25\40"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\4\40\1\112\25\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\16\40\1\113\13\40"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\16\40\1\113\13\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\4\40\1\114\25\40"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\4\40\1\114\25\40",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\40\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\40",
            "\1\27\1\uffff\12\26",
            "\1\33\10\uffff\1\33\1\116\1\uffff\12\26\1\33\6\uffff\4\33\1"+
            "\115\25\33\1\uffff\1\33\2\uffff\1\33\1\uffff\4\33\1\115\25\33",
            "\12\117",
            "",
            "",
            "",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\30\33\1\120\1\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\30\33\1\120\1\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\4\33\1\121\6\33\1"+
            "\123\1\33\1\122\14\33\1\uffff\1\33\2\uffff\1\33\1\uffff\4\33"+
            "\1\121\6\33\1\123\1\33\1\122\14\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\5\33\1\124\13\33"+
            "\1\125\10\33\1\uffff\1\33\2\uffff\1\33\1\uffff\5\33\1\124\13"+
            "\33\1\125\10\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\13\33\1\126\16\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\13\33\1\126\16\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\17\33\1\127\12\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\17\33\1\127\12\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\1\130\31\33\1\uffff"+
            "\1\33\2\uffff\1\33\1\uffff\1\130\31\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\5\33\1\131\6\33\1"+
            "\132\15\33\1\uffff\1\33\2\uffff\1\33\1\uffff\5\33\1\131\6\33"+
            "\1\132\15\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\14\33\1\133\15\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\14\33\1\133\15\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\2\33\1\134\2\33\1"+
            "\135\24\33\1\uffff\1\33\2\uffff\1\33\1\uffff\2\33\1\134\2\33"+
            "\1\135\24\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\14\33\1\136\15\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\14\33\1\136\15\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\14\33\1\137\15\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\14\33\1\137\15\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\27\33\1\140\2\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\27\33\1\140\2\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\20\33\1\141\11\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\20\33\1\141\11\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\1\142\31\33\1\uffff"+
            "\1\33\2\uffff\1\33\1\uffff\1\142\31\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\13\33\1\143\16\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\13\33\1\143\16\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\1\144\31\33\1\uffff"+
            "\1\33\2\uffff\1\33\1\uffff\1\144\31\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\15\33\1\145\14\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\15\33\1\145\14\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\1\146\31\33\1\uffff"+
            "\1\33\2\uffff\1\33\1\uffff\1\146\31\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\22\33\1\147\7\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\22\33\1\147\7\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\4\33\1\150\25\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\4\33\1\150\25\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\6\33\1\151\23\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\6\33\1\151\23\33",
            "\1\33\10\uffff\2\33\1\uffff\3\33\1\153\7\33\6\uffff\25\33\1"+
            "\152\4\33\1\uffff\1\33\2\uffff\1\33\1\uffff\25\33\1\152\4\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\2\33\1\154\27\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\2\33\1\154\27\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\23\33\1\155\6\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\23\33\1\155\6\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\17\33\1\156\12\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\17\33\1\156\12\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\4\33\1\157\25\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\4\33\1\157\25\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\4\33\1\160\25\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\4\33\1\160\25\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\27\33\1\161\2\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\27\33\1\161\2\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\16\33\1\162\13\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\16\33\1\162\13\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\1\33\1\163\30\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\1\33\1\163\30\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\21\33\1\164\10\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\21\33\1\164\10\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\10\33\1\165\21\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\10\33\1\165\21\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\14\33\1\166\15\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\14\33\1\166\15\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\15\33\1\167\14\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\15\33\1\167\14\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\21\33\1\170\10\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\21\33\1\170\10\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\3\33\1\171\26\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\3\33\1\171\26\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\3\33\1\172\26\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\3\33\1\172\26\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\23\33\1\173\6\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\23\33\1\173\6\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\27\33\1\174\2\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\27\33\1\174\2\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\21\33\1\175\2\33"+
            "\1\176\5\33\1\uffff\1\33\2\uffff\1\33\1\uffff\21\33\1\175\2"+
            "\33\1\176\5\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\7\33\1\177\22\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\7\33\1\177\22\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\13\33\1\u0080\16"+
            "\33\1\uffff\1\33\2\uffff\1\33\1\uffff\13\33\1\u0080\16\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\13\33\1\u0081\16"+
            "\33\1\uffff\1\33\2\uffff\1\33\1\uffff\13\33\1\u0081\16\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\1\u0082\31\33\1\uffff"+
            "\1\33\2\uffff\1\33\1\uffff\1\u0082\31\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\25\33\1\u0083\4\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\25\33\1\u0083\4\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\21\33\1\u0084\10"+
            "\33\1\uffff\1\33\2\uffff\1\33\1\uffff\21\33\1\u0084\10\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\21\33\1\u0085\10"+
            "\33\1\uffff\1\33\2\uffff\1\33\1\uffff\21\33\1\u0085\10\33",
            "\1\32\1\uffff\1\u0086\2\uffff\12\u0087",
            "\1\33\10\uffff\2\33\1\uffff\12\u0088\1\33\6\uffff\4\33\1\115"+
            "\25\33\1\uffff\1\33\2\uffff\1\33\1\uffff\4\33\1\115\25\33",
            "\1\33\10\uffff\2\33\1\uffff\12\117\1\33\6\uffff\4\33\1\115"+
            "\25\33\1\uffff\1\33\2\uffff\1\33\1\uffff\4\33\1\115\25\33",
            "\1\u008a\3\uffff\1\u0089\33\uffff\1\u008a\3\uffff\1\u0089",
            "\1\u008b\37\uffff\1\u008b",
            "\1\u008c\37\uffff\1\u008c",
            "\1\u008d\37\uffff\1\u008d",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u008f\37\uffff\1\u008f",
            "\1\u0090\37\uffff\1\u0090",
            "\1\u0091\37\uffff\1\u0091",
            "\1\u0092\37\uffff\1\u0092",
            "\1\u0093\37\uffff\1\u0093",
            "\1\u0094\37\uffff\1\u0094",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0096\37\uffff\1\u0096",
            "\1\u0097\37\uffff\1\u0097",
            "\1\u0098\37\uffff\1\u0098",
            "\1\u0099\37\uffff\1\u0099",
            "\1\u009a\37\uffff\1\u009a",
            "\1\u009c\16\uffff\1\u009b\20\uffff\1\u009c\16\uffff\1\u009b",
            "\1\u009d\37\uffff\1\u009d",
            "\1\u009e\37\uffff\1\u009e",
            "\1\u009f\37\uffff\1\u009f",
            "\1\u00a0\37\uffff\1\u00a0",
            "\1\u00a1\37\uffff\1\u00a1",
            "\1\u00a2\37\uffff\1\u00a2",
            "\1\u00a3\37\uffff\1\u00a3",
            "\1\u00a4\10\uffff\1\u00a5\26\uffff\1\u00a4\10\uffff\1\u00a5",
            "\1\33\10\uffff\2\33\1\uffff\2\33\2\u00a6\7\33\6\uffff\32\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\32\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\3\33\1\u00a8\26\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\3\33\1\u00a8\26\33",
            "\1\u00aa\37\uffff\1\u00aa",
            "\1\u00ab\37\uffff\1\u00ab",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\16\33\1\u00ad\3\33"+
            "\1\u00ac\7\33\1\uffff\1\33\2\uffff\1\33\1\uffff\16\33\1\u00ad"+
            "\3\33\1\u00ac\7\33",
            "\1\u00af\37\uffff\1\u00af",
            "\1\u00b0\37\uffff\1\u00b0",
            "\1\u00b1\37\uffff\1\u00b1",
            "\1\u00b2\37\uffff\1\u00b2",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u00b4\37\uffff\1\u00b4",
            "\1\u00b5\37\uffff\1\u00b5",
            "\1\u00b8\10\uffff\1\u00b6\4\uffff\1\u00b7\21\uffff\1\u00b8"+
            "\10\uffff\1\u00b6\4\uffff\1\u00b7",
            "\1\u00b9\37\uffff\1\u00b9",
            "\1\u00ba\37\uffff\1\u00ba",
            "\1\u00bb\37\uffff\1\u00bb",
            "\1\u00bc\37\uffff\1\u00bc",
            "\1\u00bd\37\uffff\1\u00bd",
            "\1\u00be\37\uffff\1\u00be",
            "\1\u00bf\37\uffff\1\u00bf",
            "\1\u00c0\37\uffff\1\u00c0",
            "\1\u00c1\37\uffff\1\u00c1",
            "\1\u00c2\37\uffff\1\u00c2",
            "\1\u00c3\37\uffff\1\u00c3",
            "\1\u00c4\37\uffff\1\u00c4",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u00c6\37\uffff\1\u00c6",
            "\1\u00c7\37\uffff\1\u00c7",
            "\12\u0087",
            "\1\33\10\uffff\2\33\1\uffff\12\u0087\1\33\6\uffff\32\33\1\uffff"+
            "\1\33\2\uffff\1\33\1\uffff\32\33",
            "\1\33\10\uffff\2\33\1\uffff\12\u0088\1\33\6\uffff\4\33\1\115"+
            "\25\33\1\uffff\1\33\2\uffff\1\33\1\uffff\4\33\1\115\25\33",
            "\1\u00c8\37\uffff\1\u00c8",
            "\1\u00c9\37\uffff\1\u00c9",
            "\1\u00ca\37\uffff\1\u00ca",
            "\1\u00cb\37\uffff\1\u00cb",
            "\1\u00cc\37\uffff\1\u00cc",
            "",
            "\1\u00cd\37\uffff\1\u00cd",
            "\1\u00ce\37\uffff\1\u00ce",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u00d0\37\uffff\1\u00d0",
            "\1\u00d1\37\uffff\1\u00d1",
            "\1\u00d2\37\uffff\1\u00d2",
            "",
            "\1\u00d3\37\uffff\1\u00d3",
            "\1\u00d4\37\uffff\1\u00d4",
            "\1\u00d5\37\uffff\1\u00d5",
            "\1\u00d6\37\uffff\1\u00d6",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\22\33\1\u00d7\7\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\22\33\1\u00d7\7\33",
            "\1\u00d9\37\uffff\1\u00d9",
            "\1\u00da\37\uffff\1\u00da",
            "\1\u00db\37\uffff\1\u00db",
            "\1\u00dc\37\uffff\1\u00dc",
            "\1\u00dd\37\uffff\1\u00dd",
            "\1\u00de\37\uffff\1\u00de",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u00e0\37\uffff\1\u00e0",
            "\1\u00e1\37\uffff\1\u00e1",
            "\1\u00e2\37\uffff\1\u00e2",
            "\1\u00e3\37\uffff\1\u00e3",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "",
            "\1\u00e4\37\uffff\1\u00e4",
            "\1\u00e5\37\uffff\1\u00e5",
            "\1\u00e6\37\uffff\1\u00e6",
            "\1\u00e7\37\uffff\1\u00e7",
            "",
            "\1\u00e8\37\uffff\1\u00e8",
            "\1\u00e9\37\uffff\1\u00e9",
            "\1\u00ea\37\uffff\1\u00ea",
            "\1\u00eb\37\uffff\1\u00eb",
            "",
            "\1\u00ec\37\uffff\1\u00ec",
            "\1\u00ed\37\uffff\1\u00ed",
            "\1\u00ee\37\uffff\1\u00ee",
            "\1\u00ef\10\uffff\1\u00f0\26\uffff\1\u00ef\10\uffff\1\u00f0",
            "\1\u00f1\37\uffff\1\u00f1",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u00f3\37\uffff\1\u00f3",
            "\1\u00f4\37\uffff\1\u00f4",
            "\1\u00f5\37\uffff\1\u00f5",
            "\1\u00f6\37\uffff\1\u00f6",
            "\1\u00f7\37\uffff\1\u00f7",
            "\1\u00f8\37\uffff\1\u00f8",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u00fc\37\uffff\1\u00fc",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "",
            "\1\u00fe\37\uffff\1\u00fe",
            "\1\u00ff\37\uffff\1\u00ff",
            "\1\u0100\37\uffff\1\u0100",
            "\1\u0101\37\uffff\1\u0101",
            "\1\u0102\37\uffff\1\u0102",
            "\1\u0103\37\uffff\1\u0103",
            "\1\u0104\37\uffff\1\u0104",
            "\1\u0105\37\uffff\1\u0105",
            "\1\u0106\37\uffff\1\u0106",
            "",
            "\1\u0107\37\uffff\1\u0107",
            "\1\u0108\37\uffff\1\u0108",
            "\1\u0109\37\uffff\1\u0109",
            "\1\u010a\37\uffff\1\u010a",
            "\1\u010b\37\uffff\1\u010b",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u010d\37\uffff\1\u010d",
            "\1\u010e\37\uffff\1\u010e",
            "",
            "\1\u010f\37\uffff\1\u010f",
            "\1\u0110\37\uffff\1\u0110",
            "\1\u0111\37\uffff\1\u0111",
            "\1\u0112\37\uffff\1\u0112",
            "\1\u0113\37\uffff\1\u0113",
            "\1\u0114\37\uffff\1\u0114",
            "",
            "\1\121\37\uffff\1\121",
            "\1\u0115\37\uffff\1\u0115",
            "\1\u0117\3\uffff\1\u0116\33\uffff\1\u0117\3\uffff\1\u0116",
            "\1\u0118\37\uffff\1\u0118",
            "\1\u0119\37\uffff\1\u0119",
            "\1\u011a\37\uffff\1\u011a",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u011c\37\uffff\1\u011c",
            "\1\u011d\37\uffff\1\u011d",
            "\1\u011e\37\uffff\1\u011e",
            "\1\u011f\37\uffff\1\u011f",
            "\1\u0120\37\uffff\1\u0120",
            "\1\u0121\37\uffff\1\u0121",
            "\1\u0122\37\uffff\1\u0122",
            "\1\u0123\37\uffff\1\u0123",
            "\1\u0124\37\uffff\1\u0124",
            "\1\u0125\37\uffff\1\u0125",
            "\1\u0126\37\uffff\1\u0126",
            "",
            "\1\u0127\37\uffff\1\u0127",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0129\37\uffff\1\u0129",
            "\1\u012a\37\uffff\1\u012a",
            "\1\u012b\37\uffff\1\u012b",
            "\1\u012c\37\uffff\1\u012c",
            "",
            "",
            "",
            "\1\u012d\37\uffff\1\u012d",
            "",
            "\1\u012e\37\uffff\1\u012e",
            "\1\u012f\37\uffff\1\u012f",
            "\1\u0130\37\uffff\1\u0130",
            "\1\u0131\37\uffff\1\u0131",
            "\1\u0132\37\uffff\1\u0132",
            "\1\u0133\37\uffff\1\u0133",
            "\1\u0134\37\uffff\1\u0134",
            "\1\u0135\37\uffff\1\u0135",
            "\1\u0136\37\uffff\1\u0136",
            "\1\u0137\37\uffff\1\u0137",
            "\1\u0138\37\uffff\1\u0138",
            "\1\u0139\37\uffff\1\u0139",
            "\1\u013a\37\uffff\1\u013a",
            "\1\u013b\37\uffff\1\u013b",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u013d\37\uffff\1\u013d",
            "\1\u013e\37\uffff\1\u013e",
            "\1\u013f\37\uffff\1\u013f",
            "\1\u0140\37\uffff\1\u0140",
            "\1\u0141\37\uffff\1\u0141",
            "\1\u0142\37\uffff\1\u0142",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0144\37\uffff\1\u0144",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0147\37\uffff\1\u0147",
            "\1\u0148\37\uffff\1\u0148",
            "\1\u0149\37\uffff\1\u0149",
            "",
            "\1\u014a\37\uffff\1\u014a",
            "\1\u014b\37\uffff\1\u014b",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u014e\6\uffff\1\u014d\30\uffff\1\u014e\6\uffff\1\u014d",
            "\1\u014f\37\uffff\1\u014f",
            "\1\u0150\37\uffff\1\u0150",
            "\1\u0151\37\uffff\1\u0151",
            "\1\u0152\37\uffff\1\u0152",
            "\1\u0153\37\uffff\1\u0153",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0156\37\uffff\1\u0156",
            "",
            "\1\u0157\37\uffff\1\u0157",
            "\1\u0158\37\uffff\1\u0158",
            "\1\u0159\37\uffff\1\u0159",
            "\1\u015a\37\uffff\1\u015a",
            "\1\u015b\37\uffff\1\u015b",
            "\1\u015c\37\uffff\1\u015c",
            "\1\u015d\37\uffff\1\u015d",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u015f\37\uffff\1\u015f",
            "\1\u0160\37\uffff\1\u0160",
            "\1\u0161\37\uffff\1\u0161",
            "\1\u0162\37\uffff\1\u0162",
            "\1\u0163\37\uffff\1\u0163",
            "\1\u0164\37\uffff\1\u0164",
            "\1\u0165\37\uffff\1\u0165",
            "\1\u0166\37\uffff\1\u0166",
            "\1\u0167\37\uffff\1\u0167",
            "\1\u0168\37\uffff\1\u0168",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u016b\37\uffff\1\u016b",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u016d\37\uffff\1\u016d",
            "\1\u016f\1\uffff\1\u016e\35\uffff\1\u016f\1\uffff\1\u016e",
            "\1\u0170\37\uffff\1\u0170",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "",
            "",
            "\1\u0172\37\uffff\1\u0172",
            "\1\u0173\37\uffff\1\u0173",
            "\1\u0174\37\uffff\1\u0174",
            "\1\u0175\37\uffff\1\u0175",
            "\1\u0176\37\uffff\1\u0176",
            "",
            "\1\u0177\37\uffff\1\u0177",
            "\1\u0178\37\uffff\1\u0178",
            "\1\u0179\4\uffff\1\u017a\32\uffff\1\u0179\4\uffff\1\u017a",
            "\1\u017b\37\uffff\1\u017b",
            "\1\u017c\37\uffff\1\u017c",
            "\1\u017d\37\uffff\1\u017d",
            "\1\u017e\37\uffff\1\u017e",
            "",
            "",
            "\1\u017f\37\uffff\1\u017f",
            "\1\u0180\37\uffff\1\u0180",
            "\1\u0181\37\uffff\1\u0181",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0183\37\uffff\1\u0183",
            "\1\u0184\37\uffff\1\u0184",
            "\1\u0185\37\uffff\1\u0185",
            "\1\u0186\37\uffff\1\u0186",
            "",
            "\1\u0187\37\uffff\1\u0187",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0189\37\uffff\1\u0189",
            "\1\u018a\37\uffff\1\u018a",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u018c\37\uffff\1\u018c",
            "\1\u018d\37\uffff\1\u018d",
            "\1\u018e\37\uffff\1\u018e",
            "\1\u018f\37\uffff\1\u018f",
            "\1\u0190\37\uffff\1\u0190",
            "",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\5\33\1\u0191\24\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\5\33\1\u0191\24\33",
            "",
            "\1\u0193\37\uffff\1\u0193",
            "\1\u0194\37\uffff\1\u0194",
            "\1\u0195\37\uffff\1\u0195",
            "\1\u0196\37\uffff\1\u0196",
            "",
            "\1\u0197\37\uffff\1\u0197",
            "\1\u0198\37\uffff\1\u0198",
            "\1\u0199\37\uffff\1\u0199",
            "\1\u019a\37\uffff\1\u019a",
            "\1\u019b\37\uffff\1\u019b",
            "\1\u019c\37\uffff\1\u019c",
            "\1\u019d\37\uffff\1\u019d",
            "\1\u019e\37\uffff\1\u019e",
            "\1\u019f\37\uffff\1\u019f",
            "\1\u01a0\37\uffff\1\u01a0",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u01a2\37\uffff\1\u01a2",
            "\1\u01a3\37\uffff\1\u01a3",
            "\1\u01a4\37\uffff\1\u01a4",
            "\1\u01a5\37\uffff\1\u01a5",
            "\1\u01a8\7\uffff\1\u01a7\6\uffff\1\u01a6\20\uffff\1\u01a8\7"+
            "\uffff\1\u01a7\6\uffff\1\u01a6",
            "",
            "\1\u01a9\37\uffff\1\u01a9",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u01ab\37\uffff\1\u01ab",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u01ad\37\uffff\1\u01ad",
            "",
            "\1\u01b0\10\uffff\1\u01ae\6\uffff\1\u01af\17\uffff\1\u01b0"+
            "\10\uffff\1\u01ae\6\uffff\1\u01af",
            "\1\u01b1\37\uffff\1\u01b1",
            "",
            "\1\u01b3\11\uffff\1\u01b2\25\uffff\1\u01b3\11\uffff\1\u01b2",
            "\1\u01b4\37\uffff\1\u01b4",
            "\1\u01b5\37\uffff\1\u01b5",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\22\33\1\u01b6\7\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\22\33\1\u01b6\7\33",
            "\1\u01b8\37\uffff\1\u01b8",
            "\1\u01b9\37\uffff\1\u01b9",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\1\u01ba\31\33\1\uffff"+
            "\1\33\2\uffff\1\33\1\uffff\1\u01ba\31\33",
            "\1\u01bc\37\uffff\1\u01bc",
            "\1\u01bd\37\uffff\1\u01bd",
            "\1\u01be\37\uffff\1\u01be",
            "\1\u01bf\37\uffff\1\u01bf",
            "\1\u01c0\37\uffff\1\u01c0",
            "\1\u01c1\37\uffff\1\u01c1",
            "\1\u01c2\37\uffff\1\u01c2",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u01c4\37\uffff\1\u01c4",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u01c6\37\uffff\1\u01c6",
            "\1\u01c7\37\uffff\1\u01c7",
            "\1\u01c8\37\uffff\1\u01c8",
            "",
            "\1\u01c9\37\uffff\1\u01c9",
            "\1\u01ca\37\uffff\1\u01ca",
            "\1\u01cb\37\uffff\1\u01cb",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u01cd\37\uffff\1\u01cd",
            "\1\u01ce\37\uffff\1\u01ce",
            "\1\u01cf\37\uffff\1\u01cf",
            "\1\u01d0\37\uffff\1\u01d0",
            "",
            "\1\u01d1\37\uffff\1\u01d1",
            "",
            "\1\u01d2\37\uffff\1\u01d2",
            "\1\u01d3\37\uffff\1\u01d3",
            "\1\u01d4\37\uffff\1\u01d4",
            "\1\u01d5\37\uffff\1\u01d5",
            "\1\u01d6\37\uffff\1\u01d6",
            "\1\u01d7\37\uffff\1\u01d7",
            "\1\u01d8\37\uffff\1\u01d8",
            "\1\u01d9\37\uffff\1\u01d9",
            "\1\u01da\37\uffff\1\u01da",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u01dc\37\uffff\1\u01dc",
            "\1\u01dd\37\uffff\1\u01dd",
            "",
            "\1\u01de\37\uffff\1\u01de",
            "\1\u01df\37\uffff\1\u01df",
            "\1\u01e0\37\uffff\1\u01e0",
            "\1\u01e1\37\uffff\1\u01e1",
            "\1\u01e2\37\uffff\1\u01e2",
            "\1\u01e3\37\uffff\1\u01e3",
            "\1\u01e4\37\uffff\1\u01e4",
            "",
            "\1\u01e5\37\uffff\1\u01e5",
            "",
            "\1\u01e6\37\uffff\1\u01e6",
            "\1\u01e7\37\uffff\1\u01e7",
            "\1\u01e8\37\uffff\1\u01e8",
            "\1\u01e9\37\uffff\1\u01e9",
            "\1\u01ea\37\uffff\1\u01ea",
            "\1\u01ed\10\uffff\1\u01eb\26\uffff\1\u01ed\10\uffff\1\u01ec",
            "",
            "\1\u01ee\37\uffff\1\u01ee",
            "\1\u01ef\37\uffff\1\u01ef",
            "\1\u01f0\37\uffff\1\u01f0",
            "\1\u01f1\37\uffff\1\u01f1",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u01f3\37\uffff\1\u01f3",
            "\1\u01f4\37\uffff\1\u01f4",
            "\1\u01f5\37\uffff\1\u01f5",
            "\1\u01f6\37\uffff\1\u01f6",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u01f8\37\uffff\1\u01f8",
            "\1\u01f9\37\uffff\1\u01f9",
            "\1\u01fa\37\uffff\1\u01fa",
            "\1\u01fb\37\uffff\1\u01fb",
            "",
            "\1\u01fc\37\uffff\1\u01fc",
            "\1\u01fd\37\uffff\1\u01fd",
            "\1\u01fe\37\uffff\1\u01fe",
            "\1\u01ff\37\uffff\1\u01ff",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0201\37\uffff\1\u0201",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0203\37\uffff\1\u0203",
            "\1\u0204\37\uffff\1\u0204",
            "\1\u0205\37\uffff\1\u0205",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\4\33\1\u0206\25\33"+
            "\1\uffff\1\33\2\uffff\1\33\1\uffff\4\33\1\u0206\25\33",
            "\1\u0208\37\uffff\1\u0208",
            "\1\u0209\37\uffff\1\u0209",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u01ed\37\uffff\1\u01ed",
            "\1\u01ed\37\uffff\1\u01ed",
            "\1\u020c\37\uffff\1\u020c",
            "\1\u020d\37\uffff\1\u020d",
            "\1\u020e\37\uffff\1\u020e",
            "\1\u020f\37\uffff\1\u020f",
            "\1\u0210\37\uffff\1\u0210",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0212\37\uffff\1\u0212",
            "\1\u0213\37\uffff\1\u0213",
            "\1\u0214\37\uffff\1\u0214",
            "",
            "\1\u0215\37\uffff\1\u0215",
            "\1\u0216\37\uffff\1\u0216",
            "\1\u0217\37\uffff\1\u0217",
            "\1\u0218\37\uffff\1\u0218",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u021a\37\uffff\1\u021a",
            "\1\u021b\37\uffff\1\u021b",
            "\1\u021c\37\uffff\1\u021c",
            "",
            "\1\u021d\37\uffff\1\u021d",
            "",
            "\1\u021e\37\uffff\1\u021e",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0220\37\uffff\1\u0220",
            "\1\u0221\37\uffff\1\u0221",
            "",
            "\1\u0222\37\uffff\1\u0222",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0226\37\uffff\1\u0226",
            "\1\u0227\37\uffff\1\u0227",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "",
            "\1\u0229\37\uffff\1\u0229",
            "\1\u022a\37\uffff\1\u022a",
            "\1\u022b\37\uffff\1\u022b",
            "\1\u022c\37\uffff\1\u022c",
            "\1\u022d\37\uffff\1\u022d",
            "\1\u022e\37\uffff\1\u022e",
            "\1\u022f\37\uffff\1\u022f",
            "",
            "\1\u0230\37\uffff\1\u0230",
            "\1\u0231\37\uffff\1\u0231",
            "\1\u0232\37\uffff\1\u0232",
            "\1\u0233\37\uffff\1\u0233",
            "\1\u0234\37\uffff\1\u0234",
            "",
            "\1\u0235\37\uffff\1\u0235",
            "\1\u0236\37\uffff\1\u0236",
            "\1\u0237\37\uffff\1\u0237",
            "",
            "",
            "",
            "\1\u0238\37\uffff\1\u0238",
            "\1\u0239\37\uffff\1\u0239",
            "",
            "\1\u023a\37\uffff\1\u023a",
            "\1\u023b\37\uffff\1\u023b",
            "\1\u023c\37\uffff\1\u023c",
            "\1\u023d\37\uffff\1\u023d",
            "\1\u023e\37\uffff\1\u023e",
            "\1\u023f\37\uffff\1\u023f",
            "\1\u0240\37\uffff\1\u0240",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0242\37\uffff\1\u0242",
            "\1\u0243\37\uffff\1\u0243",
            "\1\u0244\37\uffff\1\u0244",
            "\1\u0245\37\uffff\1\u0245",
            "\1\u0246\37\uffff\1\u0246",
            "\1\u0247\37\uffff\1\u0247",
            "\1\u0248\37\uffff\1\u0248",
            "\1\u0249\37\uffff\1\u0249",
            "\1\u024a\37\uffff\1\u024a",
            "\1\u024b\37\uffff\1\u024b",
            "\1\u024c\37\uffff\1\u024c",
            "\1\u024d\37\uffff\1\u024d",
            "\1\u024e\37\uffff\1\u024e",
            "\1\u024f\37\uffff\1\u024f",
            "\1\u0250\37\uffff\1\u0250",
            "\1\u0251\37\uffff\1\u0251",
            "",
            "\1\u0252\37\uffff\1\u0252",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0254\37\uffff\1\u0254",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0257\37\uffff\1\u0257",
            "\1\u0258\37\uffff\1\u0258",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u025a\37\uffff\1\u025a",
            "\1\u025b\37\uffff\1\u025b",
            "\1\u025c\37\uffff\1\u025c",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u025e\37\uffff\1\u025e",
            "\1\u025f\37\uffff\1\u025f",
            "\1\u0260\37\uffff\1\u0260",
            "\1\u0261\37\uffff\1\u0261",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "",
            "\1\u0263\37\uffff\1\u0263",
            "",
            "",
            "\1\u0264\37\uffff\1\u0264",
            "\1\u0265\37\uffff\1\u0265",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0269\7\uffff\1\u0268\6\uffff\1\u0267\20\uffff\1\u0269\7"+
            "\uffff\1\u0268\6\uffff\1\u0267",
            "\1\u026a\37\uffff\1\u026a",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u026d\37\uffff\1\u026d",
            "\1\u026e\37\uffff\1\u026e",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0270\37\uffff\1\u0270",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "",
            "\1\u0272\37\uffff\1\u0272",
            "\1\u0273\37\uffff\1\u0273",
            "\1\u0274\37\uffff\1\u0274",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0277\37\uffff\1\u0277",
            "",
            "\1\u0278\37\uffff\1\u0278",
            "",
            "\1\u0279\37\uffff\1\u0279",
            "\1\u027a\37\uffff\1\u027a",
            "\1\u027b\37\uffff\1\u027b",
            "",
            "",
            "\1\u027c\37\uffff\1\u027c",
            "\1\u027d\37\uffff\1\u027d",
            "\1\u027e\37\uffff\1\u027e",
            "\1\u027f\37\uffff\1\u027f",
            "\1\u0280\37\uffff\1\u0280",
            "\1\u0281\37\uffff\1\u0281",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u0284\37\uffff\1\u0284",
            "\1\u0285\37\uffff\1\u0285",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "",
            "",
            "\1\u0287\37\uffff\1\u0287",
            "\1\u0288\37\uffff\1\u0288",
            "",
            "\1\u0289\37\uffff\1\u0289",
            "\1\u028a\37\uffff\1\u028a",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            "\1\u028c\37\uffff\1\u028c",
            "",
            "\1\33\10\uffff\2\33\1\uffff\13\33\6\uffff\32\33\1\uffff\1\33"+
            "\2\uffff\1\33\1\uffff\32\33",
            ""
    };

    static final short[] DFA20_eot = DFA.unpackEncodedString(DFA20_eotS);
    static final short[] DFA20_eof = DFA.unpackEncodedString(DFA20_eofS);
    static final char[] DFA20_min = DFA.unpackEncodedStringToUnsignedChars(DFA20_minS);
    static final char[] DFA20_max = DFA.unpackEncodedStringToUnsignedChars(DFA20_maxS);
    static final short[] DFA20_accept = DFA.unpackEncodedString(DFA20_acceptS);
    static final short[] DFA20_special = DFA.unpackEncodedString(DFA20_specialS);
    static final short[][] DFA20_transition;

    static {
        int numStates = DFA20_transitionS.length;
        DFA20_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA20_transition[i] = DFA.unpackEncodedString(DFA20_transitionS[i]);
        }
    }

    class DFA20 extends DFA {

        public DFA20(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 20;
            this.eot = DFA20_eot;
            this.eof = DFA20_eof;
            this.min = DFA20_min;
            this.max = DFA20_max;
            this.accept = DFA20_accept;
            this.special = DFA20_special;
            this.transition = DFA20_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( CRYSTAL | TYPE | DIFFRACTIONDECAYMODEL | DDM | SIMPLE | LINEAR | LEAL | DECAYPARAM | ABSCOEFCALC | DUMMY | DEFAULT | AVERAGE | RDFORTAN | RDJAVA | PDB | SAXS | SEQUENCE | SAXSSEQ | SMALLMOLE | CIF | DIMENSION | ANGLEP | ANGLEL | PIXELSPERMICRON | UNITCELL | PROTEINCONC | PROTEINCONCENTRATION | NUMMONOMERS | NUMRESIDUES | NUMRNA | NUMDNA | PROTEINHEAVYATOMS | ELEMENT | SMALLMOLEATOMS | SOLVENTHEAVYCONC | SOLVENTFRACTION | PDBNAME | CIFNAME | WIREFRAMETYPE | MODELFILE | CALCULATEPEESCAPE | CONTAINERMATERIALTYPE | MATERIALTYPE | NONE | MIXTURE | ELEMENTAL | CONTAINERTHICKNESS | CONTAINERMATERIALMIXTURE | MATERIALMIXTURE | CONTAINERMATERIALELEMENTS | MATERIALELEMENTS | CONTAINERDENSITY | SEQUENCEFILE | SEQFILE | CALCULATEFLESCAPE | FLRESOLUTION | PERESOLUTION | CRYOSOLUTION | OILBASED | BEAM | FLUX | FWHM | ENERGY | KEV | FILE | PIXELSIZE | COLLIMATION | RECTANGULAR | CIRCULAR | HORIZONTAL | VERTICAL | WEDGE | EXPOSURETIME | ANGULARRESOLUTION | STARTOFFSET | TRANSLATEPERDEGREE | ROTAXBEAMOFFSET | FLOAT | COMMENT | WS | STRING );";
        }
    }
 

}