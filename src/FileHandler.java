import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    public void openFile() {
        myFile = new File(Filename);

        try
        {
            Scanner myScan = new Scanner(myFile);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File wasn't opened");
        }
    }

    //Method to read a line from a filw
    public String readLine() {
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

        return line;
    }


    //Method to write to file
    public void writeFile(String toEnter){
        try
        {
            PrintWriter mywriter = new PrintWriter(myFile);
            mywriter.println(toEnter);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("This was the error: " +e.getMessage());
            System.out.println("File wasn't found to write into");

        }

    }


    //Getters and setters

}
