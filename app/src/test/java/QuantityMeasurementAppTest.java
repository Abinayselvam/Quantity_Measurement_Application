
import org.example.project.Enums.LengthUnit;
import org.example.project.entities.QuantityLength;
import org.example.project.utils.QuantityCompares;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

    @Test
    void testAddition_SameUnit_FeetPlusFeet()
    {
        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength second = new QuantityLength(2, LengthUnit.FEET);
        assertEquals( new QuantityLength(3, LengthUnit.FEET), first.add(second) );
    }

    @Test
    void testAddition_SameUnit_InchPlusInch()
    {
        QuantityLength first = new QuantityLength(6, LengthUnit.INCHES);
        QuantityLength second = new QuantityLength(6, LengthUnit.INCHES);
        assertEquals( new QuantityLength(12, LengthUnit.INCHES), first.add(second) );
    }
    @Test
    void testAddition_CrossUnit_FeetPlusInches()
    {
        QuantityLength feet = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12, LengthUnit.INCHES);
        assertEquals( new QuantityLength(2, LengthUnit.FEET), feet.add(inches) );
    }
    @Test
    void testAddition_CrossUnit_InchPlusFeet()
    {
        QuantityLength inches = new QuantityLength(12, LengthUnit.INCHES);
        QuantityLength feet = new QuantityLength(1, LengthUnit.FEET);
        assertEquals( new QuantityLength(24, LengthUnit.INCHES), inches.add(feet) );
    }
    @Test
    void testAddition_CrossUnit_YardPlusFeet()
    {
        QuantityLength yard = new QuantityLength(1, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(3, LengthUnit.FEET);
        assertEquals( new QuantityLength(2, LengthUnit.YARDS), yard.add(feet) );
    }
    @Test
    void testAddition_CrossUnit_CentimeterPlusInch()
    {
        QuantityLength cm = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
        QuantityLength inch = new QuantityLength(1, LengthUnit.INCHES);
        QuantityLength result = cm.add(inch);
        assertEquals( 5.08, result.getValue(), 0.001 );
    }

    @Test
    void testAddition_Commutativity() {

        QuantityLength feet =
                new QuantityLength(1, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12, LengthUnit.INCHES);

        QuantityLength result1 = feet.add(inches);
        QuantityLength result2 = inches.add(feet);

        assertTrue(
                result1.equals(result2)
        );
    }
    @Test
    void testAddition_WithZero()
    {
        QuantityLength feet = new QuantityLength(5, LengthUnit.FEET);
        QuantityLength zero = new QuantityLength(0, LengthUnit.INCHES);
        assertEquals( feet, feet.add(zero) );
    }
    @Test
    void testAddition_NegativeValues()
    {
        QuantityLength first = new QuantityLength(5, LengthUnit.FEET);
        QuantityLength second = new QuantityLength(-2, LengthUnit.FEET);
        assertEquals( new QuantityLength(3, LengthUnit.FEET), first.add(second) );
    }
    @Test
    void testAddition_NullOperand()
    {
        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);
        assertThrows( IllegalArgumentException.class, () -> first.add(null) );
    }
    @Test
    void testAddition_LargeValues()
    {
        QuantityLength first = new QuantityLength(1_000_000, LengthUnit.FEET);
        QuantityLength second = new QuantityLength(1_000_000, LengthUnit.FEET);
        assertEquals( new QuantityLength(2_000_000, LengthUnit.FEET), first.add(second) );
    }
    @Test
    void testAddition_SmallValues()
    {
        QuantityLength first = new QuantityLength(0.001, LengthUnit.FEET);
        QuantityLength second = new QuantityLength(0.002, LengthUnit.FEET);
        QuantityLength result = first.add(second);
        assertEquals( 0.003, result.getValue(), 0.000001 );
    }
    @Test
    void testAddition_SameReference()
    {
        QuantityLength feet = new QuantityLength(2, LengthUnit.FEET);
        assertEquals( new QuantityLength(4, LengthUnit.FEET), feet.add(feet) );
    }
    @Test
    void testAddition_OriginalObjectsRemainUnchanged()
    {
        QuantityLength first = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength second = new QuantityLength(12, LengthUnit.INCHES);
        first.add(second); assertEquals( new QuantityLength(1, LengthUnit.FEET), first );
        assertEquals( new QuantityLength(12, LengthUnit.INCHES), second );
    }

}
