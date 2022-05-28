package slidingwindow;

import java.util.*;

/*
Suppose we have an unsorted log file of accesses to web resources. Each log entry consists of an access time, the ID of the user making the access, and the resource ID.

The access time is represented as seconds since 00:00:00, and all times are assumed to be in the same day.

Example:
logs1 = [
    ["58523", "user_1", "resource_1"],
    ["62314", "user_2", "resource_2"],
    ["54001", "user_1", "resource_3"],
    ["200", "user_6", "resource_5"],
    ["215", "user_6", "resource_4"],
    ["54060", "user_2", "resource_3"],
    ["53760", "user_3", "resource_3"],
    ["58522", "user_22", "resource_1"],
    ["53651", "user_5", "resource_3"],
    ["2", "user_6", "resource_1"],
    ["100", "user_6", "resource_6"],
    ["400", "user_7", "resource_2"],
    ["100", "user_8", "resource_6"],
    ["54359", "user_1", "resource_3"],
]

Example 2:
logs2 = [
    ["300", "user_1", "resource_3"],
    ["599", "user_1", "resource_3"],
    ["900", "user_1", "resource_3"],
    ["1199", "user_1", "resource_3"],
    ["1200", "user_1", "resource_3"],
    ["1201", "user_1", "resource_3"],
    ["1202", "user_1", "resource_3"]
]

Example 3:
logs3 = [
    ["300", "user_10", "resource_5"]
]

Write a function that takes the logs and returns the resource with the highest number of accesses in any 5 minute window, together with how many accesses it saw.

Expected Output:
most_requested_resource(logs1) # => ('resource_3', 3) [resource_3 is accessed at 53760, 54001, and 54060]
most_requested_resource(logs2) # => ('resource_3', 4) [resource_3 is accessed at 1199, 1200, 1201, and 1202]
most_requested_resource(logs3) # => ('resource_5', 1) [resource_5 is accessed at 300]

Complexity analysis variables:

n: number of logs in the input
*/
public class MaxCountIn5Min {
    public static void main(String[] argv) {
        String[][] logs1 = new String[][]{
                {"58523", "user_1", "resource_1"},
                {"62314", "user_2", "resource_2"},
                {"54001", "user_1", "resource_3"},
                {"200", "user_6", "resource_5"},
                {"215", "user_6", "resource_4"},
                {"54060", "user_2", "resource_3"},
                {"53760", "user_3", "resource_3"},
                {"58522", "user_22", "resource_1"},
                {"53651", "user_5", "resource_3"},
                {"2", "user_6", "resource_1"},
                {"100", "user_6", "resource_6"},
                {"400", "user_7", "resource_2"},
                {"100", "user_8", "resource_6"},
                {"54359", "user_1", "resource_3"},
        };

        String result = getMaxCountIn5Min(logs1, 5);
        System.out.println(result);

        String[][] logs2 = new String[][]{
                {"300", "user_1", "resource_3"},
                {"599", "user_1", "resource_3"},
                {"900", "user_1", "resource_3"},
                {"1199", "user_1", "resource_3"},
                {"1200", "user_1", "resource_3"},
                {"1201", "user_1", "resource_3"},
                {"1202", "user_1", "resource_3"}
        };

        result = getMaxCountIn5Min(logs2, 5);
        System.out.println(result);

        String[][] logs3 = new String[][]{
                {"300", "user_10", "resource_5"}
        };

        result = getMaxCountIn5Min(logs3, 5);
        System.out.println(result);

    }

    public static String getMaxCountIn5Min(String[][] logs, int mins) {
        Map<String, List<Integer>> resourceToTimeStamp = new HashMap<>();

        for (int rowIdx = 0; rowIdx < logs.length; rowIdx++) {
            int timeStamp = Integer.parseInt(logs[rowIdx][0]);
            String resourceName = logs[rowIdx][2];

            List<Integer> timeStamps = resourceToTimeStamp.get(resourceName);
            if (timeStamps == null) {
                timeStamps = new ArrayList<>();
                resourceToTimeStamp.put(resourceName, timeStamps);
            }

            timeStamps.add(timeStamp);
        }

        for (List<Integer> each : resourceToTimeStamp.values()) {
            Collections.sort(each);
        }


        ////////////////////////
        // Use the sorting map to keep the top entry with the highest number
        TreeMap<Integer, String> countsToResources = new TreeMap<>(Collections.reverseOrder());

        for (String key : resourceToTimeStamp.keySet()) {
            List<Integer> timeList = resourceToTimeStamp.get(key);
            int maxCount = maxCountInMin(timeList, mins);
            countsToResources.put(maxCount, key);
        }

        return "The resource [" + countsToResources.firstEntry().getValue() + "]" +
                " has highest number of access [" + countsToResources.firstEntry().getKey() + "]" +
                "\nwhen their access times are in " + mins + " min(s) window";
    }

    // Slid window
    public static int maxCountInMin(List<Integer> timeList, int mins) {
        int rightIdx = 0;
        int leftIdx = 0;

        int maxCount = 0;

        while (rightIdx < timeList.size()) {
            int rightTime = timeList.get(rightIdx);
            int leftTime = timeList.get(leftIdx);

            if (rightTime - leftTime <= 60 * mins) {
                rightIdx++;
                continue;
            }

            maxCount = Math.max(maxCount, (rightIdx-1) - leftIdx + 1);

            while (rightTime - leftTime > 60 * mins) {
                leftIdx++;
                leftTime = timeList.get(leftIdx);
            }
        }

        maxCount = Math.max(maxCount, (rightIdx-1) - leftIdx + 1);

        return maxCount;
    }
}
