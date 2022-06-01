package QueueHeap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class CookiesAndSweet {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 3554, 2227, 8866, 9890, 212, 8669, 2423, 7651, 3878, 3379,
                1419, 6134, 5767, 859,
                2848, 9309, 1449, 8408, 8041, 3367, 6676, 6382, 4136, 4871);
        int cookies = cookies(47245, list);  // 20

        Collections.addAll(list, 3554, 2227, 8866, 9890, 212, 8669, 2423, 7651, 3878, 3379,
                1419, 6134, 5767, 859,
                2848, 9309, 1449, 8408, 8041, 3367, 6676, 6382, 4136, 4871);
        cookies = cookiesOtherSolution(47245, list);
    }

    /**
     * This one is using Priority Queue Java implemented
     * @param k the threshold
     * @param A the list of cookies
     * @return
     */
    public static int cookiesOtherSolution(int k, List<Integer> A) {
        PriorityQueue<Integer> smallestPQ = new PriorityQueue<>();

        for (int each: A) {
            smallestPQ.add(each);
        }

        int result = 0;

        while (smallestPQ.peek() < k) {
            if (smallestPQ.size() >= 2) {
                int smallValue1 = smallestPQ.poll();
                int smallValue2 = smallestPQ.poll();

                int newValue = 1 * smallValue1 + 2 * smallValue2;

                smallestPQ.add(newValue);

                result++;
            } else {
                result = -1;
                break;
            }
        }

        return result;
    }

    /**
     * My own implementation. But the performance is not good enough
     * @param k
     * @param A
     * @return
     */
    public static int cookies(int k, List<Integer> A) {
        int result = 0;

        // Sort the list for the first one is the smallest
        Collections.sort(A);
        // Sort the elements after they apply to the formula
        List<Integer> processedList = new ArrayList<>();

        // Make sure there is no element smaller than K
        while (binarySearchSmaller(A, k, 0, A.size()-1) != -1
                || binarySearchSmaller(processedList, k, 0, processedList.size()-1) != -1) {

            int aIdx = 0;
            int pIdx = 0;

            int valueA = Integer.MAX_VALUE;
            int valueP = Integer.MAX_VALUE;

            List<Integer> smallest2 = new ArrayList<>();

            // Find the 2 smallest elements for both lists
            while (smallest2.size() < 2) {
                if (A.size() + processedList.size() < 2) {
                    return -1;
                }

                if (pIdx < processedList.size()) {
                    valueP = processedList.get(pIdx);
                }

                if (aIdx < A.size()) {
                    valueA = A.get(aIdx);
                }

                if (valueA <= valueP) {
                    smallest2.add(valueA);
                    A.remove(aIdx);

                    if (A.isEmpty()) {
                        valueA = Integer.MAX_VALUE;
                    }

                    continue;
                }

                if (valueA > valueP) {
                    smallest2.add(valueP);
                    processedList.remove(pIdx);

                    if (processedList.isEmpty()) {
                        valueP = Integer.MAX_VALUE;
                    }

                    continue;
                }
            }

            // Here is the formula
            int newValue = 1 * smallest2.get(0) + 2 * smallest2.get(1);

            processedList.add(newValue);
            Collections.sort(processedList);  // Maintain the sorting

            result++;
        }

        return result;
    }

    public static int binarySearchSmaller(List<Integer> source, int value, int startIdx, int endIdx) {
        if (source.isEmpty()) {
            return -1;
        }

        ////// Find the prev index which is just smaller than this value
        if (startIdx > endIdx) {
            return endIdx;
        }

        if (endIdx == startIdx) {
            if (value > source.get(startIdx))
                return startIdx;
            else if (value < source.get(startIdx)) {
                if (startIdx - 1 >= 0)
                    return startIdx - 1;
                else
                    return -1;
            }
        }

        int mid = startIdx + (endIdx - startIdx) / 2;

        if (source.get(mid) == value) {
            return mid;
        } else if (source.get(mid) > value) {
            return binarySearchSmaller(source, value, startIdx, mid - 1);
        } else {
            return binarySearchSmaller(source, value, mid + 1, endIdx);
        }
    }
}
