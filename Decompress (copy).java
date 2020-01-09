import java.io.IOException;
import java.io.FileInputStream;
import java.util.Stack;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

/**
 * Builds a Huffman tree and decodes it.
 * 
 * @author Alvin Prieto
 * 
 * @since 30 Nov 2012
 */
public class Decompress {

  private int i = 0;
  private FileInputStream fis;
  private StringBuilder sb = new StringBuilder();
  private String fileName = "";
  private FileOutputStream file;
  private String leftOver = "";
  private HuffmanNode root;
  private BufferedOutputStream bw;

  /**
   * Default constructor. Runs the file to be decoded through the methods required to decode it.
   * 
   * @param fileName The name name of the huff file that is to be decoded.
   */
  public Decompress(String fileName) throws IOException {

    this.fileName = fileName;

    this.file = new FileOutputStream(fileName.substring(0, fileName.length() - 5));
    this.fis = new FileInputStream(fileName);
    this.bw = new BufferedOutputStream(file);

    byteCount(fis);
    readBytes(fileName);
    buildTree(sb.toString());
    decodeTree(leftOver);

    bw.close();
  }

  /**
   * Reads in the remaining bytes of the file and the first 8 have been read. Converts the bytes to binary digits. If
   * the binary digits length < 8, it will append 0 to the beggining of it. ex: 14 ---> 1110 after addition: 00001110
   * 
   * Builds a string of the binary digits.
   * 
   * @param fileName The name of the file that is to be read.
   */
  private void readBytes(String fileName) throws IOException {

    int val = 0;
    byte[] arr = new byte[fis.available()];

    while (val != -1) {
      val = fis.read();
      if (val != -1) {

        String s = Integer.toBinaryString(val);

        if (s.length() < 8) {
          // Adding leading zeroes
          StringBuilder build = new StringBuilder();
          int num = 8 - s.length();
          for (int x = 0; x < num; x++) {
            build.append("0");
          }
          build.append(s);
          sb.append(build);

        }
        else {
          sb.append(s);
        }
      }
    }
  }

  /**
   * Reads in the first 8 bytes of the file and converts it to an integer. The integer value is the amount of bytes in
   * the original file.
   * 
   * @param fis A FileInputStream that reads in data from the file
   */
  private int byteCount(FileInputStream fis) throws IOException {

    byte[] b = new byte[4];
    fis.read(b);

    for (int x = 0; x < b.length; x++) {
      this.i = i << 8;
      this.i = i | (int) (b[x] & 0xff);
    }
    return i; // i = byte size of original file
  }

  /**
   * Builds a tree off of the given data. After building the tree, sends remaining data to decode method
   * 
   * @param str A string containing the data to build the tree
   */
  @SuppressWarnings("unchecked")
  private void buildTree(String str) {

    Stack<HuffmanNode> stack = new Stack<HuffmanNode>();
    str.charAt(0);

    this.root = new HuffmanNode();
    stack.push(root);

    HuffmanNode node = null;

    int index = 1;

    /**
     * create a stack read first 0 from stream set root to a new empty internal node and push it onto stack while stack
     * is not empty: read next bit from stream if bit is 0: create a new empty internal node else on a 1: read 8 more
     * bits and create a new leaf node with the data if stack.peek() does not have left child: add the new node as left
     * child of stack.peek() else: add the new node as right child of stack.peek() pop the top of the stack (because now
     * it has two children) if the new node is an internal node: push the new node onto stack
     */
    while (!stack.empty() && index < str.length()) {

      if (str.charAt(index) == '0') {
        node = new HuffmanNode();
      }
      else if (str.charAt(index) == '1') {

        String s = str.substring(index + 1, index + 9);

        int charCode = Integer.parseInt(s, 2);
        byte b = (byte) charCode;

        node = new HuffmanNode(b, 0);
        index += 8;
      }
      if (stack.peek().getLeft() == null) {
        stack.peek().setLeft(node);
      }
      else {
        stack.peek().setRight(node);
        stack.pop();
      }
      if (node.getData() == null) {
        stack.push(node);
      }
      index++;
    }
    this.leftOver = str.substring(index, str.length());
  }

  /**
   * Decodes the tree. Based on byteCount, that is the amount of the bytes that are read. Writes data to file.
   * 
   * @param str The remaining bytes in the file that contains info to decode the file.
   */
  private void decodeTree(String str) throws IOException {
    int length = str.length();
    int num = 0;
    // 0 left
    // 1 right
    for (int x = 0; x < i; x++) {
      HuffmanNode curr = root;
      while (!curr.isLeaf() && num < length) {
        if (str.charAt(num) == '0') {
          curr = curr.getLeft();
          num++;
        }
        else {
          curr = curr.getRight();
          num++;
        }
      }
      // Write data to file
      String bite = curr.getData().toString();
      int number = Integer.parseInt(bite);
      char c = (char) number;
      bw.write(c);
    }
  }
}