import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl<T> implements StringStack<T> {

    private Node<T> head = null; // pointer at the top of the stack
    private int size = 0;        // nodes counter 

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void push(T item) {
        Node<T> n = new Node<>(item);

        if (!isEmpty()) {
            n.next = head;     // if stack isnt empty then put the new node on top of the past head node
        }
        head = n;              // in any case make the new node the head node
        size++;                // new node just added so increase the nodes counter by one
    }

    @Override
    public T pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException(); // empty stack, no data to return 
        } else {
            T r = head.data;                    // save the data to return in the r 
            head = head.next;                   // put head pointer on the next available node
            size--;                             // a node just removed so decrease the nodes counter by one
            return r;   
        }
    }

    @Override
    public T peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {    
            return head.data;                   // return the data of the top node
        }
    }

    @Override
    public void printStack(PrintStream stream) {
        if (isEmpty()) {
            stream.println("Stack is Empty...");
        } else {
            Node<T> temp = head;
            stream.print("HEAD-> ");
            while (temp != null) {
                stream.print(temp.data + "->");
                temp = temp.next;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }
}
