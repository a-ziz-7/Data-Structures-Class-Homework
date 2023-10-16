import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class C12_BinarySearchTrees {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(
                "C:\\Users\\azabd\\Java Pro\\A CISC 3130 Data Structures\\HW4\\src\\input.txt"));
        ArrayList<BinaryTree> dataSet = new ArrayList<>();
        dataSet.add(new BinaryTree());
        readData(sc, dataSet, 0);
        preorderWalk(dataSet.get(0).root);
        inorderWalk(dataSet.get(0).root);
        postorderWalk(dataSet.get(0).root);
        search(dataSet.get(0).root, 20);
        Node m = min(dataSet.get(0).root);
        System.out.println(m.value);
        System.out.println(successor(dataSet.get(0).root).value);
    }

    public static void preorderWalk(Node n) {
        if (n != null) {
            System.out.println(n.value);
            preorderWalk(n.left);
            preorderWalk(n.right);
        }
    }

    public static void inorderWalk(Node n) {
        if (n != null) {
            inorderWalk(n.left);
            System.out.println(n.value);
            inorderWalk(n.right);
        }
    }

    public static void postorderWalk(Node n) {
        if (n != null) {
            postorderWalk(n.left);
            postorderWalk(n.right);
            System.out.println(n.value);
        }
    }

    public static void search(Node n, int s) {
        if (n == null || n.value == s) {
            System.out.println("Found! " + (n != null ? n.value : "noin"));
        } else if (n.value > s) {
            search(n.left, s);
        } else {
            search(n.right, s);
        }
    }

    public static Node min(Node n) {
        if (n.left != null) {
            return min(n.left);
        }
        return n;
    }

    public static Node successor(Node n) {
        if (n.right != null) {
            return min(n.right);
        } else {
            return n;
        }
    }

    public static void readData(Scanner sc, ArrayList<BinaryTree> dataSet, int pointer) {
        while (sc.hasNext()) {
            int data = sc.nextInt();
            if (data != -999) {
                dataSet.get(pointer).add(data);
            } else {
                pointer++;
                dataSet.add(new BinaryTree());
            }
        }
    }

}
