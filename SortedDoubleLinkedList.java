import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
    private final Comparator<T> comparator2;

    public SortedDoubleLinkedList(Comparator<T> comparator) {
        super();
        comparator2 = comparator;
    }

    protected void add(T data) {
        Node toAdd = new Node(data);
        size++;

        if (head == null) {
            tail = toAdd;
            head = toAdd;
            head.prev = null;
            return;
        }

        // if value to be added is less than head
        if (comparator2.compare(data, head.data) < 0) {
            toAdd.prev = null;
            head.prev = toAdd;
            toAdd.next = head;
            head = toAdd;
            return;
        }

        // if value to be added is greater than tail
        if (comparator2.compare(data, tail.data) > 0) {
            tail.next = toAdd;
            toAdd.prev = tail;
            toAdd.next = null;
            tail = toAdd;
            return;
        }

        // else, find the location to insert the node
        Node temp = head.next;
        while (comparator2.compare(temp.data, data) < 0) {
            temp = temp.next;
        }

        (temp.prev).next = toAdd;
        toAdd.prev = temp.prev;
        temp.prev = toAdd;
        toAdd.next = temp;
    }

    @Override
    protected void addToEnd(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    @Override
    protected void addToFront(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    @Override
    public ListIterator<T> iterator() {
        return super.iterator();
    }

    @Override
    public void remove(T data, Comparator<T> comparator) {
        super.remove(data, comparator);
    }
}
