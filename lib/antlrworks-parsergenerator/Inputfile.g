/** "Here's an initializer, here's an input file. Good luck and God's Speed." */
grammar Inputfile;

@header {
package se.raddo.raddose3D.parser;
import se.raddo.raddose3D.*;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
}

@lexer::header {
package se.raddo.raddose3D.parser;
}

@members {
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
}
	
configfile:
          ( a=crystal { raddoseInitializer.setCrystal($a.cObj); }
          | b=wedge   { raddoseInitializer.exposeWedge($b.wObj); }
          | c=beam    { raddoseInitializer.setBeam($c.bObj); }
          )* EOF;

// ------------------------------------------------------------------

crystal returns [Crystal cObj]
scope {
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
@init { 
$crystal::crystalCoefCalc = 2; // 0 = error, 1 = Simple, 2 = DEFAULT, 3 = RDV2, 4 = PDB, 5 = SAXS
		$crystal::crystalProperties = new HashMap<Object, Object>();
}
@after { 
if ($crystal::crystalCoefCalc == 1) {
  $crystal::crystalCoefCalcClass = new CoefCalcAverage();
}
if ($crystal::crystalCoefCalc == 2)
{
  $crystal::crystalCoefCalcClass = new CoefCalcFromParams($crystal::cellA, $crystal::cellB, $crystal::cellC, $crystal::cellAl, $crystal::cellBe, $crystal::cellGa,
  													$crystal::numMon, $crystal::numRes, $crystal::numRNA, $crystal::numDNA,
  													$crystal::heavyProteinAtomNames, $crystal::heavyProteinAtomNums,
  													$crystal::heavySolutionConcNames, $crystal::heavySolutionConcNums,
  													$crystal::solFrac);
}

if ($crystal::crystalCoefCalc == 3) {
  $crystal::crystalCoefCalcClass = new CoefCalcRaddose($crystal::cellA, $crystal::cellB, $crystal::cellC, $crystal::cellAl, $crystal::cellBe, $crystal::cellGa,
  													$crystal::numMon, $crystal::numRes, $crystal::numRNA, $crystal::numDNA,
  													$crystal::heavyProteinAtomNames, $crystal::heavyProteinAtomNums,
  													$crystal::heavySolutionConcNames, $crystal::heavySolutionConcNums,
  													$crystal::solFrac);
}

if ($crystal::crystalCoefCalc == 4)
{
  if ($crystal::heavySolutionConcNames.size() > 0)
  	$crystal::crystalCoefCalcClass = new CoefCalcFromPDB($crystal::pdb, $crystal::heavySolutionConcNames, $crystal::heavySolutionConcNums);
  else
	$crystal::crystalCoefCalcClass = new CoefCalcFromPDB($crystal::pdb);
  													  													
}

if ($crystal::crystalCoefCalc == 5)
{
  $crystal::crystalCoefCalcClass = new CoefCalcSAXS($crystal::cellA, $crystal::cellB, $crystal::cellC, $crystal::cellAl, $crystal::cellBe, $crystal::cellGa,
  													$crystal::numRes, $crystal::numRNA, $crystal::numDNA,
  													$crystal::heavyProteinAtomNames, $crystal::heavyProteinAtomNums,
  													$crystal::heavySolutionConcNames, $crystal::heavySolutionConcNums,
  													$crystal::solFrac, $crystal::proteinConc);
}

$crystal::crystalProperties.put(Crystal.CRYSTAL_COEFCALC, $crystal::crystalCoefCalcClass);

if ($crystal::crystalDdm == 1)
{
	$crystal::crystalDdmClass = new DDMSimple();
}

if ($crystal::crystalDdm == 2)
{
	$crystal::crystalDdmClass = new DDMLinear();
}

if ($crystal::crystalDdm == 3)
{
	$crystal::crystalDdmClass = new DDMLeal($crystal::gammaParam, $crystal::b0Param, $crystal::betaParam);
}

$crystal::crystalProperties.put(Crystal.CRYSTAL_DDM, $crystal::crystalDdmClass);


$cObj = crystalFactory.createCrystal($crystal::crystalType, $crystal::crystalProperties);
}
	: CRYSTAL crystalLine+ ;
CRYSTAL	: ('C'|'c')('R'|'r')('Y'|'y')('S'|'s')('T'|'t')('A'|'a')('L'|'l') ;

crystalLine 
	: a=crystalType			{ $crystal::crystalType 			= $a.crystalType; }
	| b=crystalDDM 			{ $crystal::crystalDdm 				= $b.value; }
	| c=crystalCoefcalc		{ $crystal::crystalCoefCalc			= $c.value; }
	| d=crystalDim			{ if ($d.properties != null) {
							   $crystal::crystalProperties.putAll($d.properties);
							  }; }
	| e=crystalPPM			{ $crystal::crystalProperties.put(Crystal.CRYSTAL_RESOLUTION, $e.ppm); }
	| f=crystalAngP 		{ $crystal::crystalProperties.put(Crystal.CRYSTAL_ANGLE_P, $f.value); }
	| g=crystalAngL			{ $crystal::crystalProperties.put(Crystal.CRYSTAL_ANGLE_L, $g.value); }
	| h=crystalDecayParam		{ $crystal::gammaParam 					= $h.gammaParam; 
	                           			  $crystal::b0Param 					= $h.b0Param; 
	                           			  $crystal::betaParam 					= $h.betaParam; }
	| m=unitcell			{ $crystal::cellA					= $m.dimA; 
   							  $crystal::cellB 					= $m.dimB; 	
							  $crystal::cellC 					= $m.dimC;	
							  $crystal::cellAl					= $m.angA;
   							  $crystal::cellBe					= $m.angB; 	
							  $crystal::cellGa 					= $m.angC;	}
	| n=nummonomers 		{ $crystal::numMon					= $n.value;	}
	| o=numresidues 		{ $crystal::numRes					= $o.value;	}
	| p=numRNA 				{ $crystal::numRNA					= $p.value;	}
	| q=numDNA 				{ $crystal::numDNA					= $q.value;	}
	| r=heavyProteinAtoms	{ $crystal::heavyProteinAtomNames	= $r.names;
							  $crystal::heavyProteinAtomNums	= $r.num;	}
	| s=heavySolutionConc	{ $crystal::heavySolutionConcNames	= $s.names;
							  $crystal::heavySolutionConcNums	= $s.num;	}
	| t=solventFraction		{ $crystal::solFrac					= $t.solFrac; }
	| u=pdb					{ $crystal::pdb						= $u.pdb; }
	| v=wireframeType			{ $crystal::crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_TYPE, $v.value); }
	| w=modelFile				{ $crystal::crystalProperties.put(Crystal.CRYSTAL_WIREFRAME_FILE, $w.value); }
	| x=calculateEscape		{ $crystal::crystalProperties.put(Crystal.CRYSTAL_ELECTRON_ESCAPE, $x.value); }
	| y=proteinConcentration	{ $crystal::proteinConc					= $y.proteinConc;}
	  
	;

	
