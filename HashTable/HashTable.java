package HashTable;
public class HashTable 
{
    private char[] arr;
    private int capacity;
    private int currentSize;
    
    public HashTable(int capacity)
    {
        this.capacity = capacity;
        this.currentSize = 0;
        this.arr = new char[capacity];
        for(int i = 0; i < arr.length; i++)
        {
            this.arr[i] = '~';
        }
    }
    
    public void clear()//O(n) clears the hashtable and sets all empty values to ~
    {
        this.currentSize = 0;
        for(int i = 0; i < this.capacity; i++)
        {
            arr[i] = '~';
        }
    }
    
    public int getSize()//O(1) returns the current size
    {
        return this.currentSize;
    }
    
    public int getCapacity()//O(1) returns the max capacity
    {
        return this.capacity;
    }
    
    public char getElement(int pos)//O(1) returns the specified element
    {
        return this.arr[pos];
    }
    
    public boolean isEmpty()//O(1) returns whether the hashTable is empty
    {
        return this.currentSize == 0;
    }
    
    public boolean isFull()//O(1) returns whether the hashTable is full
    {
        return this.currentSize == this.capacity;
    }
    
    public int[] getKeys()//O(n) returns the indecies of all the values
    {
        int[] keys = new int[this.currentSize + 1];
        int index = 0;
        for(int i = 0; i < this.capacity; i++)
        {
            if(this.arr[i] != '~')
            {    
                keys[index] = i;
                index++;
            }
        }
        return keys;
    }
    
    public char randomChar(int min, int max)//O(1) returns a random char between two values.
    {
        return (char)((int)(Math.random()*(max - min)));
    }
    
    public boolean insertRand(int min, int max, int quantity)//O(n) fills the hash table with random numbers
    {
        boolean flag = true;
        for(int i = 0; i<quantity; i++)
        {
            if(!insert(randomChar(min,max)))
                flag = false;
        }
        return flag;
    }
    
    public char[] getValues()//O(n) returns an array of values from the hash table
    {
        int[] keys = this.getKeys();
        char[] values = new char[this.currentSize + 1];
        int j = 0;
        for(int index : keys)
        {
            values[j] = this.arr[index];
            j++;
        }
        return values;
    }
    
    public double loadFactor()//O(1) returns the percentage of how full the hashtable is in decimal form.
    {
        return (double)(this.currentSize)/this.capacity;
    }
    
    public int hashCode(char ch)//O(1) returns a hashed index of the passed value
    {
        int hashNum = Math.abs(0x7fffffff * (int)(ch) % this.capacity);
        return hashNum;
    }
    
    public int hashCode2(char ch)//O(1) returns a hashed index of the passed value
    {
        int hashNum = Math.abs((int)(Math.pow((int)(ch), 2)) % this.capacity);
        if(hashNum == 0)
            return hashNum + 1;
        return hashNum;
    }
    
    public void resize()//O(n) doubles the size of the hash table and reinserts all values
    {
        this.capacity *= 2;
        char[] oldArr = this.arr;
        this.currentSize = 0;
        this.arr = new char[this.capacity];
        for(int i = 1; i < this.arr.length; i++)
        {
            this.arr[i] = '~';
        }
        for(char i : oldArr)
        {
            if(i != '~')
                this.insert(i);
        }
    }
    
    public boolean insert(String data)//O(n) Inserts each char from a string into the hashtable
    {
        data = data.replaceAll(" ", "");
        char element;
        boolean allWentThrough = true;
        for(int i = 0; i < data.length(); i++)
        {
            element = data.charAt(i);
            boolean test = this.insert(element);
            if(!test)
                allWentThrough = false;
        }
        return allWentThrough;
    }
    
    public boolean insert(double data)//O(1) inserts a double value as a char if possible
    {
        char element = (char)((int)(data));
        return this.insert(element);
    }
    
    public boolean insert(int data)//O(1) inserts an integer value as a char if possible
    {
        char element = (char)(data);
        return this.insert(element);
    }
    
    public boolean insert(char ch)//O(1) inserts a char into the hashtable, and returns true or false if it is inserted properly
    {
        int hashNum = hashCode(ch);
        
        if(this.search(ch))
            return false;
        
        if(this.loadFactor() >= 0.5)
            this.resize();
        
        if(arr[hashNum] == '~')
        {
            this.arr[hashNum] = ch;
            this.currentSize++;
            return true;
        }
        else if(arr[hashNum] != '~')
        {
            hashNum = hashCode2(ch);
            if(arr[hashNum] != '~')
            {
                for(int i = 1; i < this.capacity - hashNum; i++)
                {
                    if(this.arr[hashNum + i] != '~')
                    {
                        this.arr[hashNum + i] = ch;
                        break;
                    }
                    if(i+1 == this.capacity - hashNum)
                        return false;
                }
                this.currentSize++;
                return true;
            }
            else
            {
                this.arr[hashNum] = ch;
                this.currentSize++;
                return true;
            }
        }
        return false;
    }
    
    public String remove(String element)//O(1)Removes all chars inside the string
    {
        for(int i = 0; i < element.length(); i++)
        {
            this.remove(element.charAt(i));
        }
        return element;
    }
    
    public char remove(char element)//O(1)Removes the char from the hashTable
    {
        int spot = hashCode(element);
        int spot2 = hashCode2(element);
        
        if(!search(element))
            return '~';
        else
        {
            //hashCode
            if(this.arr[spot] == element)
            {
                this.arr[spot] = '~';
                this.currentSize--;
                return element;
            }
            else if(this.arr[spot2] == element)
            {
                this.arr[spot2] = '~';
                this.currentSize--;
                return element;
            }
            else{
                for(int i = 0; i < this.capacity; i++)
                {
                    if(this.arr[i] == element)
                    {
                        this.arr[i] = '~';
                        this.currentSize--;
                        return element;
                    }
                }
            }
            return '~';
        }
        
    }
    
    public boolean search(char data)//O(1) returns true or false whether or not the data is present
    {
        int hashNum = this.hashCode(data);
        if(this.arr[hashNum] == data)
            return true;
        
        hashNum = this.hashCode2(data);
        if(hashNum == 0)
            hashNum++;
        if(this.arr[hashNum] == data)
            return true;
        
        for(int i = hashNum; i < this.capacity; i++)
        {
            if(this.arr[i] == data)
                return true;
        }
        return false;
    }
    
    public String toString()//O(n) replaces the memory address with all the values in the hash table
    {
        String s = "";
        for(char i : arr)
        {
            s += i;
        }
        return s;
    }
}