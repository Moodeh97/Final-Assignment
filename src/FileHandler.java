import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileHandler {
    //Attributes
    String Filename; //Globally Accessible Variable
    String buffer;
    String[] stopWordArray;
    String[] wordsToAdd;

    File myFile;
    stringToArrayMaker converter = new stringToArrayMaker();

    int j;
    boolean alreadyThere;

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

    //Method to write to the stopwords file, also checks if the words being added already exist
    public boolean writeFile(String toEnter) {
        try {
            //Create buffer with nothing in it
            String buffer = "";

            //Open File and create scanner
            File Fileright = new File("stopwords.txt");
            Scanner fileScan = new Scanner(Fileright);


            //Add list of words from .txt to the buffer string
            while (fileScan.hasNext()) {
                buffer = buffer.concat(" " + fileScan.next());
            }



            //passing buffer and to enter to a converter class to return as a string array
            stopWordArray = converter.returnArray(buffer," ");
            wordsToAdd = converter.returnArray(toEnter," ");

            //Check that the words arent already in the file
            for (int i = 0; i < stopWordArray.length - 1; i++) {

                for(int j = 0; j < wordsToAdd.length;j++) {
                    if (stopWordArray[i].equals(wordsToAdd[j])) {
                        alreadyThere = true;
                        break;
                    }
                }
            }

            if(!alreadyThere) {

                //Create print writer
                PrintWriter myWriter = new PrintWriter(new FileOutputStream("stopwords.txt", true));

                //append it
                for(int i = 0; i < wordsToAdd.length; i++)
                {
                    myWriter.println(wordsToAdd[i] + "");
                }
                //Close input stream
                 myWriter.close();
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (FileNotFoundException e)
        {//Catches the exception
            System.out.println("This was the error: " + e.getMessage());
            System.out.println("File wasn't found to write into");
            return false;
        }

    }
}



