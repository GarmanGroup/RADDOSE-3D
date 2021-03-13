// $ANTLR 3.4 /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g 2021-03-13 16:00:33

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABSCOEFCALC", "ANGLEL", "ANGLEP", "ANGULARRESOLUTION", "APERTURERADIUS", "AVERAGE", "BEAM", "CALCSURROUNDING", "CALCULATEFLESCAPE", "CALCULATEPEESCAPE", "CIF", "CIFNAME", "CIRCULAR", "COLLIMATION", "COMMENT", "CONTAINERDENSITY", "CONTAINERMATERIALELEMENTS", "CONTAINERMATERIALMIXTURE", "CONTAINERMATERIALTYPE", "CONTAINERTHICKNESS", "CRYSTAL", "DDM", "DECAYPARAM", "DEFAULT", "DENSITYBASED", "DIFFRACTIONDECAYMODEL", "DIMENSION", "DUMMY", "ELEMENT", "ELEMENTAL", "ENERGY", "ENERGYFWHM", "EXPONENT", "EXPOSURE", "EXPOSURETIME", "FILE", "FLOAT", "FLRESOLUTION", "FLUX", "FWHM", "GONIOMETERAXIS", "HORIZONTAL", "IMAGEDIM", "KEV", "LEAL", "LINEAR", "MATERIALELEMENTS", "MATERIALMIXTURE", "MATERIALTYPE", "MAXRESOLUTION", "MIXTURE", "MODELFILE", "NONE", "NUMCARB", "NUMDNA", "NUMMONOMERS", "NUMRESIDUES", "NUMRNA", "PDB", "PDBNAME", "PERESOLUTION", "PIXELSIZE", "PIXELSPERMICRON", "POLARISATIONDIRECTION", "PROTEINCONC", "PROTEINCONCENTRATION", "PROTEINHEAVYATOMS", "PULSEENERGY", "RDFORTAN", "RDJAVA", "RECTANGULAR", "ROTAXBEAMOFFSET", "RUNS", "SAXS", "SAXSSEQ", "SEMIANGLE", "SEQFILE", "SEQUENCE", "SEQUENCEFILE", "SIMELECTRONS", "SIMPHOTONS", "SIMPLE", "SMALLMOLE", "SMALLMOLEATOMS", "SOLVENTFRACTION", "SOLVENTHEAVYCONC", "STARTOFFSET", "STRING", "SUBPROGRAM", "SURROUNDINGDENSITY", "SURROUNDINGELEMENTS", "SURROUNDINGHEAVYCONC", "SURROUNDINGTHICKNESS", "TRANSLATEPERDEGREE", "TYPE", "UNITCELL", "VERTICAL", "WEDGE", "WIREFRAMETYPE", "WS"
    };

    public static final int EOF=-1;
    public static final int ABSCOEFCALC=4;
    public static final int ANGLEL=5;
    public static final int ANGLEP=6;
    public static final int ANGULARRESOLUTION=7;
    public static final int APERTURERADIUS=8;
    public static final int AVERAGE=9;
    public static final int BEAM=10;
    public static final int CALCSURROUNDING=11;
    public static final int CALCULATEFLESCAPE=12;
    public static final int CALCULATEPEESCAPE=13;
    public static final int CIF=14;
    public static final int CIFNAME=15;
    public static final int CIRCULAR=16;
    public static final int COLLIMATION=17;
    public static final int COMMENT=18;
    public static final int CONTAINERDENSITY=19;
    public static final int CONTAINERMATERIALELEMENTS=20;
    public static final int CONTAINERMATERIALMIXTURE=21;
    public static final int CONTAINERMATERIALTYPE=22;
    public static final int CONTAINERTHICKNESS=23;
    public static final int CRYSTAL=24;
    public static final int DDM=25;
    public static final int DECAYPARAM=26;
    public static final int DEFAULT=27;
    public static final int DENSITYBASED=28;
    public static final int DIFFRACTIONDECAYMODEL=29;
    public static final int DIMENSION=30;
    public static final int DUMMY=31;
    public static final int ELEMENT=32;
    public static final int ELEMENTAL=33;
    public static final int ENERGY=34;
    public static final int ENERGYFWHM=35;
    public static final int EXPONENT=36;
    public static final int EXPOSURE=37;
    public static final int EXPOSURETIME=38;
    public static final int FILE=39;
    public static final int FLOAT=40;
    public static final int FLRESOLUTION=41;
    public static final int FLUX=42;
    public static final int FWHM=43;
    public static final int GONIOMETERAXIS=44;
    public static final int HORIZONTAL=45;
    public static final int IMAGEDIM=46;
    public static final int KEV=47;
    public static final int LEAL=48;
    public static final int LINEAR=49;
    public static final int MATERIALELEMENTS=50;
    public static final int MATERIALMIXTURE=51;
    public static final int MATERIALTYPE=52;
    public static final int MAXRESOLUTION=53;
    public static final int MIXTURE=54;
    public static final int MODELFILE=55;
    public static final int NONE=56;
    public static final int NUMCARB=57;
    public static final int NUMDNA=58;
    public static final int NUMMONOMERS=59;
    public static final int NUMRESIDUES=60;
    public static final int NUMRNA=61;
    public static final int PDB=62;
    public static final int PDBNAME=63;
    public static final int PERESOLUTION=64;
    public static final int PIXELSIZE=65;
    public static final int PIXELSPERMICRON=66;
    public static final int POLARISATIONDIRECTION=67;
    public static final int PROTEINCONC=68;
    public static final int PROTEINCONCENTRATION=69;
    public static final int PROTEINHEAVYATOMS=70;
    public static final int PULSEENERGY=71;
    public static final int RDFORTAN=72;
    public static final int RDJAVA=73;
    public static final int RECTANGULAR=74;
    public static final int ROTAXBEAMOFFSET=75;
    public static final int RUNS=76;
    public static final int SAXS=77;
    public static final int SAXSSEQ=78;
    public static final int SEMIANGLE=79;
    public static final int SEQFILE=80;
    public static final int SEQUENCE=81;
    public static final int SEQUENCEFILE=82;
    public static final int SIMELECTRONS=83;
    public static final int SIMPHOTONS=84;
    public static final int SIMPLE=85;
    public static final int SMALLMOLE=86;
    public static final int SMALLMOLEATOMS=87;
    public static final int SOLVENTFRACTION=88;
    public static final int SOLVENTHEAVYCONC=89;
    public static final int STARTOFFSET=90;
    public static final int STRING=91;
    public static final int SUBPROGRAM=92;
    public static final int SURROUNDINGDENSITY=93;
    public static final int SURROUNDINGELEMENTS=94;
    public static final int SURROUNDINGHEAVYCONC=95;
    public static final int SURROUNDINGTHICKNESS=96;
    public static final int TRANSLATEPERDEGREE=97;
    public static final int TYPE=98;
    public static final int UNITCELL=99;
    public static final int VERTICAL=100;
    public static final int WEDGE=101;
    public static final int WIREFRAMETYPE=102;
    public static final int WS=103;

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
    public String getGrammarFileName() { return "/home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g"; }


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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:41:1: configfile : (a= crystal |b= wedge |c= beam )* EOF ;
    public final void configfile() throws RecognitionException {
        Crystal a =null;

        Wedge b =null;

        Beam c =null;


        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:41:11: ( (a= crystal |b= wedge |c= beam )* EOF )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )* EOF
            {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )*
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
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:42:13: a= crystal
                    {
                    pushFollow(FOLLOW_crystal_in_configfile47);
                    a=crystal();

                    state._fsp--;


                     raddoseInitializer.setCrystal(a); 

                    }
                    break;
                case 2 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:43:13: b= wedge
                    {
                    pushFollow(FOLLOW_wedge_in_configfile65);
                    b=wedge();

                    state._fsp--;


                     raddoseInitializer.exposeWedge(b); 

                    }
                    break;
                case 3 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:44:13: c= beam
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
        String                  cif;
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
        int                     numCarb;
        List<String>    smallMoleAtomNames;
        List<Double>    smallMoleAtomNums;
        List<String>    heavyProteinAtomNames;
        List<Double>    heavyProteinAtomNums;
        List<String>    heavySolutionConcNames;
        List<Double>    heavySolutionConcNums;
        List<String>    cryoSolutionMolecule;
        List<Double>    cryoSolutionConc;
        Double          solFrac;
        String                 oilBased;
        List<String>         oilNames;
        List<Double>          oilNums;
        Double            oilDensity;
        String             calcSurrounding;
        long              simElectrons;
        HashMap<Object, Object> crystalProperties;
    }
    protected Stack crystal_stack = new Stack();



    // $ANTLR start "crystal"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:49:1: crystal returns [Crystal cObj] : CRYSTAL ( crystalLine )+ ;
    public final Crystal crystal() throws RecognitionException {
        crystal_stack.push(new crystal_scope());
        Crystal cObj = null;


         
        ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = 2; // 0 = error, 1 = Simple, 2 = DEFAULT, 3 = RDV2, 4 = PDB, 5 = SAXS
                ((crystal_scope)crystal_stack.peek()).crystalProperties = new HashMap<Object, Object>();

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:247:2: ( CRYSTAL ( crystalLine )+ )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:247:4: CRYSTAL ( crystalLine )+
            {
            match(input,CRYSTAL,FOLLOW_CRYSTAL_in_crystal134); 

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:247:12: ( crystalLine )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= ABSCOEFCALC && LA2_0 <= ANGLEP)||(LA2_0 >= CALCSURROUNDING && LA2_0 <= CALCULATEPEESCAPE)||LA2_0==CIFNAME||(LA2_0 >= CONTAINERDENSITY && LA2_0 <= CONTAINERTHICKNESS)||(LA2_0 >= DDM && LA2_0 <= DECAYPARAM)||(LA2_0 >= DENSITYBASED && LA2_0 <= DIMENSION)||LA2_0==FLRESOLUTION||LA2_0==GONIOMETERAXIS||(LA2_0 >= MATERIALELEMENTS && LA2_0 <= MATERIALTYPE)||LA2_0==MODELFILE||(LA2_0 >= NUMCARB && LA2_0 <= NUMRNA)||(LA2_0 >= PDBNAME && LA2_0 <= PERESOLUTION)||(LA2_0 >= PIXELSPERMICRON && LA2_0 <= PROTEINHEAVYATOMS)||LA2_0==RUNS||LA2_0==SEQFILE||(LA2_0 >= SEQUENCEFILE && LA2_0 <= SIMPHOTONS)||(LA2_0 >= SMALLMOLEATOMS && LA2_0 <= SOLVENTHEAVYCONC)||(LA2_0 >= SUBPROGRAM && LA2_0 <= SURROUNDINGTHICKNESS)||(LA2_0 >= TYPE && LA2_0 <= UNITCELL)||LA2_0==WIREFRAMETYPE) ) {
                    alt2=1;
                }


                switch (alt2) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:247:12: crystalLine
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
               if (((crystal_scope)crystal_stack.peek()).oilDensity == null){
                  ((crystal_scope)crystal_stack.peek()).oilDensity = 0.0 ;
               }
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromParams(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
                                                                ((crystal_scope)crystal_stack.peek()).numMon, ((crystal_scope)crystal_stack.peek()).numRes, ((crystal_scope)crystal_stack.peek()).numRNA, ((crystal_scope)crystal_stack.peek()).numDNA,
                                                                ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
                                                                ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
                                                                ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule, ((crystal_scope)crystal_stack.peek()).cryoSolutionConc,
                                                                ((crystal_scope)crystal_stack.peek()).solFrac, ((crystal_scope)crystal_stack.peek()).oilBased,  ((crystal_scope)crystal_stack.peek()).calcSurrounding,
                                                                ((crystal_scope)crystal_stack.peek()).numCarb, ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums,  ((crystal_scope)crystal_stack.peek()).oilDensity, 
                                                                ((crystal_scope)crystal_stack.peek()).simElectrons);
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
               if (((crystal_scope)crystal_stack.peek()).oilDensity == null){
                  ((crystal_scope)crystal_stack.peek()).oilDensity = 0.0 ;
               }
              if (((crystal_scope)crystal_stack.peek()).heavySolutionConcNames != null)
                ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromPDB(((crystal_scope)crystal_stack.peek()).pdb, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums, ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule, ((crystal_scope)crystal_stack.peek()).cryoSolutionConc, ((crystal_scope)crystal_stack.peek()).oilBased,  ((crystal_scope)crystal_stack.peek()).calcSurrounding, ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums, ((crystal_scope)crystal_stack.peek()).oilDensity, ((crystal_scope)crystal_stack.peek()).simElectrons);
              else
                ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromPDB(((crystal_scope)crystal_stack.peek()).pdb, ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule, ((crystal_scope)crystal_stack.peek()).cryoSolutionConc, ((crystal_scope)crystal_stack.peek()).oilBased,     ((crystal_scope)crystal_stack.peek()).calcSurrounding, ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums, ((crystal_scope)crystal_stack.peek()).oilDensity, ((crystal_scope)crystal_stack.peek()).simElectrons);
                                                                                                                    
            }

            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 5)
            {
               if (((crystal_scope)crystal_stack.peek()).oilDensity == null){
                  ((crystal_scope)crystal_stack.peek()).oilDensity = 0.0 ;
               }
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcSAXS(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
                                                                ((crystal_scope)crystal_stack.peek()).numRes, ((crystal_scope)crystal_stack.peek()).numRNA, ((crystal_scope)crystal_stack.peek()).numDNA,
                                                                ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
                                                                ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
                                                                ((crystal_scope)crystal_stack.peek()).solFrac, ((crystal_scope)crystal_stack.peek()).proteinConc, 
                                                                ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule, ((crystal_scope)crystal_stack.peek()).cryoSolutionConc,
                                                                ((crystal_scope)crystal_stack.peek()).oilBased,     ((crystal_scope)crystal_stack.peek()).calcSurrounding,
                                                                ((crystal_scope)crystal_stack.peek()).numCarb, ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums, ((crystal_scope)crystal_stack.peek()).oilDensity);
            }

            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 6)
            {
               if (((crystal_scope)crystal_stack.peek()).oilDensity == null){
                  ((crystal_scope)crystal_stack.peek()).oilDensity = 0.0 ;
               }
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromSequence(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
                                                                ((crystal_scope)crystal_stack.peek()).numMon,
                                                                ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
                                                                ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
                                                                ((crystal_scope)crystal_stack.peek()).solFrac, ((crystal_scope)crystal_stack.peek()).seqFile,
                                                                ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule, ((crystal_scope)crystal_stack.peek()).cryoSolutionConc,
                                                                ((crystal_scope)crystal_stack.peek()).oilBased,     ((crystal_scope)crystal_stack.peek()).calcSurrounding,
                                                                ((crystal_scope)crystal_stack.peek()).numCarb, ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums, ((crystal_scope)crystal_stack.peek()).oilDensity);
            }

            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 7)
            {
               if (((crystal_scope)crystal_stack.peek()).oilDensity == null){
                  ((crystal_scope)crystal_stack.peek()).oilDensity = 0.0 ;
               }
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromSequenceSAXS(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
                                                                ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
                                                                ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
                                                                ((crystal_scope)crystal_stack.peek()).solFrac, ((crystal_scope)crystal_stack.peek()).proteinConc, ((crystal_scope)crystal_stack.peek()).seqFile,        
                                                                ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule, ((crystal_scope)crystal_stack.peek()).cryoSolutionConc,                 
                                                                ((crystal_scope)crystal_stack.peek()).oilBased,     ((crystal_scope)crystal_stack.peek()).calcSurrounding,
                                                                ((crystal_scope)crystal_stack.peek()).numCarb, ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums, ((crystal_scope)crystal_stack.peek()).oilDensity);
            }

            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 8)
            {
               if (((crystal_scope)crystal_stack.peek()).oilDensity == null){
                  ((crystal_scope)crystal_stack.peek()).oilDensity = 0.0 ;
               }
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcSmallMolecules(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
                                                                ((crystal_scope)crystal_stack.peek()).numMon,
                                                                ((crystal_scope)crystal_stack.peek()).smallMoleAtomNames, ((crystal_scope)crystal_stack.peek()).smallMoleAtomNums,
                                                                ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
                                                                ((crystal_scope)crystal_stack.peek()).solFrac,
                                                                ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule, ((crystal_scope)crystal_stack.peek()).cryoSolutionConc,
                                                                ((crystal_scope)crystal_stack.peek()).oilBased,     ((crystal_scope)crystal_stack.peek()).calcSurrounding,
                                                                ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums, ((crystal_scope)crystal_stack.peek()).oilDensity);
            }

            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 9)
            {
               ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromCIF(((crystal_scope)crystal_stack.peek()).cif);                                                                                                 
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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:250:1: crystalLine : (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |qa= numcarb |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculatePEEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile |ab= smallMoleAtoms |ac= cif |bb= calculateFLEscape |cc= flResolution |dd= peResolution |ee= surroundingHeavyConc |ff= oilBased |gg= goniometerAxis |hh= calcSurrounding |ii= oilElements |jj= oilDensity |kk= program |ll= simElectrons |mm= runs |nn= surroundingThickness |pp= polarisationDirection );
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

        int qa =0;

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

        String ac =null;

        String bb =null;

        int cc =0;

        int dd =0;

        InputfileParser.surroundingHeavyConc_return ee =null;

        String ff =null;

        double gg =0.0;

        String hh =null;

        InputfileParser.oilElements_return ii =null;

        double jj =0.0;

        String kk =null;

        long ll =0;

        int mm =0;

        Map<Object, Object> nn =null;

        double pp =0.0;


        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:251:2: (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |qa= numcarb |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculatePEEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile |ab= smallMoleAtoms |ac= cif |bb= calculateFLEscape |cc= flResolution |dd= peResolution |ee= surroundingHeavyConc |ff= oilBased |gg= goniometerAxis |hh= calcSurrounding |ii= oilElements |jj= oilDensity |kk= program |ll= simElectrons |mm= runs |nn= surroundingThickness |pp= polarisationDirection )
            int alt3=44;
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
            case NUMCARB:
                {
                alt3=18;
                }
                break;
            case PROTEINHEAVYATOMS:
                {
                alt3=19;
                }
                break;
            case SOLVENTHEAVYCONC:
                {
                alt3=20;
                }
                break;
            case SOLVENTFRACTION:
                {
                alt3=21;
                }
                break;
            case PDBNAME:
                {
                alt3=22;
                }
                break;
            case WIREFRAMETYPE:
                {
                alt3=23;
                }
                break;
            case MODELFILE:
                {
                alt3=24;
                }
                break;
            case CALCULATEPEESCAPE:
                {
                alt3=25;
                }
                break;
            case PROTEINCONC:
            case PROTEINCONCENTRATION:
                {
                alt3=26;
                }
                break;
            case CONTAINERMATERIALELEMENTS:
            case MATERIALELEMENTS:
                {
                alt3=27;
                }
                break;
            case SEQFILE:
            case SEQUENCEFILE:
                {
                alt3=28;
                }
                break;
            case SMALLMOLEATOMS:
                {
                alt3=29;
                }
                break;
            case CIFNAME:
                {
                alt3=30;
                }
                break;
            case CALCULATEFLESCAPE:
                {
                alt3=31;
                }
                break;
            case FLRESOLUTION:
                {
                alt3=32;
                }
                break;
            case PERESOLUTION:
                {
                alt3=33;
                }
                break;
            case SURROUNDINGHEAVYCONC:
                {
                alt3=34;
                }
                break;
            case DENSITYBASED:
                {
                alt3=35;
                }
                break;
            case GONIOMETERAXIS:
                {
                alt3=36;
                }
                break;
            case CALCSURROUNDING:
                {
                alt3=37;
                }
                break;
            case SURROUNDINGELEMENTS:
                {
                alt3=38;
                }
                break;
            case SURROUNDINGDENSITY:
                {
                alt3=39;
                }
                break;
            case SUBPROGRAM:
                {
                alt3=40;
                }
                break;
            case SIMELECTRONS:
            case SIMPHOTONS:
                {
                alt3=41;
                }
                break;
            case RUNS:
                {
                alt3=42;
                }
                break;
            case SURROUNDINGTHICKNESS:
                {
                alt3=43;
                }
                break;
            case POLARISATIONDIRECTION:
                {
                alt3=44;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:251:4: a= crystalType
                    {
                    pushFollow(FOLLOW_crystalType_in_crystalLine192);
                    a=crystalType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalType = a; 

                    }
                    break;
                case 2 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:252:4: b= crystalDDM
                    {
                    pushFollow(FOLLOW_crystalDDM_in_crystalLine203);
                    b=crystalDDM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalDdm = b; 

                    }
                    break;
                case 3 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:253:4: c= crystalCoefcalc
                    {
                    pushFollow(FOLLOW_crystalCoefcalc_in_crystalLine215);
                    c=crystalCoefcalc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = c; 

                    }
                    break;
                case 4 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:254:4: d= crystalDim
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
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:257:4: e= crystalPPM
                    {
                    pushFollow(FOLLOW_crystalPPM_in_crystalLine236);
                    e=crystalPPM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, e); 

                    }
                    break;
                case 6 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:258:4: f= crystalAngP
                    {
                    pushFollow(FOLLOW_crystalAngP_in_crystalLine247);
                    f=crystalAngP();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, f); 

                    }
                    break;
                case 7 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:259:4: g= crystalAngL
                    {
                    pushFollow(FOLLOW_crystalAngL_in_crystalLine258);
                    g=crystalAngL();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, g); 

                    }
                    break;
                case 8 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:260:4: h= crystalDecayParam
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
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:263:4: i= containerThickness
                    {
                    pushFollow(FOLLOW_containerThickness_in_crystalLine279);
                    i=containerThickness();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerThickness = i; 

                    }
                    break;
                case 10 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:264:4: j= containerDensity
                    {
                    pushFollow(FOLLOW_containerDensity_in_crystalLine289);
                    j=containerDensity();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerDensity = j; 

                    }
                    break;
                case 11 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:265:4: k= crystalContainerMaterial
                    {
                    pushFollow(FOLLOW_crystalContainerMaterial_in_crystalLine299);
                    k=crystalContainerMaterial();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalContainerMaterial = k; 

                    }
                    break;
                case 12 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:266:4: l= containerMaterialMixture
                    {
                    pushFollow(FOLLOW_containerMaterialMixture_in_crystalLine308);
                    l=containerMaterialMixture();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerMixture = l; 

                    }
                    break;
                case 13 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:267:4: m= unitcell
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
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:273:4: n= nummonomers
                    {
                    pushFollow(FOLLOW_nummonomers_in_crystalLine328);
                    n=nummonomers();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numMon = n;  

                    }
                    break;
                case 15 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:274:4: o= numresidues
                    {
                    pushFollow(FOLLOW_numresidues_in_crystalLine339);
                    o=numresidues();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRes = o;  

                    }
                    break;
                case 16 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:275:4: p= numRNA
                    {
                    pushFollow(FOLLOW_numRNA_in_crystalLine350);
                    p=numRNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRNA = p;  

                    }
                    break;
                case 17 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:276:4: q= numDNA
                    {
                    pushFollow(FOLLOW_numDNA_in_crystalLine363);
                    q=numDNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numDNA = q;  

                    }
                    break;
                case 18 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:277:4: qa= numcarb
                    {
                    pushFollow(FOLLOW_numcarb_in_crystalLine376);
                    qa=numcarb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numCarb = qa;    

                    }
                    break;
                case 19 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:278:4: r= heavyProteinAtoms
                    {
                    pushFollow(FOLLOW_heavyProteinAtoms_in_crystalLine389);
                    r=heavyProteinAtoms();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames = (r!=null?r.names:null);
                                                  ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums = (r!=null?r.num:null);    

                    }
                    break;
                case 20 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:280:4: s= heavySolutionConc
                    {
                    pushFollow(FOLLOW_heavySolutionConc_in_crystalLine398);
                    s=heavySolutionConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames = (s!=null?s.names:null);
                                                  ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums = (s!=null?s.num:null);   

                    }
                    break;
                case 21 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:282:4: t= solventFraction
                    {
                    pushFollow(FOLLOW_solventFraction_in_crystalLine407);
                    t=solventFraction();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).solFrac = t; 

                    }
                    break;
                case 22 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:283:4: u= pdb
                    {
                    pushFollow(FOLLOW_pdb_in_crystalLine417);
                    u=pdb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).pdb = u; 

                    }
                    break;
                case 23 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:284:4: v= wireframeType
                    {
                    pushFollow(FOLLOW_wireframeType_in_crystalLine430);
                    v=wireframeType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_TYPE, v); 

                    }
                    break;
                case 24 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:285:4: w= modelFile
                    {
                    pushFollow(FOLLOW_modelFile_in_crystalLine441);
                    w=modelFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_FILE, w); 

                    }
                    break;
                case 25 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:286:4: x= calculatePEEscape
                    {
                    pushFollow(FOLLOW_calculatePEEscape_in_crystalLine453);
                    x=calculatePEEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ELECTRON_ESCAPE, x); 

                    }
                    break;
                case 26 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:287:4: y= proteinConcentration
                    {
                    pushFollow(FOLLOW_proteinConcentration_in_crystalLine463);
                    y=proteinConcentration();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).proteinConc = y;

                    }
                    break;
                case 27 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:288:4: z= containerMaterialElements
                    {
                    pushFollow(FOLLOW_containerMaterialElements_in_crystalLine472);
                    z=containerMaterialElements();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerElementNames = (z!=null?z.names:null);
                                                  ((crystal_scope)crystal_stack.peek()).containerElementNums = (z!=null?z.num:null);    

                    }
                    break;
                case 28 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:290:4: aa= sequenceFile
                    {
                    pushFollow(FOLLOW_sequenceFile_in_crystalLine481);
                    aa=sequenceFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).seqFile = aa; 

                    }
                    break;
                case 29 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:291:4: ab= smallMoleAtoms
                    {
                    pushFollow(FOLLOW_smallMoleAtoms_in_crystalLine492);
                    ab=smallMoleAtoms();

                    state._fsp--;


                    ((crystal_scope)crystal_stack.peek()).smallMoleAtomNames = (ab!=null?ab.names:null);
                                                ((crystal_scope)crystal_stack.peek()).smallMoleAtomNums = (ab!=null?ab.num:null);   

                    }
                    break;
                case 30 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:293:4: ac= cif
                    {
                    pushFollow(FOLLOW_cif_in_crystalLine513);
                    ac=cif();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).cif = ac; 

                    }
                    break;
                case 31 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:295:4: bb= calculateFLEscape
                    {
                    pushFollow(FOLLOW_calculateFLEscape_in_crystalLine527);
                    bb=calculateFLEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_FLUORESCENT_ESCAPE, bb); 

                    }
                    break;
                case 32 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:296:4: cc= flResolution
                    {
                    pushFollow(FOLLOW_flResolution_in_crystalLine537);
                    cc=flResolution();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_FLUORESCENT_RESOLUTION, cc);

                    }
                    break;
                case 33 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:297:4: dd= peResolution
                    {
                    pushFollow(FOLLOW_peResolution_in_crystalLine548);
                    dd=peResolution();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_PHOTOELECTRON_RESOLUTION, dd);

                    }
                    break;
                case 34 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:299:4: ee= surroundingHeavyConc
                    {
                    pushFollow(FOLLOW_surroundingHeavyConc_in_crystalLine560);
                    ee=surroundingHeavyConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule = (ee!=null?ee.names:null);
                                                  ((crystal_scope)crystal_stack.peek()).cryoSolutionConc = (ee!=null?ee.num:null);  

                    }
                    break;
                case 35 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:301:4: ff= oilBased
                    {
                    pushFollow(FOLLOW_oilBased_in_crystalLine577);
                    ff=oilBased();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).oilBased = ff;  

                    }
                    break;
                case 36 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:302:4: gg= goniometerAxis
                    {
                    pushFollow(FOLLOW_goniometerAxis_in_crystalLine602);
                    gg=goniometerAxis();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_GONIOMETER_AXIS, gg); 

                    }
                    break;
                case 37 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:303:4: hh= calcSurrounding
                    {
                    pushFollow(FOLLOW_calcSurrounding_in_crystalLine612);
                    hh=calcSurrounding();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).calcSurrounding = hh;  

                    }
                    break;
                case 38 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:304:4: ii= oilElements
                    {
                    pushFollow(FOLLOW_oilElements_in_crystalLine636);
                    ii=oilElements();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).oilNames = (ii!=null?ii.names:null);  
                                                         ((crystal_scope)crystal_stack.peek()).oilNums = (ii!=null?ii.num:null);  

                    }
                    break;
                case 39 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:306:4: jj= oilDensity
                    {
                    pushFollow(FOLLOW_oilDensity_in_crystalLine660);
                    jj=oilDensity();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).oilDensity = jj;  

                    }
                    break;
                case 40 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:307:4: kk= program
                    {
                    pushFollow(FOLLOW_program_in_crystalLine685);
                    kk=program();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_PROGRAM, kk); 

                    }
                    break;
                case 41 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:308:4: ll= simElectrons
                    {
                    pushFollow(FOLLOW_simElectrons_in_crystalLine710);
                    ll=simElectrons();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).simElectrons = ll; 

                    }
                    break;
                case 42 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:309:4: mm= runs
                    {
                    pushFollow(FOLLOW_runs_in_crystalLine720);
                    mm=runs();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RUNS, mm); 

                    }
                    break;
                case 43 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:310:4: nn= surroundingThickness
                    {
                    pushFollow(FOLLOW_surroundingThickness_in_crystalLine745);
                    nn=surroundingThickness();

                    state._fsp--;


                     if (nn != null) {
                                                   ((crystal_scope)crystal_stack.peek()).crystalProperties.putAll(nn);
                                                  }; 

                    }
                    break;
                case 44 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:313:11: pp= polarisationDirection
                    {
                    pushFollow(FOLLOW_polarisationDirection_in_crystalLine763);
                    pp=polarisationDirection();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_POLARISATION_DIRECTION, pp); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:318:1: crystalType returns [String crystalType] : TYPE e= STRING ;
    public final String crystalType() throws RecognitionException {
        String crystalType = null;


        Token e=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:319:2: ( TYPE e= STRING )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:319:4: TYPE e= STRING
            {
            match(input,TYPE,FOLLOW_TYPE_in_crystalType796); 

            e=(Token)match(input,STRING,FOLLOW_STRING_in_crystalType800); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:322:1: crystalDDM returns [int value] : ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword ;
    public final int crystalDDM() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:323:2: ( ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:323:4: ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword
            {
            if ( input.LA(1)==DDM||input.LA(1)==DIFFRACTIONDECAYMODEL ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalDDMKeyword_in_crystalDDM854);
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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:326:1: crystalDDMKeyword returns [int value] : ( SIMPLE | LINEAR | LEAL );
    public final int crystalDDMKeyword() throws RecognitionException {
        int value = 0;


        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:327:2: ( SIMPLE | LINEAR | LEAL )
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
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:327:4: SIMPLE
                    {
                    match(input,SIMPLE,FOLLOW_SIMPLE_in_crystalDDMKeyword1002); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:328:4: LINEAR
                    {
                    match(input,LINEAR,FOLLOW_LINEAR_in_crystalDDMKeyword1009); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:329:4: LEAL
                    {
                    match(input,LEAL,FOLLOW_LEAL_in_crystalDDMKeyword1016); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:335:1: crystalDecayParam returns [Double gammaParam, Double b0Param, Double betaParam] : DECAYPARAM a= FLOAT b= FLOAT c= FLOAT ;
    public final InputfileParser.crystalDecayParam_return crystalDecayParam() throws RecognitionException {
        InputfileParser.crystalDecayParam_return retval = new InputfileParser.crystalDecayParam_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:336:2: ( DECAYPARAM a= FLOAT b= FLOAT c= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:336:4: DECAYPARAM a= FLOAT b= FLOAT c= FLOAT
            {
            match(input,DECAYPARAM,FOLLOW_DECAYPARAM_in_crystalDecayParam1136); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam1140); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam1144); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam1148); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:339:1: crystalCoefcalc returns [int value] : ABSCOEFCALC a= crystalCoefcalcKeyword ;
    public final int crystalCoefcalc() throws RecognitionException {
        int value = 0;


        int a =0;


        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:340:2: ( ABSCOEFCALC a= crystalCoefcalcKeyword )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:340:4: ABSCOEFCALC a= crystalCoefcalcKeyword
            {
            match(input,ABSCOEFCALC,FOLLOW_ABSCOEFCALC_in_crystalCoefcalc1220); 

            pushFollow(FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc1224);
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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:342:1: crystalCoefcalcKeyword returns [int value] : ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS | SEQUENCE | SAXSSEQ | SMALLMOLE | CIF );
    public final int crystalCoefcalcKeyword() throws RecognitionException {
        int value = 0;


        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:343:2: ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS | SEQUENCE | SAXSSEQ | SMALLMOLE | CIF )
            int alt5=11;
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
            case CIF:
                {
                alt5=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:343:4: DUMMY
                    {
                    match(input,DUMMY,FOLLOW_DUMMY_in_crystalCoefcalcKeyword1303); 

                     value = 1;

                    }
                    break;
                case 2 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:344:4: AVERAGE
                    {
                    match(input,AVERAGE,FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1313); 

                     value = 1;

                    }
                    break;
                case 3 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:345:4: DEFAULT
                    {
                    match(input,DEFAULT,FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1321); 

                     value = 2;

                    }
                    break;
                case 4 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:346:4: RDJAVA
                    {
                    match(input,RDJAVA,FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1329); 

                     value = 2;

                    }
                    break;
                case 5 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:347:4: RDFORTAN
                    {
                    match(input,RDFORTAN,FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1336); 

                     value = 3;

                    }
                    break;
                case 6 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:348:4: PDB
                    {
                    match(input,PDB,FOLLOW_PDB_in_crystalCoefcalcKeyword1343); 

                     value = 4;

                    }
                    break;
                case 7 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:349:4: SAXS
                    {
                    match(input,SAXS,FOLLOW_SAXS_in_crystalCoefcalcKeyword1353); 

                     value = 5;

                    }
                    break;
                case 8 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:350:4: SEQUENCE
                    {
                    match(input,SEQUENCE,FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1361); 

                     value = 6;

                    }
                    break;
                case 9 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:351:4: SAXSSEQ
                    {
                    match(input,SAXSSEQ,FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1368); 

                     value = 7;

                    }
                    break;
                case 10 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:352:4: SMALLMOLE
                    {
                    match(input,SMALLMOLE,FOLLOW_SMALLMOLE_in_crystalCoefcalcKeyword1375); 

                     value = 8;

                    }
                    break;
                case 11 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:353:4: CIF
                    {
                    match(input,CIF,FOLLOW_CIF_in_crystalCoefcalcKeyword1386); 

                     value = 9;

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:367:1: crystalDim returns [Map<Object, Object> properties] : DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) ;
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
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:370:3: ( DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:370:5: DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
            {
            match(input,DIMENSION,FOLLOW_DIMENSION_in_crystalDim1795); 

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:371:2: (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==FLOAT) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==FLOAT) ) {
                    int LA6_2 = input.LA(3);

                    if ( (LA6_2==FLOAT) ) {
                        alt6=1;
                    }
                    else if ( (LA6_2==EOF||(LA6_2 >= ABSCOEFCALC && LA6_2 <= ANGLEP)||(LA6_2 >= BEAM && LA6_2 <= CALCULATEPEESCAPE)||LA6_2==CIFNAME||(LA6_2 >= CONTAINERDENSITY && LA6_2 <= DECAYPARAM)||(LA6_2 >= DENSITYBASED && LA6_2 <= DIMENSION)||LA6_2==FLRESOLUTION||LA6_2==GONIOMETERAXIS||(LA6_2 >= MATERIALELEMENTS && LA6_2 <= MATERIALTYPE)||LA6_2==MODELFILE||(LA6_2 >= NUMCARB && LA6_2 <= NUMRNA)||(LA6_2 >= PDBNAME && LA6_2 <= PERESOLUTION)||(LA6_2 >= PIXELSPERMICRON && LA6_2 <= PROTEINHEAVYATOMS)||LA6_2==RUNS||LA6_2==SEQFILE||(LA6_2 >= SEQUENCEFILE && LA6_2 <= SIMPHOTONS)||(LA6_2 >= SMALLMOLEATOMS && LA6_2 <= SOLVENTHEAVYCONC)||(LA6_2 >= SUBPROGRAM && LA6_2 <= SURROUNDINGTHICKNESS)||(LA6_2 >= TYPE && LA6_2 <= UNITCELL)||(LA6_2 >= WEDGE && LA6_2 <= WIREFRAMETYPE)) ) {
                        alt6=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA6_1==EOF||(LA6_1 >= ABSCOEFCALC && LA6_1 <= ANGLEP)||(LA6_1 >= BEAM && LA6_1 <= CALCULATEPEESCAPE)||LA6_1==CIFNAME||(LA6_1 >= CONTAINERDENSITY && LA6_1 <= DECAYPARAM)||(LA6_1 >= DENSITYBASED && LA6_1 <= DIMENSION)||LA6_1==FLRESOLUTION||LA6_1==GONIOMETERAXIS||(LA6_1 >= MATERIALELEMENTS && LA6_1 <= MATERIALTYPE)||LA6_1==MODELFILE||(LA6_1 >= NUMCARB && LA6_1 <= NUMRNA)||(LA6_1 >= PDBNAME && LA6_1 <= PERESOLUTION)||(LA6_1 >= PIXELSPERMICRON && LA6_1 <= PROTEINHEAVYATOMS)||LA6_1==RUNS||LA6_1==SEQFILE||(LA6_1 >= SEQUENCEFILE && LA6_1 <= SIMPHOTONS)||(LA6_1 >= SMALLMOLEATOMS && LA6_1 <= SOLVENTHEAVYCONC)||(LA6_1 >= SUBPROGRAM && LA6_1 <= SURROUNDINGTHICKNESS)||(LA6_1 >= TYPE && LA6_1 <= UNITCELL)||(LA6_1 >= WEDGE && LA6_1 <= WIREFRAMETYPE)) ) {
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
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:372:7: a= FLOAT b= FLOAT c= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1808); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1812); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1816); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((a!=null?a.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((b!=null?b.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble((c!=null?c.getText():null))); 

                    }
                    break;
                case 2 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:375:7: e= FLOAT f= FLOAT
                    {
                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1828); 

                    f=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1832); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((e!=null?e.getText():null)));
                                       properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((f!=null?f.getText():null))); 

                    }
                    break;
                case 3 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:377:7: d= FLOAT
                    {
                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1844); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:381:1: crystalAngP returns [double value] : ANGLEP a= FLOAT ;
    public final double crystalAngP() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:382:2: ( ANGLEP a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:382:4: ANGLEP a= FLOAT
            {
            match(input,ANGLEP,FOLLOW_ANGLEP_in_crystalAngP1921); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngP1925); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:386:1: crystalAngL returns [double value] : ANGLEL a= FLOAT ;
    public final double crystalAngL() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:387:2: ( ANGLEL a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:387:4: ANGLEL a= FLOAT
            {
            match(input,ANGLEL,FOLLOW_ANGLEL_in_crystalAngL1980); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngL1984); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:391:1: crystalPPM returns [double ppm] : PIXELSPERMICRON FLOAT ;
    public final double crystalPPM() throws RecognitionException {
        double ppm = 0.0;


        Token FLOAT1=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:392:2: ( PIXELSPERMICRON FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:392:4: PIXELSPERMICRON FLOAT
            {
            match(input,PIXELSPERMICRON,FOLLOW_PIXELSPERMICRON_in_crystalPPM2038); 

            FLOAT1=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalPPM2040); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:395:1: unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC] : UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? ;
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
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:396:2: ( UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:396:4: UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )?
            {
            match(input,UNITCELL,FOLLOW_UNITCELL_in_unitcell2138); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2142); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2146); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2150); 

            retval.dimA = Double.parseDouble((a!=null?a.getText():null));
                     retval.dimB = Double.parseDouble((b!=null?b.getText():null));
                     retval.dimC = Double.parseDouble((c!=null?c.getText():null)); 

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:400:7: (al= FLOAT be= FLOAT ga= FLOAT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FLOAT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:400:8: al= FLOAT be= FLOAT ga= FLOAT
                    {
                    al=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2165); 

                    be=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2169); 

                    ga=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2173); 

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



    // $ANTLR start "surroundingThickness"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:409:1: surroundingThickness returns [Map<Object, Object> properties] : SURROUNDINGTHICKNESS (a= FLOAT b= FLOAT c= FLOAT ) ;
    public final Map<Object, Object> surroundingThickness() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token c=null;

         
                properties = new HashMap<Object, Object>();

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:412:3: ( SURROUNDINGTHICKNESS (a= FLOAT b= FLOAT c= FLOAT ) )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:412:5: SURROUNDINGTHICKNESS (a= FLOAT b= FLOAT c= FLOAT )
            {
            match(input,SURROUNDINGTHICKNESS,FOLLOW_SURROUNDINGTHICKNESS_in_surroundingThickness2255); 

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:413:2: (a= FLOAT b= FLOAT c= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:414:2: a= FLOAT b= FLOAT c= FLOAT
            {
            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_surroundingThickness2265); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_surroundingThickness2269); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_surroundingThickness2273); 

             properties.put(Crystal.SURROUNDING_X, Double.parseDouble((a!=null?a.getText():null)));
                                            properties.put(Crystal.SURROUNDING_Y, Double.parseDouble((b!=null?b.getText():null)));
                                            properties.put(Crystal.SURROUNDING_Z, Double.parseDouble((c!=null?c.getText():null))); 

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
    // $ANTLR end "surroundingThickness"



    // $ANTLR start "proteinConcentration"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:421:1: proteinConcentration returns [Double proteinConc] : ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT ;
    public final Double proteinConcentration() throws RecognitionException {
        Double proteinConc = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:422:2: ( ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:422:4: ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT
            {
            if ( (input.LA(1) >= PROTEINCONC && input.LA(1) <= PROTEINCONCENTRATION) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_proteinConcentration2409); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:426:1: nummonomers returns [int value] : NUMMONOMERS a= FLOAT ;
    public final int nummonomers() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:427:2: ( NUMMONOMERS a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:427:4: NUMMONOMERS a= FLOAT
            {
            match(input,NUMMONOMERS,FOLLOW_NUMMONOMERS_in_nummonomers2591); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_nummonomers2595); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:430:1: numresidues returns [int value] : NUMRESIDUES a= FLOAT ;
    public final int numresidues() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:431:2: ( NUMRESIDUES a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:431:4: NUMRESIDUES a= FLOAT
            {
            match(input,NUMRESIDUES,FOLLOW_NUMRESIDUES_in_numresidues2672); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numresidues2676); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:434:1: numRNA returns [int value] : NUMRNA a= FLOAT ;
    public final int numRNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:435:2: ( NUMRNA a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:435:4: NUMRNA a= FLOAT
            {
            match(input,NUMRNA,FOLLOW_NUMRNA_in_numRNA2754); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numRNA2758); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:438:1: numDNA returns [int value] : NUMDNA a= FLOAT ;
    public final int numDNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:439:2: ( NUMDNA a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:439:4: NUMDNA a= FLOAT
            {
            match(input,NUMDNA,FOLLOW_NUMDNA_in_numDNA2811); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numDNA2815); 

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



    // $ANTLR start "numcarb"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:442:1: numcarb returns [int value] : NUMCARB a= FLOAT ;
    public final int numcarb() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:443:2: ( NUMCARB a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:443:4: NUMCARB a= FLOAT
            {
            match(input,NUMCARB,FOLLOW_NUMCARB_in_numcarb2867); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numcarb2871); 

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
    // $ANTLR end "numcarb"


    public static class heavyProteinAtoms_return extends ParserRuleReturnScope {
        public List<String> names;
        public List<Double> num;;
    };


    // $ANTLR start "heavyProteinAtoms"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:446:1: heavyProteinAtoms returns [List<String> names, List<Double> num;] : PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavyProteinAtoms_return heavyProteinAtoms() throws RecognitionException {
        InputfileParser.heavyProteinAtoms_return retval = new InputfileParser.heavyProteinAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:451:2: ( PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:451:4: PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,PROTEINHEAVYATOMS,FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2932); 

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:451:22: (a= ELEMENT b= FLOAT )+
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
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:451:23: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavyProteinAtoms2937); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavyProteinAtoms2941); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:455:1: smallMoleAtoms returns [List<String> names, List<Double> num;] : SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.smallMoleAtoms_return smallMoleAtoms() throws RecognitionException {
        InputfileParser.smallMoleAtoms_return retval = new InputfileParser.smallMoleAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:460:2: ( SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+ )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:460:4: SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,SMALLMOLEATOMS,FOLLOW_SMALLMOLEATOMS_in_smallMoleAtoms3088); 

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:460:19: (a= ELEMENT b= FLOAT )+
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
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:460:20: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_smallMoleAtoms3093); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_smallMoleAtoms3097); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:463:1: heavySolutionConc returns [List<String> names, List<Double> num;] : SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavySolutionConc_return heavySolutionConc() throws RecognitionException {
        InputfileParser.heavySolutionConc_return retval = new InputfileParser.heavySolutionConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:468:2: ( SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:468:4: SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SOLVENTHEAVYCONC,FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc3199); 

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:468:21: (a= ELEMENT b= FLOAT )+
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
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:468:22: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavySolutionConc3204); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavySolutionConc3208); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:471:1: solventFraction returns [double solFrac] : SOLVENTFRACTION a= FLOAT ;
    public final double solventFraction() throws RecognitionException {
        double solFrac = 0.0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:472:2: ( SOLVENTFRACTION a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:472:4: SOLVENTFRACTION a= FLOAT
            {
            match(input,SOLVENTFRACTION,FOLLOW_SOLVENTFRACTION_in_solventFraction3314); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_solventFraction3318); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:475:1: pdb returns [String pdb] : PDBNAME (a= STRING |a= FLOAT ) ;
    public final String pdb() throws RecognitionException {
        String pdb = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:476:2: ( PDBNAME (a= STRING |a= FLOAT ) )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:476:4: PDBNAME (a= STRING |a= FLOAT )
            {
            match(input,PDBNAME,FOLLOW_PDBNAME_in_pdb3415); 

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:476:12: (a= STRING |a= FLOAT )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==STRING) ) {
                alt11=1;
            }
            else if ( (LA11_0==FLOAT) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }
            switch (alt11) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:476:13: a= STRING
                    {
                    a=(Token)match(input,STRING,FOLLOW_STRING_in_pdb3420); 

                    }
                    break;
                case 2 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:476:22: a= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_pdb3424); 

                    }
                    break;

            }


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



    // $ANTLR start "cif"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:479:1: cif returns [String cif] : CIFNAME a= STRING ;
    public final String cif() throws RecognitionException {
        String cif = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:480:2: ( CIFNAME a= STRING )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:480:4: CIFNAME a= STRING
            {
            match(input,CIFNAME,FOLLOW_CIFNAME_in_cif3462); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_cif3466); 

            cif = (a!=null?a.getText():null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return cif;
    }
    // $ANTLR end "cif"



    // $ANTLR start "wireframeType"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:483:1: wireframeType returns [String value] : WIREFRAMETYPE a= STRING ;
    public final String wireframeType() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:484:2: ( WIREFRAMETYPE a= STRING )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:484:4: WIREFRAMETYPE a= STRING
            {
            match(input,WIREFRAMETYPE,FOLLOW_WIREFRAMETYPE_in_wireframeType3503); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_wireframeType3507); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:487:1: modelFile returns [String value] : MODELFILE a= STRING ;
    public final String modelFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:488:2: ( MODELFILE a= STRING )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:488:4: MODELFILE a= STRING
            {
            match(input,MODELFILE,FOLLOW_MODELFILE_in_modelFile3595); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_modelFile3599); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:491:1: calculatePEEscape returns [String value] : CALCULATEPEESCAPE a= STRING ;
    public final String calculatePEEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:492:2: ( CALCULATEPEESCAPE a= STRING )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:492:4: CALCULATEPEESCAPE a= STRING
            {
            match(input,CALCULATEPEESCAPE,FOLLOW_CALCULATEPEESCAPE_in_calculatePEEscape3666); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculatePEEscape3670); 

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



    // $ANTLR start "goniometerAxis"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:496:1: goniometerAxis returns [double value] : GONIOMETERAXIS a= FLOAT ;
    public final double goniometerAxis() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:497:2: ( GONIOMETERAXIS a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:497:4: GONIOMETERAXIS a= FLOAT
            {
            match(input,GONIOMETERAXIS,FOLLOW_GONIOMETERAXIS_in_goniometerAxis3782); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_goniometerAxis3786); 

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
    // $ANTLR end "goniometerAxis"



    // $ANTLR start "polarisationDirection"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:501:1: polarisationDirection returns [double value] : POLARISATIONDIRECTION a= FLOAT ;
    public final double polarisationDirection() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:502:2: ( POLARISATIONDIRECTION a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:502:4: POLARISATIONDIRECTION a= FLOAT
            {
            match(input,POLARISATIONDIRECTION,FOLLOW_POLARISATIONDIRECTION_in_polarisationDirection3883); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_polarisationDirection3887); 

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
    // $ANTLR end "polarisationDirection"



    // $ANTLR start "crystalContainerMaterial"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:506:1: crystalContainerMaterial returns [int value] : ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword ;
    public final int crystalContainerMaterial() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:507:2: ( ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:507:4: ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword
            {
            if ( input.LA(1)==CONTAINERMATERIALTYPE||input.LA(1)==MATERIALTYPE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial4031);
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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:510:1: crystalContainerKeyword returns [int value] : ( NONE | MIXTURE | ELEMENTAL );
    public final int crystalContainerKeyword() throws RecognitionException {
        int value = 0;


        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:511:2: ( NONE | MIXTURE | ELEMENTAL )
            int alt12=3;
            switch ( input.LA(1) ) {
            case NONE:
                {
                alt12=1;
                }
                break;
            case MIXTURE:
                {
                alt12=2;
                }
                break;
            case ELEMENTAL:
                {
                alt12=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }

            switch (alt12) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:511:4: NONE
                    {
                    match(input,NONE,FOLLOW_NONE_in_crystalContainerKeyword4224); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:512:4: MIXTURE
                    {
                    match(input,MIXTURE,FOLLOW_MIXTURE_in_crystalContainerKeyword4233); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:513:4: ELEMENTAL
                    {
                    match(input,ELEMENTAL,FOLLOW_ELEMENTAL_in_crystalContainerKeyword4241); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:519:1: containerThickness returns [double value] : CONTAINERTHICKNESS a= FLOAT ;
    public final double containerThickness() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:520:2: ( CONTAINERTHICKNESS a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:520:4: CONTAINERTHICKNESS a= FLOAT
            {
            match(input,CONTAINERTHICKNESS,FOLLOW_CONTAINERTHICKNESS_in_containerThickness4381); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerThickness4385); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:523:1: containerMaterialMixture returns [String value] : ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING ;
    public final String containerMaterialMixture() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:524:2: ( ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:524:4: ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING
            {
            if ( input.LA(1)==CONTAINERMATERIALMIXTURE||input.LA(1)==MATERIALMIXTURE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_containerMaterialMixture4506); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:528:1: containerMaterialElements returns [List<String> names, List<Double> num;] : ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.containerMaterialElements_return containerMaterialElements() throws RecognitionException {
        InputfileParser.containerMaterialElements_return retval = new InputfileParser.containerMaterialElements_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:533:2: ( ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:533:4: ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+
            {
            if ( input.LA(1)==CONTAINERMATERIALELEMENTS||input.LA(1)==MATERIALELEMENTS ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:533:51: (a= ELEMENT b= FLOAT )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==ELEMENT) ) {
                    alt13=1;
                }


                switch (alt13) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:533:52: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_containerMaterialElements4742); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerMaterialElements4746); 

                    retval.names.add((a!=null?a.getText():null)); retval.num.add(Double.parseDouble((b!=null?b.getText():null))); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:537:1: containerDensity returns [double value] : CONTAINERDENSITY a= FLOAT ;
    public final double containerDensity() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:538:2: ( CONTAINERDENSITY a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:538:4: CONTAINERDENSITY a= FLOAT
            {
            match(input,CONTAINERDENSITY,FOLLOW_CONTAINERDENSITY_in_containerDensity4981); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerDensity4985); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:541:1: sequenceFile returns [String value] : ( SEQUENCEFILE | SEQFILE ) a= STRING ;
    public final String sequenceFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:542:2: ( ( SEQUENCEFILE | SEQFILE ) a= STRING )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:542:4: ( SEQUENCEFILE | SEQFILE ) a= STRING
            {
            if ( input.LA(1)==SEQFILE||input.LA(1)==SEQUENCEFILE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_sequenceFile5096); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:546:1: calculateFLEscape returns [String value] : CALCULATEFLESCAPE a= STRING ;
    public final String calculateFLEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:547:2: ( CALCULATEFLESCAPE a= STRING )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:547:4: CALCULATEFLESCAPE a= STRING
            {
            match(input,CALCULATEFLESCAPE,FOLLOW_CALCULATEFLESCAPE_in_calculateFLEscape5219); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculateFLEscape5223); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:551:1: flResolution returns [int value] : FLRESOLUTION a= FLOAT ;
    public final int flResolution() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:552:2: ( FLRESOLUTION a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:552:4: FLRESOLUTION a= FLOAT
            {
            match(input,FLRESOLUTION,FOLLOW_FLRESOLUTION_in_flResolution5334); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_flResolution5338); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:555:1: peResolution returns [int value] : PERESOLUTION a= FLOAT ;
    public final int peResolution() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:556:2: ( PERESOLUTION a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:556:4: PERESOLUTION a= FLOAT
            {
            match(input,PERESOLUTION,FOLLOW_PERESOLUTION_in_peResolution5420); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_peResolution5424); 

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


    public static class surroundingHeavyConc_return extends ParserRuleReturnScope {
        public List<String> names;
        public List<Double> num;;
    };


    // $ANTLR start "surroundingHeavyConc"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:559:1: surroundingHeavyConc returns [List<String> names, List<Double> num;] : SURROUNDINGHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.surroundingHeavyConc_return surroundingHeavyConc() throws RecognitionException {
        InputfileParser.surroundingHeavyConc_return retval = new InputfileParser.surroundingHeavyConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:564:2: ( SURROUNDINGHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:564:4: SURROUNDINGHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SURROUNDINGHEAVYCONC,FOLLOW_SURROUNDINGHEAVYCONC_in_surroundingHeavyConc5510); 

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:564:25: (a= ELEMENT b= FLOAT )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==ELEMENT) ) {
                    alt14=1;
                }


                switch (alt14) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:564:26: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_surroundingHeavyConc5515); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_surroundingHeavyConc5519); 

                    retval.names.add((a!=null?a.getText():null)); retval.num.add(Double.parseDouble((b!=null?b.getText():null))); 

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
    // $ANTLR end "surroundingHeavyConc"



    // $ANTLR start "oilBased"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:567:1: oilBased returns [String value] : DENSITYBASED a= STRING ;
    public final String oilBased() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:568:2: ( DENSITYBASED a= STRING )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:568:4: DENSITYBASED a= STRING
            {
            match(input,DENSITYBASED,FOLLOW_DENSITYBASED_in_oilBased5647); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_oilBased5651); 

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
    // $ANTLR end "oilBased"



    // $ANTLR start "calcSurrounding"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:571:1: calcSurrounding returns [String value] : CALCSURROUNDING a= STRING ;
    public final String calcSurrounding() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:572:2: ( CALCSURROUNDING a= STRING )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:572:4: CALCSURROUNDING a= STRING
            {
            match(input,CALCSURROUNDING,FOLLOW_CALCSURROUNDING_in_calcSurrounding5733); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calcSurrounding5737); 

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
    // $ANTLR end "calcSurrounding"


    public static class oilElements_return extends ParserRuleReturnScope {
        public List<String> names;
        public List<Double> num;;
    };


    // $ANTLR start "oilElements"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:576:1: oilElements returns [List<String> names, List<Double> num;] : SURROUNDINGELEMENTS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.oilElements_return oilElements() throws RecognitionException {
        InputfileParser.oilElements_return retval = new InputfileParser.oilElements_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:581:2: ( SURROUNDINGELEMENTS (a= ELEMENT b= FLOAT )+ )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:581:4: SURROUNDINGELEMENTS (a= ELEMENT b= FLOAT )+
            {
            match(input,SURROUNDINGELEMENTS,FOLLOW_SURROUNDINGELEMENTS_in_oilElements5840); 

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:581:24: (a= ELEMENT b= FLOAT )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==ELEMENT) ) {
                    alt15=1;
                }


                switch (alt15) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:581:25: a= ELEMENT b= FLOAT
                    {
                    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_oilElements5845); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_oilElements5849); 

                    retval.names.add((a!=null?a.getText():null)); retval.num.add(Double.parseDouble((b!=null?b.getText():null))); 

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
    // $ANTLR end "oilElements"



    // $ANTLR start "oilDensity"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:584:1: oilDensity returns [double oildens] : SURROUNDINGDENSITY a= FLOAT ;
    public final double oilDensity() throws RecognitionException {
        double oildens = 0.0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:585:2: ( SURROUNDINGDENSITY a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:585:4: SURROUNDINGDENSITY a= FLOAT
            {
            match(input,SURROUNDINGDENSITY,FOLLOW_SURROUNDINGDENSITY_in_oilDensity5972); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_oilDensity5976); 

            oildens = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return oildens;
    }
    // $ANTLR end "oilDensity"



    // $ANTLR start "simElectrons"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:588:1: simElectrons returns [long simel] : ( SIMELECTRONS | SIMPHOTONS ) a= FLOAT ;
    public final long simElectrons() throws RecognitionException {
        long simel = 0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:589:2: ( ( SIMELECTRONS | SIMPHOTONS ) a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:589:4: ( SIMELECTRONS | SIMPHOTONS ) a= FLOAT
            {
            if ( (input.LA(1) >= SIMELECTRONS && input.LA(1) <= SIMPHOTONS) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_simElectrons6098); 

            simel = Long.parseLong((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return simel;
    }
    // $ANTLR end "simElectrons"



    // $ANTLR start "program"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:593:1: program returns [String value] : SUBPROGRAM a= STRING ;
    public final String program() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:594:2: ( SUBPROGRAM a= STRING )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:594:4: SUBPROGRAM a= STRING
            {
            match(input,SUBPROGRAM,FOLLOW_SUBPROGRAM_in_program6237); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_program6241); 

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
    // $ANTLR end "program"



    // $ANTLR start "runs"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:598:1: runs returns [int value] : RUNS a= FLOAT ;
    public final int runs() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:599:2: ( RUNS a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:599:4: RUNS a= FLOAT
            {
            match(input,RUNS,FOLLOW_RUNS_in_runs6317); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_runs6321); 

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
    // $ANTLR end "runs"


    protected static class beam_scope {
        String beamType;
        HashMap<Object, Object> beamProperties;
    }
    protected Stack beam_stack = new Stack();



    // $ANTLR start "beam"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:605:1: beam returns [Beam bObj] : BEAM ( beamLine )+ ;
    public final Beam beam() throws RecognitionException {
        beam_stack.push(new beam_scope());
        Beam bObj = null;


         
                ((beam_scope)beam_stack.peek()).beamProperties = new HashMap<Object, Object>();

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:616:2: ( BEAM ( beamLine )+ )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:616:4: BEAM ( beamLine )+
            {
            match(input,BEAM,FOLLOW_BEAM_in_beam6384); 

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:616:9: ( beamLine )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==APERTURERADIUS||(LA16_0 >= CIRCULAR && LA16_0 <= COLLIMATION)||(LA16_0 >= ENERGY && LA16_0 <= ENERGYFWHM)||LA16_0==EXPOSURE||LA16_0==FILE||(LA16_0 >= FLUX && LA16_0 <= FWHM)||(LA16_0 >= HORIZONTAL && LA16_0 <= IMAGEDIM)||LA16_0==PIXELSIZE||LA16_0==PULSEENERGY||LA16_0==RECTANGULAR||LA16_0==SEMIANGLE||LA16_0==TYPE||LA16_0==VERTICAL) ) {
                    alt16=1;
                }


                switch (alt16) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:616:9: beamLine
                    {
                    pushFollow(FOLLOW_beamLine_in_beam6386);
                    beamLine();

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:619:1: beamLine : ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize |h= beamExposure |i= beamSemiAngle |j= beamApertureRadius |k= imageDimensions |l= pulseEnergy |m= energyFWHM );
    public final void beamLine() throws RecognitionException {
        Token a=null;
        Double b =null;

        InputfileParser.beamFWHM_return c =null;

        Double d =null;

        Map<Object, Object> e =null;

        String f =null;

        Map<Object, Object> g =null;

        Double h =null;

        Double i =null;

        Double j =null;

        InputfileParser.imageDimensions_return k =null;

        Double l =null;

        Double m =null;


        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:620:2: ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize |h= beamExposure |i= beamSemiAngle |j= beamApertureRadius |k= imageDimensions |l= pulseEnergy |m= energyFWHM )
            int alt17=13;
            switch ( input.LA(1) ) {
            case TYPE:
                {
                alt17=1;
                }
                break;
            case FLUX:
                {
                alt17=2;
                }
                break;
            case FWHM:
                {
                alt17=3;
                }
                break;
            case ENERGY:
                {
                alt17=4;
                }
                break;
            case CIRCULAR:
            case COLLIMATION:
            case HORIZONTAL:
            case RECTANGULAR:
            case VERTICAL:
                {
                alt17=5;
                }
                break;
            case FILE:
                {
                alt17=6;
                }
                break;
            case PIXELSIZE:
                {
                alt17=7;
                }
                break;
            case EXPOSURE:
                {
                alt17=8;
                }
                break;
            case SEMIANGLE:
                {
                alt17=9;
                }
                break;
            case APERTURERADIUS:
                {
                alt17=10;
                }
                break;
            case IMAGEDIM:
                {
                alt17=11;
                }
                break;
            case PULSEENERGY:
                {
                alt17=12;
                }
                break;
            case ENERGYFWHM:
                {
                alt17=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }

            switch (alt17) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:620:4: TYPE a= STRING
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_beamLine6425); 

                    a=(Token)match(input,STRING,FOLLOW_STRING_in_beamLine6429); 

                     ((beam_scope)beam_stack.peek()).beamType = (a!=null?a.getText():null); 

                    }
                    break;
                case 2 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:621:4: b= beamFlux
                    {
                    pushFollow(FOLLOW_beamFlux_in_beamLine6447);
                    b=beamFlux();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FLUX, b); 

                    }
                    break;
                case 3 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:622:4: c= beamFWHM
                    {
                    pushFollow(FOLLOW_beamFWHM_in_beamLine6459);
                    c=beamFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_X, (c!=null?c.x:null)); 
                                                   ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_Y, (c!=null?c.y:null)); 

                    }
                    break;
                case 4 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:624:4: d= beamEnergy
                    {
                    pushFollow(FOLLOW_beamEnergy_in_beamLine6471);
                    d=beamEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_ENERGY, d); 

                    }
                    break;
                case 5 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:625:4: e= beamCollimation
                    {
                    pushFollow(FOLLOW_beamCollimation_in_beamLine6483);
                    e=beamCollimation();

                    state._fsp--;


                     if (e != null) {
                                                    ((beam_scope)beam_stack.peek()).beamProperties.putAll(e);
                                                   } 

                    }
                    break;
                case 6 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:628:4: f= beamFile
                    {
                    pushFollow(FOLLOW_beamFile_in_beamLine6494);
                    f=beamFile();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXTFILE, f); 

                    }
                    break;
                case 7 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:629:4: g= beamPixelSize
                    {
                    pushFollow(FOLLOW_beamPixelSize_in_beamLine6515);
                    g=beamPixelSize();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.putAll(g); 

                    }
                    break;
                case 8 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:631:4: h= beamExposure
                    {
                    pushFollow(FOLLOW_beamExposure_in_beamLine6532);
                    h=beamExposure();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXPOSURE, h); 

                    }
                    break;
                case 9 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:632:4: i= beamSemiAngle
                    {
                    pushFollow(FOLLOW_beamSemiAngle_in_beamLine6544);
                    i=beamSemiAngle();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_SEMIANGLE, i);

                    }
                    break;
                case 10 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:633:4: j= beamApertureRadius
                    {
                    pushFollow(FOLLOW_beamApertureRadius_in_beamLine6553);
                    j=beamApertureRadius();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_APERTURERADIUS, j);

                    }
                    break;
                case 11 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:634:4: k= imageDimensions
                    {
                    pushFollow(FOLLOW_imageDimensions_in_beamLine6562);
                    k=imageDimensions();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.IMAGE_X, (k!=null?k.xImage:null)); 
                                                   ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.IMAGE_Y, (k!=null?k.yImage:null)); 

                    }
                    break;
                case 12 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:636:4: l= pulseEnergy
                    {
                    pushFollow(FOLLOW_pulseEnergy_in_beamLine6572);
                    l=pulseEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.PULSE_ENERGY, l); 

                    }
                    break;
                case 13 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:637:4: m= energyFWHM
                    {
                    pushFollow(FOLLOW_energyFWHM_in_beamLine6584);
                    m=energyFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.ENERGY_FWHM, m); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:641:1: beamFlux returns [Double flux] : FLUX a= FLOAT ;
    public final Double beamFlux() throws RecognitionException {
        Double flux = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:642:2: ( FLUX a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:642:4: FLUX a= FLOAT
            {
            match(input,FLUX,FOLLOW_FLUX_in_beamFlux6614); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFlux6618); 

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



    // $ANTLR start "beamExposure"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:645:1: beamExposure returns [Double exposure] : EXPOSURE a= FLOAT ;
    public final Double beamExposure() throws RecognitionException {
        Double exposure = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:646:2: ( EXPOSURE a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:646:4: EXPOSURE a= FLOAT
            {
            match(input,EXPOSURE,FOLLOW_EXPOSURE_in_beamExposure6660); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamExposure6664); 

            exposure = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return exposure;
    }
    // $ANTLR end "beamExposure"


    public static class imageDimensions_return extends ParserRuleReturnScope {
        public Double xImage;
        public Double yImage;
    };


    // $ANTLR start "imageDimensions"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:649:1: imageDimensions returns [Double xImage, Double yImage] : IMAGEDIM a= FLOAT b= FLOAT ;
    public final InputfileParser.imageDimensions_return imageDimensions() throws RecognitionException {
        InputfileParser.imageDimensions_return retval = new InputfileParser.imageDimensions_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:650:2: ( IMAGEDIM a= FLOAT b= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:650:4: IMAGEDIM a= FLOAT b= FLOAT
            {
            match(input,IMAGEDIM,FOLLOW_IMAGEDIM_in_imageDimensions6726); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_imageDimensions6730); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_imageDimensions6734); 

            retval.xImage = Double.parseDouble((a!=null?a.getText():null)); retval.yImage = Double.parseDouble((b!=null?b.getText():null));

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
    // $ANTLR end "imageDimensions"


    public static class beamFWHM_return extends ParserRuleReturnScope {
        public Double x;
        public Double y;
    };


    // $ANTLR start "beamFWHM"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:654:1: beamFWHM returns [Double x, Double y] : FWHM a= FLOAT b= FLOAT ;
    public final InputfileParser.beamFWHM_return beamFWHM() throws RecognitionException {
        InputfileParser.beamFWHM_return retval = new InputfileParser.beamFWHM_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:655:2: ( FWHM a= FLOAT b= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:655:4: FWHM a= FLOAT b= FLOAT
            {
            match(input,FWHM,FOLLOW_FWHM_in_beamFWHM6799); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM6803); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM6807); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:658:1: beamEnergy returns [Double energy] : ENERGY a= FLOAT ( KEV )? ;
    public final Double beamEnergy() throws RecognitionException {
        Double energy = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:659:2: ( ENERGY a= FLOAT ( KEV )? )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:659:4: ENERGY a= FLOAT ( KEV )?
            {
            match(input,ENERGY,FOLLOW_ENERGY_in_beamEnergy6849); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamEnergy6853); 

            energy = Double.parseDouble((a!=null?a.getText():null));

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:660:2: ( KEV )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==KEV) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:660:4: KEV
                    {
                    match(input,KEV,FOLLOW_KEV_in_beamEnergy6860); 

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



    // $ANTLR start "pulseEnergy"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:665:1: pulseEnergy returns [Double pulse] : PULSEENERGY a= FLOAT ;
    public final Double pulseEnergy() throws RecognitionException {
        Double pulse = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:666:2: ( PULSEENERGY a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:666:4: PULSEENERGY a= FLOAT
            {
            match(input,PULSEENERGY,FOLLOW_PULSEENERGY_in_pulseEnergy6938); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_pulseEnergy6942); 

            pulse = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return pulse;
    }
    // $ANTLR end "pulseEnergy"



    // $ANTLR start "beamSemiAngle"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:670:1: beamSemiAngle returns [Double semiAngle] : SEMIANGLE a= FLOAT ;
    public final Double beamSemiAngle() throws RecognitionException {
        Double semiAngle = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:671:2: ( SEMIANGLE a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:671:4: SEMIANGLE a= FLOAT
            {
            match(input,SEMIANGLE,FOLLOW_SEMIANGLE_in_beamSemiAngle7020); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamSemiAngle7024); 

            semiAngle = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return semiAngle;
    }
    // $ANTLR end "beamSemiAngle"



    // $ANTLR start "beamApertureRadius"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:675:1: beamApertureRadius returns [Double apertureRadius] : APERTURERADIUS a= FLOAT ;
    public final Double beamApertureRadius() throws RecognitionException {
        Double apertureRadius = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:676:2: ( APERTURERADIUS a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:676:4: APERTURERADIUS a= FLOAT
            {
            match(input,APERTURERADIUS,FOLLOW_APERTURERADIUS_in_beamApertureRadius7093); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamApertureRadius7097); 

            apertureRadius = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return apertureRadius;
    }
    // $ANTLR end "beamApertureRadius"



    // $ANTLR start "beamFile"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:680:1: beamFile returns [String filename] : FILE a= STRING ;
    public final String beamFile() throws RecognitionException {
        String filename = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:681:2: ( FILE a= STRING )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:681:4: FILE a= STRING
            {
            match(input,FILE,FOLLOW_FILE_in_beamFile7189); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_beamFile7193); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:685:1: beamPixelSize returns [Map<Object, Object> properties] : PIXELSIZE a= FLOAT b= FLOAT ;
    public final Map<Object, Object> beamPixelSize() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:686:5: ( PIXELSIZE a= FLOAT b= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:686:7: PIXELSIZE a= FLOAT b= FLOAT
            {
            match(input,PIXELSIZE,FOLLOW_PIXELSIZE_in_beamPixelSize7240); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize7244); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize7248); 

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



    // $ANTLR start "energyFWHM"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:693:1: energyFWHM returns [Double eFWHM] : ENERGYFWHM a= FLOAT ;
    public final Double energyFWHM() throws RecognitionException {
        Double eFWHM = null;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:694:2: ( ENERGYFWHM a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:694:4: ENERGYFWHM a= FLOAT
            {
            match(input,ENERGYFWHM,FOLLOW_ENERGYFWHM_in_energyFWHM7323); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_energyFWHM7327); 

            eFWHM = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
            // do for sure before leaving
        }
        return eFWHM;
    }
    // $ANTLR end "energyFWHM"



    // $ANTLR start "beamCollimation"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:699:1: beamCollimation returns [Map<Object, Object> properties] : ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR c= FLOAT f= FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT );
    public final Map<Object, Object> beamCollimation() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token c=null;
        Token f=null;
        Token d=null;
        Token e=null;

         
                properties = new HashMap<Object, Object>();

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:703:2: ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR c= FLOAT f= FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT )
            int alt19=5;
            switch ( input.LA(1) ) {
            case COLLIMATION:
                {
                alt19=1;
                }
                break;
            case RECTANGULAR:
                {
                alt19=2;
                }
                break;
            case CIRCULAR:
                {
                alt19=3;
                }
                break;
            case HORIZONTAL:
                {
                alt19=4;
                }
                break;
            case VERTICAL:
                {
                alt19=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }

            switch (alt19) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:703:4: COLLIMATION
                    {
                    match(input,COLLIMATION,FOLLOW_COLLIMATION_in_beamCollimation7407); 

                    }
                    break;
                case 2 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:704:4: RECTANGULAR a= FLOAT b= FLOAT
                    {
                    match(input,RECTANGULAR,FOLLOW_RECTANGULAR_in_beamCollimation7413); 

                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7417); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7421); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((a!=null?a.getText():null)));
                                                        properties.put(Beam.BEAM_COLL_V, Double.parseDouble((b!=null?b.getText():null))); 

                    }
                    break;
                case 3 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:706:4: CIRCULAR c= FLOAT f= FLOAT
                    {
                    match(input,CIRCULAR,FOLLOW_CIRCULAR_in_beamCollimation7428); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7432); 

                    f=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7436); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((c!=null?c.getText():null)));
                                                        properties.put(Beam.BEAM_COLL_V, Double.parseDouble((f!=null?f.getText():null))); 
                                                        properties.put(Beam.BEAM_CIRCULAR, "TRUE"); 

                    }
                    break;
                case 4 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:709:4: HORIZONTAL d= FLOAT
                    {
                    match(input,HORIZONTAL,FOLLOW_HORIZONTAL_in_beamCollimation7443); 

                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7447); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((d!=null?d.getText():null))); 

                    }
                    break;
                case 5 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:710:4: VERTICAL e= FLOAT
                    {
                    match(input,VERTICAL,FOLLOW_VERTICAL_in_beamCollimation7454); 

                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7458); 

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
        Double  maxRes;
    }
    protected Stack wedge_stack = new Stack();



    // $ANTLR start "wedge"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:720:1: wedge returns [Wedge wObj] : WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ ;
    public final Wedge wedge() throws RecognitionException {
        wedge_stack.push(new wedge_scope());
        Wedge wObj = null;


        Token a=null;
        Token b=null;

         

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:740:2: ( WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:740:4: WEDGE a= FLOAT b= FLOAT ( wedgeLine )+
            {
            match(input,WEDGE,FOLLOW_WEDGE_in_wedge7771); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge7775); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge7779); 

            ((wedge_scope)wedge_stack.peek()).startAng = Double.parseDouble((a!=null?a.getText():null));
                                         ((wedge_scope)wedge_stack.peek()).endAng = Double.parseDouble((b!=null?b.getText():null)); 

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:742:4: ( wedgeLine )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==ANGULARRESOLUTION||LA20_0==EXPOSURETIME||LA20_0==MAXRESOLUTION||LA20_0==ROTAXBEAMOFFSET||LA20_0==STARTOFFSET||LA20_0==TRANSLATEPERDEGREE) ) {
                    alt20=1;
                }


                switch (alt20) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:742:4: wedgeLine
                    {
                    pushFollow(FOLLOW_wedgeLine_in_wedge7786);
                    wedgeLine();

                    state._fsp--;


                    }
                    break;

                default :
                    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);


            }

             
            wObj = new Wedge(((wedge_scope)wedge_stack.peek()).angRes, ((wedge_scope)wedge_stack.peek()).startAng, ((wedge_scope)wedge_stack.peek()).endAng, ((wedge_scope)wedge_stack.peek()).expTime, ((wedge_scope)wedge_stack.peek()).offsetX, ((wedge_scope)wedge_stack.peek()).offsetY, ((wedge_scope)wedge_stack.peek()).offsetZ, ((wedge_scope)wedge_stack.peek()).translateX, ((wedge_scope)wedge_stack.peek()).translateY, ((wedge_scope)wedge_stack.peek()).translateZ, ((wedge_scope)wedge_stack.peek()).rotationOffset, ((wedge_scope)wedge_stack.peek()).maxRes);

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:745:1: wedgeLine : (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset |f= wedgeMaxRes );
    public final void wedgeLine() throws RecognitionException {
        double a =0.0;

        double b =0.0;

        InputfileParser.wedgeStartOffset_return c =null;

        InputfileParser.wedgeTranslate_return d =null;

        double e =0.0;

        double f =0.0;


        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:746:2: (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset |f= wedgeMaxRes )
            int alt21=6;
            switch ( input.LA(1) ) {
            case EXPOSURETIME:
                {
                alt21=1;
                }
                break;
            case ANGULARRESOLUTION:
                {
                alt21=2;
                }
                break;
            case STARTOFFSET:
                {
                alt21=3;
                }
                break;
            case TRANSLATEPERDEGREE:
                {
                alt21=4;
                }
                break;
            case ROTAXBEAMOFFSET:
                {
                alt21=5;
                }
                break;
            case MAXRESOLUTION:
                {
                alt21=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }

            switch (alt21) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:746:4: a= wedgeExposure
                    {
                    pushFollow(FOLLOW_wedgeExposure_in_wedgeLine7830);
                    a=wedgeExposure();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).expTime =a; 

                    }
                    break;
                case 2 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:747:4: b= wedgeAngRes
                    {
                    pushFollow(FOLLOW_wedgeAngRes_in_wedgeLine7840);
                    b=wedgeAngRes();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).angRes =b; 

                    }
                    break;
                case 3 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:748:4: c= wedgeStartOffset
                    {
                    pushFollow(FOLLOW_wedgeStartOffset_in_wedgeLine7851);
                    c=wedgeStartOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).offsetX =(c!=null?c.x:null);
                                                 ((wedge_scope)wedge_stack.peek()).offsetY =(c!=null?c.y:null);
                                                 ((wedge_scope)wedge_stack.peek()).offsetZ =(c!=null?c.z:null); 

                    }
                    break;
                case 4 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:751:4: d= wedgeTranslate
                    {
                    pushFollow(FOLLOW_wedgeTranslate_in_wedgeLine7861);
                    d=wedgeTranslate();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).translateX =(d!=null?d.x:null);
                                                 ((wedge_scope)wedge_stack.peek()).translateY =(d!=null?d.y:null);
                                                 ((wedge_scope)wedge_stack.peek()).translateZ =(d!=null?d.z:null); 

                    }
                    break;
                case 5 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:754:4: e= wedgeRotAxBeamOffset
                    {
                    pushFollow(FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine7871);
                    e=wedgeRotAxBeamOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).rotationOffset =e; 

                    }
                    break;
                case 6 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:755:4: f= wedgeMaxRes
                    {
                    pushFollow(FOLLOW_wedgeMaxRes_in_wedgeLine7880);
                    f=wedgeMaxRes();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).maxRes =f; 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:758:1: wedgeExposure returns [double value] : EXPOSURETIME a= FLOAT ;
    public final double wedgeExposure() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:759:2: ( EXPOSURETIME a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:759:4: EXPOSURETIME a= FLOAT
            {
            match(input,EXPOSURETIME,FOLLOW_EXPOSURETIME_in_wedgeExposure7897); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeExposure7901); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:762:1: wedgeAngRes returns [double res] : ANGULARRESOLUTION a= FLOAT ;
    public final double wedgeAngRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:763:2: ( ANGULARRESOLUTION a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:763:4: ANGULARRESOLUTION a= FLOAT
            {
            match(input,ANGULARRESOLUTION,FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes7983); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeAngRes7987); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:766:1: wedgeStartOffset returns [Double x, Double y, Double z] : STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeStartOffset_return wedgeStartOffset() throws RecognitionException {
        InputfileParser.wedgeStartOffset_return retval = new InputfileParser.wedgeStartOffset_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:767:2: ( STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:767:4: STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,STARTOFFSET,FOLLOW_STARTOFFSET_in_wedgeStartOffset8094); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset8098); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset8102); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:768:17: (c= FLOAT )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==FLOAT) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:768:17: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset8123); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:772:1: wedgeTranslate returns [Double x, Double y, Double z] : TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeTranslate_return wedgeTranslate() throws RecognitionException {
        InputfileParser.wedgeTranslate_return retval = new InputfileParser.wedgeTranslate_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:773:2: ( TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:773:4: TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,TRANSLATEPERDEGREE,FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate8217); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate8221); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate8225); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:774:24: (c= FLOAT )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==FLOAT) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:774:24: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate8253); 

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
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:778:1: wedgeRotAxBeamOffset returns [double delta] : ROTAXBEAMOFFSET a= FLOAT ;
    public final double wedgeRotAxBeamOffset() throws RecognitionException {
        double delta = 0.0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:779:2: ( ROTAXBEAMOFFSET a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:779:4: ROTAXBEAMOFFSET a= FLOAT
            {
            match(input,ROTAXBEAMOFFSET,FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset8389); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeRotAxBeamOffset8393); 

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



    // $ANTLR start "wedgeMaxRes"
    // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:782:1: wedgeMaxRes returns [double res] : MAXRESOLUTION a= FLOAT ;
    public final double wedgeMaxRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:783:2: ( MAXRESOLUTION a= FLOAT )
            // /home/josh/git/RADDOSE-3D/lib/antlrworks-parsergenerator/Inputfile.g:783:4: MAXRESOLUTION a= FLOAT
            {
            match(input,MAXRESOLUTION,FOLLOW_MAXRESOLUTION_in_wedgeMaxRes8490); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeMaxRes8494); 

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
    // $ANTLR end "wedgeMaxRes"

    // Delegated rules


 

    public static final BitSet FOLLOW_crystal_in_configfile47 = new BitSet(new long[]{0x0000000001000400L,0x0000002000000000L});
    public static final BitSet FOLLOW_wedge_in_configfile65 = new BitSet(new long[]{0x0000000001000400L,0x0000002000000000L});
    public static final BitSet FOLLOW_beam_in_configfile85 = new BitSet(new long[]{0x0000000001000400L,0x0000002000000000L});
    public static final BitSet FOLLOW_EOF_in_configfile105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CRYSTAL_in_crystal134 = new BitSet(new long[]{0xBE9C120076F8B870L,0x0000004DF39D107DL});
    public static final BitSet FOLLOW_crystalLine_in_crystal136 = new BitSet(new long[]{0xBE9C120076F8B872L,0x0000004DF39D107DL});
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
    public static final BitSet FOLLOW_numcarb_in_crystalLine376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_heavyProteinAtoms_in_crystalLine389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_heavySolutionConc_in_crystalLine398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_solventFraction_in_crystalLine407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pdb_in_crystalLine417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wireframeType_in_crystalLine430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modelFile_in_crystalLine441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_calculatePEEscape_in_crystalLine453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_proteinConcentration_in_crystalLine463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerMaterialElements_in_crystalLine472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sequenceFile_in_crystalLine481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_smallMoleAtoms_in_crystalLine492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cif_in_crystalLine513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_calculateFLEscape_in_crystalLine527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_flResolution_in_crystalLine537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_peResolution_in_crystalLine548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_surroundingHeavyConc_in_crystalLine560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_oilBased_in_crystalLine577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_goniometerAxis_in_crystalLine602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_calcSurrounding_in_crystalLine612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_oilElements_in_crystalLine636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_oilDensity_in_crystalLine660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_program_in_crystalLine685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simElectrons_in_crystalLine710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_runs_in_crystalLine720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_surroundingThickness_in_crystalLine745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_polarisationDirection_in_crystalLine763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_crystalType796 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_crystalType800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalDDM842 = new BitSet(new long[]{0x0003000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_crystalDDMKeyword_in_crystalDDM854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIMPLE_in_crystalDDMKeyword1002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LINEAR_in_crystalDDMKeyword1009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEAL_in_crystalDDMKeyword1016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECAYPARAM_in_crystalDecayParam1136 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam1140 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam1144 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSCOEFCALC_in_crystalCoefcalc1220 = new BitSet(new long[]{0x4000000088004200L,0x0000000000426300L});
    public static final BitSet FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc1224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DUMMY_in_crystalCoefcalcKeyword1303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDB_in_crystalCoefcalcKeyword1343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXS_in_crystalCoefcalcKeyword1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SMALLMOLE_in_crystalCoefcalcKeyword1375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIF_in_crystalCoefcalcKeyword1386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_crystalDim1795 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1808 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1812 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1828 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEP_in_crystalAngP1921 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngP1925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEL_in_crystalAngL1980 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngL1984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSPERMICRON_in_crystalPPM2038 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalPPM2040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNITCELL_in_unitcell2138 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2142 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2146 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2150 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2165 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2169 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SURROUNDINGTHICKNESS_in_surroundingThickness2255 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_surroundingThickness2265 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_surroundingThickness2269 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_surroundingThickness2273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_proteinConcentration2399 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_proteinConcentration2409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMMONOMERS_in_nummonomers2591 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_nummonomers2595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRESIDUES_in_numresidues2672 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numresidues2676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRNA_in_numRNA2754 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numRNA2758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMDNA_in_numDNA2811 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numDNA2815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMCARB_in_numcarb2867 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numcarb2871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2932 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavyProteinAtoms2937 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavyProteinAtoms2941 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_SMALLMOLEATOMS_in_smallMoleAtoms3088 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ELEMENT_in_smallMoleAtoms3093 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_smallMoleAtoms3097 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc3199 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavySolutionConc3204 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavySolutionConc3208 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_SOLVENTFRACTION_in_solventFraction3314 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_solventFraction3318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDBNAME_in_pdb3415 = new BitSet(new long[]{0x0000010000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_pdb3420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_pdb3424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIFNAME_in_cif3462 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_cif3466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WIREFRAMETYPE_in_wireframeType3503 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_wireframeType3507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODELFILE_in_modelFile3595 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_modelFile3599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEPEESCAPE_in_calculatePEEscape3666 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_calculatePEEscape3670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GONIOMETERAXIS_in_goniometerAxis3782 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_goniometerAxis3786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POLARISATIONDIRECTION_in_polarisationDirection3883 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_polarisationDirection3887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalContainerMaterial4019 = new BitSet(new long[]{0x0140000200000000L});
    public static final BitSet FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial4031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NONE_in_crystalContainerKeyword4224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIXTURE_in_crystalContainerKeyword4233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELEMENTAL_in_crystalContainerKeyword4241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINERTHICKNESS_in_containerThickness4381 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerThickness4385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialMixture4496 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_containerMaterialMixture4506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialElements4731 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ELEMENT_in_containerMaterialElements4742 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerMaterialElements4746 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_CONTAINERDENSITY_in_containerDensity4981 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerDensity4985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sequenceFile5086 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_sequenceFile5096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEFLESCAPE_in_calculateFLEscape5219 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_calculateFLEscape5223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLRESOLUTION_in_flResolution5334 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_flResolution5338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERESOLUTION_in_peResolution5420 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_peResolution5424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SURROUNDINGHEAVYCONC_in_surroundingHeavyConc5510 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ELEMENT_in_surroundingHeavyConc5515 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_surroundingHeavyConc5519 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_DENSITYBASED_in_oilBased5647 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_oilBased5651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCSURROUNDING_in_calcSurrounding5733 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_calcSurrounding5737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SURROUNDINGELEMENTS_in_oilElements5840 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ELEMENT_in_oilElements5845 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_oilElements5849 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_SURROUNDINGDENSITY_in_oilDensity5972 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_oilDensity5976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_simElectrons6088 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_simElectrons6098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBPROGRAM_in_program6237 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_program6241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RUNS_in_runs6317 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_runs6321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEAM_in_beam6384 = new BitSet(new long[]{0x00006CAC00030100L,0x0000001400008482L});
    public static final BitSet FOLLOW_beamLine_in_beam6386 = new BitSet(new long[]{0x00006CAC00030102L,0x0000001400008482L});
    public static final BitSet FOLLOW_TYPE_in_beamLine6425 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_beamLine6429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFlux_in_beamLine6447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFWHM_in_beamLine6459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamEnergy_in_beamLine6471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamCollimation_in_beamLine6483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFile_in_beamLine6494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamPixelSize_in_beamLine6515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamExposure_in_beamLine6532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamSemiAngle_in_beamLine6544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamApertureRadius_in_beamLine6553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_imageDimensions_in_beamLine6562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pulseEnergy_in_beamLine6572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_energyFWHM_in_beamLine6584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLUX_in_beamFlux6614 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFlux6618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURE_in_beamExposure6660 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamExposure6664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMAGEDIM_in_imageDimensions6726 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_imageDimensions6730 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_imageDimensions6734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FWHM_in_beamFWHM6799 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM6803 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM6807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGY_in_beamEnergy6849 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamEnergy6853 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_KEV_in_beamEnergy6860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PULSEENERGY_in_pulseEnergy6938 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_pulseEnergy6942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMIANGLE_in_beamSemiAngle7020 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamSemiAngle7024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_APERTURERADIUS_in_beamApertureRadius7093 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamApertureRadius7097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_beamFile7189 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_beamFile7193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSIZE_in_beamPixelSize7240 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize7244 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize7248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGYFWHM_in_energyFWHM7323 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_energyFWHM7327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLIMATION_in_beamCollimation7407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RECTANGULAR_in_beamCollimation7413 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7417 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCULAR_in_beamCollimation7428 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7432 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HORIZONTAL_in_beamCollimation7443 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERTICAL_in_beamCollimation7454 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WEDGE_in_wedge7771 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge7775 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge7779 = new BitSet(new long[]{0x0020004000000080L,0x0000000204000800L});
    public static final BitSet FOLLOW_wedgeLine_in_wedge7786 = new BitSet(new long[]{0x0020004000000082L,0x0000000204000800L});
    public static final BitSet FOLLOW_wedgeExposure_in_wedgeLine7830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeAngRes_in_wedgeLine7840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeStartOffset_in_wedgeLine7851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeTranslate_in_wedgeLine7861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine7871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeMaxRes_in_wedgeLine7880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURETIME_in_wedgeExposure7897 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeExposure7901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes7983 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeAngRes7987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTOFFSET_in_wedgeStartOffset8094 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset8098 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset8102 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset8123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate8217 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate8221 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate8225 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate8253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset8389 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeRotAxBeamOffset8393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MAXRESOLUTION_in_wedgeMaxRes8490 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeMaxRes8494 = new BitSet(new long[]{0x0000000000000002L});

}