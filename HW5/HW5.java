import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class HW5 {

    private static Node father;
    private static int tc = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc1 = new Scanner(new File(
                "C:\\Users\\azabd\\Java Pro\\A CISC 3130 Data Structures\\HW5\\src\\input.txt"));
        Scanner sc2 = new Scanner(new File(
                "C:\\Users\\azabd\\Java Pro\\A CISC 3130 Data Structures\\HW5\\src\\inp2.txt"));
        Tree t1 = r(sc1);
        Tree t2 = r(sc2);
        Tree bt = new Tree();
        Tree bt2 = new Tree();
        t1.makeBT(bt);
        t2.makeBT(bt2);
        bigTraversal(bt, bt.root);
        bigTraversal(bt2, bt2.root);

    }

    public static Tree r(Scanner sc) {
        Tree t = new Tree();
        while (sc.hasNext()) {
            t.add(sc.next(), sc.nextInt());
        }
        return t;
    }

    public static void printAll(Tree bt, Node node) {
        if (bt.root == node) {
            System.out.println("Tree number " + ++tc);
        }
        Node root = bt.root;
        System.out.println("Node: " + node.info);
        ArrayList<Node> sons = getSons(node);
        System.out.println("Sons:");
        printArray(sons);
        getFather(root, node);
        System.out.println("Father: " + father.info);
        sons = getBrothers(root, node);
        System.out.println("Brothers:");
        printArray(sons);
        System.out.print(
                "The oldest brother in the family: " + getOldestBrother(root, node).info + "\n" +
                "The youngest brother in the family: " + getYoungestBrother(root, node).info + "\n" +
                "The oldest son: " + getOldestSon(node).info + "\n" +
                "The youngest son: " + getYoungestSon(node).info + "\n" +
                "Grandfather: " + getGrandfather(root, node).info + "\n");
        sons = getUncles(root, node);
        System.out.println("Uncles:");
        printArray(sons);
        System.out.println("\n--------------------------------" +
                "\n--------------------------------\n");
    }

    public static ArrayList<Node> getSons(Node node) {
        ArrayList<Node> sons = new ArrayList<>();
        if (node.sons[0] == null) {
            return sons;
        }
        node = node.sons[0];
        while (node != null) {
            sons.add(node);
            node = node.sons[1];
        }
        return sons;
    }

    public static void getFather(Node root, Node node) {
        if (root == null) {
            return;
        }
        if (root == node) {
            father = new Node("Does not have a father", 0);
        }
        if (root.DNATest(root, node)) {
            father = root;
            return;
        }
        getFather(root.sons[0], node);
        getFather(root.sons[1], node);
    }

    public static ArrayList<Node> getBrothers(Node root, Node node) {
        if (root == node) {
            ArrayList<Node> temp = new ArrayList<>();
            temp.add(new Node("Does not have brothers", 0));
            return temp;
        }
        getFather(root, node);
        int index = 0;
        ArrayList<Node> temp = getSons(father);
        for (int i = 0; i < temp.size(); i++) {
            if (Objects.equals(temp.get(i), node)) {
                index = i;
                break;
            }
        }
        temp.remove(index);
        return temp;
    }

    public static void printArray(ArrayList<Node> sons) {
        if (sons.size() == 0) {
            System.out.println("Does not have any");
        }
        for (Node son : sons) {
            System.out.println(son.info);
        }
    }

    public static Node getOldestBrother(Node root, Node node) {
        getFather(root, node);
        if (check()) {
            return new Node("Does not have brothers", 0);
        }
        return father.sons[0];
    }

    public static Node getYoungestBrother(Node root, Node node) {
        getFather(root, node);
        if (check()) {
            return new Node("Does not have brothers", 0);
        }
        ArrayList<Node> list = getSons(father);
        return list.get(list.size() - 1);
    }

    public static Node getOldestSon(Node node) {
        if (node.sons[0] != null) {
            return node.sons[0];
        }
        return new Node("Does not have the oldest son", 2);
    }

    public static Node getYoungestSon(Node node) {
        if (node.sons[0] != null) {
            return getSons(node).get(getSons(node).size() - 1);
        }
        return new Node("Does not have the youngest son", 2);
    }

    public static Node getGrandfather(Node root, Node node) {
        getFather(root, node);
        if (check() || father == root) {
            return new Node("Does not have a grandfather", 0);
        }
        getFather(root, father);
        return father;
    }

    public static ArrayList<Node> getUncles(Node root, Node node) {
        getFather(root, node);
        if (check() || father == root) {
            ArrayList<Node> temp = new ArrayList<>();
            temp.add(new Node("Does not have uncles", 0));
            return temp;
        }
        return getBrothers(root, father);
    }

    public static void bigTraversal(Tree bt, Node node) {
        if (node == null) {
            return;
        }
        printAll(bt, node);
        bigTraversal(bt, node.sons[0]);
        bigTraversal(bt, node.sons[1]);
    }

    public static boolean check() {
        return Objects.equals(father.info, "Does not have a father");
    }

}