/*
 * Stack.java
 * Author: Armanya Dalmia
 * Date: April.1, 2019
 * Purpose: Stack data structure
 */

// Class declaration
class Stack<E> { 
  
  // Private variable of type generic node
  private Node<E> head;
  
  // ----------------------------------------------------------
  
  /**
   * empty
   * @return true if stack is empty, false otherwise
   */
  public boolean empty() {
    if (head == null) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * peek
   * @return Item stored in the head
   */
  public E peek() {
    return head.getItem();
  }
  
  /**
   * pop
   * @return Returns the value in the node that is highest on the stack
   */
  public E pop() {
    Node<E> temp = head;
    head = head.getNext();
    return temp.getItem();
  }
  
  /**
   * push
   * Adds a node to the stack
   * @param takes a generic object
   */
  public void push(E item) {
    Node<E> temp = head;
    head = new Node<E>(item,null);
    head.setNext(temp);
  }
  
  // --------------------------------------------------------------------
  
  /*
   * Node.java
   * Author: Armanya Dalmia
   * Date: April.1, 2019
   * Purpose: Private inner class that defines the node of the stack
   */
  private class Node<T> { 
    
    // Variable declaration
    private T item;
    private Node<T> next;
    
    // Basic constructor that takes in an item
    public Node(T item) {
      this.item=item;
      this.next=null;
    }
    
    // Secondary constructor that takes an item and a node that becomes the next
    public Node(T item, Node<T> next) {
      this.item=item;
      this.next=next;
    }
    
    /**
     * getNext
     * @return Returns next value
     */
    public Node<T> getNext(){
      return this.next;
    }
    
    /**
     * setNext
     * Sets input as next value
     * @param takes in a generic node
     */
    public void setNext(Node<T> next){
      this.next = next;
    }
    
    /**
     * getItem
     * @return returns whatever is stored in item
     */
    public T getItem(){
      return this.item;
    }
    
  }
  
}