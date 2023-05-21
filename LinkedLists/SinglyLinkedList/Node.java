package LinkedLists.SinglyLinkedList;
public class Node 
{
    public int data;
    public Node next;

    public Node(int data)
    {
        this.data = data;
        this.next = null;
    }

    public String toString()
    {
        String s = "";
        if(this.next == null)
            s += this.data;
        else
        {
            s += this.data + ", ";
            s += this.next;
        }
        return s;
    }
}