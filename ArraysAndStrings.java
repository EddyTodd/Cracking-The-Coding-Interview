public class ArraysAndStrings {

    //1.1 Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
    //cannot use additional data structures?

    public static boolean isUnique(String str) {
        int[] set = new int[256];
        for (int i = 0; i < str.length(); i++)
            if (++set[(int) str.charAt(i)] == 2) return false;
        return true;
    }

    //1.2 Check Permutation: Given two strings, write a method to decide if one is a permutation of the
    //other.

    public static boolean checkPermutation(String s1, String s2) {
        boolean[] set = new boolean[256];
        for (int i = 0; i < s1.length(); i++)
            set[s1.charAt(i)] = true;
        for (int i = 0; i < s2.length(); i++)
            if (!set[s2.charAt(i)]) return false;
        return true;
    }

    //URLify: Write a method to replace all spaces in a string with '%20: You may assume that the string
    //has sufficient space at the end to hold the additional characters, and that you are given the "true"
    //length of the string. (Note: If implementing in Java, please use a character array so that you can
    //perform this operation in place.)
    //EXAMPLE
    //Input: "Mr John Smith ", 13
    //Output: "Mr%20John%20Smith"

    //Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
    //A palindrome is a word or phrase that is the same forwards and backwards. A permutation
    //is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
    //EXAMPLE
    //Input: Tact Coa
    //Output: True (permutations: "taco cat". "atco cta". etc.)

    //One Away: There are three types of edits that can be performed on strings: insert a character,
    //remove a character, or replace a character. Given two strings, write a function to check if they are
    //one edit (or zero edits) away.
    //EXAMPLE
    //pale, pIe -> true
    //pales. pale -> true
    //pale. bale -> true
    //pale. bake -> false

    //String Compression: Implement a method to perform basic string compression using the counts
    //of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. If the
    //"compressed" string would not become smaller than the original string, your method should return
    //the original string. You can assume the string has only uppercase and lowercase letters (a - z).

    //1.7 Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
    //bytes, write a method to rotate the image by 90 degrees. (an you do this in place?

    public static void rotateMatrix(char[][] img) {
        int h = img.length / 2;
        int l = (img.length + 1) / 2;
        int s = img.length - 1;
        for (int i = 0; i < l; i++)
            for (int j = 0; j < h; j++) {
                char tmp = img[i][j];
                img[i][j] = img[s - j][i];
                img[s - j][i] = img[s - i][s - j];
                img[s - i][s - j] = img[j][s - i];
                img[j][s - i] = tmp;
            }
    }

    //1.8 Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
    //column are set to O.

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


    //1.9 String Rotation: Assume you have a method isSubst ring which checks if one word is a substring
    //of another. Given two strings, 51 and 52, write code to check if 52 is a rotation of 51 using only one
    //call to isSubstring (e.g., "waterbottle" is a rotation of "erbottlewat").

    public static boolean stringRotation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        String s3 = s1 + s1;
        return s3.contains(s2);
    }
}
