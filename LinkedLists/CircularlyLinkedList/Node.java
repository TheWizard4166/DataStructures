package LinkedLists.CircularlyLinkedList;
public class Node 
{
    public Node next;
    public Node prev;
    public Integer data;
    
    public Node(int data)
    {
        this.next = null;
        this.prev = null;
        this.data = data;
    }

    public String toString()
    {
        String s = "";
        s += this.data + "\n ";
        return s;
    }
}
