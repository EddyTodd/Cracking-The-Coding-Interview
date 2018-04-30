# Stacks and Queues


## 3.1 Three in One
Describe how you could use a single array to implement three stacks. Write code to remove duplicates from an unsorted linked list. How would you solve this problem if a temporary buffer is not allowed? 
#### Identify
Represent 3 stacks with an array
#### Define goals
Have the array split into 3 parts and keep track of the top of each stack without interfering with the others.
#### Explore
Ask for the 3 sizes at the moment of initialization. Create an int variable to keep track of the top of the stack and use them to perform the desired operations for each stack.

```java
public class ThreeInOne {
    int[] nums;
    int top1 = 0, top2, top3, max1, max2, max3;

    ThreeInOne(int size1, int size2, int size3) {
        nums = new int[size1 + size2 + size3];
        top2 = size1;
        top3 = size2;
        int max1 = size1 - 1;
        int max2 = max1 + size2 - 1;
        int max3 = nums.length - 1;
    }

    int pop1() {
        if (top1 == 0) return Integer.MIN_VALUE;
        return nums[top1--];
    }

    int pop2() {
        if (top2 == 0) return Integer.MIN_VALUE;
        return nums[top2--];
    }

    int pop3() {
        if (top3 == 0) return Integer.MIN_VALUE;
        return nums[top3--];
    }

    void push1(int n) {
        if (top1 == max1) System.out.println("Stack 1 full");
        else nums[top1++] = n;
    }

    void push2(int n) {
        if (top2 == max2) System.out.println("Stack 2 full");
        else nums[top2++] = n;
    }

    void push3(int n) {
        if (top3 == max3) System.out.println("Stack 3 full");
        else nums[top3++] = n;
    }

    boolean isEmpty1() {
        return top1 == 0;
    }

    boolean isEmpty2() {
        return top2 == max1+1;
    }

    boolean isEmpty3() {
        return top3 == max2+1;
    }
}
```
#### Anticipate outcomes
This implementation won't work if you want a stack with dynamic size because the array must have an initial size. This limits a lot the size of each stack.
#### Look and learn
It is not wise to have a finite stack when you're not sure of what size you might need later.

## 3.2 Stack Min
How would you design a stack which, in addition to push and pop, has a function min
which returns the minimum element? Push, pop and min should all operate in 0(1) time. For each element in the stack have a min variable which contains the minimum value of the min value of element below or its own value.
#### Identify
Add a min option which returns the minimum element in a stack.
#### Define goals
Keep track of the minimum value below each element inclusive as a property of each element.
#### Explore
Each time you push an element in the stack check if its value is less than the min value from the element underneath. When you pop an element, also pop the min value in the stack.

```java
public class StackMin extends Stack<Integer> {
    Stack<Integer> minStack = new Stack<Integer>();

    public void push(int value) {
        if (super.isEmpty()) minStack.push(value);
        else minStack.push((int) Math.min(super.peek(), value));
        super.push(value);
    }
    
    public Integer pop() {
        minStack.pop();
        return super.pop();
    }
    
    public Integer min(){
    	return minStack.peek();
    }
}
```
#### Anticipate outcomes
There might be a problem when the stack is empty. Since there is no min value below the first push, you cant compare the values. The first mush must be also the first min.
#### Look and learn
Having multiple properties per node in a stack can help improve time complexity.


