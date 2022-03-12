
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.regex.Pattern;

////////////////////////////////


public class GUI extends Component implements ActionListener {

    //Class Attributes and instantiation of java swing frame
    JLabel label1, label2, label3;
    JButton button1,button2,button3, comparebutton;
    JPanel panel1;
    String[] stopWords;



    //Instantiation of file choosers so the GUI can use a browser
    JFileChooser fc1 = new JFileChooser();
    JFileChooser fc2 = new JFileChooser();


    public GUI() {

        //Creating all buttons, panels, and the input box
        JButton button1 = new JButton("Choose File 1");
        JButton button2 = new JButton("Choose File 2");
        JButton button3 = new JButton("Stop words");
        JButton comparebutton = new JButton("Compare The Files");

        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();


        //Creating a frame and setting Frame attributes
        JFrame frame1 = new JFrame();

        frame1.setVisible(true); //Lets it be seen
        frame1.setTitle("File Comparison"); //Names the window
        frame1.setSize(300, 200); //Sizes the window
        frame1.setLocationRelativeTo(null); //Centres the window on the screen
        frame1.add(panel1);

        //Adding elements to panel
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(comparebutton);

        Scanner myscan1 = null;
        Scanner myscan2 = null;

        //Methods

        //Action listener for button 1, which chooses the first file and implements scanner 1
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                int returnVal = fc1.showOpenDialog(null);
                File chosenfile1 = fc1.getSelectedFile();

                    FileHandler fh1 = new FileHandler(chosenfile1.getAbsolutePath());
                    label2.setText("File 1 is: " + chosenfile1.getName() + "\n");
                    Scanner myscan2 = fh1.openFile();
                }
        });

        //Action listener for button 2, which chooses the second file and implements scanner 2
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                int returnVal = fc2.showOpenDialog(null);
                File chosenfile2 = fc2.getSelectedFile();
                FileHandler fh2 = new FileHandler(chosenfile2.getAbsolutePath());
                label3.setText("File 2 is: " + chosenfile2.getName()+"\n");
                Scanner myscan1 = fh2.openFile();

            }
        });

        //Opens a dialog box where stop words can be implemented
        button3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {


                String m = JOptionPane.showInputDialog("Enter stop words seperated by a comma (,)");
                System.out.println(m);

                //parsing white space as a parameter
                Pattern ptr = Pattern.compile(",");
                //storing the stop words in array after splitting from user input
                stopWords = ptr.split(m);
                label1.setText("\nYour Stop words are: " + m +"\n");


            }
        });

        //Actually calls comparison and files etc
        comparebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {
            fileComparison comparEM = new fileComparison(myscan1, myscan2, stopWords);

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
