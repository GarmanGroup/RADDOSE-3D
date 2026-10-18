// $ANTLR 3.4 Inputfile.g 2026-10-18 00:03:10

package se.raddo.raddose3D.parser;
import se.raddo.raddose3D.*;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/** "Here's an initializer, here's an input file. Good luck and God's Speed." */
@SuppressWarnings({"all", "warnings", "unchecked"})
public class InputfileParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABSCOEFCALC", "ANGLEL", "ANGLEP", "ANGULARRESOLUTION", "APERTURERADIUS", "AVERAGE", "BEAM", "BFACTOR", "CALCSURROUNDING", "CALCULATEFLESCAPE", "CALCULATEPEESCAPE", "CIF", "CIFNAME", "CIRCULAR", "COLLIMATION", "COMMENT", "CONTAINERDENSITY", "CONTAINERMATERIALELEMENTS", "CONTAINERMATERIALMIXTURE", "CONTAINERMATERIALTYPE", "CONTAINERTHICKNESS", "CRYSTAL", "DDM", "DECAYPARAM", "DEFAULT", "DENSITYBASED", "DIFFRACTIONDECAYMODEL", "DIMENSION", "DUMMY", "ELEMENT", "ELEMENTAL", "ENERGY", "ENERGYFWHM", "EXPONENT", "EXPOSURE", "EXPOSURETIME", "FILE", "FLOAT", "FLRESOLUTION", "FLUX", "FWHM", "GONIOMETERAXIS", "HORIZONTAL", "IMAGEDIM", "KEV", "LEAL", "LINEAR", "MATERIALELEMENTS", "MATERIALMIXTURE", "MATERIALTYPE", "MAXRESOLUTION", "MIXTURE", "MODELFILE", "NONE", "NUMCARB", "NUMDNA", "NUMMONOMERS", "NUMRESIDUES", "NUMRNA", "PDB", "PDBNAME", "PERESOLUTION", "PIXELSIZE", "PIXELSPERMICRON", "POLARISATIONDIRECTION", "PROTEINCONC", "PROTEINCONCENTRATION", "PROTEINHEAVYATOMS", "PULSEENERGY", "PULSESHAPE", "PULSEWINDOW", "RDFORTAN", "RDJAVA", "RECTANGULAR", "ROTAXBEAMOFFSET", "RUNS", "SAXS", "SAXSSEQ", "SEMIANGLE", "SEQFILE", "SEQUENCE", "SEQUENCEFILE", "SIMELECTRONS", "SIMPHOTONS", "SIMPLE", "SMALLMOLE", "SMALLMOLEATOMS", "SOLVENTFRACTION", "SOLVENTHEAVYCONC", "STARTOFFSET", "STRING", "SUBPROGRAM", "SURROUNDINGDENSITY", "SURROUNDINGELEMENTS", "SURROUNDINGHEAVYCONC", "SURROUNDINGTHICKNESS", "TRANSLATEPERDEGREE", "TYPE", "UNITCELL", "VERTICAL", "WEDGE", "WIREFRAMETYPE", "WS"
    };

    public static final int EOF=-1;
    public static final int ABSCOEFCALC=4;
    public static final int ANGLEL=5;
    public static final int ANGLEP=6;
    public static final int ANGULARRESOLUTION=7;
    public static final int APERTURERADIUS=8;
    public static final int AVERAGE=9;
    public static final int BEAM=10;
    public static final int BFACTOR=11;
    public static final int CALCSURROUNDING=12;
    public static final int CALCULATEFLESCAPE=13;
    public static final int CALCULATEPEESCAPE=14;
    public static final int CIF=15;
    public static final int CIFNAME=16;
    public static final int CIRCULAR=17;
    public static final int COLLIMATION=18;
    public static final int COMMENT=19;
    public static final int CONTAINERDENSITY=20;
    public static final int CONTAINERMATERIALELEMENTS=21;
    public static final int CONTAINERMATERIALMIXTURE=22;
    public static final int CONTAINERMATERIALTYPE=23;
    public static final int CONTAINERTHICKNESS=24;
    public static final int CRYSTAL=25;
    public static final int DDM=26;
    public static final int DECAYPARAM=27;
    public static final int DEFAULT=28;
    public static final int DENSITYBASED=29;
    public static final int DIFFRACTIONDECAYMODEL=30;
    public static final int DIMENSION=31;
    public static final int DUMMY=32;
    public static final int ELEMENT=33;
    public static final int ELEMENTAL=34;
    public static final int ENERGY=35;
    public static final int ENERGYFWHM=36;
    public static final int EXPONENT=37;
    public static final int EXPOSURE=38;
    public static final int EXPOSURETIME=39;
    public static final int FILE=40;
    public static final int FLOAT=41;
    public static final int FLRESOLUTION=42;
    public static final int FLUX=43;
    public static final int FWHM=44;
    public static final int GONIOMETERAXIS=45;
    public static final int HORIZONTAL=46;
    public static final int IMAGEDIM=47;
    public static final int KEV=48;
    public static final int LEAL=49;
    public static final int LINEAR=50;
    public static final int MATERIALELEMENTS=51;
    public static final int MATERIALMIXTURE=52;
    public static final int MATERIALTYPE=53;
    public static final int MAXRESOLUTION=54;
    public static final int MIXTURE=55;
    public static final int MODELFILE=56;
    public static final int NONE=57;
    public static final int NUMCARB=58;
    public static final int NUMDNA=59;
    public static final int NUMMONOMERS=60;
    public static final int NUMRESIDUES=61;
    public static final int NUMRNA=62;
    public static final int PDB=63;
    public static final int PDBNAME=64;
    public static final int PERESOLUTION=65;
    public static final int PIXELSIZE=66;
    public static final int PIXELSPERMICRON=67;
    public static final int POLARISATIONDIRECTION=68;
    public static final int PROTEINCONC=69;
    public static final int PROTEINCONCENTRATION=70;
    public static final int PROTEINHEAVYATOMS=71;
    public static final int PULSEENERGY=72;
    public static final int PULSESHAPE=73;
    public static final int PULSEWINDOW=74;
    public static final int RDFORTAN=75;
    public static final int RDJAVA=76;
    public static final int RECTANGULAR=77;
    public static final int ROTAXBEAMOFFSET=78;
    public static final int RUNS=79;
    public static final int SAXS=80;
    public static final int SAXSSEQ=81;
    public static final int SEMIANGLE=82;
    public static final int SEQFILE=83;
    public static final int SEQUENCE=84;
    public static final int SEQUENCEFILE=85;
    public static final int SIMELECTRONS=86;
    public static final int SIMPHOTONS=87;
    public static final int SIMPLE=88;
    public static final int SMALLMOLE=89;
    public static final int SMALLMOLEATOMS=90;
    public static final int SOLVENTFRACTION=91;
    public static final int SOLVENTHEAVYCONC=92;
    public static final int STARTOFFSET=93;
    public static final int STRING=94;
    public static final int SUBPROGRAM=95;
    public static final int SURROUNDINGDENSITY=96;
    public static final int SURROUNDINGELEMENTS=97;
    public static final int SURROUNDINGHEAVYCONC=98;
    public static final int SURROUNDINGTHICKNESS=99;
    public static final int TRANSLATEPERDEGREE=100;
    public static final int TYPE=101;
    public static final int UNITCELL=102;
    public static final int VERTICAL=103;
    public static final int WEDGE=104;
    public static final int WIREFRAMETYPE=105;
    public static final int WS=106;

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
    public String getGrammarFileName() { return "Inputfile.g"; }


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

        /**
         * A whole number, in any notation the lexer reads as a FLOAT: 1000000,
         * 1e6 or 1000000.0. Long.parseLong took only the first, and its
         * NumberFormatException escaped the parser and ended the run.
         */
        private long wholeNumber(Token t, String keyword, long max) {
            try {
                long value = new BigDecimal(t.getText()).longValueExact();
                if (value >= 1 && value <= max) {
                    return value;
                }
            } catch (ArithmeticException e) {
                // a fraction, or too large for a long
            }
            emitErrorMessage("line " + t.getLine() + ": " + keyword
                + " needs a whole number from 1 to " + max + ", not " + t.getText());
            return 1;
        }



    // $ANTLR start "configfile"
    // Inputfile.g:79:1: configfile : (a= crystal |b= wedge |c= beam )* EOF ;
    public final void configfile() throws RecognitionException {
        Crystal a =null;

        Wedge b =null;

        Beam c =null;


        try {
            // Inputfile.g:79:11: ( (a= crystal |b= wedge |c= beam )* EOF )
            // Inputfile.g:80:11: (a= crystal |b= wedge |c= beam )* EOF
            {
            // Inputfile.g:80:11: (a= crystal |b= wedge |c= beam )*
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
            	    // Inputfile.g:80:13: a= crystal
            	    {
            	    pushFollow(FOLLOW_crystal_in_configfile56);
            	    a=crystal();

            	    state._fsp--;


            	     raddoseInitializer.setCrystal(a); 

            	    }
            	    break;
            	case 2 :
            	    // Inputfile.g:81:13: b= wedge
            	    {
            	    pushFollow(FOLLOW_wedge_in_configfile74);
            	    b=wedge();

            	    state._fsp--;


            	     raddoseInitializer.exposeWedge(b); 

            	    }
            	    break;
            	case 3 :
            	    // Inputfile.g:82:13: c= beam
            	    {
            	    pushFollow(FOLLOW_beam_in_configfile94);
            	    c=beam();

            	    state._fsp--;


            	     raddoseInitializer.setBeam(c); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            match(input,EOF,FOLLOW_EOF_in_configfile114); 

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
    // Inputfile.g:87:1: crystal returns [Crystal cObj] : CRYSTAL ( crystalLine )+ ;
    public final Crystal crystal() throws RecognitionException {
        crystal_stack.push(new crystal_scope());
        Crystal cObj = null;


         
        ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = 2; // 0 = error, 1 = Simple, 2 = DEFAULT, 3 = RDV2, 4 = PDB, 5 = SAXS
        		((crystal_scope)crystal_stack.peek()).crystalProperties = new HashMap<Object, Object>();

        try {
            // Inputfile.g:320:2: ( CRYSTAL ( crystalLine )+ )
            // Inputfile.g:320:4: CRYSTAL ( crystalLine )+
            {
            match(input,CRYSTAL,FOLLOW_CRYSTAL_in_crystal143); 

            // Inputfile.g:320:12: ( crystalLine )+
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
            	    // Inputfile.g:320:12: crystalLine
            	    {
            	    pushFollow(FOLLOW_crystalLine_in_crystal145);
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
                  if (((crystal_scope)crystal_stack.peek()).containerDensity != null){
                     ((crystal_scope)crystal_stack.peek()).oilDensity = ((crystal_scope)crystal_stack.peek()).containerDensity ;
                  }
                  else{
                     ((crystal_scope)crystal_stack.peek()).oilDensity = 0.0 ;
                  }
                  if (((crystal_scope)crystal_stack.peek()).oilNames == null){
                     if (((crystal_scope)crystal_stack.peek()).containerElementNames != null){
                        ((crystal_scope)crystal_stack.peek()).oilNames = ((crystal_scope)crystal_stack.peek()).containerElementNames;
                        ((crystal_scope)crystal_stack.peek()).oilNums = ((crystal_scope)crystal_stack.peek()).containerElementNums;
                  }
               }
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcSAXS(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
              													((crystal_scope)crystal_stack.peek()).numRes, ((crystal_scope)crystal_stack.peek()).numRNA, ((crystal_scope)crystal_stack.peek()).numDNA,
              													((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
              													((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
              													((crystal_scope)crystal_stack.peek()).solFrac, ((crystal_scope)crystal_stack.peek()).proteinConc, 
              													((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule, ((crystal_scope)crystal_stack.peek()).cryoSolutionConc,
              													((crystal_scope)crystal_stack.peek()).oilBased, 	((crystal_scope)crystal_stack.peek()).calcSurrounding,
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
              													((crystal_scope)crystal_stack.peek()).oilBased, 	((crystal_scope)crystal_stack.peek()).calcSurrounding,
              													((crystal_scope)crystal_stack.peek()).numCarb, ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums, ((crystal_scope)crystal_stack.peek()).oilDensity);
            }

            if (((crystal_scope)crystal_stack.peek()).crystalCoefCalc == 7)
            {
               if (((crystal_scope)crystal_stack.peek()).oilDensity == null){
                  if (((crystal_scope)crystal_stack.peek()).containerDensity != null){
                     ((crystal_scope)crystal_stack.peek()).oilDensity = ((crystal_scope)crystal_stack.peek()).containerDensity ;
                  }
                  else{
                     ((crystal_scope)crystal_stack.peek()).oilDensity = 0.0 ;
                  }
               }
               if (((crystal_scope)crystal_stack.peek()).oilNames == null){
                  if (((crystal_scope)crystal_stack.peek()).containerElementNames != null){
                     ((crystal_scope)crystal_stack.peek()).oilNames = ((crystal_scope)crystal_stack.peek()).containerElementNames;
                     ((crystal_scope)crystal_stack.peek()).oilNums = ((crystal_scope)crystal_stack.peek()).containerElementNums;
                  }
               }
               
              ((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromSequenceSAXS(((crystal_scope)crystal_stack.peek()).cellA, ((crystal_scope)crystal_stack.peek()).cellB, ((crystal_scope)crystal_stack.peek()).cellC, ((crystal_scope)crystal_stack.peek()).cellAl, ((crystal_scope)crystal_stack.peek()).cellBe, ((crystal_scope)crystal_stack.peek()).cellGa,
              													((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames, ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums,
              													((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums,
              													((crystal_scope)crystal_stack.peek()).solFrac, ((crystal_scope)crystal_stack.peek()).proteinConc, ((crystal_scope)crystal_stack.peek()).seqFile, 		
              													((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule, ((crystal_scope)crystal_stack.peek()).cryoSolutionConc,					
              													((crystal_scope)crystal_stack.peek()).oilBased, 	((crystal_scope)crystal_stack.peek()).calcSurrounding,
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
              													((crystal_scope)crystal_stack.peek()).oilBased, 	((crystal_scope)crystal_stack.peek()).calcSurrounding,
              													((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums, ((crystal_scope)crystal_stack.peek()).oilDensity, ((crystal_scope)crystal_stack.peek()).simElectrons);
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

            if (((crystal_scope)crystal_stack.peek()).crystalDdm == 4)
            {
            	((crystal_scope)crystal_stack.peek()).crystalDdmClass = new DDMBfactor(((crystal_scope)crystal_stack.peek()).gammaParam, ((crystal_scope)crystal_stack.peek()).b0Param, ((crystal_scope)crystal_stack.peek()).betaParam);
            }

            ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_DDM, ((crystal_scope)crystal_stack.peek()).crystalDdmClass);

            if (((crystal_scope)crystal_stack.peek()).crystalContainerMaterial == 1)
            {
            	((crystal_scope)crystal_stack.peek()).crystalContainerMaterialClass = new ContainerTransparent();
            }

            if (((crystal_scope)crystal_stack.peek()).crystalContainerMaterial == 2)
            {
            	// A material NIST has no table for is refused here, before any dose.
            	String containerProblem = ContainerMixture.problem(((crystal_scope)crystal_stack.peek()).containerMixture);
            	if (containerProblem != null) {
            		emitErrorMessage(containerProblem);
            	}
            	((crystal_scope)crystal_stack.peek()).crystalContainerMaterialClass = new ContainerMixture(((crystal_scope)crystal_stack.peek()).containerThickness, ((crystal_scope)crystal_stack.peek()).containerDensity, ((crystal_scope)crystal_stack.peek()).containerMixture);
            }

            if (((crystal_scope)crystal_stack.peek()).crystalContainerMaterial == 3)
            {
            	String containerProblem = ContainerElemental.problem(((crystal_scope)crystal_stack.peek()).containerElementNames);
            	if (containerProblem != null) {
            		emitErrorMessage(containerProblem);
            	}
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
    // Inputfile.g:323:1: crystalLine : (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |qa= numcarb |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculatePEEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile |ab= smallMoleAtoms |ac= cif |bb= calculateFLEscape |cc= flResolution |dd= peResolution |ee= surroundingHeavyConc |ff= oilBased |gg= goniometerAxis |hh= calcSurrounding |ii= oilElements |jj= oilDensity |kk= program |ll= simElectrons |mm= runs |nn= surroundingThickness |pp= polarisationDirection );
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
            // Inputfile.g:324:2: (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |qa= numcarb |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculatePEEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile |ab= smallMoleAtoms |ac= cif |bb= calculateFLEscape |cc= flResolution |dd= peResolution |ee= surroundingHeavyConc |ff= oilBased |gg= goniometerAxis |hh= calcSurrounding |ii= oilElements |jj= oilDensity |kk= program |ll= simElectrons |mm= runs |nn= surroundingThickness |pp= polarisationDirection )
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
                    // Inputfile.g:324:4: a= crystalType
                    {
                    pushFollow(FOLLOW_crystalType_in_crystalLine201);
                    a=crystalType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalType = a; 

                    }
                    break;
                case 2 :
                    // Inputfile.g:325:4: b= crystalDDM
                    {
                    pushFollow(FOLLOW_crystalDDM_in_crystalLine212);
                    b=crystalDDM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalDdm = b; 

                    }
                    break;
                case 3 :
                    // Inputfile.g:326:4: c= crystalCoefcalc
                    {
                    pushFollow(FOLLOW_crystalCoefcalc_in_crystalLine224);
                    c=crystalCoefcalc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = c; 

                    }
                    break;
                case 4 :
                    // Inputfile.g:327:4: d= crystalDim
                    {
                    pushFollow(FOLLOW_crystalDim_in_crystalLine234);
                    d=crystalDim();

                    state._fsp--;


                     if (d != null) {
                    							   ((crystal_scope)crystal_stack.peek()).crystalProperties.putAll(d);
                    							  }; 

                    }
                    break;
                case 5 :
                    // Inputfile.g:330:4: e= crystalPPM
                    {
                    pushFollow(FOLLOW_crystalPPM_in_crystalLine245);
                    e=crystalPPM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, e); 

                    }
                    break;
                case 6 :
                    // Inputfile.g:331:4: f= crystalAngP
                    {
                    pushFollow(FOLLOW_crystalAngP_in_crystalLine256);
                    f=crystalAngP();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, f); 

                    }
                    break;
                case 7 :
                    // Inputfile.g:332:4: g= crystalAngL
                    {
                    pushFollow(FOLLOW_crystalAngL_in_crystalLine267);
                    g=crystalAngL();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, g); 

                    }
                    break;
                case 8 :
                    // Inputfile.g:333:4: h= crystalDecayParam
                    {
                    pushFollow(FOLLOW_crystalDecayParam_in_crystalLine278);
                    h=crystalDecayParam();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).gammaParam = (h!=null?h.gammaParam:null); 
                    	                           			  ((crystal_scope)crystal_stack.peek()).b0Param = (h!=null?h.b0Param:null); 
                    	                           			  ((crystal_scope)crystal_stack.peek()).betaParam = (h!=null?h.betaParam:null); 

                    }
                    break;
                case 9 :
                    // Inputfile.g:336:4: i= containerThickness
                    {
                    pushFollow(FOLLOW_containerThickness_in_crystalLine288);
                    i=containerThickness();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerThickness = i; 

                    }
                    break;
                case 10 :
                    // Inputfile.g:337:4: j= containerDensity
                    {
                    pushFollow(FOLLOW_containerDensity_in_crystalLine298);
                    j=containerDensity();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerDensity = j; 

                    }
                    break;
                case 11 :
                    // Inputfile.g:338:4: k= crystalContainerMaterial
                    {
                    pushFollow(FOLLOW_crystalContainerMaterial_in_crystalLine308);
                    k=crystalContainerMaterial();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalContainerMaterial = k; 

                    }
                    break;
                case 12 :
                    // Inputfile.g:339:4: l= containerMaterialMixture
                    {
                    pushFollow(FOLLOW_containerMaterialMixture_in_crystalLine317);
                    l=containerMaterialMixture();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerMixture = l; 

                    }
                    break;
                case 13 :
                    // Inputfile.g:340:4: m= unitcell
                    {
                    pushFollow(FOLLOW_unitcell_in_crystalLine326);
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
                    // Inputfile.g:346:4: n= nummonomers
                    {
                    pushFollow(FOLLOW_nummonomers_in_crystalLine337);
                    n=nummonomers();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numMon = n;	

                    }
                    break;
                case 15 :
                    // Inputfile.g:347:4: o= numresidues
                    {
                    pushFollow(FOLLOW_numresidues_in_crystalLine348);
                    o=numresidues();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRes = o;	

                    }
                    break;
                case 16 :
                    // Inputfile.g:348:4: p= numRNA
                    {
                    pushFollow(FOLLOW_numRNA_in_crystalLine359);
                    p=numRNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRNA = p;	

                    }
                    break;
                case 17 :
                    // Inputfile.g:349:4: q= numDNA
                    {
                    pushFollow(FOLLOW_numDNA_in_crystalLine372);
                    q=numDNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numDNA = q;	

                    }
                    break;
                case 18 :
                    // Inputfile.g:350:4: qa= numcarb
                    {
                    pushFollow(FOLLOW_numcarb_in_crystalLine385);
                    qa=numcarb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numCarb = qa;	

                    }
                    break;
                case 19 :
                    // Inputfile.g:351:4: r= heavyProteinAtoms
                    {
                    pushFollow(FOLLOW_heavyProteinAtoms_in_crystalLine398);
                    r=heavyProteinAtoms();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames = (r!=null?r.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums = (r!=null?r.num:null);	

                    }
                    break;
                case 20 :
                    // Inputfile.g:353:4: s= heavySolutionConc
                    {
                    pushFollow(FOLLOW_heavySolutionConc_in_crystalLine407);
                    s=heavySolutionConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames = (s!=null?s.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums = (s!=null?s.num:null);	

                    }
                    break;
                case 21 :
                    // Inputfile.g:355:4: t= solventFraction
                    {
                    pushFollow(FOLLOW_solventFraction_in_crystalLine416);
                    t=solventFraction();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).solFrac = t; 

                    }
                    break;
                case 22 :
                    // Inputfile.g:356:4: u= pdb
                    {
                    pushFollow(FOLLOW_pdb_in_crystalLine426);
                    u=pdb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).pdb = u; 

                    }
                    break;
                case 23 :
                    // Inputfile.g:357:4: v= wireframeType
                    {
                    pushFollow(FOLLOW_wireframeType_in_crystalLine439);
                    v=wireframeType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_TYPE, v); 

                    }
                    break;
                case 24 :
                    // Inputfile.g:358:4: w= modelFile
                    {
                    pushFollow(FOLLOW_modelFile_in_crystalLine450);
                    w=modelFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_FILE, w); 

                    }
                    break;
                case 25 :
                    // Inputfile.g:359:4: x= calculatePEEscape
                    {
                    pushFollow(FOLLOW_calculatePEEscape_in_crystalLine462);
                    x=calculatePEEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ELECTRON_ESCAPE, x); 

                    }
                    break;
                case 26 :
                    // Inputfile.g:360:4: y= proteinConcentration
                    {
                    pushFollow(FOLLOW_proteinConcentration_in_crystalLine472);
                    y=proteinConcentration();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).proteinConc = y;

                    }
                    break;
                case 27 :
                    // Inputfile.g:361:4: z= containerMaterialElements
                    {
                    pushFollow(FOLLOW_containerMaterialElements_in_crystalLine481);
                    z=containerMaterialElements();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerElementNames = (z!=null?z.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).containerElementNums = (z!=null?z.num:null);	

                    }
                    break;
                case 28 :
                    // Inputfile.g:363:4: aa= sequenceFile
                    {
                    pushFollow(FOLLOW_sequenceFile_in_crystalLine490);
                    aa=sequenceFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).seqFile = aa; 

                    }
                    break;
                case 29 :
                    // Inputfile.g:364:4: ab= smallMoleAtoms
                    {
                    pushFollow(FOLLOW_smallMoleAtoms_in_crystalLine501);
                    ab=smallMoleAtoms();

                    state._fsp--;


                    ((crystal_scope)crystal_stack.peek()).smallMoleAtomNames = (ab!=null?ab.names:null);
                    							((crystal_scope)crystal_stack.peek()).smallMoleAtomNums = (ab!=null?ab.num:null);	

                    }
                    break;
                case 30 :
                    // Inputfile.g:366:4: ac= cif
                    {
                    pushFollow(FOLLOW_cif_in_crystalLine522);
                    ac=cif();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).cif = ac; 

                    }
                    break;
                case 31 :
                    // Inputfile.g:368:4: bb= calculateFLEscape
                    {
                    pushFollow(FOLLOW_calculateFLEscape_in_crystalLine536);
                    bb=calculateFLEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_FLUORESCENT_ESCAPE, bb); 

                    }
                    break;
                case 32 :
                    // Inputfile.g:369:4: cc= flResolution
                    {
                    pushFollow(FOLLOW_flResolution_in_crystalLine546);
                    cc=flResolution();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_FLUORESCENT_RESOLUTION, cc);

                    }
                    break;
                case 33 :
                    // Inputfile.g:370:4: dd= peResolution
                    {
                    pushFollow(FOLLOW_peResolution_in_crystalLine557);
                    dd=peResolution();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_PHOTOELECTRON_RESOLUTION, dd);

                    }
                    break;
                case 34 :
                    // Inputfile.g:372:4: ee= surroundingHeavyConc
                    {
                    pushFollow(FOLLOW_surroundingHeavyConc_in_crystalLine569);
                    ee=surroundingHeavyConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule = (ee!=null?ee.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).cryoSolutionConc = (ee!=null?ee.num:null);	

                    }
                    break;
                case 35 :
                    // Inputfile.g:374:4: ff= oilBased
                    {
                    pushFollow(FOLLOW_oilBased_in_crystalLine586);
                    ff=oilBased();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).oilBased = ff;  

                    }
                    break;
                case 36 :
                    // Inputfile.g:375:4: gg= goniometerAxis
                    {
                    pushFollow(FOLLOW_goniometerAxis_in_crystalLine611);
                    gg=goniometerAxis();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_GONIOMETER_AXIS, gg); 

                    }
                    break;
                case 37 :
                    // Inputfile.g:376:4: hh= calcSurrounding
                    {
                    pushFollow(FOLLOW_calcSurrounding_in_crystalLine621);
                    hh=calcSurrounding();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).calcSurrounding = hh;  

                    }
                    break;
                case 38 :
                    // Inputfile.g:377:4: ii= oilElements
                    {
                    pushFollow(FOLLOW_oilElements_in_crystalLine645);
                    ii=oilElements();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).oilNames = (ii!=null?ii.names:null);  
                    		                	         ((crystal_scope)crystal_stack.peek()).oilNums = (ii!=null?ii.num:null);  

                    }
                    break;
                case 39 :
                    // Inputfile.g:379:4: jj= oilDensity
                    {
                    pushFollow(FOLLOW_oilDensity_in_crystalLine669);
                    jj=oilDensity();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).oilDensity = jj;  

                    }
                    break;
                case 40 :
                    // Inputfile.g:380:4: kk= program
                    {
                    pushFollow(FOLLOW_program_in_crystalLine694);
                    kk=program();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_PROGRAM, kk); 

                    }
                    break;
                case 41 :
                    // Inputfile.g:381:4: ll= simElectrons
                    {
                    pushFollow(FOLLOW_simElectrons_in_crystalLine719);
                    ll=simElectrons();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).simElectrons = ll; 

                    }
                    break;
                case 42 :
                    // Inputfile.g:382:4: mm= runs
                    {
                    pushFollow(FOLLOW_runs_in_crystalLine729);
                    mm=runs();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RUNS, mm); 

                    }
                    break;
                case 43 :
                    // Inputfile.g:383:4: nn= surroundingThickness
                    {
                    pushFollow(FOLLOW_surroundingThickness_in_crystalLine754);
                    nn=surroundingThickness();

                    state._fsp--;


                     if (nn != null) {
                    							   ((crystal_scope)crystal_stack.peek()).crystalProperties.putAll(nn);
                    							  }; 

                    }
                    break;
                case 44 :
                    // Inputfile.g:386:11: pp= polarisationDirection
                    {
                    pushFollow(FOLLOW_polarisationDirection_in_crystalLine772);
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
    // Inputfile.g:391:1: crystalType returns [String crystalType] : TYPE e= STRING ;
    public final String crystalType() throws RecognitionException {
        String crystalType = null;


        Token e=null;

        try {
            // Inputfile.g:392:2: ( TYPE e= STRING )
            // Inputfile.g:392:4: TYPE e= STRING
            {
            match(input,TYPE,FOLLOW_TYPE_in_crystalType805); 

            e=(Token)match(input,STRING,FOLLOW_STRING_in_crystalType809); 

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
    // Inputfile.g:395:1: crystalDDM returns [int value] : ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword ;
    public final int crystalDDM() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // Inputfile.g:396:2: ( ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword )
            // Inputfile.g:396:4: ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword
            {
            if ( input.LA(1)==DDM||input.LA(1)==DIFFRACTIONDECAYMODEL ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalDDMKeyword_in_crystalDDM863);
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
    // Inputfile.g:399:1: crystalDDMKeyword returns [int value] : ( SIMPLE | LINEAR | LEAL | BFACTOR );
    public final int crystalDDMKeyword() throws RecognitionException {
        int value = 0;


        try {
            // Inputfile.g:400:2: ( SIMPLE | LINEAR | LEAL | BFACTOR )
            int alt4=4;
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
            case BFACTOR:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // Inputfile.g:400:4: SIMPLE
                    {
                    match(input,SIMPLE,FOLLOW_SIMPLE_in_crystalDDMKeyword1011); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // Inputfile.g:401:4: LINEAR
                    {
                    match(input,LINEAR,FOLLOW_LINEAR_in_crystalDDMKeyword1018); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // Inputfile.g:402:4: LEAL
                    {
                    match(input,LEAL,FOLLOW_LEAL_in_crystalDDMKeyword1025); 

                     value = 3; 

                    }
                    break;
                case 4 :
                    // Inputfile.g:403:4: BFACTOR
                    {
                    match(input,BFACTOR,FOLLOW_BFACTOR_in_crystalDDMKeyword1034); 

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
    // $ANTLR end "crystalDDMKeyword"


    public static class crystalDecayParam_return extends ParserRuleReturnScope {
        public Double gammaParam;
        public Double b0Param;
        public Double betaParam;
    };


    // $ANTLR start "crystalDecayParam"
    // Inputfile.g:410:1: crystalDecayParam returns [Double gammaParam, Double b0Param, Double betaParam] : DECAYPARAM a= FLOAT b= FLOAT c= FLOAT ;
    public final InputfileParser.crystalDecayParam_return crystalDecayParam() throws RecognitionException {
        InputfileParser.crystalDecayParam_return retval = new InputfileParser.crystalDecayParam_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // Inputfile.g:411:2: ( DECAYPARAM a= FLOAT b= FLOAT c= FLOAT )
            // Inputfile.g:411:4: DECAYPARAM a= FLOAT b= FLOAT c= FLOAT
            {
            match(input,DECAYPARAM,FOLLOW_DECAYPARAM_in_crystalDecayParam1196); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam1200); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam1204); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam1208); 

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
    // Inputfile.g:414:1: crystalCoefcalc returns [int value] : ABSCOEFCALC a= crystalCoefcalcKeyword ;
    public final int crystalCoefcalc() throws RecognitionException {
        int value = 0;


        int a =0;


        try {
            // Inputfile.g:415:2: ( ABSCOEFCALC a= crystalCoefcalcKeyword )
            // Inputfile.g:415:4: ABSCOEFCALC a= crystalCoefcalcKeyword
            {
            match(input,ABSCOEFCALC,FOLLOW_ABSCOEFCALC_in_crystalCoefcalc1280); 

            pushFollow(FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc1284);
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
    // Inputfile.g:417:1: crystalCoefcalcKeyword returns [int value] : ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | PDBNAME | SAXS | SEQUENCE | SAXSSEQ | SMALLMOLE | CIF | CIFNAME );
    public final int crystalCoefcalcKeyword() throws RecognitionException {
        int value = 0;


        try {
            // Inputfile.g:418:2: ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | PDBNAME | SAXS | SEQUENCE | SAXSSEQ | SMALLMOLE | CIF | CIFNAME )
            int alt5=13;
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
            case PDBNAME:
                {
                alt5=7;
                }
                break;
            case SAXS:
                {
                alt5=8;
                }
                break;
            case SEQUENCE:
                {
                alt5=9;
                }
                break;
            case SAXSSEQ:
                {
                alt5=10;
                }
                break;
            case SMALLMOLE:
                {
                alt5=11;
                }
                break;
            case CIF:
                {
                alt5=12;
                }
                break;
            case CIFNAME:
                {
                alt5=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // Inputfile.g:418:4: DUMMY
                    {
                    match(input,DUMMY,FOLLOW_DUMMY_in_crystalCoefcalcKeyword1363); 

                     value = 1;

                    }
                    break;
                case 2 :
                    // Inputfile.g:419:4: AVERAGE
                    {
                    match(input,AVERAGE,FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1373); 

                     value = 1;

                    }
                    break;
                case 3 :
                    // Inputfile.g:420:4: DEFAULT
                    {
                    match(input,DEFAULT,FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1381); 

                     value = 2;

                    }
                    break;
                case 4 :
                    // Inputfile.g:421:4: RDJAVA
                    {
                    match(input,RDJAVA,FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1389); 

                     value = 2;

                    }
                    break;
                case 5 :
                    // Inputfile.g:422:4: RDFORTAN
                    {
                    match(input,RDFORTAN,FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1396); 

                     value = 3;

                    }
                    break;
                case 6 :
                    // Inputfile.g:423:4: PDB
                    {
                    match(input,PDB,FOLLOW_PDB_in_crystalCoefcalcKeyword1403); 

                     value = 4;

                    }
                    break;
                case 7 :
                    // Inputfile.g:424:4: PDBNAME
                    {
                    match(input,PDBNAME,FOLLOW_PDBNAME_in_crystalCoefcalcKeyword1413); 

                     value = 4;

                    }
                    break;
                case 8 :
                    // Inputfile.g:425:4: SAXS
                    {
                    match(input,SAXS,FOLLOW_SAXS_in_crystalCoefcalcKeyword1420); 

                     value = 5;

                    }
                    break;
                case 9 :
                    // Inputfile.g:426:4: SEQUENCE
                    {
                    match(input,SEQUENCE,FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1428); 

                     value = 6;

                    }
                    break;
                case 10 :
                    // Inputfile.g:427:4: SAXSSEQ
                    {
                    match(input,SAXSSEQ,FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1435); 

                     value = 7;

                    }
                    break;
                case 11 :
                    // Inputfile.g:428:4: SMALLMOLE
                    {
                    match(input,SMALLMOLE,FOLLOW_SMALLMOLE_in_crystalCoefcalcKeyword1442); 

                     value = 8;

                    }
                    break;
                case 12 :
                    // Inputfile.g:429:4: CIF
                    {
                    match(input,CIF,FOLLOW_CIF_in_crystalCoefcalcKeyword1453); 

                     value = 9;

                    }
                    break;
                case 13 :
                    // Inputfile.g:430:4: CIFNAME
                    {
                    match(input,CIFNAME,FOLLOW_CIFNAME_in_crystalCoefcalcKeyword1463); 

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
    // Inputfile.g:455:1: crystalDim returns [Map<Object, Object> properties] : DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) ;
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
            // Inputfile.g:458:3: ( DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) )
            // Inputfile.g:458:5: DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
            {
            match(input,DIMENSION,FOLLOW_DIMENSION_in_crystalDim1880); 

            // Inputfile.g:459:2: (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==FLOAT) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==FLOAT) ) {
                    int LA6_2 = input.LA(3);

                    if ( (LA6_2==FLOAT) ) {
                        alt6=1;
                    }
                    else if ( (LA6_2==EOF||(LA6_2 >= ABSCOEFCALC && LA6_2 <= ANGLEP)||LA6_2==BEAM||(LA6_2 >= CALCSURROUNDING && LA6_2 <= CALCULATEPEESCAPE)||LA6_2==CIFNAME||(LA6_2 >= CONTAINERDENSITY && LA6_2 <= DECAYPARAM)||(LA6_2 >= DENSITYBASED && LA6_2 <= DIMENSION)||LA6_2==FLRESOLUTION||LA6_2==GONIOMETERAXIS||(LA6_2 >= MATERIALELEMENTS && LA6_2 <= MATERIALTYPE)||LA6_2==MODELFILE||(LA6_2 >= NUMCARB && LA6_2 <= NUMRNA)||(LA6_2 >= PDBNAME && LA6_2 <= PERESOLUTION)||(LA6_2 >= PIXELSPERMICRON && LA6_2 <= PROTEINHEAVYATOMS)||LA6_2==RUNS||LA6_2==SEQFILE||(LA6_2 >= SEQUENCEFILE && LA6_2 <= SIMPHOTONS)||(LA6_2 >= SMALLMOLEATOMS && LA6_2 <= SOLVENTHEAVYCONC)||(LA6_2 >= SUBPROGRAM && LA6_2 <= SURROUNDINGTHICKNESS)||(LA6_2 >= TYPE && LA6_2 <= UNITCELL)||(LA6_2 >= WEDGE && LA6_2 <= WIREFRAMETYPE)) ) {
                        alt6=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA6_1==EOF||(LA6_1 >= ABSCOEFCALC && LA6_1 <= ANGLEP)||LA6_1==BEAM||(LA6_1 >= CALCSURROUNDING && LA6_1 <= CALCULATEPEESCAPE)||LA6_1==CIFNAME||(LA6_1 >= CONTAINERDENSITY && LA6_1 <= DECAYPARAM)||(LA6_1 >= DENSITYBASED && LA6_1 <= DIMENSION)||LA6_1==FLRESOLUTION||LA6_1==GONIOMETERAXIS||(LA6_1 >= MATERIALELEMENTS && LA6_1 <= MATERIALTYPE)||LA6_1==MODELFILE||(LA6_1 >= NUMCARB && LA6_1 <= NUMRNA)||(LA6_1 >= PDBNAME && LA6_1 <= PERESOLUTION)||(LA6_1 >= PIXELSPERMICRON && LA6_1 <= PROTEINHEAVYATOMS)||LA6_1==RUNS||LA6_1==SEQFILE||(LA6_1 >= SEQUENCEFILE && LA6_1 <= SIMPHOTONS)||(LA6_1 >= SMALLMOLEATOMS && LA6_1 <= SOLVENTHEAVYCONC)||(LA6_1 >= SUBPROGRAM && LA6_1 <= SURROUNDINGTHICKNESS)||(LA6_1 >= TYPE && LA6_1 <= UNITCELL)||(LA6_1 >= WEDGE && LA6_1 <= WIREFRAMETYPE)) ) {
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
                    // Inputfile.g:460:7: a= FLOAT b= FLOAT c= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1893); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1897); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1901); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((a!=null?a.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((b!=null?b.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble((c!=null?c.getText():null))); 

                    }
                    break;
                case 2 :
                    // Inputfile.g:463:7: e= FLOAT f= FLOAT
                    {
                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1913); 

                    f=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1917); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((e!=null?e.getText():null)));
                        		       properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((f!=null?f.getText():null))); 

                    }
                    break;
                case 3 :
                    // Inputfile.g:465:7: d= FLOAT
                    {
                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1929); 

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
    // Inputfile.g:469:1: crystalAngP returns [double value] : ANGLEP a= FLOAT ;
    public final double crystalAngP() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // Inputfile.g:470:2: ( ANGLEP a= FLOAT )
            // Inputfile.g:470:4: ANGLEP a= FLOAT
            {
            match(input,ANGLEP,FOLLOW_ANGLEP_in_crystalAngP2006); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngP2010); 

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
    // Inputfile.g:474:1: crystalAngL returns [double value] : ANGLEL a= FLOAT ;
    public final double crystalAngL() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // Inputfile.g:475:2: ( ANGLEL a= FLOAT )
            // Inputfile.g:475:4: ANGLEL a= FLOAT
            {
            match(input,ANGLEL,FOLLOW_ANGLEL_in_crystalAngL2065); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngL2069); 

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
    // Inputfile.g:479:1: crystalPPM returns [double ppm] : PIXELSPERMICRON FLOAT ;
    public final double crystalPPM() throws RecognitionException {
        double ppm = 0.0;


        Token FLOAT1=null;

        try {
            // Inputfile.g:480:2: ( PIXELSPERMICRON FLOAT )
            // Inputfile.g:480:4: PIXELSPERMICRON FLOAT
            {
            match(input,PIXELSPERMICRON,FOLLOW_PIXELSPERMICRON_in_crystalPPM2123); 

            FLOAT1=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalPPM2125); 

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
    // Inputfile.g:483:1: unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC] : UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? ;
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
            // Inputfile.g:484:2: ( UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? )
            // Inputfile.g:484:4: UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )?
            {
            match(input,UNITCELL,FOLLOW_UNITCELL_in_unitcell2223); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2227); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2231); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2235); 

            retval.dimA = Double.parseDouble((a!=null?a.getText():null));
            		 retval.dimB = Double.parseDouble((b!=null?b.getText():null));
            		 retval.dimC = Double.parseDouble((c!=null?c.getText():null)); 

            // Inputfile.g:488:7: (al= FLOAT be= FLOAT ga= FLOAT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FLOAT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Inputfile.g:488:8: al= FLOAT be= FLOAT ga= FLOAT
                    {
                    al=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2250); 

                    be=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2254); 

                    ga=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2258); 

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
    // Inputfile.g:497:1: surroundingThickness returns [Map<Object, Object> properties] : SURROUNDINGTHICKNESS (a= FLOAT b= FLOAT c= FLOAT ) ;
    public final Map<Object, Object> surroundingThickness() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;
        Token c=null;

         
        		properties = new HashMap<Object, Object>();

        try {
            // Inputfile.g:500:3: ( SURROUNDINGTHICKNESS (a= FLOAT b= FLOAT c= FLOAT ) )
            // Inputfile.g:500:5: SURROUNDINGTHICKNESS (a= FLOAT b= FLOAT c= FLOAT )
            {
            match(input,SURROUNDINGTHICKNESS,FOLLOW_SURROUNDINGTHICKNESS_in_surroundingThickness2340); 

            // Inputfile.g:501:2: (a= FLOAT b= FLOAT c= FLOAT )
            // Inputfile.g:502:2: a= FLOAT b= FLOAT c= FLOAT
            {
            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_surroundingThickness2350); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_surroundingThickness2354); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_surroundingThickness2358); 

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
    // Inputfile.g:509:1: proteinConcentration returns [Double proteinConc] : ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT ;
    public final Double proteinConcentration() throws RecognitionException {
        Double proteinConc = null;


        Token a=null;

        try {
            // Inputfile.g:510:2: ( ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT )
            // Inputfile.g:510:4: ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT
            {
            if ( (input.LA(1) >= PROTEINCONC && input.LA(1) <= PROTEINCONCENTRATION) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_proteinConcentration2494); 

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
    // Inputfile.g:514:1: nummonomers returns [int value] : NUMMONOMERS a= FLOAT ;
    public final int nummonomers() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // Inputfile.g:515:2: ( NUMMONOMERS a= FLOAT )
            // Inputfile.g:515:4: NUMMONOMERS a= FLOAT
            {
            match(input,NUMMONOMERS,FOLLOW_NUMMONOMERS_in_nummonomers2676); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_nummonomers2680); 

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
    // Inputfile.g:518:1: numresidues returns [int value] : NUMRESIDUES a= FLOAT ;
    public final int numresidues() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // Inputfile.g:519:2: ( NUMRESIDUES a= FLOAT )
            // Inputfile.g:519:4: NUMRESIDUES a= FLOAT
            {
            match(input,NUMRESIDUES,FOLLOW_NUMRESIDUES_in_numresidues2757); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numresidues2761); 

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
    // Inputfile.g:522:1: numRNA returns [int value] : NUMRNA a= FLOAT ;
    public final int numRNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // Inputfile.g:523:2: ( NUMRNA a= FLOAT )
            // Inputfile.g:523:4: NUMRNA a= FLOAT
            {
            match(input,NUMRNA,FOLLOW_NUMRNA_in_numRNA2839); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numRNA2843); 

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
    // Inputfile.g:526:1: numDNA returns [int value] : NUMDNA a= FLOAT ;
    public final int numDNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // Inputfile.g:527:2: ( NUMDNA a= FLOAT )
            // Inputfile.g:527:4: NUMDNA a= FLOAT
            {
            match(input,NUMDNA,FOLLOW_NUMDNA_in_numDNA2896); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numDNA2900); 

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
    // Inputfile.g:530:1: numcarb returns [int value] : NUMCARB a= FLOAT ;
    public final int numcarb() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // Inputfile.g:531:2: ( NUMCARB a= FLOAT )
            // Inputfile.g:531:4: NUMCARB a= FLOAT
            {
            match(input,NUMCARB,FOLLOW_NUMCARB_in_numcarb2952); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numcarb2956); 

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
    // Inputfile.g:534:1: heavyProteinAtoms returns [List<String> names, List<Double> num;] : PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavyProteinAtoms_return heavyProteinAtoms() throws RecognitionException {
        InputfileParser.heavyProteinAtoms_return retval = new InputfileParser.heavyProteinAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // Inputfile.g:539:2: ( PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ )
            // Inputfile.g:539:4: PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,PROTEINHEAVYATOMS,FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms3017); 

            // Inputfile.g:539:22: (a= ELEMENT b= FLOAT )+
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
            	    // Inputfile.g:539:23: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavyProteinAtoms3022); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavyProteinAtoms3026); 

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
    // Inputfile.g:543:1: smallMoleAtoms returns [List<String> names, List<Double> num;] : SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.smallMoleAtoms_return smallMoleAtoms() throws RecognitionException {
        InputfileParser.smallMoleAtoms_return retval = new InputfileParser.smallMoleAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // Inputfile.g:548:2: ( SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+ )
            // Inputfile.g:548:4: SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,SMALLMOLEATOMS,FOLLOW_SMALLMOLEATOMS_in_smallMoleAtoms3173); 

            // Inputfile.g:548:19: (a= ELEMENT b= FLOAT )+
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
            	    // Inputfile.g:548:20: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_smallMoleAtoms3178); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_smallMoleAtoms3182); 

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
    // Inputfile.g:551:1: heavySolutionConc returns [List<String> names, List<Double> num;] : SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavySolutionConc_return heavySolutionConc() throws RecognitionException {
        InputfileParser.heavySolutionConc_return retval = new InputfileParser.heavySolutionConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // Inputfile.g:556:2: ( SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // Inputfile.g:556:4: SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SOLVENTHEAVYCONC,FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc3284); 

            // Inputfile.g:556:21: (a= ELEMENT b= FLOAT )+
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
            	    // Inputfile.g:556:22: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavySolutionConc3289); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavySolutionConc3293); 

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
    // Inputfile.g:559:1: solventFraction returns [double solFrac] : SOLVENTFRACTION a= FLOAT ;
    public final double solventFraction() throws RecognitionException {
        double solFrac = 0.0;


        Token a=null;

        try {
            // Inputfile.g:560:2: ( SOLVENTFRACTION a= FLOAT )
            // Inputfile.g:560:4: SOLVENTFRACTION a= FLOAT
            {
            match(input,SOLVENTFRACTION,FOLLOW_SOLVENTFRACTION_in_solventFraction3399); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_solventFraction3403); 

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
    // Inputfile.g:563:1: pdb returns [String pdb] : PDBNAME (a= STRING |a= FLOAT ) ;
    public final String pdb() throws RecognitionException {
        String pdb = null;


        Token a=null;

        try {
            // Inputfile.g:564:2: ( PDBNAME (a= STRING |a= FLOAT ) )
            // Inputfile.g:564:4: PDBNAME (a= STRING |a= FLOAT )
            {
            match(input,PDBNAME,FOLLOW_PDBNAME_in_pdb3500); 

            // Inputfile.g:564:12: (a= STRING |a= FLOAT )
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
                    // Inputfile.g:564:13: a= STRING
                    {
                    a=(Token)match(input,STRING,FOLLOW_STRING_in_pdb3505); 

                    }
                    break;
                case 2 :
                    // Inputfile.g:564:22: a= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_pdb3509); 

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
    // Inputfile.g:567:1: cif returns [String cif] : CIFNAME a= STRING ;
    public final String cif() throws RecognitionException {
        String cif = null;


        Token a=null;

        try {
            // Inputfile.g:568:2: ( CIFNAME a= STRING )
            // Inputfile.g:568:4: CIFNAME a= STRING
            {
            match(input,CIFNAME,FOLLOW_CIFNAME_in_cif3547); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_cif3551); 

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
    // Inputfile.g:571:1: wireframeType returns [String value] : WIREFRAMETYPE a= STRING ;
    public final String wireframeType() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // Inputfile.g:572:2: ( WIREFRAMETYPE a= STRING )
            // Inputfile.g:572:4: WIREFRAMETYPE a= STRING
            {
            match(input,WIREFRAMETYPE,FOLLOW_WIREFRAMETYPE_in_wireframeType3588); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_wireframeType3592); 

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
    // Inputfile.g:575:1: modelFile returns [String value] : MODELFILE a= STRING ;
    public final String modelFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // Inputfile.g:576:2: ( MODELFILE a= STRING )
            // Inputfile.g:576:4: MODELFILE a= STRING
            {
            match(input,MODELFILE,FOLLOW_MODELFILE_in_modelFile3680); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_modelFile3684); 

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
    // Inputfile.g:579:1: calculatePEEscape returns [String value] : CALCULATEPEESCAPE a= STRING ;
    public final String calculatePEEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // Inputfile.g:580:2: ( CALCULATEPEESCAPE a= STRING )
            // Inputfile.g:580:4: CALCULATEPEESCAPE a= STRING
            {
            match(input,CALCULATEPEESCAPE,FOLLOW_CALCULATEPEESCAPE_in_calculatePEEscape3751); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculatePEEscape3755); 

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
    // Inputfile.g:584:1: goniometerAxis returns [double value] : GONIOMETERAXIS a= FLOAT ;
    public final double goniometerAxis() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // Inputfile.g:585:2: ( GONIOMETERAXIS a= FLOAT )
            // Inputfile.g:585:4: GONIOMETERAXIS a= FLOAT
            {
            match(input,GONIOMETERAXIS,FOLLOW_GONIOMETERAXIS_in_goniometerAxis3867); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_goniometerAxis3871); 

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
    // Inputfile.g:589:1: polarisationDirection returns [double value] : POLARISATIONDIRECTION a= FLOAT ;
    public final double polarisationDirection() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // Inputfile.g:590:2: ( POLARISATIONDIRECTION a= FLOAT )
            // Inputfile.g:590:4: POLARISATIONDIRECTION a= FLOAT
            {
            match(input,POLARISATIONDIRECTION,FOLLOW_POLARISATIONDIRECTION_in_polarisationDirection3968); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_polarisationDirection3972); 

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
    // Inputfile.g:594:1: crystalContainerMaterial returns [int value] : ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword ;
    public final int crystalContainerMaterial() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // Inputfile.g:595:2: ( ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword )
            // Inputfile.g:595:4: ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword
            {
            if ( input.LA(1)==CONTAINERMATERIALTYPE||input.LA(1)==MATERIALTYPE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial4116);
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
    // Inputfile.g:598:1: crystalContainerKeyword returns [int value] : ( NONE | MIXTURE | ELEMENTAL );
    public final int crystalContainerKeyword() throws RecognitionException {
        int value = 0;


        try {
            // Inputfile.g:599:2: ( NONE | MIXTURE | ELEMENTAL )
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
                    // Inputfile.g:599:4: NONE
                    {
                    match(input,NONE,FOLLOW_NONE_in_crystalContainerKeyword4309); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // Inputfile.g:600:4: MIXTURE
                    {
                    match(input,MIXTURE,FOLLOW_MIXTURE_in_crystalContainerKeyword4318); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // Inputfile.g:601:4: ELEMENTAL
                    {
                    match(input,ELEMENTAL,FOLLOW_ELEMENTAL_in_crystalContainerKeyword4326); 

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
    // Inputfile.g:607:1: containerThickness returns [double value] : CONTAINERTHICKNESS a= FLOAT ;
    public final double containerThickness() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // Inputfile.g:608:2: ( CONTAINERTHICKNESS a= FLOAT )
            // Inputfile.g:608:4: CONTAINERTHICKNESS a= FLOAT
            {
            match(input,CONTAINERTHICKNESS,FOLLOW_CONTAINERTHICKNESS_in_containerThickness4466); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerThickness4470); 

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
    // Inputfile.g:611:1: containerMaterialMixture returns [String value] : ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING ;
    public final String containerMaterialMixture() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // Inputfile.g:612:2: ( ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING )
            // Inputfile.g:612:4: ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING
            {
            if ( input.LA(1)==CONTAINERMATERIALMIXTURE||input.LA(1)==MATERIALMIXTURE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_containerMaterialMixture4591); 

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
    // Inputfile.g:616:1: containerMaterialElements returns [List<String> names, List<Double> num;] : ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.containerMaterialElements_return containerMaterialElements() throws RecognitionException {
        InputfileParser.containerMaterialElements_return retval = new InputfileParser.containerMaterialElements_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // Inputfile.g:621:2: ( ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ )
            // Inputfile.g:621:4: ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+
            {
            if ( input.LA(1)==CONTAINERMATERIALELEMENTS||input.LA(1)==MATERIALELEMENTS ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // Inputfile.g:621:51: (a= ELEMENT b= FLOAT )+
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
            	    // Inputfile.g:621:52: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_containerMaterialElements4827); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerMaterialElements4831); 

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
    // Inputfile.g:625:1: containerDensity returns [double value] : CONTAINERDENSITY a= FLOAT ;
    public final double containerDensity() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // Inputfile.g:626:2: ( CONTAINERDENSITY a= FLOAT )
            // Inputfile.g:626:4: CONTAINERDENSITY a= FLOAT
            {
            match(input,CONTAINERDENSITY,FOLLOW_CONTAINERDENSITY_in_containerDensity5066); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerDensity5070); 

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
    // Inputfile.g:629:1: sequenceFile returns [String value] : ( SEQUENCEFILE | SEQFILE ) a= STRING ;
    public final String sequenceFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // Inputfile.g:630:2: ( ( SEQUENCEFILE | SEQFILE ) a= STRING )
            // Inputfile.g:630:4: ( SEQUENCEFILE | SEQFILE ) a= STRING
            {
            if ( input.LA(1)==SEQFILE||input.LA(1)==SEQUENCEFILE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_sequenceFile5181); 

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
    // Inputfile.g:634:1: calculateFLEscape returns [String value] : CALCULATEFLESCAPE a= STRING ;
    public final String calculateFLEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // Inputfile.g:635:2: ( CALCULATEFLESCAPE a= STRING )
            // Inputfile.g:635:4: CALCULATEFLESCAPE a= STRING
            {
            match(input,CALCULATEFLESCAPE,FOLLOW_CALCULATEFLESCAPE_in_calculateFLEscape5304); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculateFLEscape5308); 

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
    // Inputfile.g:639:1: flResolution returns [int value] : FLRESOLUTION a= FLOAT ;
    public final int flResolution() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // Inputfile.g:640:2: ( FLRESOLUTION a= FLOAT )
            // Inputfile.g:640:4: FLRESOLUTION a= FLOAT
            {
            match(input,FLRESOLUTION,FOLLOW_FLRESOLUTION_in_flResolution5419); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_flResolution5423); 

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
    // Inputfile.g:643:1: peResolution returns [int value] : PERESOLUTION a= FLOAT ;
    public final int peResolution() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // Inputfile.g:644:2: ( PERESOLUTION a= FLOAT )
            // Inputfile.g:644:4: PERESOLUTION a= FLOAT
            {
            match(input,PERESOLUTION,FOLLOW_PERESOLUTION_in_peResolution5505); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_peResolution5509); 

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
    // Inputfile.g:647:1: surroundingHeavyConc returns [List<String> names, List<Double> num;] : SURROUNDINGHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.surroundingHeavyConc_return surroundingHeavyConc() throws RecognitionException {
        InputfileParser.surroundingHeavyConc_return retval = new InputfileParser.surroundingHeavyConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // Inputfile.g:652:2: ( SURROUNDINGHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // Inputfile.g:652:4: SURROUNDINGHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SURROUNDINGHEAVYCONC,FOLLOW_SURROUNDINGHEAVYCONC_in_surroundingHeavyConc5595); 

            // Inputfile.g:652:25: (a= ELEMENT b= FLOAT )+
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
            	    // Inputfile.g:652:26: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_surroundingHeavyConc5600); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_surroundingHeavyConc5604); 

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
    // Inputfile.g:655:1: oilBased returns [String value] : DENSITYBASED a= STRING ;
    public final String oilBased() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // Inputfile.g:656:2: ( DENSITYBASED a= STRING )
            // Inputfile.g:656:4: DENSITYBASED a= STRING
            {
            match(input,DENSITYBASED,FOLLOW_DENSITYBASED_in_oilBased5732); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_oilBased5736); 

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
    // Inputfile.g:659:1: calcSurrounding returns [String value] : CALCSURROUNDING a= STRING ;
    public final String calcSurrounding() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // Inputfile.g:660:2: ( CALCSURROUNDING a= STRING )
            // Inputfile.g:660:4: CALCSURROUNDING a= STRING
            {
            match(input,CALCSURROUNDING,FOLLOW_CALCSURROUNDING_in_calcSurrounding5818); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calcSurrounding5822); 

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
    // Inputfile.g:664:1: oilElements returns [List<String> names, List<Double> num;] : SURROUNDINGELEMENTS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.oilElements_return oilElements() throws RecognitionException {
        InputfileParser.oilElements_return retval = new InputfileParser.oilElements_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // Inputfile.g:669:2: ( SURROUNDINGELEMENTS (a= ELEMENT b= FLOAT )+ )
            // Inputfile.g:669:4: SURROUNDINGELEMENTS (a= ELEMENT b= FLOAT )+
            {
            match(input,SURROUNDINGELEMENTS,FOLLOW_SURROUNDINGELEMENTS_in_oilElements5925); 

            // Inputfile.g:669:24: (a= ELEMENT b= FLOAT )+
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
            	    // Inputfile.g:669:25: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_oilElements5930); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_oilElements5934); 

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
    // Inputfile.g:672:1: oilDensity returns [double oildens] : SURROUNDINGDENSITY a= FLOAT ;
    public final double oilDensity() throws RecognitionException {
        double oildens = 0.0;


        Token a=null;

        try {
            // Inputfile.g:673:2: ( SURROUNDINGDENSITY a= FLOAT )
            // Inputfile.g:673:4: SURROUNDINGDENSITY a= FLOAT
            {
            match(input,SURROUNDINGDENSITY,FOLLOW_SURROUNDINGDENSITY_in_oilDensity6057); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_oilDensity6061); 

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
    // Inputfile.g:676:1: simElectrons returns [long simel] : ( SIMELECTRONS | SIMPHOTONS ) a= FLOAT ;
    public final long simElectrons() throws RecognitionException {
        long simel = 0;


        Token a=null;

        try {
            // Inputfile.g:677:2: ( ( SIMELECTRONS | SIMPHOTONS ) a= FLOAT )
            // Inputfile.g:677:4: ( SIMELECTRONS | SIMPHOTONS ) a= FLOAT
            {
            if ( (input.LA(1) >= SIMELECTRONS && input.LA(1) <= SIMPHOTONS) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_simElectrons6183); 

            simel = wholeNumber(a, "SimPhotons", Long.MAX_VALUE);

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
    // Inputfile.g:681:1: program returns [String value] : SUBPROGRAM a= STRING ;
    public final String program() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // Inputfile.g:682:2: ( SUBPROGRAM a= STRING )
            // Inputfile.g:682:4: SUBPROGRAM a= STRING
            {
            match(input,SUBPROGRAM,FOLLOW_SUBPROGRAM_in_program6322); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_program6326); 

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
    // Inputfile.g:686:1: runs returns [int value] : RUNS a= FLOAT ;
    public final int runs() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // Inputfile.g:687:2: ( RUNS a= FLOAT )
            // Inputfile.g:687:4: RUNS a= FLOAT
            {
            match(input,RUNS,FOLLOW_RUNS_in_runs6402); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_runs6406); 

            value = (int) wholeNumber(a, "Runs", Integer.MAX_VALUE);

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
    // Inputfile.g:693:1: beam returns [Beam bObj] : BEAM ( beamLine )+ ;
    public final Beam beam() throws RecognitionException {
        beam_stack.push(new beam_scope());
        Beam bObj = null;


         
        		((beam_scope)beam_stack.peek()).beamProperties = new HashMap<Object, Object>();

        try {
            // Inputfile.g:704:2: ( BEAM ( beamLine )+ )
            // Inputfile.g:704:4: BEAM ( beamLine )+
            {
            match(input,BEAM,FOLLOW_BEAM_in_beam6469); 

            // Inputfile.g:704:9: ( beamLine )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==APERTURERADIUS||(LA16_0 >= CIRCULAR && LA16_0 <= COLLIMATION)||(LA16_0 >= ENERGY && LA16_0 <= ENERGYFWHM)||LA16_0==EXPOSURE||LA16_0==FILE||(LA16_0 >= FLUX && LA16_0 <= FWHM)||(LA16_0 >= HORIZONTAL && LA16_0 <= IMAGEDIM)||LA16_0==PIXELSIZE||(LA16_0 >= PULSEENERGY && LA16_0 <= PULSEWINDOW)||LA16_0==RECTANGULAR||LA16_0==SEMIANGLE||LA16_0==TYPE||LA16_0==VERTICAL) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // Inputfile.g:704:9: beamLine
            	    {
            	    pushFollow(FOLLOW_beamLine_in_beam6471);
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
    // Inputfile.g:707:1: beamLine : ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize |h= beamExposure |i= beamSemiAngle |j= beamApertureRadius |k= imageDimensions |l= pulseEnergy |m= energyFWHM |n= pulseShape |o= pulseWindow );
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

        String n =null;

        Double o =null;


        try {
            // Inputfile.g:708:2: ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize |h= beamExposure |i= beamSemiAngle |j= beamApertureRadius |k= imageDimensions |l= pulseEnergy |m= energyFWHM |n= pulseShape |o= pulseWindow )
            int alt17=15;
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
            case PULSESHAPE:
                {
                alt17=14;
                }
                break;
            case PULSEWINDOW:
                {
                alt17=15;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }

            switch (alt17) {
                case 1 :
                    // Inputfile.g:708:4: TYPE a= STRING
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_beamLine6510); 

                    a=(Token)match(input,STRING,FOLLOW_STRING_in_beamLine6514); 

                     ((beam_scope)beam_stack.peek()).beamType = (a!=null?a.getText():null); 

                    }
                    break;
                case 2 :
                    // Inputfile.g:709:4: b= beamFlux
                    {
                    pushFollow(FOLLOW_beamFlux_in_beamLine6532);
                    b=beamFlux();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FLUX, b); 

                    }
                    break;
                case 3 :
                    // Inputfile.g:710:4: c= beamFWHM
                    {
                    pushFollow(FOLLOW_beamFWHM_in_beamLine6544);
                    c=beamFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_X, (c!=null?c.x:null)); 
                    	                           ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_Y, (c!=null?c.y:null)); 

                    }
                    break;
                case 4 :
                    // Inputfile.g:712:4: d= beamEnergy
                    {
                    pushFollow(FOLLOW_beamEnergy_in_beamLine6556);
                    d=beamEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_ENERGY, d); 

                    }
                    break;
                case 5 :
                    // Inputfile.g:713:4: e= beamCollimation
                    {
                    pushFollow(FOLLOW_beamCollimation_in_beamLine6568);
                    e=beamCollimation();

                    state._fsp--;


                     if (e != null) {
                    							    ((beam_scope)beam_stack.peek()).beamProperties.putAll(e);
                    							   } 

                    }
                    break;
                case 6 :
                    // Inputfile.g:716:4: f= beamFile
                    {
                    pushFollow(FOLLOW_beamFile_in_beamLine6579);
                    f=beamFile();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXTFILE, f); 

                    }
                    break;
                case 7 :
                    // Inputfile.g:717:4: g= beamPixelSize
                    {
                    pushFollow(FOLLOW_beamPixelSize_in_beamLine6600);
                    g=beamPixelSize();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.putAll(g); 

                    }
                    break;
                case 8 :
                    // Inputfile.g:719:4: h= beamExposure
                    {
                    pushFollow(FOLLOW_beamExposure_in_beamLine6617);
                    h=beamExposure();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXPOSURE, h); 

                    }
                    break;
                case 9 :
                    // Inputfile.g:720:4: i= beamSemiAngle
                    {
                    pushFollow(FOLLOW_beamSemiAngle_in_beamLine6629);
                    i=beamSemiAngle();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_SEMIANGLE, i);

                    }
                    break;
                case 10 :
                    // Inputfile.g:721:4: j= beamApertureRadius
                    {
                    pushFollow(FOLLOW_beamApertureRadius_in_beamLine6638);
                    j=beamApertureRadius();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_APERTURERADIUS, j);

                    }
                    break;
                case 11 :
                    // Inputfile.g:722:4: k= imageDimensions
                    {
                    pushFollow(FOLLOW_imageDimensions_in_beamLine6647);
                    k=imageDimensions();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.IMAGE_X, (k!=null?k.xImage:null)); 
                    	                           ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.IMAGE_Y, (k!=null?k.yImage:null)); 

                    }
                    break;
                case 12 :
                    // Inputfile.g:724:4: l= pulseEnergy
                    {
                    pushFollow(FOLLOW_pulseEnergy_in_beamLine6657);
                    l=pulseEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.PULSE_ENERGY, l); 

                    }
                    break;
                case 13 :
                    // Inputfile.g:725:4: m= energyFWHM
                    {
                    pushFollow(FOLLOW_energyFWHM_in_beamLine6669);
                    m=energyFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.ENERGY_FWHM, m); 

                    }
                    break;
                case 14 :
                    // Inputfile.g:726:4: n= pulseShape
                    {
                    pushFollow(FOLLOW_pulseShape_in_beamLine6690);
                    n=pulseShape();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.PULSE_SHAPE, n); 

                    }
                    break;
                case 15 :
                    // Inputfile.g:727:4: o= pulseWindow
                    {
                    pushFollow(FOLLOW_pulseWindow_in_beamLine6711);
                    o=pulseWindow();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.PULSE_WINDOW, o); 

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
    // Inputfile.g:731:1: beamFlux returns [Double flux] : FLUX a= FLOAT ;
    public final Double beamFlux() throws RecognitionException {
        Double flux = null;


        Token a=null;

        try {
            // Inputfile.g:732:2: ( FLUX a= FLOAT )
            // Inputfile.g:732:4: FLUX a= FLOAT
            {
            match(input,FLUX,FOLLOW_FLUX_in_beamFlux6740); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFlux6744); 

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
    // Inputfile.g:735:1: beamExposure returns [Double exposure] : EXPOSURE a= FLOAT ;
    public final Double beamExposure() throws RecognitionException {
        Double exposure = null;


        Token a=null;

        try {
            // Inputfile.g:736:2: ( EXPOSURE a= FLOAT )
            // Inputfile.g:736:4: EXPOSURE a= FLOAT
            {
            match(input,EXPOSURE,FOLLOW_EXPOSURE_in_beamExposure6786); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamExposure6790); 

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
    // Inputfile.g:739:1: imageDimensions returns [Double xImage, Double yImage] : IMAGEDIM a= FLOAT b= FLOAT ;
    public final InputfileParser.imageDimensions_return imageDimensions() throws RecognitionException {
        InputfileParser.imageDimensions_return retval = new InputfileParser.imageDimensions_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // Inputfile.g:740:2: ( IMAGEDIM a= FLOAT b= FLOAT )
            // Inputfile.g:740:4: IMAGEDIM a= FLOAT b= FLOAT
            {
            match(input,IMAGEDIM,FOLLOW_IMAGEDIM_in_imageDimensions6852); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_imageDimensions6856); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_imageDimensions6860); 

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
    // Inputfile.g:744:1: beamFWHM returns [Double x, Double y] : FWHM a= FLOAT b= FLOAT ;
    public final InputfileParser.beamFWHM_return beamFWHM() throws RecognitionException {
        InputfileParser.beamFWHM_return retval = new InputfileParser.beamFWHM_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // Inputfile.g:745:2: ( FWHM a= FLOAT b= FLOAT )
            // Inputfile.g:745:4: FWHM a= FLOAT b= FLOAT
            {
            match(input,FWHM,FOLLOW_FWHM_in_beamFWHM6925); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM6929); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM6933); 

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
    // Inputfile.g:748:1: beamEnergy returns [Double energy] : ENERGY a= FLOAT ( KEV )? ;
    public final Double beamEnergy() throws RecognitionException {
        Double energy = null;


        Token a=null;

        try {
            // Inputfile.g:749:2: ( ENERGY a= FLOAT ( KEV )? )
            // Inputfile.g:749:4: ENERGY a= FLOAT ( KEV )?
            {
            match(input,ENERGY,FOLLOW_ENERGY_in_beamEnergy6975); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamEnergy6979); 

            energy = Double.parseDouble((a!=null?a.getText():null));

            // Inputfile.g:750:2: ( KEV )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==KEV) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // Inputfile.g:750:4: KEV
                    {
                    match(input,KEV,FOLLOW_KEV_in_beamEnergy6986); 

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
    // Inputfile.g:755:1: pulseEnergy returns [Double pulse] : PULSEENERGY a= FLOAT ;
    public final Double pulseEnergy() throws RecognitionException {
        Double pulse = null;


        Token a=null;

        try {
            // Inputfile.g:756:2: ( PULSEENERGY a= FLOAT )
            // Inputfile.g:756:4: PULSEENERGY a= FLOAT
            {
            match(input,PULSEENERGY,FOLLOW_PULSEENERGY_in_pulseEnergy7064); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_pulseEnergy7068); 

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



    // $ANTLR start "pulseShape"
    // Inputfile.g:761:1: pulseShape returns [String shape] : PULSESHAPE a= STRING ;
    public final String pulseShape() throws RecognitionException {
        String shape = null;


        Token a=null;

        try {
            // Inputfile.g:762:2: ( PULSESHAPE a= STRING )
            // Inputfile.g:762:4: PULSESHAPE a= STRING
            {
            match(input,PULSESHAPE,FOLLOW_PULSESHAPE_in_pulseShape7147); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_pulseShape7151); 

            shape = (a!=null?a.getText():null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return shape;
    }
    // $ANTLR end "pulseShape"



    // $ANTLR start "pulseWindow"
    // Inputfile.g:766:1: pulseWindow returns [Double window] : PULSEWINDOW a= FLOAT ;
    public final Double pulseWindow() throws RecognitionException {
        Double window = null;


        Token a=null;

        try {
            // Inputfile.g:767:2: ( PULSEWINDOW a= FLOAT )
            // Inputfile.g:767:4: PULSEWINDOW a= FLOAT
            {
            match(input,PULSEWINDOW,FOLLOW_PULSEWINDOW_in_pulseWindow7224); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_pulseWindow7228); 

            window = Double.parseDouble((a!=null?a.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return window;
    }
    // $ANTLR end "pulseWindow"



    // $ANTLR start "beamSemiAngle"
    // Inputfile.g:771:1: beamSemiAngle returns [Double semiAngle] : SEMIANGLE a= FLOAT ;
    public final Double beamSemiAngle() throws RecognitionException {
        Double semiAngle = null;


        Token a=null;

        try {
            // Inputfile.g:772:2: ( SEMIANGLE a= FLOAT )
            // Inputfile.g:772:4: SEMIANGLE a= FLOAT
            {
            match(input,SEMIANGLE,FOLLOW_SEMIANGLE_in_beamSemiAngle7306); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamSemiAngle7310); 

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
    // Inputfile.g:776:1: beamApertureRadius returns [Double apertureRadius] : APERTURERADIUS a= FLOAT ;
    public final Double beamApertureRadius() throws RecognitionException {
        Double apertureRadius = null;


        Token a=null;

        try {
            // Inputfile.g:777:2: ( APERTURERADIUS a= FLOAT )
            // Inputfile.g:777:4: APERTURERADIUS a= FLOAT
            {
            match(input,APERTURERADIUS,FOLLOW_APERTURERADIUS_in_beamApertureRadius7379); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamApertureRadius7383); 

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
    // Inputfile.g:781:1: beamFile returns [String filename] : FILE a= STRING ;
    public final String beamFile() throws RecognitionException {
        String filename = null;


        Token a=null;

        try {
            // Inputfile.g:782:2: ( FILE a= STRING )
            // Inputfile.g:782:4: FILE a= STRING
            {
            match(input,FILE,FOLLOW_FILE_in_beamFile7475); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_beamFile7479); 

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
    // Inputfile.g:786:1: beamPixelSize returns [Map<Object, Object> properties] : PIXELSIZE a= FLOAT b= FLOAT ;
    public final Map<Object, Object> beamPixelSize() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;

        try {
            // Inputfile.g:787:5: ( PIXELSIZE a= FLOAT b= FLOAT )
            // Inputfile.g:787:7: PIXELSIZE a= FLOAT b= FLOAT
            {
            match(input,PIXELSIZE,FOLLOW_PIXELSIZE_in_beamPixelSize7526); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize7530); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize7534); 

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
    // Inputfile.g:794:1: energyFWHM returns [Double eFWHM] : ENERGYFWHM a= FLOAT ;
    public final Double energyFWHM() throws RecognitionException {
        Double eFWHM = null;


        Token a=null;

        try {
            // Inputfile.g:795:2: ( ENERGYFWHM a= FLOAT )
            // Inputfile.g:795:4: ENERGYFWHM a= FLOAT
            {
            match(input,ENERGYFWHM,FOLLOW_ENERGYFWHM_in_energyFWHM7609); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_energyFWHM7613); 

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
    // Inputfile.g:800:1: beamCollimation returns [Map<Object, Object> properties] : ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR c= FLOAT f= FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT );
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
            // Inputfile.g:804:2: ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR c= FLOAT f= FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT )
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
                    // Inputfile.g:804:4: COLLIMATION
                    {
                    match(input,COLLIMATION,FOLLOW_COLLIMATION_in_beamCollimation7693); 

                    }
                    break;
                case 2 :
                    // Inputfile.g:805:4: RECTANGULAR a= FLOAT b= FLOAT
                    {
                    match(input,RECTANGULAR,FOLLOW_RECTANGULAR_in_beamCollimation7699); 

                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7703); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7707); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((a!=null?a.getText():null)));
                    	                                properties.put(Beam.BEAM_COLL_V, Double.parseDouble((b!=null?b.getText():null))); 

                    }
                    break;
                case 3 :
                    // Inputfile.g:807:4: CIRCULAR c= FLOAT f= FLOAT
                    {
                    match(input,CIRCULAR,FOLLOW_CIRCULAR_in_beamCollimation7714); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7718); 

                    f=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7722); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((c!=null?c.getText():null)));
                    	                                properties.put(Beam.BEAM_COLL_V, Double.parseDouble((f!=null?f.getText():null))); 
                    	                                properties.put(Beam.BEAM_CIRCULAR, "TRUE"); 

                    }
                    break;
                case 4 :
                    // Inputfile.g:810:4: HORIZONTAL d= FLOAT
                    {
                    match(input,HORIZONTAL,FOLLOW_HORIZONTAL_in_beamCollimation7729); 

                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7733); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((d!=null?d.getText():null))); 

                    }
                    break;
                case 5 :
                    // Inputfile.g:811:4: VERTICAL e= FLOAT
                    {
                    match(input,VERTICAL,FOLLOW_VERTICAL_in_beamCollimation7740); 

                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation7744); 

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
    // Inputfile.g:821:1: wedge returns [Wedge wObj] : WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ ;
    public final Wedge wedge() throws RecognitionException {
        wedge_stack.push(new wedge_scope());
        Wedge wObj = null;


        Token a=null;
        Token b=null;

         

        try {
            // Inputfile.g:841:2: ( WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ )
            // Inputfile.g:841:4: WEDGE a= FLOAT b= FLOAT ( wedgeLine )+
            {
            match(input,WEDGE,FOLLOW_WEDGE_in_wedge8057); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge8061); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge8065); 

            ((wedge_scope)wedge_stack.peek()).startAng = Double.parseDouble((a!=null?a.getText():null));
            							 ((wedge_scope)wedge_stack.peek()).endAng = Double.parseDouble((b!=null?b.getText():null)); 

            // Inputfile.g:843:4: ( wedgeLine )+
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
            	    // Inputfile.g:843:4: wedgeLine
            	    {
            	    pushFollow(FOLLOW_wedgeLine_in_wedge8072);
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
    // Inputfile.g:846:1: wedgeLine : (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset |f= wedgeMaxRes );
    public final void wedgeLine() throws RecognitionException {
        double a =0.0;

        double b =0.0;

        InputfileParser.wedgeStartOffset_return c =null;

        InputfileParser.wedgeTranslate_return d =null;

        double e =0.0;

        double f =0.0;


        try {
            // Inputfile.g:847:2: (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset |f= wedgeMaxRes )
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
                    // Inputfile.g:847:4: a= wedgeExposure
                    {
                    pushFollow(FOLLOW_wedgeExposure_in_wedgeLine8116);
                    a=wedgeExposure();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).expTime =a; 

                    }
                    break;
                case 2 :
                    // Inputfile.g:848:4: b= wedgeAngRes
                    {
                    pushFollow(FOLLOW_wedgeAngRes_in_wedgeLine8126);
                    b=wedgeAngRes();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).angRes =b; 

                    }
                    break;
                case 3 :
                    // Inputfile.g:849:4: c= wedgeStartOffset
                    {
                    pushFollow(FOLLOW_wedgeStartOffset_in_wedgeLine8137);
                    c=wedgeStartOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).offsetX =(c!=null?c.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetY =(c!=null?c.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetZ =(c!=null?c.z:null); 

                    }
                    break;
                case 4 :
                    // Inputfile.g:852:4: d= wedgeTranslate
                    {
                    pushFollow(FOLLOW_wedgeTranslate_in_wedgeLine8147);
                    d=wedgeTranslate();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).translateX =(d!=null?d.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateY =(d!=null?d.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateZ =(d!=null?d.z:null); 

                    }
                    break;
                case 5 :
                    // Inputfile.g:855:4: e= wedgeRotAxBeamOffset
                    {
                    pushFollow(FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine8157);
                    e=wedgeRotAxBeamOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).rotationOffset =e; 

                    }
                    break;
                case 6 :
                    // Inputfile.g:856:4: f= wedgeMaxRes
                    {
                    pushFollow(FOLLOW_wedgeMaxRes_in_wedgeLine8166);
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
    // Inputfile.g:859:1: wedgeExposure returns [double value] : EXPOSURETIME a= FLOAT ;
    public final double wedgeExposure() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // Inputfile.g:860:2: ( EXPOSURETIME a= FLOAT )
            // Inputfile.g:860:4: EXPOSURETIME a= FLOAT
            {
            match(input,EXPOSURETIME,FOLLOW_EXPOSURETIME_in_wedgeExposure8183); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeExposure8187); 

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
    // Inputfile.g:863:1: wedgeAngRes returns [double res] : ANGULARRESOLUTION a= FLOAT ;
    public final double wedgeAngRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // Inputfile.g:864:2: ( ANGULARRESOLUTION a= FLOAT )
            // Inputfile.g:864:4: ANGULARRESOLUTION a= FLOAT
            {
            match(input,ANGULARRESOLUTION,FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes8269); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeAngRes8273); 

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
    // Inputfile.g:867:1: wedgeStartOffset returns [Double x, Double y, Double z] : STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeStartOffset_return wedgeStartOffset() throws RecognitionException {
        InputfileParser.wedgeStartOffset_return retval = new InputfileParser.wedgeStartOffset_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // Inputfile.g:868:2: ( STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? )
            // Inputfile.g:868:4: STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,STARTOFFSET,FOLLOW_STARTOFFSET_in_wedgeStartOffset8380); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset8384); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset8388); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // Inputfile.g:869:17: (c= FLOAT )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==FLOAT) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // Inputfile.g:869:17: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset8409); 

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
    // Inputfile.g:873:1: wedgeTranslate returns [Double x, Double y, Double z] : TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeTranslate_return wedgeTranslate() throws RecognitionException {
        InputfileParser.wedgeTranslate_return retval = new InputfileParser.wedgeTranslate_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // Inputfile.g:874:2: ( TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? )
            // Inputfile.g:874:4: TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,TRANSLATEPERDEGREE,FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate8503); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate8507); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate8511); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // Inputfile.g:875:24: (c= FLOAT )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==FLOAT) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // Inputfile.g:875:24: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate8539); 

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
    // Inputfile.g:879:1: wedgeRotAxBeamOffset returns [double delta] : ROTAXBEAMOFFSET a= FLOAT ;
    public final double wedgeRotAxBeamOffset() throws RecognitionException {
        double delta = 0.0;


        Token a=null;

        try {
            // Inputfile.g:880:2: ( ROTAXBEAMOFFSET a= FLOAT )
            // Inputfile.g:880:4: ROTAXBEAMOFFSET a= FLOAT
            {
            match(input,ROTAXBEAMOFFSET,FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset8675); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeRotAxBeamOffset8679); 

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
    // Inputfile.g:883:1: wedgeMaxRes returns [double res] : MAXRESOLUTION a= FLOAT ;
    public final double wedgeMaxRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // Inputfile.g:884:2: ( MAXRESOLUTION a= FLOAT )
            // Inputfile.g:884:4: MAXRESOLUTION a= FLOAT
            {
            match(input,MAXRESOLUTION,FOLLOW_MAXRESOLUTION_in_wedgeMaxRes8776); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeMaxRes8780); 

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


 

    public static final BitSet FOLLOW_crystal_in_configfile56 = new BitSet(new long[]{0x0000000002000400L,0x0000010000000000L});
    public static final BitSet FOLLOW_wedge_in_configfile74 = new BitSet(new long[]{0x0000000002000400L,0x0000010000000000L});
    public static final BitSet FOLLOW_beam_in_configfile94 = new BitSet(new long[]{0x0000000002000400L,0x0000010000000000L});
    public static final BitSet FOLLOW_EOF_in_configfile114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CRYSTAL_in_crystal143 = new BitSet(new long[]{0x7D382400EDF17070L,0x0000026F9CE880FBL});
    public static final BitSet FOLLOW_crystalLine_in_crystal145 = new BitSet(new long[]{0x7D382400EDF17072L,0x0000026F9CE880FBL});
    public static final BitSet FOLLOW_crystalType_in_crystalLine201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalDDM_in_crystalLine212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalCoefcalc_in_crystalLine224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalDim_in_crystalLine234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalPPM_in_crystalLine245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalAngP_in_crystalLine256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalAngL_in_crystalLine267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalDecayParam_in_crystalLine278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerThickness_in_crystalLine288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerDensity_in_crystalLine298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_crystalContainerMaterial_in_crystalLine308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerMaterialMixture_in_crystalLine317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unitcell_in_crystalLine326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nummonomers_in_crystalLine337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numresidues_in_crystalLine348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numRNA_in_crystalLine359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numDNA_in_crystalLine372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numcarb_in_crystalLine385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_heavyProteinAtoms_in_crystalLine398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_heavySolutionConc_in_crystalLine407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_solventFraction_in_crystalLine416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pdb_in_crystalLine426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wireframeType_in_crystalLine439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modelFile_in_crystalLine450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_calculatePEEscape_in_crystalLine462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_proteinConcentration_in_crystalLine472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_containerMaterialElements_in_crystalLine481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sequenceFile_in_crystalLine490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_smallMoleAtoms_in_crystalLine501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cif_in_crystalLine522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_calculateFLEscape_in_crystalLine536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_flResolution_in_crystalLine546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_peResolution_in_crystalLine557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_surroundingHeavyConc_in_crystalLine569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_oilBased_in_crystalLine586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_goniometerAxis_in_crystalLine611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_calcSurrounding_in_crystalLine621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_oilElements_in_crystalLine645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_oilDensity_in_crystalLine669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_program_in_crystalLine694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simElectrons_in_crystalLine719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_runs_in_crystalLine729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_surroundingThickness_in_crystalLine754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_polarisationDirection_in_crystalLine772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_crystalType805 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_crystalType809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalDDM851 = new BitSet(new long[]{0x0006000000000800L,0x0000000001000000L});
    public static final BitSet FOLLOW_crystalDDMKeyword_in_crystalDDM863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIMPLE_in_crystalDDMKeyword1011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LINEAR_in_crystalDDMKeyword1018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEAL_in_crystalDDMKeyword1025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BFACTOR_in_crystalDDMKeyword1034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECAYPARAM_in_crystalDecayParam1196 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam1200 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam1204 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam1208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSCOEFCALC_in_crystalCoefcalc1280 = new BitSet(new long[]{0x8000000110018200L,0x0000000002131801L});
    public static final BitSet FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc1284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DUMMY_in_crystalCoefcalcKeyword1363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDB_in_crystalCoefcalcKeyword1403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDBNAME_in_crystalCoefcalcKeyword1413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXS_in_crystalCoefcalcKeyword1420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SMALLMOLE_in_crystalCoefcalcKeyword1442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIF_in_crystalCoefcalcKeyword1453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIFNAME_in_crystalCoefcalcKeyword1463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_crystalDim1880 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1893 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1897 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1913 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEP_in_crystalAngP2006 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngP2010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEL_in_crystalAngL2065 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngL2069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSPERMICRON_in_crystalPPM2123 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalPPM2125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNITCELL_in_unitcell2223 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2227 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2231 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2235 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2250 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2254 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SURROUNDINGTHICKNESS_in_surroundingThickness2340 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_surroundingThickness2350 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_surroundingThickness2354 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_surroundingThickness2358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_proteinConcentration2484 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_proteinConcentration2494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMMONOMERS_in_nummonomers2676 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_nummonomers2680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRESIDUES_in_numresidues2757 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numresidues2761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRNA_in_numRNA2839 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numRNA2843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMDNA_in_numDNA2896 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numDNA2900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMCARB_in_numcarb2952 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numcarb2956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms3017 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavyProteinAtoms3022 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavyProteinAtoms3026 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_SMALLMOLEATOMS_in_smallMoleAtoms3173 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ELEMENT_in_smallMoleAtoms3178 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_smallMoleAtoms3182 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc3284 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavySolutionConc3289 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavySolutionConc3293 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_SOLVENTFRACTION_in_solventFraction3399 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_solventFraction3403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDBNAME_in_pdb3500 = new BitSet(new long[]{0x0000020000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_pdb3505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_pdb3509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIFNAME_in_cif3547 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_cif3551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WIREFRAMETYPE_in_wireframeType3588 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_wireframeType3592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODELFILE_in_modelFile3680 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_modelFile3684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEPEESCAPE_in_calculatePEEscape3751 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_calculatePEEscape3755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GONIOMETERAXIS_in_goniometerAxis3867 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_goniometerAxis3871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POLARISATIONDIRECTION_in_polarisationDirection3968 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_polarisationDirection3972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalContainerMaterial4104 = new BitSet(new long[]{0x0280000400000000L});
    public static final BitSet FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial4116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NONE_in_crystalContainerKeyword4309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIXTURE_in_crystalContainerKeyword4318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELEMENTAL_in_crystalContainerKeyword4326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINERTHICKNESS_in_containerThickness4466 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerThickness4470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialMixture4581 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_containerMaterialMixture4591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialElements4816 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ELEMENT_in_containerMaterialElements4827 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerMaterialElements4831 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_CONTAINERDENSITY_in_containerDensity5066 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerDensity5070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sequenceFile5171 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_sequenceFile5181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEFLESCAPE_in_calculateFLEscape5304 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_calculateFLEscape5308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLRESOLUTION_in_flResolution5419 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_flResolution5423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERESOLUTION_in_peResolution5505 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_peResolution5509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SURROUNDINGHEAVYCONC_in_surroundingHeavyConc5595 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ELEMENT_in_surroundingHeavyConc5600 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_surroundingHeavyConc5604 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_DENSITYBASED_in_oilBased5732 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_oilBased5736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCSURROUNDING_in_calcSurrounding5818 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_calcSurrounding5822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SURROUNDINGELEMENTS_in_oilElements5925 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ELEMENT_in_oilElements5930 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_oilElements5934 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_SURROUNDINGDENSITY_in_oilDensity6057 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_oilDensity6061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_simElectrons6173 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_simElectrons6183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBPROGRAM_in_program6322 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_program6326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RUNS_in_runs6402 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_runs6406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEAM_in_beam6469 = new BitSet(new long[]{0x0000D95800060100L,0x000000A000042704L});
    public static final BitSet FOLLOW_beamLine_in_beam6471 = new BitSet(new long[]{0x0000D95800060102L,0x000000A000042704L});
    public static final BitSet FOLLOW_TYPE_in_beamLine6510 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_beamLine6514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFlux_in_beamLine6532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFWHM_in_beamLine6544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamEnergy_in_beamLine6556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamCollimation_in_beamLine6568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFile_in_beamLine6579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamPixelSize_in_beamLine6600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamExposure_in_beamLine6617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamSemiAngle_in_beamLine6629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamApertureRadius_in_beamLine6638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_imageDimensions_in_beamLine6647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pulseEnergy_in_beamLine6657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_energyFWHM_in_beamLine6669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pulseShape_in_beamLine6690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pulseWindow_in_beamLine6711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLUX_in_beamFlux6740 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFlux6744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURE_in_beamExposure6786 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamExposure6790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMAGEDIM_in_imageDimensions6852 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_imageDimensions6856 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_imageDimensions6860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FWHM_in_beamFWHM6925 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM6929 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM6933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGY_in_beamEnergy6975 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamEnergy6979 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_KEV_in_beamEnergy6986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PULSEENERGY_in_pulseEnergy7064 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_pulseEnergy7068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PULSESHAPE_in_pulseShape7147 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_pulseShape7151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PULSEWINDOW_in_pulseWindow7224 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_pulseWindow7228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMIANGLE_in_beamSemiAngle7306 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamSemiAngle7310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_APERTURERADIUS_in_beamApertureRadius7379 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamApertureRadius7383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_beamFile7475 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_STRING_in_beamFile7479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSIZE_in_beamPixelSize7526 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize7530 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize7534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGYFWHM_in_energyFWHM7609 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_energyFWHM7613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLIMATION_in_beamCollimation7693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RECTANGULAR_in_beamCollimation7699 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7703 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCULAR_in_beamCollimation7714 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7718 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HORIZONTAL_in_beamCollimation7729 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERTICAL_in_beamCollimation7740 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation7744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WEDGE_in_wedge8057 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge8061 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge8065 = new BitSet(new long[]{0x0040008000000080L,0x0000001020004000L});
    public static final BitSet FOLLOW_wedgeLine_in_wedge8072 = new BitSet(new long[]{0x0040008000000082L,0x0000001020004000L});
    public static final BitSet FOLLOW_wedgeExposure_in_wedgeLine8116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeAngRes_in_wedgeLine8126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeStartOffset_in_wedgeLine8137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeTranslate_in_wedgeLine8147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine8157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeMaxRes_in_wedgeLine8166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURETIME_in_wedgeExposure8183 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeExposure8187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes8269 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeAngRes8273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTOFFSET_in_wedgeStartOffset8380 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset8384 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset8388 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset8409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate8503 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate8507 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate8511 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate8539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset8675 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeRotAxBeamOffset8679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MAXRESOLUTION_in_wedgeMaxRes8776 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeMaxRes8780 = new BitSet(new long[]{0x0000000000000002L});

}