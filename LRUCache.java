/*
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

*/ 


class LRUCache {
    
//     int size;
//     LinkedHashMap<Integer,Integer> cache;

//     public LRUCache(int capacity) {
//         this.size = capacity;
//         cache = new LinkedHashMap<>(capacity,0.75F,true){
//             @Override
//             protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//                 return size() > capacity; 
//             }
//         };
//     }
    
//     public int get(int key) {
//         return cache.getOrDefault(key,-1);
//     }
    
//     public void put(int key, int value) {
//         cache.put(key,value);
//     }
    
    class Node{
        int val;
        int key;
        Node next;
        Node prev;
        Node(int key,int val){
            this.val = val;
            this.key = key;
            this.next = null;
            this.prev  = null;
        }
    }
    
    class DoubleLinkedList{
        int size;
        Node head;
        DoubleLinkedList(){
            this.head = new Node(-1,-1);
            this.head.next = null;
            this.head.prev = null;
        }
        
        public Node add(int key,int val){
            Node temp = head.next;
            head.next = new Node(key,val);
            head.next.prev = head;
            if(temp!=null){
                head.next.next = temp;
                head.next.next.prev = head.next;
            }
            this.size++;
            return head.next;
        }
        
        public void remove(Node node){
            if(node.prev!=null)
                node.prev.next = node.next;
            if(node.next!=null)
                node.next.prev = node.prev;
            node = null;
            this.size--;
        }
        
        public Node removeLast(){
            Node current = this.head;
            while(current.next!=null){
                current = current.next;
            }
            int val = current.val;
            current.prev.next = null;
            current.prev = null;
            this.size--;
            return current;
        }
        
        public int getSize(){
            return this.size;
        }
        
        public void print(){
            Node current = this.head;
            while(current!=null){
                System.out.print(current.val+" ");
                current=current.next;
            }
            System.out.println();
        }
    }
    
    int capacity;
    DoubleLinkedList dll;
    HashMap<Integer,Node> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.dll = new DoubleLinkedList();
        this.map = new HashMap<Integer,Node>();
    }
    
    public int get(int key) {
       if(!map.containsKey(key)){
           return -1;
       }
       int val = map.get(key).val;
       dll.remove(map.get(key));
       map.put(key,dll.add(key,val));
       // dll.print();
       return val;
    }
    
    public void put(int key, int value) {
        if(dll.getSize() >= this.capacity){
            if(!map.containsKey(key)){
                Node node = dll.removeLast();
                map.remove(node.key);
                node = null;
            }
        }
        if(map.containsKey(key)){
            dll.remove(map.get(key));
            //map.remove(key);
        }
        map.put(key,dll.add(key,value));
       //dll.print();
    }
    
    
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */