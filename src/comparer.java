import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class comparer {
    //This class compares the two MyScans sent into it and then outputs the results to a text file

    private static final DecimalFormat df = new DecimalFormat("0.00");

    double wordCounter1 = 0;
    double wordCounter2 = 0;
    double overlapCounter = 0;
    double overlapValueAmount = 0;
    double overlapPercentage = 0;
    Scanner myscan1 = null;
    Scanner myscan2 = null;

    String buffer = "";

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
        while (myscan1.hasNext()) {

            String words = myscan1.next();

            if (first.containsKey(words)){
                first.computeIfPresent(words, (k, v) -> v + 1);
                wordCounter1++;

            }
            else if (!newF.checkStop(words)){
                if(!first.containsKey(words)){

                    first.put(words,1);
                    wordCounter1++;

                }
            }


        }
        System.out.println(first);

        while (myscan2.hasNext()) {

            String words = myscan2.next();

            if (second.containsKey(words)){
                second.computeIfPresent(words, (k, v) -> v + 1);
                wordCounter2++;

            }
            else if (!newF.checkStop(words)){
                if(!second.containsKey(words)){

                    second.put(words,1);
                    wordCounter2++;

                }
            }


        }
        System.out.println(second + "\n");

        String[] firstArray = first.keySet().toArray(new String[0]);
        String[] secondArray = second.keySet().toArray(new String[0]);
        List<String> overlap = new ArrayList<String>();
            for(String s: firstArray){
            for(String x: secondArray)
            {
                if (Objects.equals(s, x))
                {
                    overlap.add(s);
                    overlapCounter++;
                }
            }
        }

        double wordOverall = wordCounter1+wordCounter2;
        for(String s: overlap){
            overlapValueAmount = overlapValueAmount + first.get(s);
            overlapValueAmount = overlapValueAmount + second.get(s);
        }
        overlapPercentage = ((overlapValueAmount/wordOverall)*100);

        try {
            PrintWriter myWriter = new PrintWriter(new FileOutputStream("results.txt", true));
            myWriter.println("There is this many (non-stopword) words  in both files: " + (wordCounter1+wordCounter2));
            myWriter.println("Therfore out of all words in both files, this many overlapping words there are: " + (overlapValueAmount));
            myWriter.println("So the percentage similarity they share is: " + df.format(overlapPercentage));

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


            myWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        }






}
