package recursion.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public static void main(String[] args) {
        List<String> results = getPermutation("ABC");
    }

    static List<String> getPermutation(String source) {
        List<String> results = new ArrayList<>();

        getPermutationRecursion(results, "", source);

        return results;
    }

    static void getPermutationRecursion(List<String> results, String soFar, String remaining) {
        if (remaining.isEmpty()) {
            results.add(soFar);
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                String newSoFar = soFar + remaining.charAt(i);
                String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);

                getPermutationRecursion(results, newSoFar, newRemaining);
            }
        }
    }
}
