public class StringTestThing {
  
  public static void main (String [] args) {
    
    String postfix = "ab+ef*g*- h"; 
    char[] charArray = postfix.toCharArray(); 
    String nodeValue = "";
    
    for (int i = 0; i < charArray.length; i++){
      //nodeValue = "" + charArray[i];
      //char [] tempCharArray = charArray;
      
      for (int j=0; charArray[j+1]!=' '; j++) {
        nodeValue = "" + charArray[i];
        System.out.println(nodeValue);
        if (charArray[j+2]==' ') {
          System.out.println("Why are you running");
        }
      }
      
    }
    
    System.out.println("Hello");
    System.out.println(nodeValue);
    
    
  }
  
}