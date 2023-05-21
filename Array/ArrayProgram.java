import java.util.Arrays;
public class ArrayProgram
{
    public static void main(String[] args)
    {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};//O(1) intializes the Array
        int length = arr.length;//O(1) returns the length of an array
        int index5 = arr[5];//O(1) returns a value in the array
        System.out.println(index5);
        printArr(arr);
        System.out.println(indexOf(arr, 7));
        for(int i = 0; i < length; i++)//O(1) returns 
        {
            arr[i] = (int)(Math.random()*(50)+1);
        }
        Arrays.sort(arr);
        printArr(arr);
        System.out.println(contains(arr, 23));
        int[] newArr = resize(arr);
        for(int i = arr.length; i < newArr.length; i++)
        {
            newArr[i] = (int)(Math.random()*(50)+1);
        }
        printArr(newArr);
    }
    
    public static void printArr(int[] array)//O(n) Prints the contents of an array
    {
        System.out.print("[");
        for(int element : array)
        {
            System.out.print(element + ", ");
        }
        System.out.println("]");
    }
    
    public static boolean contains(int[] arr, int data)//O(n) returns true or false depending on if the array contains a particular piece of data
    {
        for(int element : arr)
        {
            if(element == data)
                return true;
        }
        return false;
    }
    
    public static int indexOf(int[] arr, int data)//O(n) returns the index of the specified data
    {
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] == data)
                return i;
        }
        return -1;
    }
    
    public static int[] resize(int[] arr)//O(n) returns the array with double the size
    {
        int[] newArr = new int[arr.length * 2];
        for(int i = 0; i < arr.length; i++)
        {
            newArr[i] = arr[i];
        }
        return newArr;
    }
}
