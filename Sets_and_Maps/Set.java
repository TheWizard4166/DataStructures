package Sets_and_Maps;
public class Set 
{
    private Object[] arr;
    private int size;
    private int used;
    
    public Set(int size)
    {
        this.arr = new Object[size];
        this.size = size;
        this.used = 0;
    }
    
    public int used()//O(1) returns the used variable
    {
        return this.used;
    }
    
    public int size()//O(1) returns the size variable
    {
        return this.size;
    }
    
    public boolean addAll(Object[] arr)//O(n) adds all elements in an array and returns true or false depending on if all elements were added.
    {
        boolean addedAllVars = true;
        for(int i = 0; i < arr.length; i++)
        {
            boolean addedVar = this.add(arr[i]);
            if(!addedVar)
                addedAllVars = false;
        }
        return addedAllVars;
    }
    
    public boolean add(Object data)//O(n) adds data to the set in the closest null space, if the data is not alread in the set, and the set had not reached capacity.
    {
        if(this.contains(data) || this.used == this.size)
            return false;
        for(int i = 0; i < this.size; i++)
        {
            if(this.arr[i] == null)
            {
                this.arr[i] = data;
                this.used++;
                return true;
            }
        }
        return false;
    }
    
    public Object remove(Object data)//O(n)removes the specified data from the set if it is present, and returns that value
    {
        for(int i = 0; i < this.size; i++)
        {
            if(this.arr[i] != null)
            {
                if(this.arr[i] == data)
                {
                    this.arr[i] = null;
                    this.used--;
                    return data;
                }
            }
        }
        return null;
    }
    
    public Object removeAt(int index)//O(1)Removes the data at the specified index and returns its value
    {
        if(this.arr[index] == null)
            return null;
        
        Object tmp = this.arr[index];
        this.arr[index] = null;
        this.used--;
        return tmp;
    }
    
    public void clear()//O(n) sets the whole array to null
    {
        for(int i = 0; i < this.size; i++)
        {
            this.arr[i] = null;
        }
        this.used = 0;
    }
    
    public boolean isEmpty()//O(1) returns whether or not the set is empty
    {
        if(this.used == 0)
            return true;
        else 
            return false;
    }
    
    public boolean isFull()//O(1) returns whether or not the set is full
    {
        if(this.used == this.size)
            return true;
        else
            return false;
    }
    
    public Object[] toArray()//O(1) returns the set as an array
    {
        return this.arr;
    }
    
    public boolean containsAll(Object[] arr)//O(n) returns whether or not the set contains all the elements in the array passed
    {
        for(Object element : arr)
        {
            if(!this.contains(element))
                return false;
        }
        return true;
    }
    
    public boolean contains(Object data)//O(n) returns whether or not a specified data is in the set
    {
        if(data == null)
            return true;
        for(Object element : this.arr)
        {
            if(element != null)
                if(element == data)
                    return true;
        }
        return false;
    }
    
    public String toString()//O(1) returns the values inside the set
    {
        String s = "[";
        for(int i = 0; i < this.size; i++)
        {
            if(i + 1 != this.size)
                s += this.arr[i] + ", ";
            else
                s += this.arr[i] +"]";
        }
        return s;
    }
    
    
}