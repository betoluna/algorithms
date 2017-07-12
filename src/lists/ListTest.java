package lists;

import java.util.Iterator;

/**
 * Created by beto on 7/12/17.
 */
public class ListTest {
    public static void main(String[] args) {
        MyLinkedList l = new MyLinkedList();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);

        Iterator it = l.iterator();


        System.out.println(l.size());
    }
}