crystalType returns [String crystalType]
	: TYPE e=STRING {$crystalType = $e.text;};
TYPE : ('T'|'t')('Y'|'y')('P'|'p')('E'|'e') ;

crystalDDM returns [int value]
	: ( DIFFRACTIONDECAYMODEL | DDM ) e=crystalDDMKeyword { $value = $e.value; };
DIFFRACTIONDECAYMODEL : ('D'|'d')('I'|'i')('F'|'f')('F'|'f')('R'|'r')('A'|'a')('C'|'c')('T'|'t')('I'|'i')('O'|'o')('N'|'n')('D'|'d')('E'|'e')('C'|'c')('A'|'a')('Y'|'y')('M'|'m')('O'|'o')('D'|'d')('E'|'e')('L'|'l') ;
DDM : ('D'|'d')('D'|'d')('M'|'m') ;
crystalDDMKeyword returns [int value]
	: SIMPLE { $value = 1; }
	| LINEAR { $value = 2; }
	| LEAL   { $value = 3; }
	;
SIMPLE : ('S'|'s')('I'|'i')('M'|'m')('P'|'p')('L'|'l')('E'|'e') ;
LINEAR : ('L'|'l')('I'|'i')('N'|'n')('E'|'e')('A'|'a')('R'|'r') ;
LEAL : ('L'|'l')('E'|'e')('A'|'a')('L'|'l') ;

