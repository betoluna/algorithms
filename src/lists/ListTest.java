package lists;

import lists.MyLinkedList.Node;

import java.util.Iterator;

/**
 * Created by beto on 7/12/17.
 */
public class ListTest {

    public static void show(MyLinkedList list) {
        Node curr = list.getHead();
        while (curr != null) {
            System.out.print(curr.getValue() + " ");
            curr = curr.getNext();
        }
        System.out.println();
    }

    public static void iteratorShow(MyLinkedList list) {
        Iterator it = list.iterator();
        while(it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }

    //reverse singly linked list. Check for null return
    public static void reverse(MyLinkedList l) {
        //if list is empty or has only one element
        if (l.getHead() == l.getTail()) return;

        Node prev = null, curr = l.getHead(), fwd = curr.getNext();
        while (fwd != null) {
            curr.setNext(prev);
            prev = curr;
            curr = fwd;
            fwd = fwd.getNext();
        }

        curr.setNext(prev);//don't forget
        l.setTail(l.getHead());
        l.setHead(curr);
    }

    public static void main(String[] args) {

        MyLinkedList l = new MyLinkedList();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);

        show(l);
        reverse(l);
        iteratorShow(l);


    }
}
