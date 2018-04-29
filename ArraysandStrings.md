# Arrays and Strings


## 1.1 Is Unique
Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?
#### Identify
Search for duplicates in a string.
#### Define goals
Return true if the string has all unique characters. Return false if the string has duplicate characters.
#### Explore
Insert elements into an int array of size 256 which is the amount of characters in the ASCII code. Increment the value of the corresponding index for each character. If the value at a certain index is 1 before incrementing, we have a duplicate.

```java
public static boolean isUnique(String str) {
    int[] set = new int[256];
    for (int i = 0; i < str.length(); i++)
        if (set[(int) str.charAt(i)]++ == 1) return false;
    return true;
}
```
#### Anticipate outcomes
The method should return false when we incremented 2 times the same index which means we have encountered a duplicate. 
#### Look and learn
When input is very basic and finite, we can use an array to represent a hash set. Hash sets are very fast and using this implementation we can check if we have duplicates without traversing the whole string.


## 1.2 Check Permutation
Given two strings, write a method to decide if one is a permutation of the other.
#### Identify
Check if a string is made up from the characters in another string.
#### Define goals
Return true if the second string contains the same characters from the first string. Return false if the second string  contains a character which is not contained in the first string.
#### Explore
Insert elements into a boolean array of size 256 which is the amount of characters in the ASCII code. Make true the value of the corresponding index for each character in the first string. Iterate the second string in the array. If the index you're checking is false, it means it's not contained in the first string.

```java
public static boolean checkPermutation(String s1, String s2) {
    boolean[] set = new boolean[256];
    for (int i = 0; i < s1.length(); i++)
        set[s1.charAt(i)] = true;
    for (int i = 0; i < s2.length(); i++)
        if (!set[s2.charAt(i)]) return false;
    return true;
}
```
#### Anticipate outcomes
The method should return false when we the index we are traversing for the second string is false. This means it is not contained in the first string.
#### Look and learn
This method won't work when the second string **must** contain all characters from the first string. For this we would need to make all values false in the second iteration. If there's one true left at the end, it would return false.


## 1.7 Rotate Matrix
Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
#### Identify
Rotate a matrix 90 degrees
#### Define goals
Swap each cell in the matrix to its corresponding index after rotating 90 degrees. 
#### Explore
For every cell in one quadrant of the matrix, rotate the 4 corresponding cells
```java
public static void rotateMatrix(int[][] img) {
    int h = img.length / 2;
    int l = (img.length + 1) / 2;
    int s = img.length - 1;
    for (int i = 0; i < l; i++)
        for (int j = 0; j < h; j++) {
            int tmp = img[i][j];
            img[i][j] = img[s - j][i];
            img[s - j][i] = img[s - i][s - j];
            img[s - i][s - j] = img[j][s - i];
            img[j][s - i] = tmp;
        }
}
```
#### Anticipate outcomes
If the matrix is not divisible by 3 we needed to make some math to get the right number of squares to change. 
#### Look and learn
Making use of known input constraints (such as symmetry) can make programs more efficient.


## 1.8 Zero Matrix
Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to O.
#### Identify
We need to look for 0 and change rows and columns to 0 if we find one.
#### Define goals
Set all rows and columns in a matrix to 0 if at least one element in the row or column is 0.
#### Explore
Create a boolean array where we will store the column index of those columns which contain a 0 and one for the rows as well. If we find a 0 in (x, y), we set x to true in the column array and y in the row array. For every true in each array convert all corresponding elements to 0.
```java
public static void zeroMatrix(int[][] X) {
    boolean[] R = new boolean[X.length];
    boolean[] C = new boolean[X[0].length];
    for (int i = 0; i < X.length; i++)
        for (int j = 0; j < X[0].length; j++)
            if (X[i][j] == 0) {
                R[i] = true;
                C[j] = true;
            }
    for (int i = 0; i < R.length; i++)
        if (R[i])
            for (int j = 0; j < C.length; j++)
                X[i][j] = 0;
    for (int i = 0; i < C.length; i++)
        if (C[i])
            for (int j = 0; j < R.length; j++)
                X[j][i] = 0;
}
```
#### Anticipate outcomes
If more than half the matrix is a 0 at the beginning, it will replace a lot of cells to 0 multiple times which won't be efficient. If this is the case it would be better to check if any index is true for the column array or row array.
#### Look and learn
Depending on the input, some algorithms are faster than others performing the same duty.

## 1.9 String Rotation
Assume you have a method isSubst ring which checks if one word is a substring
of another. Given two strings, 51 and 52, write code to check if 52 is a rotation of 51 using only one call to isSubstring (e.g., "waterbottle" is a rotation of"erbottlewat").
#### Identify
We need to see if the second string is a rotation of the first one.
#### Define goals
Check if a 2 substrings in the second string are contained in the first string in reverse order.
#### Explore
Concatenate the first string with itself and check if the second strung is contained in the new string. This way we guarantee all possible rotations must be contained in the created string.
```java
public static boolean stringRotation(String s1, String s2) {
    if (s1.length() != s2.length()) return false;
    String s3 = s1 + s1;
    return s3.contains(s2);
}
```
#### Anticipate outcomes
We can also check if the lengths are not the same it cant be a rotation. Also, if the strings are very large, duplicating can waste a lot of resources and in extreme cases might even overflow.
#### Look and learn
It is better to waste a little space to make algorithms faster and more readable.