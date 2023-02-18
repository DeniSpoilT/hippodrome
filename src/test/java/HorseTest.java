import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    void checkConstructorByNameIsNull() {
        Throwable ex = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5));

        assertEquals("Name cannot be null.", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void checkConstructorByNameIsEmpty(String name) {
        Throwable ex = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 5));

        assertEquals("Name cannot be blank.", ex.getMessage());
    }

    @Test
    void checkConstructorBySpeedIsNegative() {
        Throwable ex = assertThrows(IllegalArgumentException.class, () -> new Horse("Roach", -5));

        assertEquals("Speed cannot be negative.", ex.getMessage());
    }

    @Test
    void checkConstructorByDistanceIsNegative() {
        Throwable ex = assertThrows(IllegalArgumentException.class, () -> new Horse("Roach", 5, -10));

        assertEquals("Distance cannot be negative.", ex.getMessage());
    }

    Horse roach;

    @BeforeEach
    void initRoach() {
        roach = new Horse("Roach", 5.5, 1.1);
    }

    @Test
    void getNameTest() {
        assertEquals("Roach", roach.getName());
    }

    @Test
    void getSpeedTest() {
        assertEquals(5.5, roach.getSpeed());
    }

    @Test
    void getDistanceTest() {
        assertEquals(1.1, roach.getDistance());
    }


    @Test
    void checkThatMoveCallsGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            roach.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }

    @ParameterizedTest
    @ValueSource(doubles = {0.8125, 0.3, 0.5489453})
    void checkThatDistanceIsCalculatedCorrectly(double mockRandom) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockRandom);
            roach.move();

            assertEquals(1.1 + 5.5 * mockRandom, roach.getDistance());
        }
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "0.2, 0.9",
            "0.3, 0.8",
            "0.0, 0.5"
    })
    void checkThatGetRandomDoubleCalculatedCorrectly(double min, double max) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Math.random()).thenReturn(0.2);
            double result = Horse.getRandomDouble(min, max);

            assertEquals((0.2 * (max - min)) + min, result);
        }
    }
}
