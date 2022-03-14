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

}
