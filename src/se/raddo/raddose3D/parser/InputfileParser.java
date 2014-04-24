// $ANTLR 3.4 C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g 2013-05-30 12:48:52

package se.raddo.raddose3D.parser;
import se.raddo.raddose3D.*;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/** "Here's an initializer, here's an input file. Good luck and God's Speed." */
@SuppressWarnings({"all", "warnings", "unchecked"})
public class InputfileParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABSCOEFCALC", "ANGLEL", "ANGLEP", "ANGULARRESOLUTION", "AVERAGE", "BEAM", "CIRCULAR", "COLLIMATION", "COMMENT", "CRYSTAL", "DDM", "DIFFRACTIONDECAYMODEL", "DIMENSION", "DUMMY", "ELEMENT", "ENERGY", "EXPONENT", "EXPOSURETIME", "FILE", "FLOAT", "FLUX", "FWHM", "HORIZONTAL", "KEV", "LINEAR", "NUMDNA", "NUMMONOMERS", "NUMRESIDUES", "NUMRNA", "PIXELSIZE", "PIXELSPERMICRON", "PROTEINHEAVYATOMS", "RD", "RECTANGULAR", "ROTAXBEAMOFFSET", "SIMPLE", "SOLVENTFRACTION", "SOLVENTHEAVYCONC", "STARTOFFSET", "STRING", "TRANSLATEPERDEGREE", "TYPE", "UNITCELL", "VERTICAL", "WEDGE", "WS"
    };

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
    public static final int DIFFRACTIONDECAYMODEL=15;
    public static final int DIMENSION=16;
    public static final int DUMMY=17;
    public static final int ELEMENT=18;
    public static final int ENERGY=19;
    public static final int EXPONENT=20;
    public static final int EXPOSURETIME=21;
    public static final int FILE=22;
    public static final int FLOAT=23;
    public static final int FLUX=24;
    public static final int FWHM=25;
    public static final int HORIZONTAL=26;
    public static final int KEV=27;
    public static final int LINEAR=28;
    public static final int NUMDNA=29;
    public static final int NUMMONOMERS=30;
    public static final int NUMRESIDUES=31;
    public static final int NUMRNA=32;
    public static final int PIXELSIZE=33;
    public static final int PIXELSPERMICRON=34;
    public static final int PROTEINHEAVYATOMS=35;
    public static final int RD=36;
    public static final int RECTANGULAR=37;
    public static final int ROTAXBEAMOFFSET=38;
    public static final int SIMPLE=39;
    public static final int SOLVENTFRACTION=40;
    public static final int SOLVENTHEAVYCONC=41;
    public static final int STARTOFFSET=42;
    public static final int STRING=43;
    public static final int TRANSLATEPERDEGREE=44;
    public static final int TYPE=45;
    public static final int UNITCELL=46;
    public static final int VERTICAL=47;
    public static final int WEDGE=48;
    public static final int WS=49;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public InputfileParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public InputfileParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return InputfileParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g"; }


        private BeamFactory beamFactory = null;
        private CrystalFactory crystalFactory = null;
        private Initializer raddoseInitializer = null;
        private Vector<String> parsingErrors = new Vector<String>();

        public void setInitializer(Initializer i) {
            this.raddoseInitializer = i;
        }
        public void setBeamFactory(BeamFactory bf) {
            this.beamFactory = bf;
        }
        public void setCrystalFactory(CrystalFactory cf) {
            this.crystalFactory = cf;
        }
        public Vector<String> getErrors() {
            Vector<String> fetchedErrors = parsingErrors;
            parsingErrors = new Vector<String>();
            return fetchedErrors;
        }
        public void emitErrorMessage(String msg) {
            parsingErrors.add(msg);
        }



    // $ANTLR start "configfile"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:41:1: configfile : (a= crystal |b= wedge |c= beam )* EOF ;
    public final void configfile() throws RecognitionException {
        Crystal a =null;

        Wedge b =null;

        Beam c =null;


        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:41:11: ( (a= crystal |b= wedge |c= beam )* EOF )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )* EOF
            {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )*
            loop1:
            do {
                int alt1=4;
                switch ( input.LA(1) ) {
                case CRYSTAL:
                    {
                    alt1=1;
                    }
                    break;
                case WEDGE:
                    {
                    alt1=2;
                    }
                    break;
                case BEAM:
                    {
                    alt1=3;
                    }
                    break;

                }

                switch (alt1) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:42:13: a= crystal
                    {
                    pushFollow(FOLLOW_crystal_in_configfile47);
                    a=crystal();

                    state._fsp--;


                     raddoseInitializer.setCrystal(a); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:43:13: b= wedge
                    {
                    pushFollow(FOLLOW_wedge_in_configfile65);
                    b=wedge();

                    state._fsp--;


                     raddoseInitializer.exposeWedge(b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:44:13: c= beam
                    {
                    pushFollow(FOLLOW_beam_in_configfile85);
                    c=beam();

                    state._fsp--;


                     raddoseInitializer.setBeam(c); 

                    }
                    break;

                default :
                    break loop1;
                }
            } while (true);


            match(input,EOF,FOLLOW_EOF_in_configfile105); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "configfile"


    protected static class crystal_scope {
        String          crystalType;
        int             crystalCoefCalc;
        CoefCalc        crystalCoefCalcClass;
        Double          cellA;
        Double          cellB;
        Double          cellC;
        Double          cellAl;
        Double          cellBe;
        Double          cellGa;
        int             numMon;
        int             numRes;
        int             numRNA;
        int             numDNA;
        List<String>    heavyProteinAtomNames;
        List<Double>    heavyProteinAtomNums;
        List<String>    heavySolutionConcNames;
        List<Double>    heavySolutionConcNums;
        Double          solFrac;
        HashMap<Object, Object> crystalProperties;
    }
    protected Stack crystal_stack = new Stack();



    // $ANTLR start "crystal"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:49:1: crystal returns [Crystal cObj] : CRYSTAL ( crystalLine )+ ;
    public final Crystal crystal() throws RecognitionException {
        crystal_stack.push(new crystal_scope());
        Crystal cObj = null;


         
        ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = 1; // 0 = error, 1 = Simple, 2 = RDV2
                ((crystal_scope)crystal_stack.peek()).crystalProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:91:2: ( CRYSTAL ( crystalLine )+ )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:91:4: CRYSTAL ( crystalLine )+
            {
            match(input,CRYSTAL,FOLLOW_CRYSTAL_in_crystal134); 

            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:91:12: ( crystalLine )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= ABSCOEFCALC && LA2_0 <= ANGLEP)||(LA2_0 >= DDM && LA2_0 <= DIMENSION)||(LA2_0 >= NUMDNA && LA2_0 <= NUMRNA)||(LA2_0 >= PIXELSPERMICRON && LA2_0 <= PROTEINHEAVYATOMS)||(LA2_0 >= SOLVENTFRACTION && LA2_0 <= SOLVENTHEAVYCONC)||(LA2_0 >= TYPE && LA2_0 <= UNITCELL)) ) {
                    alt2=1;
                }


                switch (alt2) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:91:12: crystalLine
                    {
                    pushFollow(FOLLOW_crystalLine_in_crystal136);
                    crystalLine();

                    state._fsp--;


                    }
                    break;

                default :
                    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

             
            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 1) {
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcAverage();
            }
            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 2) {
        /*      CoefCalcCompute computeClass = new CoefCalcCompute(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
                  ((crystal_scope)crystal_stack.peek()).numMon, ((crystal_scope)crystal_stack.peek()).numRes, ((crystal_scope)crystal_stack.peek()).numRNA, ((crystal_scope)crystal_stack.peek()).numDNA,
                  ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
                  ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
                  ((crystal_scope)crystal_stack.peek()).solFrac);
              */
              
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcCompute(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
                                                                ((crystal_scope)crystal_stack.peek()).numMon, ((crystal_scope)crystal_stack.peek()).numRes, ((crystal_scope)crystal_stack.peek()).numRNA, ((crystal_scope)crystal_stack.peek()).numDNA,
                                                                ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
                                                                ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
                                                                ((crystal_scope)crystal_stack.peek()).solFrac);
            }

            ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_COEFCALC, ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass);

            cObj = crystalFactory.createCrystal(((crystal_scope)crystal_stack.peek()).crystalType, ((crystal_scope)crystal_stack.peek()).crystalProperties);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
            crystal_stack.pop();
        }
        return cObj;
    }
    // $ANTLR end "crystal"



    // $ANTLR start "crystalLine"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:94:1: crystalLine : (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction );
    public final void crystalLine() throws RecognitionException {
        String a =null;

        DDM b =null;

        int c =0;

        Map<Object, Object> d =null;

        double e =0.0;

        double f =0.0;

        double g =0.0;

        InputfileParser.unitcell_return m =null;

        int n =0;

        int o =0;

        int p =0;

        int q =0;

        InputfileParser.heavyProteinAtoms_return r =null;

        InputfileParser.heavySolutionConc_return s =null;

        double t =0.0;


        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:95:2: (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction )
            int alt3=15;
            switch ( input.LA(1) ) {
            case TYPE:
                {
                alt3=1;
                }
                break;
            case DDM:
            case DIFFRACTIONDECAYMODEL:
                {
                alt3=2;
                }
                break;
            case ABSCOEFCALC:
                {
                alt3=3;
                }
                break;
            case DIMENSION:
                {
                alt3=4;
                }
                break;
            case PIXELSPERMICRON:
                {
                alt3=5;
                }
                break;
            case ANGLEP:
                {
                alt3=6;
                }
                break;
            case ANGLEL:
                {
                alt3=7;
                }
                break;
            case UNITCELL:
                {
                alt3=8;
                }
                break;
            case NUMMONOMERS:
                {
                alt3=9;
                }
                break;
            case NUMRESIDUES:
                {
                alt3=10;
                }
                break;
            case NUMRNA:
                {
                alt3=11;
                }
                break;
            case NUMDNA:
                {
                alt3=12;
                }
                break;
            case PROTEINHEAVYATOMS:
                {
                alt3=13;
                }
                break;
            case SOLVENTHEAVYCONC:
                {
                alt3=14;
                }
                break;
            case SOLVENTFRACTION:
                {
                alt3=15;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:95:4: a= crystalType
                    {
                    pushFollow(FOLLOW_crystalType_in_crystalLine192);
                    a=crystalType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalType = a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:96:4: b= crystalDDM
                    {
                    pushFollow(FOLLOW_crystalDDM_in_crystalLine203);
                    b=crystalDDM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_DDM, b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:97:4: c= crystalCoefcalc
                    {
                    pushFollow(FOLLOW_crystalCoefcalc_in_crystalLine215);
                    c=crystalCoefcalc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = c; 

                    }
                    break;
                case 4 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:98:4: d= crystalDim
                    {
                    pushFollow(FOLLOW_crystalDim_in_crystalLine225);
                    d=crystalDim();

                    state._fsp--;


                     if (d != null) {
                                                   ((crystal_scope)crystal_stack.peek()).crystalProperties.putAll(d);
                                                  }; 

                    }
                    break;
                case 5 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:101:4: e= crystalPPM
                    {
                    pushFollow(FOLLOW_crystalPPM_in_crystalLine236);
                    e=crystalPPM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, e); 

                    }
                    break;
                case 6 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:102:4: f= crystalAngP
                    {
                    pushFollow(FOLLOW_crystalAngP_in_crystalLine247);
                    f=crystalAngP();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:103:4: g= crystalAngL
                    {
                    pushFollow(FOLLOW_crystalAngL_in_crystalLine258);
                    g=crystalAngL();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, g); 

                    }
                    break;
                case 8 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:104:4: m= unitcell
                    {
                    pushFollow(FOLLOW_unitcell_in_crystalLine269);
                    m=unitcell();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).cellA = (m!=null?m.dimA:null); 
                                                  ((crystal_scope)crystal_stack.peek()).cellB = (m!=null?m.dimB:null);  
                                                  ((crystal_scope)crystal_stack.peek()).cellC = (m!=null?m.dimC:null);  
                                                  ((crystal_scope)crystal_stack.peek()).cellAl = (m!=null?m.angA:null);
                                                  ((crystal_scope)crystal_stack.peek()).cellBe = (m!=null?m.angB:null);     
                                                  ((crystal_scope)crystal_stack.peek()).cellGa = (m!=null?m.angC:null); 

                    }
                    break;
                case 9 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:110:4: n= nummonomers
                    {
                    pushFollow(FOLLOW_nummonomers_in_crystalLine280);
                    n=nummonomers();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numMon = n;  

                    }
                    break;
                case 10 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:111:4: o= numresidues
                    {
                    pushFollow(FOLLOW_numresidues_in_crystalLine291);
                    o=numresidues();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRes = o;  

                    }
                    break;
                case 11 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:112:4: p= numRNA
                    {
                    pushFollow(FOLLOW_numRNA_in_crystalLine302);
                    p=numRNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRNA = p;  

                    }
                    break;
                case 12 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:113:4: q= numDNA
                    {
                    pushFollow(FOLLOW_numDNA_in_crystalLine315);
                    q=numDNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numDNA = q;  

                    }
                    break;
                case 13 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:114:4: r= heavyProteinAtoms
                    {
                    pushFollow(FOLLOW_heavyProteinAtoms_in_crystalLine328);
                    r=heavyProteinAtoms();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames = (r!=null?r.names:null);
                                                  ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums = (r!=null?r.num:null);    

                    }
                    break;
                case 14 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:116:4: s= heavySolutionConc
                    {
                    pushFollow(FOLLOW_heavySolutionConc_in_crystalLine337);
                    s=heavySolutionConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames = (s!=null?s.names:null);
                                                  ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums = (s!=null?s.num:null);   

                    }
                    break;
                case 15 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:118:4: t= solventFraction
                    {
                    pushFollow(FOLLOW_solventFraction_in_crystalLine346);
                    t=solventFraction();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).solFrac = t; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "crystalLine"



    // $ANTLR start "crystalType"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:122:1: crystalType returns [String crystalType] : TYPE e= STRING ;
    public final String crystalType() throws RecognitionException {
        String crystalType = null;


        Token e=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:123:2: ( TYPE e= STRING )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:123:4: TYPE e= STRING
            {
            match(input,TYPE,FOLLOW_TYPE_in_crystalType366); 

            e=(Token)match(input,STRING,FOLLOW_STRING_in_crystalType370); 

            crystalType = (e!=null?e.getText():null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return crystalType;
    }
    // $ANTLR end "crystalType"



    // $ANTLR start "crystalDDM"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:126:1: crystalDDM returns [DDM value] : ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword ;
    public final DDM crystalDDM() throws RecognitionException {
        DDM value = null;


        DDM e =null;


        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:127:2: ( ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:127:4: ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword
            {
            if ( (input.LA(1) >= DDM && input.LA(1) <= DIFFRACTIONDECAYMODEL) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalDDMKeyword_in_crystalDDM424);
            e=crystalDDMKeyword();

            state._fsp--;


             value = e; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "crystalDDM"



    // $ANTLR start "crystalDDMKeyword"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:130:1: crystalDDMKeyword returns [DDM value] : ( SIMPLE | LINEAR );
    public final DDM crystalDDMKeyword() throws RecognitionException {
        DDM value = null;


        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:131:2: ( SIMPLE | LINEAR )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==SIMPLE) ) {
                alt4=1;
            }
            else if ( (LA4_0==LINEAR) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:131:4: SIMPLE
                    {
                    match(input,SIMPLE,FOLLOW_SIMPLE_in_crystalDDMKeyword572); 

                     value = new DDMSimple(); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:132:4: LINEAR
                    {
                    match(input,LINEAR,FOLLOW_LINEAR_in_crystalDDMKeyword579); 

                     value = new DDMLinear(); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "crystalDDMKeyword"



    // $ANTLR start "crystalCoefcalc"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:137:1: crystalCoefcalc returns [int value] : ABSCOEFCALC a= crystalCoefcalcKeyword ;
    public final int crystalCoefcalc() throws RecognitionException {
        int value = 0;


        int a =0;


        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:138:2: ( ABSCOEFCALC a= crystalCoefcalcKeyword )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:138:4: ABSCOEFCALC a= crystalCoefcalcKeyword
            {
            match(input,ABSCOEFCALC,FOLLOW_ABSCOEFCALC_in_crystalCoefcalc670); 

            pushFollow(FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc674);
            a=crystalCoefcalcKeyword();

            state._fsp--;


             value = a; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "crystalCoefcalc"



    // $ANTLR start "crystalCoefcalcKeyword"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:140:1: crystalCoefcalcKeyword returns [int value] : ( DUMMY | AVERAGE | RD );
    public final int crystalCoefcalcKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:141:2: ( DUMMY | AVERAGE | RD )
            int alt5=3;
            switch ( input.LA(1) ) {
            case DUMMY:
                {
                alt5=1;
                }
                break;
            case AVERAGE:
                {
                alt5=2;
                }
                break;
            case RD:
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:141:4: DUMMY
                    {
                    match(input,DUMMY,FOLLOW_DUMMY_in_crystalCoefcalcKeyword753); 

                     value = 1;

                    }
                    break;
                case 2 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:142:4: AVERAGE
                    {
                    match(input,AVERAGE,FOLLOW_AVERAGE_in_crystalCoefcalcKeyword762); 

                     value = 1;

                    }
                    break;
                case 3 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:143:4: RD
                    {
                    match(input,RD,FOLLOW_RD_in_crystalCoefcalcKeyword769); 

                     value = 2;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "crystalCoefcalcKeyword"



    // $ANTLR start "crystalDim"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:149:1: crystalDim returns [Map<Object, Object> properties] : DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT ) ;
    public final Map<Object, Object> crystalDim() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token c=null;
        Token d=null;

         
                properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:152:3: ( DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT ) )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:152:5: DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT )
            {
            match(input,DIMENSION,FOLLOW_DIMENSION_in_crystalDim899); 

            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:153:2: (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==FLOAT) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==FLOAT) ) {
                    alt6=1;
                }
                else if ( (LA6_1==EOF||(LA6_1 >= ABSCOEFCALC && LA6_1 <= ANGLEP)||LA6_1==BEAM||(LA6_1 >= CRYSTAL && LA6_1 <= DIMENSION)||(LA6_1 >= NUMDNA && LA6_1 <= NUMRNA)||(LA6_1 >= PIXELSPERMICRON && LA6_1 <= PROTEINHEAVYATOMS)||(LA6_1 >= SOLVENTFRACTION && LA6_1 <= SOLVENTHEAVYCONC)||(LA6_1 >= TYPE && LA6_1 <= UNITCELL)||LA6_1==WEDGE) ) {
                    alt6=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:154:7: a= FLOAT b= FLOAT c= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim912); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim916); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim920); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((a!=null?a.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((b!=null?b.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble((c!=null?c.getText():null))); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:157:7: d= FLOAT
                    {
                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim933); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((d!=null?d.getText():null))); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return properties;
    }
    // $ANTLR end "crystalDim"



    // $ANTLR start "crystalAngP"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:161:1: crystalAngP returns [double value] : ANGLEP a= FLOAT ;
    public final double crystalAngP() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:162:2: ( ANGLEP a= FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:162:4: ANGLEP a= FLOAT
            {
            match(input,ANGLEP,FOLLOW_ANGLEP_in_crystalAngP1010); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngP1014); 

            value = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "crystalAngP"



    // $ANTLR start "crystalAngL"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:166:1: crystalAngL returns [double value] : ANGLEL a= FLOAT ;
    public final double crystalAngL() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:167:2: ( ANGLEL a= FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:167:4: ANGLEL a= FLOAT
            {
            match(input,ANGLEL,FOLLOW_ANGLEL_in_crystalAngL1069); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngL1073); 

            value = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "crystalAngL"



    // $ANTLR start "crystalPPM"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:171:1: crystalPPM returns [double ppm] : PIXELSPERMICRON FLOAT ;
    public final double crystalPPM() throws RecognitionException {
        double ppm = 0.0;


        Token FLOAT1=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:172:2: ( PIXELSPERMICRON FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:172:4: PIXELSPERMICRON FLOAT
            {
            match(input,PIXELSPERMICRON,FOLLOW_PIXELSPERMICRON_in_crystalPPM1127); 

            FLOAT1=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalPPM1129); 

            ppm = Double.parseDouble((FLOAT1!=null?FLOAT1.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return ppm;
    }
    // $ANTLR end "crystalPPM"


    public static class unitcell_return extends ParserRuleReturnScope {
        public Double dimA;
        public Double dimB;
        public Double dimC;
        public Double angA;
        public Double angB;
        public Double angC;
    };


    // $ANTLR start "unitcell"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:175:1: unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC] : UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? ;
    public final InputfileParser.unitcell_return unitcell() throws RecognitionException {
        InputfileParser.unitcell_return retval = new InputfileParser.unitcell_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;
        Token al=null;
        Token be=null;
        Token ga=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:176:2: ( UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:176:4: UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )?
            {
            match(input,UNITCELL,FOLLOW_UNITCELL_in_unitcell1227); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1231); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1235); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1239); 

            retval.dimA = Double.parseDouble((a!=null?a.getText():null));
                     retval.dimB = Double.parseDouble((b!=null?b.getText():null));
                     retval.dimC = Double.parseDouble((c!=null?c.getText():null)); 

            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:180:7: (al= FLOAT be= FLOAT ga= FLOAT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FLOAT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:180:8: al= FLOAT be= FLOAT ga= FLOAT
                    {
                    al=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1254); 

                    be=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1258); 

                    ga=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1262); 

                    retval.angA = Double.parseDouble((al!=null?al.getText():null));
                            retval.angB = Double.parseDouble((be!=null?be.getText():null));
                            retval.angC = Double.parseDouble((ga!=null?ga.getText():null)); 

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unitcell"



    // $ANTLR start "nummonomers"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:188:1: nummonomers returns [int value] : NUMMONOMERS a= FLOAT ;
    public final int nummonomers() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:189:2: ( NUMMONOMERS a= FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:189:4: NUMMONOMERS a= FLOAT
            {
            match(input,NUMMONOMERS,FOLLOW_NUMMONOMERS_in_nummonomers1340); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_nummonomers1344); 

            value = Integer.parseInt((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "nummonomers"



    // $ANTLR start "numresidues"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:192:1: numresidues returns [int value] : NUMRESIDUES a= FLOAT ;
    public final int numresidues() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:193:2: ( NUMRESIDUES a= FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:193:4: NUMRESIDUES a= FLOAT
            {
            match(input,NUMRESIDUES,FOLLOW_NUMRESIDUES_in_numresidues1421); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numresidues1425); 

            value = Integer.parseInt((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "numresidues"



    // $ANTLR start "numRNA"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:196:1: numRNA returns [int value] : NUMRNA a= FLOAT ;
    public final int numRNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:197:2: ( NUMRNA a= FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:197:4: NUMRNA a= FLOAT
            {
            match(input,NUMRNA,FOLLOW_NUMRNA_in_numRNA1503); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numRNA1507); 

            value = Integer.parseInt((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "numRNA"



    // $ANTLR start "numDNA"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:200:1: numDNA returns [int value] : NUMDNA a= FLOAT ;
    public final int numDNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:201:2: ( NUMDNA a= FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:201:4: NUMDNA a= FLOAT
            {
            match(input,NUMDNA,FOLLOW_NUMDNA_in_numDNA1560); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numDNA1564); 

            value = Integer.parseInt((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "numDNA"


    public static class heavyProteinAtoms_return extends ParserRuleReturnScope {
        public List<String> names;
        public List<Double> num;;
    };


    // $ANTLR start "heavyProteinAtoms"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:204:1: heavyProteinAtoms returns [List<String> names, List<Double> num;] : PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavyProteinAtoms_return heavyProteinAtoms() throws RecognitionException {
        InputfileParser.heavyProteinAtoms_return retval = new InputfileParser.heavyProteinAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:209:2: ( PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:209:4: PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,PROTEINHEAVYATOMS,FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms1620); 

            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:209:22: (a= ELEMENT b= FLOAT )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==ELEMENT) ) {
                    alt8=1;
                }


                switch (alt8) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:209:23: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavyProteinAtoms1625); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavyProteinAtoms1629); 

                    retval.names.add((a!=null?a.getText():null)); retval.num.add(Double.parseDouble((b!=null?b.getText():null))); 

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

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "heavyProteinAtoms"


    public static class heavySolutionConc_return extends ParserRuleReturnScope {
        public List<String> names;
        public List<Double> num;;
    };


    // $ANTLR start "heavySolutionConc"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:213:1: heavySolutionConc returns [List<String> names, List<Double> num;] : SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavySolutionConc_return heavySolutionConc() throws RecognitionException {
        InputfileParser.heavySolutionConc_return retval = new InputfileParser.heavySolutionConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:218:2: ( SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:218:4: SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SOLVENTHEAVYCONC,FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc1776); 

            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:218:21: (a= ELEMENT b= FLOAT )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==ELEMENT) ) {
                    alt9=1;
                }


                switch (alt9) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:218:22: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavySolutionConc1781); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavySolutionConc1785); 

                    retval.names.add((a!=null?a.getText():null)); retval.num.add(Double.parseDouble((b!=null?b.getText():null))); 

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

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "heavySolutionConc"



    // $ANTLR start "solventFraction"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:221:1: solventFraction returns [double solFrac] : SOLVENTFRACTION a= FLOAT ;
    public final double solventFraction() throws RecognitionException {
        double solFrac = 0.0;


        Token a=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:222:2: ( SOLVENTFRACTION a= FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:222:4: SOLVENTFRACTION a= FLOAT
            {
            match(input,SOLVENTFRACTION,FOLLOW_SOLVENTFRACTION_in_solventFraction1891); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_solventFraction1895); 

            solFrac = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return solFrac;
    }
    // $ANTLR end "solventFraction"


    protected static class beam_scope {
        String beamType;
        HashMap<Object, Object> beamProperties;
    }
    protected Stack beam_stack = new Stack();



    // $ANTLR start "beam"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:226:1: beam returns [Beam bObj] : BEAM ( beamLine )+ ;
    public final Beam beam() throws RecognitionException {
        beam_stack.push(new beam_scope());
        Beam bObj = null;


         
                ((beam_scope)beam_stack.peek()).beamProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:237:2: ( BEAM ( beamLine )+ )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:237:4: BEAM ( beamLine )+
            {
            match(input,BEAM,FOLLOW_BEAM_in_beam2007); 

            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:237:9: ( beamLine )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0 >= CIRCULAR && LA10_0 <= COLLIMATION)||LA10_0==ENERGY||LA10_0==FILE||(LA10_0 >= FLUX && LA10_0 <= HORIZONTAL)||LA10_0==PIXELSIZE||LA10_0==RECTANGULAR||LA10_0==TYPE||LA10_0==VERTICAL) ) {
                    alt10=1;
                }


                switch (alt10) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:237:9: beamLine
                    {
                    pushFollow(FOLLOW_beamLine_in_beam2009);
                    beamLine();

                    state._fsp--;


                    }
                    break;

                default :
                    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            }

             
                    bObj = beamFactory.createBeam(((beam_scope)beam_stack.peek()).beamType, ((beam_scope)beam_stack.peek()).beamProperties);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
            beam_stack.pop();
        }
        return bObj;
    }
    // $ANTLR end "beam"



    // $ANTLR start "beamLine"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:240:1: beamLine : ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize );
    public final void beamLine() throws RecognitionException {
        Token a=null;
        Double b =null;

        InputfileParser.beamFWHM_return c =null;

        Double d =null;

        Map<Object, Object> e =null;

        String f =null;

        Map<Object, Object> g =null;


        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:241:2: ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize )
            int alt11=7;
            switch ( input.LA(1) ) {
            case TYPE:
                {
                alt11=1;
                }
                break;
            case FLUX:
                {
                alt11=2;
                }
                break;
            case FWHM:
                {
                alt11=3;
                }
                break;
            case ENERGY:
                {
                alt11=4;
                }
                break;
            case CIRCULAR:
            case COLLIMATION:
            case HORIZONTAL:
            case RECTANGULAR:
            case VERTICAL:
                {
                alt11=5;
                }
                break;
            case FILE:
                {
                alt11=6;
                }
                break;
            case PIXELSIZE:
                {
                alt11=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }

            switch (alt11) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:241:4: TYPE a= STRING
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_beamLine2048); 

                    a=(Token)match(input,STRING,FOLLOW_STRING_in_beamLine2052); 

                     ((beam_scope)beam_stack.peek()).beamType = (a!=null?a.getText():null); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:242:4: b= beamFlux
                    {
                    pushFollow(FOLLOW_beamFlux_in_beamLine2070);
                    b=beamFlux();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FLUX, b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:243:4: c= beamFWHM
                    {
                    pushFollow(FOLLOW_beamFWHM_in_beamLine2082);
                    c=beamFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_X, (c!=null?c.x:null)); 
                                                   ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_Y, (c!=null?c.y:null)); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:245:4: d= beamEnergy
                    {
                    pushFollow(FOLLOW_beamEnergy_in_beamLine2094);
                    d=beamEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_ENERGY, d); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:246:4: e= beamCollimation
                    {
                    pushFollow(FOLLOW_beamCollimation_in_beamLine2106);
                    e=beamCollimation();

                    state._fsp--;


                     if (e != null) {
                                                    ((beam_scope)beam_stack.peek()).beamProperties.putAll(e);
                                                   } 

                    }
                    break;
                case 6 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:249:4: f= beamFile
                    {
                    pushFollow(FOLLOW_beamFile_in_beamLine2117);
                    f=beamFile();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXTFILE, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:250:4: g= beamPixelSize
                    {
                    pushFollow(FOLLOW_beamPixelSize_in_beamLine2138);
                    g=beamPixelSize();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.putAll(g); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "beamLine"



    // $ANTLR start "beamFlux"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:253:1: beamFlux returns [Double flux] : FLUX a= FLOAT ;
    public final Double beamFlux() throws RecognitionException {
        Double flux = null;


        Token a=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:254:2: ( FLUX a= FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:254:4: FLUX a= FLOAT
            {
            match(input,FLUX,FOLLOW_FLUX_in_beamFlux2162); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFlux2166); 

            flux = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return flux;
    }
    // $ANTLR end "beamFlux"


    public static class beamFWHM_return extends ParserRuleReturnScope {
        public Double x;
        public Double y;
    };


    // $ANTLR start "beamFWHM"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:257:1: beamFWHM returns [Double x, Double y] : FWHM a= FLOAT b= FLOAT ;
    public final InputfileParser.beamFWHM_return beamFWHM() throws RecognitionException {
        InputfileParser.beamFWHM_return retval = new InputfileParser.beamFWHM_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:258:2: ( FWHM a= FLOAT b= FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:258:4: FWHM a= FLOAT b= FLOAT
            {
            match(input,FWHM,FOLLOW_FWHM_in_beamFWHM2208); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM2212); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM2216); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "beamFWHM"



    // $ANTLR start "beamEnergy"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:261:1: beamEnergy returns [Double energy] : ENERGY a= FLOAT ( KEV )? ;
    public final Double beamEnergy() throws RecognitionException {
        Double energy = null;


        Token a=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:262:2: ( ENERGY a= FLOAT ( KEV )? )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:262:4: ENERGY a= FLOAT ( KEV )?
            {
            match(input,ENERGY,FOLLOW_ENERGY_in_beamEnergy2258); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamEnergy2262); 

            energy = Double.parseDouble((a!=null?a.getText():null));

            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:263:2: ( KEV )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==KEV) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:263:4: KEV
                    {
                    match(input,KEV,FOLLOW_KEV_in_beamEnergy2269); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return energy;
    }
    // $ANTLR end "beamEnergy"



    // $ANTLR start "beamFile"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:268:1: beamFile returns [String filename] : FILE a= STRING ;
    public final String beamFile() throws RecognitionException {
        String filename = null;


        Token a=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:269:2: ( FILE a= STRING )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:269:4: FILE a= STRING
            {
            match(input,FILE,FOLLOW_FILE_in_beamFile2347); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_beamFile2351); 

            filename = (a!=null?a.getText():null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return filename;
    }
    // $ANTLR end "beamFile"



    // $ANTLR start "beamPixelSize"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:273:1: beamPixelSize returns [Map<Object, Object> properties] : PIXELSIZE a= FLOAT b= FLOAT ;
    public final Map<Object, Object> beamPixelSize() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:274:5: ( PIXELSIZE a= FLOAT b= FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:274:7: PIXELSIZE a= FLOAT b= FLOAT
            {
            match(input,PIXELSIZE,FOLLOW_PIXELSIZE_in_beamPixelSize2398); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize2402); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize2406); 

            properties = new HashMap<Object, Object>();
                     properties.put(Beam.BEAM_PIXSIZE_X, Double.parseDouble((a!=null?a.getText():null)));
                     properties.put(Beam.BEAM_PIXSIZE_Y, Double.parseDouble((b!=null?b.getText():null))); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return properties;
    }
    // $ANTLR end "beamPixelSize"



    // $ANTLR start "beamCollimation"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:281:1: beamCollimation returns [Map<Object, Object> properties] : ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT );
    public final Map<Object, Object> beamCollimation() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token d=null;
        Token e=null;

         
                properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:285:2: ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT )
            int alt13=5;
            switch ( input.LA(1) ) {
            case COLLIMATION:
                {
                alt13=1;
                }
                break;
            case RECTANGULAR:
                {
                alt13=2;
                }
                break;
            case CIRCULAR:
                {
                alt13=3;
                }
                break;
            case HORIZONTAL:
                {
                alt13=4;
                }
                break;
            case VERTICAL:
                {
                alt13=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:285:4: COLLIMATION
                    {
                    match(input,COLLIMATION,FOLLOW_COLLIMATION_in_beamCollimation2485); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:286:4: RECTANGULAR a= FLOAT b= FLOAT
                    {
                    match(input,RECTANGULAR,FOLLOW_RECTANGULAR_in_beamCollimation2491); 

                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2495); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2499); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((a!=null?a.getText():null)));
                                                        properties.put(Beam.BEAM_COLL_V, Double.parseDouble((b!=null?b.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:288:4: CIRCULAR FLOAT
                    {
                    match(input,CIRCULAR,FOLLOW_CIRCULAR_in_beamCollimation2506); 

                    match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2508); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:289:4: HORIZONTAL d= FLOAT
                    {
                    match(input,HORIZONTAL,FOLLOW_HORIZONTAL_in_beamCollimation2514); 

                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2518); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((d!=null?d.getText():null))); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:290:4: VERTICAL e= FLOAT
                    {
                    match(input,VERTICAL,FOLLOW_VERTICAL_in_beamCollimation2525); 

                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2529); 

                     properties.put(Beam.BEAM_COLL_V, Double.parseDouble((e!=null?e.getText():null))); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return properties;
    }
    // $ANTLR end "beamCollimation"


    protected static class wedge_scope {
        Double  angRes;
        Double  startAng;
        Double  endAng;
        Double  expTime;
        Double  offsetX;
        Double  offsetY;
        Double  offsetZ;
        Double  translateX;
        Double  translateY;
        Double  translateZ;
        Double  rotationOffset;
    }
    protected Stack wedge_stack = new Stack();



    // $ANTLR start "wedge"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:300:1: wedge returns [Wedge wObj] : WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ ;
    public final Wedge wedge() throws RecognitionException {
        wedge_stack.push(new wedge_scope());
        Wedge wObj = null;


        Token a=null;
        Token b=null;

         

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:319:2: ( WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:319:4: WEDGE a= FLOAT b= FLOAT ( wedgeLine )+
            {
            match(input,WEDGE,FOLLOW_WEDGE_in_wedge2842); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge2846); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge2850); 

            ((wedge_scope)wedge_stack.peek()).startAng = Double.parseDouble((a!=null?a.getText():null));
                                         ((wedge_scope)wedge_stack.peek()).endAng = Double.parseDouble((b!=null?b.getText():null)); 

            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:321:4: ( wedgeLine )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==ANGULARRESOLUTION||LA14_0==EXPOSURETIME||LA14_0==ROTAXBEAMOFFSET||LA14_0==STARTOFFSET||LA14_0==TRANSLATEPERDEGREE) ) {
                    alt14=1;
                }


                switch (alt14) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:321:4: wedgeLine
                    {
                    pushFollow(FOLLOW_wedgeLine_in_wedge2857);
                    wedgeLine();

                    state._fsp--;


                    }
                    break;

                default :
                    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            }

             
            wObj = new Wedge(((wedge_scope)wedge_stack.peek()).angRes, ((wedge_scope)wedge_stack.peek()).startAng, ((wedge_scope)wedge_stack.peek()).endAng, ((wedge_scope)wedge_stack.peek()).expTime, ((wedge_scope)wedge_stack.peek()).offsetX, ((wedge_scope)wedge_stack.peek()).offsetY, ((wedge_scope)wedge_stack.peek()).offsetZ, ((wedge_scope)wedge_stack.peek()).translateX, ((wedge_scope)wedge_stack.peek()).translateY, ((wedge_scope)wedge_stack.peek()).translateZ, ((wedge_scope)wedge_stack.peek()).rotationOffset);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
            wedge_stack.pop();
        }
        return wObj;
    }
    // $ANTLR end "wedge"



    // $ANTLR start "wedgeLine"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:324:1: wedgeLine : (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset );
    public final void wedgeLine() throws RecognitionException {
        double a =0.0;

        double b =0.0;

        InputfileParser.wedgeStartOffset_return c =null;

        InputfileParser.wedgeTranslate_return d =null;

        double e =0.0;


        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:325:2: (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset )
            int alt15=5;
            switch ( input.LA(1) ) {
            case EXPOSURETIME:
                {
                alt15=1;
                }
                break;
            case ANGULARRESOLUTION:
                {
                alt15=2;
                }
                break;
            case STARTOFFSET:
                {
                alt15=3;
                }
                break;
            case TRANSLATEPERDEGREE:
                {
                alt15=4;
                }
                break;
            case ROTAXBEAMOFFSET:
                {
                alt15=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }

            switch (alt15) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:325:4: a= wedgeExposure
                    {
                    pushFollow(FOLLOW_wedgeExposure_in_wedgeLine2901);
                    a=wedgeExposure();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).expTime =a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:326:4: b= wedgeAngRes
                    {
                    pushFollow(FOLLOW_wedgeAngRes_in_wedgeLine2911);
                    b=wedgeAngRes();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).angRes =b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:327:4: c= wedgeStartOffset
                    {
                    pushFollow(FOLLOW_wedgeStartOffset_in_wedgeLine2922);
                    c=wedgeStartOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).offsetX =(c!=null?c.x:null);
                                                 ((wedge_scope)wedge_stack.peek()).offsetY =(c!=null?c.y:null);
                                                 ((wedge_scope)wedge_stack.peek()).offsetZ =(c!=null?c.z:null); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:330:4: d= wedgeTranslate
                    {
                    pushFollow(FOLLOW_wedgeTranslate_in_wedgeLine2932);
                    d=wedgeTranslate();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).translateX =(d!=null?d.x:null);
                                                 ((wedge_scope)wedge_stack.peek()).translateY =(d!=null?d.y:null);
                                                 ((wedge_scope)wedge_stack.peek()).translateZ =(d!=null?d.z:null); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:333:4: e= wedgeRotAxBeamOffset
                    {
                    pushFollow(FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine2942);
                    e=wedgeRotAxBeamOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).rotationOffset =e; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "wedgeLine"



    // $ANTLR start "wedgeExposure"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:336:1: wedgeExposure returns [double value] : EXPOSURETIME a= FLOAT ;
    public final double wedgeExposure() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:337:2: ( EXPOSURETIME a= FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:337:4: EXPOSURETIME a= FLOAT
            {
            match(input,EXPOSURETIME,FOLLOW_EXPOSURETIME_in_wedgeExposure2959); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeExposure2963); 

            value = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "wedgeExposure"



    // $ANTLR start "wedgeAngRes"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:340:1: wedgeAngRes returns [double res] : ANGULARRESOLUTION a= FLOAT ;
    public final double wedgeAngRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:341:2: ( ANGULARRESOLUTION a= FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:341:4: ANGULARRESOLUTION a= FLOAT
            {
            match(input,ANGULARRESOLUTION,FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes3045); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeAngRes3049); 

            res = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return res;
    }
    // $ANTLR end "wedgeAngRes"


    public static class wedgeStartOffset_return extends ParserRuleReturnScope {
        public Double x;
        public Double y;
        public Double z;
    };


    // $ANTLR start "wedgeStartOffset"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:344:1: wedgeStartOffset returns [Double x, Double y, Double z] : STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeStartOffset_return wedgeStartOffset() throws RecognitionException {
        InputfileParser.wedgeStartOffset_return retval = new InputfileParser.wedgeStartOffset_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:345:2: ( STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:345:4: STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,STARTOFFSET,FOLLOW_STARTOFFSET_in_wedgeStartOffset3156); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset3160); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset3164); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:346:17: (c= FLOAT )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==FLOAT) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:346:17: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset3185); 

                    }
                    break;

            }


            retval.z = ((c!=null?c.getText():null) == null) ? null : Double.parseDouble((c!=null?c.getText():null));

            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "wedgeStartOffset"


    public static class wedgeTranslate_return extends ParserRuleReturnScope {
        public Double x;
        public Double y;
        public Double z;
    };


    // $ANTLR start "wedgeTranslate"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:350:1: wedgeTranslate returns [Double x, Double y, Double z] : TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeTranslate_return wedgeTranslate() throws RecognitionException {
        InputfileParser.wedgeTranslate_return retval = new InputfileParser.wedgeTranslate_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:351:2: ( TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:351:4: TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,TRANSLATEPERDEGREE,FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate3279); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate3283); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate3287); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:352:24: (c= FLOAT )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==FLOAT) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:352:24: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate3315); 

                    }
                    break;

            }


            retval.z = ((c!=null?c.getText():null) == null) ? null : Double.parseDouble((c!=null?c.getText():null));

            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "wedgeTranslate"



    // $ANTLR start "wedgeRotAxBeamOffset"
    // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:356:1: wedgeRotAxBeamOffset returns [double delta] : ROTAXBEAMOFFSET a= FLOAT ;
    public final double wedgeRotAxBeamOffset() throws RecognitionException {
        double delta = 0.0;


        Token a=null;

        try {
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:357:2: ( ROTAXBEAMOFFSET a= FLOAT )
            // C:\\Users\\linc2253\\Desktop\\efg\\raddose\\parser\\Inputfile.g:357:4: ROTAXBEAMOFFSET a= FLOAT
            {
            match(input,ROTAXBEAMOFFSET,FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset3451); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeRotAxBeamOffset3455); 

            delta = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return delta;
    }
    // $ANTLR end "wedgeRotAxBeamOffset"

    // Delegated rules


 

    public static final BitSet FOLLOW_crystal_in_configfile47 = new BitSet(new long[]{0x0001000000002200L});
    public static final BitSet FOLLOW_wedge_in_configfile65 = new BitSet(new long[]{0x0001000000002200L});
    public static final BitSet FOLLOW_beam_in_configfile85 = new BitSet(new long[]{0x0001000000002200L});
    public static final BitSet FOLLOW_EOF_in_configfile105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CRYSTAL_in_crystal134 = new BitSet(new long[]{0x0000630DE001C070L});
    public static final BitSet FOLLOW_crystalLine_in_crystal136 = new BitSet(new long[]{0x0000630DE001C072L});
    public static final BitSet FOLLOW_crystalType_in_crystalLine192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalDDM_in_crystalLine203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalCoefcalc_in_crystalLine215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalDim_in_crystalLine225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalPPM_in_crystalLine236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalAngP_in_crystalLine247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalAngL_in_crystalLine258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unitcell_in_crystalLine269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nummonomers_in_crystalLine280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numresidues_in_crystalLine291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numRNA_in_crystalLine302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numDNA_in_crystalLine315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_heavyProteinAtoms_in_crystalLine328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_heavySolutionConc_in_crystalLine337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_solventFraction_in_crystalLine346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_crystalType366 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_STRING_in_crystalType370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalDDM412 = new BitSet(new long[]{0x0000008010000000L});
    public static final BitSet FOLLOW_crystalDDMKeyword_in_crystalDDM424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIMPLE_in_crystalDDMKeyword572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LINEAR_in_crystalDDMKeyword579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSCOEFCALC_in_crystalCoefcalc670 = new BitSet(new long[]{0x0000001000020100L});
    public static final BitSet FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DUMMY_in_crystalCoefcalcKeyword753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVERAGE_in_crystalCoefcalcKeyword762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RD_in_crystalCoefcalcKeyword769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_crystalDim899 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim912 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim916 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEP_in_crystalAngP1010 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngP1014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEL_in_crystalAngL1069 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngL1073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSPERMICRON_in_crystalPPM1127 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalPPM1129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNITCELL_in_unitcell1227 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1231 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1235 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1239 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1254 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1258 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMMONOMERS_in_nummonomers1340 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_nummonomers1344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRESIDUES_in_numresidues1421 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_numresidues1425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRNA_in_numRNA1503 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_numRNA1507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMDNA_in_numDNA1560 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_numDNA1564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms1620 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavyProteinAtoms1625 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_heavyProteinAtoms1629 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc1776 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavySolutionConc1781 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_heavySolutionConc1785 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_SOLVENTFRACTION_in_solventFraction1891 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_solventFraction1895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEAM_in_beam2007 = new BitSet(new long[]{0x0000A02207480C00L});
    public static final BitSet FOLLOW_beamLine_in_beam2009 = new BitSet(new long[]{0x0000A02207480C02L});
    public static final BitSet FOLLOW_TYPE_in_beamLine2048 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_STRING_in_beamLine2052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFlux_in_beamLine2070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFWHM_in_beamLine2082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamEnergy_in_beamLine2094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamCollimation_in_beamLine2106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFile_in_beamLine2117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamPixelSize_in_beamLine2138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLUX_in_beamFlux2162 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFlux2166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FWHM_in_beamFWHM2208 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM2212 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM2216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGY_in_beamEnergy2258 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_beamEnergy2262 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_KEV_in_beamEnergy2269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_beamFile2347 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_STRING_in_beamFile2351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSIZE_in_beamPixelSize2398 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize2402 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize2406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLIMATION_in_beamCollimation2485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RECTANGULAR_in_beamCollimation2491 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2495 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCULAR_in_beamCollimation2506 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HORIZONTAL_in_beamCollimation2514 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERTICAL_in_beamCollimation2525 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WEDGE_in_wedge2842 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge2846 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge2850 = new BitSet(new long[]{0x0000144000200080L});
    public static final BitSet FOLLOW_wedgeLine_in_wedge2857 = new BitSet(new long[]{0x0000144000200082L});
    public static final BitSet FOLLOW_wedgeExposure_in_wedgeLine2901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeAngRes_in_wedgeLine2911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeStartOffset_in_wedgeLine2922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeTranslate_in_wedgeLine2932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine2942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURETIME_in_wedgeExposure2959 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeExposure2963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes3045 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeAngRes3049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTOFFSET_in_wedgeStartOffset3156 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset3160 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset3164 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset3185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate3279 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate3283 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate3287 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate3315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset3451 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeRotAxBeamOffset3455 = new BitSet(new long[]{0x0000000000000002L});

}