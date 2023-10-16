import java.io.File;
import java.util.Scanner;

public class recursiveNdimArrayIndices {

    public static void main(String[] args) throws Exception {

        String path = "C:\\Users\\azabd\\Java Pro\\A CISC 3130 Data Structures\\ExtraCreditHW\\src\\inp.txt";
        Scanner sc = new Scanner(new File(path));
        int n, x = 0, y = 0, z = 0;

        while (sc.hasNext()) {
            n = sc.nextInt();
            // I could also use 2 dimensional array and a for loop to read x y z
            if (n == 3) {
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
                z = sc.nextInt() - 1;
            } else if (n == 2) {
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
            } else if (n == 1) {
                x = sc.nextInt() - 1;
            }
            rec(n, 0, x, 0, y, 0, z);
            y = 0;
            z = 0;
            System.out.println("\n-------------\n");
        }

    }

    public static void rec(int dim, int bx, int x, int by, int y, int bz, int z) {

        // base case (after everything is printed)
        if (x + 1 == bx) {
            return;
        }

        if (dim == 3) {
            System.out.println(bx + " " + by + " " + bz);
        } else if (dim == 2) {
            System.out.println(bx + " " + by);
        } else {
            System.out.println(bx);
        }

        // if z line is empty
        if (bz == z) {
            // if y line is empty
            if (by == y) {
                // resetting y and z and incrementing x
                // resetting is not needed if the array is 1 dimensional
                rec(dim, ++bx, x, 0, y, 0, z);
                // y line has elements
            } else {
                // resetting z and incrementing y
                // resetting is not needed if the array is 2 dimensional
                rec(dim, bx, x, ++by, y, 0, z);
            }
            //z line has elements
            // only needed for 3 dimensional arrays
        } else {
            rec(dim, bx, x, by, y, ++bz, z);
        }

    }
}