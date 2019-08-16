package se.raddo.raddose3D;

import java.util.HashMap;
import java.util.Map;

/**
 * The OutputFactory class sits between the parser and the actual crystal
 * classes. It allows easy testing of the parser and extensibility for new
 * {@link Output} types.
 */
public class OutputFactory extends ClassFactory {
  /**
   * creates and returns an Output type object.
   * 
   * @param outputName
   *          the name of the output class requested.
   * @param properties
   *          a Map containing the complete list of output properties.
   *          Different output types may require a different set of specified
   *          properties, but at least one Writer will certainly be
   *          required. Keys of the Map structure are usually determined by the
   *          constants defined in {@link Output}, but third party Output
   *          implementations may have their own key set.
   *          Check the corresponding output class for details.
   *          The newly created object should not keep any references to this
   *          Map after object creation.
   * @return
   *         the requested Output type object
   * @throws IllegalArgumentException
   *           the passed parameters are invalid
   * @throws ClassFactoryException
   *           the requested crystal class could not be initialized
   */
  public Output createOutput(final String outputName,
      final Map<Object, Object> properties)
      throws IllegalArgumentException, ClassFactoryException {
    String revisedOutputName;

    if ("finaldosestatecsv".equalsIgnoreCase(outputName)) {
      revisedOutputName = "se.raddo.raddose3D.OutputFinalDoseStateCSV";
    } else if ("finaldosestater".equalsIgnoreCase(outputName)) {
      revisedOutputName = "se.raddo.raddose3D.OutputFinalDoseStateR";
    } else if ("finaldosestaterpreview".equalsIgnoreCase(outputName)) {
      revisedOutputName = "se.raddo.raddose3D.OutputFinalDoseStateRPreview";
    } else if ("fluenceperdosehistcsv".equalsIgnoreCase(outputName)) {
      revisedOutputName = "se.raddo.raddose3D.OutputFluencePerDoseHistCSV";
    } else if ("progressestimate".equalsIgnoreCase(outputName)) {
      revisedOutputName = "se.raddo.raddose3D.OutputProgressEstimate";
    } else if ("progressindicator".equalsIgnoreCase(outputName)) {
      revisedOutputName = "se.raddo.raddose3D.OutputProgressIndicator";
    } else if ("summarycsv".equalsIgnoreCase(outputName)) {
      revisedOutputName = "se.raddo.raddose3D.OutputSummaryCSV";
    } else if ("summarytext".equalsIgnoreCase(outputName)) {
      revisedOutputName = "se.raddo.raddose3D.OutputSummaryText";
    } else if ("rdecsv".equalsIgnoreCase(outputName)) {
      revisedOutputName = "se.raddo.raddose3D.OutputRDECSV";
    }else if ("dwds".equalsIgnoreCase(outputName)) {
      revisedOutputName = "se.raddo.raddose3D.OutputDWDs";
    }else if ("voxeldose".equalsIgnoreCase(outputName)) {
      revisedOutputName = "se.raddo.raddose3D.OutputVoxelDose";
    }else if ("voxeldose".equalsIgnoreCase(outputName)) {
      revisedOutputName = "se.raddo.raddose3D.OutputVoxelFluences";
    }else {
      revisedOutputName = outputName;
    }

    return createObject(Output.class, revisedOutputName, properties);
  }

  /**
   * creates and returns an Output type object with only a single {@link Writer}
   * parameter.
   * 
   * @param outputName
   *          the name of the output class requested.
   * @param writerObject
   *          a Writer object to which all output will be directed.
   *          Will be passed to Output object constructor as OUTPUT_WRITER
   *          property.
   * @return
   *         the requested Output type object
   */
  public Output createOutputSimple(final String outputName,
      final Writer writerObject) {
    HashMap<Object, Object> properties =
        new HashMap<Object, Object>();
    properties.put(Output.OUTPUT_WRITER, writerObject);

    return createOutput(outputName, properties);
  }
}
