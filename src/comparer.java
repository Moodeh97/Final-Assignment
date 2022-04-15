import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class comparer {
    //This class compares the two MyScans sent into it and then outputs the results to a text file
/*
    comparer
        Constructor which takes 2 filenames as inputs, and then declares fileobjects and then scanners for each
            Parameters:
                String pathname1/2 - hold the pathnames for the two files to be compare

    Results
        compares the two files by adding them to hash tables
            Parameters:
                None

    writetoFile
        A method to calculate some and write all results to a text file
            Parameters:
                None
*/

    /* ATTRIBUTES

    double wordCounter1 / wordCounter2 - holds word count from both files (not including stopwords)

    double overlapCounter = 0; - how many words pop up in both

    double overlapValueAmount = 0; - Of those words in both, what is the total they all pop up
    double overlapPercentage = 0; - Compared to all words in both files, how many of them as a percentage of total

    Scanners for files to be read
        Scanner myscan1 = null;
        Scanner myscan2 = null;


    Hashmaps to hold the words and amounts from both files
        HashMap<String,Integer> first = new HashMap<>();
        HashMap<String,Integer> second = new HashMap<>();
    */

    private static final DecimalFormat df = new DecimalFormat("0.00");

    double wordCounter1 = 0;
    double wordCounter2 = 0;
    double overlapCounter = 0;
    double overlapValueAmount = 0;
    double overlapPercentage = 0;
    Scanner myscan1 = null;
    Scanner myscan2 = null;

    File file1;
    File file2;
    FileHandler newF = new FileHandler("stop.txt");

    HashMap<String,Integer> first = new HashMap<>();
    HashMap<String,Integer> second = new HashMap<>();


    public comparer(String pathname1, String pathname2) throws FileNotFoundException {

        File file1 = new File(pathname1);
        File file2 = new File(pathname2);

        this.myscan1 = new Scanner(file1);
        this.myscan2 = new Scanner(file2);
    }



    public void results() throws FileNotFoundException {

        //While there's another word in the file
        while (myscan1.hasNext()) {

            //put word into string words
            String words = myscan1.next();

            //if hashmap already contains the word
            if (first.containsKey(words)) {
                first.computeIfPresent(words, (k, v) -> v + 1); //increment key value
                wordCounter1++; //increment counter

                //Else if word not in hashmap and not in stopwords
            } else if (!newF.checkStop(words)) {
                if (!first.containsKey(words)) {

                    first.put(words, 1); //add it to hashmap
                    wordCounter1++; //increment counter

                }
            }


        }
        //While there's another word in the file
        while (myscan2.hasNext()) {

            //put word into string words
            String words = myscan2.next();

            //if hashmap already contains the word
            if (second.containsKey(words)) {
                second.computeIfPresent(words, (k, v) -> v + 1);//increment key value
                wordCounter2++;//increment counter

                //Else if word not in hashmap and not in stopwords
            } else if (!newF.checkStop(words)) {
                if (!second.containsKey(words)) {

                    second.put(words, 1);//add it to hashmap
                    wordCounter2++;//increment counter

                }
            }


        }
    }

        public void writeToFile(){

        //Convert all keys in both hashmaps to an array
        String[] firstArray = first.keySet().toArray(new String[0]);
        String[] secondArray = second.keySet().toArray(new String[0]);

        //New arraylist to hold common words
        List<String> overlap = new ArrayList<String>();

        //iterates through both arrays of words
            for(String s: firstArray) {
                for (String x : secondArray) {
                    //if words are equal
                    if (Objects.equals(s, x)) {
                        //adds to arraylist
                        overlap.add(s);
                        overlapCounter++; //increments counter
                    }
                }
            }

            //variable to hold amount of non stop word words in both files
        double wordOverall = wordCounter1+wordCounter2;

            //finds how many non stop word words there are
        for(String s: overlap){
            overlapValueAmount = overlapValueAmount + first.get(s);
            overlapValueAmount = overlapValueAmount + second.get(s);
        }

        //calculate percentage of all (non stop word) words the overlapping words are
        overlapPercentage = ((overlapValueAmount/wordOverall)*100);

        try {

            //Print results to a file
            PrintWriter myWriter = new PrintWriter(new FileOutputStream("results.txt", true));
            myWriter.println("There is this many (non-stopword) words  in both files: " + (wordCounter1+wordCounter2));
            myWriter.println("Therfore out of all words in both files, there are this many overlapping words: " + (overlapValueAmount));
            myWriter.println("So the percentage similarity they share is: " + df.format(overlapPercentage) + "%");


            //If statements that check percentage overlap and declare a result in file
            if(overlapPercentage < 25)
            {
                myWriter.println("Finally: The Files are not similar at all");
            }
            else if(overlapPercentage > 25 && overlapPercentage < 50)
            {
                myWriter.println("Finally: The Files are slightly similar");
            }
            else if(overlapPercentage > 50 && overlapPercentage < 75)
            {
                myWriter.println("Finally: The Files are quite similar");
            }
            else if(overlapPercentage > 75 && overlapPercentage < 100)
            {
                myWriter.println("Finally: The Files are very similar");
            }
             else if(overlapPercentage == 100)
             {
                 myWriter.println("Finally: These are identical (or the same file)");
             }


             //Close input stream
            myWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }






}
