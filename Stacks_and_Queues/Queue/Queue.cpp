#include <vector>
#include "queue.h"

using namespace std;

queue::queue()
{
    this->topIndex = -1;
    this->bottomIndex = 0;
    this->size = 0;
}

void queue::enqueue(int element)//O(1) adds an element to the queue, and returns false if queue is filled(this is a vec)
{
    if(this->size == 0)
    {
        this->bottomElement = element;
    }
    this->theQueue.push_back(element);
    this->topElement = element;
    this->topIndex += 1;
    this->size++;
}

void queue::add(int element)//O(1)
{
    this->theQueue.push_back(element);
    if(this->size == 0)
    {
        this->bottomElement = element;
    }
    this->topElement = element;
    this->topIndex += 1;
    this->size++;
}

bool queue::offer(int element)//O(1)
{
    this->theQueue.push_back(element);
    if(this->size == 0)
    {
        this->bottomElement = element;
    }
    this->topElement = element;
    this->topIndex += 1;
    this->size++;
    return true;
}


string queue::peek()//O(1)
{
    if(this->size == 0)
        return "Empty";
    return std::to_string(this->bottomElement);
}

string queue::peekLast()//O(1)
{
    if(this->size == 0)
        return "Empty";
    return std::to_string(this->topElement);
}

int queue::getSize()//O(1)
{
    return this->size;
}

int queue::poll()//O(1)
{
    if(this->size == 0)
        return -1;        
    else if(this->size == 1)
    {
        int oldBotElement = this->bottomElement;
        this->theQueue = vector<int>();
        this->size--;
        this->topIndex--;
        return oldBotElement;
    }
    int oldBotElement = this->bottomElement;
    theQueue.erase(theQueue.begin());
    this->bottomElement = theQueue.at(0); 
    this->topIndex--;
    this->size--;
    return oldBotElement;
}