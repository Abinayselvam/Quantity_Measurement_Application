import org.example.project.entities.Feet;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

        @Test
        void givenSameFeetValue_WhenCompared_ShouldReturnTrue() {
           Feet feet = new Feet(1.0);
           Feet feet2 = new Feet(1.0);
           assertTrue(
                       feet.equals(feet2)
               );
       }

       @Test
       void givenDifferentFeetValue_WhenCompared_ShouldReturnFalse() {
           Feet feet = new Feet(1.0);
           Feet feet2 = new Feet(2.0);
           assertFalse(
                   feet.equals(feet2)
           );
       }

       @Test
       void givenNull_WhenCompared_ShouldReturnFalse() {
           Feet feet = new Feet(1.0);
           assertFalse(feet.equals(null));
       }

       @Test
       void givenDifferentDataTypes_WhenCompared_ShouldReturnFalse() {
           Feet feet = new Feet(1.0);
           assertFalse(feet.equals("1.0"));
       }

       @Test
        void givenSameReference_WhenCompared_ShouldReturnTrue() {
           Feet feet = new Feet(1.0);
           assertTrue(feet.equals(feet));

       }

}
