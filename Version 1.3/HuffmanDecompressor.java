/*
 * HuffmanDecompressor.java
 * Author: Armanya Dalmia
 * Date: April.1, 2019
 * Purpose: Reads .MZIP file and converts the compressed binary into the original file
 */

// Necessary imports
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

// Class declaration
class HuffmanDecompressor { 
  
  // private variable of type node
  private Node<Integer> root;
  
  // --------------------------------------------------------------------------------------------------------
  
  // Constructor that runs the necessary method when HuffmanDecompressor object is created
  HuffmanDecompressor() {
    
    // Calls method that decodes the .MZIP file
    decode();
    
  }
  
  // --------------------------------------------------------------------------------------------------------
  
  /**
   * decode
   * Reads .MZIP file, creates tree, decompresses the data based on constructed tree
   */
  private void decode(){
    
    // Scanner declaration
    Scanner input = new Scanner (System.in);
    System.out.print("Please enter the name of the file to be decoded (include extension): ");
    
    // Set user input as file .MZIP file to be read
    String originalFile = input.nextLine();
    
    // Nest code in a try-catch statement
    try {          
      
      // Use Buffered input and outputstreams to speed up program
      BufferedInputStream in = new BufferedInputStream(new FileInputStream(originalFile));
      BufferedOutputStream out;
      
      // Variable declarations
      int c;
      int ignoreBits = 0;
      int nextCharacter;
      int length;
      String newFileName = "";
      String huffmanTreeArrangement = "";
      Node<Integer> testNode;
      
      // Reads first line of file until enter key and converts the line to a string that is the original file's name
      while ((c = in.read()) != 13) {
        newFileName = newFileName + (char)c;
      }      
      // Set name of the file that will be created to the original filename of the file that was compressed
      out = new BufferedOutputStream(new FileOutputStream(newFileName.toLowerCase()));
      
      // Reads first line of file until enter key and converts the line to a string that is the arrangement of the huffman tree that needs to be created
      while ((c = in.read()) != 13) {
        huffmanTreeArrangement += (char)c;
      }
      // Converts the string into a character array to be easier to traverse through
      char[] arrangementCharArray = huffmanTreeArrangement.toCharArray(); 
      
      // Reads first line of file until enter key and converts the line to an int that represents the number of bits that needs to be ignored from the end of the last character of the compressed data
      while ((c = in.read()) != 13) {
        ignoreBits = Character.getNumericValue((char)c);
      }      
      
      // Calls construct tree method while passing in the character array
      constructTree(arrangementCharArray);
      
      // Node that will be checked while traversing the input data
      testNode = root;
      
      // Next character is read twice to ignore the line feed character
      nextCharacter = in.read();
      nextCharacter = in.read();
      
      // c is initially set to nextCharacter so that the data can be traversed through
      c = nextCharacter;
      
      // Keeps running until there is no more characters left in the input data (encoded binary)
      while (c != -1) {
        
        // Takes in next character in the input
        nextCharacter = in.read();
        
        // If last character has been reached, then length of for loop is 8 - bits needed to be ignored
        length = 8 - ignoreBits;
        
        // Default case for most of the characters in the input
        if (nextCharacter != -1) {          
          length = 8;          
        }
        
        // Runs the for loop based on how many characters need to be read
        // Traverses bit by bit to decode
        for (int i = 0; i < length; i++) {
          
          //Runs if the first bit of c is a 1
          if ((c&128) == 128) {
            testNode = testNode.getRight();
            
            //Runs if the first bit of c is a 0
          } else if ((c&128) != 128) {
            testNode = testNode.getLeft();
          }
          
          // If the testNode is now a leaf, prints out the item stored within the node
          // Sets testNode to root again so that the tree can be traversed again
          if (testNode.isLeaf() == true) {
            out.write(testNode.getItem());
            testNode = root;
          }
          
          //Bit shift left
          c = c<<1;
        }
        
        //Shifts c to the next integer to continue reading the input
        c = nextCharacter;
      }
      
      // Prints that the decompression has been a success
      System.out.println ("The file has now been decompressed");
      
      // Closes the BufferedStreams
      in.close();
      out.close();
      
      // Catches IOException and calls printStackTrace function
    } catch (IOException e) {
      e.printStackTrace();      
    }
  }  
  
  // --------------------------------------------------------------------------------------------------------
  
  /**
   * isClosedBracket
   * Helper method that determines if the character at the specified location is a closed bracket or not
   * @param takes in a character
   * @return returns boolean true or false based on purpose
   */
  private boolean isClosedBracket(char c) { 
    if (c == ')') { 
      return true; 
    } 
    return false; 
  } 
  
