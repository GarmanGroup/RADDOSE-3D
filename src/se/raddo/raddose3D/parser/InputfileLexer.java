// $ANTLR 3.4 C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g 2014-04-28 13:43:14

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
    public static final int CIRCULAR=10;
    public static final int COLLIMATION=11;
    public static final int COMMENT=12;
    public static final int CRYSTAL=13;
    public static final int DDM=14;
    public static final int DEFAULT=15;
    public static final int DIFFRACTIONDECAYMODEL=16;
    public static final int DIMENSION=17;
    public static final int DUMMY=18;
    public static final int ELEMENT=19;
    public static final int ENERGY=20;
    public static final int EXPONENT=21;
    public static final int EXPOSURETIME=22;
    public static final int FILE=23;
    public static final int FLOAT=24;
    public static final int FLUX=25;
    public static final int FWHM=26;
    public static final int HORIZONTAL=27;
    public static final int KEV=28;
    public static final int LINEAR=29;
    public static final int NUMDNA=30;
    public static final int NUMMONOMERS=31;
    public static final int NUMRESIDUES=32;
    public static final int NUMRNA=33;
    public static final int PDB=34;
    public static final int PDBNAME=35;
    public static final int PIXELSIZE=36;
    public static final int PIXELSPERMICRON=37;
    public static final int PROTEINHEAVYATOMS=38;
    public static final int RD=39;
    public static final int RECTANGULAR=40;
    public static final int ROTAXBEAMOFFSET=41;
    public static final int SIMPLE=42;
    public static final int SOLVENTFRACTION=43;
    public static final int SOLVENTHEAVYCONC=44;
    public static final int STARTOFFSET=45;
    public static final int STRING=46;
    public static final int TRANSLATEPERDEGREE=47;
    public static final int TYPE=48;
    public static final int UNITCELL=49;
    public static final int VERTICAL=50;
    public static final int WEDGE=51;
    public static final int WS=52;

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
    public String getGrammarFileName() { return "C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g"; }

    // $ANTLR start "CRYSTAL"
    public final void mCRYSTAL() throws RecognitionException {
        try {
            int _type = CRYSTAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:110:9: ( ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'Y' | 'y' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:110:11: ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'Y' | 'y' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:143:6: ( ( 'T' | 't' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'E' | 'e' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:143:8: ( 'T' | 't' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'E' | 'e' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:147:23: ( ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'L' | 'l' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:147:25: ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'Y' | 'y' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'L' | 'l' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:148:5: ( ( 'D' | 'd' ) ( 'D' | 'd' ) ( 'M' | 'm' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:148:7: ( 'D' | 'd' ) ( 'D' | 'd' ) ( 'M' | 'm' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:153:8: ( ( 'S' | 's' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'P' | 'p' ) ( 'L' | 'l' ) ( 'E' | 'e' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:153:10: ( 'S' | 's' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'P' | 'p' ) ( 'L' | 'l' ) ( 'E' | 'e' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:154:8: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:154:10: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'R' | 'r' )
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

    // $ANTLR start "ABSCOEFCALC"
    public final void mABSCOEFCALC() throws RecognitionException {
        try {
            int _type = ABSCOEFCALC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:158:13: ( ( ( 'A' | 'a' ) ( 'B' | 'b' ) ( 'S' | 's' ) )? ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'C' | 'c' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:158:15: ( ( 'A' | 'a' ) ( 'B' | 'b' ) ( 'S' | 's' ) )? ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'C' | 'c' )
            {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:158:15: ( ( 'A' | 'a' ) ( 'B' | 'b' ) ( 'S' | 's' ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='A'||LA1_0=='a') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:158:16: ( 'A' | 'a' ) ( 'B' | 'b' ) ( 'S' | 's' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:166:7: ( ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'M' | 'm' ) ( 'Y' | 'y' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:166:9: ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'M' | 'm' ) ( 'Y' | 'y' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:167:9: ( ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'A' | 'a' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'T' | 't' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:167:11: ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'A' | 'a' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'T' | 't' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:168:9: ( ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'G' | 'g' ) ( 'E' | 'e' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:168:11: ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'G' | 'g' ) ( 'E' | 'e' )
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

    // $ANTLR start "RD"
    public final void mRD() throws RecognitionException {
        try {
            int _type = RD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:169:4: ( ( 'R' | 'r' ) ( 'D' | 'd' ) ( ( 'V' | 'v' ) ( '2' | '3' ) )? )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:169:6: ( 'R' | 'r' ) ( 'D' | 'd' ) ( ( 'V' | 'v' ) ( '2' | '3' ) )?
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


            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:169:24: ( ( 'V' | 'v' ) ( '2' | '3' ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='V'||LA2_0=='v') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:169:25: ( 'V' | 'v' ) ( '2' | '3' )
                    {
                    if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


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
    // $ANTLR end "RD"

    // $ANTLR start "PDB"
    public final void mPDB() throws RecognitionException {
        try {
            int _type = PDB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:170:5: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:170:7: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' )
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

    // $ANTLR start "DIMENSION"
    public final void mDIMENSION() throws RecognitionException {
        try {
            int _type = DIMENSION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:182:11: ( ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'S' | 's' )? )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:182:13: ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'S' | 's' )?
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


            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:182:94: ( 'S' | 's' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='S'||LA3_0=='s') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:187:8: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'P' | 'p' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:187:10: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'P' | 'p' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:192:8: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'L' | 'l' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:192:10: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'L' | 'l' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:196:17: ( ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:196:19: ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'N' | 'n' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:209:10: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'L' | 'l' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:209:12: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'L' | 'l' )
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

    // $ANTLR start "NUMMONOMERS"
    public final void mNUMMONOMERS() throws RecognitionException {
        try {
            int _type = NUMMONOMERS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:213:12: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:213:14: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'S' | 's' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:217:13: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'S' | 's' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:217:15: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'S' | 's' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:221:8: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'R' | 'r' ) ( 'N' | 'n' ) ( 'A' | 'a' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:221:10: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'R' | 'r' ) ( 'N' | 'n' ) ( 'A' | 'a' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:225:8: ( ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'D' | 'd' ) ( 'N' | 'n' ) ( 'A' | 'a' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:225:10: ( 'N' | 'n' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'D' | 'd' ) ( 'N' | 'n' ) ( 'A' | 'a' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:233:19: ( ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'Y' | 'y' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'S' | 's' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:233:21: ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'Y' | 'y' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'S' | 's' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:234:9: ( ( 'A' .. 'Z' | 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' )? )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:234:11: ( 'A' .. 'Z' | 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' )?
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:234:32: ( 'A' .. 'Z' | 'a' .. 'z' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0 >= 'A' && LA4_0 <= 'Z')||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
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

    // $ANTLR start "SOLVENTHEAVYCONC"
    public final void mSOLVENTHEAVYCONC() throws RecognitionException {
        try {
            int _type = SOLVENTHEAVYCONC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:242:18: ( ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'Y' | 'y' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'C' | 'c' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:242:20: ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'Y' | 'y' ) ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'C' | 'c' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:246:17: ( ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:246:19: ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:250:9: ( ( 'P' | 'p' ) ( 'D' | 'd' ) ( 'B' | 'b' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:250:11: ( 'P' | 'p' ) ( 'D' | 'd' ) ( 'B' | 'b' )
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

    // $ANTLR start "BEAM"
    public final void mBEAM() throws RecognitionException {
        try {
            int _type = BEAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:265:6: ( ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'M' | 'm' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:265:8: ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'M' | 'm' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:282:6: ( ( 'F' | 'f' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'X' | 'x' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:282:8: ( 'F' | 'f' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'X' | 'x' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:286:6: ( ( 'F' | 'f' ) ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'M' | 'm' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:286:8: ( 'F' | 'f' ) ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'M' | 'm' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:292:8: ( ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'G' | 'g' ) ( 'Y' | 'y' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:292:10: ( 'E' | 'e' ) ( 'N' | 'n' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'G' | 'g' ) ( 'Y' | 'y' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:293:5: ( ( 'K' | 'k' ) ( 'E' | 'e' ) ( 'V' | 'v' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:293:7: ( 'K' | 'k' ) ( 'E' | 'e' ) ( 'V' | 'v' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:298:6: ( ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:298:8: ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'E' | 'e' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:306:2: ( ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'Z' | 'z' ) ( 'E' | 'e' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:306:5: ( 'P' | 'p' ) ( 'I' | 'i' ) ( 'X' | 'x' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'Z' | 'z' ) ( 'E' | 'e' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:319:13: ( ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:319:15: ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:320:13: ( ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:320:15: ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'C' | 'c' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:321:11: ( ( 'C' | 'c' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:321:13: ( 'C' | 'c' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'C' | 'c' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:322:13: ( ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'Z' | 'z' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:322:15: ( 'H' | 'h' ) ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'Z' | 'z' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'L' | 'l' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:323:11: ( ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:323:13: ( 'V' | 'v' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'C' | 'c' ) ( 'A' | 'a' ) ( 'L' | 'l' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:349:7: ( ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'G' | 'g' ) ( 'E' | 'e' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:349:9: ( 'W' | 'w' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'G' | 'g' ) ( 'E' | 'e' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:365:14: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:365:16: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'P' | 'p' ) ( 'O' | 'o' ) ( 'S' | 's' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:369:19: ( ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:369:21: ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'U' | 'u' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'O' | 'o' ) ( 'L' | 'l' ) ( 'U' | 'u' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' | 'n' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:375:13: ( ( 'S' | 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:375:15: ( 'S' | 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'R' | 'r' ) ( 'T' | 't' ) ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:381:20: ( ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'E' | 'e' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:381:22: ( 'T' | 't' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'P' | 'p' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'E' | 'e' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:385:17: ( ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'X' | 'x' ) ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:385:19: ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'X' | 'x' ) ( 'B' | 'b' ) ( 'E' | 'e' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' | 'e' ) ( 'T' | 't' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:390:5: ( ( '+' | '-' )? ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ ) ( EXPONENT )? )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:390:7: ( '+' | '-' )? ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ ) ( EXPONENT )?
            {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:390:7: ( '+' | '-' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='+'||LA5_0=='-') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
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


            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:391:5: ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ )
            int alt10=3;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:391:7: ( '0' .. '9' )+
                    {
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:391:7: ( '0' .. '9' )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
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
                    	    if ( cnt6 >= 1 ) break loop6;
                                EarlyExitException eee =
                                    new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:392:7: ( '0' .. '9' )+ '.' ( '0' .. '9' )*
                    {
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:392:7: ( '0' .. '9' )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0 >= '0' && LA7_0 <= '9')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
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
                    	    if ( cnt7 >= 1 ) break loop7;
                                EarlyExitException eee =
                                    new EarlyExitException(7, input);
                                throw eee;
                        }
                        cnt7++;
                    } while (true);


                    match('.'); 

                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:392:23: ( '0' .. '9' )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0 >= '0' && LA8_0 <= '9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
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
                    	    break loop8;
                        }
                    } while (true);


                    }
                    break;
                case 3 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:393:19: '.' ( '0' .. '9' )+
                    {
                    match('.'); 

                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:393:23: ( '0' .. '9' )+
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
                    	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
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


                    }
                    break;

            }


            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:394:7: ( EXPONENT )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='E'||LA11_0=='e') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:394:7: EXPONENT
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:398:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:398:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:398:22: ( '+' | '-' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='+'||LA12_0=='-') ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
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


            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:398:33: ( '0' .. '9' )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0 >= '0' && LA13_0 <= '9')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
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
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:400:5: ( ( '#' | '//' | '!' ) (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:400:9: ( '#' | '//' | '!' ) (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:400:9: ( '#' | '//' | '!' )
            int alt14=3;
            switch ( input.LA(1) ) {
            case '#':
                {
                alt14=1;
                }
                break;
            case '/':
                {
                alt14=2;
                }
                break;
            case '!':
                {
                alt14=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }

            switch (alt14) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:400:10: '#'
                    {
                    match('#'); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:400:16: '//'
                    {
                    match("//"); 



                    }
                    break;
                case 3 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:400:23: '!'
                    {
                    match('!'); 

                    }
                    break;

            }


            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:400:28: (~ ( '\\n' | '\\r' ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0 >= '\u0000' && LA15_0 <= '\t')||(LA15_0 >= '\u000B' && LA15_0 <= '\f')||(LA15_0 >= '\u000E' && LA15_0 <= '\uFFFF')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
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
            	    break loop15;
                }
            } while (true);


            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:400:42: ( '\\r' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='\r') ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:400:42: '\\r'
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:403:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:403:9: ( ' ' | '\\t' | '\\r' | '\\n' )
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:418:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '$' | '-' | '_' )+ )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:418:4: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '$' | '-' | '_' )+
            {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:418:4: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '$' | '-' | '_' )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0=='$'||(LA17_0 >= '-' && LA17_0 <= '.')||(LA17_0 >= '0' && LA17_0 <= '9')||(LA17_0 >= 'A' && LA17_0 <= 'Z')||LA17_0=='_'||(LA17_0 >= 'a' && LA17_0 <= 'z')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
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
        // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:8: ( CRYSTAL | TYPE | DIFFRACTIONDECAYMODEL | DDM | SIMPLE | LINEAR | ABSCOEFCALC | DUMMY | DEFAULT | AVERAGE | RD | PDB | DIMENSION | ANGLEP | ANGLEL | PIXELSPERMICRON | UNITCELL | NUMMONOMERS | NUMRESIDUES | NUMRNA | NUMDNA | PROTEINHEAVYATOMS | ELEMENT | SOLVENTHEAVYCONC | SOLVENTFRACTION | PDBNAME | BEAM | FLUX | FWHM | ENERGY | KEV | FILE | PIXELSIZE | COLLIMATION | RECTANGULAR | CIRCULAR | HORIZONTAL | VERTICAL | WEDGE | EXPOSURETIME | ANGULARRESOLUTION | STARTOFFSET | TRANSLATEPERDEGREE | ROTAXBEAMOFFSET | FLOAT | COMMENT | WS | STRING )
        int alt18=48;
        alt18 = dfa18.predict(input);
        switch (alt18) {
            case 1 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:10: CRYSTAL
                {
                mCRYSTAL(); 


                }
                break;
            case 2 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:18: TYPE
                {
                mTYPE(); 


                }
                break;
            case 3 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:23: DIFFRACTIONDECAYMODEL
                {
                mDIFFRACTIONDECAYMODEL(); 


                }
                break;
            case 4 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:45: DDM
                {
                mDDM(); 


                }
                break;
            case 5 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:49: SIMPLE
                {
                mSIMPLE(); 


                }
                break;
            case 6 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:56: LINEAR
                {
                mLINEAR(); 


                }
                break;
            case 7 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:63: ABSCOEFCALC
                {
                mABSCOEFCALC(); 


                }
                break;
            case 8 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:75: DUMMY
                {
                mDUMMY(); 


                }
                break;
            case 9 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:81: DEFAULT
                {
                mDEFAULT(); 


                }
                break;
            case 10 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:89: AVERAGE
                {
                mAVERAGE(); 


                }
                break;
            case 11 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:97: RD
                {
                mRD(); 


                }
                break;
            case 12 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:100: PDB
                {
                mPDB(); 


                }
                break;
            case 13 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:104: DIMENSION
                {
                mDIMENSION(); 


                }
                break;
            case 14 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:114: ANGLEP
                {
                mANGLEP(); 


                }
                break;
            case 15 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:121: ANGLEL
                {
                mANGLEL(); 


                }
                break;
            case 16 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:128: PIXELSPERMICRON
                {
                mPIXELSPERMICRON(); 


                }
                break;
            case 17 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:144: UNITCELL
                {
                mUNITCELL(); 


                }
                break;
            case 18 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:153: NUMMONOMERS
                {
                mNUMMONOMERS(); 


                }
                break;
            case 19 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:165: NUMRESIDUES
                {
                mNUMRESIDUES(); 


                }
                break;
            case 20 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:177: NUMRNA
                {
                mNUMRNA(); 


                }
                break;
            case 21 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:184: NUMDNA
                {
                mNUMDNA(); 


                }
                break;
            case 22 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:191: PROTEINHEAVYATOMS
                {
                mPROTEINHEAVYATOMS(); 


                }
                break;
            case 23 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:209: ELEMENT
                {
                mELEMENT(); 


                }
                break;
            case 24 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:217: SOLVENTHEAVYCONC
                {
                mSOLVENTHEAVYCONC(); 


                }
                break;
            case 25 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:234: SOLVENTFRACTION
                {
                mSOLVENTFRACTION(); 


                }
                break;
            case 26 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:250: PDBNAME
                {
                mPDBNAME(); 


                }
                break;
            case 27 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:258: BEAM
                {
                mBEAM(); 


                }
                break;
            case 28 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:263: FLUX
                {
                mFLUX(); 


                }
                break;
            case 29 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:268: FWHM
                {
                mFWHM(); 


                }
                break;
            case 30 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:273: ENERGY
                {
                mENERGY(); 


                }
                break;
            case 31 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:280: KEV
                {
                mKEV(); 


                }
                break;
            case 32 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:284: FILE
                {
                mFILE(); 


                }
                break;
            case 33 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:289: PIXELSIZE
                {
                mPIXELSIZE(); 


                }
                break;
            case 34 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:299: COLLIMATION
                {
                mCOLLIMATION(); 


                }
                break;
            case 35 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:311: RECTANGULAR
                {
                mRECTANGULAR(); 


                }
                break;
            case 36 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:323: CIRCULAR
                {
                mCIRCULAR(); 


                }
                break;
            case 37 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:332: HORIZONTAL
                {
                mHORIZONTAL(); 


                }
                break;
            case 38 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:343: VERTICAL
                {
                mVERTICAL(); 


                }
                break;
            case 39 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:352: WEDGE
                {
                mWEDGE(); 


                }
                break;
            case 40 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:358: EXPOSURETIME
                {
                mEXPOSURETIME(); 


                }
                break;
            case 41 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:371: ANGULARRESOLUTION
                {
                mANGULARRESOLUTION(); 


                }
                break;
            case 42 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:389: STARTOFFSET
                {
                mSTARTOFFSET(); 


                }
                break;
            case 43 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:401: TRANSLATEPERDEGREE
                {
                mTRANSLATEPERDEGREE(); 


                }
                break;
            case 44 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:420: ROTAXBEAMOFFSET
                {
                mROTAXBEAMOFFSET(); 


                }
                break;
            case 45 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:436: FLOAT
                {
                mFLOAT(); 


                }
                break;
            case 46 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:442: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 47 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:450: WS
                {
                mWS(); 


                }
                break;
            case 48 :
                // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:1:453: STRING
                {
                mSTRING(); 


                }
                break;

        }

    }


    protected DFA10 dfa10 = new DFA10(this);
    protected DFA18 dfa18 = new DFA18(this);
    static final String DFA10_eotS =
        "\1\uffff\1\3\3\uffff";
    static final String DFA10_eofS =
        "\5\uffff";
    static final String DFA10_minS =
        "\2\56\3\uffff";
    static final String DFA10_maxS =
        "\2\71\3\uffff";
    static final String DFA10_acceptS =
        "\2\uffff\1\3\1\1\1\2";
    static final String DFA10_specialS =
        "\5\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\4\1\uffff\12\1",
            "",
            "",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "391:5: ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ )";
        }
    }
    static final String DFA18_eotS =
        "\1\uffff\22\36\1\31\1\30\1\31\4\uffff\4\36\1\uffff\15\36\1\124\21"+
        "\36\1\31\2\30\10\31\1\161\12\31\1\uffff\2\31\1\u0080\3\31\1\u0084"+
        "\6\31\1\u008d\4\31\2\30\4\31\1\u0095\3\31\1\uffff\12\31\1\124\3"+
        "\31\1\uffff\3\31\1\uffff\4\31\1\u00ae\1\u00af\1\u00b0\1\u00b1\1"+
        "\uffff\7\31\1\uffff\3\31\1\u00bc\24\31\4\uffff\2\31\1\u00d3\7\31"+
        "\1\uffff\1\31\1\u00dc\2\31\1\u00df\1\31\1\u00e1\1\u00e2\4\31\1\u00e7"+
        "\5\31\1\u00ee\1\u00ef\2\31\1\uffff\1\u00f2\6\31\1\u00f9\1\uffff"+
        "\2\31\1\uffff\1\u00fd\2\uffff\4\31\1\uffff\6\31\2\uffff\2\31\1\uffff"+
        "\1\u010a\1\31\1\u010c\3\31\1\uffff\3\31\1\uffff\7\31\1\u011a\3\31"+
        "\1\u011e\1\uffff\1\31\1\uffff\2\31\1\u0123\10\31\1\u012c\1\31\1"+
        "\uffff\3\31\1\uffff\3\31\1\u0123\1\uffff\10\31\1\uffff\3\31\1\u013f"+
        "\1\u0140\4\31\1\u0145\1\31\1\u0147\4\31\1\u014c\1\u014d\2\uffff"+
        "\4\31\1\uffff\1\31\1\uffff\1\31\1\u0154\2\31\2\uffff\6\31\1\uffff"+
        "\15\31\1\u016a\1\31\1\u016c\1\u016d\3\31\1\u0171\1\uffff\1\31\2"+
        "\uffff\3\31\1\uffff\1\u0176\1\u0177\1\u0178\1\31\3\uffff\2\31\1"+
        "\u017c\1\uffff";
    static final String DFA18_eofS =
        "\u017d\uffff";
    static final String DFA18_minS =
        "\1\11\22\44\1\56\1\44\1\60\4\uffff\4\44\1\uffff\37\44\1\53\2\44"+
        "\1\123\1\106\1\114\1\103\1\105\1\116\1\106\1\105\1\44\1\115\1\101"+
        "\1\120\1\126\1\122\1\105\1\103\1\122\1\114\1\62\1\uffff\1\124\1"+
        "\101\1\44\1\122\1\105\1\124\1\44\1\124\1\104\1\115\1\130\1\115\1"+
        "\105\1\44\1\111\1\124\1\107\1\60\2\44\1\124\1\103\1\111\1\125\1"+
        "\44\1\123\1\122\1\116\1\uffff\1\131\1\125\1\114\1\105\1\124\1\101"+
        "\1\117\1\101\1\105\1\114\1\44\1\101\1\130\1\123\1\uffff\1\107\1"+
        "\114\1\105\1\uffff\1\103\1\117\1\105\1\116\4\44\1\uffff\1\132\1"+
        "\111\1\105\2\101\1\115\1\114\1\uffff\1\114\1\101\1\123\1\44\1\114"+
        "\1\105\1\116\1\117\1\122\1\105\1\107\1\114\1\101\1\116\1\102\1\125"+
        "\1\131\1\123\1\111\1\105\1\116\1\123\2\101\4\uffff\1\117\1\103\1"+
        "\44\2\114\3\101\1\103\1\111\1\uffff\1\124\1\44\1\124\1\106\1\44"+
        "\1\105\2\44\1\122\1\107\1\105\1\122\1\44\1\111\1\116\1\114\1\117"+
        "\1\111\2\44\1\116\1\101\1\uffff\1\44\1\103\1\124\1\122\2\124\1\117"+
        "\1\44\1\uffff\2\106\1\uffff\1\44\2\uffff\1\122\1\125\1\101\1\105"+
        "\1\uffff\1\105\1\132\1\110\1\114\1\115\1\104\2\uffff\1\124\1\114"+
        "\1\uffff\1\44\1\111\1\44\1\105\1\111\1\116\1\uffff\1\105\1\122\1"+
        "\123\1\uffff\1\105\1\114\1\115\1\124\1\122\2\105\1\44\1\105\1\125"+
        "\1\101\1\44\1\uffff\1\117\1\uffff\1\120\1\117\1\44\2\101\1\105\1"+
        "\123\1\101\1\117\1\111\1\115\1\44\1\101\1\uffff\1\122\1\105\1\114"+
        "\1\uffff\1\116\1\105\1\116\1\44\1\uffff\1\126\1\103\1\124\1\117"+
        "\1\122\1\106\1\115\1\111\1\uffff\1\126\2\123\2\44\1\122\1\104\1"+
        "\131\1\124\1\44\1\114\1\44\1\106\1\105\1\103\1\131\2\44\2\uffff"+
        "\1\104\1\105\1\103\1\111\1\uffff\1\125\1\uffff\1\123\1\44\1\122"+
        "\1\101\2\uffff\1\105\1\103\2\117\1\124\1\105\1\uffff\1\117\1\124"+
        "\1\107\1\101\2\116\1\111\1\124\1\116\1\117\1\122\1\131\1\103\1\44"+
        "\1\117\2\44\1\115\1\105\1\115\1\44\1\uffff\1\116\2\uffff\1\123\1"+
        "\105\1\117\1\uffff\3\44\1\104\3\uffff\1\105\1\114\1\44\1\uffff";
    static final String DFA18_maxS =
        "\23\172\1\71\1\172\1\71\4\uffff\4\172\1\uffff\37\172\1\71\2\172"+
        "\1\163\1\146\1\154\1\143\1\145\1\156\1\146\1\145\1\172\1\155\1\141"+
        "\1\160\1\166\1\162\1\145\1\143\1\162\1\165\1\63\1\uffff\1\164\1"+
        "\141\1\172\1\162\1\145\1\164\1\172\1\164\1\162\1\155\1\170\1\155"+
        "\1\145\1\172\1\151\1\164\1\147\1\71\2\172\1\164\1\143\1\151\1\165"+
        "\1\172\1\163\1\162\1\156\1\uffff\1\171\1\165\1\154\1\145\1\164\1"+
        "\141\1\157\1\141\1\145\1\154\1\172\1\141\1\170\1\163\1\uffff\1\147"+
        "\1\154\1\145\1\uffff\1\143\1\157\2\156\4\172\1\uffff\1\172\1\151"+
        "\1\145\2\141\1\155\1\154\1\uffff\1\154\1\141\1\163\1\172\1\154\1"+
        "\145\1\156\1\157\1\162\1\145\1\147\1\160\1\141\1\156\1\142\1\165"+
        "\1\171\1\163\1\151\1\145\1\156\1\163\2\141\4\uffff\1\157\1\143\1"+
        "\172\2\154\3\141\1\143\1\151\1\uffff\1\164\1\172\1\164\1\146\1\172"+
        "\1\145\2\172\1\162\1\147\1\145\1\162\1\172\1\160\1\156\1\154\1\157"+
        "\1\151\2\172\1\156\1\141\1\uffff\1\172\1\143\1\164\1\162\2\164\1"+
        "\157\1\172\1\uffff\1\150\1\146\1\uffff\1\172\2\uffff\1\162\1\165"+
        "\1\141\1\145\1\uffff\1\145\1\172\1\150\1\154\1\155\1\144\2\uffff"+
        "\1\164\1\154\1\uffff\1\172\1\151\1\172\1\145\1\151\1\156\1\uffff"+
        "\1\145\1\162\1\163\1\uffff\1\145\1\154\1\155\1\164\1\162\2\145\1"+
        "\172\1\145\1\165\1\141\1\172\1\uffff\1\157\1\uffff\1\160\1\157\1"+
        "\172\2\141\1\145\1\163\1\141\1\157\1\151\1\155\1\172\1\141\1\uffff"+
        "\1\162\1\145\1\154\1\uffff\1\156\1\145\1\156\1\172\1\uffff\1\166"+
        "\1\143\1\164\1\157\1\162\1\146\1\155\1\151\1\uffff\1\166\2\163\2"+
        "\172\1\162\1\144\1\171\1\164\1\172\1\154\1\172\1\146\1\145\1\143"+
        "\1\171\2\172\2\uffff\1\144\1\145\1\143\1\151\1\uffff\1\165\1\uffff"+
        "\1\163\1\172\1\162\1\141\2\uffff\1\145\1\143\2\157\1\164\1\145\1"+
        "\uffff\1\157\1\164\1\147\1\141\2\156\1\151\1\164\1\156\1\157\1\162"+
        "\1\171\1\143\1\172\1\157\2\172\1\155\1\145\1\155\1\172\1\uffff\1"+
        "\156\2\uffff\1\163\1\145\1\157\1\uffff\3\172\1\144\3\uffff\1\145"+
        "\1\154\1\172\1\uffff";
    static final String DFA18_acceptS =
        "\26\uffff\1\56\1\57\1\55\1\60\4\uffff\1\27\65\uffff\1\13\34\uffff"+
        "\1\4\16\uffff\1\14\3\uffff\1\32\10\uffff\1\37\7\uffff\1\2\30\uffff"+
        "\1\33\1\34\1\35\1\40\12\uffff\1\10\26\uffff\1\47\10\uffff\1\5\2"+
        "\uffff\1\6\1\uffff\1\16\1\17\4\uffff\1\36\6\uffff\1\24\1\25\2\uffff"+
        "\1\1\6\uffff\1\11\3\uffff\1\12\14\uffff\1\7\1\uffff\1\44\15\uffff"+
        "\1\21\3\uffff\1\46\4\uffff\1\15\10\uffff\1\41\22\uffff\1\45\1\42"+
        "\4\uffff\1\52\1\uffff\1\43\4\uffff\1\22\1\23\6\uffff\1\50\25\uffff"+
        "\1\31\1\uffff\1\54\1\20\3\uffff\1\30\4\uffff\1\51\1\26\1\53\3\uffff"+
        "\1\3";
    static final String DFA18_specialS =
        "\u017d\uffff}>";
    static final String[] DFA18_transitionS = {
            "\2\27\2\uffff\1\27\22\uffff\1\27\1\26\1\uffff\1\26\1\31\6\uffff"+
            "\1\30\1\uffff\1\23\1\25\1\26\12\24\7\uffff\1\6\1\14\1\1\1\3"+
            "\1\10\1\15\1\22\1\17\2\22\1\16\1\5\1\22\1\13\1\22\1\11\1\22"+
            "\1\7\1\4\1\2\1\12\1\20\1\21\3\22\4\uffff\1\31\1\uffff\1\6\1"+
            "\14\1\1\1\3\1\10\1\15\1\22\1\17\2\22\1\16\1\5\1\22\1\13\1\22"+
            "\1\11\1\22\1\7\1\4\1\2\1\12\1\20\1\21\3\22",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\10\35\1\34\5\35\1"+
            "\33\2\35\1\32\10\35\4\uffff\1\31\1\uffff\10\35\1\34\5\35\1\33"+
            "\2\35\1\32\10\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\21\35\1\40\6\35\1"+
            "\37\1\35\4\uffff\1\31\1\uffff\21\35\1\40\6\35\1\37\1\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\3\35\1\42\1\44\3"+
            "\35\1\41\13\35\1\43\5\35\4\uffff\1\31\1\uffff\3\35\1\42\1\44"+
            "\3\35\1\41\13\35\1\43\5\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\10\35\1\45\5\35\1"+
            "\46\4\35\1\47\6\35\4\uffff\1\31\1\uffff\10\35\1\45\5\35\1\46"+
            "\4\35\1\47\6\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\10\35\1\50\21\35"+
            "\4\uffff\1\31\1\uffff\10\35\1\50\21\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\1\35\1\51\13\35\1"+
            "\53\7\35\1\52\4\35\4\uffff\1\31\1\uffff\1\35\1\51\13\35\1\53"+
            "\7\35\1\52\4\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\3\35\1\54\1\55\11"+
            "\35\1\56\13\35\4\uffff\1\31\1\uffff\3\35\1\54\1\55\11\35\1\56"+
            "\13\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\15\35\1\60\11\35"+
            "\1\57\2\35\4\uffff\1\31\1\uffff\15\35\1\60\11\35\1\57\2\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\3\35\1\63\4\35\1"+
            "\61\10\35\1\62\10\35\4\uffff\1\31\1\uffff\3\35\1\63\4\35\1\61"+
            "\10\35\1\62\10\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\15\35\1\64\14\35"+
            "\4\uffff\1\31\1\uffff\15\35\1\64\14\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\24\35\1\65\5\35\4"+
            "\uffff\1\31\1\uffff\24\35\1\65\5\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\4\35\1\66\25\35\4"+
            "\uffff\1\31\1\uffff\4\35\1\66\25\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\10\35\1\71\2\35\1"+
            "\67\12\35\1\70\3\35\4\uffff\1\31\1\uffff\10\35\1\71\2\35\1\67"+
            "\12\35\1\70\3\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\4\35\1\72\25\35\4"+
            "\uffff\1\31\1\uffff\4\35\1\72\25\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\16\35\1\73\13\35"+
            "\4\uffff\1\31\1\uffff\16\35\1\73\13\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\4\35\1\74\25\35\4"+
            "\uffff\1\31\1\uffff\4\35\1\74\25\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\4\35\1\75\25\35\4"+
            "\uffff\1\31\1\uffff\4\35\1\75\25\35",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\35\4\uffff\1\31"+
            "\1\uffff\32\35",
            "\1\25\1\uffff\12\24",
            "\1\31\10\uffff\1\31\1\77\1\uffff\12\24\7\uffff\4\31\1\76\25"+
            "\31\4\uffff\1\31\1\uffff\4\31\1\76\25\31",
            "\12\100",
            "",
            "",
            "",
            "",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\30\31\1\101\1\31"+
            "\4\uffff\1\31\1\uffff\30\31\1\101\1\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\4\31\1\102\6\31\1"+
            "\103\16\31\4\uffff\1\31\1\uffff\4\31\1\102\6\31\1\103\16\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\21\31\1\104\10\31"+
            "\4\uffff\1\31\1\uffff\21\31\1\104\10\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\17\31\1\105\12\31"+
            "\4\uffff\1\31\1\uffff\17\31\1\105\12\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\1\106\31\31\4\uffff"+
            "\1\31\1\uffff\1\106\31\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\5\31\1\107\6\31\1"+
            "\110\15\31\4\uffff\1\31\1\uffff\5\31\1\107\6\31\1\110\15\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\14\31\1\111\15\31"+
            "\4\uffff\1\31\1\uffff\14\31\1\111\15\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\14\31\1\112\15\31"+
            "\4\uffff\1\31\1\uffff\14\31\1\112\15\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\5\31\1\113\24\31"+
            "\4\uffff\1\31\1\uffff\5\31\1\113\24\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\14\31\1\114\15\31"+
            "\4\uffff\1\31\1\uffff\14\31\1\114\15\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\13\31\1\115\16\31"+
            "\4\uffff\1\31\1\uffff\13\31\1\115\16\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\1\116\31\31\4\uffff"+
            "\1\31\1\uffff\1\116\31\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\15\31\1\117\14\31"+
            "\4\uffff\1\31\1\uffff\15\31\1\117\14\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\22\31\1\120\7\31"+
            "\4\uffff\1\31\1\uffff\22\31\1\120\7\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\4\31\1\121\25\31"+
            "\4\uffff\1\31\1\uffff\4\31\1\121\25\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\6\31\1\122\23\31"+
            "\4\uffff\1\31\1\uffff\6\31\1\122\23\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\25\31\1\123\4\31"+
            "\4\uffff\1\31\1\uffff\25\31\1\123\4\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\2\31\1\125\27\31"+
            "\4\uffff\1\31\1\uffff\2\31\1\125\27\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\23\31\1\126\6\31"+
            "\4\uffff\1\31\1\uffff\23\31\1\126\6\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\17\31\1\127\12\31"+
            "\4\uffff\1\31\1\uffff\17\31\1\127\12\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\4\31\1\130\25\31"+
            "\4\uffff\1\31\1\uffff\4\31\1\130\25\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\27\31\1\131\2\31"+
            "\4\uffff\1\31\1\uffff\27\31\1\131\2\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\16\31\1\132\13\31"+
            "\4\uffff\1\31\1\uffff\16\31\1\132\13\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\1\31\1\133\30\31"+
            "\4\uffff\1\31\1\uffff\1\31\1\133\30\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\10\31\1\134\21\31"+
            "\4\uffff\1\31\1\uffff\10\31\1\134\21\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\14\31\1\135\15\31"+
            "\4\uffff\1\31\1\uffff\14\31\1\135\15\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\1\136\31\31\4\uffff"+
            "\1\31\1\uffff\1\136\31\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\24\31\1\137\5\31"+
            "\4\uffff\1\31\1\uffff\24\31\1\137\5\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\7\31\1\140\22\31"+
            "\4\uffff\1\31\1\uffff\7\31\1\140\22\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\13\31\1\141\16\31"+
            "\4\uffff\1\31\1\uffff\13\31\1\141\16\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\25\31\1\142\4\31"+
            "\4\uffff\1\31\1\uffff\25\31\1\142\4\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\21\31\1\143\10\31"+
            "\4\uffff\1\31\1\uffff\21\31\1\143\10\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\21\31\1\144\10\31"+
            "\4\uffff\1\31\1\uffff\21\31\1\144\10\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\3\31\1\145\26\31"+
            "\4\uffff\1\31\1\uffff\3\31\1\145\26\31",
            "\1\30\1\uffff\1\146\2\uffff\12\147",
            "\1\31\10\uffff\2\31\1\uffff\12\150\7\uffff\4\31\1\76\25\31"+
            "\4\uffff\1\31\1\uffff\4\31\1\76\25\31",
            "\1\31\10\uffff\2\31\1\uffff\12\100\7\uffff\4\31\1\76\25\31"+
            "\4\uffff\1\31\1\uffff\4\31\1\76\25\31",
            "\1\151\37\uffff\1\151",
            "\1\152\37\uffff\1\152",
            "\1\153\37\uffff\1\153",
            "\1\154\37\uffff\1\154",
            "\1\155\37\uffff\1\155",
            "\1\156\37\uffff\1\156",
            "\1\157\37\uffff\1\157",
            "\1\160\37\uffff\1\160",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\162\37\uffff\1\162",
            "\1\163\37\uffff\1\163",
            "\1\164\37\uffff\1\164",
            "\1\165\37\uffff\1\165",
            "\1\166\37\uffff\1\166",
            "\1\167\37\uffff\1\167",
            "\1\170\37\uffff\1\170",
            "\1\171\37\uffff\1\171",
            "\1\172\10\uffff\1\173\26\uffff\1\172\10\uffff\1\173",
            "\2\174",
            "",
            "\1\175\37\uffff\1\175",
            "\1\176\37\uffff\1\176",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\16\31\1\177\13\31"+
            "\4\uffff\1\31\1\uffff\16\31\1\177\13\31",
            "\1\u0081\37\uffff\1\u0081",
            "\1\u0082\37\uffff\1\u0082",
            "\1\u0083\37\uffff\1\u0083",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u0085\37\uffff\1\u0085",
            "\1\u0088\10\uffff\1\u0086\4\uffff\1\u0087\21\uffff\1\u0088"+
            "\10\uffff\1\u0086\4\uffff\1\u0087",
            "\1\u0089\37\uffff\1\u0089",
            "\1\u008a\37\uffff\1\u008a",
            "\1\u008b\37\uffff\1\u008b",
            "\1\u008c\37\uffff\1\u008c",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u008e\37\uffff\1\u008e",
            "\1\u008f\37\uffff\1\u008f",
            "\1\u0090\37\uffff\1\u0090",
            "\12\147",
            "\1\31\10\uffff\2\31\1\uffff\12\147\7\uffff\32\31\4\uffff\1"+
            "\31\1\uffff\32\31",
            "\1\31\10\uffff\2\31\1\uffff\12\150\7\uffff\4\31\1\76\25\31"+
            "\4\uffff\1\31\1\uffff\4\31\1\76\25\31",
            "\1\u0091\37\uffff\1\u0091",
            "\1\u0092\37\uffff\1\u0092",
            "\1\u0093\37\uffff\1\u0093",
            "\1\u0094\37\uffff\1\u0094",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u0096\37\uffff\1\u0096",
            "\1\u0097\37\uffff\1\u0097",
            "\1\u0098\37\uffff\1\u0098",
            "",
            "\1\u0099\37\uffff\1\u0099",
            "\1\u009a\37\uffff\1\u009a",
            "\1\u009b\37\uffff\1\u009b",
            "\1\u009c\37\uffff\1\u009c",
            "\1\u009d\37\uffff\1\u009d",
            "\1\u009e\37\uffff\1\u009e",
            "\1\u009f\37\uffff\1\u009f",
            "\1\u00a0\37\uffff\1\u00a0",
            "\1\u00a1\37\uffff\1\u00a1",
            "\1\u00a2\37\uffff\1\u00a2",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u00a3\37\uffff\1\u00a3",
            "\1\u00a4\37\uffff\1\u00a4",
            "\1\u00a5\37\uffff\1\u00a5",
            "",
            "\1\u00a6\37\uffff\1\u00a6",
            "\1\u00a7\37\uffff\1\u00a7",
            "\1\u00a8\37\uffff\1\u00a8",
            "",
            "\1\u00a9\37\uffff\1\u00a9",
            "\1\u00aa\37\uffff\1\u00aa",
            "\1\u00ab\10\uffff\1\u00ac\26\uffff\1\u00ab\10\uffff\1\u00ac",
            "\1\u00ad\37\uffff\1\u00ad",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "",
            "\1\u00b2\37\uffff\1\u00b2",
            "\1\u00b3\37\uffff\1\u00b3",
            "\1\u00b4\37\uffff\1\u00b4",
            "\1\u00b5\37\uffff\1\u00b5",
            "\1\u00b6\37\uffff\1\u00b6",
            "\1\u00b7\37\uffff\1\u00b7",
            "\1\u00b8\37\uffff\1\u00b8",
            "",
            "\1\u00b9\37\uffff\1\u00b9",
            "\1\u00ba\37\uffff\1\u00ba",
            "\1\u00bb\37\uffff\1\u00bb",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u00bd\37\uffff\1\u00bd",
            "\1\u00be\37\uffff\1\u00be",
            "\1\u00bf\37\uffff\1\u00bf",
            "\1\u00c0\37\uffff\1\u00c0",
            "\1\u00c1\37\uffff\1\u00c1",
            "\1\102\37\uffff\1\102",
            "\1\u00c2\37\uffff\1\u00c2",
            "\1\u00c4\3\uffff\1\u00c3\33\uffff\1\u00c4\3\uffff\1\u00c3",
            "\1\u00c5\37\uffff\1\u00c5",
            "\1\u00c6\37\uffff\1\u00c6",
            "\1\u00c7\37\uffff\1\u00c7",
            "\1\u00c8\37\uffff\1\u00c8",
            "\1\u00c9\37\uffff\1\u00c9",
            "\1\u00ca\37\uffff\1\u00ca",
            "\1\u00cb\37\uffff\1\u00cb",
            "\1\u00cc\37\uffff\1\u00cc",
            "\1\u00cd\37\uffff\1\u00cd",
            "\1\u00ce\37\uffff\1\u00ce",
            "\1\u00cf\37\uffff\1\u00cf",
            "\1\u00d0\37\uffff\1\u00d0",
            "",
            "",
            "",
            "",
            "\1\u00d1\37\uffff\1\u00d1",
            "\1\u00d2\37\uffff\1\u00d2",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u00d4\37\uffff\1\u00d4",
            "\1\u00d5\37\uffff\1\u00d5",
            "\1\u00d6\37\uffff\1\u00d6",
            "\1\u00d7\37\uffff\1\u00d7",
            "\1\u00d8\37\uffff\1\u00d8",
            "\1\u00d9\37\uffff\1\u00d9",
            "\1\u00da\37\uffff\1\u00da",
            "",
            "\1\u00db\37\uffff\1\u00db",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u00dd\37\uffff\1\u00dd",
            "\1\u00de\37\uffff\1\u00de",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u00e0\37\uffff\1\u00e0",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u00e3\37\uffff\1\u00e3",
            "\1\u00e4\37\uffff\1\u00e4",
            "\1\u00e5\37\uffff\1\u00e5",
            "\1\u00e6\37\uffff\1\u00e6",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u00e9\6\uffff\1\u00e8\30\uffff\1\u00e9\6\uffff\1\u00e8",
            "\1\u00ea\37\uffff\1\u00ea",
            "\1\u00eb\37\uffff\1\u00eb",
            "\1\u00ec\37\uffff\1\u00ec",
            "\1\u00ed\37\uffff\1\u00ed",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u00f0\37\uffff\1\u00f0",
            "\1\u00f1\37\uffff\1\u00f1",
            "",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u00f3\37\uffff\1\u00f3",
            "\1\u00f4\37\uffff\1\u00f4",
            "\1\u00f5\37\uffff\1\u00f5",
            "\1\u00f6\37\uffff\1\u00f6",
            "\1\u00f7\37\uffff\1\u00f7",
            "\1\u00f8\37\uffff\1\u00f8",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "",
            "\1\u00fb\1\uffff\1\u00fa\35\uffff\1\u00fb\1\uffff\1\u00fa",
            "\1\u00fc\37\uffff\1\u00fc",
            "",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "",
            "",
            "\1\u00fe\37\uffff\1\u00fe",
            "\1\u00ff\37\uffff\1\u00ff",
            "\1\u0100\37\uffff\1\u0100",
            "\1\u0101\37\uffff\1\u0101",
            "",
            "\1\u0102\37\uffff\1\u0102",
            "\1\u0103\37\uffff\1\u0103",
            "\1\u0104\37\uffff\1\u0104",
            "\1\u0105\37\uffff\1\u0105",
            "\1\u0106\37\uffff\1\u0106",
            "\1\u0107\37\uffff\1\u0107",
            "",
            "",
            "\1\u0108\37\uffff\1\u0108",
            "\1\u0109\37\uffff\1\u0109",
            "",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u010b\37\uffff\1\u010b",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u010d\37\uffff\1\u010d",
            "\1\u010e\37\uffff\1\u010e",
            "\1\u010f\37\uffff\1\u010f",
            "",
            "\1\u0110\37\uffff\1\u0110",
            "\1\u0111\37\uffff\1\u0111",
            "\1\u0112\37\uffff\1\u0112",
            "",
            "\1\u0113\37\uffff\1\u0113",
            "\1\u0114\37\uffff\1\u0114",
            "\1\u0115\37\uffff\1\u0115",
            "\1\u0116\37\uffff\1\u0116",
            "\1\u0117\37\uffff\1\u0117",
            "\1\u0118\37\uffff\1\u0118",
            "\1\u0119\37\uffff\1\u0119",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u011b\37\uffff\1\u011b",
            "\1\u011c\37\uffff\1\u011c",
            "\1\u011d\37\uffff\1\u011d",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "",
            "\1\u011f\37\uffff\1\u011f",
            "",
            "\1\u0120\37\uffff\1\u0120",
            "\1\u0121\37\uffff\1\u0121",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\22\31\1\u0122\7\31"+
            "\4\uffff\1\31\1\uffff\22\31\1\u0122\7\31",
            "\1\u0124\37\uffff\1\u0124",
            "\1\u0125\37\uffff\1\u0125",
            "\1\u0126\37\uffff\1\u0126",
            "\1\u0127\37\uffff\1\u0127",
            "\1\u0128\37\uffff\1\u0128",
            "\1\u0129\37\uffff\1\u0129",
            "\1\u012a\37\uffff\1\u012a",
            "\1\u012b\37\uffff\1\u012b",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u012d\37\uffff\1\u012d",
            "",
            "\1\u012e\37\uffff\1\u012e",
            "\1\u012f\37\uffff\1\u012f",
            "\1\u0130\37\uffff\1\u0130",
            "",
            "\1\u0131\37\uffff\1\u0131",
            "\1\u0132\37\uffff\1\u0132",
            "\1\u0133\37\uffff\1\u0133",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "",
            "\1\u0134\37\uffff\1\u0134",
            "\1\u0135\37\uffff\1\u0135",
            "\1\u0136\37\uffff\1\u0136",
            "\1\u0137\37\uffff\1\u0137",
            "\1\u0138\37\uffff\1\u0138",
            "\1\u0139\37\uffff\1\u0139",
            "\1\u013a\37\uffff\1\u013a",
            "\1\u013b\37\uffff\1\u013b",
            "",
            "\1\u013c\37\uffff\1\u013c",
            "\1\u013d\37\uffff\1\u013d",
            "\1\u013e\37\uffff\1\u013e",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u0141\37\uffff\1\u0141",
            "\1\u0142\37\uffff\1\u0142",
            "\1\u0143\37\uffff\1\u0143",
            "\1\u0144\37\uffff\1\u0144",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u0146\37\uffff\1\u0146",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u0148\37\uffff\1\u0148",
            "\1\u0149\37\uffff\1\u0149",
            "\1\u014a\37\uffff\1\u014a",
            "\1\u014b\37\uffff\1\u014b",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "",
            "",
            "\1\u014e\37\uffff\1\u014e",
            "\1\u014f\37\uffff\1\u014f",
            "\1\u0150\37\uffff\1\u0150",
            "\1\u0151\37\uffff\1\u0151",
            "",
            "\1\u0152\37\uffff\1\u0152",
            "",
            "\1\u0153\37\uffff\1\u0153",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u0155\37\uffff\1\u0155",
            "\1\u0156\37\uffff\1\u0156",
            "",
            "",
            "\1\u0157\37\uffff\1\u0157",
            "\1\u0158\37\uffff\1\u0158",
            "\1\u0159\37\uffff\1\u0159",
            "\1\u015a\37\uffff\1\u015a",
            "\1\u015b\37\uffff\1\u015b",
            "\1\u015c\37\uffff\1\u015c",
            "",
            "\1\u015d\37\uffff\1\u015d",
            "\1\u015e\37\uffff\1\u015e",
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
            "\1\u0169\37\uffff\1\u0169",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u016b\37\uffff\1\u016b",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u016e\37\uffff\1\u016e",
            "\1\u016f\37\uffff\1\u016f",
            "\1\u0170\37\uffff\1\u0170",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "",
            "\1\u0172\37\uffff\1\u0172",
            "",
            "",
            "\1\u0173\37\uffff\1\u0173",
            "\1\u0174\37\uffff\1\u0174",
            "\1\u0175\37\uffff\1\u0175",
            "",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            "\1\u0179\37\uffff\1\u0179",
            "",
            "",
            "",
            "\1\u017a\37\uffff\1\u017a",
            "\1\u017b\37\uffff\1\u017b",
            "\1\31\10\uffff\2\31\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31"+
            "\1\uffff\32\31",
            ""
    };

    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;

    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( CRYSTAL | TYPE | DIFFRACTIONDECAYMODEL | DDM | SIMPLE | LINEAR | ABSCOEFCALC | DUMMY | DEFAULT | AVERAGE | RD | PDB | DIMENSION | ANGLEP | ANGLEL | PIXELSPERMICRON | UNITCELL | NUMMONOMERS | NUMRESIDUES | NUMRNA | NUMDNA | PROTEINHEAVYATOMS | ELEMENT | SOLVENTHEAVYCONC | SOLVENTFRACTION | PDBNAME | BEAM | FLUX | FWHM | ENERGY | KEV | FILE | PIXELSIZE | COLLIMATION | RECTANGULAR | CIRCULAR | HORIZONTAL | VERTICAL | WEDGE | EXPOSURETIME | ANGULARRESOLUTION | STARTOFFSET | TRANSLATEPERDEGREE | ROTAXBEAMOFFSET | FLOAT | COMMENT | WS | STRING );";
        }
    }
 

}