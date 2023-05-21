package Stacks_and_Queues;
public class Stack 
{
    private int[] stack;
    private int size;
    private int used;
    private int topIndex;
    // private int bottomIndex;
    
    public Stack(int size)
    {
        this.size = size;
        this.used = 0;
        this.topIndex = 0;
        // this.bottomIndex = 0;
        this.stack = new int[size];
    }
    
    public boolean push(int data)//O(1) adds data to the top of the stack, and returns true or false depending on if it was added
    {
        if(this.used == this.size)
            return false;
        else if(this.used == 0)
        {
            this.stack[0] = data;
            this.used++;
            return true;
        }
        else
        {
            this.topIndex++;
            this.stack[topIndex] = data;
            this.used++;
            return true;
        }
    }
    
    public int pop()//O(1) removes the data at the top of the stack, and returns the removed value
    {
        if(this.used == 0)
            return -1;
        else
        {
            int tmp = this.stack[topIndex];
            this.stack[topIndex] = -1;
            this.topIndex--;
            this.used--;
            return tmp;
        }
    }
    
    public int peek()//O(1) returns the data at the top of the stack
    {
        if(this.used == 0)
            return -1;
        return this.stack[topIndex];
    }
    
    public boolean empty()//O(1) returns true or false depending on if the stack is empty
    {
        if(this.used == 0)
            return true;
        return false;
    }
    
    public int getSize()//O(1) returns the size of the stack
    {
        return this.size;
    }
    
    public int getUsed()//O(1) returns how full the stack is
    {
        return this.used;
    }
    
    public String toString()
    {
        if(this.used == 0)
            return "empty";
        return "" + this.stack[topIndex];
    }
}