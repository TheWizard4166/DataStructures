package LinkedLists.DoublyLinkedList;
public class Node {
    public Node next;
    public Node prev;
    public int data;
    
    public Node(int data)
    {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
    public String toString()
    {
        String s = "" + this.data;
        if(this.next != null)
            s += ", " + this.next;
        return s;
    }
}
