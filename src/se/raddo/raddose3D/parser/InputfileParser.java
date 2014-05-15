// $ANTLR 3.4 C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g 2014-05-15 12:09:07

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABSCOEFCALC", "ANGLEL", "ANGLEP", "ANGULARRESOLUTION", "AVERAGE", "BEAM", "CIRCULAR", "COLLIMATION", "COMMENT", "CRYSTAL", "DDM", "DEFAULT", "DIFFRACTIONDECAYMODEL", "DIMENSION", "DUMMY", "ELEMENT", "ENERGY", "EXPONENT", "EXPOSURETIME", "FILE", "FLOAT", "FLUX", "FWHM", "HORIZONTAL", "KEV", "LINEAR", "MODELFILE", "NUMDNA", "NUMMONOMERS", "NUMRESIDUES", "NUMRNA", "PDB", "PDBNAME", "PIXELSIZE", "PIXELSPERMICRON", "PROTEINHEAVYATOMS", "RD", "RECTANGULAR", "ROTAXBEAMOFFSET", "SIMPLE", "SOLVENTFRACTION", "SOLVENTHEAVYCONC", "STARTOFFSET", "STRING", "TRANSLATEPERDEGREE", "TYPE", "UNITCELL", "VERTICAL", "WEDGE", "WIREFRAMETYPE", "WS"
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
    public static final int MODELFILE=30;
    public static final int NUMDNA=31;
    public static final int NUMMONOMERS=32;
    public static final int NUMRESIDUES=33;
    public static final int NUMRNA=34;
    public static final int PDB=35;
    public static final int PDBNAME=36;
    public static final int PIXELSIZE=37;
    public static final int PIXELSPERMICRON=38;
    public static final int PROTEINHEAVYATOMS=39;
    public static final int RD=40;
    public static final int RECTANGULAR=41;
    public static final int ROTAXBEAMOFFSET=42;
    public static final int SIMPLE=43;
    public static final int SOLVENTFRACTION=44;
    public static final int SOLVENTHEAVYCONC=45;
    public static final int STARTOFFSET=46;
    public static final int STRING=47;
    public static final int TRANSLATEPERDEGREE=48;
    public static final int TYPE=49;
    public static final int UNITCELL=50;
    public static final int VERTICAL=51;
    public static final int WEDGE=52;
    public static final int WIREFRAMETYPE=53;
    public static final int WS=54;

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
    public String getGrammarFileName() { return "C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g"; }


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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:41:1: configfile : (a= crystal |b= wedge |c= beam )* EOF ;
    public final void configfile() throws RecognitionException {
        Crystal a =null;

        Wedge b =null;

        Beam c =null;


        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:41:11: ( (a= crystal |b= wedge |c= beam )* EOF )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )* EOF
            {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )*
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
            	    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:42:13: a= crystal
            	    {
            	    pushFollow(FOLLOW_crystal_in_configfile47);
            	    a=crystal();

            	    state._fsp--;


            	     raddoseInitializer.setCrystal(a); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:43:13: b= wedge
            	    {
            	    pushFollow(FOLLOW_wedge_in_configfile65);
            	    b=wedge();

            	    state._fsp--;


            	     raddoseInitializer.exposeWedge(b); 

            	    }
            	    break;
            	case 3 :
            	    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:44:13: c= beam
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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:49:1: crystal returns [Crystal cObj] : CRYSTAL ( crystalLine )+ ;
    public final Crystal crystal() throws RecognitionException {
        crystal_stack.push(new crystal_scope());
        Crystal cObj = null;


         
        ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = 2; // 0 = error, 1 = Simple, 2 = DEFAULT, 3 = RDV2, 4 = PDB
        		((crystal_scope)crystal_stack.peek()).crystalProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:109:2: ( CRYSTAL ( crystalLine )+ )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:109:4: CRYSTAL ( crystalLine )+
            {
            match(input,CRYSTAL,FOLLOW_CRYSTAL_in_crystal134); 

            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:109:12: ( crystalLine )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= ABSCOEFCALC && LA2_0 <= ANGLEP)||LA2_0==DDM||(LA2_0 >= DIFFRACTIONDECAYMODEL && LA2_0 <= DIMENSION)||(LA2_0 >= MODELFILE && LA2_0 <= NUMRNA)||LA2_0==PDBNAME||(LA2_0 >= PIXELSPERMICRON && LA2_0 <= PROTEINHEAVYATOMS)||(LA2_0 >= SOLVENTFRACTION && LA2_0 <= SOLVENTHEAVYCONC)||(LA2_0 >= TYPE && LA2_0 <= UNITCELL)||LA2_0==WIREFRAMETYPE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:109:12: crystalLine
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
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromParams(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:112:1: crystalLine : (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile );
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

        String v =null;

        String w =null;


        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:113:2: (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile )
            int alt3=18;
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
            case WIREFRAMETYPE:
                {
                alt3=17;
                }
                break;
            case MODELFILE:
                {
                alt3=18;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:113:4: a= crystalType
                    {
                    pushFollow(FOLLOW_crystalType_in_crystalLine192);
                    a=crystalType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalType = a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:114:4: b= crystalDDM
                    {
                    pushFollow(FOLLOW_crystalDDM_in_crystalLine203);
                    b=crystalDDM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_DDM, b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:115:4: c= crystalCoefcalc
                    {
                    pushFollow(FOLLOW_crystalCoefcalc_in_crystalLine215);
                    c=crystalCoefcalc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = c; 

                    }
                    break;
                case 4 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:116:4: d= crystalDim
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
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:119:4: e= crystalPPM
                    {
                    pushFollow(FOLLOW_crystalPPM_in_crystalLine236);
                    e=crystalPPM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, e); 

                    }
                    break;
                case 6 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:120:4: f= crystalAngP
                    {
                    pushFollow(FOLLOW_crystalAngP_in_crystalLine247);
                    f=crystalAngP();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:121:4: g= crystalAngL
                    {
                    pushFollow(FOLLOW_crystalAngL_in_crystalLine258);
                    g=crystalAngL();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, g); 

                    }
                    break;
                case 8 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:122:4: m= unitcell
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
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:128:4: n= nummonomers
                    {
                    pushFollow(FOLLOW_nummonomers_in_crystalLine280);
                    n=nummonomers();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numMon = n;	

                    }
                    break;
                case 10 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:129:4: o= numresidues
                    {
                    pushFollow(FOLLOW_numresidues_in_crystalLine291);
                    o=numresidues();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRes = o;	

                    }
                    break;
                case 11 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:130:4: p= numRNA
                    {
                    pushFollow(FOLLOW_numRNA_in_crystalLine302);
                    p=numRNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRNA = p;	

                    }
                    break;
                case 12 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:131:4: q= numDNA
                    {
                    pushFollow(FOLLOW_numDNA_in_crystalLine315);
                    q=numDNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numDNA = q;	

                    }
                    break;
                case 13 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:132:4: r= heavyProteinAtoms
                    {
                    pushFollow(FOLLOW_heavyProteinAtoms_in_crystalLine328);
                    r=heavyProteinAtoms();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames = (r!=null?r.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums = (r!=null?r.num:null);	

                    }
                    break;
                case 14 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:134:4: s= heavySolutionConc
                    {
                    pushFollow(FOLLOW_heavySolutionConc_in_crystalLine337);
                    s=heavySolutionConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames = (s!=null?s.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums = (s!=null?s.num:null);	

                    }
                    break;
                case 15 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:136:4: t= solventFraction
                    {
                    pushFollow(FOLLOW_solventFraction_in_crystalLine346);
                    t=solventFraction();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).solFrac = t; 

                    }
                    break;
                case 16 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:137:4: u= pdb
                    {
                    pushFollow(FOLLOW_pdb_in_crystalLine356);
                    u=pdb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).pdb = u; 

                    }
                    break;
                case 17 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:138:4: v= wireframeType
                    {
                    pushFollow(FOLLOW_wireframeType_in_crystalLine369);
                    v=wireframeType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_TYPE, v); 

                    }
                    break;
                case 18 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:139:4: w= modelFile
                    {
                    pushFollow(FOLLOW_modelFile_in_crystalLine380);
                    w=modelFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_FILE, w); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:143:1: crystalType returns [String crystalType] : TYPE e= STRING ;
    public final String crystalType() throws RecognitionException {
        String crystalType = null;


        Token e=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:144:2: ( TYPE e= STRING )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:144:4: TYPE e= STRING
            {
            match(input,TYPE,FOLLOW_TYPE_in_crystalType402); 

            e=(Token)match(input,STRING,FOLLOW_STRING_in_crystalType406); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:147:1: crystalDDM returns [DDM value] : ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword ;
    public final DDM crystalDDM() throws RecognitionException {
        DDM value = null;


        DDM e =null;


        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:148:2: ( ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:148:4: ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword
            {
            if ( input.LA(1)==DDM||input.LA(1)==DIFFRACTIONDECAYMODEL ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalDDMKeyword_in_crystalDDM460);
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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:151:1: crystalDDMKeyword returns [DDM value] : ( SIMPLE | LINEAR );
    public final DDM crystalDDMKeyword() throws RecognitionException {
        DDM value = null;


        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:152:2: ( SIMPLE | LINEAR )
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
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:152:4: SIMPLE
                    {
                    match(input,SIMPLE,FOLLOW_SIMPLE_in_crystalDDMKeyword608); 

                     value = new DDMSimple(); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:153:4: LINEAR
                    {
                    match(input,LINEAR,FOLLOW_LINEAR_in_crystalDDMKeyword615); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:158:1: crystalCoefcalc returns [int value] : ABSCOEFCALC a= crystalCoefcalcKeyword ;
    public final int crystalCoefcalc() throws RecognitionException {
        int value = 0;


        int a =0;


        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:159:2: ( ABSCOEFCALC a= crystalCoefcalcKeyword )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:159:4: ABSCOEFCALC a= crystalCoefcalcKeyword
            {
            match(input,ABSCOEFCALC,FOLLOW_ABSCOEFCALC_in_crystalCoefcalc706); 

            pushFollow(FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc710);
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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:161:1: crystalCoefcalcKeyword returns [int value] : ( DUMMY | AVERAGE | DEFAULT | RD | PDB );
    public final int crystalCoefcalcKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:162:2: ( DUMMY | AVERAGE | DEFAULT | RD | PDB )
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
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:162:4: DUMMY
                    {
                    match(input,DUMMY,FOLLOW_DUMMY_in_crystalCoefcalcKeyword789); 

                     value = 1;

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:163:4: AVERAGE
                    {
                    match(input,AVERAGE,FOLLOW_AVERAGE_in_crystalCoefcalcKeyword798); 

                     value = 1;

                    }
                    break;
                case 3 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:164:4: DEFAULT
                    {
                    match(input,DEFAULT,FOLLOW_DEFAULT_in_crystalCoefcalcKeyword805); 

                     value = 2;

                    }
                    break;
                case 4 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:165:4: RD
                    {
                    match(input,RD,FOLLOW_RD_in_crystalCoefcalcKeyword812); 

                     value = 3;

                    }
                    break;
                case 5 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:166:4: PDB
                    {
                    match(input,PDB,FOLLOW_PDB_in_crystalCoefcalcKeyword824); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:174:1: crystalDim returns [Map<Object, Object> properties] : DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT ) ;
    public final Map<Object, Object> crystalDim() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token c=null;
        Token d=null;

         
        		properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:177:3: ( DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT ) )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:177:5: DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT )
            {
            match(input,DIMENSION,FOLLOW_DIMENSION_in_crystalDim1013); 

            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:178:2: (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==FLOAT) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==FLOAT) ) {
                    alt6=1;
                }
                else if ( (LA6_1==EOF||(LA6_1 >= ABSCOEFCALC && LA6_1 <= ANGLEP)||LA6_1==BEAM||(LA6_1 >= CRYSTAL && LA6_1 <= DDM)||(LA6_1 >= DIFFRACTIONDECAYMODEL && LA6_1 <= DIMENSION)||(LA6_1 >= MODELFILE && LA6_1 <= NUMRNA)||LA6_1==PDBNAME||(LA6_1 >= PIXELSPERMICRON && LA6_1 <= PROTEINHEAVYATOMS)||(LA6_1 >= SOLVENTFRACTION && LA6_1 <= SOLVENTHEAVYCONC)||(LA6_1 >= TYPE && LA6_1 <= UNITCELL)||(LA6_1 >= WEDGE && LA6_1 <= WIREFRAMETYPE)) ) {
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
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:179:7: a= FLOAT b= FLOAT c= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1026); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1030); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1034); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((a!=null?a.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((b!=null?b.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble((c!=null?c.getText():null))); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:182:7: d= FLOAT
                    {
                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1047); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:186:1: crystalAngP returns [double value] : ANGLEP a= FLOAT ;
    public final double crystalAngP() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:187:2: ( ANGLEP a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:187:4: ANGLEP a= FLOAT
            {
            match(input,ANGLEP,FOLLOW_ANGLEP_in_crystalAngP1124); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngP1128); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:191:1: crystalAngL returns [double value] : ANGLEL a= FLOAT ;
    public final double crystalAngL() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:192:2: ( ANGLEL a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:192:4: ANGLEL a= FLOAT
            {
            match(input,ANGLEL,FOLLOW_ANGLEL_in_crystalAngL1183); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngL1187); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:196:1: crystalPPM returns [double ppm] : PIXELSPERMICRON FLOAT ;
    public final double crystalPPM() throws RecognitionException {
        double ppm = 0.0;


        Token FLOAT1=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:197:2: ( PIXELSPERMICRON FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:197:4: PIXELSPERMICRON FLOAT
            {
            match(input,PIXELSPERMICRON,FOLLOW_PIXELSPERMICRON_in_crystalPPM1241); 

            FLOAT1=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalPPM1243); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:200:1: unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC] : UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? ;
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
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:201:2: ( UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:201:4: UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )?
            {
            match(input,UNITCELL,FOLLOW_UNITCELL_in_unitcell1341); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1345); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1349); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1353); 

            retval.dimA = Double.parseDouble((a!=null?a.getText():null));
            		 retval.dimB = Double.parseDouble((b!=null?b.getText():null));
            		 retval.dimC = Double.parseDouble((c!=null?c.getText():null)); 

            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:205:7: (al= FLOAT be= FLOAT ga= FLOAT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FLOAT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:205:8: al= FLOAT be= FLOAT ga= FLOAT
                    {
                    al=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1368); 

                    be=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1372); 

                    ga=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1376); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:213:1: nummonomers returns [int value] : NUMMONOMERS a= FLOAT ;
    public final int nummonomers() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:214:2: ( NUMMONOMERS a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:214:4: NUMMONOMERS a= FLOAT
            {
            match(input,NUMMONOMERS,FOLLOW_NUMMONOMERS_in_nummonomers1454); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_nummonomers1458); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:217:1: numresidues returns [int value] : NUMRESIDUES a= FLOAT ;
    public final int numresidues() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:218:2: ( NUMRESIDUES a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:218:4: NUMRESIDUES a= FLOAT
            {
            match(input,NUMRESIDUES,FOLLOW_NUMRESIDUES_in_numresidues1535); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numresidues1539); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:221:1: numRNA returns [int value] : NUMRNA a= FLOAT ;
    public final int numRNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:222:2: ( NUMRNA a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:222:4: NUMRNA a= FLOAT
            {
            match(input,NUMRNA,FOLLOW_NUMRNA_in_numRNA1617); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numRNA1621); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:225:1: numDNA returns [int value] : NUMDNA a= FLOAT ;
    public final int numDNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:226:2: ( NUMDNA a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:226:4: NUMDNA a= FLOAT
            {
            match(input,NUMDNA,FOLLOW_NUMDNA_in_numDNA1674); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numDNA1678); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:229:1: heavyProteinAtoms returns [List<String> names, List<Double> num;] : PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavyProteinAtoms_return heavyProteinAtoms() throws RecognitionException {
        InputfileParser.heavyProteinAtoms_return retval = new InputfileParser.heavyProteinAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:234:2: ( PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:234:4: PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,PROTEINHEAVYATOMS,FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms1734); 

            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:234:22: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:234:23: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavyProteinAtoms1739); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavyProteinAtoms1743); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:238:1: heavySolutionConc returns [List<String> names, List<Double> num;] : SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavySolutionConc_return heavySolutionConc() throws RecognitionException {
        InputfileParser.heavySolutionConc_return retval = new InputfileParser.heavySolutionConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:243:2: ( SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:243:4: SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SOLVENTHEAVYCONC,FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc1890); 

            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:243:21: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:243:22: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavySolutionConc1895); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavySolutionConc1899); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:246:1: solventFraction returns [double solFrac] : SOLVENTFRACTION a= FLOAT ;
    public final double solventFraction() throws RecognitionException {
        double solFrac = 0.0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:247:2: ( SOLVENTFRACTION a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:247:4: SOLVENTFRACTION a= FLOAT
            {
            match(input,SOLVENTFRACTION,FOLLOW_SOLVENTFRACTION_in_solventFraction2005); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_solventFraction2009); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:250:1: pdb returns [String pdb] : PDBNAME a= STRING ;
    public final String pdb() throws RecognitionException {
        String pdb = null;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:251:2: ( PDBNAME a= STRING )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:251:4: PDBNAME a= STRING
            {
            match(input,PDBNAME,FOLLOW_PDBNAME_in_pdb2106); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_pdb2110); 

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



    // $ANTLR start "wireframeType"
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:254:1: wireframeType returns [String value] : WIREFRAMETYPE a= STRING ;
    public final String wireframeType() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:255:2: ( WIREFRAMETYPE a= STRING )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:255:4: WIREFRAMETYPE a= STRING
            {
            match(input,WIREFRAMETYPE,FOLLOW_WIREFRAMETYPE_in_wireframeType2147); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_wireframeType2151); 

            value = (a!=null?a.getText():null);

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
    // $ANTLR end "wireframeType"



    // $ANTLR start "modelFile"
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:258:1: modelFile returns [String value] : MODELFILE a= STRING ;
    public final String modelFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:259:2: ( MODELFILE a= STRING )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:259:4: MODELFILE a= STRING
            {
            match(input,MODELFILE,FOLLOW_MODELFILE_in_modelFile2239); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_modelFile2243); 

            value = (a!=null?a.getText():null);

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
    // $ANTLR end "modelFile"


    protected static class beam_scope {
        String beamType;
        HashMap<Object, Object> beamProperties;
    }
    protected Stack beam_stack = new Stack();



    // $ANTLR start "beam"
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:264:1: beam returns [Beam bObj] : BEAM ( beamLine )+ ;
    public final Beam beam() throws RecognitionException {
        beam_stack.push(new beam_scope());
        Beam bObj = null;


         
        		((beam_scope)beam_stack.peek()).beamProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:275:2: ( BEAM ( beamLine )+ )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:275:4: BEAM ( beamLine )+
            {
            match(input,BEAM,FOLLOW_BEAM_in_beam2326); 

            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:275:9: ( beamLine )+
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
            	    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:275:9: beamLine
            	    {
            	    pushFollow(FOLLOW_beamLine_in_beam2328);
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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:278:1: beamLine : ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize );
    public final void beamLine() throws RecognitionException {
        Token a=null;
        Double b =null;

        InputfileParser.beamFWHM_return c =null;

        Double d =null;

        Map<Object, Object> e =null;

        String f =null;

        Map<Object, Object> g =null;


        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:279:2: ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize )
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
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:279:4: TYPE a= STRING
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_beamLine2367); 

                    a=(Token)match(input,STRING,FOLLOW_STRING_in_beamLine2371); 

                     ((beam_scope)beam_stack.peek()).beamType = (a!=null?a.getText():null); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:280:4: b= beamFlux
                    {
                    pushFollow(FOLLOW_beamFlux_in_beamLine2389);
                    b=beamFlux();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FLUX, b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:281:4: c= beamFWHM
                    {
                    pushFollow(FOLLOW_beamFWHM_in_beamLine2401);
                    c=beamFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_X, (c!=null?c.x:null)); 
                    	                           ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_Y, (c!=null?c.y:null)); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:283:4: d= beamEnergy
                    {
                    pushFollow(FOLLOW_beamEnergy_in_beamLine2413);
                    d=beamEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_ENERGY, d); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:284:4: e= beamCollimation
                    {
                    pushFollow(FOLLOW_beamCollimation_in_beamLine2425);
                    e=beamCollimation();

                    state._fsp--;


                     if (e != null) {
                    							    ((beam_scope)beam_stack.peek()).beamProperties.putAll(e);
                    							   } 

                    }
                    break;
                case 6 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:287:4: f= beamFile
                    {
                    pushFollow(FOLLOW_beamFile_in_beamLine2436);
                    f=beamFile();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXTFILE, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:288:4: g= beamPixelSize
                    {
                    pushFollow(FOLLOW_beamPixelSize_in_beamLine2457);
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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:291:1: beamFlux returns [Double flux] : FLUX a= FLOAT ;
    public final Double beamFlux() throws RecognitionException {
        Double flux = null;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:292:2: ( FLUX a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:292:4: FLUX a= FLOAT
            {
            match(input,FLUX,FOLLOW_FLUX_in_beamFlux2481); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFlux2485); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:295:1: beamFWHM returns [Double x, Double y] : FWHM a= FLOAT b= FLOAT ;
    public final InputfileParser.beamFWHM_return beamFWHM() throws RecognitionException {
        InputfileParser.beamFWHM_return retval = new InputfileParser.beamFWHM_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:296:2: ( FWHM a= FLOAT b= FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:296:4: FWHM a= FLOAT b= FLOAT
            {
            match(input,FWHM,FOLLOW_FWHM_in_beamFWHM2527); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM2531); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM2535); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:299:1: beamEnergy returns [Double energy] : ENERGY a= FLOAT ( KEV )? ;
    public final Double beamEnergy() throws RecognitionException {
        Double energy = null;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:300:2: ( ENERGY a= FLOAT ( KEV )? )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:300:4: ENERGY a= FLOAT ( KEV )?
            {
            match(input,ENERGY,FOLLOW_ENERGY_in_beamEnergy2577); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamEnergy2581); 

            energy = Double.parseDouble((a!=null?a.getText():null));

            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:301:2: ( KEV )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==KEV) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:301:4: KEV
                    {
                    match(input,KEV,FOLLOW_KEV_in_beamEnergy2588); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:306:1: beamFile returns [String filename] : FILE a= STRING ;
    public final String beamFile() throws RecognitionException {
        String filename = null;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:307:2: ( FILE a= STRING )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:307:4: FILE a= STRING
            {
            match(input,FILE,FOLLOW_FILE_in_beamFile2666); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_beamFile2670); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:311:1: beamPixelSize returns [Map<Object, Object> properties] : PIXELSIZE a= FLOAT b= FLOAT ;
    public final Map<Object, Object> beamPixelSize() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:312:5: ( PIXELSIZE a= FLOAT b= FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:312:7: PIXELSIZE a= FLOAT b= FLOAT
            {
            match(input,PIXELSIZE,FOLLOW_PIXELSIZE_in_beamPixelSize2717); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize2721); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize2725); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:319:1: beamCollimation returns [Map<Object, Object> properties] : ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT );
    public final Map<Object, Object> beamCollimation() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token d=null;
        Token e=null;

         
        		properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:323:2: ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT )
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
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:323:4: COLLIMATION
                    {
                    match(input,COLLIMATION,FOLLOW_COLLIMATION_in_beamCollimation2804); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:324:4: RECTANGULAR a= FLOAT b= FLOAT
                    {
                    match(input,RECTANGULAR,FOLLOW_RECTANGULAR_in_beamCollimation2810); 

                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2814); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2818); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((a!=null?a.getText():null)));
                    	                                properties.put(Beam.BEAM_COLL_V, Double.parseDouble((b!=null?b.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:326:4: CIRCULAR FLOAT
                    {
                    match(input,CIRCULAR,FOLLOW_CIRCULAR_in_beamCollimation2825); 

                    match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2827); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:327:4: HORIZONTAL d= FLOAT
                    {
                    match(input,HORIZONTAL,FOLLOW_HORIZONTAL_in_beamCollimation2833); 

                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2837); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((d!=null?d.getText():null))); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:328:4: VERTICAL e= FLOAT
                    {
                    match(input,VERTICAL,FOLLOW_VERTICAL_in_beamCollimation2844); 

                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation2848); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:338:1: wedge returns [Wedge wObj] : WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ ;
    public final Wedge wedge() throws RecognitionException {
        wedge_stack.push(new wedge_scope());
        Wedge wObj = null;


        Token a=null;
        Token b=null;

         

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:357:2: ( WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:357:4: WEDGE a= FLOAT b= FLOAT ( wedgeLine )+
            {
            match(input,WEDGE,FOLLOW_WEDGE_in_wedge3161); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge3165); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge3169); 

            ((wedge_scope)wedge_stack.peek()).startAng = Double.parseDouble((a!=null?a.getText():null));
            							 ((wedge_scope)wedge_stack.peek()).endAng = Double.parseDouble((b!=null?b.getText():null)); 

            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:359:4: ( wedgeLine )+
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
            	    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:359:4: wedgeLine
            	    {
            	    pushFollow(FOLLOW_wedgeLine_in_wedge3176);
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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:362:1: wedgeLine : (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset );
    public final void wedgeLine() throws RecognitionException {
        double a =0.0;

        double b =0.0;

        InputfileParser.wedgeStartOffset_return c =null;

        InputfileParser.wedgeTranslate_return d =null;

        double e =0.0;


        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:363:2: (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset )
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
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:363:4: a= wedgeExposure
                    {
                    pushFollow(FOLLOW_wedgeExposure_in_wedgeLine3220);
                    a=wedgeExposure();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).expTime =a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:364:4: b= wedgeAngRes
                    {
                    pushFollow(FOLLOW_wedgeAngRes_in_wedgeLine3230);
                    b=wedgeAngRes();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).angRes =b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:365:4: c= wedgeStartOffset
                    {
                    pushFollow(FOLLOW_wedgeStartOffset_in_wedgeLine3241);
                    c=wedgeStartOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).offsetX =(c!=null?c.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetY =(c!=null?c.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetZ =(c!=null?c.z:null); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:368:4: d= wedgeTranslate
                    {
                    pushFollow(FOLLOW_wedgeTranslate_in_wedgeLine3251);
                    d=wedgeTranslate();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).translateX =(d!=null?d.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateY =(d!=null?d.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateZ =(d!=null?d.z:null); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:371:4: e= wedgeRotAxBeamOffset
                    {
                    pushFollow(FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine3261);
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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:374:1: wedgeExposure returns [double value] : EXPOSURETIME a= FLOAT ;
    public final double wedgeExposure() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:375:2: ( EXPOSURETIME a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:375:4: EXPOSURETIME a= FLOAT
            {
            match(input,EXPOSURETIME,FOLLOW_EXPOSURETIME_in_wedgeExposure3278); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeExposure3282); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:378:1: wedgeAngRes returns [double res] : ANGULARRESOLUTION a= FLOAT ;
    public final double wedgeAngRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:379:2: ( ANGULARRESOLUTION a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:379:4: ANGULARRESOLUTION a= FLOAT
            {
            match(input,ANGULARRESOLUTION,FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes3364); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeAngRes3368); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:382:1: wedgeStartOffset returns [Double x, Double y, Double z] : STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeStartOffset_return wedgeStartOffset() throws RecognitionException {
        InputfileParser.wedgeStartOffset_return retval = new InputfileParser.wedgeStartOffset_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:383:2: ( STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:383:4: STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,STARTOFFSET,FOLLOW_STARTOFFSET_in_wedgeStartOffset3475); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset3479); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset3483); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:384:17: (c= FLOAT )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==FLOAT) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:384:17: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset3504); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:388:1: wedgeTranslate returns [Double x, Double y, Double z] : TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeTranslate_return wedgeTranslate() throws RecognitionException {
        InputfileParser.wedgeTranslate_return retval = new InputfileParser.wedgeTranslate_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:389:2: ( TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:389:4: TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,TRANSLATEPERDEGREE,FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate3598); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate3602); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate3606); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:390:24: (c= FLOAT )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==FLOAT) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:390:24: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate3634); 

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
    // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:394:1: wedgeRotAxBeamOffset returns [double delta] : ROTAXBEAMOFFSET a= FLOAT ;
    public final double wedgeRotAxBeamOffset() throws RecognitionException {
        double delta = 0.0;


        Token a=null;

        try {
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:395:2: ( ROTAXBEAMOFFSET a= FLOAT )
            // C:\\Users\\magd3052\\workspace\\git-RADDOSE-3D\\raddose\\parser\\Inputfile.g:395:4: ROTAXBEAMOFFSET a= FLOAT
            {
            match(input,ROTAXBEAMOFFSET,FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset3770); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeRotAxBeamOffset3774); 

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


 

    public static final BitSet FOLLOW_crystal_in_configfile47 = new BitSet(new long[]{0x0010000000002200L});
    public static final BitSet FOLLOW_wedge_in_configfile65 = new BitSet(new long[]{0x0010000000002200L});
    public static final BitSet FOLLOW_beam_in_configfile85 = new BitSet(new long[]{0x0010000000002200L});
    public static final BitSet FOLLOW_EOF_in_configfile105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CRYSTAL_in_crystal134 = new BitSet(new long[]{0x002630D7C0034070L});
    public static final BitSet FOLLOW_crystalLine_in_crystal136 = new BitSet(new long[]{0x002630D7C0034072L});
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
    public static final BitSet FOLLOW_wireframeType_in_crystalLine369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modelFile_in_crystalLine380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_crystalType402 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_STRING_in_crystalType406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalDDM448 = new BitSet(new long[]{0x0000080020000000L});
    public static final BitSet FOLLOW_crystalDDMKeyword_in_crystalDDM460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIMPLE_in_crystalDDMKeyword608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LINEAR_in_crystalDDMKeyword615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSCOEFCALC_in_crystalCoefcalc706 = new BitSet(new long[]{0x0000010800048100L});
    public static final BitSet FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DUMMY_in_crystalCoefcalcKeyword789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVERAGE_in_crystalCoefcalcKeyword798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_crystalCoefcalcKeyword805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RD_in_crystalCoefcalcKeyword812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDB_in_crystalCoefcalcKeyword824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_crystalDim1013 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1026 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1030 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEP_in_crystalAngP1124 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngP1128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEL_in_crystalAngL1183 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngL1187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSPERMICRON_in_crystalPPM1241 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalPPM1243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNITCELL_in_unitcell1341 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1345 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1349 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1353 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1368 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1372 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMMONOMERS_in_nummonomers1454 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_nummonomers1458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRESIDUES_in_numresidues1535 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_numresidues1539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRNA_in_numRNA1617 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_numRNA1621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMDNA_in_numDNA1674 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_numDNA1678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms1734 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavyProteinAtoms1739 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavyProteinAtoms1743 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc1890 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavySolutionConc1895 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavySolutionConc1899 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_SOLVENTFRACTION_in_solventFraction2005 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_solventFraction2009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDBNAME_in_pdb2106 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_STRING_in_pdb2110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WIREFRAMETYPE_in_wireframeType2147 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_STRING_in_wireframeType2151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODELFILE_in_modelFile2239 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_STRING_in_modelFile2243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEAM_in_beam2326 = new BitSet(new long[]{0x000A02200E900C00L});
    public static final BitSet FOLLOW_beamLine_in_beam2328 = new BitSet(new long[]{0x000A02200E900C02L});
    public static final BitSet FOLLOW_TYPE_in_beamLine2367 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_STRING_in_beamLine2371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFlux_in_beamLine2389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFWHM_in_beamLine2401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamEnergy_in_beamLine2413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamCollimation_in_beamLine2425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFile_in_beamLine2436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamPixelSize_in_beamLine2457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLUX_in_beamFlux2481 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFlux2485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FWHM_in_beamFWHM2527 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM2531 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM2535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGY_in_beamEnergy2577 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamEnergy2581 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_KEV_in_beamEnergy2588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_beamFile2666 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_STRING_in_beamFile2670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSIZE_in_beamPixelSize2717 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize2721 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize2725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLIMATION_in_beamCollimation2804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RECTANGULAR_in_beamCollimation2810 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2814 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCULAR_in_beamCollimation2825 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HORIZONTAL_in_beamCollimation2833 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERTICAL_in_beamCollimation2844 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation2848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WEDGE_in_wedge3161 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge3165 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge3169 = new BitSet(new long[]{0x0001440000400080L});
    public static final BitSet FOLLOW_wedgeLine_in_wedge3176 = new BitSet(new long[]{0x0001440000400082L});
    public static final BitSet FOLLOW_wedgeExposure_in_wedgeLine3220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeAngRes_in_wedgeLine3230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeStartOffset_in_wedgeLine3241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeTranslate_in_wedgeLine3251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine3261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURETIME_in_wedgeExposure3278 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeExposure3282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes3364 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeAngRes3368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTOFFSET_in_wedgeStartOffset3475 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset3479 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset3483 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset3504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate3598 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate3602 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate3606 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate3634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset3770 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeRotAxBeamOffset3774 = new BitSet(new long[]{0x0000000000000002L});

}