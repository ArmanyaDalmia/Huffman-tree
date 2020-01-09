import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HuffmanDecompressionTest12345 {
  
    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {          

          
          in = new FileInputStream("EXAMPLE.MZIP");
          int c;
          String newFileName = "";
          String huffmanTreeArrangement = "";
          int ignoreBits = 0;
          
          while ((c = in.read()) != 13) {
            newFileName = newFileName + (char)c;
            System.out.print((char)c);
            //out.write(c);
          }              

          
          while ((c = in.read()) != 13) {
            huffmanTreeArrangement += (char)c;
          }
          System.out.println(huffmanTreeArrangement);
          
          while ((c = in.read()) != 13) {
            ignoreBits = Character.getNumericValue((char)c);
          }
          
          out = new FileOutputStream(newFileName.toLowerCase());
          char[] arrangementCharArray = huffmanTreeArrangement.toCharArray(); 
            
            
            /*
            int c;
            String newFileName = "";
            String binaryTreeArrangement = "";
            int ignoreBits = 0;
            String encodedBinary = "";

            for (int j=0; j < 4; j++) {
              while ((c = in.read()) != 13) {
                
                if (j==0) {
                  newFileName = newFileName + (char)c;
                  System.out.print((char)c);
                } else if (j==1) {
                  binaryTreeArrangement += (char)c;
                } else if (j==2) {
                  ignoreBits = Character.getNumericValue((char)c);
                } else if (j==3) {
                  encodedBinary += (char)c;
                }
                
              }
              
              out = new FileOutputStream(newFileName.toLowerCase());
              System.out.println(binaryTreeArrangement);
              System.out.println(ignoreBits);
              System.out.println(encodedBinary);
            }
            */
          
          /*
            int c;
            //Line counter checks to see what information we require
            int lineCounter = 0;
            //String of new file name and description of the tree
            String newFileName = "";
            String treeString = "";
            //Ignore needs to be initialized with a value so default is 0
            int ignore = 0;
            boolean exit = false;
            while (!exit) {
              c = in.read();
              if (c == 13) {
                //Whenever c = 13, it is the end of the end line carriage, so i read the line feed and then increase the line counter
                in.read();
                lineCounter++;
                if (lineCounter == 3) {
                  exit = true; //We have all the string information and can now exit this loop
                }
              } else if (lineCounter == 0) {
                newFileName += Character.toString((char)c);
              } else if (lineCounter == 1) {
                treeString += Character.toString((char)c);
              } else if (lineCounter == 2) {
                ignore = Integer.parseInt(Character.toString((char)c));
              } 
            }
            
            out = new FileOutputStream(newFileName.toLowerCase());
            System.out.println(treeString);
            System.out.println(ignore);
            //System.out.println(encodedBinary);
            */
              
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        
    }
    
}