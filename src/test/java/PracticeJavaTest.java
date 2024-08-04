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
        List<Integer> integerList = IntStream.rangeClosed(0, 100).boxed().collect(Collectors.toList());

        Predicate<Integer> isPrimeNumber = number -> number > 1 &&
                IntStream.rangeClosed(2, (int) Math.sqrt(number))
                        .allMatch(n -> number % n != 0);

        List<Integer> primeList = integerList.stream()
                .filter(isPrimeNumber)
                .collect(Collectors.toList());

        System.out.println(primeList);
        assertEquals("[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]", primeList.toString());
    }

    @Test
    public void convertUppercaseIfStartsWithLetterA() {
        List<String> nameList = Arrays.asList("Matias", "Rocky", "Maru", "Milton");

        Predicate<String> startsWithLetterM = string -> string.startsWith("M");

        nameList.replaceAll(str -> startsWithLetterM.test(str) ? str.toUpperCase() : str);
        nameList.sort(String::compareTo);
        System.out.println(nameList);
        assertEquals(nameList.toString(), "[MARU, MATIAS, MILTON, Rocky]");
    }
}