crystalDecayParam returns [Double gammaParam, Double b0Param, Double betaParam]
	: DECAYPARAM a=FLOAT b=FLOAT c=FLOAT {$gammaParam = Double.parseDouble($a.text); $b0Param = Double.parseDouble($b.text); $betaParam = Double.parseDouble($c.text);};
DECAYPARAM  : ('D'|'d')('E'|'e')('C'|'c')('A'|'a')('Y'|'y')('P'|'p')('A'|'a')('R'|'r')('A'|'a')('M'|'m');

crystalCoefcalc returns [int value]
	: ABSCOEFCALC a=crystalCoefcalcKeyword  { $value = $a.value; };
ABSCOEFCALC : (('A'|'a')('B'|'b')('S'|'s'))?('C'|'c')('O'|'o')('E'|'e')('F'|'f')('C'|'c')('A'|'a')('L'|'l')('C'|'c');
crystalCoefcalcKeyword returns [int value]
	: DUMMY   	{ $value = 1;}
	| AVERAGE 	{ $value = 1;}
	| DEFAULT 	{ $value = 2;}
	| RDJAVA	{ $value = 2;}
	| RDFORTAN	{ $value = 3;}
	| PDB	  	{ $value = 4;}
	| SAXS		{ $value = 5;}
	;
DUMMY : ('D'|'d')('U'|'u')('M'|'m')('M'|'m')('Y'|'y') ;
DEFAULT	: ('D'|'d')('E'|'e')('F'|'f')('A'|'a')('U'|'u')('L'|'l')('T'|'t');
AVERAGE : ('A'|'a')('V'|'v')('E'|'e')('R'|'r')('A'|'a')('G'|'g')('E'|'e') ;
RDFORTAN : ('R'|'r')('D'|'d')('V'|'v')('2'|'3')? ;
RDJAVA : ('R'|'r')('D'|'d')('3')('D'|'d')? ;
PDB : ('E'|'e')('X'|'x')('P'|'p');
SAXS : ('S'|'s')('A'|'a')('X'|'x')('S'|'s');

crystalDim returns [Map<Object, Object> properties]
@init { 
		$properties = new HashMap<Object, Object>();
}	: DIMENSION
	(
      a=FLOAT b=FLOAT c=FLOAT { $properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble($a.text));
                                $properties.put(Crystal.CRYSTAL_DIM_Y, Double.parseDouble($b.text));
                                $properties.put(Crystal.CRYSTAL_DIM_Z, Double.parseDouble($c.text)); }	
    | d=FLOAT { $properties.put(Crystal.CRYSTAL_DIM_X, Double.parseDouble($d.text)); }
	) ;
DIMENSION : ('D'|'d')('I'|'i')('M'|'m')('E'|'e')('N'|'n')('S'|'s')('I'|'i')('O'|'o')('N'|'n')('S'|'s')? ;

crystalAngP returns [double value]
	: ANGLEP a=FLOAT {$value = Double.parseDouble($a.text);}
		;
ANGLEP : ('A'|'a')('N'|'n')('G'|'g')('L'|'l')('E'|'e')('P'|'p') ;

crystalAngL returns [double value]
	: ANGLEL a=FLOAT {$value = Double.parseDouble($a.text);}
	;
