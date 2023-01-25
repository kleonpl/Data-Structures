public class Node<T> {
    protected T data;                   // generic type data slot, the part where the application's data are saved
    protected Node<T> next = null;      // pointer at the next node in a list, obviously containing a reference to this 

    Node(T data)
    {
        this.data = data;
    }
}
