import java.util.regex.Pattern;

//This class contains the  logic to convert a string to an array of the words within the string
//It takes a string and its delimiter

/* METHODS

returnArray, which returns a String array to whatever called it
    Parameters:
                String toBeConverted -  The input string
                String delimiter - the delimiter that you want the string seperated by
 */

/* ATTRIBUTES

String toBeConverted - takes the input from parameter of same name
String  delimiter - takes the input from parameter of same name
String Array toReturn - holds the converted string in order to return it
 */

public class stringToArrayMaker {
    String toBeConverted;
    String delimiter;
    String[] toReturn;

    public String[] returnArray(String toBeConverted, String delimiter){
        this.toBeConverted = toBeConverted;
        this.delimiter = delimiter;

        Pattern ptr = Pattern.compile(this.delimiter);
        toReturn = ptr.split(this.toBeConverted);

        return toReturn;
    }

}
