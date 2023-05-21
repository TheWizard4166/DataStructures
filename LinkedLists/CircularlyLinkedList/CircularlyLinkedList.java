package LinkedLists.CircularlyLinkedList;
public class CircularlyLinkedList 
{
    private Node head;
    private Node tail;
    private int currentSize;
    
    public CircularlyLinkedList()
    {
        this.head = null;
        this.tail = null;
        this.currentSize = 0;
    }
    
    public Node createNode(int data)//O(1) returns a new node
    {
        Node tmp = new Node(data);
        return tmp;
    }
    
    public boolean contains(int data)//O(n) returns whether or not the list contains a node
    {
        Node tmp = this.head;
        if(tmp == null)
            return false;
        else
        {
            while(tmp.next != head)
            {
                if(tmp.data == data)
                    return true;
                else
                    tmp = tmp.next;
            }
        }
        return false;
    }
    
    public Integer indexOf(int s)//O(n) finds the index of the specfied element
    {
        int count = 0;
        Node tmp = this.head;
        if(tmp.data == s)
            return 0;
        tmp = tmp.next;
        count++;
        while(tmp.next != this.head)
        {
            if(tmp.data == s)
                return count;
            tmp = tmp.next;
            count++;
        }
        if(tmp.data == s)
            return count;
        else
            return null;
    }
    
    //Peek Methods
    
    public Integer peekFirst()//O(1) returns the first node in the list
    {
        return this.head.data;
    }
    
    public Integer peekLast()//O(1) returns the last node in the list
    {
        return this.tail.data;
    }
    
    public Integer peek(int index)//O(n) returns the node at the specfied index
    {
        if(index < 0)
            return null;
        Node tmp = this.head;
        for(int i = 0; i < index; i++)
        {
            tmp = tmp.next;
        }
        return tmp.data;
    }
    
    //Removal Methods
    public Integer removeAt(int index)//O(n) removes the node at the specified index
    {
        if(index > this.currentSize)
            return null;
        Node tmp = this.head;
        for(int i = 0; i < index - 1; i++)
        {
            tmp = tmp.next;
        }
        Node retTmp = tmp.next;
        tmp.next.next.prev = tmp;
        tmp.next = tmp.next.next;
        return retTmp.data;
    }
    
    
    public Integer removeFirst()//O(1) removes the first node in the list
    {
        Node tmp;
        if(this.head == null)
            return null;
        else if(this.head.next == this.head)
        {    
            tmp = this.head;
            this.head = null;
            this.tail = null;
        }
        else
        {
            tmp = this.head;
            this.head.next.prev = this.tail;
            this.tail.next = this.head.next;
            this.head = this.head.next;
        }
        this.currentSize--;
        return tmp.data;
    }
    
    public void removeAllNodes()//O(1) Removes all nodes in the list
    {
        this.head = null;
        this.tail = null;
        System.gc();
        this.currentSize = 0;
    }
    
    public Integer removeLast()//O(1) Removes all nodes in the list
    {
        Node tmp = this.tail;
        if(this.tail.next == this.tail)
            return removeFirst();
        else
        {
            tmp = this.tail;
            this.tail.prev.next = this.tail.next;
            this.head.prev = this.tail.prev;
            this.tail = this.tail.prev;
        }
        this.currentSize--;
        return tmp.data;
    }
    
    //Adding to List Methods
    
    public void addAll(int[] dataList)//O(n) adds all the nodes in an array to a list
    {
        for(int element : dataList)
        {
            this.addLast(element);
        }
    }
    
    public void addFirst(int data)//O(1) adds the node to the beginning of the list
    {
        Node tmp = createNode(data);
        if(this.head == null)
        {
            this.head = tmp;
            this.head.next = this.head;
            this.head.prev = this.head;
            this.currentSize++;
        }  
        else if(this.tail == null)
        {
            this.tail = this.head;
            this.tail.next = tmp;
            this.tail.prev = tmp;
            tmp.next = this.tail;
            tmp.prev = this.tail;
            this.head = tmp;
            this.currentSize++;
        }
        else
        {
            tmp.next = this.head;
            tmp.prev = this.tail;
            this.head.prev = tmp;
            this.tail.next = tmp;
            this.head = tmp;
            this.currentSize++;
        }
    }
    
    public void addLast(int data)//O(1) adds a node to the end of the list
    {
        Node tmp = createNode(data);
        if(this.head == null)
            addFirst(data);
        else if(this.tail == null)
        {
            tmp.next = this.head;
            tmp.prev = this.head;
            this.head.next = tmp;
            this.head.prev = tmp;
            this.tail = tmp;
            this.currentSize++;
        }
        else
        {
            tmp.next = this.head;
            tmp.prev = this.tail;
            this.tail.next = tmp;
            this.head.prev = tmp;
            this.tail = tmp;
            this.currentSize++;
        }
    }
    
    //Rotations
    
    public int[] toArray()//O(n) converts the list into an array
    {
        Node tmp = this.head;
        int[] arr = new int[this.currentSize];
        int count = 0;
        while(tmp.next != this.head)
        {
            arr[count] = tmp.data;
            count++;
            tmp = tmp.next;
        }
        return arr;
    }
    
    public void rotateRight()//O(1) moves the head and tail over one node to the right
    {
        this.tail = this.tail.prev;
        this.head = this.head.prev;
    }
    
    public void rotateLeft()//O(1) moves the head and tail over one node to the left.
    {
        this.tail = this.tail.next;
        this.head = this.head.next;
    }
    
    public String toString()
    {
        Node tmp = this.head;
        if(this.currentSize == 0)
        {
            return "Empty";
        }
        String s = "[";
        if(tmp.data == null)
            return "Empty";
        s += tmp.data + ", ";
        tmp = tmp.next;
        while(tmp.next != this.head.prev)
        {
            s += tmp.data + ", ";
            tmp = tmp.next;
        }
        s += tmp.next.data + "]";
        return s;
    }
}