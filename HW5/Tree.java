import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Tree {

    Node root;
    Node finding;

    public Tree() {
        root = null;
    }

    public void add(String info, int capacity) {
        if (root == null) {
            root = new Node(info, capacity);
            return;
        }
        Node temp = root;
        while (true) {
            if (temp.sons.length != temp.index) {
                temp.sons[temp.index] = new Node(info, capacity);
                temp.index++;
                return;
            }
            for (int i = 0; i < temp.index; i++) {
                if (countUnder(temp.sons[i]) != 0) {
                    temp = temp.sons[i];
                }
            }
        }
    }

    public void makeBT(@NotNull Tree newT) {
        newT.root = new Node(root.info, 2);
        int x = countAll(root);
        while (x > countAllBT(newT.root)){
              tre(newT.root);
        }
    }

    public int countUnder(Node t) {
        int s = t.sons.length - t.index;
        for (int i = 0; i < t.index; i++) {
            s += countUnder(t.sons[i]);
        }
        return s;
    }

    public int countAll(Node t) {
        int s = t.sons.length;
        for (int i = 0; i < t.index; i++) {
            s += countAll(t.sons[i]);
        }
        return s;
        // return countUnder(root) + 1;
    }

    public int countAllBT(Node t){
        return t != null? 1 + countAllBT(t.sons[0]) + countAllBT(t.sons[1]):0;
    }

    public Node connectSons(Node t, Node current) {
        Node newNode = null;
        Node temp = t;
        for (int i = 0; i < current.index; i++) {
            if (i == 0) {
                temp.sons[0] = new Node(current.sons[0].info, 2);
                temp = temp.sons[0];
                newNode = temp;
            } else {
                temp.sons[1] = new Node(current.sons[i].info, 2);
                temp = temp.sons[1];
            }
        }
        return newNode;
    }

    public void find(Node findMe, Node tr) {
        if (tr == null) {
            return;
        }
        if (Objects.equals(findMe.info, tr.info)) {
            finding = tr;
            return;
        }
        for (int i = 0; i < tr.sons.length; i++) {
            if (Objects.equals(findMe.info, tr.sons[i].info)) {
                finding = tr.sons[i];
                return;
            }
            find(findMe, tr.sons[i]);
        }

    }

    public void tre(Node t) {
        if (t == null) {
            return;
        }
        find(t, root);
        t.sons[0] = connectSons(t, finding);
        tre(t.sons[0]);
        tre(t.sons[1]);
    }

}
