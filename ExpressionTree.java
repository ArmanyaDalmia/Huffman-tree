import java.util.Stack; 
  
class ExpressionTree { 
  
      public static void main(String []args) { 
  
        ExpressionTree et = new ExpressionTree(); 
        String postfix = "((6 8)(5 7))"; 
        char[] charArray = postfix.toCharArray(); 
        Node root = et.constructTree(charArray); 
        System.out.println("infix expression is"); 
        et.inorder(root); 
  
    } 
  
    // A utility function to check if 'c' 
    // is an operator 
  
    boolean isOperator(char c) { 
        if (c == ')') { 
            return true; 
        } 
        return false; 
    } 
  
    // Utility function to do inorder traversal 
    void inorder(Node t) { 
        if (t != null) { 
            inorder(t.left); 
            System.out.print(t.value + " "); 
            inorder(t.right); 
        } 
    } 
  
    // Returns root of constructed tree for given 
    // postfix expression 
    Node constructTree(char postfix[]) { 
        Stack<Node> st = new Stack(); 
        Node t, t1, t2; 

  
        // Traverse through every character of 
        // input expression 
        for (int i = 0; i < postfix.length; i++) { 
          
          String nodeValue = "";
          
            // If operand, simply push into stack 
            if (!isOperator(postfix[i])) {
              if (postfix[i]=='('){
                System.out.println ("(hello" + i);
                continue;
              } else if (postfix[i]==' '){
                System.out.println (" hello" + i);
                continue;
              }
              
              nodeValue += postfix[i];
              
              if (postfix[i+1]==' ' || postfix[i+1]=='(' || postfix[i+1]==')') {
                t = new Node(Integer.parseInt(nodeValue)); 
                System.out.println (i);
                st.push(t); 
              } else {
                System.out.println ("( )hello" + i);
                continue;
              }
              
            } else // operator 
            { 
                t = new Node(); 
  
                // Pop two top nodes 
                // Store top 
                t1 = st.pop();      // Remove top 
                t2 = st.pop(); 
  
                //  make them children 
                t.right = t1; 
                t.left = t2; 
  
                // System.out.println(t1 + "" + t2); 
                // Add this subexpression to stack 
                st.push(t); 
            } 
        } 
  
        //  only element will be root of expression 
        // tree 
        t = st.peek(); 
        st.pop(); 
  
        return t; 
    } 
}

class Node { 
  
    int value; 
    Node left, right; 
  
    Node() { 
        left = right = null; 
    } 
    
    Node(int item) { 
        value = item; 
        left = right = null; 
    } 
} 