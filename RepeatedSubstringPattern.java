/*
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Input: "abab"
Output: True
Input: "aba"
Output: False

solution : used KMP algorithm which store the indices of the prefix that matches with suffix
*/

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int startIndex[] = new int[n];
        int i,j=0;
        for(i = 1; i < n ; i++){
            j = startIndex[i-1];a
            while( j > 0 && s.charAt(i) != s.charAt(j)){
                j = startIndex[j-1];
            }
            if(s.charAt(i) == s.charAt(j)){
                j++;
            }
            startIndex[i] = j;
        }
        return j!=0 && n % (n-j) == 0;
    }
}