# Linked Lists


## 2.1 Remove Dups
Write code to remove duplicates from an unsorted linked list.
How would you solve this problem if a temporary buffer is not allowed? 
#### Identify
Remove duplicates from a linked list.
#### Define goals
Search for repeated values and lose the reference to the newly found repeated item.
#### Explore
Traverse the list inserting elements in a hash table. If we find an item that is already contained, we skip the node. We can do this keeping track of the last node we checked.

```java
public static void removeDups(Node n) {
    HashSet<Integer> set = new HashSet<Integer>();
    Node p = n;
    while (n != null) {
        if (set.contains(n.data)) 
            p.next = n.next;
        else {
        	set.add(n.data);
            p = n;
        }
		n = n.next;
	} 
}
```
#### Anticipate outcomes
Linked lists and hash sets have no maximum size so we will never overflow. Since we will traverse the list until we reach null, this also works when the input is null.
#### Look and learn
The order in which you insert elements in a hash set and check if it exists might completely change the behavior of your program.


## 2.2 Return Kth to Last
Implement an algorithm to find the kth to last element of a singly linked list.
#### Identify
Return the node that is k away from the end.
#### Define goals
Check if the kth next element is null
#### Explore
For each node check the next k elements if one is null. If the previous condition is true, k is bigger than the list. If it is false, check if the next element is null meaning we found the kth to last element. Else repeat for the next node.

```java
public static Node kthToLast(Node head, int k) {
    if (head == null) return null;
    Node tmp = head;
    for (int i = 0; i < k; i++) {
        if (tmp.next == null) return null;
        tmp = tmp.next;
    }
    if (tmp.next == null) return head;
    return kthToLast(tmp.next, k);
}
```
#### Anticipate outcomes
The problem with this method is that it is O(n * k). It could be done by reaching the end of the list and counting k elements backwards with O(n + k) running time.
#### Look and learn
Most of the times, when you think a problem can be solved in linear time, it can. Keeping it simple can increase efficiency and readability.


## 2.5 Sum Lists
You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
function that adds the two numbers and returns the sum as a linked list.
#### Identify
Add 2 linked lists which are represented in reverse order
#### Define goals
Add each element with the corresponding element in the other list.
#### Explore
Add the 2 elements and store it as itself mod 10. If the addition is bigger than 10, carry out a one to add to the next addition. When we reach the end of one list, append the rest of the other one still carrying the counter. 
```java
public static Node sumLists(Node n1, Node n2) {
    if (n1 == null && n2 == null) return null;
    Node head = n1;
    int carry = 0;
    while (n1 != null && n2 != null) {
    	int p = n1.data + n2.data
        n1.data = (p + carry) % 10;
        carry = n1.data / 10;
    }
    Node r = h1;
    if (r == null) r = h2;
    while (r != null) {
        r.data = (r.data + carry) % 10;
        carry = r.data / 10;
    }
    return head;
}
```
#### Anticipate outcomes
If the 2 lists do not have the same size, we would need to append other elements at the end of our output list.
#### Look and learn
If you are not using an input list after the operation you can make use of its space to reduce space complexity.


## 2.6 Palindrome
Implement a function to check if a linked list is a palindrome. 
#### Identify
We need to look for 0 and change rows and columns to 0 if we find one.
#### Define goals
Set all rows and columns in a matrix to 0 if at least one element in the row or column is 0.
#### Explore
If we create a static reference to a node, we can check recursively if nodes from last to first are equal to the static node's data and traverse the static list while traversing the other node backwards.
```java
private static Node n2;

public static boolean palindrome(Node n1, Node nt) { 
    n2 = nt;
    return check(n1, n2);
}

public static boolean check(Node n1, Node n2){
	if(n1 == null) return true;
	if(check(n1.next, n2) == false) return false;
    boolean b = (n1.data == n2.data);
    n2 = n2.next;
    return b;
}
```
#### Anticipate outcomes
When a list is bigger than other we would end up checking if an item is equal to null. This will return false so this method also works for different lengths.
#### Look and learn
Having static variables to solve a problem might bring some trouble if we want to use that instance of the variable in another problem unless we reset the variable.


## 2.8 Loop Detection
Given a circular linked list, implement an algorithm that returns the node at the
beginning of the loop. 
#### Identify
Identify the node where the loop begins
#### Define goals
Find a node which points to an earlier node.
#### Explore
if we make a node traverse the list twice as fast as another, they will intersect in a node which is equidistant to the beginning of the loop as the beginning of the list. 
```java
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
```
#### Anticipate outcomes
Since we check when a node that's going twice as fast as another intersect, it won't halt if there's no loop or if the initial value is null.
#### Look and learn
I've seen repeated times that using a node that goes faster or has an initial advantage can help solve a problem in an unexpected way. It is important to know hot to use them.