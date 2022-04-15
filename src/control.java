import javax.swing.*;


public class control {
    public static void main(String[] arg) throws Exception{

        //Using UI manager and LookAndFeel to change swing to look like the host OS
        //Normal Swing looks 2010 at best, this is somewhat better
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        //Driving Object
        GUI GUIdriver = new GUI();
    }
}
/*TO DO LIST / PICKUP LIST
*
*
* Finished:
* STOPWORDS ADD WORD
* STOPWORDS SHOW FILE
* STRINGTOARRAYMAKER
* MAIN
* */