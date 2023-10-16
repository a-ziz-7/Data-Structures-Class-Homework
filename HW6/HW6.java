import java.util.ArrayList;
import java.util.Arrays;

public class HW6 {

    private static int quickSortCounter = 0;
    private static int mergeSortCounter = 0;

    public static void main(String[] args) {

        ArrayList<int[][]> allA = new ArrayList();
        allA.add(new int[3][10]);
        allA.add(new int[3][30]);
        allA.add(new int[3][50]);
        allA.add(new int[3][100]);
        for (int[][] ints : allA) {
            makeRandom(ints[0]);
            sortedRandom(ints[1]);
            reverseRandom(ints[2]);
        }
        for (int[][] ints : allA) {
            System.out.println("Length: " + ints[0].length);
            for (int i = 0; i < 3; i++){
                if (i == 0){
                    System.out.println("\nRandom data set:\n");
                }else if(i == 1){
                    System.out.println("\nAlmost sorted data set:\n");
                }else{
                    System.out.println("\nAlmost reversed data set:");
                }

                System.out.println(Arrays.toString(ints[i]));
                int n = bubbleSort(ints[i]);
                System.out.println("Bubble sort: " + n);

                quickSort(ints[i], 0, ints[i].length - 1);
                System.out.println("Quick sort: " + quickSortCounter);
                quickSortCounter = 0;

                mergeSort(ints[i], 0, ints[i].length - 1);
                System.out.println("Merge sort: " + mergeSortCounter);
                mergeSortCounter = 0;
            }
            System.out.println("\n\n\n");
        }


    }

    public static void makeRandom(int[] arr) {
        int small = 0;
        int big = 100;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((1.0 * Math.random() * (big - small + 1)) + small);
        }
    }

    public static void sortedRandom(int[] arr) {
        int small = 0;
        int big = 5;
        for (int i = 0; i < arr.length; i++) {
            int temp = (int) ((1.0 * Math.random() * (big - small + 1)) + small);
            small = Math.max((temp - 1), 0);
//            big += (int) (5.0 * (100 - big) / arr.length);
            big += (int)(100.0/arr.length);
            arr[i] = Math.min(100, temp);
        }
//        System.out.println(Arrays.toString(arr));
    }

    public static void reverseRandom(int[] arr) {
        int small = 95;
        int big = 100;
        for (int i = 0; i < arr.length; i++) {
            int temp = (int) ((1.0 * Math.random() * (big - small + 1)) + small);
            big = Math.min((temp + 1), 100);
            small -= 2.0 * (small) / arr.length;
            arr[i] = temp;
        }
    }

    // bubble sort
    public static int bubbleSort(int[] arr) {
        int n = 0;
        boolean b = true;
        for (int i = 0; i < arr.length; i++) {
            n++;
            for (int j = 0; j < arr.length - i - 1; j++) {
                n++;
                if (arr[j] > arr[j + 1]) {
                    n++;
                    b = false;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (b){
                return n;
            }
        }
        return n;
    }
    
    // quick sort
    static int partition(int[] arr, int low, int high) {

        int pivot = arr[(low+high)/2];

        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            quickSortCounter++;
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        quickSortCounter++;
        swap(arr, i + 1, high);
        return (i + 1);
    }

    static void quickSort(int[] arr, int low, int high) {
        quickSortCounter++;
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // merge sort 
    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, l, L, 0, n1);
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];


        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            mergeSortCounter += 2;
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
            mergeSortCounter++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
            mergeSortCounter++;
        }
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }
}
