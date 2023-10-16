public class BinaryTree {

    Node root;

    public BinaryTree() {
        root = null;
    }

    public void add(int x) {
        if (root == null) {
            root = new Node(x);
            return;
        }
        Node temp = root;
        Node parent;
        while (true) {
            parent = temp;
            if (temp.value >= x) {
                temp = temp.left;
                if (temp == null) {
                    parent.left = new Node(x);
                    return;
                }
            } else {
                temp = temp.right;
                if (temp == null) {
                    parent.right = new Node(x);
                    return;
                }
            }
        }
    }

    public void preorder(Node t) {
        if (t == null) {
            return;
        }
        System.out.print(t.value + " ");
        preorder(t.left);
        preorder(t.right);
    }

    public void inorder(Node t) {
        if (t == null) {
            return;
        }
        inorder(t.left);
        System.out.print(t.value + " ");
        inorder(t.right);
    }

    public void postorder(Node t) {
        if (t == null) {
            return;
        }
        postorder(t.left);
        postorder(t.right);
        System.out.print(t.value + " ");
    }

    public int count(Node t) {
        if (t == null) {
            return 0;
        }
        return 1 + count(t.left) + count(t.right);
    }

    public int countEvenOdd(Node t, boolean b) {
        if (t == null) {
            return 0;
        }

        if (b){

            return t.value % 2 == 0?
                    1 + countEvenOdd(t.left, true) + countEvenOdd(t.right, true):
                    countEvenOdd(t.left, true) + countEvenOdd(t.right, true);

//            if (t.value % 2 == 0){
//                return 1 + countEvenOdd(t.left, true) + countEvenOdd(t.right, true);
//            } else{
//                return countEvenOdd(t.left, true) + countEvenOdd(t.right, true);
//            }
        }else {
            return t.value % 2 == 1?
                    1 + countEvenOdd(t.left, true) + countEvenOdd(t.right, true):
                    countEvenOdd(t.left, true) + countEvenOdd(t.right, true);
//            if (t.value % 2 == 1){
//                return 1 + countEvenOdd(t.left, false) + countEvenOdd(t.right, false);
//            } else{
//                return countEvenOdd(t.left, false) + countEvenOdd(t.right, false);
//            }
        }
    }
    
}
