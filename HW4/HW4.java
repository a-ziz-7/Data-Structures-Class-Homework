import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HW4 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(
                "C:\\Users\\azabd\\Java Pro\\A CISC 3130 Data Structures\\HW4\\src\\input.txt"));
        ArrayList<BinaryTree> dataSet = new ArrayList<>();
        ArrayList<BinaryTree> dataSetBigTrees = new ArrayList<>(2);
        dataSet.add(new BinaryTree());
        int pointer = readData(sc, dataSet, 0);
        printData(dataSet, pointer, false);
        dataSetBigTrees.add(createBigTree(dataSet, 0));
        dataSetBigTrees.add(createBigTree(dataSet, 3));
        printData(dataSetBigTrees, 2, true);

    }

    public static int readData(Scanner sc, ArrayList<BinaryTree> dataSet, int pointer){
        while (sc.hasNext()) {
            int data = sc.nextInt();
            if (data != -999) {
                dataSet.get(pointer).add(data);
            } else {
                pointer++;
                dataSet.add(new BinaryTree());
            }
        }
        return pointer;
    }

    public static void printData(ArrayList<BinaryTree> dataSet, int stop, boolean bigTree) {
        System.out.println((!bigTree ? "Original trees:" : "Big trees:") + "\n");
        for (int i = 0; i < stop; i++) {
            BinaryTree tempTree = dataSet.get(i);
            System.out.println("Data set " + (i + 1) + ":\npreorder");
            tempTree.preorder(tempTree.root);
            System.out.println("\ninorder");
            tempTree.inorder(tempTree.root);
            System.out.println("\npostorder");
            tempTree.postorder(tempTree.root);
            System.out.println("\nData set " + (i + 1) + " has " +
                    tempTree.count(tempTree.root) + " elements\n");
            if (bigTree) {
                System.out.println(tempTree.countEvenOdd(tempTree.root, true) + " even numbers\n" +
                        tempTree.countEvenOdd(tempTree.root, false) + " odd numbers\n");
            }
        }
    }

    public static BinaryTree createBigTree(ArrayList<BinaryTree> dataSet, int index) {
        BinaryTree bigTree = dataSet.get(index);
        Node firstNodeLeft = bigTree.root;
        while (firstNodeLeft.left != null) {
            firstNodeLeft = firstNodeLeft.left;
        }
        firstNodeLeft.left = dataSet.get(index + 1).root;

        Node firstNodeRight = bigTree.root;
        while (firstNodeRight.right != null) {
            firstNodeRight = firstNodeRight.right;
        }
        firstNodeRight.right = dataSet.get(index + 2).root;
        return bigTree;
    }
}