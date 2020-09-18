package com.wildt;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class ProblemB {
    private static ArrayList<char[]> wordList = new ArrayList<>();

    public static void main() {
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            addWordsToCharList(input);
        }

        /*addWordsToCharList("at");
        addDistinctCharsToList("at");

        addWordsToCharList("ordeals");
        addDistinctCharsToList("ordeals");

        addWordsToCharList("abcdefghijklmnopqrstuvwxyz");
        addDistinctCharsToList("abcdefghijklmnopqrstuvwxyz");

        addWordsToCharList("abcdefghijklmabcdefghijklm");
        addDistinctCharsToList("abcdefghijklmabcdefghijklm");

        addWordsToCharList("abcdABCDabcd");
        addDistinctCharsToList("abcdABCDabcd");
*/
        for (char[] chars : wordList) {
            System.out.println(calculatePossiblePermutations(chars));
        }
    }

    private static BigInteger calculatePossiblePermutations(char[] word) {
        BigInteger result = calculateFactorial(word.length);

        Map<Character, Long> counter = new String(word)
                .chars()
                .boxed()
                .collect(Collectors.groupingBy(o -> (char) o.longValue(), Collectors.counting()));

        for (Long i : counter.values()) {
            result = result.divide(calculateFactorial(i.intValue()));
        }
        return result;
    }

    private static void addWordsToCharList(Scanner input) {
        String word = "";
        try {
            word = input.next();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Adding word failed.");
        }
        char[] wordToCharList = word.toCharArray();
        wordList.add(wordToCharList);
    }

    private static BigInteger calculateFactorial(int number) {
        BigInteger fact = BigInteger.valueOf(1);
        for (int i = number; i > 0; i--) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }
}
