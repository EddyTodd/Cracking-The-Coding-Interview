import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class StacksAndQueues {

    //3.1 Three in One: Describe how you could use a single array to implement three stacks.
    //Stack1 = k * 3
    //Stack2 = k * 3 + 1
    //Stack3 = k * 3 + 2

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


    //3.2 Stack Min: How would you design a stack which, in addition to push and pop, has a function min
    //which returns the minimum element? Push, pop and min should all operate in 0(1) time.
    //for each element in the stack have a min variable which contains the minimum value of the min
    //value of element below or its own value

    public class StackMin extends Stack<Integer> {
        int min;

        public void push(int value) {
            if (super.isEmpty()) min = value;
            else min = (int) Math.min(super.peek(), value);
            super.push(value);
        }
    }

    //3.3 Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
    //Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
    //threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
    //composed of several stacks and should create a new stack once the previous one exceeds capacity.
    //SetOfStacks.push () and SetOfStacks. pop() should behave identically to a single stack
    //(that is, pop () should return the same values as it would if there were just a single stack).
    //FOLLOW UP
    //Implement a function popAt (int index) which performs a pop operation on a specific sub-stack.

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
            if (last == 0) throw new EmptyStackException();
            int r = arr.get(last).pop();
            if (size == 0) {
                size = threshold;
                arr.remove(last--);
            }
            return r;
        }
    }


    //3.4 Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.

    public class MyQueue<Object> {
        Stack<Object> s1;
        Stack<Object> s2;

        public MyQueue() {
            s1 = new Stack<Object>();
            s2 = new Stack<Object>();
        }

        void queue(Object o) {
            while (!s2.isEmpty())
                s1.push(s2.pop());

            s1.push(o);

            while (!s1.isEmpty())
                s2.push(s1.pop());
        }

        Object dequeue() {
            return s2.pop();
        }

        Object peek() {
            return s2.peek();
        }
    }

    //3.5 Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use
    //an additional temporary stack, but you may not copy the elements into any other data structure
    //(such as an array). The stack supports the following operations: push, pop, peek, and isEmpty.

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

    //3.6 Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first
    //out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
    //or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of
    //that type). They cannot select which specific animal they would like. Create the data structures to
    //maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog,
    //and dequeueCat. You may use the built-in Linked List data structure.

}
