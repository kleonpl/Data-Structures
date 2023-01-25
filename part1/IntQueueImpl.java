import java.io.PrintStream;
import java.util.NoSuchElementException;

public class IntQueueImpl<T> implements IntQueue<T> {

    private Node<T> head = null;     // points at the oldest element of the queue
    private Node<T> tail = null;     // points at the newest element of the queue
    private int size = 0;            // counter of the total nodes in the queue

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void put(T item) {
        Node<T> n = new Node<>(item);  // create a new Node containing the item 

        if (isEmpty()){
            head = n;                  // if the queue is empty then head pointer is on the new Node, the only one in the list
        } else {
            tail.next = n;             // else put the new npde after the existing tail node
        }
        tail = n;                      // anyway put the tail pointer on the new node 
        size++;                        // increase the nodes counter by 1
    }

    @Override
    public T get() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException(); // if isEmpty, no elements are available to return
        } else {
            T r = head.data;                    // save the data to return in r                 
            head = head.next;                   // head point at the next available node
            size--;                             // a node deleted so decrease node counter by one
            return r;                           // return the data
        }
    }

    @Override
    public T peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException(); // if isEmpty, no elements are available to return
        } else {
            return head.data;                   // return the data from the top node
        }
    }

    @Override
    public void printQueue(PrintStream stream) {
        if (isEmpty()) {
            stream.println("Stack is Empty...");
        } else {
            Node<T> temp = head;
            stream.print("HEAD ");
            while (temp != null) {
                stream.print("->" + temp.data);
                temp = temp.next;
            }
            stream.print(" <-TAIL");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void setData(T data){
        this.head.data = data;
    }
}