ANGLEL : ('A'|'a')('N'|'n')('G'|'g')('L'|'l')('E'|'e')('L'|'l') ;

crystalPPM returns [double ppm]
	: PIXELSPERMICRON FLOAT {$ppm = Double.parseDouble($FLOAT.text);};
PIXELSPERMICRON : ('P'|'p')('I'|'i')('X'|'x')('E'|'e')('L'|'l')('S'|'s')('P'|'p')('E'|'e')('R'|'r')('M'|'m')('I'|'i')('C'|'c')('R'|'r')('O'|'o')('N'|'n') ;
	
unitcell returns [Double dimA, Double dimB, Double dimC, Double angA, Double angB, Double angC]
	: UNITCELL a=FLOAT b=FLOAT c=FLOAT
		{$dimA = Double.parseDouble($a.text);
		 $dimB = Double.parseDouble($b.text);
		 $dimC = Double.parseDouble($c.text); }
      (al=FLOAT be=FLOAT ga=FLOAT
       {$angA = Double.parseDouble($al.text);
	 	$angB = Double.parseDouble($be.text);
		$angC = Double.parseDouble($ga.text); }
		)? 
	;
UNITCELL : ('U'|'u')('N'|'n')('I'|'i')('T'|'t')('C'|'c')('E'|'e')('L'|'l')('L'|'l') ;
	
proteinConcentration returns [Double proteinConc]
	: PROTEINCONCENTRATION a=FLOAT {$proteinConc = Double.parseDouble($a.text);};
PROTEINCONCENTRATION: ('P'|'p')('R'|'r')('O'|'o')('T'|'t')('E'|'e')('I'|'i')('N'|'n')('C'|'c')('O'|'o')('N'|'n')('C'|'c') ;

nummonomers returns [int value]
	: NUMMONOMERS a=FLOAT {$value = Integer.parseInt($a.text);};
NUMMONOMERS: ('N'|'n')('U'|'u')('M'|'m')('M'|'m')('O'|'o')('N'|'n')('O'|'o')('M'|'m')('E'|'e')('R'|'r')('S'|'s') ;
	
numresidues returns [int value]
	: NUMRESIDUES a=FLOAT {$value = Integer.parseInt($a.text);};
NUMRESIDUES : ('N'|'n')('U'|'u')('M'|'m')('R'|'r')('E'|'e')('S'|'s')('I'|'i')('D'|'d')('U'|'u')('E'|'e')('S'|'s') ;
	
numRNA returns [int value]
	: NUMRNA a=FLOAT {$value = Integer.parseInt($a.text);};
NUMRNA : ('N'|'n')('U'|'u')('M'|'m')('R'|'r')('N'|'n')('A'|'a') ;
	
numDNA returns [int value]
	: NUMDNA a=FLOAT {$value = Integer.parseInt($a.text);};
NUMDNA : ('N'|'n')('U'|'u')('M'|'m')('D'|'d')('N'|'n')('A'|'a') ;

heavyProteinAtoms returns [List<String> names, List<Double> num;]
@init{
$names 	= new ArrayList<String>();
$num	= new ArrayList<Double>();
}
	: PROTEINHEAVYATOMS (a=ELEMENT b=FLOAT {$names.add($a.text); $num.add(Double.parseDouble($b.text)); } )+ ; 	
PROTEINHEAVYATOMS : ('P'|'p')('R'|'r')('O'|'o')('T'|'t')('E'|'e')('I'|'i')('N'|'n')('H'|'h')('E'|'e')('A'|'a')('V'|'v')('Y'|'y')('A'|'a')('T'|'t')('O'|'o')('M'|'m')('S'|'s') ;
ELEMENT : ('A'..'Z' | 'a'..'z')('A'..'Z' | 'a'..'z')? ;

