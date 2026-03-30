import java.util.*;

public class AccountLookup {
    static int linearFirst(String[] logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                System.out.println("Linear first " + target + ": index " + i + " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Linear search: target not found");
        return -1;
    }

    static int linearLast(String[] logs, String target) {
        int comparisons = 0;
        int lastIndex = -1;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                lastIndex = i;
            }
        }
        if (lastIndex != -1) {
            System.out.println("Linear last " + target + ": index " + lastIndex + " (" + comparisons + " comparisons)");
        } else {
            System.out.println("Linear search: target not found");
        }
        return lastIndex;
    }

    static int binarySearch(String[] logs, String target) {
        int low = 0, high = logs.length - 1;
        int comparisons = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            int cmp = logs[mid].compareTo(target);
            if (cmp == 0) {
                System.out.println("Binary " + target + ": index " + mid + " (" + comparisons + " comparisons)");
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Binary search: target not found (" + comparisons + " comparisons)");
        return -1;
    }

    static int countOccurrences(String[] logs, String target) {
        int count = 0;
        for (String log : logs) {
            if (log.equals(target)) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};
        Arrays.sort(logs);
        System.out.println("Sorted logs: " + Arrays.toString(logs));

        linearFirst(logs, "accB");
        linearLast(logs, "accB");

        int index = binarySearch(logs, "accB");
        if (index != -1) {
            int count = countOccurrences(logs, "accB");
            System.out.println("Binary count of accB: " + count);
        }
    }
}
