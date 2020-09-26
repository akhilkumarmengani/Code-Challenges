Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2


class MedianFinder {

    PriorityQueue<Integer> q1 ;
    PriorityQueue<Integer> q2 ;
        
    /** initialize your data structure here. */
    public MedianFinder() {
        q2 = new PriorityQueue<Integer>();
        q1 = new PriorityQueue<Integer>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        if(q1.size() == q2.size()){
            if(q1.isEmpty()){
                q1.add(num);
                return;
            }
            if(q1.peek() >=  num){
                q1.add(num);
            }
            else{
                q2.add(num);
            }
        }
        else if(q1.size() > q2.size()){
            if(q1.peek() >= num){
                int top = q1.poll();
                q1.add(num);
                q2.add(top);
            }
            else{
                q2.add(num);
            }
        }
        else{
            if(q2.peek() <= num){
                int top = q2.poll();
                q2.add(num);
                q1.add(top);
            }
            else{
                q1.add(num);
            }
        }
    }
    
    public double findMedian() {
        if(q1.size() == 0)
            return 0;
        if(q1.size() == q2.size()){
            return Double.valueOf((q1.peek() + q2.peek())/2.0);
        }
        if(q1.size() > q2.size()) {
            return q1.peek();
        }
        return q2.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */