import java.util.*;

public class TransactionSort {
    static class Transaction {
        String id;
        double fee;
        String timestamp;

        Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return id + ": fee=" + fee + ", ts=" + timestamp;
        }
    }

    static void bubbleSort(List<Transaction> transactions) {
        int n = transactions.size();
        int passes = 0, swaps = 0;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (transactions.get(j).fee > transactions.get(j + 1).fee) {
                    Collections.swap(transactions, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        System.out.println("BubbleSort Result: " + transactions);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    static void insertionSort(List<Transaction> transactions) {
        int n = transactions.size();
        int shifts = 0;
        for (int i = 1; i < n; i++) {
            Transaction key = transactions.get(i);
            int j = i - 1;
            while (j >= 0 && (transactions.get(j).fee > key.fee ||
                    (transactions.get(j).fee == key.fee &&
                            transactions.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                transactions.set(j + 1, transactions.get(j));
                j--;
                shifts++;
            }
            transactions.set(j + 1, key);
        }
        System.out.println("InsertionSort Result: " + transactions);
        System.out.println("Shifts: " + shifts);
    }

    static void flagOutliers(List<Transaction> transactions) {
        for (Transaction t : transactions) {
            if (t.fee > 50) {
                System.out.println("Outlier flagged: " + t);
            }
        }
    }

    public static void main(String[] args) {
        List<Transaction> smallBatch = new ArrayList<>();
        smallBatch.add(new Transaction("id1", 10.5, "10:00"));
        smallBatch.add(new Transaction("id2", 25.0, "09:30"));
        smallBatch.add(new Transaction("id3", 5.0, "10:15"));

        System.out.println("\n--- Small Batch (Bubble Sort) ---");
        bubbleSort(smallBatch);
        flagOutliers(smallBatch);

        List<Transaction> mediumBatch = new ArrayList<>();
        mediumBatch.add(new Transaction("id1", 10.5, "10:00"));
        mediumBatch.add(new Transaction("id2", 25.0, "09:30"));
        mediumBatch.add(new Transaction("id3", 5.0, "10:15"));
        mediumBatch.add(new Transaction("id4", 25.0, "09:45"));

        System.out.println("\n--- Medium Batch (Insertion Sort) ---");
        insertionSort(mediumBatch);
        flagOutliers(mediumBatch);
    }
}