heavySolutionConc returns [List<String> names, List<Double> num;]
@init{
$names 	= new ArrayList<String>();
$num	= new ArrayList<Double>();
}
	: SOLVENTHEAVYCONC (a=ELEMENT b=FLOAT {$names.add($a.text); $num.add(Double.parseDouble($b.text)); } )+ ;
SOLVENTHEAVYCONC : ('S'|'s')('O'|'o')('L'|'l')('V'|'v')('E'|'e')('N'|'n')('T'|'t')('H'|'h')('E'|'e')('A'|'a')('V'|'v')('Y'|'y')('C'|'c')('O'|'o')('N'|'n')('C'|'c') ;

solventFraction returns [double solFrac]
	: SOLVENTFRACTION a=FLOAT {$solFrac = Double.parseDouble($a.text);};
SOLVENTFRACTION : ('S'|'s')('O'|'o')('L'|'l')('V'|'v')('E'|'e')('N'|'n')('T'|'t')('F'|'f')('R'|'r')('A'|'a')('C'|'c')('T'|'t')('I'|'i')('O'|'o')('N'|'n') ;

pdb returns [String pdb]
	: PDBNAME a=STRING {$pdb = $a.text;};
PDBNAME : ('P'|'p')('D'|'d')('B'|'b') ;

wireframeType returns [String value]
	: WIREFRAMETYPE a=STRING {$value = $a.text;};
WIREFRAMETYPE : ('W'|'w')('I'|'i')('R'|'r')('E'|'e')('F'|'f')('R'|'r')('A'|'a')('M'|'m')('E'|'e')('T'|'t')('Y'|'y'|)('P'|'p')('E'|'e') ;

modelFile returns [String value]
	: MODELFILE a=STRING {$value = $a.text;};
MODELFILE : ('M'|'m')('O'|'o')('D'|'d')('E'|'e')('L'|'l')('F'|'f')('I'|'i')('L'|'l')('E'|'e') ;

calculateEscape returns [String value]
	: CALCULATEESCAPE a=STRING {$value = $a.text;};
CALCULATEESCAPE  
	:	 ('C'|'c')('A'|'a')('L'|'l')('C'|'c')('U'|'u')('L'|'l')('A'|'a')('T'|'t')('E'|'e')('E'|'e')('S'|'s')('C'|'c')('A'|'a')('P'|'p')('E'|'e') ;


// ------------------------------------------------------------------
beam returns [Beam bObj]
scope {
		String beamType;
		HashMap<Object, Object> beamProperties;
}
@init { 
		$beam::beamProperties = new HashMap<Object, Object>();
}
@after { 
		$bObj = beamFactory.createBeam($beam::beamType, $beam::beamProperties);
}
	: BEAM beamLine+ ;
BEAM : ('B'|'b')('E'|'e')('A'|'a')('M'|'m') ;

beamLine 
	: TYPE a=STRING          { $beam::beamType = $a.text; }
	| b=beamFlux			 { $beam::beamProperties.put(Beam.BEAM_FLUX, $b.flux); }
	| c=beamFWHM			 { $beam::beamProperties.put(Beam.BEAM_FWHM_X, $c.x); 
	                           $beam::beamProperties.put(Beam.BEAM_FWHM_Y, $c.y); }
	| d=beamEnergy			 { $beam::beamProperties.put(Beam.BEAM_ENERGY, $d.energy); }
	| e=beamCollimation		 { if ($e.properties != null) {
							    $beam::beamProperties.putAll($e.properties);
							   } }
	| f=beamFile             { $beam::beamProperties.put(Beam.BEAM_EXTFILE, $f.filename); }
	| g=beamPixelSize        { $beam::beamProperties.putAll($g.properties); }
	;

beamFlux returns [Double flux]
	: FLUX a=FLOAT {$flux = Double.parseDouble($a.text);};
FLUX : ('F'|'f')('L'|'l')('U'|'u')('X'|'x') ;

beamFWHM returns [Double x, Double y]
	: FWHM a=FLOAT b=FLOAT {$x = Double.parseDouble($a.text); $y = Double.parseDouble($b.text);};
