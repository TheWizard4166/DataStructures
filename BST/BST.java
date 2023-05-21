package BST;
public class BST {
    private Node root;
    private int size;
    private int[] arr;
    private int capacity;
    
    public BST(Node root, int c, int[] arr)
    {
        this.root = root;
        this.size = arr.length;
        this.capacity = c;
        this.arr = arr;
    }
    
    public BST(int root, int c)
    {
        this.root = new Node(root);
        this.size = 1;
        this.capacity = c;
        this.arr = new int[this.capacity];
        this.arr[0] = root;
    }
    
    public BST()
    {
        this.root = null;
        this.size = 0;
        this.capacity = 100;
        this.arr = new int[this.capacity];
    }
    
    public int size()//O(1) returns the size of the tree
    {
        return this.size;
    }
    
    public void setSize(int newSize)//O(1) sets the size of the tree
    {
        this.size = newSize;
    }
    
    public Node createNode(int data)//O(1) creates a node
    {
        Node node = new Node(data);
        return node;
    }
    
    public Node createNode(int data, Node parent)//O(1) creates a node with a parent node attached
    {
        Node node = new Node(data);
        node.parent = parent;
        return node;
    }
    
    public Node getRoot()//O(1) returns the root node
    {
        return this.root;
    }
    
    public int getSize()//O(1) returns the tree size
    {
        return this.size;
    }
    
    public void clear(){//O(1) clears the entire tree
        this.root = null;
        this.size = 0;
        this.arr = new int[this.capacity];
    }
    
    public Node first(){//O(1) returns the smallest node
        return this.createNode(this.getMin());
    }
    
    public Node ceiling(Node data){//O(1)returns the leastmost node starting at the node specifed.
        
        if(contains(data))
            return data;
        
        return this.createNode(this.min(data.data));
    }
    
    public boolean isEmpty(){//O(1) returns whether or not the tree is empty
        return size == 0;
    }
    
    public Node last(){//O(1) returns the greatest node in the tree
        return this.createNode(this.getMax());
    }
    
    public Node lower(Node low)//O(1) returns the lowest most node in the tree
    {
        if(!this.contains(low))
            return null;
        
        return createNode(this.max(low.data));
    }
    
    public Integer max(int data)//O(n)Returns the maximum value from all nodes less than the data passed
    {
        int[] elements = this.elementsLower(data);
        if(elements.length < 1)
            return null;
        int max = Integer.MIN_VALUE;
        for(int element: elements){
            if(max < element)
                max = element;
        }
        return max;
    }
    
    public int[] elementsLower(int data)//O(n) returns all elements lower than the specified data.
    {
        int[] lowerThan = new int[this.arr.length];
        int i = 0;
        for(int element : this.arr)
        {
            if(element < data){
                lowerThan[i] = element;
                i++;
            }
        }
        return lowerThan;
    }
    
    public Node floor(Node data){//O(n) returns the floor node
        if(this.contains(data))
            return data;
        int[] lowerThan = elementsLower(data.data);
        int max = Integer.MIN_VALUE;
        if(lowerThan.length == 1)
            return data;
        for(int i : lowerThan)
        {
            if(max < i)
                max = i;
        }
        return createNode(max);
    }
    
    public int[] elementsHigher(int data){//O(n) returns all elements higher than the specified node
        int[] greaterThan = new int[this.getSize()];
        int i = 0;
        for(int element : this.arr){
            if(element > data)
            {    
                greaterThan[i] = element;
                i++;
            }
        }
        int[] newArr = new int[i];
        int j = 0;
        for(int element : greaterThan)
        {
            if(element != 0)
            {
                newArr[j] = element;
                j++;
            }
        }
        return newArr;
    }
    
    public Integer higher(Node root)//O(n) returns the lowest node that is higher than the pased node
    {
        if(!this.contains(root))
            return null;
        return this.min(root.data);
    }
    
    public Integer min(int data){//O(n) returns the minimum value from all the elements higher than the data specified.
        int[] elements = this.elementsHigher(data);
        if(elements.length < 1)
            return null;
        int min = Integer.MAX_VALUE;
        for(int element: elements){
            if(min > element)
                min = element;
        }
        return min;
    }
    public boolean addAll(int[]arr)//O(n) Adds all of the contents from the array into the tree.
    {
        boolean notDup = true; 
        for(int element : arr){
            if(this.size >= capacity)
            {
                notDup = false;
                break;
            }
            if(add(element) == false)
                notDup = false;
            else{
                this.arr[this.size] = element;
            }
        }
        return notDup;
    }
    
    public boolean add(Node root)//O(1) begins the recursive add method to add to the bottom of the tree
    {
        if(this.contains(root))
            return false;
        else if(this.size >= this.capacity)
            return false;
        int[] arr = new int[this.capacity];
        arr = arrNode(root, arr, 0);
        return addAll(arr);
    }
    
    public int[] arrNode(Node root, int[] arr, int size)//O(log(n)) returns an array of all the nodes in the tree.
    {
        arr[size] = root.data;
        size++;
        if(root.left != null)
            arr = arrNode(root.left, arr, size);
        if(root.right != null)
            arr = arrNode(root.right, arr, size);
        return arr;
    }
    
    public boolean add(int data){//O(1) adds to the tree at the lowermost root.
        if(this.size >= this.capacity)
            return false;
        else if(this.root == null){
            this.root = createNode(data, null);
            this.arr[this.size] = data;
            this.size++;
            return true;
        }
        else if(contains(data))
            return false;
        else if(data < this.root.data && this.root.left == null)
        {
            this.root.left = createNode(data, this.root);
            return true;
        }
        else if(data > this.root.data && this.root.right == null)
        {
            this.root.right = createNode(data, this.root);
            return true;
        }
        else{
            this.arr[this.size] = data;
            this.root = addRecur(this.root, null, data);
            this.size++;
            return true;
        }
    }
    
