// $ANTLR 3.4 C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g 2014-04-28 13:43:14

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABSCOEFCALC", "ANGLEL", "ANGLEP", "ANGULARRESOLUTION", "AVERAGE", "BEAM", "CIRCULAR", "COLLIMATION", "COMMENT", "CRYSTAL", "DDM", "DEFAULT", "DIFFRACTIONDECAYMODEL", "DIMENSION", "DUMMY", "ELEMENT", "ENERGY", "EXPONENT", "EXPOSURETIME", "FILE", "FLOAT", "FLUX", "FWHM", "HORIZONTAL", "KEV", "LINEAR", "NUMDNA", "NUMMONOMERS", "NUMRESIDUES", "NUMRNA", "PDB", "PDBNAME", "PIXELSIZE", "PIXELSPERMICRON", "PROTEINHEAVYATOMS", "RD", "RECTANGULAR", "ROTAXBEAMOFFSET", "SIMPLE", "SOLVENTFRACTION", "SOLVENTHEAVYCONC", "STARTOFFSET", "STRING", "TRANSLATEPERDEGREE", "TYPE", "UNITCELL", "VERTICAL", "WEDGE", "WS"
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
    public String getGrammarFileName() { return "C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g"; }


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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:41:1: configfile : (a= crystal |b= wedge |c= beam )* EOF ;
    public final void configfile() throws RecognitionException {
        Crystal a =null;

        Wedge b =null;

        Beam c =null;


        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:41:11: ( (a= crystal |b= wedge |c= beam )* EOF )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )* EOF
            {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )*
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
            	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:42:13: a= crystal
            	    {
            	    pushFollow(FOLLOW_crystal_in_configfile47);
            	    a=crystal();

            	    state._fsp--;


            	     raddoseInitializer.setCrystal(a); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:43:13: b= wedge
            	    {
            	    pushFollow(FOLLOW_wedge_in_configfile65);
            	    b=wedge();

            	    state._fsp--;


            	     raddoseInitializer.exposeWedge(b); 

            	    }
            	    break;
            	case 3 :
            	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:44:13: c= beam
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
        String 			crystalType;
        int 			crystalCoefCalc;
        CoefCalc		crystalCoefCalcClass;
        String			pdb;
        Double			cellA;
        Double			cellB;
        Double			cellC;
        Double			cellAl;
        Double			cellBe;
        Double			cellGa;
        int 			numMon;
        int 			numRes;
        int 			numRNA;
        int 			numDNA;
        List<String>	heavyProteinAtomNames;
        List<Double>	heavyProteinAtomNums;
        List<String>	heavySolutionConcNames;
        List<Double>	heavySolutionConcNums;
        Double 			solFrac;
        HashMap<Object, Object> crystalProperties;
    }
    protected Stack crystal_stack = new Stack();



    // $ANTLR start "crystal"
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:49:1: crystal returns [Crystal cObj] : CRYSTAL ( crystalLine )+ ;
    public final Crystal crystal() throws RecognitionException {
        crystal_stack.push(new crystal_scope());
        Crystal cObj = null;


         
        ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = 2; // 0 = error, 1 = Simple, 2 = DEFAULT, 3 = RDV2, 4 = PDB
        		((crystal_scope)crystal_stack.peek()).crystalProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:109:2: ( CRYSTAL ( crystalLine )+ )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:109:4: CRYSTAL ( crystalLine )+
            {
            match(input,CRYSTAL,FOLLOW_CRYSTAL_in_crystal134); 

            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:109:12: ( crystalLine )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= ABSCOEFCALC && LA2_0 <= ANGLEP)||LA2_0==DDM||(LA2_0 >= DIFFRACTIONDECAYMODEL && LA2_0 <= DIMENSION)||(LA2_0 >= NUMDNA && LA2_0 <= NUMRNA)||LA2_0==PDBNAME||(LA2_0 >= PIXELSPERMICRON && LA2_0 <= PROTEINHEAVYATOMS)||(LA2_0 >= SOLVENTFRACTION && LA2_0 <= SOLVENTHEAVYCONC)||(LA2_0 >= TYPE && LA2_0 <= UNITCELL)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:109:12: crystalLine
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
            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 2)
            {
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcCompute(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
              													((crystal_scope)crystal_stack.peek()).numMon, ((crystal_scope)crystal_stack.peek()).numRes, ((crystal_scope)crystal_stack.peek()).numRNA, ((crystal_scope)crystal_stack.peek()).numDNA,
              													((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
              													((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
              													((crystal_scope)crystal_stack.peek()).solFrac);
            }

            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 3) {
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcRaddose(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
              													((crystal_scope)crystal_stack.peek()).numMon, ((crystal_scope)crystal_stack.peek()).numRes, ((crystal_scope)crystal_stack.peek()).numRNA, ((crystal_scope)crystal_stack.peek()).numDNA,
              													((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
              													((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
              													((crystal_scope)crystal_stack.peek()).solFrac);
            }
            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 4)
            {
              if (((crystal_scope)crystal_stack.peek()).heavySolutionConcNames.size() > 0)
              	((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromPDB(((crystal_scope)crystal_stack.peek()).pdb, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums);
              else
            	((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromPDB(((crystal_scope)crystal_stack.peek()).pdb);
              													  													
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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:112:1: crystalLine : (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb );
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

        String u =null;


        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:113:2: (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb )
            int alt3=16;
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
            case PDBNAME:
                {
                alt3=16;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:113:4: a= crystalType
                    {
                    pushFollow(FOLLOW_crystalType_in_crystalLine192);
                    a=crystalType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalType = a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:114:4: b= crystalDDM
                    {
                    pushFollow(FOLLOW_crystalDDM_in_crystalLine203);
                    b=crystalDDM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_DDM, b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:115:4: c= crystalCoefcalc
                    {
                    pushFollow(FOLLOW_crystalCoefcalc_in_crystalLine215);
                    c=crystalCoefcalc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = c; 

                    }
                    break;
                case 4 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:116:4: d= crystalDim
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
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:119:4: e= crystalPPM
                    {
                    pushFollow(FOLLOW_crystalPPM_in_crystalLine236);
                    e=crystalPPM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, e); 

                    }
                    break;
                case 6 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:120:4: f= crystalAngP
                    {
                    pushFollow(FOLLOW_crystalAngP_in_crystalLine247);
                    f=crystalAngP();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:121:4: g= crystalAngL
                    {
                    pushFollow(FOLLOW_crystalAngL_in_crystalLine258);
                    g=crystalAngL();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, g); 

                    }
                    break;
                case 8 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:122:4: m= unitcell
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
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:128:4: n= nummonomers
                    {
                    pushFollow(FOLLOW_nummonomers_in_crystalLine280);
                    n=nummonomers();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numMon = n;	

                    }
                    break;
                case 10 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:129:4: o= numresidues
                    {
                    pushFollow(FOLLOW_numresidues_in_crystalLine291);
                    o=numresidues();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRes = o;	

                    }
                    break;
                case 11 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:130:4: p= numRNA
                    {
                    pushFollow(FOLLOW_numRNA_in_crystalLine302);
                    p=numRNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRNA = p;	

                    }
                    break;
                case 12 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:131:4: q= numDNA
                    {
                    pushFollow(FOLLOW_numDNA_in_crystalLine315);
                    q=numDNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numDNA = q;	

                    }
                    break;
                case 13 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:132:4: r= heavyProteinAtoms
                    {
                    pushFollow(FOLLOW_heavyProteinAtoms_in_crystalLine328);
                    r=heavyProteinAtoms();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames = (r!=null?r.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums = (r!=null?r.num:null);	

                    }
                    break;
                case 14 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:134:4: s= heavySolutionConc
                    {
                    pushFollow(FOLLOW_heavySolutionConc_in_crystalLine337);
                    s=heavySolutionConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames = (s!=null?s.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums = (s!=null?s.num:null);	

                    }
                    break;
                case 15 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:136:4: t= solventFraction
                    {
                    pushFollow(FOLLOW_solventFraction_in_crystalLine346);
                    t=solventFraction();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).solFrac = t; 

                    }
                    break;
                case 16 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:137:4: u= pdb
                    {
                    pushFollow(FOLLOW_pdb_in_crystalLine356);
                    u=pdb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).pdb = u; 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:141:1: crystalType returns [String crystalType] : TYPE e= STRING ;
    public final String crystalType() throws RecognitionException {
        String crystalType = null;


        Token e=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:142:2: ( TYPE e= STRING )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:142:4: TYPE e= STRING
            {
            match(input,TYPE,FOLLOW_TYPE_in_crystalType379); 

            e=(Token)match(input,STRING,FOLLOW_STRING_in_crystalType383); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:145:1: crystalDDM returns [DDM value] : ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword ;
    public final DDM crystalDDM() throws RecognitionException {
        DDM value = null;


        DDM e =null;


        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:146:2: ( ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:146:4: ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword
            {
            if ( input.LA(1)==DDM||input.LA(1)==DIFFRACTIONDECAYMODEL ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalDDMKeyword_in_crystalDDM437);
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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:149:1: crystalDDMKeyword returns [DDM value] : ( SIMPLE | LINEAR );
    public final DDM crystalDDMKeyword() throws RecognitionException {
        DDM value = null;


        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:150:2: ( SIMPLE | LINEAR )
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
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:150:4: SIMPLE
                    {
                    match(input,SIMPLE,FOLLOW_SIMPLE_in_crystalDDMKeyword585); 

                     value = new DDMSimple(); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:151:4: LINEAR
                    {
                    match(input,LINEAR,FOLLOW_LINEAR_in_crystalDDMKeyword592); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:156:1: crystalCoefcalc returns [int value] : ABSCOEFCALC a= crystalCoefcalcKeyword ;
    public final int crystalCoefcalc() throws RecognitionException {
        int value = 0;


        int a =0;


        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:157:2: ( ABSCOEFCALC a= crystalCoefcalcKeyword )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:157:4: ABSCOEFCALC a= crystalCoefcalcKeyword
            {
            match(input,ABSCOEFCALC,FOLLOW_ABSCOEFCALC_in_crystalCoefcalc683); 

            pushFollow(FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc687);
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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:159:1: crystalCoefcalcKeyword returns [int value] : ( DUMMY | AVERAGE | DEFAULT | RD | PDB );
    public final int crystalCoefcalcKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:160:2: ( DUMMY | AVERAGE | DEFAULT | RD | PDB )
            int alt5=5;
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
            case DEFAULT:
                {
                alt5=3;
                }
                break;
            case RD:
                {
                alt5=4;
                }
                break;
            case PDB:
                {
                alt5=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:160:4: DUMMY
                    {
                    match(input,DUMMY,FOLLOW_DUMMY_in_crystalCoefcalcKeyword766); 

                     value = 1;

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:161:4: AVERAGE
                    {
                    match(input,AVERAGE,FOLLOW_AVERAGE_in_crystalCoefcalcKeyword775); 

                     value = 1;

                    }
                    break;
                case 3 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:162:4: DEFAULT
                    {
                    match(input,DEFAULT,FOLLOW_DEFAULT_in_crystalCoefcalcKeyword782); 

                     value = 2;

                    }
                    break;
                case 4 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:163:4: RD
                    {
                    match(input,RD,FOLLOW_RD_in_crystalCoefcalcKeyword789); 

                     value = 3;

                    }
                    break;
                case 5 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:164:4: PDB
                    {
                    match(input,PDB,FOLLOW_PDB_in_crystalCoefcalcKeyword801); 

                     value = 4;

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:172:1: crystalDim returns [Map<Object, Object> properties] : DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT ) ;
    public final Map<Object, Object> crystalDim() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token c=null;
        Token d=null;

         
        		properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:175:3: ( DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT ) )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:175:5: DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT )
            {
            match(input,DIMENSION,FOLLOW_DIMENSION_in_crystalDim990); 

            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:176:2: (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==FLOAT) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==FLOAT) ) {
                    alt6=1;
                }
                else if ( (LA6_1==EOF||(LA6_1 >= ABSCOEFCALC && LA6_1 <= ANGLEP)||LA6_1==BEAM||(LA6_1 >= CRYSTAL && LA6_1 <= DDM)||(LA6_1 >= DIFFRACTIONDECAYMODEL && LA6_1 <= DIMENSION)||(LA6_1 >= NUMDNA && LA6_1 <= NUMRNA)||LA6_1==PDBNAME||(LA6_1 >= PIXELSPERMICRON && LA6_1 <= PROTEINHEAVYATOMS)||(LA6_1 >= SOLVENTFRACTION && LA6_1 <= SOLVENTHEAVYCONC)||(LA6_1 >= TYPE && LA6_1 <= UNITCELL)||LA6_1==WEDGE) ) {
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
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:177:7: a= FLOAT b= FLOAT c= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1003); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1007); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1011); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((a!=null?a.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((b!=null?b.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble((c!=null?c.getText():null))); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:180:7: d= FLOAT
                    {
                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1024); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:184:1: crystalAngP returns [double value] : ANGLEP a= FLOAT ;
    public final double crystalAngP() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:185:2: ( ANGLEP a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:185:4: ANGLEP a= FLOAT
            {
            match(input,ANGLEP,FOLLOW_ANGLEP_in_crystalAngP1101); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngP1105); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:189:1: crystalAngL returns [double value] : ANGLEL a= FLOAT ;
    public final double crystalAngL() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:190:2: ( ANGLEL a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:190:4: ANGLEL a= FLOAT
            {
            match(input,ANGLEL,FOLLOW_ANGLEL_in_crystalAngL1160); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngL1164); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:194:1: crystalPPM returns [double ppm] : PIXELSPERMICRON FLOAT ;
    public final double crystalPPM() throws RecognitionException {
        double ppm = 0.0;


        Token FLOAT1=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:195:2: ( PIXELSPERMICRON FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:195:4: PIXELSPERMICRON FLOAT
            {
            match(input,PIXELSPERMICRON,FOLLOW_PIXELSPERMICRON_in_crystalPPM1218); 

            FLOAT1=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalPPM1220); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:198:1: unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC] : UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? ;
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
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:199:2: ( UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:199:4: UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )?
            {
            match(input,UNITCELL,FOLLOW_UNITCELL_in_unitcell1318); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1322); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1326); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1330); 

            retval.dimA = Double.parseDouble((a!=null?a.getText():null));
            		 retval.dimB = Double.parseDouble((b!=null?b.getText():null));
            		 retval.dimC = Double.parseDouble((c!=null?c.getText():null)); 

            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:203:7: (al= FLOAT be= FLOAT ga= FLOAT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FLOAT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:203:8: al= FLOAT be= FLOAT ga= FLOAT
                    {
                    al=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1345); 

                    be=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1349); 

                    ga=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1353); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:211:1: nummonomers returns [int value] : NUMMONOMERS a= FLOAT ;
    public final int nummonomers() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:212:2: ( NUMMONOMERS a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:212:4: NUMMONOMERS a= FLOAT
            {
            match(input,NUMMONOMERS,FOLLOW_NUMMONOMERS_in_nummonomers1431); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_nummonomers1435); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:215:1: numresidues returns [int value] : NUMRESIDUES a= FLOAT ;
    public final int numresidues() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:216:2: ( NUMRESIDUES a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:216:4: NUMRESIDUES a= FLOAT
            {
            match(input,NUMRESIDUES,FOLLOW_NUMRESIDUES_in_numresidues1512); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numresidues1516); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:219:1: numRNA returns [int value] : NUMRNA a= FLOAT ;
    public final int numRNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:220:2: ( NUMRNA a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:220:4: NUMRNA a= FLOAT
            {
            match(input,NUMRNA,FOLLOW_NUMRNA_in_numRNA1594); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numRNA1598); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:223:1: numDNA returns [int value] : NUMDNA a= FLOAT ;
    public final int numDNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:224:2: ( NUMDNA a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:224:4: NUMDNA a= FLOAT
            {
            match(input,NUMDNA,FOLLOW_NUMDNA_in_numDNA1651); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numDNA1655); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:227:1: heavyProteinAtoms returns [List<String> names, List<Double> num;] : PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavyProteinAtoms_return heavyProteinAtoms() throws RecognitionException {
        InputfileParser.heavyProteinAtoms_return retval = new InputfileParser.heavyProteinAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:232:2: ( PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:232:4: PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,PROTEINHEAVYATOMS,FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms1711); 

            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:232:22: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:232:23: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavyProteinAtoms1716); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavyProteinAtoms1720); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:236:1: heavySolutionConc returns [List<String> names, List<Double> num;] : SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavySolutionConc_return heavySolutionConc() throws RecognitionException {
        InputfileParser.heavySolutionConc_return retval = new InputfileParser.heavySolutionConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:241:2: ( SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:241:4: SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SOLVENTHEAVYCONC,FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc1867); 

            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:241:21: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:241:22: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavySolutionConc1872); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavySolutionConc1876); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:244:1: solventFraction returns [double solFrac] : SOLVENTFRACTION a= FLOAT ;
    public final double solventFraction() throws RecognitionException {
        double solFrac = 0.0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:245:2: ( SOLVENTFRACTION a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:245:4: SOLVENTFRACTION a= FLOAT
            {
            match(input,SOLVENTFRACTION,FOLLOW_SOLVENTFRACTION_in_solventFraction1982); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_solventFraction1986); 

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



    // $ANTLR start "pdb"
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:248:1: pdb returns [String pdb] : PDBNAME a= STRING ;
    public final String pdb() throws RecognitionException {
        String pdb = null;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:249:2: ( PDBNAME a= STRING )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:249:4: PDBNAME a= STRING
            {
            match(input,PDBNAME,FOLLOW_PDBNAME_in_pdb2083); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_pdb2087); 

            pdb = (a!=null?a.getText():null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return pdb;
    }
    // $ANTLR end "pdb"


    protected static class beam_scope {
        String beamType;
        HashMap<Object, Object> beamProperties;
    }
    protected Stack beam_stack = new Stack();



    // $ANTLR start "beam"
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:253:1: beam returns [Beam bObj] : BEAM ( beamLine )+ ;
    public final Beam beam() throws RecognitionException {
        beam_stack.push(new beam_scope());
        Beam bObj = null;


         
        		((beam_scope)beam_stack.peek()).beamProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:264:2: ( BEAM ( beamLine )+ )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:264:4: BEAM ( beamLine )+
            {
            match(input,BEAM,FOLLOW_BEAM_in_beam2139); 

            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:264:9: ( beamLine )+
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
            	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:264:9: beamLine
            	    {
            	    pushFollow(FOLLOW_beamLine_in_beam2141);
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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:267:1: beamLine : ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize );
    public final void beamLine() throws RecognitionException {
        Token a=null;
        Double b =null;

        InputfileParser.beamFWHM_return c =null;

        Double d =null;

        Map<Object, Object> e =null;

        String f =null;

        Map<Object, Object> g =null;


        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:268:2: ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize )
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
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:268:4: TYPE a= STRING
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_beamLine2180); 

                    a=(Token)match(input,STRING,FOLLOW_STRING_in_beamLine2184); 

                     ((beam_scope)beam_stack.peek()).beamType = (a!=null?a.getText():null); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:269:4: b= beamFlux
                    {
                    pushFollow(FOLLOW_beamFlux_in_beamLine2202);
                    b=beamFlux();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FLUX, b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:270:4: c= beamFWHM
                    {
                    pushFollow(FOLLOW_beamFWHM_in_beamLine2214);
                    c=beamFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_X, (c!=null?c.x:null)); 
                    	                           ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_Y, (c!=null?c.y:null)); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:272:4: d= beamEnergy
                    {
                    pushFollow(FOLLOW_beamEnergy_in_beamLine2226);
                    d=beamEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_ENERGY, d); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:273:4: e= beamCollimation
                    {
                    pushFollow(FOLLOW_beamCollimation_in_beamLine2238);
                    e=beamCollimation();

                    state._fsp--;


                     if (e != null) {
                    							    ((beam_scope)beam_stack.peek()).beamProperties.putAll(e);
                    							   } 

                    }
                    break;
                case 6 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:276:4: f= beamFile
                    {
                    pushFollow(FOLLOW_beamFile_in_beamLine2249);
                    f=beamFile();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXTFILE, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:277:4: g= beamPixelSize
                    {
                    pushFollow(FOLLOW_beamPixelSize_in_beamLine2270);
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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:280:1: beamFlux returns [Double flux] : FLUX a= FLOAT ;
    public final Double beamFlux() throws RecognitionException {
        Double flux = null;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:281:2: ( FLUX a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:281:4: FLUX a= FLOAT
            {
            match(input,FLUX,FOLLOW_FLUX_in_beamFlux2294); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFlux2298); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:284:1: beamFWHM returns [Double x, Double y] : FWHM a= FLOAT b= FLOAT ;
    public final InputfileParser.beamFWHM_return beamFWHM() throws RecognitionException {
        InputfileParser.beamFWHM_return retval = new InputfileParser.beamFWHM_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:285:2: ( FWHM a= FLOAT b= FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:285:4: FWHM a= FLOAT b= FLOAT
            {
            match(input,FWHM,FOLLOW_FWHM_in_beamFWHM2340); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM2344); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM2348); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:288:1: beamEnergy returns [Double energy] : ENERGY a= FLOAT ( KEV )? ;
    public final Double beamEnergy() throws RecognitionException {
        Double energy = null;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:289:2: ( ENERGY a= FLOAT ( KEV )? )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:289:4: ENERGY a= FLOAT ( KEV )?
            {
            match(input,ENERGY,FOLLOW_ENERGY_in_beamEnergy2390); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamEnergy2394); 

            energy = Double.parseDouble((a!=null?a.getText():null));

            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:290:2: ( KEV )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==KEV) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:290:4: KEV
                    {
                    match(input,KEV,FOLLOW_KEV_in_beamEnergy2401); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:295:1: beamFile returns [String filename] : FILE a= STRING ;
    public final String beamFile() throws RecognitionException {
        String filename = null;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:296:2: ( FILE a= STRING )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:296:4: FILE a= STRING
            {
            match(input,FILE,FOLLOW_FILE_in_beamFile2479); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_beamFile2483); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:300:1: beamPixelSize returns [Map<Object, Object> properties] : PIXELSIZE a= FLOAT b= FLOAT ;
    public final Map<Object, Object> beamPixelSize() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:301:5: ( PIXELSIZE a= FLOAT b= FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:301:7: PIXELSIZE a= FLOAT b= FLOAT
            {
            match(input,PIXELSIZE,FOLLOW_PIXELSIZE_in_beamPixelSize2530); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize2534); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize2538); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:308:1: beamCollimation returns [Map<Object, Object> properties] : ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT );
    public final Map<Object, Object> beamCollimation() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token d=null;
        Token e=null;

         
        		properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:312:2: ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT )
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
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:312:4: COLLIMATION
                    {
                    match(input,COLLIMATION,FOLLOW_COLLIMATION_in_beamCollimation2617); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:313:4: RECTANGULAR a= FLOAT b= FLOAT
                    {
                    match(input,RECTANGULAR,FOLLOW_RECTANGULAR_in_beamCollimation2623); 

                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2627); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2631); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((a!=null?a.getText():null)));
                    	                                properties.put(Beam.BEAM_COLL_V, Double.parseDouble((b!=null?b.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:315:4: CIRCULAR FLOAT
                    {
                    match(input,CIRCULAR,FOLLOW_CIRCULAR_in_beamCollimation2638); 

                    match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2640); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:316:4: HORIZONTAL d= FLOAT
                    {
                    match(input,HORIZONTAL,FOLLOW_HORIZONTAL_in_beamCollimation2646); 

                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2650); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((d!=null?d.getText():null))); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:317:4: VERTICAL e= FLOAT
                    {
                    match(input,VERTICAL,FOLLOW_VERTICAL_in_beamCollimation2657); 

                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2661); 

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
        Double	angRes;
        Double	startAng;
        Double	endAng;
        Double	expTime;
        Double	offsetX;
        Double	offsetY;
        Double	offsetZ;
        Double	translateX;
        Double	translateY;
        Double	translateZ;
        Double	rotationOffset;
    }
    protected Stack wedge_stack = new Stack();



    // $ANTLR start "wedge"
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:327:1: wedge returns [Wedge wObj] : WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ ;
    public final Wedge wedge() throws RecognitionException {
        wedge_stack.push(new wedge_scope());
        Wedge wObj = null;


        Token a=null;
        Token b=null;

         

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:346:2: ( WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:346:4: WEDGE a= FLOAT b= FLOAT ( wedgeLine )+
            {
            match(input,WEDGE,FOLLOW_WEDGE_in_wedge2974); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge2978); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge2982); 

            ((wedge_scope)wedge_stack.peek()).startAng = Double.parseDouble((a!=null?a.getText():null));
            							 ((wedge_scope)wedge_stack.peek()).endAng = Double.parseDouble((b!=null?b.getText():null)); 

            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:348:4: ( wedgeLine )+
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
            	    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:348:4: wedgeLine
            	    {
            	    pushFollow(FOLLOW_wedgeLine_in_wedge2989);
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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:351:1: wedgeLine : (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset );
    public final void wedgeLine() throws RecognitionException {
        double a =0.0;

        double b =0.0;

        InputfileParser.wedgeStartOffset_return c =null;

        InputfileParser.wedgeTranslate_return d =null;

        double e =0.0;


        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:352:2: (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset )
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
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:352:4: a= wedgeExposure
                    {
                    pushFollow(FOLLOW_wedgeExposure_in_wedgeLine3033);
                    a=wedgeExposure();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).expTime =a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:353:4: b= wedgeAngRes
                    {
                    pushFollow(FOLLOW_wedgeAngRes_in_wedgeLine3043);
                    b=wedgeAngRes();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).angRes =b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:354:4: c= wedgeStartOffset
                    {
                    pushFollow(FOLLOW_wedgeStartOffset_in_wedgeLine3054);
                    c=wedgeStartOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).offsetX =(c!=null?c.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetY =(c!=null?c.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetZ =(c!=null?c.z:null); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:357:4: d= wedgeTranslate
                    {
                    pushFollow(FOLLOW_wedgeTranslate_in_wedgeLine3064);
                    d=wedgeTranslate();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).translateX =(d!=null?d.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateY =(d!=null?d.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateZ =(d!=null?d.z:null); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:360:4: e= wedgeRotAxBeamOffset
                    {
                    pushFollow(FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine3074);
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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:363:1: wedgeExposure returns [double value] : EXPOSURETIME a= FLOAT ;
    public final double wedgeExposure() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:364:2: ( EXPOSURETIME a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:364:4: EXPOSURETIME a= FLOAT
            {
            match(input,EXPOSURETIME,FOLLOW_EXPOSURETIME_in_wedgeExposure3091); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeExposure3095); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:367:1: wedgeAngRes returns [double res] : ANGULARRESOLUTION a= FLOAT ;
    public final double wedgeAngRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:368:2: ( ANGULARRESOLUTION a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:368:4: ANGULARRESOLUTION a= FLOAT
            {
            match(input,ANGULARRESOLUTION,FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes3177); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeAngRes3181); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:371:1: wedgeStartOffset returns [Double x, Double y, Double z] : STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeStartOffset_return wedgeStartOffset() throws RecognitionException {
        InputfileParser.wedgeStartOffset_return retval = new InputfileParser.wedgeStartOffset_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:372:2: ( STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:372:4: STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,STARTOFFSET,FOLLOW_STARTOFFSET_in_wedgeStartOffset3288); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset3292); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset3296); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:373:17: (c= FLOAT )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==FLOAT) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:373:17: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset3317); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:377:1: wedgeTranslate returns [Double x, Double y, Double z] : TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeTranslate_return wedgeTranslate() throws RecognitionException {
        InputfileParser.wedgeTranslate_return retval = new InputfileParser.wedgeTranslate_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:378:2: ( TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:378:4: TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,TRANSLATEPERDEGREE,FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate3411); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate3415); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate3419); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:379:24: (c= FLOAT )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==FLOAT) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:379:24: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate3447); 

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
    // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:383:1: wedgeRotAxBeamOffset returns [double delta] : ROTAXBEAMOFFSET a= FLOAT ;
    public final double wedgeRotAxBeamOffset() throws RecognitionException {
        double delta = 0.0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:384:2: ( ROTAXBEAMOFFSET a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\RADDOSE-3D\\parser\\Inputfile.g:384:4: ROTAXBEAMOFFSET a= FLOAT
            {
            match(input,ROTAXBEAMOFFSET,FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset3583); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeRotAxBeamOffset3587); 

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


 

    public static final BitSet FOLLOW_crystal_in_configfile47 = new BitSet(new long[]{0x0008000000002200L});
    public static final BitSet FOLLOW_wedge_in_configfile65 = new BitSet(new long[]{0x0008000000002200L});
    public static final BitSet FOLLOW_beam_in_configfile85 = new BitSet(new long[]{0x0008000000002200L});
    public static final BitSet FOLLOW_EOF_in_configfile105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CRYSTAL_in_crystal134 = new BitSet(new long[]{0x0003186BC0034070L});
    public static final BitSet FOLLOW_crystalLine_in_crystal136 = new BitSet(new long[]{0x0003186BC0034072L});
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
    public static final BitSet FOLLOW_pdb_in_crystalLine356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_crystalType379 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_STRING_in_crystalType383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalDDM425 = new BitSet(new long[]{0x0000040020000000L});
    public static final BitSet FOLLOW_crystalDDMKeyword_in_crystalDDM437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIMPLE_in_crystalDDMKeyword585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LINEAR_in_crystalDDMKeyword592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSCOEFCALC_in_crystalCoefcalc683 = new BitSet(new long[]{0x0000008400048100L});
    public static final BitSet FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DUMMY_in_crystalCoefcalcKeyword766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVERAGE_in_crystalCoefcalcKeyword775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_crystalCoefcalcKeyword782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RD_in_crystalCoefcalcKeyword789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDB_in_crystalCoefcalcKeyword801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_crystalDim990 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1003 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1007 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEP_in_crystalAngP1101 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngP1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEL_in_crystalAngL1160 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngL1164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSPERMICRON_in_crystalPPM1218 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalPPM1220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNITCELL_in_unitcell1318 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1322 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1326 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1330 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1345 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1349 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMMONOMERS_in_nummonomers1431 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_nummonomers1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRESIDUES_in_numresidues1512 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_numresidues1516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRNA_in_numRNA1594 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_numRNA1598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMDNA_in_numDNA1651 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_numDNA1655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms1711 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavyProteinAtoms1716 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavyProteinAtoms1720 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc1867 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavySolutionConc1872 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavySolutionConc1876 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_SOLVENTFRACTION_in_solventFraction1982 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_solventFraction1986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDBNAME_in_pdb2083 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_STRING_in_pdb2087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEAM_in_beam2139 = new BitSet(new long[]{0x000501100E900C00L});
    public static final BitSet FOLLOW_beamLine_in_beam2141 = new BitSet(new long[]{0x000501100E900C02L});
    public static final BitSet FOLLOW_TYPE_in_beamLine2180 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_STRING_in_beamLine2184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFlux_in_beamLine2202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFWHM_in_beamLine2214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamEnergy_in_beamLine2226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamCollimation_in_beamLine2238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFile_in_beamLine2249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamPixelSize_in_beamLine2270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLUX_in_beamFlux2294 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFlux2298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FWHM_in_beamFWHM2340 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM2344 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM2348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGY_in_beamEnergy2390 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamEnergy2394 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_KEV_in_beamEnergy2401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_beamFile2479 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_STRING_in_beamFile2483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSIZE_in_beamPixelSize2530 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize2534 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize2538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLIMATION_in_beamCollimation2617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RECTANGULAR_in_beamCollimation2623 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2627 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCULAR_in_beamCollimation2638 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HORIZONTAL_in_beamCollimation2646 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERTICAL_in_beamCollimation2657 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WEDGE_in_wedge2974 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge2978 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge2982 = new BitSet(new long[]{0x0000A20000400080L});
    public static final BitSet FOLLOW_wedgeLine_in_wedge2989 = new BitSet(new long[]{0x0000A20000400082L});
    public static final BitSet FOLLOW_wedgeExposure_in_wedgeLine3033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeAngRes_in_wedgeLine3043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeStartOffset_in_wedgeLine3054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeTranslate_in_wedgeLine3064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine3074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURETIME_in_wedgeExposure3091 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeExposure3095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes3177 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeAngRes3181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTOFFSET_in_wedgeStartOffset3288 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset3292 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset3296 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset3317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate3411 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate3415 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate3419 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate3447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset3583 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeRotAxBeamOffset3587 = new BitSet(new long[]{0x0000000000000002L});

}