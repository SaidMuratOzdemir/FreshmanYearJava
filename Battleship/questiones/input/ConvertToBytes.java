package Battleship.questiones.input;
import java.io.InputStream;

public class ConvertToBytes {
    /*
    Convert to bytes
    Read an input text from the console and print it as a sequence of bytes.

    Use System.in as input stream directly. Avoid using Scanner.

    Sample Input 1:
    abc
    Sample Output 1:
    979899
    Sample Input 2:
    another text
    Sample Output 2:

    9711011111610410111432116101120116
     */

    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        int i = inputStream.read();

        while (i!=-1){
            System.out.print(i);
            i = inputStream.read();
        }
    }
}