    public Node addRecur(Node root, Node parent, int data)//O(log(n)) a recursive add method that adds to the lowermost portion of a tree
    {
        if(root == null){
            root = createNode(data, parent);
            return root;
        }
        else if(data <= root.data)
            root.left = addRecur(root.left, parent, data);
        else if(data > root.data)
            root.right = addRecur(root.right, parent, data);
        return root;
    }
    
    
    public boolean contains(Node tmp)//O(1) returns true or false depending on if the data is present in the tree
    {
        if(searchRecur(this.root, tmp.data) == null)
            return false;
        return tmp.data == searchRecur(this.root, tmp.data).data;    
    }
    
    public boolean contains(int data)//O(1) returns true or false depending on if the data is present in the tree.
    {
        if(search(data) != null)
            return true;
        return false;
    }
    
    public Node search(int data)//O(1) a search method that returns the value of the specified data.
    {
        if(searchRecur(this.root, data) == null)
            return null;
        return searchRecur(this.root, data);
    }
    
    public Node searchRecur(Node root, int data)//O(log(n)) a recursive search method that returns the value of the specified data.
    {
        if(root == null || root.data == data)
            return root;
        
        else if(data <= root.data)
            return searchRecur(root.left, data);
        
        return searchRecur(root.right, data);
    }
    
    public Integer getMin()//O(1) returns the minimum value
    {
        if(root == null)
            return null;
        return getMin(this.root);
    }
    
    public int getMin(Node root)//O(log(n)) retuns the minimum value by recurring leftward on the tree.
    {
        if(root.left == null)
            return root.data;
        return getMin(root.left);
    }
    
    public Integer getMax()//O(1) returns the minimum value
    {
        if(root == null)
            return null;
        return getMax(this.root);
    }
    
    public int getMax(Node root)//O(log(n)) returns the maximum value by recurring rightward on the tree
    {
        if(root.right == null)
            return root.data;
        return getMax(root.right);
    } 
    
    public void printInOrder()//O(1) calls the printInOrder method
    {
        printInOrder(this.root);
    }
    
    public void printInOrder(Node root)//O(log(n)) prints the tree in "in order"
    {
        if(root == null)
            return;
        printInOrder(root.left);
        System.out.print(root.data + " ");
        printInOrder(root.right);
    }
    
   public int distFromRoot(int data)//O(1) returns the distance the data is from the root.
   {
       return distanceFromRoot(this.root, data);
   }
   
   public int distanceFromRoot(Node root, int data)//O(log(n)) recurs to determine the distance a node is from the root node.
   {
        int distance = -1;
        if(root == null || this.contains(data) == false)
            return distance;
        else
        {
            if(root.data == data)
                return distance;
            else if(data < root.data)
            {
                distance = distanceFromRoot(root.left, data);
                return distance + 1;
            }
            else
            {
                distance = distanceFromRoot(root.right, data);
                return distance + 1;
            }
        }
    
   }
   
    public int distanceInBetween(int data1, int data2)//O(1) returns the distance between two nodes
    {
        return distanceInBetween(this.root, data1, data2);
    }
   
    public int distanceInBetween(Node root, int data1, int data2)//O(log(n)) recurs to determine the distance between two nodes
    {
        if(root == null || !this.contains(data1) || !this.contains(data2))
            return -1;
        else if((data1 < root.data) && (data2 < root.data) || (data1 > root.data) &&  (data2 > root.data))
        {
            int d1 = distanceFromRoot(root, data1);
            int d2 = distanceFromRoot(root, data2);
            if(d1>d2)
                return d2 - (d1 - d2);
            return d1 - (d2 - d1);
        }
        else if((data1 < root.data) && (data2 > root.data) || (data1 > root.data) && (data2 < root.data))
        {
            return distanceFromRoot(root, data1) + distanceFromRoot(root, data2);
        }
        return -1;
    }
    
    public boolean remove(Node root)//O(n) removes the specified node from the tree
    {
        boolean removed = false;
        if(!this.contains(root))
            return false;
        int[] newArr = new int[this.size];
        for(int i = 0; i < this.size; i++)
        {
            if(this.arr[i] != root.data)
                newArr[i] = this.arr[i];
            else
                removed = true;
        }
        
        this.clear();
        this.addAll(newArr);
        return removed;
    }
    
    public Node pollLast()//O(1) removes the last (greatest) node in the tree
    {
        Node tmp = createNode(getMax());
        this.remove(tmp);
        return tmp;
    }
    
    public Node pollFirst()//O(1) removes the first (least) node in the tree
    {
        Node tmp = createNode(getMin());
        this.remove(tmp);
        return tmp;
    }
    
    public BST clone()//O(1) returns a clone of the tree
    {
        BST clone = new BST(this.root, this.capacity, this.arr);
        return clone;
    }
    
    public String toString()
    {
        String s = "";
        if(this.root == null)
            s += "Empty Tree";
        else
            s += this.root;
        return s;
    }
}

class Node {
    public Node left;
    public Node right;
    public Node parent;
    public int data;
    
    public Node(int data)
    {
        this.left = null;
        this.right = null;
        this.parent = null;
        this.data = data;
    }
    
    public String ascendingOrder(){
        String s = "";
        if(this.right != null)
            s += this.right.ascendingOrder();
        s += this.data;
        if(this.left != null)
            s += this.left.ascendingOrder();
        return s;
    }
    
    public String toString()
    {
        String s = "";
        if(this.left != null){
            s += this.left;
            s += ", ";
        }
        s += this.data;
        if(this.right != null){
            s += ", ";
            s += this.right;
        }
        return s;
    }
    
}