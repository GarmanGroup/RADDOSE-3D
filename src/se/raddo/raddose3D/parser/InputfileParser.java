// $ANTLR 3.4 C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g 2017-11-30 09:50:50

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABSCOEFCALC", "ANGLEL", "ANGLEP", "ANGULARRESOLUTION", "AVERAGE", "BEAM", "CALCULATEFLESCAPE", "CALCULATEPEESCAPE", "CIRCULAR", "COLLIMATION", "COMMENT", "CONTAINERDENSITY", "CONTAINERMATERIALELEMENTS", "CONTAINERMATERIALMIXTURE", "CONTAINERMATERIALTYPE", "CONTAINERTHICKNESS", "CRYSTAL", "DDM", "DECAYPARAM", "DEFAULT", "DIFFRACTIONDECAYMODEL", "DIMENSION", "DUMMY", "ELEMENT", "ELEMENTAL", "EM", "ENERGY", "EXPONENT", "EXPOSURETIME", "FILE", "FLOAT", "FLRESOLUTION", "FLUX", "FWHM", "HORIZONTAL", "KEV", "LEAL", "LINEAR", "MATERIALELEMENTS", "MATERIALMIXTURE", "MATERIALTYPE", "MIXTURE", "MODELFILE", "MOLECULARWEIGHT", "NONE", "NUMDNA", "NUMMONOMERS", "NUMRESIDUES", "NUMRNA", "PDB", "PDBNAME", "PERESOLUTION", "PIXELSIZE", "PIXELSPERMICRON", "PROTEINCONC", "PROTEINCONCENTRATION", "PROTEINHEAVYATOMS", "RDFORTAN", "RDJAVA", "RECTANGULAR", "ROTAXBEAMOFFSET", "SAXS", "SAXSSEQ", "SEQFILE", "SEQUENCE", "SEQUENCEFILE", "SIMPLE", "SOLVENTFRACTION", "SOLVENTHEAVYCONC", "STARTOFFSET", "STRING", "TRANSLATEPERDEGREE", "TYPE", "UNITCELL", "VERTICAL", "WEDGE", "WIREFRAMETYPE", "WS"
    };

    public static final int EOF=-1;
    public static final int ABSCOEFCALC=4;
    public static final int ANGLEL=5;
    public static final int ANGLEP=6;
    public static final int ANGULARRESOLUTION=7;
    public static final int AVERAGE=8;
    public static final int BEAM=9;
    public static final int CALCULATEFLESCAPE=10;
    public static final int CALCULATEPEESCAPE=11;
    public static final int CIRCULAR=12;
    public static final int COLLIMATION=13;
    public static final int COMMENT=14;
    public static final int CONTAINERDENSITY=15;
    public static final int CONTAINERMATERIALELEMENTS=16;
    public static final int CONTAINERMATERIALMIXTURE=17;
    public static final int CONTAINERMATERIALTYPE=18;
    public static final int CONTAINERTHICKNESS=19;
    public static final int CRYSTAL=20;
    public static final int DDM=21;
    public static final int DECAYPARAM=22;
    public static final int DEFAULT=23;
    public static final int DIFFRACTIONDECAYMODEL=24;
    public static final int DIMENSION=25;
    public static final int DUMMY=26;
    public static final int ELEMENT=27;
    public static final int ELEMENTAL=28;
    public static final int EM=29;
    public static final int ENERGY=30;
    public static final int EXPONENT=31;
    public static final int EXPOSURETIME=32;
    public static final int FILE=33;
    public static final int FLOAT=34;
    public static final int FLRESOLUTION=35;
    public static final int FLUX=36;
    public static final int FWHM=37;
    public static final int HORIZONTAL=38;
    public static final int KEV=39;
    public static final int LEAL=40;
    public static final int LINEAR=41;
    public static final int MATERIALELEMENTS=42;
    public static final int MATERIALMIXTURE=43;
    public static final int MATERIALTYPE=44;
    public static final int MIXTURE=45;
    public static final int MODELFILE=46;
    public static final int MOLECULARWEIGHT=47;
    public static final int NONE=48;
    public static final int NUMDNA=49;
    public static final int NUMMONOMERS=50;
    public static final int NUMRESIDUES=51;
    public static final int NUMRNA=52;
    public static final int PDB=53;
    public static final int PDBNAME=54;
    public static final int PERESOLUTION=55;
    public static final int PIXELSIZE=56;
    public static final int PIXELSPERMICRON=57;
    public static final int PROTEINCONC=58;
    public static final int PROTEINCONCENTRATION=59;
    public static final int PROTEINHEAVYATOMS=60;
    public static final int RDFORTAN=61;
    public static final int RDJAVA=62;
    public static final int RECTANGULAR=63;
    public static final int ROTAXBEAMOFFSET=64;
    public static final int SAXS=65;
    public static final int SAXSSEQ=66;
    public static final int SEQFILE=67;
    public static final int SEQUENCE=68;
    public static final int SEQUENCEFILE=69;
    public static final int SIMPLE=70;
    public static final int SOLVENTFRACTION=71;
    public static final int SOLVENTHEAVYCONC=72;
    public static final int STARTOFFSET=73;
    public static final int STRING=74;
    public static final int TRANSLATEPERDEGREE=75;
    public static final int TYPE=76;
    public static final int UNITCELL=77;
    public static final int VERTICAL=78;
    public static final int WEDGE=79;
    public static final int WIREFRAMETYPE=80;
    public static final int WS=81;

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
    public String getGrammarFileName() { return "C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g"; }


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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:41:1: configfile : (a= crystal |b= wedge |c= beam )* EOF ;
    public final void configfile() throws RecognitionException {
        Crystal a =null;

        Wedge b =null;

        Beam c =null;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:41:11: ( (a= crystal |b= wedge |c= beam )* EOF )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )* EOF
            {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )*
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
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:42:13: a= crystal
            	    {
            	    pushFollow(FOLLOW_crystal_in_configfile47);
            	    a=crystal();

            	    state._fsp--;


            	     raddoseInitializer.setCrystal(a); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:43:13: b= wedge
            	    {
            	    pushFollow(FOLLOW_wedge_in_configfile65);
            	    b=wedge();

            	    state._fsp--;


            	     raddoseInitializer.exposeWedge(b); 

            	    }
            	    break;
            	case 3 :
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:44:13: c= beam
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
        String			seqFile;
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
        Double 		   molecularWeight;
        HashMap<Object, Object> crystalProperties;
    }
    protected Stack crystal_stack = new Stack();



    // $ANTLR start "crystal"
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:49:1: crystal returns [Crystal cObj] : CRYSTAL ( crystalLine )+ ;
    public final Crystal crystal() throws RecognitionException {
        crystal_stack.push(new crystal_scope());
        Crystal cObj = null;


         
        ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = 2; // 0 = error, 1 = Simple, 2 = DEFAULT, 3 = RDV2, 4 = PDB, 5 = SAXS, 8 = EM
        		((crystal_scope)crystal_stack.peek()).crystalProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:199:2: ( CRYSTAL ( crystalLine )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:199:4: CRYSTAL ( crystalLine )+
            {
            match(input,CRYSTAL,FOLLOW_CRYSTAL_in_crystal134); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:199:12: ( crystalLine )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= ABSCOEFCALC && LA2_0 <= ANGLEP)||(LA2_0 >= CALCULATEFLESCAPE && LA2_0 <= CALCULATEPEESCAPE)||(LA2_0 >= CONTAINERDENSITY && LA2_0 <= CONTAINERTHICKNESS)||(LA2_0 >= DDM && LA2_0 <= DECAYPARAM)||(LA2_0 >= DIFFRACTIONDECAYMODEL && LA2_0 <= DIMENSION)||LA2_0==FLRESOLUTION||(LA2_0 >= MATERIALELEMENTS && LA2_0 <= MATERIALTYPE)||(LA2_0 >= MODELFILE && LA2_0 <= MOLECULARWEIGHT)||(LA2_0 >= NUMDNA && LA2_0 <= NUMRNA)||(LA2_0 >= PDBNAME && LA2_0 <= PERESOLUTION)||(LA2_0 >= PIXELSPERMICRON && LA2_0 <= PROTEINHEAVYATOMS)||LA2_0==SEQFILE||LA2_0==SEQUENCEFILE||(LA2_0 >= SOLVENTFRACTION && LA2_0 <= SOLVENTHEAVYCONC)||(LA2_0 >= TYPE && LA2_0 <= UNITCELL)||LA2_0==WIREFRAMETYPE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:199:12: crystalLine
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
              if (((crystal_scope)crystal_stack.peek()).heavySolutionConcNames != null)
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

            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 6)
            {
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromSequence(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
              													((crystal_scope)crystal_stack.peek()).numMon,
              													((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
              													((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
              													((crystal_scope)crystal_stack.peek()).solFrac, ((crystal_scope)crystal_stack.peek()).seqFile);
            }

            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 7)
            {
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromSequenceSAXS(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
              													((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
              													((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
              													((crystal_scope)crystal_stack.peek()).solFrac, ((crystal_scope)crystal_stack.peek()).proteinConc, ((crystal_scope)crystal_stack.peek()).seqFile);
            }

            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 8)
            {
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcEM(((crystal_scope)crystal_stack.peek()).numMon, ((crystal_scope)crystal_stack.peek()).numRes, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums, ((crystal_scope)crystal_stack.peek()).proteinConc, 
              													((crystal_scope)crystal_stack.peek()).molecularWeight,
              													((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC,
              													((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums);
              													
              													  													
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:202:1: crystalLine : (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculatePEEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile |bb= calculateFLEscape |cc= flResolution |dd= peResolution |ee= molecularWeight );
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

        String aa =null;

        String bb =null;

        int cc =0;

        int dd =0;

        double ee =0.0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:203:2: (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculatePEEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile |bb= calculateFLEscape |cc= flResolution |dd= peResolution |ee= molecularWeight )
            int alt3=31;
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
            case CALCULATEPEESCAPE:
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
            case SEQFILE:
            case SEQUENCEFILE:
                {
                alt3=27;
                }
                break;
            case CALCULATEFLESCAPE:
                {
                alt3=28;
                }
                break;
            case FLRESOLUTION:
                {
                alt3=29;
                }
                break;
            case PERESOLUTION:
                {
                alt3=30;
                }
                break;
            case MOLECULARWEIGHT:
                {
                alt3=31;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:203:4: a= crystalType
                    {
                    pushFollow(FOLLOW_crystalType_in_crystalLine192);
                    a=crystalType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalType = a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:204:4: b= crystalDDM
                    {
                    pushFollow(FOLLOW_crystalDDM_in_crystalLine203);
                    b=crystalDDM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalDdm = b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:205:4: c= crystalCoefcalc
                    {
                    pushFollow(FOLLOW_crystalCoefcalc_in_crystalLine215);
                    c=crystalCoefcalc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = c; 

                    }
                    break;
                case 4 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:206:4: d= crystalDim
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:209:4: e= crystalPPM
                    {
                    pushFollow(FOLLOW_crystalPPM_in_crystalLine236);
                    e=crystalPPM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, e); 

                    }
                    break;
                case 6 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:210:4: f= crystalAngP
                    {
                    pushFollow(FOLLOW_crystalAngP_in_crystalLine247);
                    f=crystalAngP();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:211:4: g= crystalAngL
                    {
                    pushFollow(FOLLOW_crystalAngL_in_crystalLine258);
                    g=crystalAngL();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, g); 

                    }
                    break;
                case 8 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:212:4: h= crystalDecayParam
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:215:4: i= containerThickness
                    {
                    pushFollow(FOLLOW_containerThickness_in_crystalLine279);
                    i=containerThickness();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerThickness = i; 

                    }
                    break;
                case 10 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:216:4: j= containerDensity
                    {
                    pushFollow(FOLLOW_containerDensity_in_crystalLine289);
                    j=containerDensity();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerDensity = j; 

                    }
                    break;
                case 11 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:217:4: k= crystalContainerMaterial
                    {
                    pushFollow(FOLLOW_crystalContainerMaterial_in_crystalLine299);
                    k=crystalContainerMaterial();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalContainerMaterial = k; 

                    }
                    break;
                case 12 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:218:4: l= containerMaterialMixture
                    {
                    pushFollow(FOLLOW_containerMaterialMixture_in_crystalLine308);
                    l=containerMaterialMixture();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerMixture = l; 

                    }
                    break;
                case 13 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:219:4: m= unitcell
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:225:4: n= nummonomers
                    {
                    pushFollow(FOLLOW_nummonomers_in_crystalLine328);
                    n=nummonomers();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numMon = n;	

                    }
                    break;
                case 15 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:226:4: o= numresidues
                    {
                    pushFollow(FOLLOW_numresidues_in_crystalLine339);
                    o=numresidues();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRes = o;	

                    }
                    break;
                case 16 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:227:4: p= numRNA
                    {
                    pushFollow(FOLLOW_numRNA_in_crystalLine350);
                    p=numRNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRNA = p;	

                    }
                    break;
                case 17 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:228:4: q= numDNA
                    {
                    pushFollow(FOLLOW_numDNA_in_crystalLine363);
                    q=numDNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numDNA = q;	

                    }
                    break;
                case 18 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:229:4: r= heavyProteinAtoms
                    {
                    pushFollow(FOLLOW_heavyProteinAtoms_in_crystalLine376);
                    r=heavyProteinAtoms();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames = (r!=null?r.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums = (r!=null?r.num:null);	

                    }
                    break;
                case 19 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:231:4: s= heavySolutionConc
                    {
                    pushFollow(FOLLOW_heavySolutionConc_in_crystalLine385);
                    s=heavySolutionConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames = (s!=null?s.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums = (s!=null?s.num:null);	

                    }
                    break;
                case 20 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:233:4: t= solventFraction
                    {
                    pushFollow(FOLLOW_solventFraction_in_crystalLine394);
                    t=solventFraction();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).solFrac = t; 

                    }
                    break;
                case 21 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:234:4: u= pdb
                    {
                    pushFollow(FOLLOW_pdb_in_crystalLine404);
                    u=pdb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).pdb = u; 

                    }
                    break;
                case 22 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:235:4: v= wireframeType
                    {
                    pushFollow(FOLLOW_wireframeType_in_crystalLine417);
                    v=wireframeType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_TYPE, v); 

                    }
                    break;
                case 23 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:236:4: w= modelFile
                    {
                    pushFollow(FOLLOW_modelFile_in_crystalLine428);
                    w=modelFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_FILE, w); 

                    }
                    break;
                case 24 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:237:4: x= calculatePEEscape
                    {
                    pushFollow(FOLLOW_calculatePEEscape_in_crystalLine440);
                    x=calculatePEEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ELECTRON_ESCAPE, x); 

                    }
                    break;
                case 25 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:238:4: y= proteinConcentration
                    {
                    pushFollow(FOLLOW_proteinConcentration_in_crystalLine450);
                    y=proteinConcentration();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).proteinConc = y;

                    }
                    break;
                case 26 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:239:4: z= containerMaterialElements
                    {
                    pushFollow(FOLLOW_containerMaterialElements_in_crystalLine459);
                    z=containerMaterialElements();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerElementNames = (z!=null?z.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).containerElementNums = (z!=null?z.num:null);	

                    }
                    break;
                case 27 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:241:4: aa= sequenceFile
                    {
                    pushFollow(FOLLOW_sequenceFile_in_crystalLine468);
                    aa=sequenceFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).seqFile = aa; 

                    }
                    break;
                case 28 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:243:4: bb= calculateFLEscape
                    {
                    pushFollow(FOLLOW_calculateFLEscape_in_crystalLine481);
                    bb=calculateFLEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_FLUORESCENT_ESCAPE, bb); 

                    }
                    break;
                case 29 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:244:4: cc= flResolution
                    {
                    pushFollow(FOLLOW_flResolution_in_crystalLine491);
                    cc=flResolution();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_FLUORESCENT_RESOLUTION, cc);

                    }
                    break;
                case 30 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:245:4: dd= peResolution
                    {
                    pushFollow(FOLLOW_peResolution_in_crystalLine502);
                    dd=peResolution();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_PHOTOELECTRON_RESOLUTION, dd);

                    }
                    break;
                case 31 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:246:4: ee= molecularWeight
                    {
                    pushFollow(FOLLOW_molecularWeight_in_crystalLine513);
                    ee=molecularWeight();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).molecularWeight = ee;	

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:251:1: crystalType returns [String crystalType] : TYPE e= STRING ;
    public final String crystalType() throws RecognitionException {
        String crystalType = null;


        Token e=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:252:2: ( TYPE e= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:252:4: TYPE e= STRING
            {
            match(input,TYPE,FOLLOW_TYPE_in_crystalType535); 

            e=(Token)match(input,STRING,FOLLOW_STRING_in_crystalType539); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:255:1: crystalDDM returns [int value] : ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword ;
    public final int crystalDDM() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:256:2: ( ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:256:4: ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword
            {
            if ( input.LA(1)==DDM||input.LA(1)==DIFFRACTIONDECAYMODEL ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalDDMKeyword_in_crystalDDM593);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:259:1: crystalDDMKeyword returns [int value] : ( SIMPLE | LINEAR | LEAL );
    public final int crystalDDMKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:260:2: ( SIMPLE | LINEAR | LEAL )
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:260:4: SIMPLE
                    {
                    match(input,SIMPLE,FOLLOW_SIMPLE_in_crystalDDMKeyword741); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:261:4: LINEAR
                    {
                    match(input,LINEAR,FOLLOW_LINEAR_in_crystalDDMKeyword748); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:262:4: LEAL
                    {
                    match(input,LEAL,FOLLOW_LEAL_in_crystalDDMKeyword755); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:268:1: crystalDecayParam returns [Double gammaParam, Double b0Param, Double betaParam] : DECAYPARAM a= FLOAT b= FLOAT c= FLOAT ;
    public final InputfileParser.crystalDecayParam_return crystalDecayParam() throws RecognitionException {
        InputfileParser.crystalDecayParam_return retval = new InputfileParser.crystalDecayParam_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:269:2: ( DECAYPARAM a= FLOAT b= FLOAT c= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:269:4: DECAYPARAM a= FLOAT b= FLOAT c= FLOAT
            {
            match(input,DECAYPARAM,FOLLOW_DECAYPARAM_in_crystalDecayParam875); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam879); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam883); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam887); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:272:1: crystalCoefcalc returns [int value] : ABSCOEFCALC a= crystalCoefcalcKeyword ;
    public final int crystalCoefcalc() throws RecognitionException {
        int value = 0;


        int a =0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:273:2: ( ABSCOEFCALC a= crystalCoefcalcKeyword )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:273:4: ABSCOEFCALC a= crystalCoefcalcKeyword
            {
            match(input,ABSCOEFCALC,FOLLOW_ABSCOEFCALC_in_crystalCoefcalc959); 

            pushFollow(FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc963);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:275:1: crystalCoefcalcKeyword returns [int value] : ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS | SEQUENCE | SAXSSEQ | EM );
    public final int crystalCoefcalcKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:276:2: ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS | SEQUENCE | SAXSSEQ | EM )
            int alt5=10;
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
            case SEQUENCE:
                {
                alt5=8;
                }
                break;
            case SAXSSEQ:
                {
                alt5=9;
                }
                break;
            case EM:
                {
                alt5=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:276:4: DUMMY
                    {
                    match(input,DUMMY,FOLLOW_DUMMY_in_crystalCoefcalcKeyword1042); 

                     value = 1;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:277:4: AVERAGE
                    {
                    match(input,AVERAGE,FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1052); 

                     value = 1;

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:278:4: DEFAULT
                    {
                    match(input,DEFAULT,FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1060); 

                     value = 2;

                    }
                    break;
                case 4 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:279:4: RDJAVA
                    {
                    match(input,RDJAVA,FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1068); 

                     value = 2;

                    }
                    break;
                case 5 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:280:4: RDFORTAN
                    {
                    match(input,RDFORTAN,FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1075); 

                     value = 3;

                    }
                    break;
                case 6 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:281:4: PDB
                    {
                    match(input,PDB,FOLLOW_PDB_in_crystalCoefcalcKeyword1082); 

                     value = 4;

                    }
                    break;
                case 7 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:282:4: SAXS
                    {
                    match(input,SAXS,FOLLOW_SAXS_in_crystalCoefcalcKeyword1092); 

                     value = 5;

                    }
                    break;
                case 8 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:283:4: SEQUENCE
                    {
                    match(input,SEQUENCE,FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1100); 

                     value = 6;

                    }
                    break;
                case 9 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:284:4: SAXSSEQ
                    {
                    match(input,SAXSSEQ,FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1107); 

                     value = 7;

                    }
                    break;
                case 10 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:285:4: EM
                    {
                    match(input,EM,FOLLOW_EM_in_crystalCoefcalcKeyword1114); 

                     value = 8;

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:298:1: crystalDim returns [Map<Object, Object> properties] : DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) ;
    public final Map<Object, Object> crystalDim() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token c=null;
        Token e=null;
        Token f=null;
        Token d=null;

         
        		properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:301:3: ( DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:301:5: DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
            {
            match(input,DIMENSION,FOLLOW_DIMENSION_in_crystalDim1465); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:302:2: (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==FLOAT) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==FLOAT) ) {
                    int LA6_2 = input.LA(3);

                    if ( (LA6_2==FLOAT) ) {
                        alt6=1;
                    }
                    else if ( (LA6_2==EOF||(LA6_2 >= ABSCOEFCALC && LA6_2 <= ANGLEP)||(LA6_2 >= BEAM && LA6_2 <= CALCULATEPEESCAPE)||(LA6_2 >= CONTAINERDENSITY && LA6_2 <= DECAYPARAM)||(LA6_2 >= DIFFRACTIONDECAYMODEL && LA6_2 <= DIMENSION)||LA6_2==FLRESOLUTION||(LA6_2 >= MATERIALELEMENTS && LA6_2 <= MATERIALTYPE)||(LA6_2 >= MODELFILE && LA6_2 <= MOLECULARWEIGHT)||(LA6_2 >= NUMDNA && LA6_2 <= NUMRNA)||(LA6_2 >= PDBNAME && LA6_2 <= PERESOLUTION)||(LA6_2 >= PIXELSPERMICRON && LA6_2 <= PROTEINHEAVYATOMS)||LA6_2==SEQFILE||LA6_2==SEQUENCEFILE||(LA6_2 >= SOLVENTFRACTION && LA6_2 <= SOLVENTHEAVYCONC)||(LA6_2 >= TYPE && LA6_2 <= UNITCELL)||(LA6_2 >= WEDGE && LA6_2 <= WIREFRAMETYPE)) ) {
                        alt6=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA6_1==EOF||(LA6_1 >= ABSCOEFCALC && LA6_1 <= ANGLEP)||(LA6_1 >= BEAM && LA6_1 <= CALCULATEPEESCAPE)||(LA6_1 >= CONTAINERDENSITY && LA6_1 <= DECAYPARAM)||(LA6_1 >= DIFFRACTIONDECAYMODEL && LA6_1 <= DIMENSION)||LA6_1==FLRESOLUTION||(LA6_1 >= MATERIALELEMENTS && LA6_1 <= MATERIALTYPE)||(LA6_1 >= MODELFILE && LA6_1 <= MOLECULARWEIGHT)||(LA6_1 >= NUMDNA && LA6_1 <= NUMRNA)||(LA6_1 >= PDBNAME && LA6_1 <= PERESOLUTION)||(LA6_1 >= PIXELSPERMICRON && LA6_1 <= PROTEINHEAVYATOMS)||LA6_1==SEQFILE||LA6_1==SEQUENCEFILE||(LA6_1 >= SOLVENTFRACTION && LA6_1 <= SOLVENTHEAVYCONC)||(LA6_1 >= TYPE && LA6_1 <= UNITCELL)||(LA6_1 >= WEDGE && LA6_1 <= WIREFRAMETYPE)) ) {
                    alt6=3;
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:303:7: a= FLOAT b= FLOAT c= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1478); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1482); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1486); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((a!=null?a.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((b!=null?b.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble((c!=null?c.getText():null))); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:306:7: e= FLOAT f= FLOAT
                    {
                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1498); 

                    f=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1502); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((e!=null?e.getText():null)));
                        		       properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((f!=null?f.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:308:7: d= FLOAT
                    {
                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1514); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:312:1: crystalAngP returns [double value] : ANGLEP a= FLOAT ;
    public final double crystalAngP() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:313:2: ( ANGLEP a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:313:4: ANGLEP a= FLOAT
            {
            match(input,ANGLEP,FOLLOW_ANGLEP_in_crystalAngP1591); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngP1595); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:317:1: crystalAngL returns [double value] : ANGLEL a= FLOAT ;
    public final double crystalAngL() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:318:2: ( ANGLEL a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:318:4: ANGLEL a= FLOAT
            {
            match(input,ANGLEL,FOLLOW_ANGLEL_in_crystalAngL1650); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngL1654); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:322:1: crystalPPM returns [double ppm] : PIXELSPERMICRON FLOAT ;
    public final double crystalPPM() throws RecognitionException {
        double ppm = 0.0;


        Token FLOAT1=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:323:2: ( PIXELSPERMICRON FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:323:4: PIXELSPERMICRON FLOAT
            {
            match(input,PIXELSPERMICRON,FOLLOW_PIXELSPERMICRON_in_crystalPPM1708); 

            FLOAT1=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalPPM1710); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:326:1: unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC] : UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? ;
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
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:327:2: ( UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:327:4: UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )?
            {
            match(input,UNITCELL,FOLLOW_UNITCELL_in_unitcell1808); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1812); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1816); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1820); 

            retval.dimA = Double.parseDouble((a!=null?a.getText():null));
            		 retval.dimB = Double.parseDouble((b!=null?b.getText():null));
            		 retval.dimC = Double.parseDouble((c!=null?c.getText():null)); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:331:7: (al= FLOAT be= FLOAT ga= FLOAT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FLOAT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:331:8: al= FLOAT be= FLOAT ga= FLOAT
                    {
                    al=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1835); 

                    be=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1839); 

                    ga=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1843); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:339:1: proteinConcentration returns [Double proteinConc] : ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT ;
    public final Double proteinConcentration() throws RecognitionException {
        Double proteinConc = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:340:2: ( ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:340:4: ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT
            {
            if ( (input.LA(1) >= PROTEINCONC && input.LA(1) <= PROTEINCONCENTRATION) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_proteinConcentration1931); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:344:1: nummonomers returns [int value] : NUMMONOMERS a= FLOAT ;
    public final int nummonomers() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:345:2: ( NUMMONOMERS a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:345:4: NUMMONOMERS a= FLOAT
            {
            match(input,NUMMONOMERS,FOLLOW_NUMMONOMERS_in_nummonomers2113); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_nummonomers2117); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:348:1: numresidues returns [int value] : NUMRESIDUES a= FLOAT ;
    public final int numresidues() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:349:2: ( NUMRESIDUES a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:349:4: NUMRESIDUES a= FLOAT
            {
            match(input,NUMRESIDUES,FOLLOW_NUMRESIDUES_in_numresidues2194); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numresidues2198); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:352:1: numRNA returns [int value] : NUMRNA a= FLOAT ;
    public final int numRNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:353:2: ( NUMRNA a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:353:4: NUMRNA a= FLOAT
            {
            match(input,NUMRNA,FOLLOW_NUMRNA_in_numRNA2276); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numRNA2280); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:356:1: numDNA returns [int value] : NUMDNA a= FLOAT ;
    public final int numDNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:357:2: ( NUMDNA a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:357:4: NUMDNA a= FLOAT
            {
            match(input,NUMDNA,FOLLOW_NUMDNA_in_numDNA2333); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numDNA2337); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:360:1: heavyProteinAtoms returns [List<String> names, List<Double> num;] : PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavyProteinAtoms_return heavyProteinAtoms() throws RecognitionException {
        InputfileParser.heavyProteinAtoms_return retval = new InputfileParser.heavyProteinAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:365:2: ( PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:365:4: PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,PROTEINHEAVYATOMS,FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2393); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:365:22: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:365:23: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavyProteinAtoms2398); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavyProteinAtoms2402); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:369:1: heavySolutionConc returns [List<String> names, List<Double> num;] : SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavySolutionConc_return heavySolutionConc() throws RecognitionException {
        InputfileParser.heavySolutionConc_return retval = new InputfileParser.heavySolutionConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:374:2: ( SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:374:4: SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SOLVENTHEAVYCONC,FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc2549); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:374:21: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:374:22: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavySolutionConc2554); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavySolutionConc2558); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:377:1: solventFraction returns [double solFrac] : SOLVENTFRACTION a= FLOAT ;
    public final double solventFraction() throws RecognitionException {
        double solFrac = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:378:2: ( SOLVENTFRACTION a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:378:4: SOLVENTFRACTION a= FLOAT
            {
            match(input,SOLVENTFRACTION,FOLLOW_SOLVENTFRACTION_in_solventFraction2664); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_solventFraction2668); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:381:1: pdb returns [String pdb] : PDBNAME a= STRING ;
    public final String pdb() throws RecognitionException {
        String pdb = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:382:2: ( PDBNAME a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:382:4: PDBNAME a= STRING
            {
            match(input,PDBNAME,FOLLOW_PDBNAME_in_pdb2765); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_pdb2769); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:385:1: wireframeType returns [String value] : WIREFRAMETYPE a= STRING ;
    public final String wireframeType() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:386:2: ( WIREFRAMETYPE a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:386:4: WIREFRAMETYPE a= STRING
            {
            match(input,WIREFRAMETYPE,FOLLOW_WIREFRAMETYPE_in_wireframeType2806); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_wireframeType2810); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:389:1: modelFile returns [String value] : MODELFILE a= STRING ;
    public final String modelFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:390:2: ( MODELFILE a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:390:4: MODELFILE a= STRING
            {
            match(input,MODELFILE,FOLLOW_MODELFILE_in_modelFile2898); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_modelFile2902); 

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



    // $ANTLR start "calculatePEEscape"
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:393:1: calculatePEEscape returns [String value] : CALCULATEPEESCAPE a= STRING ;
    public final String calculatePEEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:394:2: ( CALCULATEPEESCAPE a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:394:4: CALCULATEPEESCAPE a= STRING
            {
            match(input,CALCULATEPEESCAPE,FOLLOW_CALCULATEPEESCAPE_in_calculatePEEscape2969); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculatePEEscape2973); 

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
    // $ANTLR end "calculatePEEscape"



    // $ANTLR start "crystalContainerMaterial"
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:398:1: crystalContainerMaterial returns [int value] : ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword ;
    public final int crystalContainerMaterial() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:399:2: ( ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:399:4: ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword
            {
            if ( input.LA(1)==CONTAINERMATERIALTYPE||input.LA(1)==MATERIALTYPE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial3097);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:402:1: crystalContainerKeyword returns [int value] : ( NONE | MIXTURE | ELEMENTAL );
    public final int crystalContainerKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:403:2: ( NONE | MIXTURE | ELEMENTAL )
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:403:4: NONE
                    {
                    match(input,NONE,FOLLOW_NONE_in_crystalContainerKeyword3290); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:404:4: MIXTURE
                    {
                    match(input,MIXTURE,FOLLOW_MIXTURE_in_crystalContainerKeyword3299); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:405:4: ELEMENTAL
                    {
                    match(input,ELEMENTAL,FOLLOW_ELEMENTAL_in_crystalContainerKeyword3307); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:411:1: containerThickness returns [double value] : CONTAINERTHICKNESS a= FLOAT ;
    public final double containerThickness() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:412:2: ( CONTAINERTHICKNESS a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:412:4: CONTAINERTHICKNESS a= FLOAT
            {
            match(input,CONTAINERTHICKNESS,FOLLOW_CONTAINERTHICKNESS_in_containerThickness3447); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerThickness3451); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:415:1: containerMaterialMixture returns [String value] : ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING ;
    public final String containerMaterialMixture() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:416:2: ( ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:416:4: ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING
            {
            if ( input.LA(1)==CONTAINERMATERIALMIXTURE||input.LA(1)==MATERIALMIXTURE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_containerMaterialMixture3572); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:420:1: containerMaterialElements returns [List<String> names, List<Double> num;] : ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.containerMaterialElements_return containerMaterialElements() throws RecognitionException {
        InputfileParser.containerMaterialElements_return retval = new InputfileParser.containerMaterialElements_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:425:2: ( ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:425:4: ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+
            {
            if ( input.LA(1)==CONTAINERMATERIALELEMENTS||input.LA(1)==MATERIALELEMENTS ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:425:51: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:425:52: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_containerMaterialElements3808); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerMaterialElements3812); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:429:1: containerDensity returns [double value] : CONTAINERDENSITY a= FLOAT ;
    public final double containerDensity() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:430:2: ( CONTAINERDENSITY a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:430:4: CONTAINERDENSITY a= FLOAT
            {
            match(input,CONTAINERDENSITY,FOLLOW_CONTAINERDENSITY_in_containerDensity4047); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerDensity4051); 

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



    // $ANTLR start "sequenceFile"
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:433:1: sequenceFile returns [String value] : ( SEQUENCEFILE | SEQFILE ) a= STRING ;
    public final String sequenceFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:434:2: ( ( SEQUENCEFILE | SEQFILE ) a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:434:4: ( SEQUENCEFILE | SEQFILE ) a= STRING
            {
            if ( input.LA(1)==SEQFILE||input.LA(1)==SEQUENCEFILE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_sequenceFile4162); 

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
    // $ANTLR end "sequenceFile"



    // $ANTLR start "calculateFLEscape"
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:438:1: calculateFLEscape returns [String value] : CALCULATEFLESCAPE a= STRING ;
    public final String calculateFLEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:439:2: ( CALCULATEFLESCAPE a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:439:4: CALCULATEFLESCAPE a= STRING
            {
            match(input,CALCULATEFLESCAPE,FOLLOW_CALCULATEFLESCAPE_in_calculateFLEscape4285); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculateFLEscape4289); 

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
    // $ANTLR end "calculateFLEscape"



    // $ANTLR start "flResolution"
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:443:2: flResolution returns [int value] : FLRESOLUTION a= FLOAT ;
    public final int flResolution() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:444:2: ( FLRESOLUTION a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:444:4: FLRESOLUTION a= FLOAT
            {
            match(input,FLRESOLUTION,FOLLOW_FLRESOLUTION_in_flResolution4402); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_flResolution4406); 

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
    // $ANTLR end "flResolution"



    // $ANTLR start "peResolution"
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:447:1: peResolution returns [int value] : PERESOLUTION a= FLOAT ;
    public final int peResolution() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:448:2: ( PERESOLUTION a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:448:4: PERESOLUTION a= FLOAT
            {
            match(input,PERESOLUTION,FOLLOW_PERESOLUTION_in_peResolution4488); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_peResolution4492); 

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
    // $ANTLR end "peResolution"



    // $ANTLR start "molecularWeight"
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:451:1: molecularWeight returns [double value] : MOLECULARWEIGHT a= FLOAT ;
    public final double molecularWeight() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:452:2: ( MOLECULARWEIGHT a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:452:4: MOLECULARWEIGHT a= FLOAT
            {
            match(input,MOLECULARWEIGHT,FOLLOW_MOLECULARWEIGHT_in_molecularWeight4574); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_molecularWeight4578); 

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
    // $ANTLR end "molecularWeight"


    protected static class beam_scope {
        String beamType;
        HashMap<Object, Object> beamProperties;
    }
    protected Stack beam_stack = new Stack();



    // $ANTLR start "beam"
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:457:1: beam returns [Beam bObj] : BEAM ( beamLine )+ ;
    public final Beam beam() throws RecognitionException {
        beam_stack.push(new beam_scope());
        Beam bObj = null;


         
        		((beam_scope)beam_stack.peek()).beamProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:468:2: ( BEAM ( beamLine )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:468:4: BEAM ( beamLine )+
            {
            match(input,BEAM,FOLLOW_BEAM_in_beam4691); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:468:9: ( beamLine )+
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
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:468:9: beamLine
            	    {
            	    pushFollow(FOLLOW_beamLine_in_beam4693);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:471:1: beamLine : ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize );
    public final void beamLine() throws RecognitionException {
        Token a=null;
        Double b =null;

        InputfileParser.beamFWHM_return c =null;

        Double d =null;

        Map<Object, Object> e =null;

        String f =null;

        Map<Object, Object> g =null;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:472:2: ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize )
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:472:4: TYPE a= STRING
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_beamLine4732); 

                    a=(Token)match(input,STRING,FOLLOW_STRING_in_beamLine4736); 

                     ((beam_scope)beam_stack.peek()).beamType = (a!=null?a.getText():null); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:473:4: b= beamFlux
                    {
                    pushFollow(FOLLOW_beamFlux_in_beamLine4754);
                    b=beamFlux();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FLUX, b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:474:4: c= beamFWHM
                    {
                    pushFollow(FOLLOW_beamFWHM_in_beamLine4766);
                    c=beamFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_X, (c!=null?c.x:null)); 
                    	                           ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_Y, (c!=null?c.y:null)); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:476:4: d= beamEnergy
                    {
                    pushFollow(FOLLOW_beamEnergy_in_beamLine4778);
                    d=beamEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_ENERGY, d); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:477:4: e= beamCollimation
                    {
                    pushFollow(FOLLOW_beamCollimation_in_beamLine4790);
                    e=beamCollimation();

                    state._fsp--;


                     if (e != null) {
                    							    ((beam_scope)beam_stack.peek()).beamProperties.putAll(e);
                    							   } 

                    }
                    break;
                case 6 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:480:4: f= beamFile
                    {
                    pushFollow(FOLLOW_beamFile_in_beamLine4801);
                    f=beamFile();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXTFILE, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:481:4: g= beamPixelSize
                    {
                    pushFollow(FOLLOW_beamPixelSize_in_beamLine4822);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:484:1: beamFlux returns [Double flux] : FLUX a= FLOAT ;
    public final Double beamFlux() throws RecognitionException {
        Double flux = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:485:2: ( FLUX a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:485:4: FLUX a= FLOAT
            {
            match(input,FLUX,FOLLOW_FLUX_in_beamFlux4846); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFlux4850); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:488:1: beamFWHM returns [Double x, Double y] : FWHM a= FLOAT b= FLOAT ;
    public final InputfileParser.beamFWHM_return beamFWHM() throws RecognitionException {
        InputfileParser.beamFWHM_return retval = new InputfileParser.beamFWHM_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:489:2: ( FWHM a= FLOAT b= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:489:4: FWHM a= FLOAT b= FLOAT
            {
            match(input,FWHM,FOLLOW_FWHM_in_beamFWHM4892); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM4896); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM4900); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:492:1: beamEnergy returns [Double energy] : ENERGY a= FLOAT ( KEV )? ;
    public final Double beamEnergy() throws RecognitionException {
        Double energy = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:493:2: ( ENERGY a= FLOAT ( KEV )? )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:493:4: ENERGY a= FLOAT ( KEV )?
            {
            match(input,ENERGY,FOLLOW_ENERGY_in_beamEnergy4942); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamEnergy4946); 

            energy = Double.parseDouble((a!=null?a.getText():null));

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:494:2: ( KEV )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==KEV) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:494:4: KEV
                    {
                    match(input,KEV,FOLLOW_KEV_in_beamEnergy4953); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:499:1: beamFile returns [String filename] : FILE a= STRING ;
    public final String beamFile() throws RecognitionException {
        String filename = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:500:2: ( FILE a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:500:4: FILE a= STRING
            {
            match(input,FILE,FOLLOW_FILE_in_beamFile5031); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_beamFile5035); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:504:1: beamPixelSize returns [Map<Object, Object> properties] : PIXELSIZE a= FLOAT b= FLOAT ;
    public final Map<Object, Object> beamPixelSize() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:505:5: ( PIXELSIZE a= FLOAT b= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:505:7: PIXELSIZE a= FLOAT b= FLOAT
            {
            match(input,PIXELSIZE,FOLLOW_PIXELSIZE_in_beamPixelSize5082); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize5086); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize5090); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:512:1: beamCollimation returns [Map<Object, Object> properties] : ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT );
    public final Map<Object, Object> beamCollimation() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token d=null;
        Token e=null;

         
        		properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:516:2: ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT )
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:516:4: COLLIMATION
                    {
                    match(input,COLLIMATION,FOLLOW_COLLIMATION_in_beamCollimation5169); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:517:4: RECTANGULAR a= FLOAT b= FLOAT
                    {
                    match(input,RECTANGULAR,FOLLOW_RECTANGULAR_in_beamCollimation5175); 

                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation5179); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation5183); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((a!=null?a.getText():null)));
                    	                                properties.put(Beam.BEAM_COLL_V, Double.parseDouble((b!=null?b.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:519:4: CIRCULAR FLOAT
                    {
                    match(input,CIRCULAR,FOLLOW_CIRCULAR_in_beamCollimation5190); 

                    match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation5192); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:520:4: HORIZONTAL d= FLOAT
                    {
                    match(input,HORIZONTAL,FOLLOW_HORIZONTAL_in_beamCollimation5198); 

                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation5202); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((d!=null?d.getText():null))); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:521:4: VERTICAL e= FLOAT
                    {
                    match(input,VERTICAL,FOLLOW_VERTICAL_in_beamCollimation5209); 

                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation5213); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:531:1: wedge returns [Wedge wObj] : WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ ;
    public final Wedge wedge() throws RecognitionException {
        wedge_stack.push(new wedge_scope());
        Wedge wObj = null;


        Token a=null;
        Token b=null;

         

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:550:2: ( WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:550:4: WEDGE a= FLOAT b= FLOAT ( wedgeLine )+
            {
            match(input,WEDGE,FOLLOW_WEDGE_in_wedge5526); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge5530); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge5534); 

            ((wedge_scope)wedge_stack.peek()).startAng = Double.parseDouble((a!=null?a.getText():null));
            							 ((wedge_scope)wedge_stack.peek()).endAng = Double.parseDouble((b!=null?b.getText():null)); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:552:4: ( wedgeLine )+
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
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:552:4: wedgeLine
            	    {
            	    pushFollow(FOLLOW_wedgeLine_in_wedge5541);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:555:1: wedgeLine : (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset );
    public final void wedgeLine() throws RecognitionException {
        double a =0.0;

        double b =0.0;

        InputfileParser.wedgeStartOffset_return c =null;

        InputfileParser.wedgeTranslate_return d =null;

        double e =0.0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:556:2: (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset )
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:556:4: a= wedgeExposure
                    {
                    pushFollow(FOLLOW_wedgeExposure_in_wedgeLine5585);
                    a=wedgeExposure();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).expTime =a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:557:4: b= wedgeAngRes
                    {
                    pushFollow(FOLLOW_wedgeAngRes_in_wedgeLine5595);
                    b=wedgeAngRes();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).angRes =b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:558:4: c= wedgeStartOffset
                    {
                    pushFollow(FOLLOW_wedgeStartOffset_in_wedgeLine5606);
                    c=wedgeStartOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).offsetX =(c!=null?c.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetY =(c!=null?c.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetZ =(c!=null?c.z:null); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:561:4: d= wedgeTranslate
                    {
                    pushFollow(FOLLOW_wedgeTranslate_in_wedgeLine5616);
                    d=wedgeTranslate();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).translateX =(d!=null?d.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateY =(d!=null?d.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateZ =(d!=null?d.z:null); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:564:4: e= wedgeRotAxBeamOffset
                    {
                    pushFollow(FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine5626);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:567:1: wedgeExposure returns [double value] : EXPOSURETIME a= FLOAT ;
    public final double wedgeExposure() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:568:2: ( EXPOSURETIME a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:568:4: EXPOSURETIME a= FLOAT
            {
            match(input,EXPOSURETIME,FOLLOW_EXPOSURETIME_in_wedgeExposure5643); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeExposure5647); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:571:1: wedgeAngRes returns [double res] : ANGULARRESOLUTION a= FLOAT ;
    public final double wedgeAngRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:572:2: ( ANGULARRESOLUTION a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:572:4: ANGULARRESOLUTION a= FLOAT
            {
            match(input,ANGULARRESOLUTION,FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes5729); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeAngRes5733); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:575:1: wedgeStartOffset returns [Double x, Double y, Double z] : STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeStartOffset_return wedgeStartOffset() throws RecognitionException {
        InputfileParser.wedgeStartOffset_return retval = new InputfileParser.wedgeStartOffset_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:576:2: ( STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:576:4: STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,STARTOFFSET,FOLLOW_STARTOFFSET_in_wedgeStartOffset5840); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset5844); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset5848); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:577:17: (c= FLOAT )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==FLOAT) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:577:17: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset5869); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:581:1: wedgeTranslate returns [Double x, Double y, Double z] : TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeTranslate_return wedgeTranslate() throws RecognitionException {
        InputfileParser.wedgeTranslate_return retval = new InputfileParser.wedgeTranslate_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:582:2: ( TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:582:4: TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,TRANSLATEPERDEGREE,FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate5963); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate5967); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate5971); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:583:24: (c= FLOAT )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==FLOAT) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:583:24: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate5999); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:587:1: wedgeRotAxBeamOffset returns [double delta] : ROTAXBEAMOFFSET a= FLOAT ;
    public final double wedgeRotAxBeamOffset() throws RecognitionException {
        double delta = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:588:2: ( ROTAXBEAMOFFSET a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:588:4: ROTAXBEAMOFFSET a= FLOAT
            {
            match(input,ROTAXBEAMOFFSET,FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset6135); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeRotAxBeamOffset6139); 

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


 

    public static final BitSet FOLLOW_crystal_in_configfile47 = new BitSet(new long[]{0x0000000000100200L,0x0000000000008000L});
    public static final BitSet FOLLOW_wedge_in_configfile65 = new BitSet(new long[]{0x0000000000100200L,0x0000000000008000L});
    public static final BitSet FOLLOW_beam_in_configfile85 = new BitSet(new long[]{0x0000000000100200L,0x0000000000008000L});
    public static final BitSet FOLLOW_EOF_in_configfile105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CRYSTAL_in_crystal134 = new BitSet(new long[]{0x1EDEDC08036F8C70L,0x00000000000131A8L});
    public static final BitSet FOLLOW_crystalLine_in_crystal136 = new BitSet(new long[]{0x1EDEDC08036F8C72L,0x00000000000131A8L});
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
    public static final BitSet FOLLOW_calculatePEEscape_in_crystalLine440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_proteinConcentration_in_crystalLine450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerMaterialElements_in_crystalLine459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sequenceFile_in_crystalLine468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_calculateFLEscape_in_crystalLine481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_flResolution_in_crystalLine491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_peResolution_in_crystalLine502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_molecularWeight_in_crystalLine513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_crystalType535 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_STRING_in_crystalType539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalDDM581 = new BitSet(new long[]{0x0000030000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_crystalDDMKeyword_in_crystalDDM593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIMPLE_in_crystalDDMKeyword741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LINEAR_in_crystalDDMKeyword748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEAL_in_crystalDDMKeyword755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECAYPARAM_in_crystalDecayParam875 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam879 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam883 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSCOEFCALC_in_crystalCoefcalc959 = new BitSet(new long[]{0x6020000024800100L,0x0000000000000016L});
    public static final BitSet FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DUMMY_in_crystalCoefcalcKeyword1042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDB_in_crystalCoefcalcKeyword1082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXS_in_crystalCoefcalcKeyword1092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EM_in_crystalCoefcalcKeyword1114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_crystalDim1465 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1478 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1482 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1498 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEP_in_crystalAngP1591 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngP1595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEL_in_crystalAngL1650 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngL1654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSPERMICRON_in_crystalPPM1708 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalPPM1710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNITCELL_in_unitcell1808 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1812 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1816 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1820 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1835 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1839 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_proteinConcentration1921 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_proteinConcentration1931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMMONOMERS_in_nummonomers2113 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_nummonomers2117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRESIDUES_in_numresidues2194 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_numresidues2198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRNA_in_numRNA2276 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_numRNA2280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMDNA_in_numDNA2333 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_numDNA2337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2393 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavyProteinAtoms2398 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavyProteinAtoms2402 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc2549 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavySolutionConc2554 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavySolutionConc2558 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_SOLVENTFRACTION_in_solventFraction2664 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_solventFraction2668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDBNAME_in_pdb2765 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_STRING_in_pdb2769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WIREFRAMETYPE_in_wireframeType2806 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_STRING_in_wireframeType2810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODELFILE_in_modelFile2898 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_STRING_in_modelFile2902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEPEESCAPE_in_calculatePEEscape2969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_STRING_in_calculatePEEscape2973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalContainerMaterial3085 = new BitSet(new long[]{0x0001200010000000L});
    public static final BitSet FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial3097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NONE_in_crystalContainerKeyword3290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIXTURE_in_crystalContainerKeyword3299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELEMENTAL_in_crystalContainerKeyword3307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINERTHICKNESS_in_containerThickness3447 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerThickness3451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialMixture3562 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_STRING_in_containerMaterialMixture3572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialElements3797 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ELEMENT_in_containerMaterialElements3808 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerMaterialElements3812 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_CONTAINERDENSITY_in_containerDensity4047 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerDensity4051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sequenceFile4152 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_STRING_in_sequenceFile4162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEFLESCAPE_in_calculateFLEscape4285 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_STRING_in_calculateFLEscape4289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLRESOLUTION_in_flResolution4402 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_flResolution4406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERESOLUTION_in_peResolution4488 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_peResolution4492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOLECULARWEIGHT_in_molecularWeight4574 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_molecularWeight4578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEAM_in_beam4691 = new BitSet(new long[]{0x8100007240003000L,0x0000000000005000L});
    public static final BitSet FOLLOW_beamLine_in_beam4693 = new BitSet(new long[]{0x8100007240003002L,0x0000000000005000L});
    public static final BitSet FOLLOW_TYPE_in_beamLine4732 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_STRING_in_beamLine4736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFlux_in_beamLine4754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFWHM_in_beamLine4766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamEnergy_in_beamLine4778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamCollimation_in_beamLine4790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFile_in_beamLine4801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamPixelSize_in_beamLine4822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLUX_in_beamFlux4846 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFlux4850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FWHM_in_beamFWHM4892 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM4896 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM4900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGY_in_beamEnergy4942 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamEnergy4946 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_KEV_in_beamEnergy4953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_beamFile5031 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_STRING_in_beamFile5035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSIZE_in_beamPixelSize5082 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize5086 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize5090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLIMATION_in_beamCollimation5169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RECTANGULAR_in_beamCollimation5175 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation5179 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation5183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCULAR_in_beamCollimation5190 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation5192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HORIZONTAL_in_beamCollimation5198 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation5202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERTICAL_in_beamCollimation5209 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation5213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WEDGE_in_wedge5526 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge5530 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge5534 = new BitSet(new long[]{0x0000000100000080L,0x0000000000000A01L});
    public static final BitSet FOLLOW_wedgeLine_in_wedge5541 = new BitSet(new long[]{0x0000000100000082L,0x0000000000000A01L});
    public static final BitSet FOLLOW_wedgeExposure_in_wedgeLine5585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeAngRes_in_wedgeLine5595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeStartOffset_in_wedgeLine5606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeTranslate_in_wedgeLine5616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine5626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURETIME_in_wedgeExposure5643 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeExposure5647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes5729 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeAngRes5733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTOFFSET_in_wedgeStartOffset5840 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset5844 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset5848 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset5869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate5963 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate5967 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate5971 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate5999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset6135 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeRotAxBeamOffset6139 = new BitSet(new long[]{0x0000000000000002L});

}