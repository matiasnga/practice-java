import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PracticeJavaTest {

    @Test
    public void test() {

        List<Integer> integerList = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            integerList.add(i);
        }

        Predicate<Integer> isPrimeNumber = number -> {
            if (number <= 1) {
                return false;
            }
            return IntStream.rangeClosed(2, (int) Math.sqrt(number))
                    .allMatch(n -> number % n != 0);
        };

        List<Integer> primeList = integerList.stream()
                .filter(isPrimeNumber)
                .collect(Collectors.toList());

        System.out.println(primeList);
        assertEquals("[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]", primeList.toString());
    }
}
