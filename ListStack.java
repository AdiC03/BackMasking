import java.util.EmptyStackException;

// Questions:
// 1. Why exactly do we need the iterator, when size is there to keep track
// -----Answer: needed for the main file to effectively parse through the data
// ----But to parse through the file, there is a while loop (Questions answered)
// 2. How exactly would we test out the performance of the code with different sizes
// -------Implement a time to track how well each function does; can use online gdb and track
// 3. Need javadoc comments? // Regular comments fine
// 4. No iterator required for arraylist? Cuz iterator not really needed with arrays (yep not required)
// 5. Ok to import NoSuchElement?
// 6. I've been seeing a couple implementations where currIndex == -1; Any subtle differences (If it works it works
// 7. Program not running (It's working now)

public class ListStack implements BKStack, Iterable<Double> {

    // Attributes: top refers to the top of the stack,
    // size is size of stack, and modCount is how many 
    // changes made to stack (ex. adding, removing)
    private Node top;
    private int size;
    private int modCount;

    // Node class: contains double value and 
    // reference to the next node 
    private static class Node {
        double value;
        Node next;

        public Node(double value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    // Construtor for ListStack
    public ListStack() {
        top = null;
        size = 0;
        modCount = 0;
    }

    // Returns if stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    public int count() {

        int count = 0;
        
        // Iterates through current instance of 
        // ListStack and increments count
        for (Double element : this)
        {
            count++;
        }

        return count;
    }

    // Creates a new node and pushes it on top 
    // of the stack by referring to the top index
    public void push(double d) {
        Node newNode = new Node(d, top);
        top = newNode;
        size++;
        modCount++;
    }

    // Throws exception if the stack is empty
    // Otherwise, the top index references the 
    // next node (to delete the current top node),
    // and the current top node gets returned
    public double pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        double value = top.value;
        top = top.next;
        size--;
        modCount++;
        return value;
    }

    // Checks what the top/current value is
    public double peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.value;
    }


    // Instance of ListStackIterator
    public java.util.Iterator<Double> iterator() {
        return new ListStackIterator();
    }

    private class ListStackIterator implements java.util.Iterator<Double> {
        private Node current;
        private int expectedModCount;

        public ListStackIterator() {
            current = top;
            expectedModCount = modCount;
        }

        public boolean hasNext() {
            if (expectedModCount != modCount)
            {
                throw new java.util.ConcurrentModificationException();
            }
            return current != null;
        }

        public Double next() {
            if (expectedModCount != modCount)
            {
                throw new java.util.ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            double value = current.value;
            current = current.next;
            return value;
        }
    }
    
}
