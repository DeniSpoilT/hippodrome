import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {
    @Test
    void checkConstructorByNull() {
        Throwable ex = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", ex.getMessage());
    }

    @Test
    void checkConstructorByListIsEmpty() {
        List<Horse> emptyList = new ArrayList<>();
        Throwable ex = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));

        assertEquals("Horses cannot be empty.", ex.getMessage());
    }

    @Test
    void checkThatGetHorsesReturnsCorrectList() {
        int id = 0;
        String name = "Horse №";
        List<Horse> horses = Stream.generate(() -> new Horse(name + id, 5.4, 8.2))
                .limit(30)
                .collect(Collectors.toList());
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void checkThatMoveCallsForAllHorses() {
        List<Horse> horses = Stream.generate(() -> mock(Horse.class))
                .limit(50)
                .collect(Collectors.toList());
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        horses.forEach(horse -> verify(horse).move());
    }

    @Test
    void checkThatGetWinnerReturnsHorseWithLargesDistance() {
        List<Horse> horses = List.of(
                new Horse("Буцефал", 2.4, 1.0),
                new Horse("Туз Пик", 2.5, 5.6),
                new Horse("Зефир", 2.6, 0.1),
                new Horse("Пожар", 2.7, 0.0));
        Hippodrome hippodrome = new Hippodrome(horses);

        assertSame(horses.get(1), hippodrome.getWinner());
    }

}
