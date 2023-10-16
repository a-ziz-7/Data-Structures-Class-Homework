import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class AdvancedArrayOperations {

    public static void main(String[] args) throws Exception {

        String path = "C:\\Users\\azabd\\Java Pro\\A CISC 3130 Data Structures\\HW2\\src\\data.txt";
        String outputPath = "C:\\Users\\azabd\\Java Pro\\A CISC 3130 Data Structures\\HW2\\src\\output.txt";
        Scanner sc = new Scanner(new File(path));
        PrintWriter o = new PrintWriter(outputPath);

        int size = sc.nextInt();
        double[] originalArray;
        double[] howFarArray;
        double howFarAverage;

        // reads data from input file
        originalArray = readData(size, sc, o);
        // prints data with header
        printArray(originalArray, size, "The original values", o);

        //finds and prints average of the original array
        double average = findAverage(originalArray, size);
        o.println("\n\nThe average of the original array is:\n" +
                String.format("%-8.3f", average));

        // calculates the distance (value - average) and puts then into the new array
        howFarArray = howFarAway(size, average, originalArray);
        printArray(howFarArray, size, "\nHow far array is", o);
        
        // average of howFarArray
        howFarAverage = findAverage(howFarArray, size);
        o.println("\n\nThe average of how far array is:\n" +
                String.format("%-8.3f", howFarAverage * -1));

        // prints closest and furthest (from average )values
        int maxDif = dist(howFarArray, size, true); // id od max
        int minDif = dist(howFarArray, size, false); // id of min

        o.println("\nThe furthest value from the average is: " +
                String.format("%.3f", originalArray[maxDif]) +
                " (" + String.format("%.3f", Math.abs(howFarArray[maxDif])) + ")");
        o.println("The closest value to the average is: " +
                String.format("%.3f", originalArray[minDif]) +
                " (" + String.format("%.3f", Math.abs(howFarArray[minDif])) + ")");


        sc.close();
        o.close();

    }
    
    // reads input file and prints in the output file
    public static double[] readData(int n, Scanner sc, PrintWriter out) {

        double[] array = new double[n];
        double value;
        StringBuilder oData = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (sc.hasNext()) {
                value = sc.nextDouble();
                oData.append(value).append("   ");
                array[i] = value;

            }
        }
        out.println("Values from the input file:\n" + n);
        out.println(oData + "\n");
        return array;

    }
    
    // prints formatted output
    public static void printArray(double[] arr, int n, String header, PrintWriter out) {
        
        // prints a header
        out.print(header + ":\n");
        for (int i = 0; i < n; i++) {
            // formatting form positive and negative values
            out.print((arr[i] >= 0 ? " " + String.format("%-7.3f", arr[i]) + " " :
                    String.format("%-8.3f", arr[i]) + " "));
            if ((i + 1) % 5 == 0) {
                out.println();
            }

        }

    }

    // simply finds the average
    public static double findAverage(double[] arr, int n) {

        double sum = 0;

        for (int i = 0; i < n; i++) {

            sum += arr[i];

        }
        return sum / n;
    }

    // creates an array with distances from the average
    public static double[] howFarAway(int n, double ave, double[] arr) {

        double[] array = new double[n];

        for (int i = 0; i < n; i++) {

            array[i] = arr[i] - ave;

        }

        return array;

    }

    // finds max if b is true else min
    public static int dist(double[] arr, int n, boolean b) {

        int temp = 0;
        for (int i = 1; i < n; i++) {
            if (b && Math.abs(arr[i]) > Math.abs(arr[temp])) {
                temp = i;
            }
            if (!b && Math.abs(arr[i]) < Math.abs(arr[temp])) {
                temp = i;
            }

        }

        return temp;
    }

}
