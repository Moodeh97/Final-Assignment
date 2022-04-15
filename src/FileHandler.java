import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class FileHandler {

    /* METHODS


    Filehandler
        sets globally accessible String variable Filename from parameter input
            Parameters:
                        String Filename - String parameter

    checkStop
        Checks if the string parameter inputted is present in the stopwords.txt file
            Parameters:
                        String toCheck - the word that is to be checked against the file

    writeFile
        A method to write a stopword to the stopwords.txt file
            Parameters:
                        String toEnter - a string of all the words to be entered into the file
*/

/* ATTRIBUTES

    String Filename - set in the constructor to be used by various methods

    String buffer - used in method writeFile as a way of gathering all words in stopwords.txt into one variable

    String[] stopWordArray - String buffer is converted to this array with class stringToArrayMaker

    String[] wordsToAdd - String toEnter is converted into this array with class stringToArrayMaker

    boolean alreadyThere - this bool holds the switch for whether or not the word being checked/inputted
                            already exists in the stopword file
*/

    //String attributes
    String Filename;
    String buffer;
    String[] stopWordArray;
    String[] wordsToAdd;

    //declaring these variables so they can be used
    File myFile;
    stringToArrayMaker converter = new stringToArrayMaker();

    boolean alreadyThere;

    //constructor
    public FileHandler(String Filename) {
        this.Filename = Filename;
    };

    //Method to check if a word is in the stopwords file already
    public boolean checkStop(String toCheck){
        try {
            alreadyThere = false;
            ;
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

            //Check that the words arent already in the file
            for (int i = 0; i < stopWordArray.length - 1; i++) {

                if (Objects.equals(toCheck, stopWordArray[i])) {
                    alreadyThere = true;
                }
            }

            if(alreadyThere) {
                return true;
                //If the word is IN STOPWORDS, RETURN TRUE
            }
            else
            {
                return false;
                //If the word is NOT IN STOPWORDS, RETURN FALSE

            }
        }
        catch (FileNotFoundException e)
        {//Catches the exception
            System.out.println("This was the error: " + e.getMessage());
            System.out.println("File wasn't found to write into");
            return false;
        }
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

                for (String s : wordsToAdd) {
                    if (stopWordArray[i].equals(s)) {
                        alreadyThere = true;
                        break;
                    }
                }
            }

            //If the words are not in stopwords, add them
            if(!alreadyThere) {

                //Create print writer
                PrintWriter myWriter = new PrintWriter(new FileOutputStream("stopwords.txt", true));

                //append it
                for (String s : wordsToAdd) {
                    myWriter.println(s + "");
                }
                //Close input stream
                 myWriter.close();
                return true;
            }

            //Else return that nothing was added
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



