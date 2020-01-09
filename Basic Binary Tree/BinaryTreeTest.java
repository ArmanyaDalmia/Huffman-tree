class BinaryTreeTest {
  public static void main(String[] args) {
    
    BinarySortTree<String> myBinaryTree = new BinarySortTree <String>();   //you can use your list similar to an ArrayList
    
    myBinaryTree.add("a");
    myBinaryTree.add("b");
    myBinaryTree.add("d");
    myBinaryTree.add("c");
    myBinaryTree.add("e");
    
    System.out.println(myBinaryTree.contains("e"));
    System.out.println(myBinaryTree.contains("b"));
    System.out.println(myBinaryTree.contains("hello"));
    
    
  }
}

class BinarySortTree <E extends Comparable<E>> {
  
  private BinaryTreeNode<E> root;
  
  
  // ---------------------------------------------------------------------------------------------------------------
  
  public void add(E item) {
    
    BinaryTreeNode<E> tempRoot = root;
          
      if (root==null) {
        root=new BinaryTreeNode<E>(item,null,null);
        return;
      }
      
      if (item.compareTo(root.getItem())<0) {
        
        if (root.getLeft() != null) {
          recursivelyAdd(root.getLeft(), item);
        } else {
          tempRoot.setLeft (new BinaryTreeNode<E>(item,null,null));
        }
                          
      } else {
        
        if (root.getRight() != null) {
          recursivelyAdd(root.getRight(), item);
        } else { 
          tempRoot.setRight (new BinaryTreeNode<E>(item,null,null));
        }
        
      }
      
  }
  
  private void recursivelyAdd(BinaryTreeNode<E> tempNode, E item) {
    
    if (item.compareTo(tempNode.getItem())<0) {
        
        if (root.getLeft() != null) {
          recursivelyAdd(tempNode.getLeft(), item);
        } else {
          tempNode.setLeft (new BinaryTreeNode<E>(item,null,null));
        }
                          
      } else {
        
        if (tempNode.getRight() != null) {
          recursivelyAdd(tempNode.getRight(), item);
        } else { 
          tempNode.setRight (new BinaryTreeNode<E>(item,null,null));
        }
        
      }
      
  }
  
  // ---------------------------------------------------------------------------------------------------------------
  // ===============================================================================================================
  // ---------------------------------------------------------------------------------------------------------------
  
  public boolean contains (E item) {
    
    BinaryTreeNode<E> tempRoot = root;
    
    if (root==null) {
      return false;
    }
    
    if (item.compareTo(root.getItem())==0) {          
      return true;      
    }
    
    return recursiveContainItem (tempRoot, item);
    
  }

  private boolean recursiveContainItem (BinaryTreeNode<E> tempNode, E item) {
    
    if (item.compareTo(tempNode.getItem())==0) {
      return true;
    }
    
    if (tempNode.isLeaf()==true) {
      return false;
    }
    
    if (item.compareTo(tempNode.getItem())<0) {
        
        if (tempNode.getLeft() != null) {
          recursiveContainItem(tempNode.getLeft(), item);
        } 
                         
      } else if (item.compareTo(tempNode.getItem())>0){
        
        if (tempNode.getRight() != null) {
          recursiveContainItem(tempNode.getRight(), item);
        }

      }
      
      return false;    
  }
  
  // ---------------------------------------------------------------------------------------------------------------
  // ===============================================================================================================
  // ---------------------------------------------------------------------------------------------------------------
                         
  public int size() {
    return 0;
  }
    
  public Boolean isEmpty() {
    return true;
  }
    
  public boolean remove(E item) {
    return true;
  }
  
  private void print () {
    
  }
      
}

class BinaryTreeNode <T extends Comparable<T>> { 
  private T item;
  private BinaryTreeNode<T> left;
  private BinaryTreeNode<T> right;
  
  public BinaryTreeNode(T item) {
    this.item=item;
    this.left=null;
    this.right=null;
  }
  
  public BinaryTreeNode(T item, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
    this.item=item;
    this.left=left;
    this.right=right;
  }
  
  public BinaryTreeNode<T> getRight(){
    return this.right;
  }
  
  public BinaryTreeNode<T> getLeft(){
    return this.left;
  }
  
  public void setRight(BinaryTreeNode<T> right){
    this.right = right;
  }
  
  public void setLeft(BinaryTreeNode<T> left){
    this.left = left;
  }
  
  public T getItem(){
    return this.item;
  }
  
  public void setItem(T item){
    this.item=item;
  }
  
  public boolean isLeaf() {
    if (this.left==null && this.right==null){
    return true;
    }
    
    return false;
  }
  
}