  /**
   * constructTree
   * Creates a huffman tree according to the arrangement that is passed in, tree will be used to decode compressed data
   * @param takes in a character array that represent the tree arrangement
   */
  private void constructTree(char arrangement[]) { 
    
    // Create a stack to be used to create the tree
    Stack<Node<Integer>> stack = new Stack<>(); 
    // Create temporary nodes to fill tree based on stack
    Node<Integer> tempNode, child1, child2; 
    
    // Empty string that will represent the value of the leaves in the tree
    String nodeValue = "";
    
    
    // Traverses through all the characters in the charArray that was passed in
    // Creates tree based on values
    for (int i = 0; i < arrangement.length; i++) { 
      
      // If character is not a closed bracket, runs through subsequent code 
      // Will push values onto the stack until a closed bracket is reached in charArray that was passed in
      if (!isClosedBracket(arrangement[i])) {
        
        // If the current character is an open bracket or a space, will skip the iteration
        if (arrangement[i]=='('){
          continue;
        } else if (arrangement[i]==' '){
          continue;
        }
        
        // Since iteration has not been skipped yet, adds the character to the nodeValue string
        nodeValue += arrangement[i];
        
        // If the next character is a space, or a bracket, then means that the node value has been read
        // New node will be created and pushed onto the stack with the node's item being the nodeValue
        if (arrangement[i+1]==' ' || arrangement[i+1]=='(' || arrangement[i+1]==')') {
          
          // try-catch statement to avoid NumberFormatException that arises due to parsing the string to an integer
          try {
            stack.push(new Node<>(Integer.parseInt(nodeValue)));
          } catch (NumberFormatException e) {
          }
          
          // Reset nodeValue to empty to allow for the other ascii values to be read
          nodeValue = "";
          
          // Next character must be another integer which means that the value of the node hasn't been fully read
          // Skips iteration
        } else {
          continue;
        }
        
        // If character is a closed bracket, runs through subsequent code
        // Instantiates two temporary nodes and sets them as the leaves of an internal node, pushes new branch onto the stack
      } else {
        
        // Instantiates temporary node that is empty
        // Sets value of the other two temporary nodes as the top two entries in the stack
        tempNode = new Node<>(null, null, null);
        child1 = stack.pop();
        child2 = stack.pop(); 
        
        
        //  Sets the two children nodes as the left and right values of the temporary node
        tempNode.setRight(child1); 
        tempNode.setLeft(child2); 
        
        // Pushes the new branch onto the stack
        stack.push(tempNode); 
      } 
    } 
    
    // Sets the root as the last element in the stack
    root = stack.pop(); 
  } 
  
  // --------------------------------------------------------------------------------------------------------
  
  /*
   * Node.java
   * Author: Armanya Dalmia
   * Date: April.1, 2019
   * Purpose: Private inner class that defines the node of the tree
   */
  private class Node <T> { 
    
    // Defines private variables
    private T item;
    private Node<T> left;
    private Node<T> right;
    
    // Basic constructor for the Node class, only has a defined item
    public Node(T item) {
      this.item=item;
      this.left=null;
      this.right=null;
    }
    
    // Second Constructor that has a defined item, left, and right
    public Node(T item, Node<T> left, Node<T> right) {
      this.item=item;
      this.left=left;
      this.right=right;
    }
    
    /**
     * getRight
     * @return returns the right value which is a node
     */
    public Node<T> getRight(){
      return this.right;
    }
    
    /**
     * getLeft
     * @return returns the left value which is a node
     */
    public Node<T> getLeft(){
      return this.left;
    }
    
    /**
     * setRight
     * Sets the right value to whatever node was passed in
     * @param takes in a generic node
     */
    public void setRight(Node<T> right){
      this.right = right;
    }
    
    /**
     * setLeft
     * Sets the left value to whatever node was passed in
     * @param takes in a generic node
     */
    public void setLeft(Node<T> left){
      this.left = left;
    }
    
    /**
     * getItem
     * @return returns whatever is stored in item
     */
    public T getItem(){
      return this.item;
    }
    
    /**
     * setItem
     * Sets the value of item to whatever is passed in
     * @param takes in a generic object
     */
    public void setItem(T item){
      this.item=item;
    }
    
    /**
     * isLeaf
     * Determines whether the node is a leaf or not (whether it has left or right values)
     * @return boolean value of true or false
     */
    public boolean isLeaf() {
      if (this.left==null && this.right==null){
        return true;
      }
      
      return false;
    }
    
  }
  
}