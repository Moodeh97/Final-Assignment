import javax.swing.*;

//Main class
public class control {
    public static void main(String[] arg) throws Exception{

        //Using UI manager and LookAndFeel to change swing to look like the host OS
        //Normal Swing looks 2010 at best, this is somewhat better
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        //Driving the UI
        GUI GUIdriver = new GUI();
    }
}
