
import org.example.project.Enums.LengthUnit;
import org.example.project.Enums.WeightUnit;
import org.example.project.entities.QuantityLength;
import org.example.project.entities.QuantityWeight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

    @Test
    void testNaNValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityWeight(
                        Double.NaN,
                        WeightUnit.KILOGRAM)
        );
    }
    @Test
    void testNullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityWeight(1, null)
        );
    }
    @Test
    void testWeightVsLength() {

        QuantityWeight weight =
                new QuantityWeight(1, WeightUnit.KILOGRAM);

        QuantityLength length =
                new QuantityLength(1, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }
    @Test
    void testAddition_TargetUnit_Gram() {

        QuantityWeight result =
                new QuantityWeight(1, WeightUnit.KILOGRAM)
                        .add(
                                new QuantityWeight(1000, WeightUnit.GRAM),
                                WeightUnit.GRAM
                        );

        assertEquals(
                2000,
                result.getValue(),
                0.001
        );
    }
    @Test
    void testAddition_KgPlusGram() {

        QuantityWeight result =
                new QuantityWeight(1, WeightUnit.KILOGRAM)
                        .add(
                                new QuantityWeight(1000, WeightUnit.GRAM)
                        );

        assertEquals(
                2.0,
                result.getValue(),
                0.001
        );
    }
    @Test
    void testConversion_PoundToKilogram() {

        QuantityWeight result =
                new QuantityWeight(2.20462, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(
                1.0,
                result.getValue(),
                0.001
        );
    }
    @Test
    void testEquality_KgToGram() {
        assertTrue(
                new QuantityWeight(1, WeightUnit.KILOGRAM)
                        .equals(
                                new QuantityWeight(1000, WeightUnit.GRAM)
                        ));
    }
    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(12, LengthUnit.INCHES);

        QuantityLength result1 =
                first.add(second, LengthUnit.YARDS);

        QuantityLength result2 =
                second.add(first, LengthUnit.YARDS);

        assertEquals(result1, result2);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(12, LengthUnit.INCHES);

        QuantityLength result =
                first.add(second, LengthUnit.INCHES);

        assertEquals(
                new QuantityLength(24, LengthUnit.INCHES),
                result);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        QuantityLength first =
                new QuantityLength(1, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(12, LengthUnit.INCHES);

        QuantityLength result =
                first.add(second, LengthUnit.FEET);

        assertEquals(
                new QuantityLength(2, LengthUnit.FEET),
                result);
    }
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