## 3.3 Stack of Plates
Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real life, we would likely start a new stack when the previous stack exceeds some threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be composed of several stacks and should create a new stack once the previous one exceeds capacity. SetOfStacks.push() and SetOfStacks.pop() should behave identically to a single stack (that is, pop() should return the same values as it would if there were just a single stack).
#### Identify
Implement multiple stacks with a size limit.
#### Define goals
Create multiple stacks and keep track of the size of the stacks. When a stack reaches its maximum height, create a new stack.
#### Explore
Save global variables to keep track of the last stack, the size and the threshold. Create an array list of stacks to store them. Each operation performed, increase or decrease size and index accordingly.
```java
public class SetOfStacks {
    final int threshold;
    private int size, last;

    ArrayList<Stack<Integer>> arr = new ArrayList<Stack<Integer>>();

    SetOfStacks(int t) {
        this.threshold = t - 1;
    }

    void push(int v) {
        if (size == threshold) {
            Stack<Integer> next = new Stack<Integer>();
            arr.add(next);
            next.push(v);
            size = 0;
            last++;
        } else {
            Stack stack = arr.get(last);
            stack.push(v);
            size++;
        }
    }

    int pop() {
        if (last == 0 && size == 0) throw new EmptyStackException();
        int r = arr.get(last).pop();
        if (size == 0) {
            size = threshold;
            arr.remove(last--);
        }
        return r;
    }
}
```
#### Anticipate outcomes
Every element pushed should be placed in the last stack or a new stack if the last one's size is equal to the threshold. Every element popped should be removed from the last stack.
#### Look and learn
Having multiple stacks can help reach an element which was inserted earlier without necessarily popping every element above in in the stack.


## 3.4 Queue via Stacks
Implement a MyQueue class which implements a queue using two stacks.
#### Identify
Use 2 stacks to represent a queue.
#### Define goals
Use the top of a stack to dequeue elements and swap the stack to the other to enqueue. This way we reach the end of the queue without losing the order.
#### Explore
Initialize 2 stacks. Enqueue operation will be performed on stack 1. First we need to pop all elements from stack 2 as we push them to stack 1. This way the end of the queue will be on top of stack 1. To dequeue and peek we pop all elements from stack 1 into stack 2 to get the beginning of the queue on top.
```java
public class MyQueue<Object> {
    Stack<Object> s1;
    Stack<Object> s2;

    public MyQueue() {
        s1 = new Stack<Object>();
        s2 = new Stack<Object>();
    }

    void enqueue(Object o) {
        while (!s2.isEmpty())
            s1.push(s2.pop());
        s1.push(o);
    }
    
    Object dequeue() {
        while (!s1.isEmpty())
            s2.push(s1.pop());
        return s2.pop();
    }

    Object peek() {
        while (!s1.isEmpty())
            s2.push(s1.pop());
        return s2.peek();
    }
}
```
#### Anticipate outcomes
This wont be very efficient if operations are very alternate because each element will be queued or dequeued in O(n) time. If most operations are consecutively the same, operations will be performed in O(1). 
#### Look and learn
Data structures can be used to represent other data structures in other ways that can sometimes be useful but it depends on the problem and the implementation.


## 3.5 Sort Stack
Write a program to sort a stack such that the smallest items are on the top. You can use an additional temporary stack, but you may not copy the elements into any other data structure (such as an array). The stack supports the following operations: push, pop, peek, and isEmpty. 
#### Identify
Sort a stack
#### Define goals
Create another stack where we will be pushing the biggest element each iteration.
#### Explore
Until the original stack is empty, push all elements from it into another stack keeping track of the biggest element. The biggest element will be pushed into another stack. Return the elements from the second stack to the first one and repeat until stack 1 is empty.
```java
public void sortStack(Stack<Integer> s1) {
    Stack<Integer> s2 = new Stack<Integer>();
    Stack<Integer> sorted = new Stack<Integer>();
    while (!s1.isEmpty()) {
        swap(s1, s2, sorted);
        swap(s2, s1, sorted);
    }
    s1 = sorted;
}

private void swap(Stack<Integer> A, Stack<Integer> B, Stack<Integer> S) {
    int tmp = A.pop();
    while (!A.isEmpty()) {
        if (tmp < A.peek()) {
            B.push(tmp);
            tmp = A.pop();
        } else
            B.push(A.pop());
    }
    S.push(tmp);
}
```
#### Anticipate outcomes
If the stack contains an odd number of elements, returning the second stack to the first one might give an empty stack exception.
#### Look and learn
It is not wise to sort a stack using stacks because you can't reach middle elements unless you pop all elements above it.