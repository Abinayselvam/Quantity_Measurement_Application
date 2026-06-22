import org.example.project.Enums.LengthUnit;
import org.example.project.entities.QuantityLength;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

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