FWHM : ('F'|'f')('W'|'w')('H'|'h')('M'|'m') ;

beamEnergy returns [Double energy]
	: ENERGY a=FLOAT {$energy = Double.parseDouble($a.text);}
	( KEV )? 
	;
ENERGY : ('E'|'e')('N'|'n')('E'|'e')('R'|'r')('G'|'g')('Y'|'y') ;
KEV : ('K'|'k')('E'|'e')('V'|'v') ;

beamFile returns [String filename]
	: FILE a=STRING {$filename = $a.text;}
	;
FILE : ('F'|'f')('I'|'i')('L'|'l')('E'|'e') ;

beamPixelSize returns [Map<Object, Object> properties]
    : PIXELSIZE a=FLOAT b=FLOAT {$properties = new HashMap<Object, Object>();
		 $properties.put(Beam.BEAM_PIXSIZE_X, Double.parseDouble($a.text));
		 $properties.put(Beam.BEAM_PIXSIZE_Y, Double.parseDouble($b.text)); }
    ;
PIXELSIZE 
	:	 ('P'|'p')('I'|'i')('X'|'x')('E'|'e')('L'|'l')('S'|'s')('I'|'i')('Z'|'z')('E'|'e');

beamCollimation returns [Map<Object, Object> properties]
@init { 
		$properties = new HashMap<Object, Object>();
}
	: COLLIMATION 
	| RECTANGULAR a=FLOAT b=FLOAT { $properties.put(Beam.BEAM_COLL_H, Double.parseDouble($a.text));
	                                $properties.put(Beam.BEAM_COLL_V, Double.parseDouble($b.text)); }
	| CIRCULAR FLOAT 
	| HORIZONTAL d=FLOAT { $properties.put(Beam.BEAM_COLL_H, Double.parseDouble($d.text)); }
	| VERTICAL e=FLOAT   { $properties.put(Beam.BEAM_COLL_V, Double.parseDouble($e.text)); }
	;
COLLIMATION : ('C'|'c')('O'|'o')('L'|'l')('L'|'l')('I'|'i')('M'|'m')('A'|'a')('T'|'t')('I'|'i')('O'|'o')('N'|'n') ;
RECTANGULAR : ('R'|'r')('E'|'e')('C'|'c')('T'|'t')('A'|'a')('N'|'n')('G'|'g')('U'|'u')('L'|'l')('A'|'a')('R'|'r') ;
CIRCULAR  : ('C'|'c')('I'|'i')('R'|'r')('C'|'c')('U'|'u')('L'|'l')('A'|'a')('R'|'r') ;
HORIZONTAL  : ('H'|'h')('O'|'o')('R'|'r')('I'|'i')('Z'|'z')('O'|'o')('N'|'n')('T'|'t')('A'|'a')('L'|'l') ;
VERTICAL  : ('V'|'v')('E'|'e')('R'|'r')('T'|'t')('I'|'i')('C'|'c')('A'|'a')('L'|'l') ;

// ------------------------------------------------------------------

