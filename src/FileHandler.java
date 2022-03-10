import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler
{
    //Attributes
    String Filename; //Globally Accessible Variable
    File myFile;

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
/*
    //Method to read a line from a fils
    public void wordAnalyzer() {
        String line = "";

        //Using a try except just in case the file isn't found, if it isn't found then it goes to
        //The catch prints out the error message
        try
        {
            Scanner myScanner = new Scanner(myFile);
            line = myScanner.nextLine();

        }
        catch (FileNotFoundException e)
        {//Returns the error specifically to the file
            System.out.println("File Not Found:" + e.getMessage());
        }


    }*/

    //Getters and setters
}
