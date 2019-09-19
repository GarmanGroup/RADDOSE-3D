// $ANTLR 3.4 C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g 2019-09-19 16:45:40

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABSCOEFCALC", "ANGLEL", "ANGLEP", "ANGULARRESOLUTION", "APERTURERADIUS", "AVERAGE", "BEAM", "CALCSURROUNDING", "CALCULATEFLESCAPE", "CALCULATEPEESCAPE", "CIF", "CIFNAME", "CIRCULAR", "COLLIMATION", "COMMENT", "CONTAINERDENSITY", "CONTAINERMATERIALELEMENTS", "CONTAINERMATERIALMIXTURE", "CONTAINERMATERIALTYPE", "CONTAINERTHICKNESS", "CRYSTAL", "DDM", "DECAYPARAM", "DEFAULT", "DENSITYBASED", "DIFFRACTIONDECAYMODEL", "DIMENSION", "DUMMY", "ELEMENT", "ELEMENTAL", "ENERGY", "ENERGYFWHM", "EXPONENT", "EXPOSURE", "EXPOSURETIME", "FILE", "FLOAT", "FLRESOLUTION", "FLUX", "FWHM", "GONIOMETERAXIS", "HORIZONTAL", "IMAGEDIM", "KEV", "LEAL", "LINEAR", "MATERIALELEMENTS", "MATERIALMIXTURE", "MATERIALTYPE", "MAXRESOLUTION", "MIXTURE", "MODELFILE", "NONE", "NUMCARB", "NUMDNA", "NUMMONOMERS", "NUMRESIDUES", "NUMRNA", "PDB", "PDBNAME", "PERESOLUTION", "PIXELSIZE", "PIXELSPERMICRON", "PROTEINCONC", "PROTEINCONCENTRATION", "PROTEINHEAVYATOMS", "PULSEENERGY", "RDFORTAN", "RDJAVA", "RECTANGULAR", "ROTAXBEAMOFFSET", "RUNS", "SAXS", "SAXSSEQ", "SEMIANGLE", "SEQFILE", "SEQUENCE", "SEQUENCEFILE", "SIMELECTRONS", "SIMPHOTONS", "SIMPLE", "SMALLMOLE", "SMALLMOLEATOMS", "SOLVENTFRACTION", "SOLVENTHEAVYCONC", "STARTOFFSET", "STRING", "SUBPROGRAM", "SURROUNDINGDENSITY", "SURROUNDINGELEMENTS", "SURROUNDINGHEAVYCONC", "SURROUNDINGTHICKNESS", "TRANSLATEPERDEGREE", "TYPE", "UNITCELL", "VERTICAL", "WEDGE", "WIREFRAMETYPE", "WS"
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
    public static final int PROTEINCONC=67;
    public static final int PROTEINCONCENTRATION=68;
    public static final int PROTEINHEAVYATOMS=69;
    public static final int PULSEENERGY=70;
    public static final int RDFORTAN=71;
    public static final int RDJAVA=72;
    public static final int RECTANGULAR=73;
    public static final int ROTAXBEAMOFFSET=74;
    public static final int RUNS=75;
    public static final int SAXS=76;
    public static final int SAXSSEQ=77;
    public static final int SEMIANGLE=78;
    public static final int SEQFILE=79;
    public static final int SEQUENCE=80;
    public static final int SEQUENCEFILE=81;
    public static final int SIMELECTRONS=82;
    public static final int SIMPHOTONS=83;
    public static final int SIMPLE=84;
    public static final int SMALLMOLE=85;
    public static final int SMALLMOLEATOMS=86;
    public static final int SOLVENTFRACTION=87;
    public static final int SOLVENTHEAVYCONC=88;
    public static final int STARTOFFSET=89;
    public static final int STRING=90;
    public static final int SUBPROGRAM=91;
    public static final int SURROUNDINGDENSITY=92;
    public static final int SURROUNDINGELEMENTS=93;
    public static final int SURROUNDINGHEAVYCONC=94;
    public static final int SURROUNDINGTHICKNESS=95;
    public static final int TRANSLATEPERDEGREE=96;
    public static final int TYPE=97;
    public static final int UNITCELL=98;
    public static final int VERTICAL=99;
    public static final int WEDGE=100;
    public static final int WIREFRAMETYPE=101;
    public static final int WS=102;

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
    public String getGrammarFileName() { return "C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g"; }


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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:41:1: configfile : (a= crystal |b= wedge |c= beam )* EOF ;
    public final void configfile() throws RecognitionException {
        Crystal a =null;

        Wedge b =null;

        Beam c =null;


        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:41:11: ( (a= crystal |b= wedge |c= beam )* EOF )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )* EOF
            {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:42:11: (a= crystal |b= wedge |c= beam )*
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
            	    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:42:13: a= crystal
            	    {
            	    pushFollow(FOLLOW_crystal_in_configfile47);
            	    a=crystal();

            	    state._fsp--;


            	     raddoseInitializer.setCrystal(a); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:43:13: b= wedge
            	    {
            	    pushFollow(FOLLOW_wedge_in_configfile65);
            	    b=wedge();

            	    state._fsp--;


            	     raddoseInitializer.exposeWedge(b); 

            	    }
            	    break;
            	case 3 :
            	    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:44:13: c= beam
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
        String                  cif;
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
        int                     numCarb;
        List<String>    smallMoleAtomNames;
        List<Double>    smallMoleAtomNums;
        List<String>	heavyProteinAtomNames;
        List<Double>	heavyProteinAtomNums;
        List<String>	heavySolutionConcNames;
        List<Double>	heavySolutionConcNums;
        List<String>	cryoSolutionMolecule;
        List<Double>	cryoSolutionConc;
        Double 			solFrac;
        String                 oilBased;
        List<String>         oilNames;
        List<Double>          oilNums;
        Double 		      oilDensity;
        String 	           calcSurrounding;
        long              simElectrons;
        HashMap<Object, Object> crystalProperties;
    }
    protected Stack crystal_stack = new Stack();



    // $ANTLR start "crystal"
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:49:1: crystal returns [Crystal cObj] : CRYSTAL ( crystalLine )+ ;
    public final Crystal crystal() throws RecognitionException {
        crystal_stack.push(new crystal_scope());
        Crystal cObj = null;


         
        ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = 2; // 0 = error, 1 = Simple, 2 = DEFAULT, 3 = RDV2, 4 = PDB, 5 = SAXS
        		((crystal_scope)crystal_stack.peek()).crystalProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:237:2: ( CRYSTAL ( crystalLine )+ )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:237:4: CRYSTAL ( crystalLine )+
            {
            match(input,CRYSTAL,FOLLOW_CRYSTAL_in_crystal134); 

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:237:12: ( crystalLine )+
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
            	    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:237:12: crystalLine
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
              													((crystal_scope)crystal_stack.peek()).solFrac, ((crystal_scope)crystal_stack.peek()).oilBased, 	((crystal_scope)crystal_stack.peek()).calcSurrounding,
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
              	((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromPDB(((crystal_scope)crystal_stack.peek()).pdb, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums, ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule, ((crystal_scope)crystal_stack.peek()).cryoSolutionConc, ((crystal_scope)crystal_stack.peek()).oilBased, 	((crystal_scope)crystal_stack.peek()).calcSurrounding, ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums, ((crystal_scope)crystal_stack.peek()).oilDensity, ((crystal_scope)crystal_stack.peek()).simElectrons);
              else
            	((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromPDB(((crystal_scope)crystal_stack.peek()).pdb, ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule, ((crystal_scope)crystal_stack.peek()).cryoSolutionConc, ((crystal_scope)crystal_stack.peek()).oilBased, 	((crystal_scope)crystal_stack.peek()).calcSurrounding, ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums, ((crystal_scope)crystal_stack.peek()).oilDensity, ((crystal_scope)crystal_stack.peek()).simElectrons);
              													  													
            }

            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 5)
            {
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcSAXS(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
              													((crystal_scope)crystal_stack.peek()).numRes, ((crystal_scope)crystal_stack.peek()).numRNA, ((crystal_scope)crystal_stack.peek()).numDNA,
              													((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
              													((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
              													((crystal_scope)crystal_stack.peek()).solFrac, ((crystal_scope)crystal_stack.peek()).proteinConc,
              													((crystal_scope)crystal_stack.peek()).numCarb);
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
              													((crystal_scope)crystal_stack.peek()).oilBased, 	((crystal_scope)crystal_stack.peek()).calcSurrounding,
              													((crystal_scope)crystal_stack.peek()).numCarb, ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums, ((crystal_scope)crystal_stack.peek()).oilDensity);
            }

            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 7)
            {
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromSequenceSAXS(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
              													((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
              													((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
              													((crystal_scope)crystal_stack.peek()).solFrac, ((crystal_scope)crystal_stack.peek()).proteinConc, ((crystal_scope)crystal_stack.peek()).seqFile,
              													((crystal_scope)crystal_stack.peek()).numCarb);
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
              													((crystal_scope)crystal_stack.peek()).oilBased, 	((crystal_scope)crystal_stack.peek()).calcSurrounding,
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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:240:1: crystalLine : (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |qa= numcarb |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculatePEEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile |ab= smallMoleAtoms |ac= cif |bb= calculateFLEscape |cc= flResolution |dd= peResolution |ee= surroundingHeavyConc |ff= oilBased |gg= goniometerAxis |hh= calcSurrounding |ii= oilElements |jj= oilDensity |kk= program |ll= simElectrons |mm= runs |nn= surroundingThickness );
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


        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:241:2: (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |qa= numcarb |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculatePEEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile |ab= smallMoleAtoms |ac= cif |bb= calculateFLEscape |cc= flResolution |dd= peResolution |ee= surroundingHeavyConc |ff= oilBased |gg= goniometerAxis |hh= calcSurrounding |ii= oilElements |jj= oilDensity |kk= program |ll= simElectrons |mm= runs |nn= surroundingThickness )
            int alt3=43;
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
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:241:4: a= crystalType
                    {
                    pushFollow(FOLLOW_crystalType_in_crystalLine192);
                    a=crystalType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalType = a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:242:4: b= crystalDDM
                    {
                    pushFollow(FOLLOW_crystalDDM_in_crystalLine203);
                    b=crystalDDM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalDdm = b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:243:4: c= crystalCoefcalc
                    {
                    pushFollow(FOLLOW_crystalCoefcalc_in_crystalLine215);
                    c=crystalCoefcalc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = c; 

                    }
                    break;
                case 4 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:244:4: d= crystalDim
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
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:247:4: e= crystalPPM
                    {
                    pushFollow(FOLLOW_crystalPPM_in_crystalLine236);
                    e=crystalPPM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, e); 

                    }
                    break;
                case 6 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:248:4: f= crystalAngP
                    {
                    pushFollow(FOLLOW_crystalAngP_in_crystalLine247);
                    f=crystalAngP();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:249:4: g= crystalAngL
                    {
                    pushFollow(FOLLOW_crystalAngL_in_crystalLine258);
                    g=crystalAngL();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, g); 

                    }
                    break;
                case 8 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:250:4: h= crystalDecayParam
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
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:253:4: i= containerThickness
                    {
                    pushFollow(FOLLOW_containerThickness_in_crystalLine279);
                    i=containerThickness();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerThickness = i; 

                    }
                    break;
                case 10 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:254:4: j= containerDensity
                    {
                    pushFollow(FOLLOW_containerDensity_in_crystalLine289);
                    j=containerDensity();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerDensity = j; 

                    }
                    break;
                case 11 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:255:4: k= crystalContainerMaterial
                    {
                    pushFollow(FOLLOW_crystalContainerMaterial_in_crystalLine299);
                    k=crystalContainerMaterial();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalContainerMaterial = k; 

                    }
                    break;
                case 12 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:256:4: l= containerMaterialMixture
                    {
                    pushFollow(FOLLOW_containerMaterialMixture_in_crystalLine308);
                    l=containerMaterialMixture();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerMixture = l; 

                    }
                    break;
                case 13 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:257:4: m= unitcell
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
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:263:4: n= nummonomers
                    {
                    pushFollow(FOLLOW_nummonomers_in_crystalLine328);
                    n=nummonomers();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numMon = n;	

                    }
                    break;
                case 15 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:264:4: o= numresidues
                    {
                    pushFollow(FOLLOW_numresidues_in_crystalLine339);
                    o=numresidues();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRes = o;	

                    }
                    break;
                case 16 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:265:4: p= numRNA
                    {
                    pushFollow(FOLLOW_numRNA_in_crystalLine350);
                    p=numRNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRNA = p;	

                    }
                    break;
                case 17 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:266:4: q= numDNA
                    {
                    pushFollow(FOLLOW_numDNA_in_crystalLine363);
                    q=numDNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numDNA = q;	

                    }
                    break;
                case 18 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:267:4: qa= numcarb
                    {
                    pushFollow(FOLLOW_numcarb_in_crystalLine376);
                    qa=numcarb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numCarb = qa;	

                    }
                    break;
                case 19 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:268:4: r= heavyProteinAtoms
                    {
                    pushFollow(FOLLOW_heavyProteinAtoms_in_crystalLine389);
                    r=heavyProteinAtoms();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames = (r!=null?r.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums = (r!=null?r.num:null);	

                    }
                    break;
                case 20 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:270:4: s= heavySolutionConc
                    {
                    pushFollow(FOLLOW_heavySolutionConc_in_crystalLine398);
                    s=heavySolutionConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames = (s!=null?s.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums = (s!=null?s.num:null);	

                    }
                    break;
                case 21 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:272:4: t= solventFraction
                    {
                    pushFollow(FOLLOW_solventFraction_in_crystalLine407);
                    t=solventFraction();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).solFrac = t; 

                    }
                    break;
                case 22 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:273:4: u= pdb
                    {
                    pushFollow(FOLLOW_pdb_in_crystalLine417);
                    u=pdb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).pdb = u; 

                    }
                    break;
                case 23 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:274:4: v= wireframeType
                    {
                    pushFollow(FOLLOW_wireframeType_in_crystalLine430);
                    v=wireframeType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_TYPE, v); 

                    }
                    break;
                case 24 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:275:4: w= modelFile
                    {
                    pushFollow(FOLLOW_modelFile_in_crystalLine441);
                    w=modelFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_FILE, w); 

                    }
                    break;
                case 25 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:276:4: x= calculatePEEscape
                    {
                    pushFollow(FOLLOW_calculatePEEscape_in_crystalLine453);
                    x=calculatePEEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ELECTRON_ESCAPE, x); 

                    }
                    break;
                case 26 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:277:4: y= proteinConcentration
                    {
                    pushFollow(FOLLOW_proteinConcentration_in_crystalLine463);
                    y=proteinConcentration();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).proteinConc = y;

                    }
                    break;
                case 27 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:278:4: z= containerMaterialElements
                    {
                    pushFollow(FOLLOW_containerMaterialElements_in_crystalLine472);
                    z=containerMaterialElements();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerElementNames = (z!=null?z.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).containerElementNums = (z!=null?z.num:null);	

                    }
                    break;
                case 28 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:280:4: aa= sequenceFile
                    {
                    pushFollow(FOLLOW_sequenceFile_in_crystalLine481);
                    aa=sequenceFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).seqFile = aa; 

                    }
                    break;
                case 29 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:281:4: ab= smallMoleAtoms
                    {
                    pushFollow(FOLLOW_smallMoleAtoms_in_crystalLine492);
                    ab=smallMoleAtoms();

                    state._fsp--;


                    ((crystal_scope)crystal_stack.peek()).smallMoleAtomNames = (ab!=null?ab.names:null);
                    							((crystal_scope)crystal_stack.peek()).smallMoleAtomNums = (ab!=null?ab.num:null);	

                    }
                    break;
                case 30 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:283:4: ac= cif
                    {
                    pushFollow(FOLLOW_cif_in_crystalLine513);
                    ac=cif();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).cif = ac; 

                    }
                    break;
                case 31 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:285:4: bb= calculateFLEscape
                    {
                    pushFollow(FOLLOW_calculateFLEscape_in_crystalLine527);
                    bb=calculateFLEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_FLUORESCENT_ESCAPE, bb); 

                    }
                    break;
                case 32 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:286:4: cc= flResolution
                    {
                    pushFollow(FOLLOW_flResolution_in_crystalLine537);
                    cc=flResolution();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_FLUORESCENT_RESOLUTION, cc);

                    }
                    break;
                case 33 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:287:4: dd= peResolution
                    {
                    pushFollow(FOLLOW_peResolution_in_crystalLine548);
                    dd=peResolution();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_PHOTOELECTRON_RESOLUTION, dd);

                    }
                    break;
                case 34 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:289:4: ee= surroundingHeavyConc
                    {
                    pushFollow(FOLLOW_surroundingHeavyConc_in_crystalLine560);
                    ee=surroundingHeavyConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule = (ee!=null?ee.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).cryoSolutionConc = (ee!=null?ee.num:null);	

                    }
                    break;
                case 35 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:291:4: ff= oilBased
                    {
                    pushFollow(FOLLOW_oilBased_in_crystalLine577);
                    ff=oilBased();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).oilBased = ff;  

                    }
                    break;
                case 36 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:292:4: gg= goniometerAxis
                    {
                    pushFollow(FOLLOW_goniometerAxis_in_crystalLine602);
                    gg=goniometerAxis();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_GONIOMETER_AXIS, gg); 

                    }
                    break;
                case 37 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:293:4: hh= calcSurrounding
                    {
                    pushFollow(FOLLOW_calcSurrounding_in_crystalLine612);
                    hh=calcSurrounding();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).calcSurrounding = hh;  

                    }
                    break;
                case 38 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:294:4: ii= oilElements
                    {
                    pushFollow(FOLLOW_oilElements_in_crystalLine636);
                    ii=oilElements();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).oilNames = (ii!=null?ii.names:null);  
                    		                	         ((crystal_scope)crystal_stack.peek()).oilNums = (ii!=null?ii.num:null);  

                    }
                    break;
                case 39 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:296:4: jj= oilDensity
                    {
                    pushFollow(FOLLOW_oilDensity_in_crystalLine660);
                    jj=oilDensity();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).oilDensity = jj;  

                    }
                    break;
                case 40 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:297:4: kk= program
                    {
                    pushFollow(FOLLOW_program_in_crystalLine685);
                    kk=program();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_PROGRAM, kk); 

                    }
                    break;
                case 41 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:298:4: ll= simElectrons
                    {
                    pushFollow(FOLLOW_simElectrons_in_crystalLine710);
                    ll=simElectrons();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).simElectrons = ll; 

                    }
                    break;
                case 42 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:299:4: mm= runs
                    {
                    pushFollow(FOLLOW_runs_in_crystalLine720);
                    mm=runs();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RUNS, mm); 

                    }
                    break;
                case 43 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:300:4: nn= surroundingThickness
                    {
                    pushFollow(FOLLOW_surroundingThickness_in_crystalLine745);
                    nn=surroundingThickness();

                    state._fsp--;


                     if (nn != null) {
                    							   ((crystal_scope)crystal_stack.peek()).crystalProperties.putAll(nn);
                    							  }; 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:307:1: crystalType returns [String crystalType] : TYPE e= STRING ;
    public final String crystalType() throws RecognitionException {
        String crystalType = null;


        Token e=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:308:2: ( TYPE e= STRING )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:308:4: TYPE e= STRING
            {
            match(input,TYPE,FOLLOW_TYPE_in_crystalType774); 

            e=(Token)match(input,STRING,FOLLOW_STRING_in_crystalType778); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:311:1: crystalDDM returns [int value] : ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword ;
    public final int crystalDDM() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:312:2: ( ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:312:4: ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword
            {
            if ( input.LA(1)==DDM||input.LA(1)==DIFFRACTIONDECAYMODEL ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalDDMKeyword_in_crystalDDM832);
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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:315:1: crystalDDMKeyword returns [int value] : ( SIMPLE | LINEAR | LEAL );
    public final int crystalDDMKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:316:2: ( SIMPLE | LINEAR | LEAL )
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
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:316:4: SIMPLE
                    {
                    match(input,SIMPLE,FOLLOW_SIMPLE_in_crystalDDMKeyword980); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:317:4: LINEAR
                    {
                    match(input,LINEAR,FOLLOW_LINEAR_in_crystalDDMKeyword987); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:318:4: LEAL
                    {
                    match(input,LEAL,FOLLOW_LEAL_in_crystalDDMKeyword994); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:324:1: crystalDecayParam returns [Double gammaParam, Double b0Param, Double betaParam] : DECAYPARAM a= FLOAT b= FLOAT c= FLOAT ;
    public final InputfileParser.crystalDecayParam_return crystalDecayParam() throws RecognitionException {
        InputfileParser.crystalDecayParam_return retval = new InputfileParser.crystalDecayParam_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:325:2: ( DECAYPARAM a= FLOAT b= FLOAT c= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:325:4: DECAYPARAM a= FLOAT b= FLOAT c= FLOAT
            {
            match(input,DECAYPARAM,FOLLOW_DECAYPARAM_in_crystalDecayParam1114); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam1118); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam1122); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam1126); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:328:1: crystalCoefcalc returns [int value] : ABSCOEFCALC a= crystalCoefcalcKeyword ;
    public final int crystalCoefcalc() throws RecognitionException {
        int value = 0;


        int a =0;


        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:329:2: ( ABSCOEFCALC a= crystalCoefcalcKeyword )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:329:4: ABSCOEFCALC a= crystalCoefcalcKeyword
            {
            match(input,ABSCOEFCALC,FOLLOW_ABSCOEFCALC_in_crystalCoefcalc1198); 

            pushFollow(FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc1202);
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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:331:1: crystalCoefcalcKeyword returns [int value] : ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS | SEQUENCE | SAXSSEQ | SMALLMOLE | CIF );
    public final int crystalCoefcalcKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:332:2: ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS | SEQUENCE | SAXSSEQ | SMALLMOLE | CIF )
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
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:332:4: DUMMY
                    {
                    match(input,DUMMY,FOLLOW_DUMMY_in_crystalCoefcalcKeyword1281); 

                     value = 1;

                    }
                    break;
                case 2 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:333:4: AVERAGE
                    {
                    match(input,AVERAGE,FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1291); 

                     value = 1;

                    }
                    break;
                case 3 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:334:4: DEFAULT
                    {
                    match(input,DEFAULT,FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1299); 

                     value = 2;

                    }
                    break;
                case 4 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:335:4: RDJAVA
                    {
                    match(input,RDJAVA,FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1307); 

                     value = 2;

                    }
                    break;
                case 5 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:336:4: RDFORTAN
                    {
                    match(input,RDFORTAN,FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1314); 

                     value = 3;

                    }
                    break;
                case 6 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:337:4: PDB
                    {
                    match(input,PDB,FOLLOW_PDB_in_crystalCoefcalcKeyword1321); 

                     value = 4;

                    }
                    break;
                case 7 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:338:4: SAXS
                    {
                    match(input,SAXS,FOLLOW_SAXS_in_crystalCoefcalcKeyword1331); 

                     value = 5;

                    }
                    break;
                case 8 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:339:4: SEQUENCE
                    {
                    match(input,SEQUENCE,FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1339); 

                     value = 6;

                    }
                    break;
                case 9 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:340:4: SAXSSEQ
                    {
                    match(input,SAXSSEQ,FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1346); 

                     value = 7;

                    }
                    break;
                case 10 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:341:4: SMALLMOLE
                    {
                    match(input,SMALLMOLE,FOLLOW_SMALLMOLE_in_crystalCoefcalcKeyword1353); 

                     value = 8;

                    }
                    break;
                case 11 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:342:4: CIF
                    {
                    match(input,CIF,FOLLOW_CIF_in_crystalCoefcalcKeyword1364); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:356:1: crystalDim returns [Map<Object, Object> properties] : DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) ;
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
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:359:3: ( DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:359:5: DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
            {
            match(input,DIMENSION,FOLLOW_DIMENSION_in_crystalDim1773); 

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:360:2: (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
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
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:361:7: a= FLOAT b= FLOAT c= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1786); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1790); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1794); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((a!=null?a.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((b!=null?b.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble((c!=null?c.getText():null))); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:364:7: e= FLOAT f= FLOAT
                    {
                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1806); 

                    f=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1810); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((e!=null?e.getText():null)));
                        		       properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((f!=null?f.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:366:7: d= FLOAT
                    {
                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1822); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:370:1: crystalAngP returns [double value] : ANGLEP a= FLOAT ;
    public final double crystalAngP() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:371:2: ( ANGLEP a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:371:4: ANGLEP a= FLOAT
            {
            match(input,ANGLEP,FOLLOW_ANGLEP_in_crystalAngP1899); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngP1903); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:375:1: crystalAngL returns [double value] : ANGLEL a= FLOAT ;
    public final double crystalAngL() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:376:2: ( ANGLEL a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:376:4: ANGLEL a= FLOAT
            {
            match(input,ANGLEL,FOLLOW_ANGLEL_in_crystalAngL1958); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngL1962); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:380:1: crystalPPM returns [double ppm] : PIXELSPERMICRON FLOAT ;
    public final double crystalPPM() throws RecognitionException {
        double ppm = 0.0;


        Token FLOAT1=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:381:2: ( PIXELSPERMICRON FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:381:4: PIXELSPERMICRON FLOAT
            {
            match(input,PIXELSPERMICRON,FOLLOW_PIXELSPERMICRON_in_crystalPPM2016); 

            FLOAT1=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalPPM2018); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:384:1: unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC] : UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? ;
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
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:385:2: ( UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:385:4: UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )?
            {
            match(input,UNITCELL,FOLLOW_UNITCELL_in_unitcell2116); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2120); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2124); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2128); 

            retval.dimA = Double.parseDouble((a!=null?a.getText():null));
            		 retval.dimB = Double.parseDouble((b!=null?b.getText():null));
            		 retval.dimC = Double.parseDouble((c!=null?c.getText():null)); 

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:389:7: (al= FLOAT be= FLOAT ga= FLOAT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FLOAT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:389:8: al= FLOAT be= FLOAT ga= FLOAT
                    {
                    al=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2143); 

                    be=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2147); 

                    ga=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2151); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:398:1: surroundingThickness returns [Map<Object, Object> properties] : SURROUNDINGTHICKNESS (a= FLOAT b= FLOAT c= FLOAT ) ;
    public final Map<Object, Object> surroundingThickness() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token c=null;

         
        		properties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:401:3: ( SURROUNDINGTHICKNESS (a= FLOAT b= FLOAT c= FLOAT ) )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:401:5: SURROUNDINGTHICKNESS (a= FLOAT b= FLOAT c= FLOAT )
            {
            match(input,SURROUNDINGTHICKNESS,FOLLOW_SURROUNDINGTHICKNESS_in_surroundingThickness2233); 

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:402:2: (a= FLOAT b= FLOAT c= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:403:2: a= FLOAT b= FLOAT c= FLOAT
            {
            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_surroundingThickness2243); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_surroundingThickness2247); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_surroundingThickness2251); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:410:1: proteinConcentration returns [Double proteinConc] : ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT ;
    public final Double proteinConcentration() throws RecognitionException {
        Double proteinConc = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:411:2: ( ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:411:4: ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT
            {
            if ( (input.LA(1) >= PROTEINCONC && input.LA(1) <= PROTEINCONCENTRATION) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_proteinConcentration2387); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:415:1: nummonomers returns [int value] : NUMMONOMERS a= FLOAT ;
    public final int nummonomers() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:416:2: ( NUMMONOMERS a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:416:4: NUMMONOMERS a= FLOAT
            {
            match(input,NUMMONOMERS,FOLLOW_NUMMONOMERS_in_nummonomers2569); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_nummonomers2573); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:419:1: numresidues returns [int value] : NUMRESIDUES a= FLOAT ;
    public final int numresidues() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:420:2: ( NUMRESIDUES a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:420:4: NUMRESIDUES a= FLOAT
            {
            match(input,NUMRESIDUES,FOLLOW_NUMRESIDUES_in_numresidues2650); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numresidues2654); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:423:1: numRNA returns [int value] : NUMRNA a= FLOAT ;
    public final int numRNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:424:2: ( NUMRNA a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:424:4: NUMRNA a= FLOAT
            {
            match(input,NUMRNA,FOLLOW_NUMRNA_in_numRNA2732); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numRNA2736); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:427:1: numDNA returns [int value] : NUMDNA a= FLOAT ;
    public final int numDNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:428:2: ( NUMDNA a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:428:4: NUMDNA a= FLOAT
            {
            match(input,NUMDNA,FOLLOW_NUMDNA_in_numDNA2789); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numDNA2793); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:431:1: numcarb returns [int value] : NUMCARB a= FLOAT ;
    public final int numcarb() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:432:2: ( NUMCARB a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:432:4: NUMCARB a= FLOAT
            {
            match(input,NUMCARB,FOLLOW_NUMCARB_in_numcarb2845); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numcarb2849); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:435:1: heavyProteinAtoms returns [List<String> names, List<Double> num;] : PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavyProteinAtoms_return heavyProteinAtoms() throws RecognitionException {
        InputfileParser.heavyProteinAtoms_return retval = new InputfileParser.heavyProteinAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:440:2: ( PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:440:4: PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,PROTEINHEAVYATOMS,FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2910); 

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:440:22: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:440:23: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavyProteinAtoms2915); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavyProteinAtoms2919); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:444:1: smallMoleAtoms returns [List<String> names, List<Double> num;] : SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.smallMoleAtoms_return smallMoleAtoms() throws RecognitionException {
        InputfileParser.smallMoleAtoms_return retval = new InputfileParser.smallMoleAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:449:2: ( SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:449:4: SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,SMALLMOLEATOMS,FOLLOW_SMALLMOLEATOMS_in_smallMoleAtoms3066); 

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:449:19: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:449:20: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_smallMoleAtoms3071); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_smallMoleAtoms3075); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:452:1: heavySolutionConc returns [List<String> names, List<Double> num;] : SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavySolutionConc_return heavySolutionConc() throws RecognitionException {
        InputfileParser.heavySolutionConc_return retval = new InputfileParser.heavySolutionConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:457:2: ( SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:457:4: SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SOLVENTHEAVYCONC,FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc3177); 

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:457:21: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:457:22: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavySolutionConc3182); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavySolutionConc3186); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:460:1: solventFraction returns [double solFrac] : SOLVENTFRACTION a= FLOAT ;
    public final double solventFraction() throws RecognitionException {
        double solFrac = 0.0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:461:2: ( SOLVENTFRACTION a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:461:4: SOLVENTFRACTION a= FLOAT
            {
            match(input,SOLVENTFRACTION,FOLLOW_SOLVENTFRACTION_in_solventFraction3292); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_solventFraction3296); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:464:1: pdb returns [String pdb] : PDBNAME (a= STRING |a= FLOAT ) ;
    public final String pdb() throws RecognitionException {
        String pdb = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:465:2: ( PDBNAME (a= STRING |a= FLOAT ) )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:465:4: PDBNAME (a= STRING |a= FLOAT )
            {
            match(input,PDBNAME,FOLLOW_PDBNAME_in_pdb3393); 

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:465:12: (a= STRING |a= FLOAT )
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
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:465:13: a= STRING
                    {
                    a=(Token)match(input,STRING,FOLLOW_STRING_in_pdb3398); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:465:22: a= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_pdb3402); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:468:1: cif returns [String cif] : CIFNAME a= STRING ;
    public final String cif() throws RecognitionException {
        String cif = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:469:2: ( CIFNAME a= STRING )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:469:4: CIFNAME a= STRING
            {
            match(input,CIFNAME,FOLLOW_CIFNAME_in_cif3440); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_cif3444); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:472:1: wireframeType returns [String value] : WIREFRAMETYPE a= STRING ;
    public final String wireframeType() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:473:2: ( WIREFRAMETYPE a= STRING )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:473:4: WIREFRAMETYPE a= STRING
            {
            match(input,WIREFRAMETYPE,FOLLOW_WIREFRAMETYPE_in_wireframeType3481); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_wireframeType3485); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:476:1: modelFile returns [String value] : MODELFILE a= STRING ;
    public final String modelFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:477:2: ( MODELFILE a= STRING )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:477:4: MODELFILE a= STRING
            {
            match(input,MODELFILE,FOLLOW_MODELFILE_in_modelFile3573); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_modelFile3577); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:480:1: calculatePEEscape returns [String value] : CALCULATEPEESCAPE a= STRING ;
    public final String calculatePEEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:481:2: ( CALCULATEPEESCAPE a= STRING )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:481:4: CALCULATEPEESCAPE a= STRING
            {
            match(input,CALCULATEPEESCAPE,FOLLOW_CALCULATEPEESCAPE_in_calculatePEEscape3644); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculatePEEscape3648); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:485:1: goniometerAxis returns [double value] : GONIOMETERAXIS a= FLOAT ;
    public final double goniometerAxis() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:486:2: ( GONIOMETERAXIS a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:486:4: GONIOMETERAXIS a= FLOAT
            {
            match(input,GONIOMETERAXIS,FOLLOW_GONIOMETERAXIS_in_goniometerAxis3760); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_goniometerAxis3764); 

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



    // $ANTLR start "crystalContainerMaterial"
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:490:1: crystalContainerMaterial returns [int value] : ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword ;
    public final int crystalContainerMaterial() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:491:2: ( ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:491:4: ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword
            {
            if ( input.LA(1)==CONTAINERMATERIALTYPE||input.LA(1)==MATERIALTYPE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial3873);
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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:494:1: crystalContainerKeyword returns [int value] : ( NONE | MIXTURE | ELEMENTAL );
    public final int crystalContainerKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:495:2: ( NONE | MIXTURE | ELEMENTAL )
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
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:495:4: NONE
                    {
                    match(input,NONE,FOLLOW_NONE_in_crystalContainerKeyword4066); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:496:4: MIXTURE
                    {
                    match(input,MIXTURE,FOLLOW_MIXTURE_in_crystalContainerKeyword4075); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:497:4: ELEMENTAL
                    {
                    match(input,ELEMENTAL,FOLLOW_ELEMENTAL_in_crystalContainerKeyword4083); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:503:1: containerThickness returns [double value] : CONTAINERTHICKNESS a= FLOAT ;
    public final double containerThickness() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:504:2: ( CONTAINERTHICKNESS a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:504:4: CONTAINERTHICKNESS a= FLOAT
            {
            match(input,CONTAINERTHICKNESS,FOLLOW_CONTAINERTHICKNESS_in_containerThickness4223); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerThickness4227); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:507:1: containerMaterialMixture returns [String value] : ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING ;
    public final String containerMaterialMixture() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:508:2: ( ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:508:4: ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING
            {
            if ( input.LA(1)==CONTAINERMATERIALMIXTURE||input.LA(1)==MATERIALMIXTURE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_containerMaterialMixture4348); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:512:1: containerMaterialElements returns [List<String> names, List<Double> num;] : ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.containerMaterialElements_return containerMaterialElements() throws RecognitionException {
        InputfileParser.containerMaterialElements_return retval = new InputfileParser.containerMaterialElements_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:517:2: ( ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:517:4: ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+
            {
            if ( input.LA(1)==CONTAINERMATERIALELEMENTS||input.LA(1)==MATERIALELEMENTS ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:517:51: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:517:52: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_containerMaterialElements4584); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerMaterialElements4588); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:521:1: containerDensity returns [double value] : CONTAINERDENSITY a= FLOAT ;
    public final double containerDensity() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:522:2: ( CONTAINERDENSITY a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:522:4: CONTAINERDENSITY a= FLOAT
            {
            match(input,CONTAINERDENSITY,FOLLOW_CONTAINERDENSITY_in_containerDensity4823); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerDensity4827); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:525:1: sequenceFile returns [String value] : ( SEQUENCEFILE | SEQFILE ) a= STRING ;
    public final String sequenceFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:526:2: ( ( SEQUENCEFILE | SEQFILE ) a= STRING )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:526:4: ( SEQUENCEFILE | SEQFILE ) a= STRING
            {
            if ( input.LA(1)==SEQFILE||input.LA(1)==SEQUENCEFILE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_sequenceFile4938); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:530:1: calculateFLEscape returns [String value] : CALCULATEFLESCAPE a= STRING ;
    public final String calculateFLEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:531:2: ( CALCULATEFLESCAPE a= STRING )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:531:4: CALCULATEFLESCAPE a= STRING
            {
            match(input,CALCULATEFLESCAPE,FOLLOW_CALCULATEFLESCAPE_in_calculateFLEscape5061); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculateFLEscape5065); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:535:1: flResolution returns [int value] : FLRESOLUTION a= FLOAT ;
    public final int flResolution() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:536:2: ( FLRESOLUTION a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:536:4: FLRESOLUTION a= FLOAT
            {
            match(input,FLRESOLUTION,FOLLOW_FLRESOLUTION_in_flResolution5176); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_flResolution5180); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:539:1: peResolution returns [int value] : PERESOLUTION a= FLOAT ;
    public final int peResolution() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:540:2: ( PERESOLUTION a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:540:4: PERESOLUTION a= FLOAT
            {
            match(input,PERESOLUTION,FOLLOW_PERESOLUTION_in_peResolution5262); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_peResolution5266); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:543:1: surroundingHeavyConc returns [List<String> names, List<Double> num;] : SURROUNDINGHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.surroundingHeavyConc_return surroundingHeavyConc() throws RecognitionException {
        InputfileParser.surroundingHeavyConc_return retval = new InputfileParser.surroundingHeavyConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:548:2: ( SURROUNDINGHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:548:4: SURROUNDINGHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SURROUNDINGHEAVYCONC,FOLLOW_SURROUNDINGHEAVYCONC_in_surroundingHeavyConc5352); 

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:548:25: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:548:26: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_surroundingHeavyConc5357); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_surroundingHeavyConc5361); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:551:1: oilBased returns [String value] : DENSITYBASED a= STRING ;
    public final String oilBased() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:552:2: ( DENSITYBASED a= STRING )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:552:4: DENSITYBASED a= STRING
            {
            match(input,DENSITYBASED,FOLLOW_DENSITYBASED_in_oilBased5489); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_oilBased5493); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:555:1: calcSurrounding returns [String value] : CALCSURROUNDING a= STRING ;
    public final String calcSurrounding() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:556:2: ( CALCSURROUNDING a= STRING )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:556:4: CALCSURROUNDING a= STRING
            {
            match(input,CALCSURROUNDING,FOLLOW_CALCSURROUNDING_in_calcSurrounding5575); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calcSurrounding5579); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:560:1: oilElements returns [List<String> names, List<Double> num;] : SURROUNDINGELEMENTS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.oilElements_return oilElements() throws RecognitionException {
        InputfileParser.oilElements_return retval = new InputfileParser.oilElements_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:565:2: ( SURROUNDINGELEMENTS (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:565:4: SURROUNDINGELEMENTS (a= ELEMENT b= FLOAT )+
            {
            match(input,SURROUNDINGELEMENTS,FOLLOW_SURROUNDINGELEMENTS_in_oilElements5682); 

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:565:24: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:565:25: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_oilElements5687); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_oilElements5691); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:568:1: oilDensity returns [double oildens] : SURROUNDINGDENSITY a= FLOAT ;
    public final double oilDensity() throws RecognitionException {
        double oildens = 0.0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:569:2: ( SURROUNDINGDENSITY a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:569:4: SURROUNDINGDENSITY a= FLOAT
            {
            match(input,SURROUNDINGDENSITY,FOLLOW_SURROUNDINGDENSITY_in_oilDensity5814); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_oilDensity5818); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:572:1: simElectrons returns [long simel] : ( SIMELECTRONS | SIMPHOTONS ) a= FLOAT ;
    public final long simElectrons() throws RecognitionException {
        long simel = 0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:573:2: ( ( SIMELECTRONS | SIMPHOTONS ) a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:573:4: ( SIMELECTRONS | SIMPHOTONS ) a= FLOAT
            {
            if ( (input.LA(1) >= SIMELECTRONS && input.LA(1) <= SIMPHOTONS) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_simElectrons5940); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:577:1: program returns [String value] : SUBPROGRAM a= STRING ;
    public final String program() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:578:2: ( SUBPROGRAM a= STRING )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:578:4: SUBPROGRAM a= STRING
            {
            match(input,SUBPROGRAM,FOLLOW_SUBPROGRAM_in_program6079); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_program6083); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:582:1: runs returns [int value] : RUNS a= FLOAT ;
    public final int runs() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:583:2: ( RUNS a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:583:4: RUNS a= FLOAT
            {
            match(input,RUNS,FOLLOW_RUNS_in_runs6159); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_runs6163); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:589:1: beam returns [Beam bObj] : BEAM ( beamLine )+ ;
    public final Beam beam() throws RecognitionException {
        beam_stack.push(new beam_scope());
        Beam bObj = null;


         
        		((beam_scope)beam_stack.peek()).beamProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:600:2: ( BEAM ( beamLine )+ )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:600:4: BEAM ( beamLine )+
            {
            match(input,BEAM,FOLLOW_BEAM_in_beam6226); 

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:600:9: ( beamLine )+
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
            	    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:600:9: beamLine
            	    {
            	    pushFollow(FOLLOW_beamLine_in_beam6228);
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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:603:1: beamLine : ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize |h= beamExposure |i= beamSemiAngle |j= beamApertureRadius |k= imageDimensions |l= pulseEnergy |m= energyFWHM );
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
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:604:2: ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize |h= beamExposure |i= beamSemiAngle |j= beamApertureRadius |k= imageDimensions |l= pulseEnergy |m= energyFWHM )
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
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:604:4: TYPE a= STRING
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_beamLine6267); 

                    a=(Token)match(input,STRING,FOLLOW_STRING_in_beamLine6271); 

                     ((beam_scope)beam_stack.peek()).beamType = (a!=null?a.getText():null); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:605:4: b= beamFlux
                    {
                    pushFollow(FOLLOW_beamFlux_in_beamLine6289);
                    b=beamFlux();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FLUX, b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:606:4: c= beamFWHM
                    {
                    pushFollow(FOLLOW_beamFWHM_in_beamLine6301);
                    c=beamFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_X, (c!=null?c.x:null)); 
                    	                           ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_Y, (c!=null?c.y:null)); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:608:4: d= beamEnergy
                    {
                    pushFollow(FOLLOW_beamEnergy_in_beamLine6313);
                    d=beamEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_ENERGY, d); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:609:4: e= beamCollimation
                    {
                    pushFollow(FOLLOW_beamCollimation_in_beamLine6325);
                    e=beamCollimation();

                    state._fsp--;


                     if (e != null) {
                    							    ((beam_scope)beam_stack.peek()).beamProperties.putAll(e);
                    							   } 

                    }
                    break;
                case 6 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:612:4: f= beamFile
                    {
                    pushFollow(FOLLOW_beamFile_in_beamLine6336);
                    f=beamFile();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXTFILE, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:613:4: g= beamPixelSize
                    {
                    pushFollow(FOLLOW_beamPixelSize_in_beamLine6357);
                    g=beamPixelSize();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.putAll(g); 

                    }
                    break;
                case 8 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:615:4: h= beamExposure
                    {
                    pushFollow(FOLLOW_beamExposure_in_beamLine6374);
                    h=beamExposure();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXPOSURE, h); 

                    }
                    break;
                case 9 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:616:4: i= beamSemiAngle
                    {
                    pushFollow(FOLLOW_beamSemiAngle_in_beamLine6386);
                    i=beamSemiAngle();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_SEMIANGLE, i);

                    }
                    break;
                case 10 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:617:4: j= beamApertureRadius
                    {
                    pushFollow(FOLLOW_beamApertureRadius_in_beamLine6395);
                    j=beamApertureRadius();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_APERTURERADIUS, j);

                    }
                    break;
                case 11 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:618:4: k= imageDimensions
                    {
                    pushFollow(FOLLOW_imageDimensions_in_beamLine6404);
                    k=imageDimensions();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.IMAGE_X, (k!=null?k.xImage:null)); 
                    	                           ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.IMAGE_Y, (k!=null?k.yImage:null)); 

                    }
                    break;
                case 12 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:620:4: l= pulseEnergy
                    {
                    pushFollow(FOLLOW_pulseEnergy_in_beamLine6414);
                    l=pulseEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.PULSE_ENERGY, l); 

                    }
                    break;
                case 13 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:621:4: m= energyFWHM
                    {
                    pushFollow(FOLLOW_energyFWHM_in_beamLine6426);
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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:625:1: beamFlux returns [Double flux] : FLUX a= FLOAT ;
    public final Double beamFlux() throws RecognitionException {
        Double flux = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:626:2: ( FLUX a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:626:4: FLUX a= FLOAT
            {
            match(input,FLUX,FOLLOW_FLUX_in_beamFlux6456); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFlux6460); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:629:1: beamExposure returns [Double exposure] : EXPOSURE a= FLOAT ;
    public final Double beamExposure() throws RecognitionException {
        Double exposure = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:630:2: ( EXPOSURE a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:630:4: EXPOSURE a= FLOAT
            {
            match(input,EXPOSURE,FOLLOW_EXPOSURE_in_beamExposure6502); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamExposure6506); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:633:1: imageDimensions returns [Double xImage, Double yImage] : IMAGEDIM a= FLOAT b= FLOAT ;
    public final InputfileParser.imageDimensions_return imageDimensions() throws RecognitionException {
        InputfileParser.imageDimensions_return retval = new InputfileParser.imageDimensions_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:634:2: ( IMAGEDIM a= FLOAT b= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:634:4: IMAGEDIM a= FLOAT b= FLOAT
            {
            match(input,IMAGEDIM,FOLLOW_IMAGEDIM_in_imageDimensions6568); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_imageDimensions6572); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_imageDimensions6576); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:638:1: beamFWHM returns [Double x, Double y] : FWHM a= FLOAT b= FLOAT ;
    public final InputfileParser.beamFWHM_return beamFWHM() throws RecognitionException {
        InputfileParser.beamFWHM_return retval = new InputfileParser.beamFWHM_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:639:2: ( FWHM a= FLOAT b= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:639:4: FWHM a= FLOAT b= FLOAT
            {
            match(input,FWHM,FOLLOW_FWHM_in_beamFWHM6641); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM6645); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM6649); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:642:1: beamEnergy returns [Double energy] : ENERGY a= FLOAT ( KEV )? ;
    public final Double beamEnergy() throws RecognitionException {
        Double energy = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:643:2: ( ENERGY a= FLOAT ( KEV )? )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:643:4: ENERGY a= FLOAT ( KEV )?
            {
            match(input,ENERGY,FOLLOW_ENERGY_in_beamEnergy6691); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamEnergy6695); 

            energy = Double.parseDouble((a!=null?a.getText():null));

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:644:2: ( KEV )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==KEV) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:644:4: KEV
                    {
                    match(input,KEV,FOLLOW_KEV_in_beamEnergy6702); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:649:1: pulseEnergy returns [Double pulse] : PULSEENERGY a= FLOAT ;
    public final Double pulseEnergy() throws RecognitionException {
        Double pulse = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:650:2: ( PULSEENERGY a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:650:4: PULSEENERGY a= FLOAT
            {
            match(input,PULSEENERGY,FOLLOW_PULSEENERGY_in_pulseEnergy6780); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_pulseEnergy6784); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:654:1: beamSemiAngle returns [Double semiAngle] : SEMIANGLE a= FLOAT ;
    public final Double beamSemiAngle() throws RecognitionException {
        Double semiAngle = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:655:2: ( SEMIANGLE a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:655:4: SEMIANGLE a= FLOAT
            {
            match(input,SEMIANGLE,FOLLOW_SEMIANGLE_in_beamSemiAngle6862); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamSemiAngle6866); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:659:1: beamApertureRadius returns [Double apertureRadius] : APERTURERADIUS a= FLOAT ;
    public final Double beamApertureRadius() throws RecognitionException {
        Double apertureRadius = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:660:2: ( APERTURERADIUS a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:660:4: APERTURERADIUS a= FLOAT
            {
            match(input,APERTURERADIUS,FOLLOW_APERTURERADIUS_in_beamApertureRadius6935); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamApertureRadius6939); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:664:1: beamFile returns [String filename] : FILE a= STRING ;
    public final String beamFile() throws RecognitionException {
        String filename = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:665:2: ( FILE a= STRING )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:665:4: FILE a= STRING
            {
            match(input,FILE,FOLLOW_FILE_in_beamFile7031); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_beamFile7035); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:669:1: beamPixelSize returns [Map<Object, Object> properties] : PIXELSIZE a= FLOAT b= FLOAT ;
    public final Map<Object, Object> beamPixelSize() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:670:5: ( PIXELSIZE a= FLOAT b= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:670:7: PIXELSIZE a= FLOAT b= FLOAT
            {
            match(input,PIXELSIZE,FOLLOW_PIXELSIZE_in_beamPixelSize7082); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize7086); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize7090); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:677:1: energyFWHM returns [Double eFWHM] : ENERGYFWHM a= FLOAT ;
    public final Double energyFWHM() throws RecognitionException {
        Double eFWHM = null;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:678:2: ( ENERGYFWHM a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:678:4: ENERGYFWHM a= FLOAT
            {
            match(input,ENERGYFWHM,FOLLOW_ENERGYFWHM_in_energyFWHM7165); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_energyFWHM7169); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:683:1: beamCollimation returns [Map<Object, Object> properties] : ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR c= FLOAT f= FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT );
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
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:687:2: ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR c= FLOAT f= FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT )
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
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:687:4: COLLIMATION
                    {
                    match(input,COLLIMATION,FOLLOW_COLLIMATION_in_beamCollimation7249); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:688:4: RECTANGULAR a= FLOAT b= FLOAT
                    {
                    match(input,RECTANGULAR,FOLLOW_RECTANGULAR_in_beamCollimation7255); 

                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7259); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7263); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((a!=null?a.getText():null)));
                    	                                properties.put(Beam.BEAM_COLL_V, Double.parseDouble((b!=null?b.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:690:4: CIRCULAR c= FLOAT f= FLOAT
                    {
                    match(input,CIRCULAR,FOLLOW_CIRCULAR_in_beamCollimation7270); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7274); 

                    f=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7278); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((c!=null?c.getText():null)));
                    	                                properties.put(Beam.BEAM_COLL_V, Double.parseDouble((f!=null?f.getText():null))); 
                    	                                properties.put(Beam.BEAM_CIRCULAR, "TRUE"); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:693:4: HORIZONTAL d= FLOAT
                    {
                    match(input,HORIZONTAL,FOLLOW_HORIZONTAL_in_beamCollimation7285); 

                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7289); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((d!=null?d.getText():null))); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:694:4: VERTICAL e= FLOAT
                    {
                    match(input,VERTICAL,FOLLOW_VERTICAL_in_beamCollimation7296); 

                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7300); 

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
        Double  maxRes;
    }
    protected Stack wedge_stack = new Stack();



    // $ANTLR start "wedge"
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:704:1: wedge returns [Wedge wObj] : WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ ;
    public final Wedge wedge() throws RecognitionException {
        wedge_stack.push(new wedge_scope());
        Wedge wObj = null;


        Token a=null;
        Token b=null;

         

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:724:2: ( WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:724:4: WEDGE a= FLOAT b= FLOAT ( wedgeLine )+
            {
            match(input,WEDGE,FOLLOW_WEDGE_in_wedge7613); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge7617); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge7621); 

            ((wedge_scope)wedge_stack.peek()).startAng = Double.parseDouble((a!=null?a.getText():null));
            							 ((wedge_scope)wedge_stack.peek()).endAng = Double.parseDouble((b!=null?b.getText():null)); 

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:726:4: ( wedgeLine )+
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
            	    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:726:4: wedgeLine
            	    {
            	    pushFollow(FOLLOW_wedgeLine_in_wedge7628);
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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:729:1: wedgeLine : (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset |f= wedgeMaxRes );
    public final void wedgeLine() throws RecognitionException {
        double a =0.0;

        double b =0.0;

        InputfileParser.wedgeStartOffset_return c =null;

        InputfileParser.wedgeTranslate_return d =null;

        double e =0.0;

        double f =0.0;


        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:730:2: (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset |f= wedgeMaxRes )
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
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:730:4: a= wedgeExposure
                    {
                    pushFollow(FOLLOW_wedgeExposure_in_wedgeLine7672);
                    a=wedgeExposure();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).expTime =a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:731:4: b= wedgeAngRes
                    {
                    pushFollow(FOLLOW_wedgeAngRes_in_wedgeLine7682);
                    b=wedgeAngRes();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).angRes =b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:732:4: c= wedgeStartOffset
                    {
                    pushFollow(FOLLOW_wedgeStartOffset_in_wedgeLine7693);
                    c=wedgeStartOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).offsetX =(c!=null?c.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetY =(c!=null?c.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetZ =(c!=null?c.z:null); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:735:4: d= wedgeTranslate
                    {
                    pushFollow(FOLLOW_wedgeTranslate_in_wedgeLine7703);
                    d=wedgeTranslate();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).translateX =(d!=null?d.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateY =(d!=null?d.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateZ =(d!=null?d.z:null); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:738:4: e= wedgeRotAxBeamOffset
                    {
                    pushFollow(FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine7713);
                    e=wedgeRotAxBeamOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).rotationOffset =e; 

                    }
                    break;
                case 6 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:739:4: f= wedgeMaxRes
                    {
                    pushFollow(FOLLOW_wedgeMaxRes_in_wedgeLine7722);
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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:742:1: wedgeExposure returns [double value] : EXPOSURETIME a= FLOAT ;
    public final double wedgeExposure() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:743:2: ( EXPOSURETIME a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:743:4: EXPOSURETIME a= FLOAT
            {
            match(input,EXPOSURETIME,FOLLOW_EXPOSURETIME_in_wedgeExposure7739); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeExposure7743); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:746:1: wedgeAngRes returns [double res] : ANGULARRESOLUTION a= FLOAT ;
    public final double wedgeAngRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:747:2: ( ANGULARRESOLUTION a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:747:4: ANGULARRESOLUTION a= FLOAT
            {
            match(input,ANGULARRESOLUTION,FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes7825); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeAngRes7829); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:750:1: wedgeStartOffset returns [Double x, Double y, Double z] : STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeStartOffset_return wedgeStartOffset() throws RecognitionException {
        InputfileParser.wedgeStartOffset_return retval = new InputfileParser.wedgeStartOffset_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:751:2: ( STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:751:4: STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,STARTOFFSET,FOLLOW_STARTOFFSET_in_wedgeStartOffset7936); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset7940); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset7944); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:752:17: (c= FLOAT )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==FLOAT) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:752:17: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset7965); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:756:1: wedgeTranslate returns [Double x, Double y, Double z] : TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeTranslate_return wedgeTranslate() throws RecognitionException {
        InputfileParser.wedgeTranslate_return retval = new InputfileParser.wedgeTranslate_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:757:2: ( TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:757:4: TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,TRANSLATEPERDEGREE,FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate8059); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate8063); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate8067); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:758:24: (c= FLOAT )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==FLOAT) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:758:24: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate8095); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:762:1: wedgeRotAxBeamOffset returns [double delta] : ROTAXBEAMOFFSET a= FLOAT ;
    public final double wedgeRotAxBeamOffset() throws RecognitionException {
        double delta = 0.0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:763:2: ( ROTAXBEAMOFFSET a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:763:4: ROTAXBEAMOFFSET a= FLOAT
            {
            match(input,ROTAXBEAMOFFSET,FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset8231); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeRotAxBeamOffset8235); 

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
    // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:766:1: wedgeMaxRes returns [double res] : MAXRESOLUTION a= FLOAT ;
    public final double wedgeMaxRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:767:2: ( MAXRESOLUTION a= FLOAT )
            // C:\\Users\\chess\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:767:4: MAXRESOLUTION a= FLOAT
            {
            match(input,MAXRESOLUTION,FOLLOW_MAXRESOLUTION_in_wedgeMaxRes8332); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeMaxRes8336); 

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


 

    public static final BitSet FOLLOW_crystal_in_configfile47 = new BitSet(new long[]{0x0000000001000400L,0x0000001000000000L});
    public static final BitSet FOLLOW_wedge_in_configfile65 = new BitSet(new long[]{0x0000000001000400L,0x0000001000000000L});
    public static final BitSet FOLLOW_beam_in_configfile85 = new BitSet(new long[]{0x0000000001000400L,0x0000001000000000L});
    public static final BitSet FOLLOW_EOF_in_configfile105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CRYSTAL_in_crystal134 = new BitSet(new long[]{0xBE9C120076F8B870L,0x00000026F9CE883DL});
    public static final BitSet FOLLOW_crystalLine_in_crystal136 = new BitSet(new long[]{0xBE9C120076F8B872L,0x00000026F9CE883DL});
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
    public static final BitSet FOLLOW_TYPE_in_crystalType774 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_crystalType778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalDDM820 = new BitSet(new long[]{0x0003000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_crystalDDMKeyword_in_crystalDDM832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIMPLE_in_crystalDDMKeyword980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LINEAR_in_crystalDDMKeyword987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEAL_in_crystalDDMKeyword994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECAYPARAM_in_crystalDecayParam1114 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam1118 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam1122 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam1126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSCOEFCALC_in_crystalCoefcalc1198 = new BitSet(new long[]{0x4000000088004200L,0x0000000000213180L});
    public static final BitSet FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc1202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DUMMY_in_crystalCoefcalcKeyword1281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDB_in_crystalCoefcalcKeyword1321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXS_in_crystalCoefcalcKeyword1331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SMALLMOLE_in_crystalCoefcalcKeyword1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIF_in_crystalCoefcalcKeyword1364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_crystalDim1773 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1786 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1790 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1806 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEP_in_crystalAngP1899 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngP1903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEL_in_crystalAngL1958 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngL1962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSPERMICRON_in_crystalPPM2016 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalPPM2018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNITCELL_in_unitcell2116 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2120 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2124 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2128 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2143 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2147 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SURROUNDINGTHICKNESS_in_surroundingThickness2233 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_surroundingThickness2243 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_surroundingThickness2247 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_surroundingThickness2251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_proteinConcentration2377 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_proteinConcentration2387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMMONOMERS_in_nummonomers2569 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_nummonomers2573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRESIDUES_in_numresidues2650 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numresidues2654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRNA_in_numRNA2732 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numRNA2736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMDNA_in_numDNA2789 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numDNA2793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMCARB_in_numcarb2845 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numcarb2849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2910 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavyProteinAtoms2915 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavyProteinAtoms2919 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_SMALLMOLEATOMS_in_smallMoleAtoms3066 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ELEMENT_in_smallMoleAtoms3071 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_smallMoleAtoms3075 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc3177 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavySolutionConc3182 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavySolutionConc3186 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_SOLVENTFRACTION_in_solventFraction3292 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_solventFraction3296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDBNAME_in_pdb3393 = new BitSet(new long[]{0x0000010000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_pdb3398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_pdb3402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIFNAME_in_cif3440 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_cif3444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WIREFRAMETYPE_in_wireframeType3481 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_wireframeType3485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODELFILE_in_modelFile3573 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_modelFile3577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEPEESCAPE_in_calculatePEEscape3644 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_calculatePEEscape3648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GONIOMETERAXIS_in_goniometerAxis3760 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_goniometerAxis3764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalContainerMaterial3861 = new BitSet(new long[]{0x0140000200000000L});
    public static final BitSet FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial3873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NONE_in_crystalContainerKeyword4066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIXTURE_in_crystalContainerKeyword4075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELEMENTAL_in_crystalContainerKeyword4083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINERTHICKNESS_in_containerThickness4223 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerThickness4227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialMixture4338 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_containerMaterialMixture4348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialElements4573 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ELEMENT_in_containerMaterialElements4584 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerMaterialElements4588 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_CONTAINERDENSITY_in_containerDensity4823 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerDensity4827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sequenceFile4928 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_sequenceFile4938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEFLESCAPE_in_calculateFLEscape5061 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_calculateFLEscape5065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLRESOLUTION_in_flResolution5176 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_flResolution5180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERESOLUTION_in_peResolution5262 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_peResolution5266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SURROUNDINGHEAVYCONC_in_surroundingHeavyConc5352 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ELEMENT_in_surroundingHeavyConc5357 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_surroundingHeavyConc5361 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_DENSITYBASED_in_oilBased5489 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_oilBased5493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCSURROUNDING_in_calcSurrounding5575 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_calcSurrounding5579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SURROUNDINGELEMENTS_in_oilElements5682 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ELEMENT_in_oilElements5687 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_oilElements5691 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_SURROUNDINGDENSITY_in_oilDensity5814 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_oilDensity5818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_simElectrons5930 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_simElectrons5940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBPROGRAM_in_program6079 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_program6083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RUNS_in_runs6159 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_runs6163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEAM_in_beam6226 = new BitSet(new long[]{0x00006CAC00030100L,0x0000000A00004242L});
    public static final BitSet FOLLOW_beamLine_in_beam6228 = new BitSet(new long[]{0x00006CAC00030102L,0x0000000A00004242L});
    public static final BitSet FOLLOW_TYPE_in_beamLine6267 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_beamLine6271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFlux_in_beamLine6289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFWHM_in_beamLine6301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamEnergy_in_beamLine6313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamCollimation_in_beamLine6325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFile_in_beamLine6336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamPixelSize_in_beamLine6357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamExposure_in_beamLine6374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamSemiAngle_in_beamLine6386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamApertureRadius_in_beamLine6395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_imageDimensions_in_beamLine6404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pulseEnergy_in_beamLine6414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_energyFWHM_in_beamLine6426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLUX_in_beamFlux6456 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFlux6460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURE_in_beamExposure6502 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamExposure6506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMAGEDIM_in_imageDimensions6568 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_imageDimensions6572 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_imageDimensions6576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FWHM_in_beamFWHM6641 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM6645 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM6649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGY_in_beamEnergy6691 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamEnergy6695 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_KEV_in_beamEnergy6702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PULSEENERGY_in_pulseEnergy6780 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_pulseEnergy6784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMIANGLE_in_beamSemiAngle6862 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamSemiAngle6866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_APERTURERADIUS_in_beamApertureRadius6935 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamApertureRadius6939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_beamFile7031 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_STRING_in_beamFile7035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSIZE_in_beamPixelSize7082 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize7086 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize7090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGYFWHM_in_energyFWHM7165 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_energyFWHM7169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLIMATION_in_beamCollimation7249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RECTANGULAR_in_beamCollimation7255 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7259 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCULAR_in_beamCollimation7270 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7274 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HORIZONTAL_in_beamCollimation7285 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERTICAL_in_beamCollimation7296 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WEDGE_in_wedge7613 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge7617 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge7621 = new BitSet(new long[]{0x0020004000000080L,0x0000000102000400L});
    public static final BitSet FOLLOW_wedgeLine_in_wedge7628 = new BitSet(new long[]{0x0020004000000082L,0x0000000102000400L});
    public static final BitSet FOLLOW_wedgeExposure_in_wedgeLine7672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeAngRes_in_wedgeLine7682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeStartOffset_in_wedgeLine7693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeTranslate_in_wedgeLine7703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine7713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeMaxRes_in_wedgeLine7722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURETIME_in_wedgeExposure7739 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeExposure7743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes7825 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeAngRes7829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTOFFSET_in_wedgeStartOffset7936 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset7940 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset7944 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset7965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate8059 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate8063 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate8067 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate8095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset8231 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeRotAxBeamOffset8235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MAXRESOLUTION_in_wedgeMaxRes8332 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeMaxRes8336 = new BitSet(new long[]{0x0000000000000002L});

}