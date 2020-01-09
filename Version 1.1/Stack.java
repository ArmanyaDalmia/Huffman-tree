class Stack<E> {  
  private Node<E> head;
  
  /*
   * 
   public void push (E item){
   
   Node<E> tempNode = head;
   
   if (head==null) {
   head=new Node<E>(item,null);
   return;
   }
   
   while(tempNode.getNext()!=null) { 
   tempNode = tempNode.getNext();
   }
   
   tempNode.setNext(new Node<E>(item,null));
   return;    
   }
   
   public E pop (){
   
   Node<E> tempNode = head;
   E tempNodeItem;
   
   if (head==null) {
   return null;
   }
   
   if (head.getNext()==null){
   tempNodeItem = tempNode.getItem();
   head=null;
   return tempNodeItem;
   }
   
   while(tempNode.getNext().getNext()!=null) { 
   tempNode = tempNode.getNext();
   }
   
   tempNodeItem = tempNode.getNext().getItem();
   tempNode.setNext(null);
   return tempNodeItem;
   }
   
   */
  
  
  
  // ----------------------------------------------------------
  
  public boolean empty() {
    if (head == null) {
      return true;
    } else {
      return false;
    }
  }
  
  public E peek() {
    return head.getItem();
  }
  
  public E pop() {
    Node<E> temp = head;
    head = head.getNext();
    return temp.getItem();
  }
  
  public void push(E item) {
    Node<E> temp = head;
    head = new Node<E>(item,null);
    head.setNext(temp);
  }
  
  // --------------------------------------------------------------------
  
  
  private class Node<T> { 
    private T item;
    private Node<T> next;
    
    public Node(T item) {
      this.item=item;
      this.next=null;
    }
    
    public Node(T item, Node<T> next) {
      this.item=item;
      this.next=next;
    }
    
    public Node<T> getNext(){
      return this.next;
    }
    
    public void setNext(Node<T> next){
      this.next = next;
    }
    
    public T getItem(){
      return this.item;
    }
    
  }
  
}