wedge returns [Wedge wObj]
scope {	
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
@init { 
}
@after { 
wObj = new Wedge($wedge::angRes, $wedge::startAng, $wedge::endAng, $wedge::expTime, $wedge::offsetX, $wedge::offsetY, $wedge::offsetZ, $wedge::translateX, $wedge::translateY, $wedge::translateZ, $wedge::rotationOffset);
}
	: WEDGE a=FLOAT b=FLOAT {$wedge::startAng = Double.parseDouble($a.text);
							 $wedge::endAng = Double.parseDouble($b.text); }
	  wedgeLine+;
WEDGE : ('W'|'w')('E'|'e')('D'|'d')('G'|'g')('E'|'e') ;

wedgeLine
	: a=wedgeExposure		{$wedge::expTime	=$a.value; }
	| b=wedgeAngRes			{$wedge::angRes		=$b.res; }
	| c=wedgeStartOffset	{$wedge::offsetX	=$c.x;
						  	 $wedge::offsetY	=$c.y;
						  	 $wedge::offsetZ	=$c.z; } 
	| d=wedgeTranslate		{$wedge::translateX =$d.x;
						  	 $wedge::translateY =$d.y;
						  	 $wedge::translateZ =$d.z; }
	| e=wedgeRotAxBeamOffset {$wedge::rotationOffset =$e.delta; }
	;

wedgeExposure returns [double value]
	: EXPOSURETIME a=FLOAT {$value = Double.parseDouble($a.text);};
EXPOSURETIME : ('E'|'e')('X'|'x')('P'|'p')('O'|'o')('S'|'s')('U'|'u')('R'|'r')('E'|'e')('T'|'t')('I'|'i')('M'|'m')('E'|'e') ;

wedgeAngRes returns [double res]
	: ANGULARRESOLUTION a=FLOAT {$res = Double.parseDouble($a.text);};
ANGULARRESOLUTION : ('A'|'a')('N'|'n')('G'|'g')('U'|'u')('L'|'l')('A'|'a')('R'|'r')('R'|'r')('E'|'e')('S'|'s')('O'|'o')('L'|'l')('U'|'u')('T'|'t')('I'|'i')('O'|'o')('N'|'n') ;

wedgeStartOffset returns [Double x, Double y, Double z]
	: STARTOFFSET a=FLOAT b=FLOAT {$x = Double.parseDouble($a.text); $y = Double.parseDouble($b.text);}
	              c=FLOAT? {$z = ($c.text == null) ? null : Double.parseDouble($c.text);}
	              ;
STARTOFFSET : ('S'|'s')('T'|'t')('A'|'a')('R'|'r')('T'|'t')('O'|'o')('F'|'f')('F'|'f')('S'|'s')('E'|'e')('T'|'t') ;

wedgeTranslate returns [Double x, Double y, Double z]
	: TRANSLATEPERDEGREE a=FLOAT b=FLOAT {$x = Double.parseDouble($a.text); $y = Double.parseDouble($b.text);}
	                     c=FLOAT? {$z = ($c.text == null) ? null : Double.parseDouble($c.text);}
	                     ;
TRANSLATEPERDEGREE : ('T'|'t')('R'|'r')('A'|'a')('N'|'n')('S'|'s')('L'|'l')('A'|'a')('T'|'t')('E'|'e')('P'|'p')('E'|'e')('R'|'r')('D'|'d')('E'|'e')('G'|'g')('R'|'r')('E'|'e')('E'|'e') ;

wedgeRotAxBeamOffset returns [double delta]
	: ROTAXBEAMOFFSET a=FLOAT {$delta = Double.parseDouble($a.text);};
ROTAXBEAMOFFSET : ('R'|'r')('O'|'o')('T'|'t')('A'|'a')('X'|'x')('B'|'b')('E'|'e')('A'|'a')('M'|'m')('O'|'o')('F'|'f')('F'|'f')('S'|'s')('E'|'e')('T'|'t') ;

/ ------------------------------------------------------------------

FLOAT 
    : ('+'|'-')?
    ( ('0'..'9')+ 
    | ('0'..'9')+ '.' ('0'..'9')*
    |             '.' ('0'..'9')+ 
    ) EXPONENT? 
    ;
fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

COMMENT
    :   ('#' | '//' | '!') ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

/*STRING
//    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    	: ( '"' ( ~('\\'|'"') )* '"' )
    	| ( '\'' ( ~('\''|'\\') )* '\'' )
    ;
*/
    
STRING
	: ('a'..'z' | 'A'..'Z' | '0'..'9' | '.' | '$' | '-' | '_')+
	;

/*CHAR:  '\'' ( ESC_SEQ | ~('\''|'\\') ) '\''
//    ;
*/

