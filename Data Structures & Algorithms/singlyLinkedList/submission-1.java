
class LinkedList {

    // 1. Define the internal Node structure
    private static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // 2. Class properties to keep track of the list
    private Node head;
    private Node tail;
    private int size;

    // Initializes an empty linked list
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Return the value of the ith node (0-indexed). If out of bounds, return -1
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.val;
    }

    // Insert a node with val at the head of the list
    public void insertHead(int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;

        if (size == 0) {
            tail = head;
        }
        size++;
    }

    // Insert a node with val at the tail of the list
    public void insertTail(int val) {
        Node newNode = new Node(val);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Remove the ith node (0-indexed). If out of bounds, return false
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            return false;
        }

        // Case 1: Removing the head node
        if (index == 0) {
            head = head.next;
            if (size == 1) {
                tail = null; // List is now completely empty
            }
        } else {
            // Case 2: Removing an intermediate or tail node
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

            // Bypass the target node
            prev.next = prev.next.next;

            // If we removed the tail node, update the tail pointer
            if (index == size - 1) {
                tail = prev;
            }
        }

        size--;
        return true;
    }

    // Return an ArrayList of all the values in the linked list, ordered from head to tail
    public ArrayList<Integer> getValues() {
        ArrayList<Integer> values = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            values.add(curr.val);
            curr = curr.next;
        }
        return values;
    }
}