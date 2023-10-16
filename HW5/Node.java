import java.util.Objects;

public class Node {

    String info;
    Node[] sons;
    int index;

    public Node(String info, int capacity){
        index = 0;
        sons  = new Node[capacity];
        this.info = info;
    }

    public boolean DNATest(Node potentialFather, Node son){
        if (potentialFather.sons[0] == null){
            return false;
        }
        Node node = potentialFather.sons[0];
        while (node != null){
            if (Objects.equals(node, son)){
                return true;
            }
            node = node.sons[1];
        }
        return false;
    }

}
