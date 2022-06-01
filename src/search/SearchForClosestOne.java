package search;

import java.util.Arrays;
import java.util.List;

public class SearchForClosestOne {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9, 10, 11);

        int result = search(list, 10, 0, list.size() - 1);
        result = search(list, 2, 0, list.size() - 1);

        list = Arrays.asList(1, 3, 5, 7, 9, 20, 23, 26, 28, 30);
        result = search(list, 0, 0, list.size() - 1);

        list = Arrays.asList(10125,11310,12770,16405,18898,19736,23733,24935,26401,29089);
        result = search(list, 47245, 0, list.size() - 1);
    }

    public static int search(List<Integer> source, int value, int startIdx, int endIdx) {
///// Find the next index which is just bigger than this value
//        if (startIdx > endIdx) {
//            return startIdx;
//        }
//
//        if (endIdx == startIdx) {
//            if (value < source.get(startIdx))
//                return startIdx;
//            else if (value > source.get(startIdx)) {
//                if (startIdx + 1 < source.size())
//                    return startIdx + 1;
//                else
//                    return -1;
//            }
//        }

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
            return search(source, value, startIdx, mid - 1);
        } else {
            return search(source, value, mid + 1, endIdx);
        }
    }

    public static int runBinarySearchRecursively(
            List<Integer> source, int key, int low, int high) {
        int middle = low + ((high - low) / 2);

        if (high < low) {
            return -1;
        }

        if (key == source.get(middle)) {
            return middle;
        } else if (key < source.get(middle)) {
            return runBinarySearchRecursively(
                    source, key, low, middle - 1);
        } else {
            return runBinarySearchRecursively(
                    source, key, middle + 1, high);
        }
    }
}
