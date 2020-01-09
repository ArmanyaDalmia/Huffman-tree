import java.lang.IndexOutOfBoundsException;
import java.lang.NoSuchMethodError;
import java.util.*;

//**********A template  for a simple linked list ********

class linkTemplate {
  public static void main(String[] args) {     //the main method
    Tree<Integer> tree = new Tree<Integer>();
    tree.remove(12);
  }
}
class Tree<E extends Comparable<E>> {
  private Node<E> root;
  public void print() {
    print1(root);
  }
  
  private void print1(Node node) {
    if (node != null) {
      print1(node.getLeft());
      System.out.println(node.getItem());
      print1(node.getRight());
    }
  }
  public void add(E item) {
    if (root == null) {
      root = new Node<E>(item,null,null);
    } else {
      add1(root,item);
    }
  }
  
  public void remove(E item) {
    if (root == null) {
      return;
    } else {
      root = remove1(root,item);
    }
  }
  
  private void add1(Node<E> node, E item) {
    Node<E> temp = new Node<E>(item,null,null);
    if (temp.compareTo(node) > 0) {
      if (node.getRight() == null) {
        node.setRight(temp);
      } else {
        add1(node.getRight(),item);
      }
    } else if (temp.compareTo(node) < 0) {
      if (node.getLeft() == null) {
        node.setLeft(temp);
      } else {
        add1(node.getLeft(),item);
      }
    }
  }
  
  private Node<E> remove1(Node<E> node, E item) {
    if (node == null) {
      return node;
    }
    if (node.getItem().equals(item)) {
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else {
        node.setItem(remove2(node.getRight()));
        node.setRight(remove1(node.getRight(),node.getItem()));
        return node;
      }
    } else {
      Node<E> temp = new Node<E>(item,null,null);
      if (temp.compareTo(node) > 0) {
        node.setRight(remove1(node.getRight(), item));
      } else {
        node.setLeft(remove1(node.getLeft(), item));
      }
    }
    return node;
  }
  private E remove2 (Node node) {
    E min = (E)node.getItem();
    while(node.getLeft() != null) {
      node = node.getLeft();
      min = (E)node.getItem();
    }
    return min;
  }
}


class Stack<E extends Comparable<E>> {
  private Node<E> head;
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
}


class PriorityQueue<E extends Comparable<E>>{
  private Node<E> head;
  public void enqueue(E item) {
    Node<E> temp = head;
    Node<E> prev = null;
    Node<E> node = new Node<E>(item,null);
    while(temp != null) {
      if (temp.compareTo(node) > 0) {
        if (prev == null) {
          prev = head;
          head = node;
          head.setNext(prev);
        } else {
          prev.setNext(node);
          prev.getNext().setNext(temp);
        }
        return;
      }
      prev = temp;
      temp = temp.getNext();
    }
    if (prev == null) {
      head = node;
    } else {
      prev.setNext(node);
    }
  }
  public E dequeue() {
    Node<E> temp = head;
    Node<E> prev = null;
    Node<E> prev1 = null;
    while(temp != null) {
      prev1 = prev;
      prev = temp;
      temp = temp.getNext();
      
    }
    if (prev == null) {
      if (temp == null) {
        return null;
      } else {
        head = null;
        return temp.getItem();
      }
    } else {
      if (prev1 == null) {
        head.setNext(null);
        return prev.getItem();
      } else {
        prev1.setNext(null);
        return prev.getItem();
      }
    }
  }
  public boolean empty() {
    if (head == null) {
      return true;
    }
    return false;
  }
}
// ********************** Simple Linked List class in the linked list *********
class SimpleLinkedList<E extends Comparable<E>> { 
  private Node<E> head;
  
  
  public void add(E item) { 
    Node<E> tempNode = head;
    
    if (head == null) {
      head = new Node<E>(item,null);
      return;
    }
    
    while(tempNode.getNext() != null) { 
      tempNode = tempNode.getNext();
    }
    
    tempNode.setNext(new Node<E>(item,null));
  }
  
  
  
  public E get(int index) {
    Node<E> tempNode = head;
    int counter = 0;
    while(tempNode != null) {
      if (counter == index) {
        return tempNode.getItem();
      }
      counter++;
      tempNode = tempNode.getNext();
    }
    throw new IndexOutOfBoundsException();
  }
  
  public int indexOf(E item) {
    Node<E> tempNode = head;
    int counter = 0;
    while(tempNode != null) {
      if (tempNode.getItem().equals(item)) {
        return counter;
      }
      tempNode = tempNode.getNext();
      counter++;
    }
    return -1;
  }
  
  public E remove(int index) {
    Node<E> tempNode = head;
    Node<E> prev = null;
    int counter = 0;
    while(tempNode != null) {
      if (counter == index) {
        if (prev == null) {
          head = head.getNext();
        } else {
          prev.setNext(tempNode.getNext());
        }
        return tempNode.getItem();
      }
      counter++;
      prev = tempNode;
      tempNode = tempNode.getNext();
    }
    throw new IndexOutOfBoundsException();
  }
  
  public boolean remove(E item) {
    Node<E> tempNode = head;
    Node<E> prev = null;
    while(tempNode != null) {
      if (tempNode.getItem().equals(item)) {
        if (prev == null) {
          head = head.getNext();
        } else {
          prev.setNext(tempNode.getNext());
        }
        return true;
      }
      prev = tempNode;
      tempNode = tempNode.getNext();
    }
    return false;
  }
  
  
  public void clear() { 
    head = null;
  }
  
  public int size() {
    int counter = 0;
    Node<E> tempNode = head;
    while(tempNode != null) {
      counter++;
      tempNode = tempNode.getNext();
    }
    return counter;
  }
  
}



// ********************** A Node in the linked list *********
class Node<T extends Comparable<T>> implements Comparable<Node<T>>{ 
  private T item;
  private Node<T> next;
  private Node<T> left;
  private Node<T> right;
  public Node(T item, Node<T> left, Node<T> right) {
    this.item = item;
    this.left = left;
    this.right = right;
  }
  
  public Node<T> getLeft() {
    return left;
  }
  
  public Node<T> getRight() {
    return right;
  }
  
  public void setLeft(Node<T> node) {
    this.left = node;
  }
  
  public void setRight(Node<T> node) {
    this.right = node;
  }
  
  public Node(T item) {
    this.item=item;
    this.next=null;
  }
  
  public int compareTo(Node<T> node) {
    return this.getItem().compareTo(node.getItem());
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
  
  public void setItem(T item) {
    this.item = item;
  }
}