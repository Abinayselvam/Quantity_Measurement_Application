import static org.junit.jupiter.api.Assertions.*;
import org.example.project.Enums.ArithmeticOperation;
import org.example.project.Enums.LengthUnit;
import org.example.project.Enums.WeightUnit;
import org.example.project.entities.Quantity;
import org.junit.jupiter.api.Test;
public class QuantityMeasurementAppTest {

    @Test
    void crossCategory()
    {
        Quantity<LengthUnit> length =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1, WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> length.divide((Quantity) weight));
    }
   @Test
   void  enumTest()
   {
       assertEquals(
               15.0,
               ArithmeticOperation.ADD.compute(10,5));

       assertEquals(
               5.0,
               ArithmeticOperation.SUBTRACT.compute(10,5));

       assertEquals(
               2.0,
               ArithmeticOperation.DIVIDE.compute(10,5));
   }
    @Test
    void testDivisionByZero() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(0.0,
                        LengthUnit.INCHES);

        assertThrows(
                ArithmeticException.class,
                () -> q1.divide(q2));
    }
    @Test
    void testDivision_Length() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(2.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(24.0,
                        LengthUnit.INCHES);

        assertEquals(
                1.0,
                q1.divide(q2));
    }
    @Test
    void testNaNValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<WeightUnit>(
                        Double.NaN,
                        WeightUnit.KILOGRAM)
        );
    }
    @Test
    void testNullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<WeightUnit>(1, null)
        );
    }
    @Test
    void testWeightVsLength() {

        Quantity<WeightUnit> weight =
                new Quantity<WeightUnit>(1, WeightUnit.KILOGRAM);

        Quantity<LengthUnit> length =
                new Quantity<LengthUnit>(1, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }
    @Test
    void testAddition_TargetUnit_Gram() {

        Quantity<WeightUnit> result =
                new Quantity<WeightUnit>(1, WeightUnit.KILOGRAM)
                        .add(
                                new Quantity<WeightUnit>(1000, WeightUnit.GRAM),
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

        Quantity<WeightUnit> result =
                new Quantity<WeightUnit>(1, WeightUnit.KILOGRAM)
                        .add(
                                new Quantity<WeightUnit>(1000, WeightUnit.GRAM)
                        );

        assertEquals(
                2.0,
                result.getValue(),
                0.001
        );
    }
    @Test
    void testConversion_PoundToKilogram() {

        Quantity<WeightUnit> result =
                new Quantity<WeightUnit>(2.20462, WeightUnit.POUND)
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
                new Quantity<WeightUnit>(1, WeightUnit.KILOGRAM)
                        .equals(
                                new Quantity<WeightUnit>(1000, WeightUnit.GRAM)
                        ));
    }
    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        Quantity<LengthUnit> first =
                new Quantity<LengthUnit>(1, LengthUnit.FEET);

        Quantity<LengthUnit> second =
                new Quantity<LengthUnit>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result1 =
                first.add(second, LengthUnit.YARDS);

        Quantity<LengthUnit> result2 =
                second.add(first, LengthUnit.YARDS);

        assertEquals(result1, result2);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        Quantity<LengthUnit> first =
                new Quantity<LengthUnit>(1, LengthUnit.FEET);

        Quantity<LengthUnit> second =
                new Quantity<LengthUnit>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                first.add(second, LengthUnit.INCHES);

        assertEquals(
                new Quantity<LengthUnit>(24, LengthUnit.INCHES),
                result);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        Quantity<LengthUnit> first =
                new Quantity<LengthUnit>(1, LengthUnit.FEET);

        Quantity<LengthUnit> second =
                new Quantity<LengthUnit>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                first.add(second, LengthUnit.FEET);

        assertEquals(
                new Quantity<LengthUnit>(2, LengthUnit.FEET),
                result);
    }
    @Test
    void testAddition_SameUnit_FeetPlusFeet()
    {
        Quantity<LengthUnit> first = new Quantity<LengthUnit>(1, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<LengthUnit>(2, LengthUnit.FEET);
        assertEquals( new Quantity<LengthUnit>(3, LengthUnit.FEET), first.add(second) );
    }

    @Test
    void testAddition_SameUnit_InchPlusInch()
    {
        Quantity<LengthUnit> first = new Quantity<LengthUnit>(6, LengthUnit.INCHES);
        Quantity<LengthUnit> second = new Quantity<LengthUnit>(6, LengthUnit.INCHES);
        assertEquals( new Quantity<LengthUnit>(12, LengthUnit.INCHES), first.add(second) );
    }
    @Test
    void testAddition_CrossUnit_FeetPlusInches()
    {
        Quantity<LengthUnit> feet = new Quantity<LengthUnit>(1, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<LengthUnit>(12, LengthUnit.INCHES);
        assertEquals( new Quantity<LengthUnit>(2, LengthUnit.FEET), feet.add(inches) );
    }
    @Test
    void testAddition_CrossUnit_InchPlusFeet()
    {
        Quantity<LengthUnit> inches = new Quantity<LengthUnit>(12, LengthUnit.INCHES);
        Quantity<LengthUnit> feet = new Quantity<LengthUnit>(1, LengthUnit.FEET);
        assertEquals( new Quantity<LengthUnit>(24, LengthUnit.INCHES), inches.add(feet) );
    }
    @Test
    void testAddition_CrossUnit_YardPlusFeet()
    {
        Quantity<LengthUnit> yard = new Quantity<LengthUnit>(1, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = new Quantity<LengthUnit>(3, LengthUnit.FEET);
        assertEquals( new Quantity<LengthUnit>(2, LengthUnit.YARDS), yard.add(feet) );
    }
    @Test
    void testAddition_CrossUnit_CentimeterPlusInch()
    {
        Quantity<LengthUnit> cm = new Quantity<LengthUnit>(2.54, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> inch = new Quantity<LengthUnit>(1, LengthUnit.INCHES);
        Quantity<LengthUnit> result = cm.add(inch);
        assertEquals( 5.08, result.getValue(), 0.001 );
    }

    @Test
    void testAddition_Commutativity() {

        Quantity<LengthUnit> feet =
                new Quantity<LengthUnit>(1, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<LengthUnit>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result1 = feet.add(inches);
        Quantity<LengthUnit> result2 = inches.add(feet);

        assertTrue(
                result1.equals(result2)
        );
    }
    @Test
    void testAddition_WithZero()
    {
        Quantity<LengthUnit> feet = new Quantity<LengthUnit>(5, LengthUnit.FEET);
        Quantity<LengthUnit> zero = new Quantity<LengthUnit>(0, LengthUnit.INCHES);
        assertEquals( feet, feet.add(zero) );
    }
    @Test
    void testAddition_NegativeValues()
    {
        Quantity<LengthUnit> first = new Quantity<LengthUnit>(5, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<LengthUnit>(-2, LengthUnit.FEET);
        assertEquals( new Quantity<LengthUnit>(3, LengthUnit.FEET), first.add(second) );
    }
    @Test
    void testAddition_NullOperand()
    {
        Quantity<LengthUnit> first = new Quantity<LengthUnit>(1, LengthUnit.FEET);
        assertThrows( IllegalArgumentException.class, () -> first.add(null) );
    }
    @Test
    void testAddition_LargeValues()
    {
        Quantity<LengthUnit> first = new Quantity<LengthUnit>(1_000_000, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<LengthUnit>(1_000_000, LengthUnit.FEET);
        assertEquals( new Quantity<LengthUnit>(2_000_000, LengthUnit.FEET), first.add(second) );
    }

    @Test
    void testAddition_SameReference()
    {
        Quantity<LengthUnit> feet = new Quantity<LengthUnit>(2, LengthUnit.FEET);
        assertEquals( new Quantity<LengthUnit>(4, LengthUnit.FEET), feet.add(feet) );
    }
    @Test
    void testAddition_OriginalObjectsRemainUnchanged()
    {
        Quantity<LengthUnit> first = new Quantity<LengthUnit>(1, LengthUnit.FEET);
        Quantity<LengthUnit> second = new Quantity<LengthUnit>(12, LengthUnit.INCHES);
        first.add(second); assertEquals( new Quantity<LengthUnit>(1, LengthUnit.FEET), first );
        assertEquals( new Quantity<LengthUnit>(12, LengthUnit.INCHES), second );
    }

}
