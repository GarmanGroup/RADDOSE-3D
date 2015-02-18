// $ANTLR 3.4 C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g 2015-02-18 15:01:54

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABSCOEFCALC", "ANGLEL", "ANGLEP", "ANGULARRESOLUTION", "AVERAGE", "BEAM", "CALCULATEESCAPE", "CIRCULAR", "COLLIMATION", "COMMENT", "CONTAINERDENSITY", "CONTAINERMATERIALELEMENTS", "CONTAINERMATERIALMIXTURE", "CONTAINERMATERIALTYPE", "CONTAINERTHICKNESS", "CRYSTAL", "DDM", "DECAYPARAM", "DEFAULT", "DIFFRACTIONDECAYMODEL", "DIMENSION", "DUMMY", "ELEMENT", "ELEMENTAL", "ENERGY", "EXPONENT", "EXPOSURETIME", "FILE", "FLOAT", "FLUX", "FWHM", "HORIZONTAL", "KEV", "LEAL", "LINEAR", "MATERIALELEMENTS", "MATERIALMIXTURE", "MATERIALTYPE", "MIXTURE", "MODELFILE", "NONE", "NUMDNA", "NUMMONOMERS", "NUMRESIDUES", "NUMRNA", "PDB", "PDBNAME", "PIXELSIZE", "PIXELSPERMICRON", "PROTEINCONC", "PROTEINCONCENTRATION", "PROTEINHEAVYATOMS", "RDFORTAN", "RDJAVA", "RECTANGULAR", "ROTAXBEAMOFFSET", "SAXS", "SIMPLE", "SOLVENTFRACTION", "SOLVENTHEAVYCONC", "STARTOFFSET", "STRING", "TRANSLATEPERDEGREE", "TYPE", "UNITCELL", "VERTICAL", "WEDGE", "WIREFRAMETYPE", "WS"
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
    public static final int CONTAINERDENSITY=14;
    public static final int CONTAINERMATERIALELEMENTS=15;
    public static final int CONTAINERMATERIALMIXTURE=16;
    public static final int CONTAINERMATERIALTYPE=17;
    public static final int CONTAINERTHICKNESS=18;
    public static final int CRYSTAL=19;
    public static final int DDM=20;
    public static final int DECAYPARAM=21;
    public static final int DEFAULT=22;
    public static final int DIFFRACTIONDECAYMODEL=23;
    public static final int DIMENSION=24;
    public static final int DUMMY=25;
    public static final int ELEMENT=26;
    public static final int ELEMENTAL=27;
    public static final int ENERGY=28;
    public static final int EXPONENT=29;
    public static final int EXPOSURETIME=30;
    public static final int FILE=31;
    public static final int FLOAT=32;
    public static final int FLUX=33;
    public static final int FWHM=34;
    public static final int HORIZONTAL=35;
    public static final int KEV=36;
    public static final int LEAL=37;
    public static final int LINEAR=38;
    public static final int MATERIALELEMENTS=39;
    public static final int MATERIALMIXTURE=40;
    public static final int MATERIALTYPE=41;
    public static final int MIXTURE=42;
    public static final int MODELFILE=43;
    public static final int NONE=44;
    public static final int NUMDNA=45;
    public static final int NUMMONOMERS=46;
    public static final int NUMRESIDUES=47;
    public static final int NUMRNA=48;
    public static final int PDB=49;
    public static final int PDBNAME=50;
    public static final int PIXELSIZE=51;
    public static final int PIXELSPERMICRON=52;
    public static final int PROTEINCONC=53;
    public static final int PROTEINCONCENTRATION=54;
    public static final int PROTEINHEAVYATOMS=55;
    public static final int RDFORTAN=56;
    public static final int RDJAVA=57;
    public static final int RECTANGULAR=58;
    public static final int ROTAXBEAMOFFSET=59;
    public static final int SAXS=60;
    public static final int SIMPLE=61;
    public static final int SOLVENTFRACTION=62;
    public static final int SOLVENTHEAVYCONC=63;
    public static final int STARTOFFSET=64;
    public static final int STRING=65;
    public static final int TRANSLATEPERDEGREE=66;
    public static final int TYPE=67;
    public static final int UNITCELL=68;
    public static final int VERTICAL=69;
    public static final int WEDGE=70;
    public static final int WIREFRAMETYPE=71;
    public static final int WS=72;

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
        int			crystalContainerMaterial;
        Container		crystalContainerMaterialClass;
        Double			gammaParam;
        Double			b0Param;
        Double			betaParam;
        String			containerMixture;
        Double			containerThickness;
        Double			containerDensity;
        List<String>	containerElementNames;
        List<Double>	containerElementNums;
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
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:168:2: ( CRYSTAL ( crystalLine )+ )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:168:4: CRYSTAL ( crystalLine )+
            {
            match(input,CRYSTAL,FOLLOW_CRYSTAL_in_crystal134); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:168:12: ( crystalLine )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= ABSCOEFCALC && LA2_0 <= ANGLEP)||LA2_0==CALCULATEESCAPE||(LA2_0 >= CONTAINERDENSITY && LA2_0 <= CONTAINERTHICKNESS)||(LA2_0 >= DDM && LA2_0 <= DECAYPARAM)||(LA2_0 >= DIFFRACTIONDECAYMODEL && LA2_0 <= DIMENSION)||(LA2_0 >= MATERIALELEMENTS && LA2_0 <= MATERIALTYPE)||LA2_0==MODELFILE||(LA2_0 >= NUMDNA && LA2_0 <= NUMRNA)||LA2_0==PDBNAME||(LA2_0 >= PIXELSPERMICRON && LA2_0 <= PROTEINHEAVYATOMS)||(LA2_0 >= SOLVENTFRACTION && LA2_0 <= SOLVENTHEAVYCONC)||(LA2_0 >= TYPE && LA2_0 <= UNITCELL)||LA2_0==WIREFRAMETYPE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:168:12: crystalLine
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

            if (((crystal_scope)crystal_stack.peek()).crystalContainerMaterial == 1)
            {
            	((crystal_scope)crystal_stack.peek()).crystalContainerMaterialClass = new ContainerTransparent();
            }

            if (((crystal_scope)crystal_stack.peek()).crystalContainerMaterial == 2)
            {
            	((crystal_scope)crystal_stack.peek()).crystalContainerMaterialClass = new ContainerMixture(((crystal_scope)crystal_stack.peek()).containerThickness, ((crystal_scope)crystal_stack.peek()).containerDensity, ((crystal_scope)crystal_stack.peek()).containerMixture);
            }

            if (((crystal_scope)crystal_stack.peek()).crystalContainerMaterial == 3)
            {
            	((crystal_scope)crystal_stack.peek()).crystalContainerMaterialClass = new ContainerElemental(((crystal_scope)crystal_stack.peek()).containerThickness, ((crystal_scope)crystal_stack.peek()).containerDensity, ((crystal_scope)crystal_stack.peek()).containerElementNames,
            													((crystal_scope)crystal_stack.peek()).containerElementNums);
            }

            ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_CONTAINER, ((crystal_scope)crystal_stack.peek()).crystalContainerMaterialClass);


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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:171:1: crystalLine : (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculateEscape |y= proteinConcentration |z= containerMaterialElements );
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

        double j =0.0;

        int k =0;

        String l =null;

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

        InputfileParser.containerMaterialElements_return z =null;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:172:2: (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculateEscape |y= proteinConcentration |z= containerMaterialElements )
            int alt3=26;
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
            case CONTAINERDENSITY:
                {
                alt3=10;
                }
                break;
            case CONTAINERMATERIALTYPE:
            case MATERIALTYPE:
                {
                alt3=11;
                }
                break;
            case CONTAINERMATERIALMIXTURE:
            case MATERIALMIXTURE:
                {
                alt3=12;
                }
                break;
            case UNITCELL:
                {
                alt3=13;
                }
                break;
            case NUMMONOMERS:
                {
                alt3=14;
                }
                break;
            case NUMRESIDUES:
                {
                alt3=15;
                }
                break;
            case NUMRNA:
                {
                alt3=16;
                }
                break;
            case NUMDNA:
                {
                alt3=17;
                }
                break;
            case PROTEINHEAVYATOMS:
                {
                alt3=18;
                }
                break;
            case SOLVENTHEAVYCONC:
                {
                alt3=19;
                }
                break;
            case SOLVENTFRACTION:
                {
                alt3=20;
                }
                break;
            case PDBNAME:
                {
                alt3=21;
                }
                break;
            case WIREFRAMETYPE:
                {
                alt3=22;
                }
                break;
            case MODELFILE:
                {
                alt3=23;
                }
                break;
            case CALCULATEESCAPE:
                {
                alt3=24;
                }
                break;
            case PROTEINCONC:
            case PROTEINCONCENTRATION:
                {
                alt3=25;
                }
                break;
            case CONTAINERMATERIALELEMENTS:
            case MATERIALELEMENTS:
                {
                alt3=26;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:172:4: a= crystalType
                    {
                    pushFollow(FOLLOW_crystalType_in_crystalLine192);
                    a=crystalType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalType = a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:173:4: b= crystalDDM
                    {
                    pushFollow(FOLLOW_crystalDDM_in_crystalLine203);
                    b=crystalDDM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalDdm = b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:174:4: c= crystalCoefcalc
                    {
                    pushFollow(FOLLOW_crystalCoefcalc_in_crystalLine215);
                    c=crystalCoefcalc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = c; 

                    }
                    break;
                case 4 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:175:4: d= crystalDim
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
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:178:4: e= crystalPPM
                    {
                    pushFollow(FOLLOW_crystalPPM_in_crystalLine236);
                    e=crystalPPM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, e); 

                    }
                    break;
                case 6 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:179:4: f= crystalAngP
                    {
                    pushFollow(FOLLOW_crystalAngP_in_crystalLine247);
                    f=crystalAngP();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:180:4: g= crystalAngL
                    {
                    pushFollow(FOLLOW_crystalAngL_in_crystalLine258);
                    g=crystalAngL();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, g); 

                    }
                    break;
                case 8 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:181:4: h= crystalDecayParam
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
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:184:4: i= containerThickness
                    {
                    pushFollow(FOLLOW_containerThickness_in_crystalLine279);
                    i=containerThickness();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerThickness = i; 

                    }
                    break;
                case 10 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:185:4: j= containerDensity
                    {
                    pushFollow(FOLLOW_containerDensity_in_crystalLine289);
                    j=containerDensity();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerDensity = j; 

                    }
                    break;
                case 11 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:186:4: k= crystalContainerMaterial
                    {
                    pushFollow(FOLLOW_crystalContainerMaterial_in_crystalLine299);
                    k=crystalContainerMaterial();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalContainerMaterial = k; 

                    }
                    break;
                case 12 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:187:4: l= containerMaterialMixture
                    {
                    pushFollow(FOLLOW_containerMaterialMixture_in_crystalLine308);
                    l=containerMaterialMixture();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerMixture = l; 

                    }
                    break;
                case 13 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:188:4: m= unitcell
                    {
                    pushFollow(FOLLOW_unitcell_in_crystalLine317);
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
                case 14 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:194:4: n= nummonomers
                    {
                    pushFollow(FOLLOW_nummonomers_in_crystalLine328);
                    n=nummonomers();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numMon = n;	

                    }
                    break;
                case 15 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:195:4: o= numresidues
                    {
                    pushFollow(FOLLOW_numresidues_in_crystalLine339);
                    o=numresidues();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRes = o;	

                    }
                    break;
                case 16 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:196:4: p= numRNA
                    {
                    pushFollow(FOLLOW_numRNA_in_crystalLine350);
                    p=numRNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRNA = p;	

                    }
                    break;
                case 17 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:197:4: q= numDNA
                    {
                    pushFollow(FOLLOW_numDNA_in_crystalLine363);
                    q=numDNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numDNA = q;	

                    }
                    break;
                case 18 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:198:4: r= heavyProteinAtoms
                    {
                    pushFollow(FOLLOW_heavyProteinAtoms_in_crystalLine376);
                    r=heavyProteinAtoms();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames = (r!=null?r.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums = (r!=null?r.num:null);	

                    }
                    break;
                case 19 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:200:4: s= heavySolutionConc
                    {
                    pushFollow(FOLLOW_heavySolutionConc_in_crystalLine385);
                    s=heavySolutionConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames = (s!=null?s.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums = (s!=null?s.num:null);	

                    }
                    break;
                case 20 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:202:4: t= solventFraction
                    {
                    pushFollow(FOLLOW_solventFraction_in_crystalLine394);
                    t=solventFraction();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).solFrac = t; 

                    }
                    break;
                case 21 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:203:4: u= pdb
                    {
                    pushFollow(FOLLOW_pdb_in_crystalLine404);
                    u=pdb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).pdb = u; 

                    }
                    break;
                case 22 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:204:4: v= wireframeType
                    {
                    pushFollow(FOLLOW_wireframeType_in_crystalLine417);
                    v=wireframeType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_TYPE, v); 

                    }
                    break;
                case 23 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:205:4: w= modelFile
                    {
                    pushFollow(FOLLOW_modelFile_in_crystalLine428);
                    w=modelFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_FILE, w); 

                    }
                    break;
                case 24 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:206:4: x= calculateEscape
                    {
                    pushFollow(FOLLOW_calculateEscape_in_crystalLine440);
                    x=calculateEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ELECTRON_ESCAPE, x); 

                    }
                    break;
                case 25 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:207:4: y= proteinConcentration
                    {
                    pushFollow(FOLLOW_proteinConcentration_in_crystalLine450);
                    y=proteinConcentration();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).proteinConc = y;

                    }
                    break;
                case 26 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:208:4: z= containerMaterialElements
                    {
                    pushFollow(FOLLOW_containerMaterialElements_in_crystalLine459);
                    z=containerMaterialElements();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerElementNames = (z!=null?z.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).containerElementNums = (z!=null?z.num:null);	

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:214:1: crystalType returns [String crystalType] : TYPE e= STRING ;
    public final String crystalType() throws RecognitionException {
        String crystalType = null;


        Token e=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:215:2: ( TYPE e= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:215:4: TYPE e= STRING
            {
            match(input,TYPE,FOLLOW_TYPE_in_crystalType482); 

            e=(Token)match(input,STRING,FOLLOW_STRING_in_crystalType486); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:218:1: crystalDDM returns [int value] : ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword ;
    public final int crystalDDM() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:219:2: ( ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:219:4: ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword
            {
            if ( input.LA(1)==DDM||input.LA(1)==DIFFRACTIONDECAYMODEL ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalDDMKeyword_in_crystalDDM540);
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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:222:1: crystalDDMKeyword returns [int value] : ( SIMPLE | LINEAR | LEAL );
    public final int crystalDDMKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:223:2: ( SIMPLE | LINEAR | LEAL )
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
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:223:4: SIMPLE
                    {
                    match(input,SIMPLE,FOLLOW_SIMPLE_in_crystalDDMKeyword688); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:224:4: LINEAR
                    {
                    match(input,LINEAR,FOLLOW_LINEAR_in_crystalDDMKeyword695); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:225:4: LEAL
                    {
                    match(input,LEAL,FOLLOW_LEAL_in_crystalDDMKeyword702); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:231:1: crystalDecayParam returns [Double gammaParam, Double b0Param, Double betaParam] : DECAYPARAM a= FLOAT b= FLOAT c= FLOAT ;
    public final InputfileParser.crystalDecayParam_return crystalDecayParam() throws RecognitionException {
        InputfileParser.crystalDecayParam_return retval = new InputfileParser.crystalDecayParam_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:232:2: ( DECAYPARAM a= FLOAT b= FLOAT c= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:232:4: DECAYPARAM a= FLOAT b= FLOAT c= FLOAT
            {
            match(input,DECAYPARAM,FOLLOW_DECAYPARAM_in_crystalDecayParam822); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam826); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam830); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam834); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:235:1: crystalCoefcalc returns [int value] : ABSCOEFCALC a= crystalCoefcalcKeyword ;
    public final int crystalCoefcalc() throws RecognitionException {
        int value = 0;


        int a =0;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:236:2: ( ABSCOEFCALC a= crystalCoefcalcKeyword )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:236:4: ABSCOEFCALC a= crystalCoefcalcKeyword
            {
            match(input,ABSCOEFCALC,FOLLOW_ABSCOEFCALC_in_crystalCoefcalc906); 

            pushFollow(FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc910);
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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:238:1: crystalCoefcalcKeyword returns [int value] : ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS );
    public final int crystalCoefcalcKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:239:2: ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS )
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
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:239:4: DUMMY
                    {
                    match(input,DUMMY,FOLLOW_DUMMY_in_crystalCoefcalcKeyword989); 

                     value = 1;

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:240:4: AVERAGE
                    {
                    match(input,AVERAGE,FOLLOW_AVERAGE_in_crystalCoefcalcKeyword999); 

                     value = 1;

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:241:4: DEFAULT
                    {
                    match(input,DEFAULT,FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1007); 

                     value = 2;

                    }
                    break;
                case 4 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:242:4: RDJAVA
                    {
                    match(input,RDJAVA,FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1015); 

                     value = 2;

                    }
                    break;
                case 5 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:243:4: RDFORTAN
                    {
                    match(input,RDFORTAN,FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1022); 

                     value = 3;

                    }
                    break;
                case 6 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:244:4: PDB
                    {
                    match(input,PDB,FOLLOW_PDB_in_crystalCoefcalcKeyword1029); 

                     value = 4;

                    }
                    break;
                case 7 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:245:4: SAXS
                    {
                    match(input,SAXS,FOLLOW_SAXS_in_crystalCoefcalcKeyword1039); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:255:1: crystalDim returns [Map<Object, Object> properties] : DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT ) ;
    public final Map<Object, Object> crystalDim() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token c=null;
        Token d=null;

         
        		properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:258:3: ( DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT ) )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:258:5: DIMENSION (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT )
            {
            match(input,DIMENSION,FOLLOW_DIMENSION_in_crystalDim1277); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:259:2: (a= FLOAT b= FLOAT c= FLOAT |d= FLOAT )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==FLOAT) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==FLOAT) ) {
                    alt6=1;
                }
                else if ( (LA6_1==EOF||(LA6_1 >= ABSCOEFCALC && LA6_1 <= ANGLEP)||(LA6_1 >= BEAM && LA6_1 <= CALCULATEESCAPE)||(LA6_1 >= CONTAINERDENSITY && LA6_1 <= DECAYPARAM)||(LA6_1 >= DIFFRACTIONDECAYMODEL && LA6_1 <= DIMENSION)||(LA6_1 >= MATERIALELEMENTS && LA6_1 <= MATERIALTYPE)||LA6_1==MODELFILE||(LA6_1 >= NUMDNA && LA6_1 <= NUMRNA)||LA6_1==PDBNAME||(LA6_1 >= PIXELSPERMICRON && LA6_1 <= PROTEINHEAVYATOMS)||(LA6_1 >= SOLVENTFRACTION && LA6_1 <= SOLVENTHEAVYCONC)||(LA6_1 >= TYPE && LA6_1 <= UNITCELL)||(LA6_1 >= WEDGE && LA6_1 <= WIREFRAMETYPE)) ) {
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
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:260:7: a= FLOAT b= FLOAT c= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1290); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1294); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1298); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((a!=null?a.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((b!=null?b.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble((c!=null?c.getText():null))); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:263:7: d= FLOAT
                    {
                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1311); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:267:1: crystalAngP returns [double value] : ANGLEP a= FLOAT ;
    public final double crystalAngP() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:268:2: ( ANGLEP a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:268:4: ANGLEP a= FLOAT
            {
            match(input,ANGLEP,FOLLOW_ANGLEP_in_crystalAngP1388); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngP1392); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:272:1: crystalAngL returns [double value] : ANGLEL a= FLOAT ;
    public final double crystalAngL() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:273:2: ( ANGLEL a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:273:4: ANGLEL a= FLOAT
            {
            match(input,ANGLEL,FOLLOW_ANGLEL_in_crystalAngL1447); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngL1451); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:277:1: crystalPPM returns [double ppm] : PIXELSPERMICRON FLOAT ;
    public final double crystalPPM() throws RecognitionException {
        double ppm = 0.0;


        Token FLOAT1=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:278:2: ( PIXELSPERMICRON FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:278:4: PIXELSPERMICRON FLOAT
            {
            match(input,PIXELSPERMICRON,FOLLOW_PIXELSPERMICRON_in_crystalPPM1505); 

            FLOAT1=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalPPM1507); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:281:1: unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC] : UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? ;
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
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:282:2: ( UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:282:4: UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )?
            {
            match(input,UNITCELL,FOLLOW_UNITCELL_in_unitcell1605); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1609); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1613); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1617); 

            retval.dimA = Double.parseDouble((a!=null?a.getText():null));
            		 retval.dimB = Double.parseDouble((b!=null?b.getText():null));
            		 retval.dimC = Double.parseDouble((c!=null?c.getText():null)); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:286:7: (al= FLOAT be= FLOAT ga= FLOAT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FLOAT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:286:8: al= FLOAT be= FLOAT ga= FLOAT
                    {
                    al=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1632); 

                    be=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1636); 

                    ga=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1640); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:294:1: proteinConcentration returns [Double proteinConc] : ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT ;
    public final Double proteinConcentration() throws RecognitionException {
        Double proteinConc = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:295:2: ( ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:295:4: ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT
            {
            if ( (input.LA(1) >= PROTEINCONC && input.LA(1) <= PROTEINCONCENTRATION) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_proteinConcentration1728); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:299:1: nummonomers returns [int value] : NUMMONOMERS a= FLOAT ;
    public final int nummonomers() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:300:2: ( NUMMONOMERS a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:300:4: NUMMONOMERS a= FLOAT
            {
            match(input,NUMMONOMERS,FOLLOW_NUMMONOMERS_in_nummonomers1910); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_nummonomers1914); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:303:1: numresidues returns [int value] : NUMRESIDUES a= FLOAT ;
    public final int numresidues() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:304:2: ( NUMRESIDUES a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:304:4: NUMRESIDUES a= FLOAT
            {
            match(input,NUMRESIDUES,FOLLOW_NUMRESIDUES_in_numresidues1991); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numresidues1995); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:307:1: numRNA returns [int value] : NUMRNA a= FLOAT ;
    public final int numRNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:308:2: ( NUMRNA a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:308:4: NUMRNA a= FLOAT
            {
            match(input,NUMRNA,FOLLOW_NUMRNA_in_numRNA2073); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numRNA2077); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:311:1: numDNA returns [int value] : NUMDNA a= FLOAT ;
    public final int numDNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:312:2: ( NUMDNA a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:312:4: NUMDNA a= FLOAT
            {
            match(input,NUMDNA,FOLLOW_NUMDNA_in_numDNA2130); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numDNA2134); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:315:1: heavyProteinAtoms returns [List<String> names, List<Double> num;] : PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavyProteinAtoms_return heavyProteinAtoms() throws RecognitionException {
        InputfileParser.heavyProteinAtoms_return retval = new InputfileParser.heavyProteinAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:320:2: ( PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:320:4: PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,PROTEINHEAVYATOMS,FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2190); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:320:22: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:320:23: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavyProteinAtoms2195); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavyProteinAtoms2199); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:324:1: heavySolutionConc returns [List<String> names, List<Double> num;] : SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavySolutionConc_return heavySolutionConc() throws RecognitionException {
        InputfileParser.heavySolutionConc_return retval = new InputfileParser.heavySolutionConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:329:2: ( SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:329:4: SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SOLVENTHEAVYCONC,FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc2346); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:329:21: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:329:22: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavySolutionConc2351); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavySolutionConc2355); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:332:1: solventFraction returns [double solFrac] : SOLVENTFRACTION a= FLOAT ;
    public final double solventFraction() throws RecognitionException {
        double solFrac = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:333:2: ( SOLVENTFRACTION a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:333:4: SOLVENTFRACTION a= FLOAT
            {
            match(input,SOLVENTFRACTION,FOLLOW_SOLVENTFRACTION_in_solventFraction2461); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_solventFraction2465); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:336:1: pdb returns [String pdb] : PDBNAME a= STRING ;
    public final String pdb() throws RecognitionException {
        String pdb = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:337:2: ( PDBNAME a= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:337:4: PDBNAME a= STRING
            {
            match(input,PDBNAME,FOLLOW_PDBNAME_in_pdb2562); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_pdb2566); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:340:1: wireframeType returns [String value] : WIREFRAMETYPE a= STRING ;
    public final String wireframeType() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:341:2: ( WIREFRAMETYPE a= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:341:4: WIREFRAMETYPE a= STRING
            {
            match(input,WIREFRAMETYPE,FOLLOW_WIREFRAMETYPE_in_wireframeType2603); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_wireframeType2607); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:344:1: modelFile returns [String value] : MODELFILE a= STRING ;
    public final String modelFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:345:2: ( MODELFILE a= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:345:4: MODELFILE a= STRING
            {
            match(input,MODELFILE,FOLLOW_MODELFILE_in_modelFile2695); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_modelFile2699); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:348:1: calculateEscape returns [String value] : CALCULATEESCAPE a= STRING ;
    public final String calculateEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:349:2: ( CALCULATEESCAPE a= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:349:4: CALCULATEESCAPE a= STRING
            {
            match(input,CALCULATEESCAPE,FOLLOW_CALCULATEESCAPE_in_calculateEscape2766); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculateEscape2770); 

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



    // $ANTLR start "crystalContainerMaterial"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:353:1: crystalContainerMaterial returns [int value] : ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword ;
    public final int crystalContainerMaterial() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:354:2: ( ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:354:4: ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword
            {
            if ( input.LA(1)==CONTAINERMATERIALTYPE||input.LA(1)==MATERIALTYPE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial2884);
            e=crystalContainerKeyword();

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
    // $ANTLR end "crystalContainerMaterial"



    // $ANTLR start "crystalContainerKeyword"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:357:1: crystalContainerKeyword returns [int value] : ( NONE | MIXTURE | ELEMENTAL );
    public final int crystalContainerKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:358:2: ( NONE | MIXTURE | ELEMENTAL )
            int alt10=3;
            switch ( input.LA(1) ) {
            case NONE:
                {
                alt10=1;
                }
                break;
            case MIXTURE:
                {
                alt10=2;
                }
                break;
            case ELEMENTAL:
                {
                alt10=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }

            switch (alt10) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:358:4: NONE
                    {
                    match(input,NONE,FOLLOW_NONE_in_crystalContainerKeyword3077); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:359:4: MIXTURE
                    {
                    match(input,MIXTURE,FOLLOW_MIXTURE_in_crystalContainerKeyword3086); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:360:4: ELEMENTAL
                    {
                    match(input,ELEMENTAL,FOLLOW_ELEMENTAL_in_crystalContainerKeyword3094); 

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
    // $ANTLR end "crystalContainerKeyword"



    // $ANTLR start "containerThickness"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:366:1: containerThickness returns [double value] : CONTAINERTHICKNESS a= FLOAT ;
    public final double containerThickness() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:367:2: ( CONTAINERTHICKNESS a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:367:4: CONTAINERTHICKNESS a= FLOAT
            {
            match(input,CONTAINERTHICKNESS,FOLLOW_CONTAINERTHICKNESS_in_containerThickness3234); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerThickness3238); 

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



    // $ANTLR start "containerMaterialMixture"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:370:1: containerMaterialMixture returns [String value] : ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING ;
    public final String containerMaterialMixture() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:371:2: ( ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:371:4: ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING
            {
            if ( input.LA(1)==CONTAINERMATERIALMIXTURE||input.LA(1)==MATERIALMIXTURE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_containerMaterialMixture3359); 

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
    // $ANTLR end "containerMaterialMixture"


    public static class containerMaterialElements_return extends ParserRuleReturnScope {
        public List<String> names;
        public List<Double> num;;
    };


    // $ANTLR start "containerMaterialElements"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:375:1: containerMaterialElements returns [List<String> names, List<Double> num;] : ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.containerMaterialElements_return containerMaterialElements() throws RecognitionException {
        InputfileParser.containerMaterialElements_return retval = new InputfileParser.containerMaterialElements_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:380:2: ( ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:380:4: ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+
            {
            if ( input.LA(1)==CONTAINERMATERIALELEMENTS||input.LA(1)==MATERIALELEMENTS ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:380:51: (a= ELEMENT b= FLOAT )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==ELEMENT) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:380:52: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_containerMaterialElements3595); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerMaterialElements3599); 

            	    retval.names.add((a!=null?a.getText():null)); retval.num.add(Double.parseDouble((b!=null?b.getText():null))); 

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
    // $ANTLR end "containerMaterialElements"



    // $ANTLR start "containerDensity"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:384:1: containerDensity returns [double value] : CONTAINERDENSITY a= FLOAT ;
    public final double containerDensity() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:385:2: ( CONTAINERDENSITY a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:385:4: CONTAINERDENSITY a= FLOAT
            {
            match(input,CONTAINERDENSITY,FOLLOW_CONTAINERDENSITY_in_containerDensity3834); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerDensity3838); 

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
    // $ANTLR end "containerDensity"


    protected static class beam_scope {
        String beamType;
        HashMap<Object, Object> beamProperties;
    }
    protected Stack beam_stack = new Stack();



    // $ANTLR start "beam"
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:390:1: beam returns [Beam bObj] : BEAM ( beamLine )+ ;
    public final Beam beam() throws RecognitionException {
        beam_stack.push(new beam_scope());
        Beam bObj = null;


         
        		((beam_scope)beam_stack.peek()).beamProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:401:2: ( BEAM ( beamLine )+ )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:401:4: BEAM ( beamLine )+
            {
            match(input,BEAM,FOLLOW_BEAM_in_beam3955); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:401:9: ( beamLine )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0 >= CIRCULAR && LA12_0 <= COLLIMATION)||LA12_0==ENERGY||LA12_0==FILE||(LA12_0 >= FLUX && LA12_0 <= HORIZONTAL)||LA12_0==PIXELSIZE||LA12_0==RECTANGULAR||LA12_0==TYPE||LA12_0==VERTICAL) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:401:9: beamLine
            	    {
            	    pushFollow(FOLLOW_beamLine_in_beam3957);
            	    beamLine();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:404:1: beamLine : ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize );
    public final void beamLine() throws RecognitionException {
        Token a=null;
        Double b =null;

        InputfileParser.beamFWHM_return c =null;

        Double d =null;

        Map<Object, Object> e =null;

        String f =null;

        Map<Object, Object> g =null;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:405:2: ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize )
            int alt13=7;
            switch ( input.LA(1) ) {
            case TYPE:
                {
                alt13=1;
                }
                break;
            case FLUX:
                {
                alt13=2;
                }
                break;
            case FWHM:
                {
                alt13=3;
                }
                break;
            case ENERGY:
                {
                alt13=4;
                }
                break;
            case CIRCULAR:
            case COLLIMATION:
            case HORIZONTAL:
            case RECTANGULAR:
            case VERTICAL:
                {
                alt13=5;
                }
                break;
            case FILE:
                {
                alt13=6;
                }
                break;
            case PIXELSIZE:
                {
                alt13=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:405:4: TYPE a= STRING
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_beamLine3996); 

                    a=(Token)match(input,STRING,FOLLOW_STRING_in_beamLine4000); 

                     ((beam_scope)beam_stack.peek()).beamType = (a!=null?a.getText():null); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:406:4: b= beamFlux
                    {
                    pushFollow(FOLLOW_beamFlux_in_beamLine4018);
                    b=beamFlux();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FLUX, b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:407:4: c= beamFWHM
                    {
                    pushFollow(FOLLOW_beamFWHM_in_beamLine4030);
                    c=beamFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_X, (c!=null?c.x:null)); 
                    	                           ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_Y, (c!=null?c.y:null)); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:409:4: d= beamEnergy
                    {
                    pushFollow(FOLLOW_beamEnergy_in_beamLine4042);
                    d=beamEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_ENERGY, d); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:410:4: e= beamCollimation
                    {
                    pushFollow(FOLLOW_beamCollimation_in_beamLine4054);
                    e=beamCollimation();

                    state._fsp--;


                     if (e != null) {
                    							    ((beam_scope)beam_stack.peek()).beamProperties.putAll(e);
                    							   } 

                    }
                    break;
                case 6 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:413:4: f= beamFile
                    {
                    pushFollow(FOLLOW_beamFile_in_beamLine4065);
                    f=beamFile();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXTFILE, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:414:4: g= beamPixelSize
                    {
                    pushFollow(FOLLOW_beamPixelSize_in_beamLine4086);
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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:417:1: beamFlux returns [Double flux] : FLUX a= FLOAT ;
    public final Double beamFlux() throws RecognitionException {
        Double flux = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:418:2: ( FLUX a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:418:4: FLUX a= FLOAT
            {
            match(input,FLUX,FOLLOW_FLUX_in_beamFlux4110); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFlux4114); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:421:1: beamFWHM returns [Double x, Double y] : FWHM a= FLOAT b= FLOAT ;
    public final InputfileParser.beamFWHM_return beamFWHM() throws RecognitionException {
        InputfileParser.beamFWHM_return retval = new InputfileParser.beamFWHM_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:422:2: ( FWHM a= FLOAT b= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:422:4: FWHM a= FLOAT b= FLOAT
            {
            match(input,FWHM,FOLLOW_FWHM_in_beamFWHM4156); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM4160); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM4164); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:425:1: beamEnergy returns [Double energy] : ENERGY a= FLOAT ( KEV )? ;
    public final Double beamEnergy() throws RecognitionException {
        Double energy = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:426:2: ( ENERGY a= FLOAT ( KEV )? )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:426:4: ENERGY a= FLOAT ( KEV )?
            {
            match(input,ENERGY,FOLLOW_ENERGY_in_beamEnergy4206); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamEnergy4210); 

            energy = Double.parseDouble((a!=null?a.getText():null));

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:427:2: ( KEV )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==KEV) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:427:4: KEV
                    {
                    match(input,KEV,FOLLOW_KEV_in_beamEnergy4217); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:432:1: beamFile returns [String filename] : FILE a= STRING ;
    public final String beamFile() throws RecognitionException {
        String filename = null;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:433:2: ( FILE a= STRING )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:433:4: FILE a= STRING
            {
            match(input,FILE,FOLLOW_FILE_in_beamFile4295); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_beamFile4299); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:437:1: beamPixelSize returns [Map<Object, Object> properties] : PIXELSIZE a= FLOAT b= FLOAT ;
    public final Map<Object, Object> beamPixelSize() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:438:5: ( PIXELSIZE a= FLOAT b= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:438:7: PIXELSIZE a= FLOAT b= FLOAT
            {
            match(input,PIXELSIZE,FOLLOW_PIXELSIZE_in_beamPixelSize4346); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize4350); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize4354); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:445:1: beamCollimation returns [Map<Object, Object> properties] : ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT );
    public final Map<Object, Object> beamCollimation() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token d=null;
        Token e=null;

         
        		properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:449:2: ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT )
            int alt15=5;
            switch ( input.LA(1) ) {
            case COLLIMATION:
                {
                alt15=1;
                }
                break;
            case RECTANGULAR:
                {
                alt15=2;
                }
                break;
            case CIRCULAR:
                {
                alt15=3;
                }
                break;
            case HORIZONTAL:
                {
                alt15=4;
                }
                break;
            case VERTICAL:
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
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:449:4: COLLIMATION
                    {
                    match(input,COLLIMATION,FOLLOW_COLLIMATION_in_beamCollimation4433); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:450:4: RECTANGULAR a= FLOAT b= FLOAT
                    {
                    match(input,RECTANGULAR,FOLLOW_RECTANGULAR_in_beamCollimation4439); 

                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4443); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4447); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((a!=null?a.getText():null)));
                    	                                properties.put(Beam.BEAM_COLL_V, Double.parseDouble((b!=null?b.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:452:4: CIRCULAR FLOAT
                    {
                    match(input,CIRCULAR,FOLLOW_CIRCULAR_in_beamCollimation4454); 

                    match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4456); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:453:4: HORIZONTAL d= FLOAT
                    {
                    match(input,HORIZONTAL,FOLLOW_HORIZONTAL_in_beamCollimation4462); 

                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4466); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((d!=null?d.getText():null))); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:454:4: VERTICAL e= FLOAT
                    {
                    match(input,VERTICAL,FOLLOW_VERTICAL_in_beamCollimation4473); 

                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4477); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:464:1: wedge returns [Wedge wObj] : WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ ;
    public final Wedge wedge() throws RecognitionException {
        wedge_stack.push(new wedge_scope());
        Wedge wObj = null;


        Token a=null;
        Token b=null;

         

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:483:2: ( WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:483:4: WEDGE a= FLOAT b= FLOAT ( wedgeLine )+
            {
            match(input,WEDGE,FOLLOW_WEDGE_in_wedge4790); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge4794); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge4798); 

            ((wedge_scope)wedge_stack.peek()).startAng = Double.parseDouble((a!=null?a.getText():null));
            							 ((wedge_scope)wedge_stack.peek()).endAng = Double.parseDouble((b!=null?b.getText():null)); 

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:485:4: ( wedgeLine )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==ANGULARRESOLUTION||LA16_0==EXPOSURETIME||LA16_0==ROTAXBEAMOFFSET||LA16_0==STARTOFFSET||LA16_0==TRANSLATEPERDEGREE) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:485:4: wedgeLine
            	    {
            	    pushFollow(FOLLOW_wedgeLine_in_wedge4805);
            	    wedgeLine();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:488:1: wedgeLine : (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset );
    public final void wedgeLine() throws RecognitionException {
        double a =0.0;

        double b =0.0;

        InputfileParser.wedgeStartOffset_return c =null;

        InputfileParser.wedgeTranslate_return d =null;

        double e =0.0;


        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:489:2: (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset )
            int alt17=5;
            switch ( input.LA(1) ) {
            case EXPOSURETIME:
                {
                alt17=1;
                }
                break;
            case ANGULARRESOLUTION:
                {
                alt17=2;
                }
                break;
            case STARTOFFSET:
                {
                alt17=3;
                }
                break;
            case TRANSLATEPERDEGREE:
                {
                alt17=4;
                }
                break;
            case ROTAXBEAMOFFSET:
                {
                alt17=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }

            switch (alt17) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:489:4: a= wedgeExposure
                    {
                    pushFollow(FOLLOW_wedgeExposure_in_wedgeLine4849);
                    a=wedgeExposure();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).expTime =a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:490:4: b= wedgeAngRes
                    {
                    pushFollow(FOLLOW_wedgeAngRes_in_wedgeLine4859);
                    b=wedgeAngRes();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).angRes =b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:491:4: c= wedgeStartOffset
                    {
                    pushFollow(FOLLOW_wedgeStartOffset_in_wedgeLine4870);
                    c=wedgeStartOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).offsetX =(c!=null?c.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetY =(c!=null?c.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetZ =(c!=null?c.z:null); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:494:4: d= wedgeTranslate
                    {
                    pushFollow(FOLLOW_wedgeTranslate_in_wedgeLine4880);
                    d=wedgeTranslate();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).translateX =(d!=null?d.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateY =(d!=null?d.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateZ =(d!=null?d.z:null); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:497:4: e= wedgeRotAxBeamOffset
                    {
                    pushFollow(FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine4890);
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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:500:1: wedgeExposure returns [double value] : EXPOSURETIME a= FLOAT ;
    public final double wedgeExposure() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:501:2: ( EXPOSURETIME a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:501:4: EXPOSURETIME a= FLOAT
            {
            match(input,EXPOSURETIME,FOLLOW_EXPOSURETIME_in_wedgeExposure4907); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeExposure4911); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:504:1: wedgeAngRes returns [double res] : ANGULARRESOLUTION a= FLOAT ;
    public final double wedgeAngRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:505:2: ( ANGULARRESOLUTION a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:505:4: ANGULARRESOLUTION a= FLOAT
            {
            match(input,ANGULARRESOLUTION,FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes4993); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeAngRes4997); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:508:1: wedgeStartOffset returns [Double x, Double y, Double z] : STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeStartOffset_return wedgeStartOffset() throws RecognitionException {
        InputfileParser.wedgeStartOffset_return retval = new InputfileParser.wedgeStartOffset_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:509:2: ( STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:509:4: STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,STARTOFFSET,FOLLOW_STARTOFFSET_in_wedgeStartOffset5104); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset5108); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset5112); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:510:17: (c= FLOAT )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==FLOAT) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:510:17: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset5133); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:514:1: wedgeTranslate returns [Double x, Double y, Double z] : TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeTranslate_return wedgeTranslate() throws RecognitionException {
        InputfileParser.wedgeTranslate_return retval = new InputfileParser.wedgeTranslate_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:515:2: ( TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:515:4: TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,TRANSLATEPERDEGREE,FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate5227); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate5231); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate5235); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:516:24: (c= FLOAT )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==FLOAT) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:516:24: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate5263); 

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
    // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:520:1: wedgeRotAxBeamOffset returns [double delta] : ROTAXBEAMOFFSET a= FLOAT ;
    public final double wedgeRotAxBeamOffset() throws RecognitionException {
        double delta = 0.0;


        Token a=null;

        try {
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:521:2: ( ROTAXBEAMOFFSET a= FLOAT )
            // C:\\Users\\orie2707\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:521:4: ROTAXBEAMOFFSET a= FLOAT
            {
            match(input,ROTAXBEAMOFFSET,FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset5399); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeRotAxBeamOffset5403); 

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


 

    public static final BitSet FOLLOW_crystal_in_configfile47 = new BitSet(new long[]{0x0000000000080200L,0x0000000000000040L});
    public static final BitSet FOLLOW_wedge_in_configfile65 = new BitSet(new long[]{0x0000000000080200L,0x0000000000000040L});
    public static final BitSet FOLLOW_beam_in_configfile85 = new BitSet(new long[]{0x0000000000080200L,0x0000000000000040L});
    public static final BitSet FOLLOW_EOF_in_configfile105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CRYSTAL_in_crystal134 = new BitSet(new long[]{0xC0F5EB8001B7C470L,0x0000000000000098L});
    public static final BitSet FOLLOW_crystalLine_in_crystal136 = new BitSet(new long[]{0xC0F5EB8001B7C472L,0x0000000000000098L});
    public static final BitSet FOLLOW_crystalType_in_crystalLine192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalDDM_in_crystalLine203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalCoefcalc_in_crystalLine215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalDim_in_crystalLine225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalPPM_in_crystalLine236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalAngP_in_crystalLine247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalAngL_in_crystalLine258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalDecayParam_in_crystalLine269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerThickness_in_crystalLine279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerDensity_in_crystalLine289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalContainerMaterial_in_crystalLine299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerMaterialMixture_in_crystalLine308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unitcell_in_crystalLine317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nummonomers_in_crystalLine328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numresidues_in_crystalLine339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numRNA_in_crystalLine350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numDNA_in_crystalLine363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_heavyProteinAtoms_in_crystalLine376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_heavySolutionConc_in_crystalLine385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_solventFraction_in_crystalLine394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pdb_in_crystalLine404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wireframeType_in_crystalLine417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modelFile_in_crystalLine428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_calculateEscape_in_crystalLine440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_proteinConcentration_in_crystalLine450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerMaterialElements_in_crystalLine459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_crystalType482 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_crystalType486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalDDM528 = new BitSet(new long[]{0x2000006000000000L});
    public static final BitSet FOLLOW_crystalDDMKeyword_in_crystalDDM540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIMPLE_in_crystalDDMKeyword688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LINEAR_in_crystalDDMKeyword695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEAL_in_crystalDDMKeyword702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECAYPARAM_in_crystalDecayParam822 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam826 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam830 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSCOEFCALC_in_crystalCoefcalc906 = new BitSet(new long[]{0x1302000002400100L});
    public static final BitSet FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DUMMY_in_crystalCoefcalcKeyword989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVERAGE_in_crystalCoefcalcKeyword999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDB_in_crystalCoefcalcKeyword1029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXS_in_crystalCoefcalcKeyword1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_crystalDim1277 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1290 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1294 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEP_in_crystalAngP1388 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngP1392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEL_in_crystalAngL1447 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngL1451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSPERMICRON_in_crystalPPM1505 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalPPM1507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNITCELL_in_unitcell1605 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1609 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1613 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1617 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1632 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1636 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_proteinConcentration1718 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_proteinConcentration1728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMMONOMERS_in_nummonomers1910 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_nummonomers1914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRESIDUES_in_numresidues1991 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_numresidues1995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRNA_in_numRNA2073 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_numRNA2077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMDNA_in_numDNA2130 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_numDNA2134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2190 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavyProteinAtoms2195 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavyProteinAtoms2199 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc2346 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavySolutionConc2351 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavySolutionConc2355 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_SOLVENTFRACTION_in_solventFraction2461 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_solventFraction2465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDBNAME_in_pdb2562 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_pdb2566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WIREFRAMETYPE_in_wireframeType2603 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_wireframeType2607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODELFILE_in_modelFile2695 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_modelFile2699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEESCAPE_in_calculateEscape2766 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_calculateEscape2770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalContainerMaterial2872 = new BitSet(new long[]{0x0000140008000000L});
    public static final BitSet FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial2884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NONE_in_crystalContainerKeyword3077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIXTURE_in_crystalContainerKeyword3086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELEMENTAL_in_crystalContainerKeyword3094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINERTHICKNESS_in_containerThickness3234 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerThickness3238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialMixture3349 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_containerMaterialMixture3359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialElements3584 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ELEMENT_in_containerMaterialElements3595 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerMaterialElements3599 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_CONTAINERDENSITY_in_containerDensity3834 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerDensity3838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEAM_in_beam3955 = new BitSet(new long[]{0x0408000E90001800L,0x0000000000000028L});
    public static final BitSet FOLLOW_beamLine_in_beam3957 = new BitSet(new long[]{0x0408000E90001802L,0x0000000000000028L});
    public static final BitSet FOLLOW_TYPE_in_beamLine3996 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_beamLine4000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFlux_in_beamLine4018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFWHM_in_beamLine4030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamEnergy_in_beamLine4042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamCollimation_in_beamLine4054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFile_in_beamLine4065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamPixelSize_in_beamLine4086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLUX_in_beamFlux4110 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFlux4114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FWHM_in_beamFWHM4156 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM4160 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM4164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGY_in_beamEnergy4206 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamEnergy4210 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_KEV_in_beamEnergy4217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_beamFile4295 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_beamFile4299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSIZE_in_beamPixelSize4346 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize4350 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize4354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLIMATION_in_beamCollimation4433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RECTANGULAR_in_beamCollimation4439 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4443 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCULAR_in_beamCollimation4454 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HORIZONTAL_in_beamCollimation4462 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERTICAL_in_beamCollimation4473 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WEDGE_in_wedge4790 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge4794 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge4798 = new BitSet(new long[]{0x0800000040000080L,0x0000000000000005L});
    public static final BitSet FOLLOW_wedgeLine_in_wedge4805 = new BitSet(new long[]{0x0800000040000082L,0x0000000000000005L});
    public static final BitSet FOLLOW_wedgeExposure_in_wedgeLine4849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeAngRes_in_wedgeLine4859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeStartOffset_in_wedgeLine4870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeTranslate_in_wedgeLine4880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine4890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURETIME_in_wedgeExposure4907 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeExposure4911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes4993 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeAngRes4997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTOFFSET_in_wedgeStartOffset5104 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset5108 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset5112 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset5133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate5227 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate5231 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate5235 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate5263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset5399 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeRotAxBeamOffset5403 = new BitSet(new long[]{0x0000000000000002L});

}