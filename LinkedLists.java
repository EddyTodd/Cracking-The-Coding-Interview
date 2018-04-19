class Node {
    Node next = null;
    int data;
    Node Node = null;

    public Node() {
    }

    public Node(int d) {
        data = d;
    }

    public Node(Node B) {
        Node = B;
    }

    public void append(Node N) {
        Node tmp = this;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = N;
    }
}

public class LinkedLists {

    //2.1 Remove Dups: Write code to remove duplicates from an unsorted linked list.
    //FOLLOW UP
    //How would you solve this problem if a temporary buffer is not allowed?

    public static void removeDups(Node head, int n) {
        if (head == null) return;
        p1_c(head, 0);
    }

    public static int[] p1_c(Node head, int n) {
        if (head.next == null) {
            int[] d = new int[n + 1];
            d[n] = head.data;
            return d;
        }
        int[] d = p1_c(head.next, n + 1);
        d[n] = head.data;
        for (int i : d)
            if (head.data == i)
                head = head.next;
        return d;
    }

    //2.2 Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.

    public static Node kthToLast(Node head, int n) {
        if (head == null) return null;
        Node tmp = head;
        for (int i = 0; i < n; i++) {
            if (tmp.next == null) return null;
            tmp = tmp.next;
        }
        if (tmp.next == null) return head;
        return kthToLast(tmp.next, n);
    }

    //2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
    //the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
    //that node.
    //EXAMPLE
    //Input: the node c from the linked list a - >b- >c - >d - >e- >f
    //Result: nothing is returned, but the new linked list looks like a - >b- >d - >e- >f

    //2.4 Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
    //before all nodes greater than or equal to x. lf x is contained within the list, the values of x only need
    //to be after the elements less than x (see below). The partition element x can appear anywhere in the
    //"right partition"; it does not need to appear between the left and right partitions.
    //EXAMPLE
    //Input: 3 -> 5 -> 8 -> 5 - > 10 -> 2 -> 1 [partition = 5)
    //Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8

    //2.5 Sum Lists: You have two numbers represented by a linked list, where each node contains a single
    //digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
    //function that adds the two numbers and returns the sum as a linked list.
    //EXAMPLE
    //Input: (7 -> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.
    //Output: 2 -> 1 -> 9. That is, 912.
    //FOLLOW UP
    //Suppose the digits are stored in forward order. Repeat the above problem.
    //EXAMPLE
    //Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
    //Output: 9 -> 1 -> 2. That is, 912.

    public static Node sumLists(Node h1, Node h2) {
        if (h1 == null && h2 == null) return null;
        Node h3 = new Node();
        Node tmp = h3;
        while (h1 != null && h2 != null) {
            tmp.data = h1.data + h2.data;
            h1 = h1.next;
            h2 = h2.next;
            if (h1 != null && h2 != null)
                tmp.next = new Node();
        }
        Node rem = h1;
        if (rem == null) rem = h2;
        while (rem != null) {
            tmp.next = new Node(rem.data);
            tmp = tmp.next;
        }
        return h3;
    }

    //2.6 Palindrome: Implement a function to check if a linked list is a palindrome.
    //Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting
    //node. Note that the intersection is defined based on reference, not value. That is, if the kth
    //node of the first linked list is the exact same node (by reference) as the jth node of the second
    //linked list, then they are intersecting.
    public static boolean palindrome(Node n1, Node n2) {
        while (n1.next != null)
            n1 = n1.next;
        while (n2.next != null)
            n2 = n2.next;
        return n1 == n2;
    }

    //2.7 Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
    //beginning of the loop.
    //DEFINITION
    //Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
    //as to make a loop in the linked list.
    //EXAMPLE
    //Input: A -) B -) C -) 0 -) E - ) C [the same C as earlier]
    //Output: C

    public static Node loopDetection(Node head) {
        Node S = head.next;
        Node F = head.next.next;
        while (S != F) {
            S = S.next;
            F = F.next.next;
        }
        S = head;
        while (S != F) {
            S = S.next;
            F = F.next;
        }
        return S;
    }
}

