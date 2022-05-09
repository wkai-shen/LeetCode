package recursion.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public static void main(String[] args) {
        /**
         * ABC
         * ACB
         * BAC
         * BCA
         * CAB
         * CBA
         */
        List<String> results = getPermutation("ABC", 0, Character.MIN_VALUE);
        results.stream().forEach(System.out::println);

        System.out.println("-----");

        /**
         * ABC
         * ACB
         * BAC
         * CAB
         */
        results = getPermutation("ABC", 3, 'A');
        results.stream().forEach(System.out::println);
    }

    /**
     * Advanced Permutation calculation.
     *
     * @param source Original string
     * @param ignorePos When a char is in this post, this permutation is ignored
     * @param ignoreChar When a char is in this post, this permutation is ignored
     * @return
     */
    static List<String> getPermutation(String source, int ignorePos, char ignoreChar) {
        List<String> results = new ArrayList<>();

        getPermutationRecursion(results, "", source, ignorePos, ignoreChar);

        return results;
    }

    static void getPermutationRecursion(List<String> results, String soFar, String remaining,
                                        int ignorePos, char ignoreChar) {
        if (ignorePos > 0 && soFar.length() == ignorePos) {
            char lastCharSoFar = soFar.charAt(soFar.length() - 1);
            if (lastCharSoFar == ignoreChar)
                return;
        }

        if (remaining.isEmpty()) {
            results.add(soFar);
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                String newSoFar = soFar + remaining.charAt(i);
                String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);

                getPermutationRecursion(results, newSoFar, newRemaining, ignorePos, ignoreChar);
            }
        }
    }
}
