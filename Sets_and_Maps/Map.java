package Sets_and_Maps;
public class Map 
{
    private Object[] valueArr;
    private String[] keyArr;
    private int size;
    private int used;
    
    public Map(int size) 
    {
        this.size = size;
        this.used = 0;
        this.keyArr = new String[this.size];
        this.valueArr = new Object[this.size];
    }
    
    public int size()//O(1) returns the size variable
    {
        return this.size;
    }
    
    public int used()//O(1) returns the used variable
    {
        return this.used;
    }
    
    public boolean put(String key, int value)//O(n) adds a new key value pair to the map. returns true or false depending on if the key value pair was added.
    {
        if(containsKey(key) || isFull())
            return false;
        for(int i = 0; i < this.size; i++)
        {
            if(this.keyArr[i] == null)
            {
                this.keyArr[i] = key;
                this.valueArr[i] = value;
                this.used++;
                return true;
            }
        }
        return false;
    }
    
    public Object remove(String key)//O(n) removes a key value pair depending on if the pair is present in the map. returns the keys value.
    {
        if(!containsKey(key))
            return null;
        int index = indexOf(key);
        Object value = this.valueArr[index];
        this.keyArr[index] = null;
        this.valueArr[index] = null;
        this.used--;
        return value;
    }
    
    public Object replace(String key, Object data)//O(n) replaces a keys value with a new value if the key is present.
    {
        if(!this.containsKey(key))
            return null;
        int index = this.indexOf(key);
        Object tmp = valueArr[index];
        this.valueArr[index] = data;
        return tmp;
    }
    
    public boolean isEmpty()//O(1) returns true or false depending on if the map is empty.
    {
        if(this.used == 0)
            return true;
        return false;
    }
    
    public boolean isFull()//O(1) returns true or false depending on if the map is full.
    {
        if(this.size == this.used)
            return true;
        return false;
    }
    
    public int indexOf(String key)//O(n) returns the index of a specified key
    {
        for(int i = 0; i < this.size; i++)
        {
            if(this.keyArr[i].equals(key))
                return i;
        }
        return -1;
    }
    
    public boolean containsKey(String key)//O(n) returns true or false depending on if the map contains the specified key
    {
        for(String element : keyArr)
        {
            if(element != null)
            {
                if(element.equals(key))
                    return true;
            }
        }
        return false;
    }
    
    public boolean containsValue(Object value)//O(n) returns true or false depending on if the map contains the specified value
    {
        for(Object element : valueArr)
        {
            if(element != null)
            {
                if(element == value)
                    return true;
            }
        }
        return false;
    }
    
    public Set keySet()//O(n) returns a Set view of the keys in the map
    {
        Set keySet = new Set(this.used);
        keySet.addAll(this.keyArr); //this method is O(n) thus the whole method is O(n).
        return keySet;
    }
    
    public Object[] values()//O(1) returns the value array
    {
        return this.valueArr;
    }
    
    public String toString()
    {
        String s = "[";
        for(int i = 0; i < this.size - 1; i++)
        {
            s += this.keyArr[i] +": " + this.valueArr[i] +", ";
        }
        s += this.keyArr[this.size-1] +": " + this.valueArr[this.size-1] +"]";
        return s;
    }
}