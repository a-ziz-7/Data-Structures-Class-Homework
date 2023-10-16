import java.util.ArrayList;
import java.util.Objects;

public class LinkedList {

    public ArrayList<Node> data = new ArrayList<>();
    public Wood info;
    public Node head;

    public LinkedList(){

    }

    public void append(Wood info){
        if (head == null){
            head = new Node(info);
            data.add(head);
            return;
        }
        Node current = head;
        while (current.next != null){
            current = current.next;
        }
        current.next = new Node(info);
        data.add(current.next);
    }


    public void freeNode(Wood info){
        if (head == null){
            return;
        }
        if (head.info == info){
            data.remove(head);
            head = head.next;

            return;
        }
        Node current = head;
        while (current.next != null){
            if (Objects.equals(current.next.info, info)) {
                current.next = current.next.next;
                return;
            }
        }
    }
}

class Node{

    public Wood info;
    public Node next;

    public Node(Wood info){
        this.info = info;
    }

}