import static org.junit.jupiter.api.Assertions.*;
import org.example.project.Enums.ArithmeticOperation;
import org.example.project.Enums.LengthUnit;
import org.example.project.Enums.TemperatureUnit;
import org.example.project.Enums.WeightUnit;
import org.example.project.model.QuantityModel;
import org.junit.jupiter.api.Test;
public class QuantityMeasurementAppTest {


    @Test
    void testTemperatureEquality_CelsiusToFahrenheit() {

        QuantityModel<TemperatureUnit> celsius =
                new QuantityModel<>(0.0,
                        TemperatureUnit.CELSIUS);

        QuantityModel<TemperatureUnit> fahrenheit =
                new QuantityModel<>(32.0,
                        TemperatureUnit.FAHRENHEIT);

        assertTrue(
                celsius.equals(fahrenheit));
    }
    @Test
    void testTemperatureConversion() {

        QuantityModel<TemperatureUnit> celsius =
                new QuantityModel<>(100.0,
                        TemperatureUnit.CELSIUS);

        QuantityModel<TemperatureUnit> result =
                celsius.convertTo(
                        TemperatureUnit.FAHRENHEIT);

        assertEquals(
                212.0,
                result.getValue(),
                0.01);
    }
    @Test
    void testTemperatureAddThrowsException() {

        QuantityModel<TemperatureUnit> t1 =
                new QuantityModel<>(100.0,
                        TemperatureUnit.CELSIUS);

        QuantityModel<TemperatureUnit> t2 =
                new QuantityModel<>(50.0,
                        TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.add(t2));
    }

