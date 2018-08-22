// $ANTLR 3.4 C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g 2018-08-22 13:04:13

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABSCOEFCALC", "ANGLEL", "ANGLEP", "ANGULARRESOLUTION", "AVERAGE", "BEAM", "CALCSURROUNDING", "CALCULATEFLESCAPE", "CALCULATEPEESCAPE", "CIF", "CIFNAME", "CIRCULAR", "COLLIMATION", "COMMENT", "CONTAINERDENSITY", "CONTAINERMATERIALELEMENTS", "CONTAINERMATERIALMIXTURE", "CONTAINERMATERIALTYPE", "CONTAINERTHICKNESS", "CRYSTAL", "DDM", "DECAYPARAM", "DEFAULT", "DIFFRACTIONDECAYMODEL", "DIMENSION", "DUMMY", "ELEMENT", "ELEMENTAL", "ENERGY", "EXPONENT", "EXPOSURETIME", "FILE", "FLOAT", "FLRESOLUTION", "FLUX", "FWHM", "GONIOMETERAXIS", "HORIZONTAL", "KEV", "LEAL", "LINEAR", "MATERIALELEMENTS", "MATERIALMIXTURE", "MATERIALTYPE", "MIXTURE", "MODELFILE", "NONE", "NUMCARB", "NUMDNA", "NUMMONOMERS", "NUMRESIDUES", "NUMRNA", "OILBASED", "OILDENSITY", "OILELEMENTS", "PDB", "PDBNAME", "PERESOLUTION", "PIXELSIZE", "PIXELSPERMICRON", "PROTEINCONC", "PROTEINCONCENTRATION", "PROTEINHEAVYATOMS", "RDFORTAN", "RDJAVA", "RECTANGULAR", "ROTAXBEAMOFFSET", "SAXS", "SAXSSEQ", "SEQFILE", "SEQUENCE", "SEQUENCEFILE", "SIMPLE", "SMALLMOLE", "SMALLMOLEATOMS", "SOLVENTFRACTION", "SOLVENTHEAVYCONC", "STARTOFFSET", "STRING", "SURROUNDINGHEAVYCONC", "TRANSLATEPERDEGREE", "TYPE", "UNITCELL", "VERTICAL", "WEDGE", "WIREFRAMETYPE", "WS"
    };

    public static final int EOF=-1;
    public static final int ABSCOEFCALC=4;
    public static final int ANGLEL=5;
    public static final int ANGLEP=6;
    public static final int ANGULARRESOLUTION=7;
    public static final int AVERAGE=8;
    public static final int BEAM=9;
    public static final int CALCSURROUNDING=10;
    public static final int CALCULATEFLESCAPE=11;
    public static final int CALCULATEPEESCAPE=12;
    public static final int CIF=13;
    public static final int CIFNAME=14;
    public static final int CIRCULAR=15;
    public static final int COLLIMATION=16;
    public static final int COMMENT=17;
    public static final int CONTAINERDENSITY=18;
    public static final int CONTAINERMATERIALELEMENTS=19;
    public static final int CONTAINERMATERIALMIXTURE=20;
    public static final int CONTAINERMATERIALTYPE=21;
    public static final int CONTAINERTHICKNESS=22;
    public static final int CRYSTAL=23;
    public static final int DDM=24;
    public static final int DECAYPARAM=25;
    public static final int DEFAULT=26;
    public static final int DIFFRACTIONDECAYMODEL=27;
    public static final int DIMENSION=28;
    public static final int DUMMY=29;
    public static final int ELEMENT=30;
    public static final int ELEMENTAL=31;
    public static final int ENERGY=32;
    public static final int EXPONENT=33;
    public static final int EXPOSURETIME=34;
    public static final int FILE=35;
    public static final int FLOAT=36;
    public static final int FLRESOLUTION=37;
    public static final int FLUX=38;
    public static final int FWHM=39;
    public static final int GONIOMETERAXIS=40;
    public static final int HORIZONTAL=41;
    public static final int KEV=42;
    public static final int LEAL=43;
    public static final int LINEAR=44;
    public static final int MATERIALELEMENTS=45;
    public static final int MATERIALMIXTURE=46;
    public static final int MATERIALTYPE=47;
    public static final int MIXTURE=48;
    public static final int MODELFILE=49;
    public static final int NONE=50;
    public static final int NUMCARB=51;
    public static final int NUMDNA=52;
    public static final int NUMMONOMERS=53;
    public static final int NUMRESIDUES=54;
    public static final int NUMRNA=55;
    public static final int OILBASED=56;
    public static final int OILDENSITY=57;
    public static final int OILELEMENTS=58;
    public static final int PDB=59;
    public static final int PDBNAME=60;
    public static final int PERESOLUTION=61;
    public static final int PIXELSIZE=62;
    public static final int PIXELSPERMICRON=63;
    public static final int PROTEINCONC=64;
    public static final int PROTEINCONCENTRATION=65;
    public static final int PROTEINHEAVYATOMS=66;
    public static final int RDFORTAN=67;
    public static final int RDJAVA=68;
    public static final int RECTANGULAR=69;
    public static final int ROTAXBEAMOFFSET=70;
    public static final int SAXS=71;
    public static final int SAXSSEQ=72;
    public static final int SEQFILE=73;
    public static final int SEQUENCE=74;
    public static final int SEQUENCEFILE=75;
    public static final int SIMPLE=76;
    public static final int SMALLMOLE=77;
    public static final int SMALLMOLEATOMS=78;
    public static final int SOLVENTFRACTION=79;
    public static final int SOLVENTHEAVYCONC=80;
    public static final int STARTOFFSET=81;
    public static final int STRING=82;
    public static final int SURROUNDINGHEAVYCONC=83;
    public static final int TRANSLATEPERDEGREE=84;
    public static final int TYPE=85;
    public static final int UNITCELL=86;
    public static final int VERTICAL=87;
    public static final int WEDGE=88;
    public static final int WIREFRAMETYPE=89;
    public static final int WS=90;

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
        HashMap<Object, Object> crystalProperties;
    }
    protected Stack crystal_stack = new Stack();



    // $ANTLR start "crystal"
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:49:1: crystal returns [Crystal cObj] : CRYSTAL ( crystalLine )+ ;
    public final Crystal crystal() throws RecognitionException {
        crystal_stack.push(new crystal_scope());
        Crystal cObj = null;


         
        ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = 2; // 0 = error, 1 = Simple, 2 = DEFAULT, 3 = RDV2, 4 = PDB, 5 = SAXS
        		((crystal_scope)crystal_stack.peek()).crystalProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:235:2: ( CRYSTAL ( crystalLine )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:235:4: CRYSTAL ( crystalLine )+
            {
            match(input,CRYSTAL,FOLLOW_CRYSTAL_in_crystal134); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:235:12: ( crystalLine )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= ABSCOEFCALC && LA2_0 <= ANGLEP)||(LA2_0 >= CALCSURROUNDING && LA2_0 <= CALCULATEPEESCAPE)||LA2_0==CIFNAME||(LA2_0 >= CONTAINERDENSITY && LA2_0 <= CONTAINERTHICKNESS)||(LA2_0 >= DDM && LA2_0 <= DECAYPARAM)||(LA2_0 >= DIFFRACTIONDECAYMODEL && LA2_0 <= DIMENSION)||LA2_0==FLRESOLUTION||LA2_0==GONIOMETERAXIS||(LA2_0 >= MATERIALELEMENTS && LA2_0 <= MATERIALTYPE)||LA2_0==MODELFILE||(LA2_0 >= NUMCARB && LA2_0 <= OILELEMENTS)||(LA2_0 >= PDBNAME && LA2_0 <= PERESOLUTION)||(LA2_0 >= PIXELSPERMICRON && LA2_0 <= PROTEINHEAVYATOMS)||LA2_0==SEQFILE||LA2_0==SEQUENCEFILE||(LA2_0 >= SMALLMOLEATOMS && LA2_0 <= SOLVENTHEAVYCONC)||LA2_0==SURROUNDINGHEAVYCONC||(LA2_0 >= TYPE && LA2_0 <= UNITCELL)||LA2_0==WIREFRAMETYPE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:235:12: crystalLine
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
              													((crystal_scope)crystal_stack.peek()).numCarb, ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums,  ((crystal_scope)crystal_stack.peek()).oilDensity);
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
              	((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromPDB(((crystal_scope)crystal_stack.peek()).pdb, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames, ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums, ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule, ((crystal_scope)crystal_stack.peek()).cryoSolutionConc, ((crystal_scope)crystal_stack.peek()).oilBased, 	((crystal_scope)crystal_stack.peek()).calcSurrounding, ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums, ((crystal_scope)crystal_stack.peek()).oilDensity);
              else
            	((crystal_scope)crystal_stack.peek()).crystalCoefCalcClass = new CoefCalcFromPDB(((crystal_scope)crystal_stack.peek()).pdb, ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule, ((crystal_scope)crystal_stack.peek()).cryoSolutionConc, ((crystal_scope)crystal_stack.peek()).oilBased, 	((crystal_scope)crystal_stack.peek()).calcSurrounding, ((crystal_scope)crystal_stack.peek()).oilNames, ((crystal_scope)crystal_stack.peek()).oilNums, ((crystal_scope)crystal_stack.peek()).oilDensity);
              													  													
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:238:1: crystalLine : (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |qa= numcarb |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculatePEEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile |ab= smallMoleAtoms |ac= cif |bb= calculateFLEscape |cc= flResolution |dd= peResolution |ee= surroundingHeavyConc |ff= oilBased |gg= goniometerAxis |hh= calcSurrounding |ii= oilElements |jj= oilDensity );
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


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:239:2: (a= crystalType |b= crystalDDM |c= crystalCoefcalc |d= crystalDim |e= crystalPPM |f= crystalAngP |g= crystalAngL |h= crystalDecayParam |i= containerThickness |j= containerDensity |k= crystalContainerMaterial |l= containerMaterialMixture |m= unitcell |n= nummonomers |o= numresidues |p= numRNA |q= numDNA |qa= numcarb |r= heavyProteinAtoms |s= heavySolutionConc |t= solventFraction |u= pdb |v= wireframeType |w= modelFile |x= calculatePEEscape |y= proteinConcentration |z= containerMaterialElements |aa= sequenceFile |ab= smallMoleAtoms |ac= cif |bb= calculateFLEscape |cc= flResolution |dd= peResolution |ee= surroundingHeavyConc |ff= oilBased |gg= goniometerAxis |hh= calcSurrounding |ii= oilElements |jj= oilDensity )
            int alt3=39;
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
            case OILBASED:
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
            case OILELEMENTS:
                {
                alt3=38;
                }
                break;
            case OILDENSITY:
                {
                alt3=39;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:239:4: a= crystalType
                    {
                    pushFollow(FOLLOW_crystalType_in_crystalLine192);
                    a=crystalType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalType = a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:240:4: b= crystalDDM
                    {
                    pushFollow(FOLLOW_crystalDDM_in_crystalLine203);
                    b=crystalDDM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalDdm = b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:241:4: c= crystalCoefcalc
                    {
                    pushFollow(FOLLOW_crystalCoefcalc_in_crystalLine215);
                    c=crystalCoefcalc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalCoefCalc = c; 

                    }
                    break;
                case 4 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:242:4: d= crystalDim
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:245:4: e= crystalPPM
                    {
                    pushFollow(FOLLOW_crystalPPM_in_crystalLine236);
                    e=crystalPPM();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, e); 

                    }
                    break;
                case 6 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:246:4: f= crystalAngP
                    {
                    pushFollow(FOLLOW_crystalAngP_in_crystalLine247);
                    f=crystalAngP();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:247:4: g= crystalAngL
                    {
                    pushFollow(FOLLOW_crystalAngL_in_crystalLine258);
                    g=crystalAngL();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, g); 

                    }
                    break;
                case 8 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:248:4: h= crystalDecayParam
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:251:4: i= containerThickness
                    {
                    pushFollow(FOLLOW_containerThickness_in_crystalLine279);
                    i=containerThickness();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerThickness = i; 

                    }
                    break;
                case 10 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:252:4: j= containerDensity
                    {
                    pushFollow(FOLLOW_containerDensity_in_crystalLine289);
                    j=containerDensity();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerDensity = j; 

                    }
                    break;
                case 11 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:253:4: k= crystalContainerMaterial
                    {
                    pushFollow(FOLLOW_crystalContainerMaterial_in_crystalLine299);
                    k=crystalContainerMaterial();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalContainerMaterial = k; 

                    }
                    break;
                case 12 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:254:4: l= containerMaterialMixture
                    {
                    pushFollow(FOLLOW_containerMaterialMixture_in_crystalLine308);
                    l=containerMaterialMixture();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerMixture = l; 

                    }
                    break;
                case 13 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:255:4: m= unitcell
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:261:4: n= nummonomers
                    {
                    pushFollow(FOLLOW_nummonomers_in_crystalLine328);
                    n=nummonomers();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numMon = n;	

                    }
                    break;
                case 15 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:262:4: o= numresidues
                    {
                    pushFollow(FOLLOW_numresidues_in_crystalLine339);
                    o=numresidues();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRes = o;	

                    }
                    break;
                case 16 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:263:4: p= numRNA
                    {
                    pushFollow(FOLLOW_numRNA_in_crystalLine350);
                    p=numRNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numRNA = p;	

                    }
                    break;
                case 17 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:264:4: q= numDNA
                    {
                    pushFollow(FOLLOW_numDNA_in_crystalLine363);
                    q=numDNA();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numDNA = q;	

                    }
                    break;
                case 18 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:265:4: qa= numcarb
                    {
                    pushFollow(FOLLOW_numcarb_in_crystalLine376);
                    qa=numcarb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).numCarb = qa;	

                    }
                    break;
                case 19 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:266:4: r= heavyProteinAtoms
                    {
                    pushFollow(FOLLOW_heavyProteinAtoms_in_crystalLine389);
                    r=heavyProteinAtoms();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNames = (r!=null?r.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavyProteinAtomNums = (r!=null?r.num:null);	

                    }
                    break;
                case 20 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:268:4: s= heavySolutionConc
                    {
                    pushFollow(FOLLOW_heavySolutionConc_in_crystalLine398);
                    s=heavySolutionConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).heavySolutionConcNames = (s!=null?s.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).heavySolutionConcNums = (s!=null?s.num:null);	

                    }
                    break;
                case 21 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:270:4: t= solventFraction
                    {
                    pushFollow(FOLLOW_solventFraction_in_crystalLine407);
                    t=solventFraction();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).solFrac = t; 

                    }
                    break;
                case 22 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:271:4: u= pdb
                    {
                    pushFollow(FOLLOW_pdb_in_crystalLine417);
                    u=pdb();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).pdb = u; 

                    }
                    break;
                case 23 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:272:4: v= wireframeType
                    {
                    pushFollow(FOLLOW_wireframeType_in_crystalLine430);
                    v=wireframeType();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_TYPE, v); 

                    }
                    break;
                case 24 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:273:4: w= modelFile
                    {
                    pushFollow(FOLLOW_modelFile_in_crystalLine441);
                    w=modelFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_FILE, w); 

                    }
                    break;
                case 25 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:274:4: x= calculatePEEscape
                    {
                    pushFollow(FOLLOW_calculatePEEscape_in_crystalLine453);
                    x=calculatePEEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_ELECTRON_ESCAPE, x); 

                    }
                    break;
                case 26 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:275:4: y= proteinConcentration
                    {
                    pushFollow(FOLLOW_proteinConcentration_in_crystalLine463);
                    y=proteinConcentration();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).proteinConc = y;

                    }
                    break;
                case 27 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:276:4: z= containerMaterialElements
                    {
                    pushFollow(FOLLOW_containerMaterialElements_in_crystalLine472);
                    z=containerMaterialElements();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).containerElementNames = (z!=null?z.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).containerElementNums = (z!=null?z.num:null);	

                    }
                    break;
                case 28 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:278:4: aa= sequenceFile
                    {
                    pushFollow(FOLLOW_sequenceFile_in_crystalLine481);
                    aa=sequenceFile();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).seqFile = aa; 

                    }
                    break;
                case 29 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:279:4: ab= smallMoleAtoms
                    {
                    pushFollow(FOLLOW_smallMoleAtoms_in_crystalLine492);
                    ab=smallMoleAtoms();

                    state._fsp--;


                    ((crystal_scope)crystal_stack.peek()).smallMoleAtomNames = (ab!=null?ab.names:null);
                    							((crystal_scope)crystal_stack.peek()).smallMoleAtomNums = (ab!=null?ab.num:null);	

                    }
                    break;
                case 30 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:281:4: ac= cif
                    {
                    pushFollow(FOLLOW_cif_in_crystalLine513);
                    ac=cif();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).cif = ac; 

                    }
                    break;
                case 31 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:283:4: bb= calculateFLEscape
                    {
                    pushFollow(FOLLOW_calculateFLEscape_in_crystalLine527);
                    bb=calculateFLEscape();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_FLUORESCENT_ESCAPE, bb); 

                    }
                    break;
                case 32 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:284:4: cc= flResolution
                    {
                    pushFollow(FOLLOW_flResolution_in_crystalLine537);
                    cc=flResolution();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_FLUORESCENT_RESOLUTION, cc);

                    }
                    break;
                case 33 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:285:4: dd= peResolution
                    {
                    pushFollow(FOLLOW_peResolution_in_crystalLine548);
                    dd=peResolution();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_PHOTOELECTRON_RESOLUTION, dd);

                    }
                    break;
                case 34 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:287:4: ee= surroundingHeavyConc
                    {
                    pushFollow(FOLLOW_surroundingHeavyConc_in_crystalLine560);
                    ee=surroundingHeavyConc();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).cryoSolutionMolecule = (ee!=null?ee.names:null);
                    							  ((crystal_scope)crystal_stack.peek()).cryoSolutionConc = (ee!=null?ee.num:null);	

                    }
                    break;
                case 35 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:289:4: ff= oilBased
                    {
                    pushFollow(FOLLOW_oilBased_in_crystalLine577);
                    ff=oilBased();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).oilBased = ff;  

                    }
                    break;
                case 36 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:290:4: gg= goniometerAxis
                    {
                    pushFollow(FOLLOW_goniometerAxis_in_crystalLine602);
                    gg=goniometerAxis();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).crystalProperties.put(Crystal.CRYSTAL_GONIOMETER_AXIS, gg); 

                    }
                    break;
                case 37 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:291:4: hh= calcSurrounding
                    {
                    pushFollow(FOLLOW_calcSurrounding_in_crystalLine612);
                    hh=calcSurrounding();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).calcSurrounding = hh;  

                    }
                    break;
                case 38 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:292:4: ii= oilElements
                    {
                    pushFollow(FOLLOW_oilElements_in_crystalLine636);
                    ii=oilElements();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).oilNames = (ii!=null?ii.names:null);  
                    		                	         ((crystal_scope)crystal_stack.peek()).oilNums = (ii!=null?ii.num:null);  

                    }
                    break;
                case 39 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:294:4: jj= oilDensity
                    {
                    pushFollow(FOLLOW_oilDensity_in_crystalLine660);
                    jj=oilDensity();

                    state._fsp--;


                     ((crystal_scope)crystal_stack.peek()).oilDensity = jj;  

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:299:1: crystalType returns [String crystalType] : TYPE e= STRING ;
    public final String crystalType() throws RecognitionException {
        String crystalType = null;


        Token e=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:300:2: ( TYPE e= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:300:4: TYPE e= STRING
            {
            match(input,TYPE,FOLLOW_TYPE_in_crystalType703); 

            e=(Token)match(input,STRING,FOLLOW_STRING_in_crystalType707); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:303:1: crystalDDM returns [int value] : ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword ;
    public final int crystalDDM() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:304:2: ( ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:304:4: ( DIFFRACTIONDECAYMODEL | DDM ) e= crystalDDMKeyword
            {
            if ( input.LA(1)==DDM||input.LA(1)==DIFFRACTIONDECAYMODEL ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalDDMKeyword_in_crystalDDM761);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:307:1: crystalDDMKeyword returns [int value] : ( SIMPLE | LINEAR | LEAL );
    public final int crystalDDMKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:308:2: ( SIMPLE | LINEAR | LEAL )
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:308:4: SIMPLE
                    {
                    match(input,SIMPLE,FOLLOW_SIMPLE_in_crystalDDMKeyword909); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:309:4: LINEAR
                    {
                    match(input,LINEAR,FOLLOW_LINEAR_in_crystalDDMKeyword916); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:310:4: LEAL
                    {
                    match(input,LEAL,FOLLOW_LEAL_in_crystalDDMKeyword923); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:316:1: crystalDecayParam returns [Double gammaParam, Double b0Param, Double betaParam] : DECAYPARAM a= FLOAT b= FLOAT c= FLOAT ;
    public final InputfileParser.crystalDecayParam_return crystalDecayParam() throws RecognitionException {
        InputfileParser.crystalDecayParam_return retval = new InputfileParser.crystalDecayParam_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:317:2: ( DECAYPARAM a= FLOAT b= FLOAT c= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:317:4: DECAYPARAM a= FLOAT b= FLOAT c= FLOAT
            {
            match(input,DECAYPARAM,FOLLOW_DECAYPARAM_in_crystalDecayParam1043); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam1047); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam1051); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDecayParam1055); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:320:1: crystalCoefcalc returns [int value] : ABSCOEFCALC a= crystalCoefcalcKeyword ;
    public final int crystalCoefcalc() throws RecognitionException {
        int value = 0;


        int a =0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:321:2: ( ABSCOEFCALC a= crystalCoefcalcKeyword )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:321:4: ABSCOEFCALC a= crystalCoefcalcKeyword
            {
            match(input,ABSCOEFCALC,FOLLOW_ABSCOEFCALC_in_crystalCoefcalc1127); 

            pushFollow(FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc1131);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:323:1: crystalCoefcalcKeyword returns [int value] : ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS | SEQUENCE | SAXSSEQ | SMALLMOLE | CIF );
    public final int crystalCoefcalcKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:324:2: ( DUMMY | AVERAGE | DEFAULT | RDJAVA | RDFORTAN | PDB | SAXS | SEQUENCE | SAXSSEQ | SMALLMOLE | CIF )
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:324:4: DUMMY
                    {
                    match(input,DUMMY,FOLLOW_DUMMY_in_crystalCoefcalcKeyword1210); 

                     value = 1;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:325:4: AVERAGE
                    {
                    match(input,AVERAGE,FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1220); 

                     value = 1;

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:326:4: DEFAULT
                    {
                    match(input,DEFAULT,FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1228); 

                     value = 2;

                    }
                    break;
                case 4 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:327:4: RDJAVA
                    {
                    match(input,RDJAVA,FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1236); 

                     value = 2;

                    }
                    break;
                case 5 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:328:4: RDFORTAN
                    {
                    match(input,RDFORTAN,FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1243); 

                     value = 3;

                    }
                    break;
                case 6 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:329:4: PDB
                    {
                    match(input,PDB,FOLLOW_PDB_in_crystalCoefcalcKeyword1250); 

                     value = 4;

                    }
                    break;
                case 7 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:330:4: SAXS
                    {
                    match(input,SAXS,FOLLOW_SAXS_in_crystalCoefcalcKeyword1260); 

                     value = 5;

                    }
                    break;
                case 8 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:331:4: SEQUENCE
                    {
                    match(input,SEQUENCE,FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1268); 

                     value = 6;

                    }
                    break;
                case 9 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:332:4: SAXSSEQ
                    {
                    match(input,SAXSSEQ,FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1275); 

                     value = 7;

                    }
                    break;
                case 10 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:333:4: SMALLMOLE
                    {
                    match(input,SMALLMOLE,FOLLOW_SMALLMOLE_in_crystalCoefcalcKeyword1282); 

                     value = 8;

                    }
                    break;
                case 11 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:334:4: CIF
                    {
                    match(input,CIF,FOLLOW_CIF_in_crystalCoefcalcKeyword1293); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:348:1: crystalDim returns [Map<Object, Object> properties] : DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) ;
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
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:351:3: ( DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:351:5: DIMENSION (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
            {
            match(input,DIMENSION,FOLLOW_DIMENSION_in_crystalDim1702); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:352:2: (a= FLOAT b= FLOAT c= FLOAT |e= FLOAT f= FLOAT |d= FLOAT )
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==FLOAT) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==FLOAT) ) {
                    int LA6_2 = input.LA(3);

                    if ( (LA6_2==FLOAT) ) {
                        alt6=1;
                    }
                    else if ( (LA6_2==EOF||(LA6_2 >= ABSCOEFCALC && LA6_2 <= ANGLEP)||(LA6_2 >= BEAM && LA6_2 <= CALCULATEPEESCAPE)||LA6_2==CIFNAME||(LA6_2 >= CONTAINERDENSITY && LA6_2 <= DECAYPARAM)||(LA6_2 >= DIFFRACTIONDECAYMODEL && LA6_2 <= DIMENSION)||LA6_2==FLRESOLUTION||LA6_2==GONIOMETERAXIS||(LA6_2 >= MATERIALELEMENTS && LA6_2 <= MATERIALTYPE)||LA6_2==MODELFILE||(LA6_2 >= NUMCARB && LA6_2 <= OILELEMENTS)||(LA6_2 >= PDBNAME && LA6_2 <= PERESOLUTION)||(LA6_2 >= PIXELSPERMICRON && LA6_2 <= PROTEINHEAVYATOMS)||LA6_2==SEQFILE||LA6_2==SEQUENCEFILE||(LA6_2 >= SMALLMOLEATOMS && LA6_2 <= SOLVENTHEAVYCONC)||LA6_2==SURROUNDINGHEAVYCONC||(LA6_2 >= TYPE && LA6_2 <= UNITCELL)||(LA6_2 >= WEDGE && LA6_2 <= WIREFRAMETYPE)) ) {
                        alt6=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 2, input);

                        throw nvae;

                    }
                }
                else if ( (LA6_1==EOF||(LA6_1 >= ABSCOEFCALC && LA6_1 <= ANGLEP)||(LA6_1 >= BEAM && LA6_1 <= CALCULATEPEESCAPE)||LA6_1==CIFNAME||(LA6_1 >= CONTAINERDENSITY && LA6_1 <= DECAYPARAM)||(LA6_1 >= DIFFRACTIONDECAYMODEL && LA6_1 <= DIMENSION)||LA6_1==FLRESOLUTION||LA6_1==GONIOMETERAXIS||(LA6_1 >= MATERIALELEMENTS && LA6_1 <= MATERIALTYPE)||LA6_1==MODELFILE||(LA6_1 >= NUMCARB && LA6_1 <= OILELEMENTS)||(LA6_1 >= PDBNAME && LA6_1 <= PERESOLUTION)||(LA6_1 >= PIXELSPERMICRON && LA6_1 <= PROTEINHEAVYATOMS)||LA6_1==SEQFILE||LA6_1==SEQUENCEFILE||(LA6_1 >= SMALLMOLEATOMS && LA6_1 <= SOLVENTHEAVYCONC)||LA6_1==SURROUNDINGHEAVYCONC||(LA6_1 >= TYPE && LA6_1 <= UNITCELL)||(LA6_1 >= WEDGE && LA6_1 <= WIREFRAMETYPE)) ) {
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:353:7: a= FLOAT b= FLOAT c= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1715); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1719); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1723); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((a!=null?a.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((b!=null?b.getText():null)));
                                                    properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble((c!=null?c.getText():null))); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:356:7: e= FLOAT f= FLOAT
                    {
                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1735); 

                    f=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1739); 

                     properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble((e!=null?e.getText():null)));
                        		       properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble((f!=null?f.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:358:7: d= FLOAT
                    {
                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalDim1751); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:362:1: crystalAngP returns [double value] : ANGLEP a= FLOAT ;
    public final double crystalAngP() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:363:2: ( ANGLEP a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:363:4: ANGLEP a= FLOAT
            {
            match(input,ANGLEP,FOLLOW_ANGLEP_in_crystalAngP1828); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngP1832); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:367:1: crystalAngL returns [double value] : ANGLEL a= FLOAT ;
    public final double crystalAngL() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:368:2: ( ANGLEL a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:368:4: ANGLEL a= FLOAT
            {
            match(input,ANGLEL,FOLLOW_ANGLEL_in_crystalAngL1887); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalAngL1891); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:372:1: crystalPPM returns [double ppm] : PIXELSPERMICRON FLOAT ;
    public final double crystalPPM() throws RecognitionException {
        double ppm = 0.0;


        Token FLOAT1=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:373:2: ( PIXELSPERMICRON FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:373:4: PIXELSPERMICRON FLOAT
            {
            match(input,PIXELSPERMICRON,FOLLOW_PIXELSPERMICRON_in_crystalPPM1945); 

            FLOAT1=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_crystalPPM1947); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:376:1: unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC] : UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? ;
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
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:377:2: ( UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )? )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:377:4: UNITCELL a= FLOAT b= FLOAT c= FLOAT (al= FLOAT be= FLOAT ga= FLOAT )?
            {
            match(input,UNITCELL,FOLLOW_UNITCELL_in_unitcell2045); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2049); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2053); 

            c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2057); 

            retval.dimA = Double.parseDouble((a!=null?a.getText():null));
            		 retval.dimB = Double.parseDouble((b!=null?b.getText():null));
            		 retval.dimC = Double.parseDouble((c!=null?c.getText():null)); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:381:7: (al= FLOAT be= FLOAT ga= FLOAT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FLOAT) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:381:8: al= FLOAT be= FLOAT ga= FLOAT
                    {
                    al=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2072); 

                    be=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2076); 

                    ga=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_unitcell2080); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:389:1: proteinConcentration returns [Double proteinConc] : ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT ;
    public final Double proteinConcentration() throws RecognitionException {
        Double proteinConc = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:390:2: ( ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:390:4: ( PROTEINCONCENTRATION | PROTEINCONC ) a= FLOAT
            {
            if ( (input.LA(1) >= PROTEINCONC && input.LA(1) <= PROTEINCONCENTRATION) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_proteinConcentration2168); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:394:1: nummonomers returns [int value] : NUMMONOMERS a= FLOAT ;
    public final int nummonomers() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:395:2: ( NUMMONOMERS a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:395:4: NUMMONOMERS a= FLOAT
            {
            match(input,NUMMONOMERS,FOLLOW_NUMMONOMERS_in_nummonomers2350); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_nummonomers2354); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:398:1: numresidues returns [int value] : NUMRESIDUES a= FLOAT ;
    public final int numresidues() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:399:2: ( NUMRESIDUES a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:399:4: NUMRESIDUES a= FLOAT
            {
            match(input,NUMRESIDUES,FOLLOW_NUMRESIDUES_in_numresidues2431); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numresidues2435); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:402:1: numRNA returns [int value] : NUMRNA a= FLOAT ;
    public final int numRNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:403:2: ( NUMRNA a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:403:4: NUMRNA a= FLOAT
            {
            match(input,NUMRNA,FOLLOW_NUMRNA_in_numRNA2513); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numRNA2517); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:406:1: numDNA returns [int value] : NUMDNA a= FLOAT ;
    public final int numDNA() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:407:2: ( NUMDNA a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:407:4: NUMDNA a= FLOAT
            {
            match(input,NUMDNA,FOLLOW_NUMDNA_in_numDNA2570); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numDNA2574); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:410:1: numcarb returns [int value] : NUMCARB a= FLOAT ;
    public final int numcarb() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:411:2: ( NUMCARB a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:411:4: NUMCARB a= FLOAT
            {
            match(input,NUMCARB,FOLLOW_NUMCARB_in_numcarb2626); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_numcarb2630); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:414:1: heavyProteinAtoms returns [List<String> names, List<Double> num;] : PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavyProteinAtoms_return heavyProteinAtoms() throws RecognitionException {
        InputfileParser.heavyProteinAtoms_return retval = new InputfileParser.heavyProteinAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:419:2: ( PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:419:4: PROTEINHEAVYATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,PROTEINHEAVYATOMS,FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2691); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:419:22: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:419:23: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavyProteinAtoms2696); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavyProteinAtoms2700); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:423:1: smallMoleAtoms returns [List<String> names, List<Double> num;] : SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.smallMoleAtoms_return smallMoleAtoms() throws RecognitionException {
        InputfileParser.smallMoleAtoms_return retval = new InputfileParser.smallMoleAtoms_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:428:2: ( SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:428:4: SMALLMOLEATOMS (a= ELEMENT b= FLOAT )+
            {
            match(input,SMALLMOLEATOMS,FOLLOW_SMALLMOLEATOMS_in_smallMoleAtoms2847); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:428:19: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:428:20: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_smallMoleAtoms2852); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_smallMoleAtoms2856); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:431:1: heavySolutionConc returns [List<String> names, List<Double> num;] : SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.heavySolutionConc_return heavySolutionConc() throws RecognitionException {
        InputfileParser.heavySolutionConc_return retval = new InputfileParser.heavySolutionConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:436:2: ( SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:436:4: SOLVENTHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SOLVENTHEAVYCONC,FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc2958); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:436:21: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:436:22: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_heavySolutionConc2963); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_heavySolutionConc2967); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:439:1: solventFraction returns [double solFrac] : SOLVENTFRACTION a= FLOAT ;
    public final double solventFraction() throws RecognitionException {
        double solFrac = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:440:2: ( SOLVENTFRACTION a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:440:4: SOLVENTFRACTION a= FLOAT
            {
            match(input,SOLVENTFRACTION,FOLLOW_SOLVENTFRACTION_in_solventFraction3073); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_solventFraction3077); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:443:1: pdb returns [String pdb] : PDBNAME (a= STRING |a= FLOAT ) ;
    public final String pdb() throws RecognitionException {
        String pdb = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:444:2: ( PDBNAME (a= STRING |a= FLOAT ) )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:444:4: PDBNAME (a= STRING |a= FLOAT )
            {
            match(input,PDBNAME,FOLLOW_PDBNAME_in_pdb3174); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:444:12: (a= STRING |a= FLOAT )
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:444:13: a= STRING
                    {
                    a=(Token)match(input,STRING,FOLLOW_STRING_in_pdb3179); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:444:22: a= FLOAT
                    {
                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_pdb3183); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:447:1: cif returns [String cif] : CIFNAME a= STRING ;
    public final String cif() throws RecognitionException {
        String cif = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:448:2: ( CIFNAME a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:448:4: CIFNAME a= STRING
            {
            match(input,CIFNAME,FOLLOW_CIFNAME_in_cif3221); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_cif3225); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:451:1: wireframeType returns [String value] : WIREFRAMETYPE a= STRING ;
    public final String wireframeType() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:452:2: ( WIREFRAMETYPE a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:452:4: WIREFRAMETYPE a= STRING
            {
            match(input,WIREFRAMETYPE,FOLLOW_WIREFRAMETYPE_in_wireframeType3262); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_wireframeType3266); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:455:1: modelFile returns [String value] : MODELFILE a= STRING ;
    public final String modelFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:456:2: ( MODELFILE a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:456:4: MODELFILE a= STRING
            {
            match(input,MODELFILE,FOLLOW_MODELFILE_in_modelFile3354); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_modelFile3358); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:459:1: calculatePEEscape returns [String value] : CALCULATEPEESCAPE a= STRING ;
    public final String calculatePEEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:460:2: ( CALCULATEPEESCAPE a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:460:4: CALCULATEPEESCAPE a= STRING
            {
            match(input,CALCULATEPEESCAPE,FOLLOW_CALCULATEPEESCAPE_in_calculatePEEscape3425); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculatePEEscape3429); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:464:1: goniometerAxis returns [double value] : GONIOMETERAXIS a= FLOAT ;
    public final double goniometerAxis() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:465:2: ( GONIOMETERAXIS a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:465:4: GONIOMETERAXIS a= FLOAT
            {
            match(input,GONIOMETERAXIS,FOLLOW_GONIOMETERAXIS_in_goniometerAxis3541); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_goniometerAxis3545); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:469:1: crystalContainerMaterial returns [int value] : ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword ;
    public final int crystalContainerMaterial() throws RecognitionException {
        int value = 0;


        int e =0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:470:2: ( ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:470:4: ( CONTAINERMATERIALTYPE | MATERIALTYPE ) e= crystalContainerKeyword
            {
            if ( input.LA(1)==CONTAINERMATERIALTYPE||input.LA(1)==MATERIALTYPE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial3654);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:473:1: crystalContainerKeyword returns [int value] : ( NONE | MIXTURE | ELEMENTAL );
    public final int crystalContainerKeyword() throws RecognitionException {
        int value = 0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:474:2: ( NONE | MIXTURE | ELEMENTAL )
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:474:4: NONE
                    {
                    match(input,NONE,FOLLOW_NONE_in_crystalContainerKeyword3847); 

                     value = 1; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:475:4: MIXTURE
                    {
                    match(input,MIXTURE,FOLLOW_MIXTURE_in_crystalContainerKeyword3856); 

                     value = 2; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:476:4: ELEMENTAL
                    {
                    match(input,ELEMENTAL,FOLLOW_ELEMENTAL_in_crystalContainerKeyword3864); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:482:1: containerThickness returns [double value] : CONTAINERTHICKNESS a= FLOAT ;
    public final double containerThickness() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:483:2: ( CONTAINERTHICKNESS a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:483:4: CONTAINERTHICKNESS a= FLOAT
            {
            match(input,CONTAINERTHICKNESS,FOLLOW_CONTAINERTHICKNESS_in_containerThickness4004); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerThickness4008); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:486:1: containerMaterialMixture returns [String value] : ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING ;
    public final String containerMaterialMixture() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:487:2: ( ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:487:4: ( CONTAINERMATERIALMIXTURE | MATERIALMIXTURE ) a= STRING
            {
            if ( input.LA(1)==CONTAINERMATERIALMIXTURE||input.LA(1)==MATERIALMIXTURE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_containerMaterialMixture4129); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:491:1: containerMaterialElements returns [List<String> names, List<Double> num;] : ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.containerMaterialElements_return containerMaterialElements() throws RecognitionException {
        InputfileParser.containerMaterialElements_return retval = new InputfileParser.containerMaterialElements_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:496:2: ( ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:496:4: ( CONTAINERMATERIALELEMENTS | MATERIALELEMENTS ) (a= ELEMENT b= FLOAT )+
            {
            if ( input.LA(1)==CONTAINERMATERIALELEMENTS||input.LA(1)==MATERIALELEMENTS ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:496:51: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:496:52: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_containerMaterialElements4365); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerMaterialElements4369); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:500:1: containerDensity returns [double value] : CONTAINERDENSITY a= FLOAT ;
    public final double containerDensity() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:501:2: ( CONTAINERDENSITY a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:501:4: CONTAINERDENSITY a= FLOAT
            {
            match(input,CONTAINERDENSITY,FOLLOW_CONTAINERDENSITY_in_containerDensity4604); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_containerDensity4608); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:504:1: sequenceFile returns [String value] : ( SEQUENCEFILE | SEQFILE ) a= STRING ;
    public final String sequenceFile() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:505:2: ( ( SEQUENCEFILE | SEQFILE ) a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:505:4: ( SEQUENCEFILE | SEQFILE ) a= STRING
            {
            if ( input.LA(1)==SEQFILE||input.LA(1)==SEQUENCEFILE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            a=(Token)match(input,STRING,FOLLOW_STRING_in_sequenceFile4719); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:509:1: calculateFLEscape returns [String value] : CALCULATEFLESCAPE a= STRING ;
    public final String calculateFLEscape() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:510:2: ( CALCULATEFLESCAPE a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:510:4: CALCULATEFLESCAPE a= STRING
            {
            match(input,CALCULATEFLESCAPE,FOLLOW_CALCULATEFLESCAPE_in_calculateFLEscape4842); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calculateFLEscape4846); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:514:1: flResolution returns [int value] : FLRESOLUTION a= FLOAT ;
    public final int flResolution() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:515:2: ( FLRESOLUTION a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:515:4: FLRESOLUTION a= FLOAT
            {
            match(input,FLRESOLUTION,FOLLOW_FLRESOLUTION_in_flResolution4957); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_flResolution4961); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:518:1: peResolution returns [int value] : PERESOLUTION a= FLOAT ;
    public final int peResolution() throws RecognitionException {
        int value = 0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:519:2: ( PERESOLUTION a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:519:4: PERESOLUTION a= FLOAT
            {
            match(input,PERESOLUTION,FOLLOW_PERESOLUTION_in_peResolution5043); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_peResolution5047); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:522:1: surroundingHeavyConc returns [List<String> names, List<Double> num;] : SURROUNDINGHEAVYCONC (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.surroundingHeavyConc_return surroundingHeavyConc() throws RecognitionException {
        InputfileParser.surroundingHeavyConc_return retval = new InputfileParser.surroundingHeavyConc_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:527:2: ( SURROUNDINGHEAVYCONC (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:527:4: SURROUNDINGHEAVYCONC (a= ELEMENT b= FLOAT )+
            {
            match(input,SURROUNDINGHEAVYCONC,FOLLOW_SURROUNDINGHEAVYCONC_in_surroundingHeavyConc5133); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:527:25: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:527:26: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_surroundingHeavyConc5138); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_surroundingHeavyConc5142); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:530:1: oilBased returns [String value] : OILBASED a= STRING ;
    public final String oilBased() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:531:2: ( OILBASED a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:531:4: OILBASED a= STRING
            {
            match(input,OILBASED,FOLLOW_OILBASED_in_oilBased5270); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_oilBased5274); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:534:1: calcSurrounding returns [String value] : CALCSURROUNDING a= STRING ;
    public final String calcSurrounding() throws RecognitionException {
        String value = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:535:2: ( CALCSURROUNDING a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:535:4: CALCSURROUNDING a= STRING
            {
            match(input,CALCSURROUNDING,FOLLOW_CALCSURROUNDING_in_calcSurrounding5336); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_calcSurrounding5340); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:539:1: oilElements returns [List<String> names, List<Double> num;] : OILELEMENTS (a= ELEMENT b= FLOAT )+ ;
    public final InputfileParser.oilElements_return oilElements() throws RecognitionException {
        InputfileParser.oilElements_return retval = new InputfileParser.oilElements_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;


        retval.names = new ArrayList<String>();
        retval.num = new ArrayList<Double>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:544:2: ( OILELEMENTS (a= ELEMENT b= FLOAT )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:544:4: OILELEMENTS (a= ELEMENT b= FLOAT )+
            {
            match(input,OILELEMENTS,FOLLOW_OILELEMENTS_in_oilElements5443); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:544:16: (a= ELEMENT b= FLOAT )+
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
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:544:17: a= ELEMENT b= FLOAT
            	    {
            	    a=(Token)match(input,ELEMENT,FOLLOW_ELEMENT_in_oilElements5448); 

            	    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_oilElements5452); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:547:1: oilDensity returns [double oildens] : OILDENSITY a= FLOAT ;
    public final double oilDensity() throws RecognitionException {
        double oildens = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:548:2: ( OILDENSITY a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:548:4: OILDENSITY a= FLOAT
            {
            match(input,OILDENSITY,FOLLOW_OILDENSITY_in_oilDensity5535); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_oilDensity5539); 

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


    protected static class beam_scope {
        String beamType;
        HashMap<Object, Object> beamProperties;
    }
    protected Stack beam_stack = new Stack();



    // $ANTLR start "beam"
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:552:1: beam returns [Beam bObj] : BEAM ( beamLine )+ ;
    public final Beam beam() throws RecognitionException {
        beam_stack.push(new beam_scope());
        Beam bObj = null;


         
        		((beam_scope)beam_stack.peek()).beamProperties = new HashMap<Object, Object>();

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:563:2: ( BEAM ( beamLine )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:563:4: BEAM ( beamLine )+
            {
            match(input,BEAM,FOLLOW_BEAM_in_beam5627); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:563:9: ( beamLine )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0 >= CIRCULAR && LA16_0 <= COLLIMATION)||LA16_0==ENERGY||LA16_0==FILE||(LA16_0 >= FLUX && LA16_0 <= FWHM)||LA16_0==HORIZONTAL||LA16_0==PIXELSIZE||LA16_0==RECTANGULAR||LA16_0==TYPE||LA16_0==VERTICAL) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:563:9: beamLine
            	    {
            	    pushFollow(FOLLOW_beamLine_in_beam5629);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:566:1: beamLine : ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize );
    public final void beamLine() throws RecognitionException {
        Token a=null;
        Double b =null;

        InputfileParser.beamFWHM_return c =null;

        Double d =null;

        Map<Object, Object> e =null;

        String f =null;

        Map<Object, Object> g =null;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:567:2: ( TYPE a= STRING |b= beamFlux |c= beamFWHM |d= beamEnergy |e= beamCollimation |f= beamFile |g= beamPixelSize )
            int alt17=7;
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
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }

            switch (alt17) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:567:4: TYPE a= STRING
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_beamLine5668); 

                    a=(Token)match(input,STRING,FOLLOW_STRING_in_beamLine5672); 

                     ((beam_scope)beam_stack.peek()).beamType = (a!=null?a.getText():null); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:568:4: b= beamFlux
                    {
                    pushFollow(FOLLOW_beamFlux_in_beamLine5690);
                    b=beamFlux();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FLUX, b); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:569:4: c= beamFWHM
                    {
                    pushFollow(FOLLOW_beamFWHM_in_beamLine5702);
                    c=beamFWHM();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_X, (c!=null?c.x:null)); 
                    	                           ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_FWHM_Y, (c!=null?c.y:null)); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:571:4: d= beamEnergy
                    {
                    pushFollow(FOLLOW_beamEnergy_in_beamLine5714);
                    d=beamEnergy();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_ENERGY, d); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:572:4: e= beamCollimation
                    {
                    pushFollow(FOLLOW_beamCollimation_in_beamLine5726);
                    e=beamCollimation();

                    state._fsp--;


                     if (e != null) {
                    							    ((beam_scope)beam_stack.peek()).beamProperties.putAll(e);
                    							   } 

                    }
                    break;
                case 6 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:575:4: f= beamFile
                    {
                    pushFollow(FOLLOW_beamFile_in_beamLine5737);
                    f=beamFile();

                    state._fsp--;


                     ((beam_scope)beam_stack.peek()).beamProperties.put(Beam.BEAM_EXTFILE, f); 

                    }
                    break;
                case 7 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:576:4: g= beamPixelSize
                    {
                    pushFollow(FOLLOW_beamPixelSize_in_beamLine5758);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:580:1: beamFlux returns [Double flux] : FLUX a= FLOAT ;
    public final Double beamFlux() throws RecognitionException {
        Double flux = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:581:2: ( FLUX a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:581:4: FLUX a= FLOAT
            {
            match(input,FLUX,FOLLOW_FLUX_in_beamFlux5784); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFlux5788); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:584:1: beamFWHM returns [Double x, Double y] : FWHM a= FLOAT b= FLOAT ;
    public final InputfileParser.beamFWHM_return beamFWHM() throws RecognitionException {
        InputfileParser.beamFWHM_return retval = new InputfileParser.beamFWHM_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:585:2: ( FWHM a= FLOAT b= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:585:4: FWHM a= FLOAT b= FLOAT
            {
            match(input,FWHM,FOLLOW_FWHM_in_beamFWHM5830); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM5834); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamFWHM5838); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:588:1: beamEnergy returns [Double energy] : ENERGY a= FLOAT ( KEV )? ;
    public final Double beamEnergy() throws RecognitionException {
        Double energy = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:589:2: ( ENERGY a= FLOAT ( KEV )? )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:589:4: ENERGY a= FLOAT ( KEV )?
            {
            match(input,ENERGY,FOLLOW_ENERGY_in_beamEnergy5880); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamEnergy5884); 

            energy = Double.parseDouble((a!=null?a.getText():null));

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:590:2: ( KEV )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==KEV) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:590:4: KEV
                    {
                    match(input,KEV,FOLLOW_KEV_in_beamEnergy5891); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:595:1: beamFile returns [String filename] : FILE a= STRING ;
    public final String beamFile() throws RecognitionException {
        String filename = null;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:596:2: ( FILE a= STRING )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:596:4: FILE a= STRING
            {
            match(input,FILE,FOLLOW_FILE_in_beamFile5969); 

            a=(Token)match(input,STRING,FOLLOW_STRING_in_beamFile5973); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:600:1: beamPixelSize returns [Map<Object, Object> properties] : PIXELSIZE a= FLOAT b= FLOAT ;
    public final Map<Object, Object> beamPixelSize() throws RecognitionException {
        Map<Object, Object> properties = null;


        Token a=null;
        Token b=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:601:5: ( PIXELSIZE a= FLOAT b= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:601:7: PIXELSIZE a= FLOAT b= FLOAT
            {
            match(input,PIXELSIZE,FOLLOW_PIXELSIZE_in_beamPixelSize6020); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize6024); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamPixelSize6028); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:608:1: beamCollimation returns [Map<Object, Object> properties] : ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR c= FLOAT f= FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT );
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
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:612:2: ( COLLIMATION | RECTANGULAR a= FLOAT b= FLOAT | CIRCULAR c= FLOAT f= FLOAT | HORIZONTAL d= FLOAT | VERTICAL e= FLOAT )
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
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:612:4: COLLIMATION
                    {
                    match(input,COLLIMATION,FOLLOW_COLLIMATION_in_beamCollimation6107); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:613:4: RECTANGULAR a= FLOAT b= FLOAT
                    {
                    match(input,RECTANGULAR,FOLLOW_RECTANGULAR_in_beamCollimation6113); 

                    a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation6117); 

                    b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation6121); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((a!=null?a.getText():null)));
                    	                                properties.put(Beam.BEAM_COLL_V, Double.parseDouble((b!=null?b.getText():null))); 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:615:4: CIRCULAR c= FLOAT f= FLOAT
                    {
                    match(input,CIRCULAR,FOLLOW_CIRCULAR_in_beamCollimation6128); 

                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation6132); 

                    f=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation6136); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((c!=null?c.getText():null)));
                    	                                properties.put(Beam.BEAM_COLL_V, Double.parseDouble((f!=null?f.getText():null))); 
                    	                                properties.put(Beam.BEAM_CIRCULAR, "TRUE"); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:618:4: HORIZONTAL d= FLOAT
                    {
                    match(input,HORIZONTAL,FOLLOW_HORIZONTAL_in_beamCollimation6143); 

                    d=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation6147); 

                     properties.put(Beam.BEAM_COLL_H, Double.parseDouble((d!=null?d.getText():null))); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:619:4: VERTICAL e= FLOAT
                    {
                    match(input,VERTICAL,FOLLOW_VERTICAL_in_beamCollimation6154); 

                    e=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_beamCollimation6158); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:629:1: wedge returns [Wedge wObj] : WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ ;
    public final Wedge wedge() throws RecognitionException {
        wedge_stack.push(new wedge_scope());
        Wedge wObj = null;


        Token a=null;
        Token b=null;

         

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:648:2: ( WEDGE a= FLOAT b= FLOAT ( wedgeLine )+ )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:648:4: WEDGE a= FLOAT b= FLOAT ( wedgeLine )+
            {
            match(input,WEDGE,FOLLOW_WEDGE_in_wedge6471); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge6475); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedge6479); 

            ((wedge_scope)wedge_stack.peek()).startAng = Double.parseDouble((a!=null?a.getText():null));
            							 ((wedge_scope)wedge_stack.peek()).endAng = Double.parseDouble((b!=null?b.getText():null)); 

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:650:4: ( wedgeLine )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==ANGULARRESOLUTION||LA20_0==EXPOSURETIME||LA20_0==ROTAXBEAMOFFSET||LA20_0==STARTOFFSET||LA20_0==TRANSLATEPERDEGREE) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:650:4: wedgeLine
            	    {
            	    pushFollow(FOLLOW_wedgeLine_in_wedge6486);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:653:1: wedgeLine : (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset );
    public final void wedgeLine() throws RecognitionException {
        double a =0.0;

        double b =0.0;

        InputfileParser.wedgeStartOffset_return c =null;

        InputfileParser.wedgeTranslate_return d =null;

        double e =0.0;


        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:654:2: (a= wedgeExposure |b= wedgeAngRes |c= wedgeStartOffset |d= wedgeTranslate |e= wedgeRotAxBeamOffset )
            int alt21=5;
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
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }

            switch (alt21) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:654:4: a= wedgeExposure
                    {
                    pushFollow(FOLLOW_wedgeExposure_in_wedgeLine6530);
                    a=wedgeExposure();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).expTime =a; 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:655:4: b= wedgeAngRes
                    {
                    pushFollow(FOLLOW_wedgeAngRes_in_wedgeLine6540);
                    b=wedgeAngRes();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).angRes =b; 

                    }
                    break;
                case 3 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:656:4: c= wedgeStartOffset
                    {
                    pushFollow(FOLLOW_wedgeStartOffset_in_wedgeLine6551);
                    c=wedgeStartOffset();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).offsetX =(c!=null?c.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetY =(c!=null?c.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).offsetZ =(c!=null?c.z:null); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:659:4: d= wedgeTranslate
                    {
                    pushFollow(FOLLOW_wedgeTranslate_in_wedgeLine6561);
                    d=wedgeTranslate();

                    state._fsp--;


                    ((wedge_scope)wedge_stack.peek()).translateX =(d!=null?d.x:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateY =(d!=null?d.y:null);
                    						  	 ((wedge_scope)wedge_stack.peek()).translateZ =(d!=null?d.z:null); 

                    }
                    break;
                case 5 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:662:4: e= wedgeRotAxBeamOffset
                    {
                    pushFollow(FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine6571);
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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:665:1: wedgeExposure returns [double value] : EXPOSURETIME a= FLOAT ;
    public final double wedgeExposure() throws RecognitionException {
        double value = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:666:2: ( EXPOSURETIME a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:666:4: EXPOSURETIME a= FLOAT
            {
            match(input,EXPOSURETIME,FOLLOW_EXPOSURETIME_in_wedgeExposure6588); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeExposure6592); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:669:1: wedgeAngRes returns [double res] : ANGULARRESOLUTION a= FLOAT ;
    public final double wedgeAngRes() throws RecognitionException {
        double res = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:670:2: ( ANGULARRESOLUTION a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:670:4: ANGULARRESOLUTION a= FLOAT
            {
            match(input,ANGULARRESOLUTION,FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes6674); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeAngRes6678); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:673:1: wedgeStartOffset returns [Double x, Double y, Double z] : STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeStartOffset_return wedgeStartOffset() throws RecognitionException {
        InputfileParser.wedgeStartOffset_return retval = new InputfileParser.wedgeStartOffset_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:674:2: ( STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:674:4: STARTOFFSET a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,STARTOFFSET,FOLLOW_STARTOFFSET_in_wedgeStartOffset6785); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset6789); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset6793); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:675:17: (c= FLOAT )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==FLOAT) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:675:17: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeStartOffset6814); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:679:1: wedgeTranslate returns [Double x, Double y, Double z] : TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? ;
    public final InputfileParser.wedgeTranslate_return wedgeTranslate() throws RecognitionException {
        InputfileParser.wedgeTranslate_return retval = new InputfileParser.wedgeTranslate_return();
        retval.start = input.LT(1);


        Token a=null;
        Token b=null;
        Token c=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:680:2: ( TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )? )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:680:4: TRANSLATEPERDEGREE a= FLOAT b= FLOAT (c= FLOAT )?
            {
            match(input,TRANSLATEPERDEGREE,FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate6908); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate6912); 

            b=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate6916); 

            retval.x = Double.parseDouble((a!=null?a.getText():null)); retval.y = Double.parseDouble((b!=null?b.getText():null));

            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:681:24: (c= FLOAT )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==FLOAT) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:681:24: c= FLOAT
                    {
                    c=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeTranslate6944); 

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
    // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:685:1: wedgeRotAxBeamOffset returns [double delta] : ROTAXBEAMOFFSET a= FLOAT ;
    public final double wedgeRotAxBeamOffset() throws RecognitionException {
        double delta = 0.0;


        Token a=null;

        try {
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:686:2: ( ROTAXBEAMOFFSET a= FLOAT )
            // C:\\Users\\Josh\\git\\RADDOSE-3D\\lib\\antlrworks-parsergenerator\\Inputfile.g:686:4: ROTAXBEAMOFFSET a= FLOAT
            {
            match(input,ROTAXBEAMOFFSET,FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset7080); 

            a=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_wedgeRotAxBeamOffset7084); 

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


 

    public static final BitSet FOLLOW_crystal_in_configfile47 = new BitSet(new long[]{0x0000000000800200L,0x0000000001000000L});
    public static final BitSet FOLLOW_wedge_in_configfile65 = new BitSet(new long[]{0x0000000000800200L,0x0000000001000000L});
    public static final BitSet FOLLOW_beam_in_configfile85 = new BitSet(new long[]{0x0000000000800200L,0x0000000001000000L});
    public static final BitSet FOLLOW_EOF_in_configfile105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CRYSTAL_in_crystal134 = new BitSet(new long[]{0xB7FAE1201B7C5C70L,0x000000000269CA07L});
    public static final BitSet FOLLOW_crystalLine_in_crystal136 = new BitSet(new long[]{0xB7FAE1201B7C5C72L,0x000000000269CA07L});
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
    public static final BitSet FOLLOW_TYPE_in_crystalType703 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_crystalType707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalDDM749 = new BitSet(new long[]{0x0000180000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_crystalDDMKeyword_in_crystalDDM761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIMPLE_in_crystalDDMKeyword909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LINEAR_in_crystalDDMKeyword916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEAL_in_crystalDDMKeyword923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECAYPARAM_in_crystalDecayParam1043 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam1047 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam1051 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDecayParam1055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABSCOEFCALC_in_crystalCoefcalc1127 = new BitSet(new long[]{0x0800000024002100L,0x0000000000002598L});
    public static final BitSet FOLLOW_crystalCoefcalcKeyword_in_crystalCoefcalc1131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DUMMY_in_crystalCoefcalcKeyword1210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVERAGE_in_crystalCoefcalcKeyword1220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_crystalCoefcalcKeyword1228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDJAVA_in_crystalCoefcalcKeyword1236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RDFORTAN_in_crystalCoefcalcKeyword1243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDB_in_crystalCoefcalcKeyword1250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXS_in_crystalCoefcalcKeyword1260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEQUENCE_in_crystalCoefcalcKeyword1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAXSSEQ_in_crystalCoefcalcKeyword1275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SMALLMOLE_in_crystalCoefcalcKeyword1282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIF_in_crystalCoefcalcKeyword1293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_crystalDim1702 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1715 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1719 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1735 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_crystalDim1751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEP_in_crystalAngP1828 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngP1832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGLEL_in_crystalAngL1887 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalAngL1891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSPERMICRON_in_crystalPPM1945 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_crystalPPM1947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNITCELL_in_unitcell2045 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2049 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2053 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2057 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2072 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2076 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_unitcell2080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_proteinConcentration2158 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_proteinConcentration2168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMMONOMERS_in_nummonomers2350 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_nummonomers2354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRESIDUES_in_numresidues2431 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numresidues2435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMRNA_in_numRNA2513 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numRNA2517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMDNA_in_numDNA2570 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numDNA2574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMCARB_in_numcarb2626 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_numcarb2630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROTEINHEAVYATOMS_in_heavyProteinAtoms2691 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavyProteinAtoms2696 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavyProteinAtoms2700 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_SMALLMOLEATOMS_in_smallMoleAtoms2847 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ELEMENT_in_smallMoleAtoms2852 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_smallMoleAtoms2856 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_SOLVENTHEAVYCONC_in_heavySolutionConc2958 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ELEMENT_in_heavySolutionConc2963 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_heavySolutionConc2967 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_SOLVENTFRACTION_in_solventFraction3073 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_solventFraction3077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PDBNAME_in_pdb3174 = new BitSet(new long[]{0x0000001000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_pdb3179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_pdb3183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIFNAME_in_cif3221 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_cif3225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WIREFRAMETYPE_in_wireframeType3262 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_wireframeType3266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODELFILE_in_modelFile3354 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_modelFile3358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEPEESCAPE_in_calculatePEEscape3425 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_calculatePEEscape3429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GONIOMETERAXIS_in_goniometerAxis3541 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_goniometerAxis3545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_crystalContainerMaterial3642 = new BitSet(new long[]{0x0005000080000000L});
    public static final BitSet FOLLOW_crystalContainerKeyword_in_crystalContainerMaterial3654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NONE_in_crystalContainerKeyword3847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIXTURE_in_crystalContainerKeyword3856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELEMENTAL_in_crystalContainerKeyword3864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINERTHICKNESS_in_containerThickness4004 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerThickness4008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialMixture4119 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_containerMaterialMixture4129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_containerMaterialElements4354 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ELEMENT_in_containerMaterialElements4365 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerMaterialElements4369 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_CONTAINERDENSITY_in_containerDensity4604 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_containerDensity4608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_sequenceFile4709 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_sequenceFile4719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCULATEFLESCAPE_in_calculateFLEscape4842 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_calculateFLEscape4846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLRESOLUTION_in_flResolution4957 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_flResolution4961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERESOLUTION_in_peResolution5043 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_peResolution5047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SURROUNDINGHEAVYCONC_in_surroundingHeavyConc5133 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ELEMENT_in_surroundingHeavyConc5138 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_surroundingHeavyConc5142 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_OILBASED_in_oilBased5270 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_oilBased5274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALCSURROUNDING_in_calcSurrounding5336 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_calcSurrounding5340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OILELEMENTS_in_oilElements5443 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_ELEMENT_in_oilElements5448 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_oilElements5452 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_OILDENSITY_in_oilDensity5535 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_oilDensity5539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEAM_in_beam5627 = new BitSet(new long[]{0x400002C900018000L,0x0000000000A00020L});
    public static final BitSet FOLLOW_beamLine_in_beam5629 = new BitSet(new long[]{0x400002C900018002L,0x0000000000A00020L});
    public static final BitSet FOLLOW_TYPE_in_beamLine5668 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_beamLine5672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFlux_in_beamLine5690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFWHM_in_beamLine5702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamEnergy_in_beamLine5714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamCollimation_in_beamLine5726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamFile_in_beamLine5737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_beamPixelSize_in_beamLine5758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLUX_in_beamFlux5784 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFlux5788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FWHM_in_beamFWHM5830 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM5834 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamFWHM5838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENERGY_in_beamEnergy5880 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamEnergy5884 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_KEV_in_beamEnergy5891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_beamFile5969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_STRING_in_beamFile5973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PIXELSIZE_in_beamPixelSize6020 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize6024 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamPixelSize6028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLIMATION_in_beamCollimation6107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RECTANGULAR_in_beamCollimation6113 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation6117 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation6121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCULAR_in_beamCollimation6128 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation6132 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation6136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HORIZONTAL_in_beamCollimation6143 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation6147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERTICAL_in_beamCollimation6154 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_beamCollimation6158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WEDGE_in_wedge6471 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge6475 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedge6479 = new BitSet(new long[]{0x0000000400000080L,0x0000000000120040L});
    public static final BitSet FOLLOW_wedgeLine_in_wedge6486 = new BitSet(new long[]{0x0000000400000082L,0x0000000000120040L});
    public static final BitSet FOLLOW_wedgeExposure_in_wedgeLine6530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeAngRes_in_wedgeLine6540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeStartOffset_in_wedgeLine6551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeTranslate_in_wedgeLine6561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_wedgeRotAxBeamOffset_in_wedgeLine6571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPOSURETIME_in_wedgeExposure6588 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeExposure6592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ANGULARRESOLUTION_in_wedgeAngRes6674 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeAngRes6678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTOFFSET_in_wedgeStartOffset6785 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset6789 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset6793 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeStartOffset6814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSLATEPERDEGREE_in_wedgeTranslate6908 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate6912 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate6916 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeTranslate6944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROTAXBEAMOFFSET_in_wedgeRotAxBeamOffset7080 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_FLOAT_in_wedgeRotAxBeamOffset7084 = new BitSet(new long[]{0x0000000000000002L});

}