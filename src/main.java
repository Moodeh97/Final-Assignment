import javax.swing.*;


public class main {
    public static void main(String[] arg) throws Exception{

        //Using UI manager and LookAndFeel to change swing to look like the host OS
        //Normal Swing looks 2010 at best, this is somewhat better
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        //Creating GUI
        GUI GUIdriver = new GUI();
    }
}
/*TO DO LIST / PICKUP LIST
* GUI needs to be cleaned up, maybe code refactored, could delegate more functionality to fileComparison class?
* Filehandler needs code clean,
* fileComparison needs the analysing driver code made /comments /cleaning
* Readme needs to be written
*
*
* Finished:
* STOPWORDS ADD WORD
* STOPWORDS SHOW FILE
* STRINGTOARRAYMAKER
* MAIN
* */