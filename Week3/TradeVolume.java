import java.util.*;

public class TradeVolume {
    static class Trade {
        String id;
        int volume;

        Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return id + ":" + volume;
        }
    }

    static void mergeSort(Trade[] trades, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(trades, left, mid);
            mergeSort(trades, mid + 1, right);
            merge(trades, left, mid, right);
        }
    }

    static void merge(Trade[] trades, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];
        for (int i = 0; i < n1; i++) L[i] = trades[left + i];
        for (int j = 0; j < n2; j++) R[j] = trades[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) {
                trades[k++] = L[i++];
            } else {
                trades[k++] = R[j++];
            }
        }
        while (i < n1) trades[k++] = L[i++];
        while (j < n2) trades[k++] = R[j++];
    }

    static void quickSort(Trade[] trades, int low, int high) {
        if (low < high) {
            int pi = partition(trades, low, high);
            quickSort(trades, low, pi - 1);
            quickSort(trades, pi + 1, high);
        }
    }

    static int partition(Trade[] trades, int low, int high) {
        int pivot = trades[high].volume;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (trades[j].volume >= pivot) {
                i++;
                Trade temp = trades[i];
                trades[i] = trades[j];
                trades[j] = temp;
            }
        }
        Trade temp = trades[i + 1];
        trades[i + 1] = trades[high];
        trades[high] = temp;
        return i + 1;
    }

    static Trade[] mergeLists(Trade[] list1, Trade[] list2) {
        Trade[] merged = new Trade[list1.length + list2.length];
        int i = 0, j = 0, k = 0;
        while (i < list1.length && j < list2.length) {
            if (list1[i].volume <= list2[j].volume) {
                merged[k++] = list1[i++];
            } else {
                merged[k++] = list2[j++];
            }
        }
        while (i < list1.length) merged[k++] = list1[i++];
        while (j < list2.length) merged[k++] = list2[j++];
        return merged;
    }

    static int totalVolume(Trade[] trades) {
        int sum = 0;
        for (Trade t : trades) sum += t.volume;
        return sum;
    }

    public static void main(String[] args) {
        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        Trade[] mergeSorted = Arrays.copyOf(trades, trades.length);
        mergeSort(mergeSorted, 0, mergeSorted.length - 1);
        System.out.println("MergeSort Ascending: " + Arrays.toString(mergeSorted));

        Trade[] quickSorted = Arrays.copyOf(trades, trades.length);
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("QuickSort Descending: " + Arrays.toString(quickSorted));

        Trade[] morning = { new Trade("m1", 200), new Trade("m2", 150) };
        Trade[] afternoon = { new Trade("a1", 300), new Trade("a2", 250) };
        mergeSort(morning, 0, morning.length - 1);
        mergeSort(afternoon, 0, afternoon.length - 1);
        Trade[] merged = mergeLists(morning, afternoon);
        System.out.println("Merged List: " + Arrays.toString(merged));
        System.out.println("Total Volume: " + totalVolume(merged));
//        committed issues
    }
}
