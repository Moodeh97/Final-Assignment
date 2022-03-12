import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileHandler {
    //Attributes
    String Filename; //Globally Accessible Variable
    File myFile;
    String buffer;
    String[] stopWordArray;
    //constructors
    public FileHandler(String Filename) {
        this.Filename = Filename;

    }

    //Methods / Behaviour

    //Method to just create a file object for use in other methods
    public Scanner openFile() {
        myFile = new File(Filename);

        Scanner myScan = null;
        try {
            myScan = new Scanner(myFile);
            System.out.println("Scanner Sucessfully Instantiated\n");
        } catch (FileNotFoundException e) {
            System.out.println("File wasn't opened");
        }
        return myScan;
    }

    //Method to write to or create a file
    public void writeFile(String toEnter) {
        try {
            //Open File
            File Fileright = new File("stopwords.txt");
            Scanner fileScan = new Scanner(Fileright);

            String buffer = "";

            while(fileScan.hasNext())
            {
                buffer = buffer.concat("" + fileScan.next());
            }
            System.out.println("Buffer looks like this right now:" + buffer);

            //using newline character as a delimiter
            Pattern ptr = Pattern.compile("\n");

            //storing the string elements in array after splitting
            stopWordArray = ptr.split(buffer);

            System.out.println("This is the array not the buffer:");
            //Check
            for (int i = 0; i < stopWordArray.length-1; i++ ) {
                System.out.println(stopWordArray[i]+ " ");
            }

            //Create printwriter
            //PrintWriter myWriter = new PrintWriter(new FileOutputStream("stopwords.txt", true));

            //If word doesnt already exist, append it
            //myWriter.println(toEnter);

            //Close input stream
            //  myWriter.close();
        } catch (FileNotFoundException e) {//Catches the exception
            System.out.println("This was the error: " + e.getMessage());
            System.out.println("File wasn't found to write into");

        }

    }

    //Method to write to or create a file
    public void removeFromfile(String toRemove) {
        try {
            //Open File
            File Fileright = new File("stopwords.txt");

            //Create printwriter
            PrintWriter mywriter = new PrintWriter(Fileright);

            //Print job from parameter
            //mywriter.(toRemove);

            //Close input stream
            mywriter.close();
        } catch (FileNotFoundException e) {//Catches the exception
            System.out.println("This was the error: " + e.getMessage());
            System.out.println("File wasn't found to write into");

        }

    }

}
