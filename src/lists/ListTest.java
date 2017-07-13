package lists;

import lists.MyLinkedList.Node;
import java.util.Iterator;

/**
 * Created by beto on 7/12/17.
 */
public class ListTest {
//
//    public static void print(Node head) {
//        if(head != null) {
//            Node curr = head;
//            while(curr != null) {
//                System.out.println(curr.getValue());
//                curr = curr.getNext();
//            }
//        }
//    }

    public static void print(MyLinkedList list) {
        Node curr = list.getHead();
        if(curr != null) {
            while(curr != null) {
                System.out.println(curr.getValue());
                curr = curr.getNext();
            }
        }
    }

    public static void main(String[] args) {
        MyLinkedList l = new MyLinkedList();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);

        Iterator it = l.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }

        l.clear();
        System.out.println("list cleared. " + " Size is: " + l.size());
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        print(l);
    }
}
