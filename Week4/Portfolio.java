import java.util.*;

public class Portfol0io {
    static class Asset {
        String name;
        double returnRate;
        double volatility;

        Asset(String name, double returnRate, double volatility) {
            this.name = name;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }

        @Override
        public String toString() {
            return name + ":" + returnRate + "%";
        }
    }

    static void mergeSort(Asset[] assets, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(assets, left, mid);
            mergeSort(assets, mid + 1, right);
            merge(assets, left, mid, right);
        }
    }

    static void merge(Asset[] assets, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];
        for (int i = 0; i < n1; i++) L[i] = assets[left + i];
        for (int j = 0; j < n2; j++) R[j] = assets[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                assets[k++] = L[i++];
            } else {
                assets[k++] = R[j++];
            }
        }
        while (i < n1) assets[k++] = L[i++];
        while (j < n2) assets[k++] = R[j++];
    }

    static void quickSort(Asset[] assets, int low, int high) {
        if (low < high) {
            int pi = partition(assets, low, high);
            quickSort(assets, low, pi - 1);
            quickSort(assets, pi + 1, high);
        }
    }

    static int partition(Asset[] assets, int low, int high) {
        Asset pivot = assets[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (assets[j].returnRate > pivot.returnRate ||
                    (assets[j].returnRate == pivot.returnRate &&
                            assets[j].volatility < pivot.volatility)) {
                i++;
                Asset temp = assets[i];
                assets[i] = assets[j];
                assets[j] = temp;
            }
        }
        Asset temp = assets[i + 1];
        assets[i + 1] = assets[high];
        assets[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12, 0.25),
                new Asset("TSLA", 8, 0.40),
                new Asset("GOOG", 15, 0.20)
        };

        Asset[] mergeSorted = Arrays.copyOf(assets, assets.length);
        mergeSort(mergeSorted, 0, mergeSorted.length - 1);
        System.out.println("MergeSort Ascending: " + Arrays.toString(mergeSorted));

        Asset[] quickSorted = Arrays.copyOf(assets, assets.length);
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("QuickSort Descending: " + Arrays.toString(quickSorted));
    }
}
