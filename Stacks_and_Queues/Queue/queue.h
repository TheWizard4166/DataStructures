#include <iostream>
#include <vector>
using namespace std;

class queue
{
    private:
    vector<int> theQueue;
    int size = 0;
    int topElement;
    int topIndex = -1;
    int bottomElement;
    int bottomIndex = 0;
    public:
    queue();
    void add(int elemenet);
    void enqueue(int element);
    bool offer(int element);
    string peek();
    string peekLast();
    int poll();
    int getSize();
};