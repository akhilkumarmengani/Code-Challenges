class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0;
        int totalLength = (nums1.length + nums2.length);
        int medIndex =  totalLength /2;
        int i = 0;
        int j = 0;
        int count = 0;
        int m1=0,m2=0;
        while(count <= medIndex){
            m2 = m1;
            if(i < nums1.length && j < nums2.length){
                if(nums1[i]<=nums2[j]){
                    m1 = nums1[i++];
                }
                else{
                    m1 = nums2[j++];
                }
            }
            else if (i < nums1.length){
                m1 = nums1[i++];
            }
            else if (j < nums2.length){
                m1 = nums2[j++];
            }
            count++;
        }
        if(totalLength%2==0){
            return (double)(m1+m2)/2.0;
        }
        return (double)m1;
    }
}