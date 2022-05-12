package string;

import java.util.*;

/**
 * 给一个字典String[] words = {"scasns", "cat", "baby", "dog", "bird", "car", "ax", "scans"},
 * 另一个String s = "tscabnihjs", 问第二个String中的字符能否构成第一个字典中的单词，注意每个字符只能使用一次
 */
public class PossibleEngWords {
    public static void main(String[] args) {
        Map<Character, Integer> charCount = new HashMap<>();

        String inputStr = "tscabnihjs";
        char[] inputChar = inputStr.toCharArray();
        for (int i = 0; i < inputChar.length; i++) {
            Integer value = charCount.get(inputChar[i]);
            if (value == null) {
                value = 1;
            } else {
                value++;
            }

            charCount.put(inputChar[i], value);
        }

        List<String> results = new ArrayList<>();

        String[] words = {"scasns", "cat", "baby", "dog", "bird", "car", "ax", "scans"};
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Map<Character, Integer> charMoreCount = new HashMap<>();

            int allMatch = 0;
            for (int j = 0; j < word.length(); j++) {
                Integer value = null;
                value = charMoreCount.get(word.charAt(j));
                if (value != null) {
                    value--;

                    if (value < 0)
                        break;

                    charMoreCount.put(word.charAt(j), value);
                } else {
                    value = charCount.get(word.charAt(j));

                    if (value == null)
                        break;
                    charMoreCount.put(word.charAt(j), --value);
                }

                allMatch++;
            }

            if (allMatch == word.length())
                results.add(word);
        }

        System.out.println(results);
    }
}

