import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
    protected Node head;
    protected Node tail;
    protected int size;

    public BasicDoubleLinkedList() {
        head = tail = null;
        size = 0;
    }

    protected class Node {
        Node next;
        Node prev;
        T data;

        protected Node(T data) {
            next = null;
            prev = null;
            this.data = data;
        }
    }

    protected class BasicDoubleLinkedListListIterator implements ListIterator<T> {
        protected Node current;
        protected Node lastAccessed;
        protected int index;

        BasicDoubleLinkedListListIterator() {
            current = head;
            lastAccessed = null;
            index = 0;
        }

        @Override public boolean hasNext() {
            return index < size;
        }

        @Override public T next() {
            if (hasNext()) {
                lastAccessed = current;
                T temp = current.data;
                current = current.next;
                index++;
                return temp;
            }
            throw new NoSuchElementException();
        }

        @Override public boolean hasPrevious() {
            return index > 0;
        }

        @Override public T previous() {
            if (hasPrevious()) {
                current = lastAccessed;
                T temp = current.data;
                current = current.prev;
                lastAccessed = current;
                index--;
                return temp;
            }
            throw new NoSuchElementException();
        }

        @Override public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override public void set(T t) {
            throw new UnsupportedOperationException();
        }

        @Override public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }

    @Override public ListIterator<T> iterator() {
        return new BasicDoubleLinkedListListIterator();
    }

    protected int getSize() {
        return size;
    }

    protected void addToEnd(T data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            head.prev = null;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        tail.next = null;
        size++;
    }

    protected void addToFront(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    protected T getLast() {
        if (tail != null) {
            return tail.data;
        }
        return null;
    }

    protected T getFirst() {
        if (head != null) {
            return head.data;
        }
        return null;
    }

    protected ArrayList<T> toArrayList() {
        Node dummy = head;
        ArrayList<T> arrayList = new ArrayList<>();
        while (dummy != null) {
            arrayList.add(dummy.data);
            dummy = dummy.next;
        }
        return arrayList;
    }

    protected void remove(T targetData, Comparator<T> comparator) {
        Node dummy = head;
        boolean finished = false;
        while (!finished) {
            if (comparator.compare(dummy.data, targetData) == 0) {
                if (dummy == head) {
                    Node newHead = dummy.next;
                    newHead.prev = null;
                    head = newHead;
                } else if (dummy == tail) {
                    Node newTail = dummy.prev;
                    newTail.next = null;
                    tail = newTail;
                } else {
                    dummy.prev.next = dummy.next;
                    dummy.next.prev = dummy.prev;
                }
                size--;
                finished = true;
            } else {
                dummy = dummy.next;
            }
        }
    }

    protected T retrieveFirstElement() {
        if (head != null) {
            Node toRetrieve = new Node(head.data);
            Node newHead = head.next;
            newHead.prev = null;
            head = newHead;
            return toRetrieve.data;
        }
        return null;
    }

    protected T retrieveLastElement() {
        if (tail != null) {
            Node toRetrieve = new Node(tail.data);
            Node newTail = tail.prev;
            newTail.next = null;
            tail = newTail;
            return toRetrieve.data;
        }
        return null;
    }

}
