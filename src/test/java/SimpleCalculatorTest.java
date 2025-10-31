import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;

public class SimpleCalculatorTest {
    
    private SimpleCalculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new SimpleCalculator();
    }
    
    @Test
    @DisplayName("Test addition with positive integers")
    void testAdditionWithPositiveIntegers() throws Exception {
        // Use reflection to access the private add method
        Method addMethod = SimpleCalculator.class.getDeclaredMethod("add", double.class, double.class);
        addMethod.setAccessible(true);
        
        // Test cases with positive integers
        double result1 = (double) addMethod.invoke(calculator, 5.0, 3.0);
        assertEquals(8.0, result1, 0.001, "5 + 3 should equal 8");
        
        double result2 = (double) addMethod.invoke(calculator, 10.0, 15.0);
        assertEquals(25.0, result2, 0.001, "10 + 15 should equal 25");
        
        double result3 = (double) addMethod.invoke(calculator, 100.0, 200.0);
        assertEquals(300.0, result3, 0.001, "100 + 200 should equal 300");
    }
    
    @Test
    @DisplayName("Test addition with positive decimal numbers")
    void testAdditionWithPositiveDecimals() throws Exception {
        Method addMethod = SimpleCalculator.class.getDeclaredMethod("add", double.class, double.class);
        addMethod.setAccessible(true);
        
        // Test cases with positive decimal numbers
        double result1 = (double) addMethod.invoke(calculator, 2.5, 3.7);
        assertEquals(6.2, result1, 0.001, "2.5 + 3.7 should equal 6.2");
        
        double result2 = (double) addMethod.invoke(calculator, 0.1, 0.2);
        assertEquals(0.3, result2, 0.001, "0.1 + 0.2 should equal 0.3");
        
        double result3 = (double) addMethod.invoke(calculator, 12.75, 7.25);
        assertEquals(20.0, result3, 0.001, "12.75 + 7.25 should equal 20.0");
    }
    
    @Test
    @DisplayName("Test addition with zero")
    void testAdditionWithZero() throws Exception {
        Method addMethod = SimpleCalculator.class.getDeclaredMethod("add", double.class, double.class);
        addMethod.setAccessible(true);
        
        // Test cases with zero
        double result1 = (double) addMethod.invoke(calculator, 0.0, 5.0);
        assertEquals(5.0, result1, 0.001, "0 + 5 should equal 5");
        
        double result2 = (double) addMethod.invoke(calculator, 10.0, 0.0);
        assertEquals(10.0, result2, 0.001, "10 + 0 should equal 10");
        
        double result3 = (double) addMethod.invoke(calculator, 0.0, 0.0);
        assertEquals(0.0, result3, 0.001, "0 + 0 should equal 0");
    }
    
    @Test
    @DisplayName("Test addition with large positive numbers")
    void testAdditionWithLargePositiveNumbers() throws Exception {
        Method addMethod = SimpleCalculator.class.getDeclaredMethod("add", double.class, double.class);
        addMethod.setAccessible(true);
        
        // Test cases with large positive numbers
        double result1 = (double) addMethod.invoke(calculator, 1000000.0, 2000000.0);
        assertEquals(3000000.0, result1, 0.001, "1000000 + 2000000 should equal 3000000");
        
        double result2 = (double) addMethod.invoke(calculator, 999.999, 0.001);
        assertEquals(1000.0, result2, 0.001, "999.999 + 0.001 should equal 1000.0");
    }
    
    @Test
    @DisplayName("Test addition commutative property")
    void testAdditionCommutativeProperty() throws Exception {
        Method addMethod = SimpleCalculator.class.getDeclaredMethod("add", double.class, double.class);
        addMethod.setAccessible(true);
        
        // Test commutative property: a + b = b + a
        double a = 15.5;
        double b = 24.3;
        
        double result1 = (double) addMethod.invoke(calculator, a, b);
        double result2 = (double) addMethod.invoke(calculator, b, a);
        
        assertEquals(result1, result2, 0.001, "Addition should be commutative: a + b = b + a");
        assertEquals(39.8, result1, 0.001, "15.5 + 24.3 should equal 39.8");
    }
    
    @Test
    @DisplayName("Test addition with very small positive numbers")
    void testAdditionWithVerySmallPositiveNumbers() throws Exception {
        Method addMethod = SimpleCalculator.class.getDeclaredMethod("add", double.class, double.class);
        addMethod.setAccessible(true);
        
        // Test cases with very small positive numbers
        double result1 = (double) addMethod.invoke(calculator, 0.001, 0.002);
        assertEquals(0.003, result1, 0.0001, "0.001 + 0.002 should equal 0.003");
        
        double result2 = (double) addMethod.invoke(calculator, 0.0001, 0.0009);
        assertEquals(0.001, result2, 0.0001, "0.0001 + 0.0009 should equal 0.001");
    }
}