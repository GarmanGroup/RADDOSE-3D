// $ANTLR 3.4 C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g 2017-02-02 22:27:28

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABSCOEFCALC", "ANGLEL", "ANGLEP", "ANGULARRESOLUTION", "AVERAGE", "BEAM", "CALCULATEESCAPE", "CIRCULAR", "COLLIMATION", "COMMENT", "CONTAINERDENSITY", "CONTAINERMATERIALELEMENTS", "CONTAINERMATERIALMIXTURE", "CONTAINERMATERIALTYPE", "CONTAINERTHICKNESS", "CRYSTAL", "DDM", "DECAYPARAM", "DEFAULT", "DIFFRACTIONDECAYMODEL", "DIMENSION", "DUMMY", "ELEMENT", "ELEMENTAL", "ENERGY", "EXPONENT", "EXPOSURETIME", "FILE", "FLOAT", "FLUX", "FWHM", "HORIZONTAL", "KEV", "LEAL", "LINEAR", "MATERIALELEMENTS", "MATERIALMIXTURE", "MATERIALTYPE", "MIXTURE", "MODELFILE", "NONE", "NUMDNA", "NUMMONOMERS", "NUMRESIDUES", "NUMRNA", "PDB", "PDBNAME", "PIXELSIZE", "PIXELSPERMICRON", "PROTEINCONC", "PROTEINCONCENTRATION", "PROTEINHEAVYATOMS", "RDFORTAN", "RDJAVA", "RECTANGULAR", "ROTAXBEAMOFFSET", "SAXS", "SAXSSEQ", "SEQFILE", "SEQUENCE", "SEQUENCEFILE", "SIMPLE", "SOLVENTFRACTION", "SOLVENTHEAVYCONC", "STARTOFFSET", "STRING", "TRANSLATEPERDEGREE", "TYPE", "UNITCELL", "VERTICAL", "WEDGE", "WIREFRAMETYPE", "WS"
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
    public static final int SAXSSEQ=61;
    public static final int SEQFILE=62;
    public static final int SEQUENCE=63;
    public static final int SEQUENCEFILE=64;
    public static final int SIMPLE=65;
    public static final int SOLVENTFRACTION=66;
    public static final int SOLVENTHEAVYCONC=67;
    public static final int STARTOFFSET=68;
    public static final int STRING=69;
    public static final int TRANSLATEPERDEGREE=70;
    public static final int TYPE=71;
    public static final int UNITCELL=72;
    public static final int VERTICAL=73;
    public static final int WEDGE=74;
    public static final int WIREFRAMETYPE=75;
    public static final int WS=76;

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
    public String getGrammarFileName() { return "C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g"; }


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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:41:1: configfile : (a= crystal |b= wedge |c= beam )* EOF ;
    public final void configfile() throws RecognitionException {
        Crystal a =null;

        Wedge b =null;

        Beam c =null;


        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:41:11: ( (a= crystal |b= wedge |c= beam )* EOF )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )* EOF
            {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )*
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
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:42:13: a= crystal
                    {
                    pushFollow(FOLLOW_crystal_in_configfile47);
                    a=crystal();

                    state._fsp--;


                     raddoseInitializer.setCrystal(a); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:43:13: b= wedge
                    {
                    pushFollow(FOLLOW_wedge_in_configfile65);
                    b=wedge();

                    state._fsp--;


                     raddoseInitializer.exposeWedge(b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:44:13: c= beam
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
        int         crystalDdm;
        DDM         crystalDdmClass;
        int         crystalContainerMaterial;
        Container       crystalContainerMaterialClass;
        Double          gammaParam;
        Double          b0Param;
        Double          betaParam;
        String          containerMixture;
        Double          containerThickness;
        Double          containerDensity;
        List<String>    containerElementNames;
        List<Double>    containerElementNums;
        String          pdb;
        String          seqFile;
        Double          proteinConc;
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
    protected Stack<InputfileParser.crystal_scope> crystal_stack = new Stack<>();



    // $ANTLR start "crystal"
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:49:1: crystal returns [Crystal cObj] : CRYSTAL ( crystalLine )+ ;
    public final Crystal crystal() throws RecognitionException {
        crystal_stack.push(new crystal_scope());
        Crystal cObj = null;


         
        (crystal_stack.peek()).crystalCoefCalc = 2; // 0 = error, 1 = Simple, 2 = DEFAULT, 3 = RDV2, 4 = PDB, 5 = SAXS
                (crystal_stack.peek()).crystalProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:186:2: ( CRYSTAL ( crystalLine )+ )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:186:4: CRYSTAL ( crystalLine )+
            {
            match(input,CRYSTAL,FOLLOW_CRYSTAL_in_crystal134); 

            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:186:12: ( crystalLine )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= ABSCOEFCALC && LA2_0 <= ANGLEP)||LA2_0==CALCULATEESCAPE||(LA2_0 >= CONTAINERDENSITY && LA2_0 <= CONTAINERTHICKNESS)||(LA2_0 >= DDM && LA2_0 <= DECAYPARAM)||(LA2_0 >= DIFFRACTIONDECAYMODEL && LA2_0 <= DIMENSION)||(LA2_0 >= MATERIALELEMENTS && LA2_0 <= MATERIALTYPE)||LA2_0==MODELFILE||(LA2_0 >= NUMDNA && LA2_0 <= NUMRNA)||LA2_0==PDBNAME||(LA2_0 >= PIXELSPERMICRON && LA2_0 <= PROTEINHEAVYATOMS)||LA2_0==SEQFILE||LA2_0==SEQUENCEFILE||(LA2_0 >= SOLVENTFRACTION && LA2_0 <= SOLVENTHEAVYCONC)||(LA2_0 >= TYPE && LA2_0 <= UNITCELL)||LA2_0==WIREFRAMETYPE) ) {
                    alt2=1;
                }


                switch (alt2) {
                case 1 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:186:12: crystalLine
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

             
            if ((crystal_stack.peek()).crystalCoefCalc == 1) {
              (crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcAverage();
            }
            if ((crystal_stack.peek()).crystalCoefCalc == 2)
            {
              (crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromParams((crystal_stack.peek()).cellA, (crystal_stack.peek()).cellB, (crystal_stack.peek()).cellC, (crystal_stack.peek()).cellAl, (crystal_stack.peek()).cellBe, (crystal_stack.peek()).cellGa,
                                                                (crystal_stack.peek()).numMon, (crystal_stack.peek()).numRes, (crystal_stack.peek()).numRNA, (crystal_stack.peek()).numDNA,
                                                                (crystal_stack.peek()).heavyProteinAtomNames, (crystal_stack.peek()).heavyProteinAtomNums,
                                                                (crystal_stack.peek()).heavySolutionConcNames, (crystal_stack.peek()).heavySolutionConcNums,
                                                                (crystal_stack.peek()).solFrac);
            }

            if ((crystal_stack.peek()).crystalCoefCalc == 3) {
              (crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcRaddose((crystal_stack.peek()).cellA, (crystal_stack.peek()).cellB, (crystal_stack.peek()).cellC, (crystal_stack.peek()).cellAl, (crystal_stack.peek()).cellBe, (crystal_stack.peek()).cellGa,
                                                                (crystal_stack.peek()).numMon, (crystal_stack.peek()).numRes, (crystal_stack.peek()).numRNA, (crystal_stack.peek()).numDNA,
                                                                (crystal_stack.peek()).heavyProteinAtomNames, (crystal_stack.peek()).heavyProteinAtomNums,
                                                                (crystal_stack.peek()).heavySolutionConcNames, (crystal_stack.peek()).heavySolutionConcNums,
                                                                (crystal_stack.peek()).solFrac);
            }

            if ((crystal_stack.peek()).crystalCoefCalc == 4)
            {
              if ((crystal_stack.peek()).heavySolutionConcNames != null)
                (crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromPDB((crystal_stack.peek()).pdb, (crystal_stack.peek()).heavySolutionConcNames, (crystal_stack.peek()).heavySolutionConcNums);
              else
                (crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromPDB((crystal_stack.peek()).pdb);
                                                                                                                    
            }

            if ((crystal_stack.peek()).crystalCoefCalc == 5)
            {
              (crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcSAXS((crystal_stack.peek()).cellA, (crystal_stack.peek()).cellB, (crystal_stack.peek()).cellC, (crystal_stack.peek()).cellAl, (crystal_stack.peek()).cellBe, (crystal_stack.peek()).cellGa,
                                                                (crystal_stack.peek()).numRes, (crystal_stack.peek()).numRNA, (crystal_stack.peek()).numDNA,
                                                                (crystal_stack.peek()).heavyProteinAtomNames, (crystal_stack.peek()).heavyProteinAtomNums,
                                                                (crystal_stack.peek()).heavySolutionConcNames, (crystal_stack.peek()).heavySolutionConcNums,
                                                                (crystal_stack.peek()).solFrac, (crystal_stack.peek()).proteinConc);
            }

            if ((crystal_stack.peek()).crystalCoefCalc == 6)
            {
              (crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromSequence((crystal_stack.peek()).cellA, (crystal_stack.peek()).cellB, (crystal_stack.peek()).cellC, (crystal_stack.peek()).cellAl, (crystal_stack.peek()).cellBe, (crystal_stack.peek()).cellGa,
                                                                (crystal_stack.peek()).numMon,
                                                                (crystal_stack.peek()).heavyProteinAtomNames, (crystal_stack.peek()).heavyProteinAtomNums,
                                                                (crystal_stack.peek()).heavySolutionConcNames, (crystal_stack.peek()).heavySolutionConcNums,
                                                                (crystal_stack.peek()).solFrac, (crystal_stack.peek()).seqFile);
            }

            if ((crystal_stack.peek()).crystalCoefCalc == 7)
            {
              (crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromSequenceSAXS((crystal_stack.peek()).cellA, (crystal_stack.peek()).cellB, (crystal_stack.peek()).cellC, (crystal_stack.peek()).cellAl, (crystal_stack.peek()).cellBe, (crystal_stack.peek()).cellGa,
                                                                (crystal_stack.peek()).heavyProteinAtomNames, (crystal_stack.peek()).heavyProteinAtomNums,
                                                                (crystal_stack.peek()).heavySolutionConcNames, (crystal_stack.peek()).heavySolutionConcNums,
                                                                (crystal_stack.peek()).solFrac, (crystal_stack.peek()).proteinConc, (crystal_stack.peek()).seqFile);
            }

            (crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_COEFCALC, (crystal_stack.peek()).crystalCoefCalcClass);

            if ((crystal_stack.peek()).crystalDdm == 1)
            {
                (crystal_stack.peek()).crystalDdmClass = new DDMSimple();
            }

            if ((crystal_stack.peek()).crystalDdm == 2)
            {
                (crystal_stack.peek()).crystalDdmClass = new DDMLinear();
            }

            if ((crystal_stack.peek()).crystalDdm == 3)
            {
                (crystal_stack.peek()).crystalDdmClass = new DDMLeal((crystal_stack.peek()).gammaParam, (crystal_stack.peek()).b0Param, (crystal_stack.peek()).betaParam);
            }

            (crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_DDM, (crystal_stack.peek()).crystalDdmClass);

            if ((crystal_stack.peek()).crystalContainerMaterial == 1)
            {
                (crystal_stack.peek()).crystalContainerMaterialClass = new ContainerTransparent();
            }

            if ((crystal_stack.peek()).crystalContainerMaterial == 2)
            {
                (crystal_stack.peek()).crystalContainerMaterialClass = new ContainerMixture((crystal_stack.peek()).containerThickness, (crystal_stack.peek()).containerDensity, (crystal_stack.peek()).containerMixture);
            }

            if ((crystal_stack.peek()).crystalContainerMaterial == 3)
            {
                (crystal_stack.peek()).crystalContainerMaterialClass = new ContainerElemental((crystal_stack.peek()).containerThickness, (crystal_stack.peek()).containerDensity, (crystal_stack.peek()).containerElementNames,
                                                                (crystal_stack.peek()).containerElementNums);
            }

            (crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_CONTAINER, (crystal_stack.peek()).crystalContainerMaterialClass);


            cObj = crystalFactory.createCrystal((crystal_stack.peek()).crystalType, (crystal_stack.peek()).crystalProperties);

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:189:1: crystalLine : (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculateEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile );
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


        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:190:2: (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculateEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile )
            int alt3=27;
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
            case SEQFILE:
            case SEQUENCEFILE:
                {
                alt3=27;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:190:4: a= crystalType
                    {
                    pushFollow(FOLLOW_crystalType_in_crystalLine192);
                    a=crystalType();

                    state._fsp--;


                     (crystal_stack.peek()).crystalType = a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:191:4: b= crystalDDM
                    {
                    pushFollow(FOLLOW_crystalDDM_in_crystalLine203);
                    b=crystalDDM();

                    state._fsp--;


                     (crystal_stack.peek()).crystalDdm = b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:192:4: c= crystalCoefcalc
                    {
                    pushFollow(FOLLOW_crystalCoefcalc_in_crystalLine215);
                    c=crystalCoefcalc();

                    state._fsp--;


                     (crystal_stack.peek()).crystalCoefCalc = c; 

                    }
                    break;
                case 4 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:193:4: d= crystalDim
                    {
                    pushFollow(FOLLOW_crystalDim_in_crystalLine225);
                    d=crystalDim();

                    state._fsp--;


                     if (d != null) {
                                                   (crystal_stack.peek()).crystalProperties.putAll(d);
                                                  }; 

                    }
                    break;
                case 5 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:196:4: e= crystalPPM
                    {
                    pushFollow(FOLLOW_crystalPPM_in_crystalLine236);
                    e=crystalPPM();

                    state._fsp--;


                     (crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, e); 

                    }
                    break;
                case 6 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:197:4: f= crystalAngP
                    {
                    pushFollow(FOLLOW_crystalAngP_in_crystalLine247);
                    f=crystalAngP();

                    state._fsp--;


                     (crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:198:4: g= crystalAngL
                    {
                    pushFollow(FOLLOW_crystalAngL_in_crystalLine258);
                    g=crystalAngL();

                    state._fsp--;


                     (crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, g); 

                    }
                    break;
                case 8 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:199:4: h= crystalDecayParam
                    {
                    pushFollow(FOLLOW_crystalDecayParam_in_crystalLine269);
                    h=crystalDecayParam();

                    state._fsp--;


                     (crystal_stack.peek()).gammaParam = (h!=null?h.gammaParam:null); 
                                                              (crystal_stack.peek()).b0Param = (h!=null?h.b0Param:null); 
                                                              (crystal_stack.peek()).betaParam = (h!=null?h.betaParam:null); 

                    }
                    break;
                case 9 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:202:4: i= containerThickness
                    {
                    pushFollow(FOLLOW_containerThickness_in_crystalLine279);
                    i=containerThickness();

                    state._fsp--;


                     (crystal_stack.peek()).containerThickness = i; 

                    }
                    break;
                case 10 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:203:4: j= containerDensity
                    {
                    pushFollow(FOLLOW_containerDensity_in_crystalLine289);
                    j=containerDensity();

                    state._fsp--;


                     (crystal_stack.peek()).containerDensity = j; 

                    }
                    break;
                case 11 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:204:4: k= crystalContainerMaterial
                    {
                    pushFollow(FOLLOW_crystalContainerMaterial_in_crystalLine299);
                    k=crystalContainerMaterial();

                    state._fsp--;


                     (crystal_stack.peek()).crystalContainerMaterial = k; 

                    }
                    break;
                case 12 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:205:4: l= containerMaterialMixture
                    {
                    pushFollow(FOLLOW_containerMaterialMixture_in_crystalLine308);
                    l=containerMaterialMixture();

                    state._fsp--;


                     (crystal_stack.peek()).containerMixture = l; 

                    }
                    break;
                case 13 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:206:4: m= unitcell
                    {
                    pushFollow(FOLLOW_unitcell_in_crystalLine317);
                    m=unitcell();

                    state._fsp--;


                     (crystal_stack.peek()).cellA = (m!=null?m.dimA:null); 
                                                  (crystal_stack.peek()).cellB = (m!=null?m.dimB:null);  
                                                  (crystal_stack.peek()).cellC = (m!=null?m.dimC:null);  
                                                  (crystal_stack.peek()).cellAl = (m!=null?m.angA:null);
                                                  (crystal_stack.peek()).cellBe = (m!=null?m.angB:null);     
                                                  (crystal_stack.peek()).cellGa = (m!=null?m.angC:null); 

                    }
                    break;
                case 14 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:212:4: n= nummonomers
                    {
                    pushFollow(FOLLOW_nummonomers_in_crystalLine328);
                    n=nummonomers();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numMon = n;  

                    }
                    break;
                case 15 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:213:4: o= numresidues
                    {
                    pushFollow(FOLLOW_numresidues_in_crystalLine339);
                    o=numresidues();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRes = o;  

                    }
                    break;
                case 16 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:214:4: p= numRNA
                    {
                    pushFollow(FOLLOW_numRNA_in_crystalLine350);
                    p=numRNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRNA = p;  

                    }
                    break;
                case 17 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:215:4: q= numDNA
                    {
                    pushFollow(FOLLOW_numDNA_in_crystalLine363);
                    q=numDNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numDNA = q;  

                    }
                    break;
                case 18 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:216:4: r= heavyProteinAtoms
                    {
                    pushFollow(FOLLOW_heavyProteinAtoms_in_crystalLine376);
                    r=heavyProteinAtoms();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames = (r!=null?r.names:null);
                                                  ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums = (r!=null?r.num:null);    

                    }
                    break;
                case 19 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:218:4: s= heavySolutionConc
                    {
                    pushFollow(FOLLOW_heavySolutionConc_in_crystalLine385);
                    s=heavySolutionConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames = (s!=null?s.names:null);
                                                  ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums = (s!=null?s.num:null);   

                    }
                    break;
                case 20 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:220:4: t= solventFraction
                    {
                    pushFollow(FOLLOW_solventFraction_in_crystalLine394);
                    t=solventFraction();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).solFrac = t; 

                    }
                    break;
                case 21 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:221:4: u= pdb
                    {
                    pushFollow(FOLLOW_pdb_in_crystalLine404);
                    u=pdb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).pdb = u; 

                    }
                    break;
                case 22 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:222:4: v= wireframeType
                    {
                    pushFollow(FOLLOW_wireframeType_in_crystalLine417);
                    v=wireframeType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_TYPE, v); 

                    }
                    break;
                case 23 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:223:4: w= modelFile
                    {
                    pushFollow(FOLLOW_modelFile_in_crystalLine428);
                    w=modelFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_FILE, w); 

                    }
                    break;
                case 24 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:224:4: x= calculateEscape
                    {
                    pushFollow(FOLLOW_calculateEscape_in_crystalLine440);
                    x=calculateEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ELECTRON_ESCAPE, x); 

                    }
                    break;
                case 25 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:225:4: y= proteinConcentration
                    {
                    pushFollow(FOLLOW_proteinConcentration_in_crystalLine450);
                    y=proteinConcentration();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).proteinConc = y;

                    }
                    break;
                case 26 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:226:4: z= containerMaterialElements
                    {
                    pushFollow(FOLLOW_containerMaterialElements_in_crystalLine459);
                    z=containerMaterialElements();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerElementNames = (z!=null?z.names:null);
                                                  ((crystal_scope)crystal_stack.peek()).containerElementNums = (z!=null?z.num:null);    

                    }
                    break;
                case 27 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:228:4: aa= sequenceFile
                    {
                    pushFollow(FOLLOW_sequenceFile_in_crystalLine468);
                    aa=sequenceFile();

                    state._fsp--;


                     (crystal_stack.peek()).seqFile = aa; 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:233:1: crystalType returns [String crystalType] : TYPE e= STRING ;
    public final String crystalType() throws RecognitionException {
        String crystalType = null;


        Token e=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:234:2: ( TYPE e= STRING )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:234:4: TYPE e= STRING
            {
            match(input,TYPE,FOLLOW_TYPE_in_crystalType493); 

            e=(Token)match(input,STRING,FOLLOW_STRING_in_crystalType497); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:237:1: crystalDDM returns [int value] : ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword ;
    public final int crystalDDM() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:238:2: ( ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:238:4: ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword
            {
            if ( input.LA(1)==DDM||input.LA(1)==DIFFRACTIONDECAYMODEL ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalDDMKeyword_in_crystalDDM551);
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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:241:1: crystalDDMKeyword returns [int value] : ( SIMPLE | LINEAR | LEAL );
    public final int crystalDDMKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:242:2: ( SIMPLE | LINEAR | LEAL )
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
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:242:4: SIMPLE
                    {
                    match(input,SIMPLE,FOLLOW_SIMPLE_in_crystalDDMKeyword699); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:243:4: LINEAR
                    {
                    match(input,LINEAR,FOLLOW_LINEAR_in_crystalDDMKeyword706); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:244:4: LEAL
                    {
                    match(input,LEAL,FOLLOW_LEAL_in_crystalDDMKeyword713); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:250:1: crystalDecayParam returns [Double gammaParam, Double b0Param, Double betaParam] : DECAYPARAM a= FLOAT b= FLOAT c= FLOAT ;
    public final InputfileParser.crystalDecayParam_return crystalDecayParam() throws RecognitionException {
        InputfileParser.crystalDecayParam_return retval = new InputfileParser.crystalDecayParam_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:251:2: ( DECAYPARAM a= FLOAT b= FLOAT c= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:251:4: DECAYPARAM a= FLOAT b= FLOAT c= FLOAT
            {
            match(input,DECAYPARAM,FOLLOW_DECAYPARAM_in_crystalDecayParam833); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam837); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam841); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam845); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:254:1: crystalCoefcalc returns [int value] : ABSCOEFCALC a= crystalCoefcalcKeyword ;
    public final int crystalCoefcalc() throws RecognitionException {
        int value = 0;


        int a =0;


        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:255:2: ( ABSCOEFCALC a= crystalCoefcalcKeyword )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:255:4: ABSCOEFCALC a= crystalCoefcalcKeyword
            {
            match(input,ABSCOEFCALC,FOLLOW_ABSCOEFCALC_in_crystalCoefcalc917); 

            pushFollow(FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc921);
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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:257:1: crystalCoefcalcKeyword returns [int value] : ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS | SEQUENCE | SAXSSEQ );
    public final int crystalCoefcalcKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:258:2: ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS | SEQUENCE | SAXSSEQ )
            int alt5=9;
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
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:258:4: DUMMY
                    {
                    match(input,DUMMY,FOLLOW_DUMMY_in_crystalCoefcalcKeyword1000); 

                     value = 1;

                    }
                    break;
                case 2 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:259:4: AVERAGE
                    {
                    match(input,AVERAGE,FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1010); 

                     value = 1;

                    }
                    break;
                case 3 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:260:4: DEFAULT
                    {
                    match(input,DEFAULT,FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1018); 

                     value = 2;

                    }
                    break;
                case 4 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:261:4: RDJAVA
                    {
                    match(input,RDJAVA,FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1026); 

                     value = 2;

                    }
                    break;
                case 5 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:262:4: RDFORTAN
                    {
                    match(input,RDFORTAN,FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1033); 

                     value = 3;

                    }
                    break;
                case 6 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:263:4: PDB
                    {
                    match(input,PDB,FOLLOW_PDB_in_crystalCoefcalcKeyword1040); 

                     value = 4;

                    }
                    break;
                case 7 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:264:4: SAXS
                    {
                    match(input,SAXS,FOLLOW_SAXS_in_crystalCoefcalcKeyword1050); 

                     value = 5;

                    }
                    break;
                case 8 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:265:4: SEQUENCE
                    {
                    match(input,SEQUENCE,FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1058); 

                     value = 6;

                    }
                    break;
                case 9 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:266:4: SAXSSEQ
                    {
                    match(input,SAXSSEQ,FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1065); 

                     value = 7;

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:278:1: crystalDim returns [Map<Object, Object> properties] : DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) ;
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
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:281:3: ( DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:281:5: DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
            {
            match(input,DIMENSION,FOLLOW_DIMENSION_in_crystalDim1389); 

            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:282:2: (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==FLOAT) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==FLOAT) ) {
                    int LA6_2 = input.LA(3);

                    if ( (LA6_2==FLOAT) ) {
                        alt6=1;
                    }
                    else if ( (LA6_2==EOF||(LA6_2 >= ABSCOEFCALC && LA6_2 <= ANGLEP)||(LA6_2 >= BEAM && LA6_2 <= CALCULATEESCAPE)||(LA6_2 >= CONTAINERDENSITY && LA6_2 <= DECAYPARAM)||(LA6_2 >= DIFFRACTIONDECAYMODEL && LA6_2 <= DIMENSION)||(LA6_2 >= MATERIALELEMENTS && LA6_2 <= MATERIALTYPE)||LA6_2==MODELFILE||(LA6_2 >= NUMDNA && LA6_2 <= NUMRNA)||LA6_2==PDBNAME||(LA6_2 >= PIXELSPERMICRON && LA6_2 <= PROTEINHEAVYATOMS)||LA6_2==SEQFILE||LA6_2==SEQUENCEFILE||(LA6_2 >= SOLVENTFRACTION && LA6_2 <= SOLVENTHEAVYCONC)||(LA6_2 >= TYPE && LA6_2 <= UNITCELL)||(LA6_2 >= WEDGE && LA6_2 <= WIREFRAMETYPE)) ) {
                        alt6=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA6_1==EOF||(LA6_1 >= ABSCOEFCALC && LA6_1 <= ANGLEP)||(LA6_1 >= BEAM && LA6_1 <= CALCULATEESCAPE)||(LA6_1 >= CONTAINERDENSITY && LA6_1 <= DECAYPARAM)||(LA6_1 >= DIFFRACTIONDECAYMODEL && LA6_1 <= DIMENSION)||(LA6_1 >= MATERIALELEMENTS && LA6_1 <= MATERIALTYPE)||LA6_1==MODELFILE||(LA6_1 >= NUMDNA && LA6_1 <= NUMRNA)||LA6_1==PDBNAME||(LA6_1 >= PIXELSPERMICRON && LA6_1 <= PROTEINHEAVYATOMS)||LA6_1==SEQFILE||LA6_1==SEQUENCEFILE||(LA6_1 >= SOLVENTFRACTION && LA6_1 <= SOLVENTHEAVYCONC)||(LA6_1 >= TYPE && LA6_1 <= UNITCELL)||(LA6_1 >= WEDGE && LA6_1 <= WIREFRAMETYPE)) ) {
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
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:283:7: a= FLOAT b= FLOAT c= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1402); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1406); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1410); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((a!=null?a.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((b!=null?b.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble((c!=null?c.getText():null))); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:286:7: e= FLOAT f= FLOAT
                    {
                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1422); 

                    f=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1426); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((e!=null?e.getText():null)));
                                       properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((f!=null?f.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:288:7: d= FLOAT
                    {
                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1438); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:292:1: crystalAngP returns [double value] : ANGLEP a= FLOAT ;
    public final double crystalAngP() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:293:2: ( ANGLEP a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:293:4: ANGLEP a= FLOAT
            {
            match(input,ANGLEP,FOLLOW_ANGLEP_in_crystalAngP1515); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngP1519); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:297:1: crystalAngL returns [double value] : ANGLEL a= FLOAT ;
    public final double crystalAngL() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:298:2: ( ANGLEL a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:298:4: ANGLEL a= FLOAT
            {
            match(input,ANGLEL,FOLLOW_ANGLEL_in_crystalAngL1574); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngL1578); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:302:1: crystalPPM returns [double ppm] : PIXELSPERMICRON FLOAT ;
    public final double crystalPPM() throws RecognitionException {
        double ppm = 0.0;


        Token FLOAT1=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:303:2: ( PIXELSPERMICRON FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:303:4: PIXELSPERMICRON FLOAT
            {
            match(input,PIXELSPERMICRON,FOLLOW_PIXELSPERMICRON_in_crystalPPM1632); 

            FLOAT1=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalPPM1634); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:306:1: unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC] : UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? ;
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
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:307:2: ( UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:307:4: UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )?
            {
            match(input,UNITCELL,FOLLOW_UNITCELL_in_unitcell1732); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1736); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1740); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1744); 

            retval.dimA = Double.parseDouble((a!=null?a.getText():null));
                     retval.dimB = Double.parseDouble((b!=null?b.getText():null));
                     retval.dimC = Double.parseDouble((c!=null?c.getText():null)); 

            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:311:7: (al= FLOAT be= FLOAT ga= FLOAT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FLOAT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:311:8: al= FLOAT be= FLOAT ga= FLOAT
                    {
                    al=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1759); 

                    be=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1763); 

                    ga=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1767); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:319:1: proteinConcentration returns [Double proteinConc] : ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT ;
    public final Double proteinConcentration() throws RecognitionException {
        Double proteinConc = null;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:320:2: ( ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:320:4: ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT
            {
            if ( (input.LA(1) >= PROTEINCONC && input.LA(1) <= PROTEINCONCENTRATION) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_proteinConcentration1855); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:324:1: nummonomers returns [int value] : NUMMONOMERS a= FLOAT ;
    public final int nummonomers() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:325:2: ( NUMMONOMERS a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:325:4: NUMMONOMERS a= FLOAT
            {
            match(input,NUMMONOMERS,FOLLOW_NUMMONOMERS_in_nummonomers2037); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_nummonomers2041); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:328:1: numresidues returns [int value] : NUMRESIDUES a= FLOAT ;
    public final int numresidues() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:329:2: ( NUMRESIDUES a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:329:4: NUMRESIDUES a= FLOAT
            {
            match(input,NUMRESIDUES,FOLLOW_NUMRESIDUES_in_numresidues2118); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numresidues2122); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:332:1: numRNA returns [int value] : NUMRNA a= FLOAT ;
    public final int numRNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:333:2: ( NUMRNA a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:333:4: NUMRNA a= FLOAT
            {
            match(input,NUMRNA,FOLLOW_NUMRNA_in_numRNA2200); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numRNA2204); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:336:1: numDNA returns [int value] : NUMDNA a= FLOAT ;
    public final int numDNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:337:2: ( NUMDNA a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:337:4: NUMDNA a= FLOAT
            {
            match(input,NUMDNA,FOLLOW_NUMDNA_in_numDNA2257); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numDNA2261); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:340:1: heavyProteinAtoms returns [List<String> names, List<Double> num;] : PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavyProteinAtoms_return heavyProteinAtoms() throws RecognitionException {
        InputfileParser.heavyProteinAtoms_return retval = new InputfileParser.heavyProteinAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:345:2: ( PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:345:4: PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,PROTEINHEAVYATOMS,FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2317); 

            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:345:22: (a= ELEMENT b= FLOAT )+
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
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:345:23: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavyProteinAtoms2322); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavyProteinAtoms2326); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:349:1: heavySolutionConc returns [List<String> names, List<Double> num;] : SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavySolutionConc_return heavySolutionConc() throws RecognitionException {
        InputfileParser.heavySolutionConc_return retval = new InputfileParser.heavySolutionConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:354:2: ( SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:354:4: SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SOLVENTHEAVYCONC,FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc2473); 

            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:354:21: (a= ELEMENT b= FLOAT )+
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
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:354:22: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavySolutionConc2478); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavySolutionConc2482); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:357:1: solventFraction returns [double solFrac] : SOLVENTFRACTION a= FLOAT ;
    public final double solventFraction() throws RecognitionException {
        double solFrac = 0.0;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:358:2: ( SOLVENTFRACTION a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:358:4: SOLVENTFRACTION a= FLOAT
            {
            match(input,SOLVENTFRACTION,FOLLOW_SOLVENTFRACTION_in_solventFraction2588); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_solventFraction2592); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:361:1: pdb returns [String pdb] : PDBNAME a= STRING ;
    public final String pdb() throws RecognitionException {
        String pdb = null;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:362:2: ( PDBNAME a= STRING )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:362:4: PDBNAME a= STRING
            {
            match(input,PDBNAME,FOLLOW_PDBNAME_in_pdb2689); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_pdb2693); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:365:1: wireframeType returns [String value] : WIREFRAMETYPE a= STRING ;
    public final String wireframeType() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:366:2: ( WIREFRAMETYPE a= STRING )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:366:4: WIREFRAMETYPE a= STRING
            {
            match(input,WIREFRAMETYPE,FOLLOW_WIREFRAMETYPE_in_wireframeType2730); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_wireframeType2734); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:369:1: modelFile returns [String value] : MODELFILE a= STRING ;
    public final String modelFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:370:2: ( MODELFILE a= STRING )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:370:4: MODELFILE a= STRING
            {
            match(input,MODELFILE,FOLLOW_MODELFILE_in_modelFile2822); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_modelFile2826); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:373:1: calculateEscape returns [String value] : CALCULATEESCAPE a= STRING ;
    public final String calculateEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:374:2: ( CALCULATEESCAPE a= STRING )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:374:4: CALCULATEESCAPE a= STRING
            {
            match(input,CALCULATEESCAPE,FOLLOW_CALCULATEESCAPE_in_calculateEscape2893); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculateEscape2897); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:378:1: crystalContainerMaterial returns [int value] : ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword ;
    public final int crystalContainerMaterial() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:379:2: ( ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:379:4: ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword
            {
            if ( input.LA(1)==CONTAINERMATERIALTYPE||input.LA(1)==MATERIALTYPE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial3011);
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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:382:1: crystalContainerKeyword returns [int value] : ( NONE | MIXTURE | ELEMENTAL );
    public final int crystalContainerKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:383:2: ( NONE | MIXTURE | ELEMENTAL )
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
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:383:4: NONE
                    {
                    match(input,NONE,FOLLOW_NONE_in_crystalContainerKeyword3204); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:384:4: MIXTURE
                    {
                    match(input,MIXTURE,FOLLOW_MIXTURE_in_crystalContainerKeyword3213); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:385:4: ELEMENTAL
                    {
                    match(input,ELEMENTAL,FOLLOW_ELEMENTAL_in_crystalContainerKeyword3221); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:391:1: containerThickness returns [double value] : CONTAINERTHICKNESS a= FLOAT ;
    public final double containerThickness() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:392:2: ( CONTAINERTHICKNESS a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:392:4: CONTAINERTHICKNESS a= FLOAT
            {
            match(input,CONTAINERTHICKNESS,FOLLOW_CONTAINERTHICKNESS_in_containerThickness3361); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerThickness3365); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:395:1: containerMaterialMixture returns [String value] : ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING ;
    public final String containerMaterialMixture() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:396:2: ( ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:396:4: ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING
            {
            if ( input.LA(1)==CONTAINERMATERIALMIXTURE||input.LA(1)==MATERIALMIXTURE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_containerMaterialMixture3486); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:400:1: containerMaterialElements returns [List<String> names, List<Double> num;] : ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.containerMaterialElements_return containerMaterialElements() throws RecognitionException {
        InputfileParser.containerMaterialElements_return retval = new InputfileParser.containerMaterialElements_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:405:2: ( ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:405:4: ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+
            {
            if ( input.LA(1)==CONTAINERMATERIALELEMENTS||input.LA(1)==MATERIALELEMENTS ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:405:51: (a= ELEMENT b= FLOAT )+
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
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:405:52: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_containerMaterialElements3722); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerMaterialElements3726); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:409:1: containerDensity returns [double value] : CONTAINERDENSITY a= FLOAT ;
    public final double containerDensity() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:410:2: ( CONTAINERDENSITY a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:410:4: CONTAINERDENSITY a= FLOAT
            {
            match(input,CONTAINERDENSITY,FOLLOW_CONTAINERDENSITY_in_containerDensity3961); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerDensity3965); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:413:1: sequenceFile returns [String value] : ( SEQUENCEFILE | SEQFILE ) a= STRING ;
    public final String sequenceFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:414:2: ( ( SEQUENCEFILE | SEQFILE ) a= STRING )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:414:4: ( SEQUENCEFILE | SEQFILE ) a= STRING
            {
            if ( input.LA(1)==SEQFILE||input.LA(1)==SEQUENCEFILE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_sequenceFile4076); 

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


    protected static class beam_scope {
        String beamType;
        HashMap<Object, Object> beamProperties;
    }
    protected Stack<InputfileParser.beam_scope> beam_stack = new Stack<>();



    // $ANTLR start "beam"
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:420:1: beam returns [Beam bObj] : BEAM ( beamLine )+ ;
    public final Beam beam() throws RecognitionException {
        beam_stack.push(new beam_scope());
        Beam bObj = null;


         
                ((beam_scope)beam_stack.peek()).beamProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:431:2: ( BEAM ( beamLine )+ )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:431:4: BEAM ( beamLine )+
            {
            match(input,BEAM,FOLLOW_BEAM_in_beam4215); 

            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:431:9: ( beamLine )+
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
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:431:9: beamLine
                    {
                    pushFollow(FOLLOW_beamLine_in_beam4217);
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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:434:1: beamLine : ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize );
    public final void beamLine() throws RecognitionException {
        Token a=null;
        Double b =null;

        InputfileParser.beamFWHM_return c =null;

        Double d =null;

        Map<Object, Object> e =null;

        String f =null;

        Map<Object, Object> g =null;


        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:435:2: ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize )
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
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:435:4: TYPE a= STRING
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_beamLine4256); 

                    a=(Token)match(input,STRING,FOLLOW_STRING_in_beamLine4260); 

                     ((beam_scope)beam_stack.peek()).beamType = (a!=null?a.getText():null); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:436:4: b= beamFlux
                    {
                    pushFollow(FOLLOW_beamFlux_in_beamLine4278);
                    b=beamFlux();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FLUX, b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:437:4: c= beamFWHM
                    {
                    pushFollow(FOLLOW_beamFWHM_in_beamLine4290);
                    c=beamFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_X, (c!=null?c.x:null)); 
                                                   ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_Y, (c!=null?c.y:null)); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:439:4: d= beamEnergy
                    {
                    pushFollow(FOLLOW_beamEnergy_in_beamLine4302);
                    d=beamEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_ENERGY, d); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:440:4: e= beamCollimation
                    {
                    pushFollow(FOLLOW_beamCollimation_in_beamLine4314);
                    e=beamCollimation();

                    state._fsp--;


                     if (e != null) {
                                                    ((beam_scope)beam_stack.peek()).beamProperties.putAll(e);
                                                   } 

                    }
                    break;
                case 6 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:443:4: f= beamFile
                    {
                    pushFollow(FOLLOW_beamFile_in_beamLine4325);
                    f=beamFile();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXTFILE, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:444:4: g= beamPixelSize
                    {
                    pushFollow(FOLLOW_beamPixelSize_in_beamLine4346);
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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:447:1: beamFlux returns [Double flux] : FLUX a= FLOAT ;
    public final Double beamFlux() throws RecognitionException {
        Double flux = null;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:448:2: ( FLUX a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:448:4: FLUX a= FLOAT
            {
            match(input,FLUX,FOLLOW_FLUX_in_beamFlux4370); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFlux4374); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:451:1: beamFWHM returns [Double x, Double y] : FWHM a= FLOAT b= FLOAT ;
    public final InputfileParser.beamFWHM_return beamFWHM() throws RecognitionException {
        InputfileParser.beamFWHM_return retval = new InputfileParser.beamFWHM_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:452:2: ( FWHM a= FLOAT b= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:452:4: FWHM a= FLOAT b= FLOAT
            {
            match(input,FWHM,FOLLOW_FWHM_in_beamFWHM4416); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM4420); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM4424); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:455:1: beamEnergy returns [Double energy] : ENERGY a= FLOAT ( KEV )? ;
    public final Double beamEnergy() throws RecognitionException {
        Double energy = null;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:456:2: ( ENERGY a= FLOAT ( KEV )? )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:456:4: ENERGY a= FLOAT ( KEV )?
            {
            match(input,ENERGY,FOLLOW_ENERGY_in_beamEnergy4466); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamEnergy4470); 

            energy = Double.parseDouble((a!=null?a.getText():null));

            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:457:2: ( KEV )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==KEV) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:457:4: KEV
                    {
                    match(input,KEV,FOLLOW_KEV_in_beamEnergy4477); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:462:1: beamFile returns [String filename] : FILE a= STRING ;
    public final String beamFile() throws RecognitionException {
        String filename = null;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:463:2: ( FILE a= STRING )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:463:4: FILE a= STRING
            {
            match(input,FILE,FOLLOW_FILE_in_beamFile4555); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_beamFile4559); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:467:1: beamPixelSize returns [Map<Object, Object> properties] : PIXELSIZE a= FLOAT b= FLOAT ;
    public final Map<Object, Object> beamPixelSize() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:468:5: ( PIXELSIZE a= FLOAT b= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:468:7: PIXELSIZE a= FLOAT b= FLOAT
            {
            match(input,PIXELSIZE,FOLLOW_PIXELSIZE_in_beamPixelSize4606); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize4610); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize4614); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:475:1: beamCollimation returns [Map<Object, Object> properties] : ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT );
    public final Map<Object, Object> beamCollimation() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token d=null;
        Token e=null;

         
                properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:479:2: ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT )
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
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:479:4: COLLIMATION
                    {
                    match(input,COLLIMATION,FOLLOW_COLLIMATION_in_beamCollimation4693); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:480:4: RECTANGULAR a= FLOAT b= FLOAT
                    {
                    match(input,RECTANGULAR,FOLLOW_RECTANGULAR_in_beamCollimation4699); 

                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4703); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4707); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((a!=null?a.getText():null)));
                                                        properties.put(Beam.BEAM_COLL_V, Double.parseDouble((b!=null?b.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:482:4: CIRCULAR FLOAT
                    {
                    match(input,CIRCULAR,FOLLOW_CIRCULAR_in_beamCollimation4714); 

                    match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4716); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:483:4: HORIZONTAL d= FLOAT
                    {
                    match(input,HORIZONTAL,FOLLOW_HORIZONTAL_in_beamCollimation4722); 

                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4726); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((d!=null?d.getText():null))); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:484:4: VERTICAL e= FLOAT
                    {
                    match(input,VERTICAL,FOLLOW_VERTICAL_in_beamCollimation4733); 

                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4737); 

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
    protected Stack<InputfileParser.wedge_scope> wedge_stack = new Stack<>();



    // $ANTLR start "wedge"
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:494:1: wedge returns [Wedge wObj] : WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ ;
    public final Wedge wedge() throws RecognitionException {
        wedge_stack.push(new wedge_scope());
        Wedge wObj = null;


        Token a=null;
        Token b=null;

         

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:513:2: ( WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:513:4: WEDGE a= FLOAT b= FLOAT ( wedgeLine )+
            {
            match(input,WEDGE,FOLLOW_WEDGE_in_wedge5050); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge5054); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge5058); 

            ((wedge_scope)wedge_stack.peek()).startAng = Double.parseDouble((a!=null?a.getText():null));
                                         ((wedge_scope)wedge_stack.peek()).endAng = Double.parseDouble((b!=null?b.getText():null)); 

            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:515:4: ( wedgeLine )+
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
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:515:4: wedgeLine
                    {
                    pushFollow(FOLLOW_wedgeLine_in_wedge5065);
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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:518:1: wedgeLine : (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset );
    public final void wedgeLine() throws RecognitionException {
        double a =0.0;

        double b =0.0;

        InputfileParser.wedgeStartOffset_return c =null;

        InputfileParser.wedgeTranslate_return d =null;

        double e =0.0;


        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:519:2: (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset )
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
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:519:4: a= wedgeExposure
                    {
                    pushFollow(FOLLOW_wedgeExposure_in_wedgeLine5109);
                    a=wedgeExposure();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).expTime =a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:520:4: b= wedgeAngRes
                    {
                    pushFollow(FOLLOW_wedgeAngRes_in_wedgeLine5119);
                    b=wedgeAngRes();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).angRes =b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:521:4: c= wedgeStartOffset
                    {
                    pushFollow(FOLLOW_wedgeStartOffset_in_wedgeLine5130);
                    c=wedgeStartOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).offsetX =(c!=null?c.x:null);
                                                 ((wedge_scope)wedge_stack.peek()).offsetY =(c!=null?c.y:null);
                                                 ((wedge_scope)wedge_stack.peek()).offsetZ =(c!=null?c.z:null); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:524:4: d= wedgeTranslate
                    {
                    pushFollow(FOLLOW_wedgeTranslate_in_wedgeLine5140);
                    d=wedgeTranslate();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).translateX =(d!=null?d.x:null);
                                                 ((wedge_scope)wedge_stack.peek()).translateY =(d!=null?d.y:null);
                                                 ((wedge_scope)wedge_stack.peek()).translateZ =(d!=null?d.z:null); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:527:4: e= wedgeRotAxBeamOffset
                    {
                    pushFollow(FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine5150);
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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:530:1: wedgeExposure returns [double value] : EXPOSURETIME a= FLOAT ;
    public final double wedgeExposure() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:531:2: ( EXPOSURETIME a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:531:4: EXPOSURETIME a= FLOAT
            {
            match(input,EXPOSURETIME,FOLLOW_EXPOSURETIME_in_wedgeExposure5167); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeExposure5171); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:534:1: wedgeAngRes returns [double res] : ANGULARRESOLUTION a= FLOAT ;
    public final double wedgeAngRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:535:2: ( ANGULARRESOLUTION a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:535:4: ANGULARRESOLUTION a= FLOAT
            {
            match(input,ANGULARRESOLUTION,FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes5253); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeAngRes5257); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:538:1: wedgeStartOffset returns [Double x, Double y, Double z] : STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeStartOffset_return wedgeStartOffset() throws RecognitionException {
        InputfileParser.wedgeStartOffset_return retval = new InputfileParser.wedgeStartOffset_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:539:2: ( STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:539:4: STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,STARTOFFSET,FOLLOW_STARTOFFSET_in_wedgeStartOffset5364); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset5368); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset5372); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:540:17: (c= FLOAT )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==FLOAT) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:540:17: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset5393); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:544:1: wedgeTranslate returns [Double x, Double y, Double z] : TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeTranslate_return wedgeTranslate() throws RecognitionException {
        InputfileParser.wedgeTranslate_return retval = new InputfileParser.wedgeTranslate_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:545:2: ( TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:545:4: TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,TRANSLATEPERDEGREE,FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate5487); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate5491); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate5495); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:546:24: (c= FLOAT )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==FLOAT) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:546:24: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate5523); 

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
    // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:550:1: wedgeRotAxBeamOffset returns [double delta] : ROTAXBEAMOFFSET a= FLOAT ;
    public final double wedgeRotAxBeamOffset() throws RecognitionException {
        double delta = 0.0;


        Token a=null;

        try {
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:551:2: ( ROTAXBEAMOFFSET a= FLOAT )
            // C:\\Users\\jonathan\\workspace\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:551:4: ROTAXBEAMOFFSET a= FLOAT
            {
            match(input,ROTAXBEAMOFFSET,FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset5659); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeRotAxBeamOffset5663); 

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


 

    public static final BitSet FOLLOW_crystal_in_configfile47 = new BitSet(new long[]{0x0000000000080200L,0x0000000000000400L});
    public static final BitSet FOLLOW_wedge_in_configfile65 = new BitSet(new long[]{0x0000000000080200L,0x0000000000000400L});
    public static final BitSet FOLLOW_beam_in_configfile85 = new BitSet(new long[]{0x0000000000080200L,0x0000000000000400L});
    public static final BitSet FOLLOW_EOF_in_configfile105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CRYSTAL_in_crystal134 = new BitSet(new long[]{0x40F5EB8001B7C470L,0x000000000000098DL});
    public static final BitSet FOLLOW_crystalLine_in_crystal136 = new BitSet(new long[]{0x40F5EB8001B7C472L,0x000000000000098DL});
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
    public static final BitSet FOLLOW_sequenceFile_in_crystalLine468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_crystalType493 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_crystalType497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalDDM539 = new BitSet(new long[]{0x0000006000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_crystalDDMKeyword_in_crystalDDM551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIMPLE_in_crystalDDMKeyword699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LINEAR_in_crystalDDMKeyword706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEAL_in_crystalDDMKeyword713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECAYPARAM_in_crystalDecayParam833 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam837 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam841 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSCOEFCALC_in_crystalCoefcalc917 = new BitSet(new long[]{0xB302000002400100L});
    public static final BitSet FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DUMMY_in_crystalCoefcalcKeyword1000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDB_in_crystalCoefcalcKeyword1040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXS_in_crystalCoefcalcKeyword1050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_crystalDim1389 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1402 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1406 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1422 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEP_in_crystalAngP1515 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngP1519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEL_in_crystalAngL1574 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngL1578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSPERMICRON_in_crystalPPM1632 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalPPM1634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNITCELL_in_unitcell1732 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1736 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1740 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1744 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1759 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1763 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_proteinConcentration1845 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_proteinConcentration1855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMMONOMERS_in_nummonomers2037 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_nummonomers2041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRESIDUES_in_numresidues2118 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_numresidues2122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRNA_in_numRNA2200 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_numRNA2204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMDNA_in_numDNA2257 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_numDNA2261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2317 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavyProteinAtoms2322 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavyProteinAtoms2326 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc2473 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavySolutionConc2478 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavySolutionConc2482 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_SOLVENTFRACTION_in_solventFraction2588 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_solventFraction2592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDBNAME_in_pdb2689 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_pdb2693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WIREFRAMETYPE_in_wireframeType2730 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_wireframeType2734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODELFILE_in_modelFile2822 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_modelFile2826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEESCAPE_in_calculateEscape2893 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_calculateEscape2897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalContainerMaterial2999 = new BitSet(new long[]{0x0000140008000000L});
    public static final BitSet FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial3011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NONE_in_crystalContainerKeyword3204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIXTURE_in_crystalContainerKeyword3213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELEMENTAL_in_crystalContainerKeyword3221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINERTHICKNESS_in_containerThickness3361 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerThickness3365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialMixture3476 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_containerMaterialMixture3486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialElements3711 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ELEMENT_in_containerMaterialElements3722 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerMaterialElements3726 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_CONTAINERDENSITY_in_containerDensity3961 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerDensity3965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sequenceFile4066 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_sequenceFile4076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEAM_in_beam4215 = new BitSet(new long[]{0x0408000E90001800L,0x0000000000000280L});
    public static final BitSet FOLLOW_beamLine_in_beam4217 = new BitSet(new long[]{0x0408000E90001802L,0x0000000000000280L});
    public static final BitSet FOLLOW_TYPE_in_beamLine4256 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_beamLine4260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFlux_in_beamLine4278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFWHM_in_beamLine4290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamEnergy_in_beamLine4302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamCollimation_in_beamLine4314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFile_in_beamLine4325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamPixelSize_in_beamLine4346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLUX_in_beamFlux4370 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFlux4374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FWHM_in_beamFWHM4416 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM4420 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM4424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGY_in_beamEnergy4466 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamEnergy4470 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_KEV_in_beamEnergy4477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_beamFile4555 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_STRING_in_beamFile4559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSIZE_in_beamPixelSize4606 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize4610 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize4614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLIMATION_in_beamCollimation4693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RECTANGULAR_in_beamCollimation4699 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4703 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCULAR_in_beamCollimation4714 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HORIZONTAL_in_beamCollimation4722 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERTICAL_in_beamCollimation4733 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WEDGE_in_wedge5050 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge5054 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge5058 = new BitSet(new long[]{0x0800000040000080L,0x0000000000000050L});
    public static final BitSet FOLLOW_wedgeLine_in_wedge5065 = new BitSet(new long[]{0x0800000040000082L,0x0000000000000050L});
    public static final BitSet FOLLOW_wedgeExposure_in_wedgeLine5109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeAngRes_in_wedgeLine5119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeStartOffset_in_wedgeLine5130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeTranslate_in_wedgeLine5140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine5150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURETIME_in_wedgeExposure5167 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeExposure5171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes5253 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeAngRes5257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTOFFSET_in_wedgeStartOffset5364 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset5368 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset5372 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset5393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate5487 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate5491 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate5495 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate5523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset5659 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeRotAxBeamOffset5663 = new BitSet(new long[]{0x0000000000000002L});

}