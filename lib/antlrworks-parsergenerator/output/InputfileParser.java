// $ANTLR 3.4 /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g 2017-10-16 17:02:01

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABSCOEFCALC", "ANGLEL", "ANGLEP", "ANGULARRESOLUTION", "AVERAGE", "BEAM", "CALCULATEESCAPE", "CIRCULAR", "COLLIMATION", "COMMENT", "CONTAINERDENSITY", "CONTAINERMATERIALELEMENTS", "CONTAINERMATERIALMIXTURE", "CONTAINERMATERIALTYPE", "CONTAINERTHICKNESS", "CRYSTAL", "DDM", "DECAYPARAM", "DEFAULT", "DIFFRACTIONDECAYMODEL", "DIMENSION", "DUMMY", "ELEMENT", "ELEMENTAL", "ENERGY", "EXPONENT", "EXPOSURETIME", "FILE", "FLOAT", "FLUX", "FWHM", "HORIZONTAL", "KEV", "LEAL", "LINEAR", "MATERIALELEMENTS", "MATERIALMIXTURE", "MATERIALTYPE", "MIXTURE", "MODELFILE", "NONE", "NUMDNA", "NUMMONOMERS", "NUMRESIDUES", "NUMRNA", "PDB", "PDBNAME", "PIXELSIZE", "PIXELSPERMICRON", "PROTEINCONC", "PROTEINCONCENTRATION", "PROTEINHEAVYATOMS", "RDFORTAN", "RDJAVA", "RECTANGULAR", "ROTAXBEAMOFFSET", "SAXS", "SAXSSEQ", "SEQFILE", "SEQUENCE", "SEQUENCEFILE", "SIMPLE", "SMALLMOLE", "SMALLMOLEATOMS", "SOLVENTFRACTION", "SOLVENTHEAVYCONC", "STARTOFFSET", "STRING", "TRANSLATEPERDEGREE", "TYPE", "UNITCELL", "VERTICAL", "WEDGE", "WIREFRAMETYPE", "WS"
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
    public static final int SMALLMOLE=66;
    public static final int SMALLMOLEATOMS=67;
    public static final int SOLVENTFRACTION=68;
    public static final int SOLVENTHEAVYCONC=69;
    public static final int STARTOFFSET=70;
    public static final int STRING=71;
    public static final int TRANSLATEPERDEGREE=72;
    public static final int TYPE=73;
    public static final int UNITCELL=74;
    public static final int VERTICAL=75;
    public static final int WEDGE=76;
    public static final int WIREFRAMETYPE=77;
    public static final int WS=78;

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
    public String getGrammarFileName() { return "/Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g"; }


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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:41:1: configfile : (a= crystal |b= wedge |c= beam )* EOF ;
    public final void configfile() throws RecognitionException {
        Crystal a =null;

        Wedge b =null;

        Beam c =null;


        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:41:11: ( (a= crystal |b= wedge |c= beam )* EOF )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )* EOF
            {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )*
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
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:42:13: a= crystal
                    {
                    pushFollow(FOLLOW_crystal_in_configfile47);
                    a=crystal();

                    state._fsp--;


                     raddoseInitializer.setCrystal(a); 

                    }
                    break;
                case 2 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:43:13: b= wedge
                    {
                    pushFollow(FOLLOW_wedge_in_configfile65);
                    b=wedge();

                    state._fsp--;


                     raddoseInitializer.exposeWedge(b); 

                    }
                    break;
                case 3 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:44:13: c= beam
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
        List<String>    smallMoleAtomNames;
        List<Double>    smallMoleAtomNums;
        List<String>    heavyProteinAtomNames;
        List<Double>    heavyProteinAtomNums;
        List<String>    heavySolutionConcNames;
        List<Double>    heavySolutionConcNums;
        Double          solFrac;
        HashMap<Object, Object> crystalProperties;
    }
    protected Stack crystal_stack = new Stack();



    // $ANTLR start "crystal"
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:49:1: crystal returns [Crystal cObj] : CRYSTAL ( crystalLine )+ ;
    public final Crystal crystal() throws RecognitionException {
        crystal_stack.push(new crystal_scope());
        Crystal cObj = null;


         
        ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = 2; // 0 = error, 1 = Simple, 2 = DEFAULT, 3 = RDV2, 4 = PDB, 5 = SAXS
                ((crystal_scope)crystal_stack.peek()).crystalProperties = new HashMap<Object, Object>();

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:199:2: ( CRYSTAL ( crystalLine )+ )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:199:4: CRYSTAL ( crystalLine )+
            {
            match(input,CRYSTAL,FOLLOW_CRYSTAL_in_crystal134); 

            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:199:12: ( crystalLine )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= ABSCOEFCALC && LA2_0 <= ANGLEP)||LA2_0==CALCULATEESCAPE||(LA2_0 >= CONTAINERDENSITY && LA2_0 <= CONTAINERTHICKNESS)||(LA2_0 >= DDM && LA2_0 <= DECAYPARAM)||(LA2_0 >= DIFFRACTIONDECAYMODEL && LA2_0 <= DIMENSION)||(LA2_0 >= MATERIALELEMENTS && LA2_0 <= MATERIALTYPE)||LA2_0==MODELFILE||(LA2_0 >= NUMDNA && LA2_0 <= NUMRNA)||LA2_0==PDBNAME||(LA2_0 >= PIXELSPERMICRON && LA2_0 <= PROTEINHEAVYATOMS)||LA2_0==SEQFILE||LA2_0==SEQUENCEFILE||(LA2_0 >= SMALLMOLEATOMS && LA2_0 <= SOLVENTHEAVYCONC)||(LA2_0 >= TYPE && LA2_0 <= UNITCELL)||LA2_0==WIREFRAMETYPE) ) {
                    alt2=1;
                }


                switch (alt2) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:199:12: crystalLine
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
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcSmallMolecules(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
                                                                ((crystal_scope)crystal_stack.peek()).numMon,
                                                                ((crystal_scope)crystal_stack.peek()).smallMoleAtomNames, ((crystal_scope)crystal_stack.peek()).smallMoleAtomNums,
                                                                ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
                                                                ((crystal_scope)crystal_stack.peek()).solFrac);
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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:202:1: crystalLine : (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculateEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile |ab= smallMoleAtoms );
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

        InputfileParser.smallMoleAtoms_return ab =null;


        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:203:2: (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculateEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile |ab= smallMoleAtoms )
            int alt3=28;
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
            case SMALLMOLEATOMS:
                {
                alt3=28;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:203:4: a= crystalType
                    {
                    pushFollow(FOLLOW_crystalType_in_crystalLine192);
                    a=crystalType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalType = a; 

                    }
                    break;
                case 2 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:204:4: b= crystalDDM
                    {
                    pushFollow(FOLLOW_crystalDDM_in_crystalLine203);
                    b=crystalDDM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalDdm = b; 

                    }
                    break;
                case 3 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:205:4: c= crystalCoefcalc
                    {
                    pushFollow(FOLLOW_crystalCoefcalc_in_crystalLine215);
                    c=crystalCoefcalc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = c; 

                    }
                    break;
                case 4 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:206:4: d= crystalDim
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
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:209:4: e= crystalPPM
                    {
                    pushFollow(FOLLOW_crystalPPM_in_crystalLine236);
                    e=crystalPPM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, e); 

                    }
                    break;
                case 6 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:210:4: f= crystalAngP
                    {
                    pushFollow(FOLLOW_crystalAngP_in_crystalLine247);
                    f=crystalAngP();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, f); 

                    }
                    break;
                case 7 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:211:4: g= crystalAngL
                    {
                    pushFollow(FOLLOW_crystalAngL_in_crystalLine258);
                    g=crystalAngL();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, g); 

                    }
                    break;
                case 8 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:212:4: h= crystalDecayParam
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
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:215:4: i= containerThickness
                    {
                    pushFollow(FOLLOW_containerThickness_in_crystalLine279);
                    i=containerThickness();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerThickness = i; 

                    }
                    break;
                case 10 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:216:4: j= containerDensity
                    {
                    pushFollow(FOLLOW_containerDensity_in_crystalLine289);
                    j=containerDensity();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerDensity = j; 

                    }
                    break;
                case 11 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:217:4: k= crystalContainerMaterial
                    {
                    pushFollow(FOLLOW_crystalContainerMaterial_in_crystalLine299);
                    k=crystalContainerMaterial();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalContainerMaterial = k; 

                    }
                    break;
                case 12 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:218:4: l= containerMaterialMixture
                    {
                    pushFollow(FOLLOW_containerMaterialMixture_in_crystalLine308);
                    l=containerMaterialMixture();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerMixture = l; 

                    }
                    break;
                case 13 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:219:4: m= unitcell
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
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:225:4: n= nummonomers
                    {
                    pushFollow(FOLLOW_nummonomers_in_crystalLine328);
                    n=nummonomers();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numMon = n;  

                    }
                    break;
                case 15 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:226:4: o= numresidues
                    {
                    pushFollow(FOLLOW_numresidues_in_crystalLine339);
                    o=numresidues();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRes = o;  

                    }
                    break;
                case 16 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:227:4: p= numRNA
                    {
                    pushFollow(FOLLOW_numRNA_in_crystalLine350);
                    p=numRNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRNA = p;  

                    }
                    break;
                case 17 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:228:4: q= numDNA
                    {
                    pushFollow(FOLLOW_numDNA_in_crystalLine363);
                    q=numDNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numDNA = q;  

                    }
                    break;
                case 18 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:229:4: r= heavyProteinAtoms
                    {
                    pushFollow(FOLLOW_heavyProteinAtoms_in_crystalLine376);
                    r=heavyProteinAtoms();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames = (r!=null?r.names:null);
                                                  ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums = (r!=null?r.num:null);    

                    }
                    break;
                case 19 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:231:4: s= heavySolutionConc
                    {
                    pushFollow(FOLLOW_heavySolutionConc_in_crystalLine385);
                    s=heavySolutionConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames = (s!=null?s.names:null);
                                                  ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums = (s!=null?s.num:null);   

                    }
                    break;
                case 20 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:233:4: t= solventFraction
                    {
                    pushFollow(FOLLOW_solventFraction_in_crystalLine394);
                    t=solventFraction();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).solFrac = t; 

                    }
                    break;
                case 21 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:234:4: u= pdb
                    {
                    pushFollow(FOLLOW_pdb_in_crystalLine404);
                    u=pdb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).pdb = u; 

                    }
                    break;
                case 22 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:235:4: v= wireframeType
                    {
                    pushFollow(FOLLOW_wireframeType_in_crystalLine417);
                    v=wireframeType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_TYPE, v); 

                    }
                    break;
                case 23 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:236:4: w= modelFile
                    {
                    pushFollow(FOLLOW_modelFile_in_crystalLine428);
                    w=modelFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_FILE, w); 

                    }
                    break;
                case 24 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:237:4: x= calculateEscape
                    {
                    pushFollow(FOLLOW_calculateEscape_in_crystalLine440);
                    x=calculateEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ELECTRON_ESCAPE, x); 

                    }
                    break;
                case 25 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:238:4: y= proteinConcentration
                    {
                    pushFollow(FOLLOW_proteinConcentration_in_crystalLine450);
                    y=proteinConcentration();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).proteinConc = y;

                    }
                    break;
                case 26 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:239:4: z= containerMaterialElements
                    {
                    pushFollow(FOLLOW_containerMaterialElements_in_crystalLine459);
                    z=containerMaterialElements();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerElementNames = (z!=null?z.names:null);
                                                  ((crystal_scope)crystal_stack.peek()).containerElementNums = (z!=null?z.num:null);    

                    }
                    break;
                case 27 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:241:4: aa= sequenceFile
                    {
                    pushFollow(FOLLOW_sequenceFile_in_crystalLine468);
                    aa=sequenceFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).seqFile = aa; 

                    }
                    break;
                case 28 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:242:4: ab= smallMoleAtoms
                    {
                    pushFollow(FOLLOW_smallMoleAtoms_in_crystalLine479);
                    ab=smallMoleAtoms();

                    state._fsp--;


                    ((crystal_scope)crystal_stack.peek()).smallMoleAtomNames = (ab!=null?ab.names:null);
                                                ((crystal_scope)crystal_stack.peek()).smallMoleAtomNums = (ab!=null?ab.num:null);   

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:248:1: crystalType returns [String crystalType] : TYPE e= STRING ;
    public final String crystalType() throws RecognitionException {
        String crystalType = null;


        Token e=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:249:2: ( TYPE e= STRING )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:249:4: TYPE e= STRING
            {
            match(input,TYPE,FOLLOW_TYPE_in_crystalType514); 

            e=(Token)match(input,STRING,FOLLOW_STRING_in_crystalType518); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:252:1: crystalDDM returns [int value] : ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword ;
    public final int crystalDDM() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:253:2: ( ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:253:4: ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword
            {
            if ( input.LA(1)==DDM||input.LA(1)==DIFFRACTIONDECAYMODEL ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalDDMKeyword_in_crystalDDM572);
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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:256:1: crystalDDMKeyword returns [int value] : ( SIMPLE | LINEAR | LEAL );
    public final int crystalDDMKeyword() throws RecognitionException {
        int value = 0;


        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:257:2: ( SIMPLE | LINEAR | LEAL )
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
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:257:4: SIMPLE
                    {
                    match(input,SIMPLE,FOLLOW_SIMPLE_in_crystalDDMKeyword720); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:258:4: LINEAR
                    {
                    match(input,LINEAR,FOLLOW_LINEAR_in_crystalDDMKeyword727); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:259:4: LEAL
                    {
                    match(input,LEAL,FOLLOW_LEAL_in_crystalDDMKeyword734); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:265:1: crystalDecayParam returns [Double gammaParam, Double b0Param, Double betaParam] : DECAYPARAM a= FLOAT b= FLOAT c= FLOAT ;
    public final InputfileParser.crystalDecayParam_return crystalDecayParam() throws RecognitionException {
        InputfileParser.crystalDecayParam_return retval = new InputfileParser.crystalDecayParam_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:266:2: ( DECAYPARAM a= FLOAT b= FLOAT c= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:266:4: DECAYPARAM a= FLOAT b= FLOAT c= FLOAT
            {
            match(input,DECAYPARAM,FOLLOW_DECAYPARAM_in_crystalDecayParam854); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam858); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam862); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam866); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:269:1: crystalCoefcalc returns [int value] : ABSCOEFCALC a= crystalCoefcalcKeyword ;
    public final int crystalCoefcalc() throws RecognitionException {
        int value = 0;


        int a =0;


        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:270:2: ( ABSCOEFCALC a= crystalCoefcalcKeyword )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:270:4: ABSCOEFCALC a= crystalCoefcalcKeyword
            {
            match(input,ABSCOEFCALC,FOLLOW_ABSCOEFCALC_in_crystalCoefcalc938); 

            pushFollow(FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc942);
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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:272:1: crystalCoefcalcKeyword returns [int value] : ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS | SEQUENCE | SAXSSEQ | SMALLMOLE );
    public final int crystalCoefcalcKeyword() throws RecognitionException {
        int value = 0;


        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:273:2: ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS | SEQUENCE | SAXSSEQ | SMALLMOLE )
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
            case SMALLMOLE:
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
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:273:4: DUMMY
                    {
                    match(input,DUMMY,FOLLOW_DUMMY_in_crystalCoefcalcKeyword1021); 

                     value = 1;

                    }
                    break;
                case 2 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:274:4: AVERAGE
                    {
                    match(input,AVERAGE,FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1031); 

                     value = 1;

                    }
                    break;
                case 3 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:275:4: DEFAULT
                    {
                    match(input,DEFAULT,FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1039); 

                     value = 2;

                    }
                    break;
                case 4 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:276:4: RDJAVA
                    {
                    match(input,RDJAVA,FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1047); 

                     value = 2;

                    }
                    break;
                case 5 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:277:4: RDFORTAN
                    {
                    match(input,RDFORTAN,FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1054); 

                     value = 3;

                    }
                    break;
                case 6 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:278:4: PDB
                    {
                    match(input,PDB,FOLLOW_PDB_in_crystalCoefcalcKeyword1061); 

                     value = 4;

                    }
                    break;
                case 7 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:279:4: SAXS
                    {
                    match(input,SAXS,FOLLOW_SAXS_in_crystalCoefcalcKeyword1071); 

                     value = 5;

                    }
                    break;
                case 8 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:280:4: SEQUENCE
                    {
                    match(input,SEQUENCE,FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1079); 

                     value = 6;

                    }
                    break;
                case 9 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:281:4: SAXSSEQ
                    {
                    match(input,SAXSSEQ,FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1086); 

                     value = 7;

                    }
                    break;
                case 10 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:282:4: SMALLMOLE
                    {
                    match(input,SMALLMOLE,FOLLOW_SMALLMOLE_in_crystalCoefcalcKeyword1093); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:295:1: crystalDim returns [Map<Object, Object> properties] : DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) ;
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
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:298:3: ( DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:298:5: DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
            {
            match(input,DIMENSION,FOLLOW_DIMENSION_in_crystalDim1472); 

            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:299:2: (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==FLOAT) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==FLOAT) ) {
                    int LA6_2 = input.LA(3);

                    if ( (LA6_2==FLOAT) ) {
                        alt6=1;
                    }
                    else if ( (LA6_2==EOF||(LA6_2 >= ABSCOEFCALC && LA6_2 <= ANGLEP)||(LA6_2 >= BEAM && LA6_2 <= CALCULATEESCAPE)||(LA6_2 >= CONTAINERDENSITY && LA6_2 <= DECAYPARAM)||(LA6_2 >= DIFFRACTIONDECAYMODEL && LA6_2 <= DIMENSION)||(LA6_2 >= MATERIALELEMENTS && LA6_2 <= MATERIALTYPE)||LA6_2==MODELFILE||(LA6_2 >= NUMDNA && LA6_2 <= NUMRNA)||LA6_2==PDBNAME||(LA6_2 >= PIXELSPERMICRON && LA6_2 <= PROTEINHEAVYATOMS)||LA6_2==SEQFILE||LA6_2==SEQUENCEFILE||(LA6_2 >= SMALLMOLEATOMS && LA6_2 <= SOLVENTHEAVYCONC)||(LA6_2 >= TYPE && LA6_2 <= UNITCELL)||(LA6_2 >= WEDGE && LA6_2 <= WIREFRAMETYPE)) ) {
                        alt6=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA6_1==EOF||(LA6_1 >= ABSCOEFCALC && LA6_1 <= ANGLEP)||(LA6_1 >= BEAM && LA6_1 <= CALCULATEESCAPE)||(LA6_1 >= CONTAINERDENSITY && LA6_1 <= DECAYPARAM)||(LA6_1 >= DIFFRACTIONDECAYMODEL && LA6_1 <= DIMENSION)||(LA6_1 >= MATERIALELEMENTS && LA6_1 <= MATERIALTYPE)||LA6_1==MODELFILE||(LA6_1 >= NUMDNA && LA6_1 <= NUMRNA)||LA6_1==PDBNAME||(LA6_1 >= PIXELSPERMICRON && LA6_1 <= PROTEINHEAVYATOMS)||LA6_1==SEQFILE||LA6_1==SEQUENCEFILE||(LA6_1 >= SMALLMOLEATOMS && LA6_1 <= SOLVENTHEAVYCONC)||(LA6_1 >= TYPE && LA6_1 <= UNITCELL)||(LA6_1 >= WEDGE && LA6_1 <= WIREFRAMETYPE)) ) {
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
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:300:7: a= FLOAT b= FLOAT c= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1485); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1489); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1493); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((a!=null?a.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((b!=null?b.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble((c!=null?c.getText():null))); 

                    }
                    break;
                case 2 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:303:7: e= FLOAT f= FLOAT
                    {
                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1505); 

                    f=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1509); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((e!=null?e.getText():null)));
                                       properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((f!=null?f.getText():null))); 

                    }
                    break;
                case 3 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:305:7: d= FLOAT
                    {
                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1521); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:309:1: crystalAngP returns [double value] : ANGLEP a= FLOAT ;
    public final double crystalAngP() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:310:2: ( ANGLEP a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:310:4: ANGLEP a= FLOAT
            {
            match(input,ANGLEP,FOLLOW_ANGLEP_in_crystalAngP1598); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngP1602); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:314:1: crystalAngL returns [double value] : ANGLEL a= FLOAT ;
    public final double crystalAngL() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:315:2: ( ANGLEL a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:315:4: ANGLEL a= FLOAT
            {
            match(input,ANGLEL,FOLLOW_ANGLEL_in_crystalAngL1657); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngL1661); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:319:1: crystalPPM returns [double ppm] : PIXELSPERMICRON FLOAT ;
    public final double crystalPPM() throws RecognitionException {
        double ppm = 0.0;


        Token FLOAT1=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:320:2: ( PIXELSPERMICRON FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:320:4: PIXELSPERMICRON FLOAT
            {
            match(input,PIXELSPERMICRON,FOLLOW_PIXELSPERMICRON_in_crystalPPM1715); 

            FLOAT1=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalPPM1717); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:323:1: unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC] : UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? ;
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
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:324:2: ( UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:324:4: UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )?
            {
            match(input,UNITCELL,FOLLOW_UNITCELL_in_unitcell1815); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1819); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1823); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1827); 

            retval.dimA = Double.parseDouble((a!=null?a.getText():null));
                     retval.dimB = Double.parseDouble((b!=null?b.getText():null));
                     retval.dimC = Double.parseDouble((c!=null?c.getText():null)); 

            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:328:7: (al= FLOAT be= FLOAT ga= FLOAT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FLOAT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:328:8: al= FLOAT be= FLOAT ga= FLOAT
                    {
                    al=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1842); 

                    be=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1846); 

                    ga=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell1850); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:336:1: proteinConcentration returns [Double proteinConc] : ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT ;
    public final Double proteinConcentration() throws RecognitionException {
        Double proteinConc = null;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:337:2: ( ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:337:4: ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT
            {
            if ( (input.LA(1) >= PROTEINCONC && input.LA(1) <= PROTEINCONCENTRATION) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_proteinConcentration1938); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:341:1: nummonomers returns [int value] : NUMMONOMERS a= FLOAT ;
    public final int nummonomers() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:342:2: ( NUMMONOMERS a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:342:4: NUMMONOMERS a= FLOAT
            {
            match(input,NUMMONOMERS,FOLLOW_NUMMONOMERS_in_nummonomers2120); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_nummonomers2124); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:345:1: numresidues returns [int value] : NUMRESIDUES a= FLOAT ;
    public final int numresidues() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:346:2: ( NUMRESIDUES a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:346:4: NUMRESIDUES a= FLOAT
            {
            match(input,NUMRESIDUES,FOLLOW_NUMRESIDUES_in_numresidues2201); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numresidues2205); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:349:1: numRNA returns [int value] : NUMRNA a= FLOAT ;
    public final int numRNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:350:2: ( NUMRNA a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:350:4: NUMRNA a= FLOAT
            {
            match(input,NUMRNA,FOLLOW_NUMRNA_in_numRNA2283); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numRNA2287); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:353:1: numDNA returns [int value] : NUMDNA a= FLOAT ;
    public final int numDNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:354:2: ( NUMDNA a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:354:4: NUMDNA a= FLOAT
            {
            match(input,NUMDNA,FOLLOW_NUMDNA_in_numDNA2340); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numDNA2344); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:357:1: heavyProteinAtoms returns [List<String> names, List<Double> num;] : PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavyProteinAtoms_return heavyProteinAtoms() throws RecognitionException {
        InputfileParser.heavyProteinAtoms_return retval = new InputfileParser.heavyProteinAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:362:2: ( PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:362:4: PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,PROTEINHEAVYATOMS,FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2400); 

            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:362:22: (a= ELEMENT b= FLOAT )+
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
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:362:23: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavyProteinAtoms2405); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavyProteinAtoms2409); 

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


    public static class smallMoleAtoms_return extends ParserRuleReturnScope {
        public List<String> names;
        public List<Double> num;;
    };


    // $ANTLR start "smallMoleAtoms"
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:366:1: smallMoleAtoms returns [List<String> names, List<Double> num;] : SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.smallMoleAtoms_return smallMoleAtoms() throws RecognitionException {
        InputfileParser.smallMoleAtoms_return retval = new InputfileParser.smallMoleAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:371:2: ( SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+ )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:371:4: SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,SMALLMOLEATOMS,FOLLOW_SMALLMOLEATOMS_in_smallMoleAtoms2556); 

            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:371:19: (a= ELEMENT b= FLOAT )+
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
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:371:20: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_smallMoleAtoms2561); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_smallMoleAtoms2565); 

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
    // $ANTLR end "smallMoleAtoms"


    public static class heavySolutionConc_return extends ParserRuleReturnScope {
        public List<String> names;
        public List<Double> num;;
    };


    // $ANTLR start "heavySolutionConc"
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:374:1: heavySolutionConc returns [List<String> names, List<Double> num;] : SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavySolutionConc_return heavySolutionConc() throws RecognitionException {
        InputfileParser.heavySolutionConc_return retval = new InputfileParser.heavySolutionConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:379:2: ( SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:379:4: SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SOLVENTHEAVYCONC,FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc2667); 

            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:379:21: (a= ELEMENT b= FLOAT )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==ELEMENT) ) {
                    alt10=1;
                }


                switch (alt10) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:379:22: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavySolutionConc2672); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavySolutionConc2676); 

                    retval.names.add((a!=null?a.getText():null)); retval.num.add(Double.parseDouble((b!=null?b.getText():null))); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:382:1: solventFraction returns [double solFrac] : SOLVENTFRACTION a= FLOAT ;
    public final double solventFraction() throws RecognitionException {
        double solFrac = 0.0;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:383:2: ( SOLVENTFRACTION a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:383:4: SOLVENTFRACTION a= FLOAT
            {
            match(input,SOLVENTFRACTION,FOLLOW_SOLVENTFRACTION_in_solventFraction2782); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_solventFraction2786); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:386:1: pdb returns [String pdb] : PDBNAME a= STRING ;
    public final String pdb() throws RecognitionException {
        String pdb = null;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:387:2: ( PDBNAME a= STRING )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:387:4: PDBNAME a= STRING
            {
            match(input,PDBNAME,FOLLOW_PDBNAME_in_pdb2883); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_pdb2887); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:390:1: wireframeType returns [String value] : WIREFRAMETYPE a= STRING ;
    public final String wireframeType() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:391:2: ( WIREFRAMETYPE a= STRING )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:391:4: WIREFRAMETYPE a= STRING
            {
            match(input,WIREFRAMETYPE,FOLLOW_WIREFRAMETYPE_in_wireframeType2924); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_wireframeType2928); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:394:1: modelFile returns [String value] : MODELFILE a= STRING ;
    public final String modelFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:395:2: ( MODELFILE a= STRING )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:395:4: MODELFILE a= STRING
            {
            match(input,MODELFILE,FOLLOW_MODELFILE_in_modelFile3016); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_modelFile3020); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:398:1: calculateEscape returns [String value] : CALCULATEESCAPE a= STRING ;
    public final String calculateEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:399:2: ( CALCULATEESCAPE a= STRING )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:399:4: CALCULATEESCAPE a= STRING
            {
            match(input,CALCULATEESCAPE,FOLLOW_CALCULATEESCAPE_in_calculateEscape3087); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculateEscape3091); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:403:1: crystalContainerMaterial returns [int value] : ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword ;
    public final int crystalContainerMaterial() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:404:2: ( ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:404:4: ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword
            {
            if ( input.LA(1)==CONTAINERMATERIALTYPE||input.LA(1)==MATERIALTYPE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial3205);
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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:407:1: crystalContainerKeyword returns [int value] : ( NONE | MIXTURE | ELEMENTAL );
    public final int crystalContainerKeyword() throws RecognitionException {
        int value = 0;


        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:408:2: ( NONE | MIXTURE | ELEMENTAL )
            int alt11=3;
            switch ( input.LA(1) ) {
            case NONE:
                {
                alt11=1;
                }
                break;
            case MIXTURE:
                {
                alt11=2;
                }
                break;
            case ELEMENTAL:
                {
                alt11=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }

            switch (alt11) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:408:4: NONE
                    {
                    match(input,NONE,FOLLOW_NONE_in_crystalContainerKeyword3398); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:409:4: MIXTURE
                    {
                    match(input,MIXTURE,FOLLOW_MIXTURE_in_crystalContainerKeyword3407); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:410:4: ELEMENTAL
                    {
                    match(input,ELEMENTAL,FOLLOW_ELEMENTAL_in_crystalContainerKeyword3415); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:416:1: containerThickness returns [double value] : CONTAINERTHICKNESS a= FLOAT ;
    public final double containerThickness() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:417:2: ( CONTAINERTHICKNESS a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:417:4: CONTAINERTHICKNESS a= FLOAT
            {
            match(input,CONTAINERTHICKNESS,FOLLOW_CONTAINERTHICKNESS_in_containerThickness3555); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerThickness3559); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:420:1: containerMaterialMixture returns [String value] : ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING ;
    public final String containerMaterialMixture() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:421:2: ( ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:421:4: ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING
            {
            if ( input.LA(1)==CONTAINERMATERIALMIXTURE||input.LA(1)==MATERIALMIXTURE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_containerMaterialMixture3680); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:425:1: containerMaterialElements returns [List<String> names, List<Double> num;] : ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.containerMaterialElements_return containerMaterialElements() throws RecognitionException {
        InputfileParser.containerMaterialElements_return retval = new InputfileParser.containerMaterialElements_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:430:2: ( ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:430:4: ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+
            {
            if ( input.LA(1)==CONTAINERMATERIALELEMENTS||input.LA(1)==MATERIALELEMENTS ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:430:51: (a= ELEMENT b= FLOAT )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==ELEMENT) ) {
                    alt12=1;
                }


                switch (alt12) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:430:52: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_containerMaterialElements3916); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerMaterialElements3920); 

                    retval.names.add((a!=null?a.getText():null)); retval.num.add(Double.parseDouble((b!=null?b.getText():null))); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:434:1: containerDensity returns [double value] : CONTAINERDENSITY a= FLOAT ;
    public final double containerDensity() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:435:2: ( CONTAINERDENSITY a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:435:4: CONTAINERDENSITY a= FLOAT
            {
            match(input,CONTAINERDENSITY,FOLLOW_CONTAINERDENSITY_in_containerDensity4155); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerDensity4159); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:438:1: sequenceFile returns [String value] : ( SEQUENCEFILE | SEQFILE ) a= STRING ;
    public final String sequenceFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:439:2: ( ( SEQUENCEFILE | SEQFILE ) a= STRING )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:439:4: ( SEQUENCEFILE | SEQFILE ) a= STRING
            {
            if ( input.LA(1)==SEQFILE||input.LA(1)==SEQUENCEFILE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_sequenceFile4270); 

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
    protected Stack beam_stack = new Stack();



    // $ANTLR start "beam"
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:445:1: beam returns [Beam bObj] : BEAM ( beamLine )+ ;
    public final Beam beam() throws RecognitionException {
        beam_stack.push(new beam_scope());
        Beam bObj = null;


         
                ((beam_scope)beam_stack.peek()).beamProperties = new HashMap<Object, Object>();

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:456:2: ( BEAM ( beamLine )+ )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:456:4: BEAM ( beamLine )+
            {
            match(input,BEAM,FOLLOW_BEAM_in_beam4409); 

            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:456:9: ( beamLine )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0 >= CIRCULAR && LA13_0 <= COLLIMATION)||LA13_0==ENERGY||LA13_0==FILE||(LA13_0 >= FLUX && LA13_0 <= HORIZONTAL)||LA13_0==PIXELSIZE||LA13_0==RECTANGULAR||LA13_0==TYPE||LA13_0==VERTICAL) ) {
                    alt13=1;
                }


                switch (alt13) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:456:9: beamLine
                    {
                    pushFollow(FOLLOW_beamLine_in_beam4411);
                    beamLine();

                    state._fsp--;


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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:459:1: beamLine : ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize );
    public final void beamLine() throws RecognitionException {
        Token a=null;
        Double b =null;

        InputfileParser.beamFWHM_return c =null;

        Double d =null;

        Map<Object, Object> e =null;

        String f =null;

        Map<Object, Object> g =null;


        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:460:2: ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize )
            int alt14=7;
            switch ( input.LA(1) ) {
            case TYPE:
                {
                alt14=1;
                }
                break;
            case FLUX:
                {
                alt14=2;
                }
                break;
            case FWHM:
                {
                alt14=3;
                }
                break;
            case ENERGY:
                {
                alt14=4;
                }
                break;
            case CIRCULAR:
            case COLLIMATION:
            case HORIZONTAL:
            case RECTANGULAR:
            case VERTICAL:
                {
                alt14=5;
                }
                break;
            case FILE:
                {
                alt14=6;
                }
                break;
            case PIXELSIZE:
                {
                alt14=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }

            switch (alt14) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:460:4: TYPE a= STRING
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_beamLine4450); 

                    a=(Token)match(input,STRING,FOLLOW_STRING_in_beamLine4454); 

                     ((beam_scope)beam_stack.peek()).beamType = (a!=null?a.getText():null); 

                    }
                    break;
                case 2 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:461:4: b= beamFlux
                    {
                    pushFollow(FOLLOW_beamFlux_in_beamLine4472);
                    b=beamFlux();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FLUX, b); 

                    }
                    break;
                case 3 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:462:4: c= beamFWHM
                    {
                    pushFollow(FOLLOW_beamFWHM_in_beamLine4484);
                    c=beamFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_X, (c!=null?c.x:null)); 
                                                   ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_Y, (c!=null?c.y:null)); 

                    }
                    break;
                case 4 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:464:4: d= beamEnergy
                    {
                    pushFollow(FOLLOW_beamEnergy_in_beamLine4496);
                    d=beamEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_ENERGY, d); 

                    }
                    break;
                case 5 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:465:4: e= beamCollimation
                    {
                    pushFollow(FOLLOW_beamCollimation_in_beamLine4508);
                    e=beamCollimation();

                    state._fsp--;


                     if (e != null) {
                                                    ((beam_scope)beam_stack.peek()).beamProperties.putAll(e);
                                                   } 

                    }
                    break;
                case 6 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:468:4: f= beamFile
                    {
                    pushFollow(FOLLOW_beamFile_in_beamLine4519);
                    f=beamFile();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXTFILE, f); 

                    }
                    break;
                case 7 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:469:4: g= beamPixelSize
                    {
                    pushFollow(FOLLOW_beamPixelSize_in_beamLine4540);
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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:472:1: beamFlux returns [Double flux] : FLUX a= FLOAT ;
    public final Double beamFlux() throws RecognitionException {
        Double flux = null;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:473:2: ( FLUX a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:473:4: FLUX a= FLOAT
            {
            match(input,FLUX,FOLLOW_FLUX_in_beamFlux4564); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFlux4568); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:476:1: beamFWHM returns [Double x, Double y] : FWHM a= FLOAT b= FLOAT ;
    public final InputfileParser.beamFWHM_return beamFWHM() throws RecognitionException {
        InputfileParser.beamFWHM_return retval = new InputfileParser.beamFWHM_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:477:2: ( FWHM a= FLOAT b= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:477:4: FWHM a= FLOAT b= FLOAT
            {
            match(input,FWHM,FOLLOW_FWHM_in_beamFWHM4610); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM4614); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM4618); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:480:1: beamEnergy returns [Double energy] : ENERGY a= FLOAT ( KEV )? ;
    public final Double beamEnergy() throws RecognitionException {
        Double energy = null;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:481:2: ( ENERGY a= FLOAT ( KEV )? )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:481:4: ENERGY a= FLOAT ( KEV )?
            {
            match(input,ENERGY,FOLLOW_ENERGY_in_beamEnergy4660); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamEnergy4664); 

            energy = Double.parseDouble((a!=null?a.getText():null));

            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:482:2: ( KEV )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==KEV) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:482:4: KEV
                    {
                    match(input,KEV,FOLLOW_KEV_in_beamEnergy4671); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:487:1: beamFile returns [String filename] : FILE a= STRING ;
    public final String beamFile() throws RecognitionException {
        String filename = null;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:488:2: ( FILE a= STRING )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:488:4: FILE a= STRING
            {
            match(input,FILE,FOLLOW_FILE_in_beamFile4749); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_beamFile4753); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:492:1: beamPixelSize returns [Map<Object, Object> properties] : PIXELSIZE a= FLOAT b= FLOAT ;
    public final Map<Object, Object> beamPixelSize() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:493:5: ( PIXELSIZE a= FLOAT b= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:493:7: PIXELSIZE a= FLOAT b= FLOAT
            {
            match(input,PIXELSIZE,FOLLOW_PIXELSIZE_in_beamPixelSize4800); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize4804); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize4808); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:500:1: beamCollimation returns [Map<Object, Object> properties] : ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT );
    public final Map<Object, Object> beamCollimation() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token d=null;
        Token e=null;

         
                properties = new HashMap<Object, Object>();

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:504:2: ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT )
            int alt16=5;
            switch ( input.LA(1) ) {
            case COLLIMATION:
                {
                alt16=1;
                }
                break;
            case RECTANGULAR:
                {
                alt16=2;
                }
                break;
            case CIRCULAR:
                {
                alt16=3;
                }
                break;
            case HORIZONTAL:
                {
                alt16=4;
                }
                break;
            case VERTICAL:
                {
                alt16=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }

            switch (alt16) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:504:4: COLLIMATION
                    {
                    match(input,COLLIMATION,FOLLOW_COLLIMATION_in_beamCollimation4887); 

                    }
                    break;
                case 2 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:505:4: RECTANGULAR a= FLOAT b= FLOAT
                    {
                    match(input,RECTANGULAR,FOLLOW_RECTANGULAR_in_beamCollimation4893); 

                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4897); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4901); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((a!=null?a.getText():null)));
                                                        properties.put(Beam.BEAM_COLL_V, Double.parseDouble((b!=null?b.getText():null))); 

                    }
                    break;
                case 3 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:507:4: CIRCULAR FLOAT
                    {
                    match(input,CIRCULAR,FOLLOW_CIRCULAR_in_beamCollimation4908); 

                    match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4910); 

                    }
                    break;
                case 4 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:508:4: HORIZONTAL d= FLOAT
                    {
                    match(input,HORIZONTAL,FOLLOW_HORIZONTAL_in_beamCollimation4916); 

                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4920); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((d!=null?d.getText():null))); 

                    }
                    break;
                case 5 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:509:4: VERTICAL e= FLOAT
                    {
                    match(input,VERTICAL,FOLLOW_VERTICAL_in_beamCollimation4927); 

                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation4931); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:519:1: wedge returns [Wedge wObj] : WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ ;
    public final Wedge wedge() throws RecognitionException {
        wedge_stack.push(new wedge_scope());
        Wedge wObj = null;


        Token a=null;
        Token b=null;

         

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:538:2: ( WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:538:4: WEDGE a= FLOAT b= FLOAT ( wedgeLine )+
            {
            match(input,WEDGE,FOLLOW_WEDGE_in_wedge5244); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge5248); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge5252); 

            ((wedge_scope)wedge_stack.peek()).startAng = Double.parseDouble((a!=null?a.getText():null));
                                         ((wedge_scope)wedge_stack.peek()).endAng = Double.parseDouble((b!=null?b.getText():null)); 

            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:540:4: ( wedgeLine )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==ANGULARRESOLUTION||LA17_0==EXPOSURETIME||LA17_0==ROTAXBEAMOFFSET||LA17_0==STARTOFFSET||LA17_0==TRANSLATEPERDEGREE) ) {
                    alt17=1;
                }


                switch (alt17) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:540:4: wedgeLine
                    {
                    pushFollow(FOLLOW_wedgeLine_in_wedge5259);
                    wedgeLine();

                    state._fsp--;


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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:543:1: wedgeLine : (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset );
    public final void wedgeLine() throws RecognitionException {
        double a =0.0;

        double b =0.0;

        InputfileParser.wedgeStartOffset_return c =null;

        InputfileParser.wedgeTranslate_return d =null;

        double e =0.0;


        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:544:2: (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset )
            int alt18=5;
            switch ( input.LA(1) ) {
            case EXPOSURETIME:
                {
                alt18=1;
                }
                break;
            case ANGULARRESOLUTION:
                {
                alt18=2;
                }
                break;
            case STARTOFFSET:
                {
                alt18=3;
                }
                break;
            case TRANSLATEPERDEGREE:
                {
                alt18=4;
                }
                break;
            case ROTAXBEAMOFFSET:
                {
                alt18=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }

            switch (alt18) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:544:4: a= wedgeExposure
                    {
                    pushFollow(FOLLOW_wedgeExposure_in_wedgeLine5303);
                    a=wedgeExposure();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).expTime =a; 

                    }
                    break;
                case 2 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:545:4: b= wedgeAngRes
                    {
                    pushFollow(FOLLOW_wedgeAngRes_in_wedgeLine5313);
                    b=wedgeAngRes();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).angRes =b; 

                    }
                    break;
                case 3 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:546:4: c= wedgeStartOffset
                    {
                    pushFollow(FOLLOW_wedgeStartOffset_in_wedgeLine5324);
                    c=wedgeStartOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).offsetX =(c!=null?c.x:null);
                                                 ((wedge_scope)wedge_stack.peek()).offsetY =(c!=null?c.y:null);
                                                 ((wedge_scope)wedge_stack.peek()).offsetZ =(c!=null?c.z:null); 

                    }
                    break;
                case 4 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:549:4: d= wedgeTranslate
                    {
                    pushFollow(FOLLOW_wedgeTranslate_in_wedgeLine5334);
                    d=wedgeTranslate();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).translateX =(d!=null?d.x:null);
                                                 ((wedge_scope)wedge_stack.peek()).translateY =(d!=null?d.y:null);
                                                 ((wedge_scope)wedge_stack.peek()).translateZ =(d!=null?d.z:null); 

                    }
                    break;
                case 5 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:552:4: e= wedgeRotAxBeamOffset
                    {
                    pushFollow(FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine5344);
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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:555:1: wedgeExposure returns [double value] : EXPOSURETIME a= FLOAT ;
    public final double wedgeExposure() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:556:2: ( EXPOSURETIME a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:556:4: EXPOSURETIME a= FLOAT
            {
            match(input,EXPOSURETIME,FOLLOW_EXPOSURETIME_in_wedgeExposure5361); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeExposure5365); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:559:1: wedgeAngRes returns [double res] : ANGULARRESOLUTION a= FLOAT ;
    public final double wedgeAngRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:560:2: ( ANGULARRESOLUTION a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:560:4: ANGULARRESOLUTION a= FLOAT
            {
            match(input,ANGULARRESOLUTION,FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes5447); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeAngRes5451); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:563:1: wedgeStartOffset returns [Double x, Double y, Double z] : STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeStartOffset_return wedgeStartOffset() throws RecognitionException {
        InputfileParser.wedgeStartOffset_return retval = new InputfileParser.wedgeStartOffset_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:564:2: ( STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:564:4: STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,STARTOFFSET,FOLLOW_STARTOFFSET_in_wedgeStartOffset5558); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset5562); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset5566); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:565:17: (c= FLOAT )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==FLOAT) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:565:17: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset5587); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:569:1: wedgeTranslate returns [Double x, Double y, Double z] : TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeTranslate_return wedgeTranslate() throws RecognitionException {
        InputfileParser.wedgeTranslate_return retval = new InputfileParser.wedgeTranslate_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:570:2: ( TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:570:4: TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,TRANSLATEPERDEGREE,FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate5681); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate5685); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate5689); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:571:24: (c= FLOAT )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==FLOAT) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:571:24: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate5717); 

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
    // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:575:1: wedgeRotAxBeamOffset returns [double delta] : ROTAXBEAMOFFSET a= FLOAT ;
    public final double wedgeRotAxBeamOffset() throws RecognitionException {
        double delta = 0.0;


        Token a=null;

        try {
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:576:2: ( ROTAXBEAMOFFSET a= FLOAT )
            // /Users/charlesbury/Documents/DPhil/RADDOSE-3D/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:576:4: ROTAXBEAMOFFSET a= FLOAT
            {
            match(input,ROTAXBEAMOFFSET,FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset5853); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeRotAxBeamOffset5857); 

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


 

    public static final BitSet FOLLOW_crystal_in_configfile47 = new BitSet(new long[]{0x0000000000080200L,0x0000000000001000L});
    public static final BitSet FOLLOW_wedge_in_configfile65 = new BitSet(new long[]{0x0000000000080200L,0x0000000000001000L});
    public static final BitSet FOLLOW_beam_in_configfile85 = new BitSet(new long[]{0x0000000000080200L,0x0000000000001000L});
    public static final BitSet FOLLOW_EOF_in_configfile105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CRYSTAL_in_crystal134 = new BitSet(new long[]{0x40F5EB8001B7C470L,0x0000000000002639L});
    public static final BitSet FOLLOW_crystalLine_in_crystal136 = new BitSet(new long[]{0x40F5EB8001B7C472L,0x0000000000002639L});
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
    public static final BitSet FOLLOW_smallMoleAtoms_in_crystalLine479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_crystalType514 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_STRING_in_crystalType518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalDDM560 = new BitSet(new long[]{0x0000006000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_crystalDDMKeyword_in_crystalDDM572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIMPLE_in_crystalDDMKeyword720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LINEAR_in_crystalDDMKeyword727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEAL_in_crystalDDMKeyword734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECAYPARAM_in_crystalDecayParam854 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam858 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam862 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSCOEFCALC_in_crystalCoefcalc938 = new BitSet(new long[]{0xB302000002400100L,0x0000000000000004L});
    public static final BitSet FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DUMMY_in_crystalCoefcalcKeyword1021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDB_in_crystalCoefcalcKeyword1061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXS_in_crystalCoefcalcKeyword1071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SMALLMOLE_in_crystalCoefcalcKeyword1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_crystalDim1472 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1485 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1489 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1505 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEP_in_crystalAngP1598 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngP1602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEL_in_crystalAngL1657 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngL1661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSPERMICRON_in_crystalPPM1715 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalPPM1717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNITCELL_in_unitcell1815 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1819 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1823 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1827 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1842 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1846 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell1850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_proteinConcentration1928 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_proteinConcentration1938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMMONOMERS_in_nummonomers2120 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_nummonomers2124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRESIDUES_in_numresidues2201 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_numresidues2205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRNA_in_numRNA2283 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_numRNA2287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMDNA_in_numDNA2340 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_numDNA2344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2400 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavyProteinAtoms2405 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavyProteinAtoms2409 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_SMALLMOLEATOMS_in_smallMoleAtoms2556 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ELEMENT_in_smallMoleAtoms2561 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_smallMoleAtoms2565 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc2667 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavySolutionConc2672 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavySolutionConc2676 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_SOLVENTFRACTION_in_solventFraction2782 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_solventFraction2786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDBNAME_in_pdb2883 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_STRING_in_pdb2887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WIREFRAMETYPE_in_wireframeType2924 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_STRING_in_wireframeType2928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODELFILE_in_modelFile3016 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_STRING_in_modelFile3020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEESCAPE_in_calculateEscape3087 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_STRING_in_calculateEscape3091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalContainerMaterial3193 = new BitSet(new long[]{0x0000140008000000L});
    public static final BitSet FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial3205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NONE_in_crystalContainerKeyword3398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIXTURE_in_crystalContainerKeyword3407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELEMENTAL_in_crystalContainerKeyword3415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINERTHICKNESS_in_containerThickness3555 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerThickness3559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialMixture3670 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_STRING_in_containerMaterialMixture3680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialElements3905 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ELEMENT_in_containerMaterialElements3916 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerMaterialElements3920 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_CONTAINERDENSITY_in_containerDensity4155 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerDensity4159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sequenceFile4260 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_STRING_in_sequenceFile4270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEAM_in_beam4409 = new BitSet(new long[]{0x0408000E90001800L,0x0000000000000A00L});
    public static final BitSet FOLLOW_beamLine_in_beam4411 = new BitSet(new long[]{0x0408000E90001802L,0x0000000000000A00L});
    public static final BitSet FOLLOW_TYPE_in_beamLine4450 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_STRING_in_beamLine4454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFlux_in_beamLine4472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFWHM_in_beamLine4484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamEnergy_in_beamLine4496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamCollimation_in_beamLine4508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFile_in_beamLine4519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamPixelSize_in_beamLine4540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLUX_in_beamFlux4564 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFlux4568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FWHM_in_beamFWHM4610 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM4614 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM4618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGY_in_beamEnergy4660 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamEnergy4664 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_KEV_in_beamEnergy4671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_beamFile4749 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_STRING_in_beamFile4753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSIZE_in_beamPixelSize4800 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize4804 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize4808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLIMATION_in_beamCollimation4887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RECTANGULAR_in_beamCollimation4893 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4897 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCULAR_in_beamCollimation4908 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HORIZONTAL_in_beamCollimation4916 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERTICAL_in_beamCollimation4927 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation4931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WEDGE_in_wedge5244 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge5248 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge5252 = new BitSet(new long[]{0x0800000040000080L,0x0000000000000140L});
    public static final BitSet FOLLOW_wedgeLine_in_wedge5259 = new BitSet(new long[]{0x0800000040000082L,0x0000000000000140L});
    public static final BitSet FOLLOW_wedgeExposure_in_wedgeLine5303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeAngRes_in_wedgeLine5313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeStartOffset_in_wedgeLine5324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeTranslate_in_wedgeLine5334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine5344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURETIME_in_wedgeExposure5361 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeExposure5365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes5447 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeAngRes5451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTOFFSET_in_wedgeStartOffset5558 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset5562 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset5566 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset5587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate5681 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate5685 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate5689 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate5717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset5853 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeRotAxBeamOffset5857 = new BitSet(new long[]{0x0000000000000002L});

}