// $ANTLR 3.4 C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g 2015-02-09 10:50:12

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABSCOEFCALC", "ANGLEL", "ANGLEP", "ANGULARRESOLUTION", "AVERAGE", "BEAM", "CALCULATEESCAPE", "CIRCULAR", "COLLIMATION", "COMMENT", "CONTAINERMATERIAL", "CONTAINERTHICKNESS", "CRYSTAL", "DDM", "DECAYPARAM", "DEFAULT", "DIFFRACTIONDECAYMODEL", "DIMENSION", "DUMMY", "ELEMENT", "ENERGY", "EXPONENT", "EXPOSURETIME", "FILE", "FLOAT", "FLUX", "FWHM", "HORIZONTAL", "KEV", "LEAL", "LINEAR", "MODELFILE", "NUMDNA", "NUMMONOMERS", "NUMRESIDUES", "NUMRNA", "PDB", "PDBNAME", "PIXELSIZE", "PIXELSPERMICRON", "PROTEINCONCENTRATION", "PROTEINHEAVYATOMS", "RDFORTAN", "RDJAVA", "RECTANGULAR", "ROTAXBEAMOFFSET", "SAXS", "SIMPLE", "SOLVENTFRACTION", "SOLVENTHEAVYCONC", "STARTOFFSET", "STRING", "TRANSLATEPERDEGREE", "TYPE", "UNITCELL", "VERTICAL", "WEDGE", "WIREFRAMETYPE", "WS"
    };

    public static final int EOF=-1;
    public static final int ABSCOEFCALC=4;
    public static final int ANGLEL=5;
    public static final int ANGLEP=6;
    public static final int ANGULARRESOLUTION=7;
    public static final int AVERAGE=8;
    public static final int BEAM=9;
    public static final int CALCULATEESCAPE=10;
    public static final int CIRCULAR=11;
    public static final int COLLIMATION=12;
    public static final int COMMENT=13;
    public static final int CONTAINERMATERIAL=14;
    public static final int CONTAINERTHICKNESS=15;
    public static final int CRYSTAL=16;
    public static final int DDM=17;
    public static final int DECAYPARAM=18;
    public static final int DEFAULT=19;
    public static final int DIFFRACTIONDECAYMODEL=20;
    public static final int DIMENSION=21;
    public static final int DUMMY=22;
    public static final int ELEMENT=23;
    public static final int ENERGY=24;
    public static final int EXPONENT=25;
    public static final int EXPOSURETIME=26;
    public static final int FILE=27;
    public static final int FLOAT=28;
    public static final int FLUX=29;
    public static final int FWHM=30;
    public static final int HORIZONTAL=31;
    public static final int KEV=32;
    public static final int LEAL=33;
    public static final int LINEAR=34;
    public static final int MODELFILE=35;
    public static final int NUMDNA=36;
    public static final int NUMMONOMERS=37;
    public static final int NUMRESIDUES=38;
    public static final int NUMRNA=39;
    public static final int PDB=40;
    public static final int PDBNAME=41;
    public static final int PIXELSIZE=42;
    public static final int PIXELSPERMICRON=43;
    public static final int PROTEINCONCENTRATION=44;
    public static final int PROTEINHEAVYATOMS=45;
    public static final int RDFORTAN=46;
    public static final int RDJAVA=47;
    public static final int RECTANGULAR=48;
    public static final int ROTAXBEAMOFFSET=49;
    public static final int SAXS=50;
    public static final int SIMPLE=51;
    public static final int SOLVENTFRACTION=52;
    public static final int SOLVENTHEAVYCONC=53;
    public static final int STARTOFFSET=54;
    public static final int STRING=55;
    public static final int TRANSLATEPERDEGREE=56;
    public static final int TYPE=57;
    public static final int UNITCELL=58;
    public static final int VERTICAL=59;
    public static final int WEDGE=60;
    public static final int WIREFRAMETYPE=61;
    public static final int WS=62;

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
    public String getGrammarFileName() { return "C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g"; }


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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:41:1: configfile : (a= crystal |b= wedge |c= beam )* EOF ;
    public final void configfile() throws RecognitionException {
        Crystal a =null;

        Wedge b =null;

        Beam c =null;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:41:11: ( (a= crystal |b= wedge |c= beam )* EOF )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )* EOF
            {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )*
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
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:42:13: a= crystal
            	    {
            	    pushFollow(FOLLOW_crystal_in_configfile47);
            	    a=crystal();

            	    state._fsp--;


            	     raddoseInitializer.setCrystal(a); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:43:13: b= wedge
            	    {
            	    pushFollow(FOLLOW_wedge_in_configfile65);
            	    b=wedge();

            	    state._fsp--;


            	     raddoseInitializer.exposeWedge(b); 

            	    }
            	    break;
            	case 3 :
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:44:13: c= beam
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
        int			crystalDdm;
        DDM			crystalDdmClass;
        Double			gammaParam;
        Double			b0Param;
        Double			betaParam;
        String			pdb;
        Double			proteinConc;
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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:49:1: crystal returns [Crystal cObj] : CRYSTAL ( crystalLine )+ ;
    public final Crystal crystal() throws RecognitionException {
        crystal_stack.push(new crystal_scope());
        Crystal cObj = null;


         
        ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = 2; // 0 = error, 1 = Simple, 2 = DEFAULT, 3 = RDV2, 4 = PDB, 5 = SAXS
        		((crystal_scope)crystal_stack.peek()).crystalProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:143:2: ( CRYSTAL ( crystalLine )+ )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:143:4: CRYSTAL ( crystalLine )+
            {
            match(input,CRYSTAL,FOLLOW_CRYSTAL_in_crystal134); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:143:12: ( crystalLine )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= ABSCOEFCALC && LA2_0 <= ANGLEP)||LA2_0==CALCULATEESCAPE||(LA2_0 >= CONTAINERMATERIAL && LA2_0 <= CONTAINERTHICKNESS)||(LA2_0 >= DDM && LA2_0 <= DECAYPARAM)||(LA2_0 >= DIFFRACTIONDECAYMODEL && LA2_0 <= DIMENSION)||(LA2_0 >= MODELFILE && LA2_0 <= NUMRNA)||LA2_0==PDBNAME||(LA2_0 >= PIXELSPERMICRON && LA2_0 <= PROTEINHEAVYATOMS)||(LA2_0 >= SOLVENTFRACTION && LA2_0 <= SOLVENTHEAVYCONC)||(LA2_0 >= TYPE && LA2_0 <= UNITCELL)||LA2_0==WIREFRAMETYPE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:143:12: crystalLine
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

            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 5)
            {
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcSAXS(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
              													((crystal_scope)crystal_stack.peek()).numRes, ((crystal_scope)crystal_stack.peek()).numRNA, ((crystal_scope)crystal_stack.peek()).numDNA,
              													((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
              													((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
              													((crystal_scope)crystal_stack.peek()).solFrac, ((crystal_scope)crystal_stack.peek()).proteinConc);
            }

            ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_COEFCALC, ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass);

            if (((crystal_scope)crystal_stack.peek()).crystalDdm == 1)
            {
            	((crystal_scope)crystal_stack.peek()).crystalDdmClass = new DDMSimple();
            }

            if (((crystal_scope)crystal_stack.peek()).crystalDdm == 2)
            {
            	((crystal_scope)crystal_stack.peek()).crystalDdmClass = new DDMLinear();
            }

            if (((crystal_scope)crystal_stack.peek()).crystalDdm == 3)
            {
            	((crystal_scope)crystal_stack.peek()).crystalDdmClass = new DDMLeal(((crystal_scope)crystal_stack.peek()).gammaParam, ((crystal_scope)crystal_stack.peek()).b0Param, ((crystal_scope)crystal_stack.peek()).betaParam);
            }

            ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_DDM, ((crystal_scope)crystal_stack.peek()).crystalDdmClass);


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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:146:1: crystalLine : (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerMaterial |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculateEscape |y= proteinConcentration );
    public final void crystalLine() throws RecognitionException {
        String a =null;

        int b =0;

        int c =0;

        Map<Object, Object> d =null;

        double e =0.0;

        double f =0.0;

        double g =0.0;

        InputfileParser.crystalDecayParam_return h =null;

        double i =0.0;

        String j =null;

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

        String x =null;

        Double y =null;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:147:2: (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerMaterial |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculateEscape |y= proteinConcentration )
            int alt3=23;
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
            case DECAYPARAM:
                {
                alt3=8;
                }
                break;
            case CONTAINERTHICKNESS:
                {
                alt3=9;
                }
                break;
            case CONTAINERMATERIAL:
                {
                alt3=10;
                }
                break;
            case UNITCELL:
                {
                alt3=11;
                }
                break;
            case NUMMONOMERS:
                {
                alt3=12;
                }
                break;
            case NUMRESIDUES:
                {
                alt3=13;
                }
                break;
            case NUMRNA:
                {
                alt3=14;
                }
                break;
            case NUMDNA:
                {
                alt3=15;
                }
                break;
            case PROTEINHEAVYATOMS:
                {
                alt3=16;
                }
                break;
            case SOLVENTHEAVYCONC:
                {
                alt3=17;
                }
                break;
            case SOLVENTFRACTION:
                {
                alt3=18;
                }
                break;
            case PDBNAME:
                {
                alt3=19;
                }
                break;
            case WIREFRAMETYPE:
                {
                alt3=20;
                }
                break;
            case MODELFILE:
                {
                alt3=21;
                }
                break;
            case CALCULATEESCAPE:
                {
                alt3=22;
                }
                break;
            case PROTEINCONCENTRATION:
                {
                alt3=23;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:147:4: a= crystalType
                    {
                    pushFollow(FOLLOW_crystalType_in_crystalLine192);
                    a=crystalType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalType = a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:148:4: b= crystalDDM
                    {
                    pushFollow(FOLLOW_crystalDDM_in_crystalLine203);
                    b=crystalDDM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalDdm = b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:149:4: c= crystalCoefcalc
                    {
                    pushFollow(FOLLOW_crystalCoefcalc_in_crystalLine215);
                    c=crystalCoefcalc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = c; 

                    }
                    break;
                case 4 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:150:4: d= crystalDim
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
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:153:4: e= crystalPPM
                    {
                    pushFollow(FOLLOW_crystalPPM_in_crystalLine236);
                    e=crystalPPM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, e); 

                    }
                    break;
                case 6 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:154:4: f= crystalAngP
                    {
                    pushFollow(FOLLOW_crystalAngP_in_crystalLine247);
                    f=crystalAngP();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:155:4: g= crystalAngL
                    {
                    pushFollow(FOLLOW_crystalAngL_in_crystalLine258);
                    g=crystalAngL();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, g); 

                    }
                    break;
                case 8 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:156:4: h= crystalDecayParam
                    {
                    pushFollow(FOLLOW_crystalDecayParam_in_crystalLine269);
                    h=crystalDecayParam();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).gammaParam = (h!=null?h.gammaParam:null); 
                    	                           			  ((crystal_scope)crystal_stack.peek()).b0Param = (h!=null?h.b0Param:null); 
                    	                           			  ((crystal_scope)crystal_stack.peek()).betaParam = (h!=null?h.betaParam:null); 

                    }
                    break;
                case 9 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:159:4: i= containerThickness
                    {
                    pushFollow(FOLLOW_containerThickness_in_crystalLine279);
                    i=containerThickness();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_CONTAINER_THICKNESS, i); 

                    }
                    break;
                case 10 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:160:4: j= containerMaterial
                    {
                    pushFollow(FOLLOW_containerMaterial_in_crystalLine289);
                    j=containerMaterial();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_CONTAINER_MATERIAL, j); 

                    }
                    break;
                case 11 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:161:4: m= unitcell
                    {
                    pushFollow(FOLLOW_unitcell_in_crystalLine299);
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
                case 12 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:167:4: n= nummonomers
                    {
                    pushFollow(FOLLOW_nummonomers_in_crystalLine310);
                    n=nummonomers();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numMon = n;	

                    }
                    break;
                case 13 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:168:4: o= numresidues
                    {
                    pushFollow(FOLLOW_numresidues_in_crystalLine321);
                    o=numresidues();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRes = o;	

                    }
                    break;
                case 14 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:169:4: p= numRNA
                    {
                    pushFollow(FOLLOW_numRNA_in_crystalLine332);
                    p=numRNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRNA = p;	

                    }
                    break;
                case 15 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:170:4: q= numDNA
                    {
                    pushFollow(FOLLOW_numDNA_in_crystalLine345);
                    q=numDNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numDNA = q;	

                    }
                    break;
                case 16 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:171:4: r= heavyProteinAtoms
                    {
                    pushFollow(FOLLOW_heavyProteinAtoms_in_crystalLine358);
                    r=heavyProteinAtoms();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames = (r!=null?r.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums = (r!=null?r.num:null);	

                    }
                    break;
                case 17 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:173:4: s= heavySolutionConc
                    {
                    pushFollow(FOLLOW_heavySolutionConc_in_crystalLine367);
                    s=heavySolutionConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames = (s!=null?s.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums = (s!=null?s.num:null);	

                    }
                    break;
                case 18 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:175:4: t= solventFraction
                    {
                    pushFollow(FOLLOW_solventFraction_in_crystalLine376);
                    t=solventFraction();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).solFrac = t; 

                    }
                    break;
                case 19 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:176:4: u= pdb
                    {
                    pushFollow(FOLLOW_pdb_in_crystalLine386);
                    u=pdb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).pdb = u; 

                    }
                    break;
                case 20 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:177:4: v= wireframeType
                    {
                    pushFollow(FOLLOW_wireframeType_in_crystalLine399);
                    v=wireframeType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_TYPE, v); 

                    }
                    break;
                case 21 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:178:4: w= modelFile
                    {
                    pushFollow(FOLLOW_modelFile_in_crystalLine410);
                    w=modelFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_FILE, w); 

                    }
                    break;
                case 22 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:179:4: x= calculateEscape
                    {
                    pushFollow(FOLLOW_calculateEscape_in_crystalLine422);
                    x=calculateEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ELECTRON_ESCAPE, x); 

                    }
                    break;
                case 23 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:180:4: y= proteinConcentration
                    {
                    pushFollow(FOLLOW_proteinConcentration_in_crystalLine432);
                    y=proteinConcentration();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).proteinConc = y;

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:185:1: crystalType returns [String crystalType] : TYPE e= STRING ;
    public final String crystalType() throws RecognitionException {
        String crystalType = null;


        Token e=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:186:2: ( TYPE e= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:186:4: TYPE e= STRING
            {
            match(input,TYPE,FOLLOW_TYPE_in_crystalType455); 

            e=(Token)match(input,STRING,FOLLOW_STRING_in_crystalType459); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:189:1: crystalDDM returns [int value] : ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword ;
    public final int crystalDDM() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:190:2: ( ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:190:4: ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword
            {
            if ( input.LA(1)==DDM||input.LA(1)==DIFFRACTIONDECAYMODEL ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalDDMKeyword_in_crystalDDM513);
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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:193:1: crystalDDMKeyword returns [int value] : ( SIMPLE | LINEAR | LEAL );
    public final int crystalDDMKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:194:2: ( SIMPLE | LINEAR | LEAL )
            int alt4=3;
            switch ( input.LA(1) ) {
            case SIMPLE:
                {
                alt4=1;
                }
                break;
            case LINEAR:
                {
                alt4=2;
                }
                break;
            case LEAL:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:194:4: SIMPLE
                    {
                    match(input,SIMPLE,FOLLOW_SIMPLE_in_crystalDDMKeyword661); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:195:4: LINEAR
                    {
                    match(input,LINEAR,FOLLOW_LINEAR_in_crystalDDMKeyword668); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:196:4: LEAL
                    {
                    match(input,LEAL,FOLLOW_LEAL_in_crystalDDMKeyword675); 

                     value = 3; 

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


    public static class crystalDecayParam_return extends ParserRuleReturnScope {
        public Double gammaParam;
        public Double b0Param;
        public Double betaParam;
    };


    // $ANTLR start "crystalDecayParam"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:202:1: crystalDecayParam returns [Double gammaParam, Double b0Param, Double betaParam] : DECAYPARAM a= FLOAT b= FLOAT c= FLOAT ;
    public final InputfileParser.crystalDecayParam_return crystalDecayParam() throws RecognitionException {
        InputfileParser.crystalDecayParam_return retval = new InputfileParser.crystalDecayParam_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:203:2: ( DECAYPARAM a= FLOAT b= FLOAT c= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:203:4: DECAYPARAM a= FLOAT b= FLOAT c= FLOAT
            {
            match(input,DECAYPARAM,FOLLOW_DECAYPARAM_in_crystalDecayParam795); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam799); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam803); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam807); 

            retval.gammaParam = Double.parseDouble((a!=null?a.getText():null)); retval.b0Param = Double.parseDouble((b!=null?b.getText():null)); retval.betaParam = Double.parseDouble((c!=null?c.getText():null));

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
    // $ANTLR end "crystalDecayParam"



    // $ANTLR start "crystalCoefcalc"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:206:1: crystalCoefcalc returns [int value] : ABSCOEFCALC a= crystalCoefcalcKeyword ;
    public final int crystalCoefcalc() throws RecognitionException {
        int value = 0;


        int a =0;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:207:2: ( ABSCOEFCALC a= crystalCoefcalcKeyword )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:207:4: ABSCOEFCALC a= crystalCoefcalcKeyword
            {
            match(input,ABSCOEFCALC,FOLLOW_ABSCOEFCALC_in_crystalCoefcalc879); 

            pushFollow(FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc883);
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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:209:1: crystalCoefcalcKeyword returns [int value] : ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS );
    public final int crystalCoefcalcKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:210:2: ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS )
            int alt5=7;
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
            case RDJAVA:
                {
                alt5=4;
                }
                break;
            case RDFORTAN:
                {
                alt5=5;
                }
                break;
            case PDB:
                {
                alt5=6;
                }
                break;
            case SAXS:
                {
                alt5=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:210:4: DUMMY
                    {
                    match(input,DUMMY,FOLLOW_DUMMY_in_crystalCoefcalcKeyword962); 

                     value = 1;

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:211:4: AVERAGE
                    {
                    match(input,AVERAGE,FOLLOW_AVERAGE_in_crystalCoefcalcKeyword972); 

                     value = 1;

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:212:4: DEFAULT
                    {
                    match(input,DEFAULT,FOLLOW_DEFAULT_in_crystalCoefcalcKeyword980); 

                     value = 2;

                    }
                    break;
                case 4 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:213:4: RDJAVA
                    {
                    match(input,RDJAVA,FOLLOW_RDJAVA_in_crystalCoefcalcKeyword988); 

                     value = 2;

                    }
                    break;
                case 5 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:214:4: RDFORTAN
                    {
                    match(input,RDFORTAN,FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword995); 

                     value = 3;

                    }
                    break;
                case 6 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:215:4: PDB
                    {
                    match(input,PDB,FOLLOW_PDB_in_crystalCoefcalcKeyword1002); 

                     value = 4;

                    }
                    break;
                case 7 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:216:4: SAXS
                    {
                    match(input,SAXS,FOLLOW_SAXS_in_crystalCoefcalcKeyword1012); 

                     value = 5;

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:226:1: crystalDim returns [Map<Object, Object> properties] : DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT ) ;
    public final Map<Object, Object> crystalDim() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token c=null;
        Token d=null;

         
        		properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:229:3: ( DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:229:5: DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT )
            {
            match(input,DIMENSION,FOLLOW_DIMENSION_in_crystalDim1250); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:230:2: (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==FLOAT) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==FLOAT) ) {
                    alt6=1;
                }
                else if ( (LA6_1==EOF||(LA6_1 >= ABSCOEFCALC && LA6_1 <= ANGLEP)||(LA6_1 >= BEAM && LA6_1 <= CALCULATEESCAPE)||(LA6_1 >= CONTAINERMATERIAL && LA6_1 <= DECAYPARAM)||(LA6_1 >= DIFFRACTIONDECAYMODEL && LA6_1 <= DIMENSION)||(LA6_1 >= MODELFILE && LA6_1 <= NUMRNA)||LA6_1==PDBNAME||(LA6_1 >= PIXELSPERMICRON && LA6_1 <= PROTEINHEAVYATOMS)||(LA6_1 >= SOLVENTFRACTION && LA6_1 <= SOLVENTHEAVYCONC)||(LA6_1 >= TYPE && LA6_1 <= UNITCELL)||(LA6_1 >= WEDGE && LA6_1 <= WIREFRAMETYPE)) ) {
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
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:231:7: a= FLOAT b= FLOAT c= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1263); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1267); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1271); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((a!=null?a.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((b!=null?b.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble((c!=null?c.getText():null))); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:234:7: d= FLOAT
                    {
                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1284); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:238:1: crystalAngP returns [double value] : ANGLEP a= FLOAT ;
    public final double crystalAngP() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:239:2: ( ANGLEP a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:239:4: ANGLEP a= FLOAT
            {
            match(input,ANGLEP,FOLLOW_ANGLEP_in_crystalAngP1361); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngP1365); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:243:1: crystalAngL returns [double value] : ANGLEL a= FLOAT ;
    public final double crystalAngL() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:244:2: ( ANGLEL a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:244:4: ANGLEL a= FLOAT
            {
            match(input,ANGLEL,FOLLOW_ANGLEL_in_crystalAngL1420); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngL1424); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:248:1: crystalPPM returns [double ppm] : PIXELSPERMICRON FLOAT ;
    public final double crystalPPM() throws RecognitionException {
        double ppm = 0.0;


        Token FLOAT1=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:249:2: ( PIXELSPERMICRON FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:249:4: PIXELSPERMICRON FLOAT
            {
            match(input,PIXELSPERMICRON,FOLLOW_PIXELSPERMICRON_in_crystalPPM1478); 

            FLOAT1=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalPPM1480); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:252:1: unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC] : UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? ;
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
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:253:2: ( UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:253:4: UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )?
            {
            match(input,UNITCELL,FOLLOW_UNITCELL_in_unitcell1578); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1582); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1586); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1590); 

            retval.dimA = Double.parseDouble((a!=null?a.getText():null));
            		 retval.dimB = Double.parseDouble((b!=null?b.getText():null));
            		 retval.dimC = Double.parseDouble((c!=null?c.getText():null)); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:257:7: (al= FLOAT be= FLOAT ga= FLOAT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FLOAT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:257:8: al= FLOAT be= FLOAT ga= FLOAT
                    {
                    al=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1605); 

                    be=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1609); 

                    ga=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1613); 

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



    // $ANTLR start "proteinConcentration"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:265:1: proteinConcentration returns [Double proteinConc] : PROTEINCONCENTRATION a= FLOAT ;
    public final Double proteinConcentration() throws RecognitionException {
        Double proteinConc = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:266:2: ( PROTEINCONCENTRATION a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:266:4: PROTEINCONCENTRATION a= FLOAT
            {
            match(input,PROTEINCONCENTRATION,FOLLOW_PROTEINCONCENTRATION_in_proteinConcentration1691); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_proteinConcentration1695); 

            proteinConc = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return proteinConc;
    }
    // $ANTLR end "proteinConcentration"



    // $ANTLR start "nummonomers"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:269:1: nummonomers returns [int value] : NUMMONOMERS a= FLOAT ;
    public final int nummonomers() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:270:2: ( NUMMONOMERS a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:270:4: NUMMONOMERS a= FLOAT
            {
            match(input,NUMMONOMERS,FOLLOW_NUMMONOMERS_in_nummonomers1771); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_nummonomers1775); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:273:1: numresidues returns [int value] : NUMRESIDUES a= FLOAT ;
    public final int numresidues() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:274:2: ( NUMRESIDUES a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:274:4: NUMRESIDUES a= FLOAT
            {
            match(input,NUMRESIDUES,FOLLOW_NUMRESIDUES_in_numresidues1852); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numresidues1856); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:277:1: numRNA returns [int value] : NUMRNA a= FLOAT ;
    public final int numRNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:278:2: ( NUMRNA a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:278:4: NUMRNA a= FLOAT
            {
            match(input,NUMRNA,FOLLOW_NUMRNA_in_numRNA1934); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numRNA1938); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:281:1: numDNA returns [int value] : NUMDNA a= FLOAT ;
    public final int numDNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:282:2: ( NUMDNA a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:282:4: NUMDNA a= FLOAT
            {
            match(input,NUMDNA,FOLLOW_NUMDNA_in_numDNA1991); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numDNA1995); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:285:1: heavyProteinAtoms returns [List<String> names, List<Double> num;] : PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavyProteinAtoms_return heavyProteinAtoms() throws RecognitionException {
        InputfileParser.heavyProteinAtoms_return retval = new InputfileParser.heavyProteinAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:290:2: ( PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:290:4: PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,PROTEINHEAVYATOMS,FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2051); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:290:22: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:290:23: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavyProteinAtoms2056); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavyProteinAtoms2060); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:294:1: heavySolutionConc returns [List<String> names, List<Double> num;] : SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavySolutionConc_return heavySolutionConc() throws RecognitionException {
        InputfileParser.heavySolutionConc_return retval = new InputfileParser.heavySolutionConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:299:2: ( SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:299:4: SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SOLVENTHEAVYCONC,FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc2207); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:299:21: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:299:22: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavySolutionConc2212); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavySolutionConc2216); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:302:1: solventFraction returns [double solFrac] : SOLVENTFRACTION a= FLOAT ;
    public final double solventFraction() throws RecognitionException {
        double solFrac = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:303:2: ( SOLVENTFRACTION a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:303:4: SOLVENTFRACTION a= FLOAT
            {
            match(input,SOLVENTFRACTION,FOLLOW_SOLVENTFRACTION_in_solventFraction2322); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_solventFraction2326); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:306:1: pdb returns [String pdb] : PDBNAME a= STRING ;
    public final String pdb() throws RecognitionException {
        String pdb = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:307:2: ( PDBNAME a= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:307:4: PDBNAME a= STRING
            {
            match(input,PDBNAME,FOLLOW_PDBNAME_in_pdb2423); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_pdb2427); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:310:1: wireframeType returns [String value] : WIREFRAMETYPE a= STRING ;
    public final String wireframeType() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:311:2: ( WIREFRAMETYPE a= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:311:4: WIREFRAMETYPE a= STRING
            {
            match(input,WIREFRAMETYPE,FOLLOW_WIREFRAMETYPE_in_wireframeType2464); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_wireframeType2468); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:314:1: modelFile returns [String value] : MODELFILE a= STRING ;
    public final String modelFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:315:2: ( MODELFILE a= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:315:4: MODELFILE a= STRING
            {
            match(input,MODELFILE,FOLLOW_MODELFILE_in_modelFile2556); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_modelFile2560); 

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



    // $ANTLR start "calculateEscape"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:318:1: calculateEscape returns [String value] : CALCULATEESCAPE a= STRING ;
    public final String calculateEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:319:2: ( CALCULATEESCAPE a= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:319:4: CALCULATEESCAPE a= STRING
            {
            match(input,CALCULATEESCAPE,FOLLOW_CALCULATEESCAPE_in_calculateEscape2627); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculateEscape2631); 

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
    // $ANTLR end "calculateEscape"



    // $ANTLR start "containerThickness"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:323:1: containerThickness returns [double value] : CONTAINERTHICKNESS a= FLOAT ;
    public final double containerThickness() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:324:2: ( CONTAINERTHICKNESS a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:324:4: CONTAINERTHICKNESS a= FLOAT
            {
            match(input,CONTAINERTHICKNESS,FOLLOW_CONTAINERTHICKNESS_in_containerThickness2732); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerThickness2736); 

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
    // $ANTLR end "containerThickness"



    // $ANTLR start "containerMaterial"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:328:1: containerMaterial returns [String value] : CONTAINERMATERIAL a= STRING ;
    public final String containerMaterial() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:329:2: ( CONTAINERMATERIAL a= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:329:4: CONTAINERMATERIAL a= STRING
            {
            match(input,CONTAINERMATERIAL,FOLLOW_CONTAINERMATERIAL_in_containerMaterial2847); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_containerMaterial2851); 

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
    // $ANTLR end "containerMaterial"


    protected static class beam_scope {
        String beamType;
        HashMap<Object, Object> beamProperties;
    }
    protected Stack beam_stack = new Stack();



    // $ANTLR start "beam"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:333:1: beam returns [Beam bObj] : BEAM ( beamLine )+ ;
    public final Beam beam() throws RecognitionException {
        beam_stack.push(new beam_scope());
        Beam bObj = null;


         
        		((beam_scope)beam_stack.peek()).beamProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:344:2: ( BEAM ( beamLine )+ )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:344:4: BEAM ( beamLine )+
            {
            match(input,BEAM,FOLLOW_BEAM_in_beam2972); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:344:9: ( beamLine )+
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
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:344:9: beamLine
            	    {
            	    pushFollow(FOLLOW_beamLine_in_beam2974);
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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:347:1: beamLine : ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize );
    public final void beamLine() throws RecognitionException {
        Token a=null;
        Double b =null;

        InputfileParser.beamFWHM_return c =null;

        Double d =null;

        Map<Object, Object> e =null;

        String f =null;

        Map<Object, Object> g =null;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:348:2: ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize )
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
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:348:4: TYPE a= STRING
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_beamLine3013); 

                    a=(Token)match(input,STRING,FOLLOW_STRING_in_beamLine3017); 

                     ((beam_scope)beam_stack.peek()).beamType = (a!=null?a.getText():null); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:349:4: b= beamFlux
                    {
                    pushFollow(FOLLOW_beamFlux_in_beamLine3035);
                    b=beamFlux();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FLUX, b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:350:4: c= beamFWHM
                    {
                    pushFollow(FOLLOW_beamFWHM_in_beamLine3047);
                    c=beamFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_X, (c!=null?c.x:null)); 
                    	                           ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_Y, (c!=null?c.y:null)); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:352:4: d= beamEnergy
                    {
                    pushFollow(FOLLOW_beamEnergy_in_beamLine3059);
                    d=beamEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_ENERGY, d); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:353:4: e= beamCollimation
                    {
                    pushFollow(FOLLOW_beamCollimation_in_beamLine3071);
                    e=beamCollimation();

                    state._fsp--;


                     if (e != null) {
                    							    ((beam_scope)beam_stack.peek()).beamProperties.putAll(e);
                    							   } 

                    }
                    break;
                case 6 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:356:4: f= beamFile
                    {
                    pushFollow(FOLLOW_beamFile_in_beamLine3082);
                    f=beamFile();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXTFILE, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:357:4: g= beamPixelSize
                    {
                    pushFollow(FOLLOW_beamPixelSize_in_beamLine3103);
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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:360:1: beamFlux returns [Double flux] : FLUX a= FLOAT ;
    public final Double beamFlux() throws RecognitionException {
        Double flux = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:361:2: ( FLUX a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:361:4: FLUX a= FLOAT
            {
            match(input,FLUX,FOLLOW_FLUX_in_beamFlux3127); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFlux3131); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:364:1: beamFWHM returns [Double x, Double y] : FWHM a= FLOAT b= FLOAT ;
    public final InputfileParser.beamFWHM_return beamFWHM() throws RecognitionException {
        InputfileParser.beamFWHM_return retval = new InputfileParser.beamFWHM_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:365:2: ( FWHM a= FLOAT b= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:365:4: FWHM a= FLOAT b= FLOAT
            {
            match(input,FWHM,FOLLOW_FWHM_in_beamFWHM3173); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM3177); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM3181); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:368:1: beamEnergy returns [Double energy] : ENERGY a= FLOAT ( KEV )? ;
    public final Double beamEnergy() throws RecognitionException {
        Double energy = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:369:2: ( ENERGY a= FLOAT ( KEV )? )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:369:4: ENERGY a= FLOAT ( KEV )?
            {
            match(input,ENERGY,FOLLOW_ENERGY_in_beamEnergy3223); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamEnergy3227); 

            energy = Double.parseDouble((a!=null?a.getText():null));

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:370:2: ( KEV )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==KEV) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:370:4: KEV
                    {
                    match(input,KEV,FOLLOW_KEV_in_beamEnergy3234); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:375:1: beamFile returns [String filename] : FILE a= STRING ;
    public final String beamFile() throws RecognitionException {
        String filename = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:376:2: ( FILE a= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:376:4: FILE a= STRING
            {
            match(input,FILE,FOLLOW_FILE_in_beamFile3312); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_beamFile3316); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:380:1: beamPixelSize returns [Map<Object, Object> properties] : PIXELSIZE a= FLOAT b= FLOAT ;
    public final Map<Object, Object> beamPixelSize() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:381:5: ( PIXELSIZE a= FLOAT b= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:381:7: PIXELSIZE a= FLOAT b= FLOAT
            {
            match(input,PIXELSIZE,FOLLOW_PIXELSIZE_in_beamPixelSize3363); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize3367); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize3371); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:388:1: beamCollimation returns [Map<Object, Object> properties] : ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT );
    public final Map<Object, Object> beamCollimation() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token d=null;
        Token e=null;

         
        		properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:392:2: ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT )
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
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:392:4: COLLIMATION
                    {
                    match(input,COLLIMATION,FOLLOW_COLLIMATION_in_beamCollimation3450); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:393:4: RECTANGULAR a= FLOAT b= FLOAT
                    {
                    match(input,RECTANGULAR,FOLLOW_RECTANGULAR_in_beamCollimation3456); 

                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation3460); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation3464); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((a!=null?a.getText():null)));
                    	                                properties.put(Beam.BEAM_COLL_V, Double.parseDouble((b!=null?b.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:395:4: CIRCULAR FLOAT
                    {
                    match(input,CIRCULAR,FOLLOW_CIRCULAR_in_beamCollimation3471); 

                    match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation3473); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:396:4: HORIZONTAL d= FLOAT
                    {
                    match(input,HORIZONTAL,FOLLOW_HORIZONTAL_in_beamCollimation3479); 

                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation3483); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((d!=null?d.getText():null))); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:397:4: VERTICAL e= FLOAT
                    {
                    match(input,VERTICAL,FOLLOW_VERTICAL_in_beamCollimation3490); 

                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation3494); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:407:1: wedge returns [Wedge wObj] : WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ ;
    public final Wedge wedge() throws RecognitionException {
        wedge_stack.push(new wedge_scope());
        Wedge wObj = null;


        Token a=null;
        Token b=null;

         

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:426:2: ( WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:426:4: WEDGE a= FLOAT b= FLOAT ( wedgeLine )+
            {
            match(input,WEDGE,FOLLOW_WEDGE_in_wedge3807); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge3811); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge3815); 

            ((wedge_scope)wedge_stack.peek()).startAng = Double.parseDouble((a!=null?a.getText():null));
            							 ((wedge_scope)wedge_stack.peek()).endAng = Double.parseDouble((b!=null?b.getText():null)); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:428:4: ( wedgeLine )+
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
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:428:4: wedgeLine
            	    {
            	    pushFollow(FOLLOW_wedgeLine_in_wedge3822);
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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:431:1: wedgeLine : (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset );
    public final void wedgeLine() throws RecognitionException {
        double a =0.0;

        double b =0.0;

        InputfileParser.wedgeStartOffset_return c =null;

        InputfileParser.wedgeTranslate_return d =null;

        double e =0.0;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:432:2: (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset )
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
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:432:4: a= wedgeExposure
                    {
                    pushFollow(FOLLOW_wedgeExposure_in_wedgeLine3866);
                    a=wedgeExposure();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).expTime =a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:433:4: b= wedgeAngRes
                    {
                    pushFollow(FOLLOW_wedgeAngRes_in_wedgeLine3876);
                    b=wedgeAngRes();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).angRes =b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:434:4: c= wedgeStartOffset
                    {
                    pushFollow(FOLLOW_wedgeStartOffset_in_wedgeLine3887);
                    c=wedgeStartOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).offsetX =(c!=null?c.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetY =(c!=null?c.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetZ =(c!=null?c.z:null); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:437:4: d= wedgeTranslate
                    {
                    pushFollow(FOLLOW_wedgeTranslate_in_wedgeLine3897);
                    d=wedgeTranslate();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).translateX =(d!=null?d.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateY =(d!=null?d.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateZ =(d!=null?d.z:null); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:440:4: e= wedgeRotAxBeamOffset
                    {
                    pushFollow(FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine3907);
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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:443:1: wedgeExposure returns [double value] : EXPOSURETIME a= FLOAT ;
    public final double wedgeExposure() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:444:2: ( EXPOSURETIME a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:444:4: EXPOSURETIME a= FLOAT
            {
            match(input,EXPOSURETIME,FOLLOW_EXPOSURETIME_in_wedgeExposure3924); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeExposure3928); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:447:1: wedgeAngRes returns [double res] : ANGULARRESOLUTION a= FLOAT ;
    public final double wedgeAngRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:448:2: ( ANGULARRESOLUTION a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:448:4: ANGULARRESOLUTION a= FLOAT
            {
            match(input,ANGULARRESOLUTION,FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes4010); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeAngRes4014); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:451:1: wedgeStartOffset returns [Double x, Double y, Double z] : STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeStartOffset_return wedgeStartOffset() throws RecognitionException {
        InputfileParser.wedgeStartOffset_return retval = new InputfileParser.wedgeStartOffset_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:452:2: ( STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:452:4: STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,STARTOFFSET,FOLLOW_STARTOFFSET_in_wedgeStartOffset4121); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset4125); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset4129); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:453:17: (c= FLOAT )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==FLOAT) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:453:17: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset4150); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:457:1: wedgeTranslate returns [Double x, Double y, Double z] : TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeTranslate_return wedgeTranslate() throws RecognitionException {
        InputfileParser.wedgeTranslate_return retval = new InputfileParser.wedgeTranslate_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:458:2: ( TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:458:4: TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,TRANSLATEPERDEGREE,FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate4244); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate4248); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate4252); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:459:24: (c= FLOAT )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==FLOAT) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:459:24: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate4280); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:463:1: wedgeRotAxBeamOffset returns [double delta] : ROTAXBEAMOFFSET a= FLOAT ;
    public final double wedgeRotAxBeamOffset() throws RecognitionException {
        double delta = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:464:2: ( ROTAXBEAMOFFSET a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:464:4: ROTAXBEAMOFFSET a= FLOAT
            {
            match(input,ROTAXBEAMOFFSET,FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset4416); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeRotAxBeamOffset4420); 

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


 

    public static final BitSet FOLLOW_crystal_in_configfile47 = new BitSet(new long[]{0x1000000000010200L});
    public static final BitSet FOLLOW_wedge_in_configfile65 = new BitSet(new long[]{0x1000000000010200L});
    public static final BitSet FOLLOW_beam_in_configfile85 = new BitSet(new long[]{0x1000000000010200L});
    public static final BitSet FOLLOW_EOF_in_configfile105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CRYSTAL_in_crystal134 = new BitSet(new long[]{0x26303AF80036C470L});
    public static final BitSet FOLLOW_crystalLine_in_crystal136 = new BitSet(new long[]{0x26303AF80036C472L});
    public static final BitSet FOLLOW_crystalType_in_crystalLine192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalDDM_in_crystalLine203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalCoefcalc_in_crystalLine215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalDim_in_crystalLine225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalPPM_in_crystalLine236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalAngP_in_crystalLine247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalAngL_in_crystalLine258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalDecayParam_in_crystalLine269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerThickness_in_crystalLine279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerMaterial_in_crystalLine289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unitcell_in_crystalLine299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nummonomers_in_crystalLine310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numresidues_in_crystalLine321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numRNA_in_crystalLine332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numDNA_in_crystalLine345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_heavyProteinAtoms_in_crystalLine358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_heavySolutionConc_in_crystalLine367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_solventFraction_in_crystalLine376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pdb_in_crystalLine386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wireframeType_in_crystalLine399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modelFile_in_crystalLine410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_calculateEscape_in_crystalLine422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_proteinConcentration_in_crystalLine432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_crystalType455 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_STRING_in_crystalType459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalDDM501 = new BitSet(new long[]{0x0008000600000000L});
    public static final BitSet FOLLOW_crystalDDMKeyword_in_crystalDDM513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIMPLE_in_crystalDDMKeyword661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LINEAR_in_crystalDDMKeyword668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEAL_in_crystalDDMKeyword675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECAYPARAM_in_crystalDecayParam795 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam799 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam803 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSCOEFCALC_in_crystalCoefcalc879 = new BitSet(new long[]{0x0004C10000480100L});
    public static final BitSet FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DUMMY_in_crystalCoefcalcKeyword962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVERAGE_in_crystalCoefcalcKeyword972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_crystalCoefcalcKeyword980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDJAVA_in_crystalCoefcalcKeyword988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDB_in_crystalCoefcalcKeyword1002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXS_in_crystalCoefcalcKeyword1012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_crystalDim1250 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1263 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1267 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEP_in_crystalAngP1361 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngP1365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEL_in_crystalAngL1420 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngL1424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSPERMICRON_in_crystalPPM1478 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalPPM1480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNITCELL_in_unitcell1578 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1582 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1586 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1590 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1605 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1609 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTEINCONCENTRATION_in_proteinConcentration1691 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_proteinConcentration1695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMMONOMERS_in_nummonomers1771 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_nummonomers1775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRESIDUES_in_numresidues1852 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_numresidues1856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRNA_in_numRNA1934 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_numRNA1938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMDNA_in_numDNA1991 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_numDNA1995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2051 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavyProteinAtoms2056 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavyProteinAtoms2060 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc2207 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavySolutionConc2212 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavySolutionConc2216 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_SOLVENTFRACTION_in_solventFraction2322 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_solventFraction2326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDBNAME_in_pdb2423 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_STRING_in_pdb2427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WIREFRAMETYPE_in_wireframeType2464 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_STRING_in_wireframeType2468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODELFILE_in_modelFile2556 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_STRING_in_modelFile2560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEESCAPE_in_calculateEscape2627 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_STRING_in_calculateEscape2631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINERTHICKNESS_in_containerThickness2732 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerThickness2736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINERMATERIAL_in_containerMaterial2847 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_STRING_in_containerMaterial2851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEAM_in_beam2972 = new BitSet(new long[]{0x0A010400E9001800L});
    public static final BitSet FOLLOW_beamLine_in_beam2974 = new BitSet(new long[]{0x0A010400E9001802L});
    public static final BitSet FOLLOW_TYPE_in_beamLine3013 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_STRING_in_beamLine3017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFlux_in_beamLine3035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFWHM_in_beamLine3047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamEnergy_in_beamLine3059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamCollimation_in_beamLine3071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFile_in_beamLine3082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamPixelSize_in_beamLine3103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLUX_in_beamFlux3127 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFlux3131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FWHM_in_beamFWHM3173 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM3177 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM3181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGY_in_beamEnergy3223 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamEnergy3227 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_KEV_in_beamEnergy3234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_beamFile3312 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_STRING_in_beamFile3316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSIZE_in_beamPixelSize3363 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize3367 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize3371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLIMATION_in_beamCollimation3450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RECTANGULAR_in_beamCollimation3456 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation3460 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation3464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCULAR_in_beamCollimation3471 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation3473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HORIZONTAL_in_beamCollimation3479 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation3483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERTICAL_in_beamCollimation3490 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation3494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WEDGE_in_wedge3807 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge3811 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge3815 = new BitSet(new long[]{0x0142000004000080L});
    public static final BitSet FOLLOW_wedgeLine_in_wedge3822 = new BitSet(new long[]{0x0142000004000082L});
    public static final BitSet FOLLOW_wedgeExposure_in_wedgeLine3866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeAngRes_in_wedgeLine3876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeStartOffset_in_wedgeLine3887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeTranslate_in_wedgeLine3897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine3907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURETIME_in_wedgeExposure3924 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeExposure3928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes4010 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeAngRes4014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTOFFSET_in_wedgeStartOffset4121 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset4125 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset4129 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset4150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate4244 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate4248 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate4252 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate4280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset4416 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeRotAxBeamOffset4420 = new BitSet(new long[]{0x0000000000000002L});

}