package LinkedLists.SinglyLinkedList;
public class SinglyLinkedList 
{
    private Node head;
    private Node tail;
    private int size;

    public SinglyLinkedList()
    {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size()
    {
        return this.size;
    }

    public Node peekFirst()//O(1) displays the first node
    {
        return this.head;
    }

    public Node peekLast()//O(1) displays the last node
    {
        return this.tail;
    }

    public Node peek(int index)//O(n) displays the node at an index
    {
        Node tmp = this.head;
        for(int i = 0; i < index; i++)
        {
            tmp = tmp.next;
        }
        return tmp;
    }

    public int[] toArray()//O(n) converts the linkedlist into an array
    {
        int[] arr = new int[this.size];
        Node tmp = this.head;
        for(int i = 0; i < this.size; i++)
        {
            arr[i] = tmp.data;
            tmp = tmp.next;
        }
        return arr;
    }

    public void add(int data)//O(1) adds another link to the list
    {
        if(this.head == null)
        {
            this.head = new Node(data);
            this.tail = this.head;
            this.size++;
        }
        else
        {
            Node node = new Node(data);
            this.tail.next = node;
            this.tail = this.tail.next;
            this.size++;
        }
    }
    
    public void addAll(int[] arr)//O(n) adds all of the elements from an array to a linked list
    {
        for(int element : arr)
        {
            this.add(element);
        }
    }

    public Node removeLast()//O(n) removes the last element in the list
    {
        Node tmp = this.head;
        while(tmp.next != this.tail)
        {
            tmp = tmp.next;
        }
        this.tail = tmp;
        this.tail.next = null;
        this.size--;
        return tmp;
    }

    public Node removeFirst()//O(1) removes the first node in the list
    {
        Node tmp = this.head;
        this.head = this.head.next;
        this.size--;
        return tmp;
    }

    public Node removeAt(int index)//O(n) removes the node at the specified index
    {
        if(index > this.size)
            return null;
        else if(index == this.size - 1)
            return removeLast();
        else if(index == 0)
            return removeFirst();
        Node tmp = this.head;
        for(int i = 0; i < index - 1; i++)
        {
            tmp = tmp.next;
        }
        Node retTmp = tmp.next;
        tmp.next = tmp.next.next;
        this.size--;
        return retTmp;
    }
    
    public void clear()//O(1) deletes the entire linked list
    {
        this.head = null;
        this.tail = null;
    }
    
    public boolean contains(int data)//O(n) checks if the linkedlist has a specified node
    {
        Node tmp = this.head;
        while(tmp.next != null)
        {
            if(tmp.data == data)
                return true;
            tmp = tmp.next;
        }
        return false;
    }
    
    public String toString()//O(1) a standard tostring method
    {
        String s = "[" + this.head + "]";
        return s;
    }
}