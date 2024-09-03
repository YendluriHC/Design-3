class LRUCache {
    private class Node {
        int key, value;
        Node prev, next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        
        // Dummy head and tail nodes
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        
        // Move the accessed node to the front (most recently used)
        Node node = cache.get(key);
        remove(node);
        insertAtFront(node);
        
        return node.value;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // If key exists, update the value and move it to the front
            Node node = cache.get(key);
            node.value = value;
            remove(node);
            insertAtFront(node);
        } else {
            if (cache.size() == capacity) {
                // Remove the least recently used item
                cache.remove(tail.prev.key);
                remove(tail.prev);
            }
            
            // Insert the new node at the front
            Node newNode = new Node(key, value);
            insertAtFront(newNode);
            cache.put(key, newNode);
        }
    }
    
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void insertAtFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}
