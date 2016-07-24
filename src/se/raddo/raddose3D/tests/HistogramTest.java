package se.raddo.raddose3D.tests;

import static org.testng.Assert.*;
import org.testng.annotations.*;

import se.raddo.raddose3D.Histogram;

public class HistogramTest {

  @Test
  public void histogramInitialization() {
    Histogram h = new Histogram(0, 10, 5);
    assertNotNull(h);
    assertEquals(h.getObservationCount(), 0);
    assertNull(h.getPositionMean());
    assertNull(h.getWeightMean());
    assertNull(h.getWeightedPositionMean());
  }

  @Test
  public void histogramObservationTest() {
    Histogram h = new Histogram(0, 10, 5);
    // Buckets are: x<0 0<=x<2 2<=x<4 4<=x<6 6<=x<8 8<=x<10 x>=10

    assertNotNull(h);
    assertEquals(h.getObservationCount(), 0);
    assertNull(h.getPositionMean());
    assertNull(h.getWeightMean());
    assertNull(h.getWeightedPositionMean());

    h.addObservation(3);
    h.addObservation(4);
    h.addObservation(5);
    h.addObservation(5);
    h.addObservation(5.5);
    // Let's be evil:
    // Replace h by a copy of itself. Should not change a thing.
    h = new Histogram(h);
    
    h.addObservation(6);
    h.addObservation(6);
    h.addObservation(7);
    h.addObservation(8);
    h.addObservation(14);

    assertEquals(h.getObservationCount(), 10);
    Assertion.equals(h.getPositionMean(), 6.35, "histogram mean");

    Assertion.equals(h.getWeightMean(), 1, "histogram value mean");
    // Value for each observation is 1

    Assertion.equals(h.getWeightedPositionMean(), h.getPositionMean(),
        "histogram weighted mean");

    Double[] histBreaks = h.getHistogramBreaks();
    int[] histData = h.getObservationHistogram();

    Assertion
        .equals(histBreaks.length, 7, "histogram data structure size (2)");
    Assertion
        .equals(histData.length, 7, "histogram data structure size (3)");

    Assertion.equals(histBreaks[0], Double.NEGATIVE_INFINITY,
        "histogram breaks (0)");
    Assertion.equals(histBreaks[1], 0, "histogram breaks (1)");
    Assertion.equals(histBreaks[2], 2, "histogram breaks (2)");
    Assertion.equals(histBreaks[3], 4, "histogram breaks (3)");
    Assertion.equals(histBreaks[4], 6, "histogram breaks (4)");
    Assertion.equals(histBreaks[5], 8, "histogram breaks (5)");
    Assertion.equals(histBreaks[6], 10, "histogram breaks (6)");

    Assertion.equals(histData[0], 0, "histogram data (0)");
    Assertion.equals(histData[1], 0, "histogram data (1)");
    Assertion.equals(histData[2], 1, "histogram data (2)");
    Assertion.equals(histData[3], 4, "histogram data (3)");
    Assertion.equals(histData[4], 3, "histogram data (4)");
    Assertion.equals(histData[5], 1, "histogram data (5)");
    Assertion.equals(histData[6], 1, "histogram data (6)");

    Double[] histNorm = h.getNormalizedObservationHistogram();
    Double[] histNBreaks = h.getHistogramBreaks();

    Assertion.equals(histNorm.length, histData.length,
        "normalized histogram data structure size");
    Assertion.equals(histBreaks.length, histNBreaks.length,
        "normalized histogram breaks size");

    for (int i = 0; i <= 6; i++) {
      Assertion.equals(histBreaks[i], histNBreaks[i],
          "normalized histogram breaks (" + i + ")");
    }

    Assertion.equals(histNorm[0], 0, "normalized histogram data (0)");
    Assertion.equals(histNorm[1], 0, "normalized histogram data (1)");
    Assertion.equals(histNorm[2], 0.1, "normalized histogram data (2)");
    Assertion.equals(histNorm[3], 0.4, "normalized histogram data (3)");
    Assertion.equals(histNorm[4], 0.3, "normalized histogram data (4)");
    Assertion.equals(histNorm[5], 0.1, "normalized histogram data (5)");
    Assertion.equals(histNorm[6], 0.1, "normalized histogram data (6)");

  }

  @Test
  public void histogramValueTest() {
    Histogram h = new Histogram(0, 8, 4);
    // Buckets are: x<0 0<=x<2 2<=x<4 4<=x<6 6<=x<8 x>=8

    assertNotNull(h);
    assertEquals(h.getObservationCount(), 0);
    assertNull(h.getPositionMean());
    assertNull(h.getWeightMean());
    assertNull(h.getWeightedPositionMean());

    h.addValue(-2, 0.5);
    h.addValue(1.3, 1);
    h.addValue(5, 3);
    h.addValue(3, 1.3);
    h.addValue(7, 2);
    h.addValue(9, 0.5);
    h.addValue(2.1, 1.7);

    assertEquals(h.getObservationCount(), 7);
   
    Assertion.equals(h.getPositionMean(), 25.4 / 7, "histogram position mean");
    // mean of all positions: 

    Assertion.equals(h.getWeightMean(), 10.0 / 7, "histogram weight mean");
    // mean of all weights

    Assertion.equals(h.getWeightedPositionMean(), 41.27 / 10,
        "histogram weighted mean");
    // -2 * 0.5 + 1.3 * 1 + 5 * 3 + ... / (0.5+1+3+1.3+2+..)
    
    Double[] histBreaks = h.getHistogramBreaks();
    Double[] histData = h.getWeightHistogram();

    Assertion.equals(histData.length, 6, "histogram data structure size (1)");
    Assertion.equals(histBreaks.length, 6, "histogram data structure size (2)");

    Assertion.equals(histBreaks[0], Double.NEGATIVE_INFINITY,
        "histogram breaks (0)");
    Assertion.equals(histBreaks[1], 0, "histogram breaks (1)");
    Assertion.equals(histBreaks[2], 2, "histogram breaks (2)");
    Assertion.equals(histBreaks[3], 4, "histogram breaks (3)");
    Assertion.equals(histBreaks[4], 6, "histogram breaks (4)");
    Assertion.equals(histBreaks[5], 8, "histogram breaks (5)");

    Assertion.equals(histData[0], 0.5, "histogram data (0)");
    Assertion.equals(histData[1], 1, "histogram data (1)");
    Assertion.equals(histData[2], 3, "histogram data (2)");
    Assertion.equals(histData[3], 3, "histogram data (3)");
    Assertion.equals(histData[4], 2, "histogram data (4)");
    Assertion.equals(histData[5], 0.5, "histogram data (5)");

    Double[] histNBreaks = h.getHistogramBreaks();
    Double[] histNorm = h.getNormalizedWeightHistogram();

    Assertion.equals(histNorm.length, histData.length,
        "normalized histogram data structure size");
    Assertion.equals(histNBreaks.length, histBreaks.length,
        "normalized histogram breaks structure size");

    for (int i = 0; i <= 5; i++) {
      Assertion.equals(histNBreaks[i], histBreaks[i],
          "normalized histogram breaks (" + i + ")");
    }

    Assertion.equals(histNorm[0], 0.05, "normalized histogram data (0)");
    Assertion.equals(histNorm[1], 0.1, "normalized histogram data (1)");
    Assertion.equals(histNorm[2], 0.3, "normalized histogram data (2)");
    Assertion.equals(histNorm[3], 0.3, "normalized histogram data (3)");
    Assertion.equals(histNorm[4], 0.2, "normalized histogram data (4)");
    Assertion.equals(histNorm[5], 0.05, "normalized histogram data (5)");

  }

}
