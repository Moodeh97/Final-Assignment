import java.util.regex.Pattern;

//This class contains the repeated logic to convert a string to an array of the words within it
//It takes a string and its delimiter

public class stringToArrayMaker {
    String m;
    String delimiter;
    String[] toReturn;

    public String[] returnArray(String m, String delimiter){
        this.m = m;
        this.delimiter = delimiter;

        Pattern ptr = Pattern.compile(this.delimiter);
        toReturn = ptr.split(this.m);

        return toReturn;
    }

}
