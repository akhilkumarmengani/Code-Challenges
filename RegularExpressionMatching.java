/*
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where: 

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).


Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
*/


class Solution {
    
    Boolean dp[][];
    
    public boolean isMatchUtil(String s,String p,int sIndex,int pIndex){
        if(dp[pIndex][sIndex]!=null){
            return dp[pIndex][sIndex];
        }
        boolean result = false;
        
        if(pIndex==p.length()){
            if(sIndex==s.length())
                result = true;
        }
        else if(pIndex<p.length()-1 && p.charAt(pIndex+1)=='*'){
            if(sIndex<s.length() && (p.charAt(pIndex)=='.' || p.charAt(pIndex)==s.charAt(sIndex))){
                result = isMatchUtil(s,p,sIndex+1,pIndex) ;
            }
            result = result || isMatchUtil(s,p,sIndex,pIndex+2);
        }
        else if (sIndex<s.length() && (p.charAt(pIndex)=='.' || p.charAt(pIndex)==s.charAt(sIndex))){
            result = isMatchUtil(s,p,sIndex+1,pIndex+1);
        }
        dp[pIndex][sIndex] = result;
        return result;
    }
    
    
    public boolean isMatch(String s, String p) {
        dp = new Boolean[p.length()+1][s.length()+1];
        return isMatchUtil(s,p,0,0);
        
    }
}