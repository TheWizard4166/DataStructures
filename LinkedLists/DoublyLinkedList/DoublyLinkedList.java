package LinkedLists.DoublyLinkedList;
public class DoublyLinkedList 
{
    private Node head;
    private Node tail;
    private int size;
    
    public DoublyLinkedList()
    {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public void add(int data)//O(1) adds a node to the list
    {
        if(this.head == null)
        {
            this.head = new Node(data);
            this.tail = this.head;
            this.size++;
        }
        else
        {
            Node tmp = new Node(data);
            this.tail.next = tmp;
            tmp.prev = this.tail;
            this.tail = tmp;
            this.size++;
        }
    }
    
    public void addAll(int[] arr)//O(n) adds all the values of an array to the list
    {
        for(int element : arr)
        {
            this.add(element);
        }
    }
    
    public Node removeFirst()//O(1) removes the first node
    {
        Node tmp = this.head;
        this.head = this.head.next;
        this.head.prev = null;
        this.size--;
        return tmp;
    }
    
    public Node removeLast()//O(1) removes the last node
    {
        Node tmp = this.tail;
        this.tail = this.tail.prev;
        this.tail.next = null;
        this.size--;
        return tmp;
    }
    
    public Node remove(int index)//O(n) removes the node at the specified index
    {
        if(index >= this.size)
            return null;
        Node tmp = this.head;
        
        for(int i = 0; i < index; i++)
        {
            tmp = tmp.next;
        }
        Node retTmp = tmp.next;        
        tmp.next.next.prev = null;
        tmp.next = tmp.next.next;
        this.size--;
        return retTmp;
    }
    
    public Node peekFirst()//O(1) returns the first node in the list
    {
        return this.head;
    }
    
    public Node peekLast()//O(1) returns the last node in the list
    {
        return this.tail;
    }
    
    public Node peek(int index)//O(n) returns the node at the specified index
    {
        Node tmp = this.head;
        for(int i = 0; i < index; i++)
        {
            tmp = tmp.next;
        }
        return tmp;
    }
    
    public void clear()//O(1) clears the entire list
    {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public int[] toArray()//O(1) returns the list as an array
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
    
    public boolean contains(int data)//O(1) returns whether or not the list contains the specified value
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
    
    public String toString()//O(1)
    {
        String s = "[" + this.head + "]";
        return s;
    }
}
