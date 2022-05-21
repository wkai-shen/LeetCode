package Hash;

import java.util.Objects;

/**
 * This custom string hashmap is going to put entry <String, String>
 *     and return the previous added value of the same key
 */
public class CustomStringHashMap {
    public static void main(String[] args) {
        CustomStringHashMap stringHashMap = new CustomStringHashMap();

        String result = stringHashMap.put("AAA", "a1");
        result = stringHashMap.put("AAA", "a2");  // result = a1
        result = stringHashMap.put("BBB", "b1");
        result = stringHashMap.put("CCC", "c1");
        result = stringHashMap.put("DDD", "d1");
        result = stringHashMap.put("EEE", "e1");
        result = stringHashMap.put("DDD", "d2");  // result = d1
    }

    static final int BUCKET_SIZE = 10;

    static class Entry {
        String key;
        String value;

        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        // For tracing the entry
        Entry next;
    }

    Entry[] bucket = new Entry[BUCKET_SIZE];

    private int hashCode(String key) {
        int result = Objects.hash(key);

        // or this traditional way...
//        int result = 7;
//        result = result * 11 + key.hashCode();

        // Because bucket size is limited.
        return result % BUCKET_SIZE;
    }

    /**
     * Add the new entry to the hashmap.
     *
     * @param key
     * @param value
     * @return Return the previous value if the key already exists; otherwise, return NULL.
     */
    public String put(String key, String value) {
        int hashId = hashCode(key);
        Entry newEntry = new Entry(key, value);
        Entry currEntry = bucket[hashId];

        if (currEntry != null) {
            Entry pointer = currEntry;
            Entry lastEntry = currEntry;
            // Handle collision...
            while (pointer != null) {
                // If the key exists in this entry of the bucket, replace its value with the new one
                if (pointer.key.equals(key)) {
                    String currValue = pointer.value;
                    pointer.value = value;

                    return currValue;
                }

                lastEntry = pointer;
                pointer = pointer.next;
            }

            lastEntry.next = newEntry;
        } else {
            bucket[hashId] = newEntry;
        }

        return null;
    }
}
