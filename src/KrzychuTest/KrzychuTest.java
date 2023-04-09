package KrzychuTest;

import Shared.LaazyContainers;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KrzychuTest {
    public static void main(String[] args) {
        int range = 123456789;
        List<Integer> primeNumbers = computePrimeNumbers(range);
        List<List<Integer>> listOfNumbersDigits = getSeparateDigitsFromNumbers(primeNumbers);
        Map<List<Integer>, Integer> mapWithNumberOfSimilarNumbers = getNumberOFSimilarNumbers(listOfNumbersDigits);
        List<Integer> key = getDigitsWithTheMostSimilarNumbers(mapWithNumberOfSimilarNumbers);
        System.out.println("digits: " + key);
        System.out.println("number of similar numbers: " + mapWithNumberOfSimilarNumbers.get(key));
    }

    private static List<Integer> getDigitsWithTheMostSimilarNumbers(Map<List<Integer>, Integer> getMap21) {
        return getMap21.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
    }

    static Map<List<Integer>, Integer> getNumberOFSimilarNumbers(List<List<Integer>> numbers) {
        List<List<Integer>> distinct = numbers.stream().distinct().collect(Collectors.toList());
        Map<List<Integer>, Integer> result = new HashMap<>();

        for (int i = 0; i < distinct.size(); i++) {
            Integer numbersOfSimilar = 0;
            for (int j = 0; j < numbers.size(); j++) {
                if (distinct.get(i).equals(numbers.get(j))) {
                    numbersOfSimilar++;
                }
            }
            result.put(distinct.get(i), numbersOfSimilar);
//            System.out.println("cos: " + distinct.get(i));
        }
        return result;
    }

    static List<Integer> computePrimeNumbers(int range) {
        int tablica[] = new int[range + 1];
        int sqrt = (int) Math.floor(Math.sqrt(range));
        for (int i = 1; i <= range; i++) {
            tablica[i] = i;
        }
        for (int i = 2; i <= sqrt; i++) {
            if (tablica[i] != 0) {
                int j = i + i;
                while (j <= range) {
                    tablica[j] = 0;
                    j += i;
                }
            }
        }
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= range; i++) {
            if (tablica[i] != 0) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    static List<List<Integer>> getSeparateDigitsFromNumbers(List<Integer> integers) {
        List<List<Integer>> separateDigitsList = new ArrayList<>();
        for (int x = 0; x < integers.size(); x++) {
            List<Integer> separateDigits = new ArrayList<>();
            int y = integers.get(x);
            while (y > 0) {
                separateDigits.add(y % 10);
                y = y / 10;
            }
            separateDigits = separateDigits.stream().sorted().collect(Collectors.toList());
            separateDigitsList.add(separateDigits);
        }
        return separateDigitsList;
    }

}
