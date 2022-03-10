import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class fileComparison{

    String[] buffer = null;
    String[] repWords = null;
    Scanner myScanner1, myScanner2;
    String[] stopWords;

    public fileComparison(Scanner myScanner1, Scanner myScanner2, String[] stopWords){
        this.myScanner1 = myScanner1;
        this.myScanner2 = myScanner2;
        this.stopWords = stopWords;

    }
    //This method obtains 2 buffers from buffermaker class, and then begins analysing it
    public void comparfile() {
        Pattern ptr = Pattern.compile(" ");

        //.next returns a string of the line from a file
        //patter split parses through the line using whitespace as delimiter
        //puts it into buffer

        buffer = ptr.split(myScanner1.next());

        //Checking Buffer
        for (String s : buffer) {
            System.out.println(s);
        }
    }

    public void analyse(){

    }

}
