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
* How are we displaying results? In which class?
* GUI needs to be cleaned up, maybe code refactored, could delegate more functionality to fileComparison class?
* Filehandler is done and just needs code clean, functionality solid
* fileComparison needs the analysing driver code made /comments /cleaning
* buffermaker needs to be made - Currently empty
* Readme needs to be written
* Main is DONE
*
* Finished:
* STOPWORDS ADD WORD
*
*
* */