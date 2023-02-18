import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Test
    void checkConstructorByNameIsNull(){
        Throwable ex = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5));
        assertEquals("Name cannot be null.", ex.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {""," ", "\t","\n"})
    void checkConstructorByNameIsEmpty(String name){
        Throwable ex = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 5));
        assertEquals("Name cannot be blank.", ex.getMessage());
    }
    @Test
    void checkConstructorBySpeedIsNegative(){
        Throwable ex = assertThrows(IllegalArgumentException.class, () -> new Horse("Roach", -5));
        assertEquals("Speed cannot be negative.", ex.getMessage());
    }
    @Test
    void checkConstructorByDistanceIsNegative(){
        Throwable ex = assertThrows(IllegalArgumentException.class, () -> new Horse("Roach", 5, -10));
        assertEquals("Distance cannot be negative.", ex.getMessage());
    }

    Horse roach;
    @BeforeEach
    void initRoach(){
        roach = new Horse("Roach", 5.5,1.1);
    }
    @Test
    void getNameTest(){
         assertEquals("Roach", roach.getName());
    }

    @Test
    void getSpeedTest(){
        assertEquals(5.5, roach.getSpeed());
    }

    @Test
    void getDistanceTest(){
        assertEquals(1.1, roach.getDistance());
    }
}
