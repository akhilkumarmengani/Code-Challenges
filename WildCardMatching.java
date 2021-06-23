class Solution {
    
    Boolean dp[][] ;
    public boolean isMatchUtil(String s, String p, int pIndex, int sIndex){
        if(pIndex>=p.length() && sIndex<s.length()){
            return false;
        }
        if(pIndex<p.length() && sIndex>=s.length()){
            if(pIndex+1==p.length() && p.charAt(pIndex)=='*'){
                dp[pIndex][sIndex-1] = true;
                return true;
            }
            return false;
        }
        if(pIndex==p.length() && sIndex==s.length()){
            return true;
        }
        
        if(dp[pIndex][sIndex]!=null){
            return dp[pIndex][sIndex];
        }
        
        char pChar = p.charAt(pIndex);
        char sChar = s.charAt(sIndex);
        
        if(pChar==sChar || pChar == '?'){
            dp[pIndex][sIndex] = isMatchUtil(s,p,pIndex+1,sIndex+1);
        }
        else if(pChar=='*'){
            dp[pIndex][sIndex] = isMatchUtil(s,p,pIndex+1,sIndex) || isMatchUtil(s,p,pIndex,sIndex+1);
        }
        else{
            dp[pIndex][sIndex] = false;
        }
        
        return dp[pIndex][sIndex];
    }
    
    public boolean isMatch(String s, String p) {
        if(p.length()==0 && s.length()==0){
            return true;
        }
        if(p.length()==0){
            return false;
        }
        StringBuilder pMod = new StringBuilder();
        for(int i = 0; i < p.length(); ){
            char ch = p.charAt(i);
            pMod.append(ch);
            if(ch=='*'){
                while(i<p.length()){
                    ch = p.charAt(i);
                    if(ch!='*'){
                        i--;
                        break;
                    }
                    i++;
                }
            }
            i++;
        }
        
        if(s.length()==0){
            if(pMod.length()==1 && pMod.charAt(0)=='*')
                return true;
            return false;
        }
        
        dp = new Boolean[pMod.length()][s.length()];
        isMatchUtil(s,pMod.toString(),0,0);
        return dp[pMod.length()-1][s.length()-1]==null? false:dp[pMod.length()-1][s.length()-1];
    }
    
    // time complexity = O(min(s,p/2))
    // p/2 is the maximum number of stars in the pattern
    
}