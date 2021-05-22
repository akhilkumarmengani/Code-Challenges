/*
We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)". Then, we removed all commas, decimal points, and spaces and ended up with the string s.

For example, "(1, 3)" becomes s = "(13)" and "(2, 0.5)" becomes s = "(205)".
Return a list of strings representing all possibilities for what our original coordinates could have been.

Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with fewer digits. Also, a decimal point within a number never occurs without at least one digit occurring before it, so we never started with numbers like ".1".

The final answer list can be returned in any order. All coordinates in the final answer have exactly one space between them (occurring after the comma.)

Input: s = "(123)"
Output: ["(1, 2.3)","(1, 23)","(1.2, 3)","(12, 3)"]

Input: s = "(0123)"
Output: ["(0, 1.23)","(0, 12.3)","(0, 123)","(0.1, 2.3)","(0.1, 23)","(0.12, 3)"]
Explanation: 0.0, 00, 0001 or 00.01 are not allowed.

Input: s = "(00011)"
Output: ["(0, 0.011)","(0.001, 1)"]

Input: s = "(100)"
Output: ["(10, 0)"]
Explanation: 1.0 is not allowed.

*/


class Solution {
    
    List<String> result ;
    public List<String> ambiguousCoordinates(String s) {
        result = new ArrayList<String>();
        ambiguousCoordinatesUtil(s.substring(1,s.length()-1), 1);
   
        return result;
    }
    
    public void ambiguousCoordinatesUtil(String s,int index){
        if(index==s.length()){
            return;
        }
        String left = s.substring(0,index);
        String right = s.substring(index,s.length());
        List<String> leftList = new ArrayList<>();
        List<String> rightList = new ArrayList<>();
        
        if(conditionLeft(left))
            leftList.add(left);
        
        if(conditionLeft(right))
            rightList.add(right);
        
        addToList(left,leftList);
        addToList(right,rightList);
        
        for(String l : leftList){
            for(String r : rightList){
                String str = "("+l+", "+r+")";
                result.add(str);
            } 
        }  
        ambiguousCoordinatesUtil(s,index+1);
    }
    
    public void addToList(String str,List<String> list){
        for(int i = 1 ; i < str.length(); i++){
            String lStr = str.substring(0,i);
            String rStr = str.substring(i,str.length());
            if(conditionLeft(lStr) && conditionRight(rStr)){
                list.add(lStr+"."+rStr);
            }
        }
    }
    
    public boolean conditionLeft(String str){
        int n =str.length();
        if(str.charAt(0)=='0' && n > 1 ){
            return false;
        }
        return true;
    }
    
    public boolean conditionRight(String str){
        int n =str.length();
        if(n==1 && str.charAt(0)=='0')
            return false;
        if(n>1 && str.charAt(n-1)=='0')
            return false;
        return true;
        
    }
    
    
}