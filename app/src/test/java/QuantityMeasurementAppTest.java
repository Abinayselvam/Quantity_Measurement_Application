import org.example.project.Enums.LengthUnit;
import org.example.project.entities.QuantityLength;
import org.example.project.utils.QuantityCompares;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

        //Feet to Inches
        @Test
        void testConversion_FeetToInches()
        {
            double result = QuantityCompares.convert(
                    1,
                    LengthUnit.FEET,
                    LengthUnit.INCHES
            );

            assertEquals(
                    12.0,
                    result,
                    0.000001
            );
        }
    @Test
    void testConversion_RoundTrip_PreservesValue() {

        double value = 5.0;

        double inches =
                QuantityCompares.convert(
                        value,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                );

        double feet =
                QuantityCompares.convert(
                        inches,
                        LengthUnit.INCHES,
                        LengthUnit.FEET
                );

        assertEquals(
                value,
                feet,
                0.000001
        );
    }

        //Inches to Feet
        @Test
        void testConversion_InchesToFeet() {

            double result =
                    QuantityCompares.convert(
                            24,
                            LengthUnit.INCHES,
                            LengthUnit.FEET
                    );

            assertEquals(
                    2.0,
                    result,
                    0.000001
            );
        }
    @Test
    void testConversion_YardsToInches() {

        double result =
                QuantityCompares.convert(
                        1,
                        LengthUnit.YARDS,
                        LengthUnit.INCHES
                );

        assertEquals(
                36.0,
                result,
                0.000001
        );
    }
         //Centimeters to inches
         @Test
         void testConversion_CentimetersToInches() {

             double result =
                     QuantityCompares.convert(
                             2.54,
                             LengthUnit.CENTIMETERS,
                             LengthUnit.INCHES
                     );

             assertEquals(
                     1.0,
                     result,
                     0.0001
             );
         }
    @Test
    void testConversion_InvalidUnit_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(
                        1,
                        null
                )
        );
    }
    @Test
    void testConversion_NaNOrInfinite_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(
                        Double.NaN,
                        LengthUnit.FEET
                )
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(
                        Double.POSITIVE_INFINITY,
                        LengthUnit.FEET
                )
        );
    }

         //Yard to Yard
          @Test
          void testEquality_YardToYard_SameValue()
          {
                QuantityLength  q1 = new QuantityLength(1,LengthUnit.YARDS);
                QuantityLength  q2 = new QuantityLength(1,LengthUnit.YARDS);
                assertEquals( q1, q2 );
          }

        //Different values
        @Test
        void testEquality_YardToFeet_NonEquivalentValue()
        {
            QuantityLength  yard = new QuantityLength(1,LengthUnit.YARDS);
            QuantityLength feet = new QuantityLength(2,LengthUnit.FEET);
            assertNotEquals( yard, feet );
        }

        @Test
        void testEquality_FeetToFeet_SameValue()
        {
            assertTrue(new QuantityLength(1, LengthUnit.FEET).equals(new QuantityLength(1,LengthUnit.FEET)));
        }

       @Test
       void testEquality_InchesToInches_SameValue() {
          assertTrue(new QuantityLength(1, LengthUnit.INCHES).equals(new QuantityLength(1,LengthUnit.INCHES)));
       }

       @Test
       void testEquality_FeetToInch_EquivalentValue()
       {
           assertTrue(new QuantityLength(1,LengthUnit.FEET).equals(new QuantityLength(12,LengthUnit.INCHES)));
       }

       @Test
       void testEquality_InchToFeet_EquivalentValue()
       {
           assertTrue(new QuantityLength(12,LengthUnit.INCHES).equals(new QuantityLength(1,LengthUnit.FEET)));
       }

       @Test
        void testEquality_InchToInch_DifferentValue() {
            assertFalse(new QuantityLength(1,LengthUnit.INCHES).equals(new QuantityLength(2,LengthUnit.INCHES)));
       }

       @Test
        void testEquality_FeetToFeet_DifferentValue() {
            assertFalse(new QuantityLength(1,LengthUnit.FEET).equals(new QuantityLength(2,LengthUnit.FEET)));
       }

       @Test
       void testEquality_SameReference()
       {
           QuantityLength length = new QuantityLength(1,LengthUnit.INCHES);
           assertTrue(length.equals(length));
       }

       @Test
       void testEquality_NullComparison()
       {
           QuantityLength length = new QuantityLength(1,LengthUnit.INCHES);
           assertFalse(length.equals(null));
       }
       @Test
       void testEquality_NullUnit()
       {
           assertThrows(

                   IllegalArgumentException.class,

                   () -> new QuantityLength(
                           1,
                           null
                   )
           );
       }

}