    @Test
    void testTemperatureDivideThrowsException() {
        QuantityModel<TemperatureUnit> t1 =
                new QuantityModel<>(100.0,
                        TemperatureUnit.CELSIUS);

        QuantityModel<TemperatureUnit> t2 =
                new QuantityModel<>(50.0,
                        TemperatureUnit.CELSIUS);
        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.divide(t2));
    }
    @Test
    void crossCategory()
    {
        QuantityModel<LengthUnit> length =
                new QuantityModel<>(1, LengthUnit.FEET);

        QuantityModel<WeightUnit> weight =
                new QuantityModel<>(1, WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> length.divide((QuantityModel) weight));
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

        QuantityModel<LengthUnit> q1 =
                new QuantityModel<>(1.0,
                        LengthUnit.FEET);

        QuantityModel<LengthUnit> q2 =
                new QuantityModel<>(0.0,
                        LengthUnit.INCHES);

        assertThrows(
                ArithmeticException.class,
                () -> q1.divide(q2));
    }
    @Test
    void testDivision_Length() {

        QuantityModel<LengthUnit> q1 =
                new QuantityModel<>(2.0,
                        LengthUnit.FEET);

        QuantityModel<LengthUnit> q2 =
                new QuantityModel<>(24.0,
                        LengthUnit.INCHES);

        assertEquals(
                1.0,
                q1.divide(q2));
    }
    @Test
    void testNaNValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityModel<WeightUnit>(
                        Double.NaN,
                        WeightUnit.KILOGRAM)
        );
    }
    @Test
    void testNullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityModel<WeightUnit>(1, null)
        );
    }
    @Test
    void testWeightVsLength() {

        QuantityModel<WeightUnit> weight =
                new QuantityModel<WeightUnit>(1, WeightUnit.KILOGRAM);

        QuantityModel<LengthUnit> length =
                new QuantityModel<LengthUnit>(1, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }
    @Test
    void testAddition_TargetUnit_Gram() {

        QuantityModel<WeightUnit> result =
                new QuantityModel<WeightUnit>(1, WeightUnit.KILOGRAM)
                        .add(
                                new QuantityModel<WeightUnit>(1000, WeightUnit.GRAM),
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

        QuantityModel<WeightUnit> result =
                new QuantityModel<WeightUnit>(1, WeightUnit.KILOGRAM)
                        .add(
                                new QuantityModel<WeightUnit>(1000, WeightUnit.GRAM)
                        );

        assertEquals(
                2.0,
                result.getValue(),
                0.001
        );
    }
    @Test
    void testConversion_PoundToKilogram() {

        QuantityModel<WeightUnit> result =
                new QuantityModel<WeightUnit>(2.20462, WeightUnit.POUND)
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
                new QuantityModel<WeightUnit>(1, WeightUnit.KILOGRAM)
                        .equals(
                                new QuantityModel<WeightUnit>(1000, WeightUnit.GRAM)
                        ));
    }
    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        QuantityModel<LengthUnit> first =
                new QuantityModel<LengthUnit>(1, LengthUnit.FEET);

        QuantityModel<LengthUnit> second =
                new QuantityModel<LengthUnit>(12, LengthUnit.INCHES);

        QuantityModel<LengthUnit> result1 =
                first.add(second, LengthUnit.YARDS);

        QuantityModel<LengthUnit> result2 =
                second.add(first, LengthUnit.YARDS);

        assertEquals(result1, result2);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        QuantityModel<LengthUnit> first =
                new QuantityModel<LengthUnit>(1, LengthUnit.FEET);

        QuantityModel<LengthUnit> second =
                new QuantityModel<LengthUnit>(12, LengthUnit.INCHES);

        QuantityModel<LengthUnit> result =
                first.add(second, LengthUnit.INCHES);

        assertEquals(
                new QuantityModel<LengthUnit>(24, LengthUnit.INCHES),
                result);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        QuantityModel<LengthUnit> first =
                new QuantityModel<LengthUnit>(1, LengthUnit.FEET);

        QuantityModel<LengthUnit> second =
                new QuantityModel<LengthUnit>(12, LengthUnit.INCHES);

        QuantityModel<LengthUnit> result =
                first.add(second, LengthUnit.FEET);

        assertEquals(
                new QuantityModel<LengthUnit>(2, LengthUnit.FEET),
                result);
    }
    @Test
    void testAddition_SameUnit_FeetPlusFeet()
    {
        QuantityModel<LengthUnit> first = new QuantityModel<LengthUnit>(1, LengthUnit.FEET);
        QuantityModel<LengthUnit> second = new QuantityModel<LengthUnit>(2, LengthUnit.FEET);
        assertEquals( new QuantityModel<LengthUnit>(3, LengthUnit.FEET), first.add(second) );
    }

    @Test
    void testAddition_SameUnit_InchPlusInch()
    {
        QuantityModel<LengthUnit> first = new QuantityModel<LengthUnit>(6, LengthUnit.INCHES);
        QuantityModel<LengthUnit> second = new QuantityModel<LengthUnit>(6, LengthUnit.INCHES);
        assertEquals( new QuantityModel<LengthUnit>(12, LengthUnit.INCHES), first.add(second) );
    }
    @Test
    void testAddition_CrossUnit_FeetPlusInches()
    {
        QuantityModel<LengthUnit> feet = new QuantityModel<LengthUnit>(1, LengthUnit.FEET);
        QuantityModel<LengthUnit> inches = new QuantityModel<LengthUnit>(12, LengthUnit.INCHES);
        assertEquals( new QuantityModel<LengthUnit>(2, LengthUnit.FEET), feet.add(inches) );
    }
    @Test
    void testAddition_CrossUnit_InchPlusFeet()
    {
        QuantityModel<LengthUnit> inches = new QuantityModel<LengthUnit>(12, LengthUnit.INCHES);
        QuantityModel<LengthUnit> feet = new QuantityModel<LengthUnit>(1, LengthUnit.FEET);
        assertEquals( new QuantityModel<LengthUnit>(24, LengthUnit.INCHES), inches.add(feet) );
    }
    @Test
    void testAddition_CrossUnit_YardPlusFeet()
    {
        QuantityModel<LengthUnit> yard = new QuantityModel<LengthUnit>(1, LengthUnit.YARDS);
        QuantityModel<LengthUnit> feet = new QuantityModel<LengthUnit>(3, LengthUnit.FEET);
        assertEquals( new QuantityModel<LengthUnit>(2, LengthUnit.YARDS), yard.add(feet) );
    }
    @Test
    void testAddition_CrossUnit_CentimeterPlusInch()
    {
        QuantityModel<LengthUnit> cm = new QuantityModel<LengthUnit>(2.54, LengthUnit.CENTIMETERS);
        QuantityModel<LengthUnit> inch = new QuantityModel<LengthUnit>(1, LengthUnit.INCHES);
        QuantityModel<LengthUnit> result = cm.add(inch);
        assertEquals( 5.08, result.getValue(), 0.001 );
    }

    @Test
    void testAddition_Commutativity() {

        QuantityModel<LengthUnit> feet =
                new QuantityModel<LengthUnit>(1, LengthUnit.FEET);

        QuantityModel<LengthUnit> inches =
                new QuantityModel<LengthUnit>(12, LengthUnit.INCHES);

        QuantityModel<LengthUnit> result1 = feet.add(inches);
        QuantityModel<LengthUnit> result2 = inches.add(feet);

        assertTrue(
                result1.equals(result2)
        );
    }
    @Test
    void testAddition_WithZero()
    {
        QuantityModel<LengthUnit> feet = new QuantityModel<LengthUnit>(5, LengthUnit.FEET);
        QuantityModel<LengthUnit> zero = new QuantityModel<LengthUnit>(0, LengthUnit.INCHES);
        assertEquals( feet, feet.add(zero) );
    }
    @Test
    void testAddition_NegativeValues()
    {
        QuantityModel<LengthUnit> first = new QuantityModel<LengthUnit>(5, LengthUnit.FEET);
        QuantityModel<LengthUnit> second = new QuantityModel<LengthUnit>(-2, LengthUnit.FEET);
        assertEquals( new QuantityModel<LengthUnit>(3, LengthUnit.FEET), first.add(second) );
    }
    @Test
    void testAddition_NullOperand()
    {
        QuantityModel<LengthUnit> first = new QuantityModel<LengthUnit>(1, LengthUnit.FEET);
        assertThrows( IllegalArgumentException.class, () -> first.add(null) );
    }
    @Test
    void testAddition_LargeValues()
    {
        QuantityModel<LengthUnit> first = new QuantityModel<LengthUnit>(1_000_000, LengthUnit.FEET);
        QuantityModel<LengthUnit> second = new QuantityModel<LengthUnit>(1_000_000, LengthUnit.FEET);
        assertEquals( new QuantityModel<LengthUnit>(2_000_000, LengthUnit.FEET), first.add(second) );
    }

    @Test
    void testAddition_SameReference()
    {
        QuantityModel<LengthUnit> feet = new QuantityModel<>(2, LengthUnit.FEET);
        assertEquals( new QuantityModel<LengthUnit>(4, LengthUnit.FEET), feet.add(feet) );
    }
    @Test
    void testAddition_OriginalObjectsRemainUnchanged()
    {
        QuantityModel<LengthUnit> first = new QuantityModel<LengthUnit>(1, LengthUnit.FEET);
        QuantityModel<LengthUnit> second = new QuantityModel<LengthUnit>(12, LengthUnit.INCHES);
        first.add(second); assertEquals( new QuantityModel<>(1, LengthUnit.FEET), first );
        assertEquals( new QuantityModel<>(12, LengthUnit.INCHES), second );
    }

}
