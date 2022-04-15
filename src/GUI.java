
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.io.File;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Desktop;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.HashMap;
public class GUI extends JFrame implements ActionListener {


/* METHODS


    GUI
        Dives the UI
                Parameters:
                         None
*/

/* ATTRIBUTES

        String pathname1 / pathname2 - hold the pathnames for the two files to be compare
        String stopwords - holds the words to be added to the stopwords file

*/

    //Creating the various buttons used in UI
    JButton addFile1,addFile2, clearfiles, addWordButton, showWordButton, compareButton, removeResults, openResults;

    //Creating the panels needed for the tab system
    JPanel panel1,panel2,panel3;

    //a string to gather the words that the user wants to add to stopwords file
    String[] stopWords;

    //Create strings to hold the filenames
    String pathname1 = null;
    String pathname2 = null;

    //Instantiation of file chooser so the GUI can use a browser
    JFileChooser fc1 = new JFileChooser();

    //Create the tab object
    JTabbedPane tabbedPane = new JTabbedPane();


    public GUI() {

        //Creating all objects: buttons, panels, textarea,

        //Buttons in first tab
        JButton addFile1 = new JButton("Choose file 1");
        JButton addFile2 =  new JButton("Choose file 2");
        JButton clearfiles = new JButton("Clear files");

        //Buttons in second tab
        JButton addWordButton = new JButton("Add Stop words");
        JButton showWordButton = new JButton("Show Stop words");
        JButton compareButton = new JButton("Compare The Files");

        //Buttons in third tab
        JButton removeResults = new JButton("Wipe all previous Results");
        JButton openResults = new JButton("Open Results");

        //Creating panels and labels
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        //Textarea in first tab
        JTextArea Area1 = new JTextArea("The Files are:");
        Area1.setWrapStyleWord(true);
        Area1.setLineWrap(true);

        //Setting up the tab system
        JTabbedPane tp=new JTabbedPane();
        tp.setBounds(50,50,200,200);
        tp.add("Files",panel1);
        tp.add("Stop Words",panel2);
        tp.add("Results",panel3);

        //Creating a frame and setting Frame attributes
        JFrame frame1 = new JFrame();
        frame1.setVisible(true); //Lets it be seen
        frame1.setTitle("File Comparison"); //Names the window
        frame1.setSize(500, 500); //Sizes the window
        frame1.setLocationRelativeTo(null); //Centres the window on the screen
        frame1.add(tp);

        //Setting up Tab 1
        panel1.add(addFile1);
        panel1.add(addFile2);
        panel1.add(compareButton);
        panel1.add(clearfiles);
        panel1.add(Area1);
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));

        //Setting up panel 2
        panel2.add(addWordButton);
        panel2.add(showWordButton);
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));

        //Setting up panel 3
        panel3.add(removeResults);
        panel3.add(openResults);

        /*
        * From here I set up action buttons for all buttons in all three tabs
        * There are 8 separate buttons I implement an action for
        *
        * Addfile1: At core just gathers file path to be passed as parameter later
        *
        * Addfile1: At core just gathers file path to be passed as parameter later
        *
        * clearfiles: clears the chosen files so far and removes the text from the text area
        * allowing you to choose different files
        *
        * Comparebutton: calls the class comparer which actually processes the files
        *
        * addwordbutton: this button calls the file handler class and passes some words
        * as a parameter through a string
        *
        * showWordButton: a simple button which opens the stopwords.txt file in the OS's
        * default .txt file application
        *
        * removeresults: This button wipes all results from the text file in which they are stored
        * This is permanent
        *
        * openresults: a simple button which opens the results.txt file in the OS's
         * default .txt file application
         */

        //Action listener for addFile1
        addFile1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                if(pathname1 == null){ //if no file chosen so far
                    int returnVal = fc1.showOpenDialog(frame1); //open file browser
                    File chosenfile1 = fc1.getSelectedFile(); //Sets file object to file they choose
                    pathname1 = chosenfile1.getPath(); //Takes pathname from file
                    Area1.append("\n" + chosenfile1.getName()); //Shows what file was chosen
                }
                else{JOptionPane.showMessageDialog(frame1,"This file has been chosen already");} //If file already chosen then cannot be overwritten yet
            }

        });

        //Action listener for addFile2
        addFile2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {
                if(pathname2 == null) {//if no file chosen so far
                    int returnVal = fc1.showOpenDialog(frame1);//open file browser
                    File chosenfile2 = fc1.getSelectedFile();//Sets file object to file they choose
                    pathname2 = chosenfile2.getPath();//Takes pathname from file
                    Area1.append("\n" + chosenfile2.getName()); //Shows what file was chosen
                }
                else{JOptionPane.showMessageDialog(frame1,"This file has been chosen already");}//If file already chosen then cannot be overwritten yet
            }

        });


        //Calls comaparer class (after error checking) which
        //actually compares the two files chosen by the user
        compareButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                if(pathname1 != null && pathname2 != null) { //If both files have been picekd
                    comparer thisCompares = null;

                    //Try create comparer object
                    try {thisCompares = new comparer(pathname1, pathname2);}
                    catch (FileNotFoundException e) {e.printStackTrace();
                    }

                    //Use method results in comparer class
                    assert thisCompares != null;
                    try {
                        thisCompares.results();
                        thisCompares.writeToFile();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame1,"You have not selected both files"); //Else if both files not chosen
                }
            }
        });

        //Action listener for adding words to stopwords.txt
        addWordButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                //Getting the input from usewr
                String m = " ";
                m = JOptionPane.showInputDialog("Enter stop words separated by a space");

                //If string isn't empty, send it to filehandler to add
                if (m != null) {
                    FileHandler stopFh = new FileHandler("stopwords.txt");
                    boolean addedOrNo = stopFh.writeFile(m); //boolean indicates if anything was added

                    //If something was added display message
                    if(addedOrNo) {
                        JOptionPane.showMessageDialog(frame1, "These stop words were added!: "+m);
                    }
                    //If nothing was added display this message
                    if(!addedOrNo){
                        JOptionPane.showMessageDialog(frame1, "One of these words already exists, please enter new words");
                    }

                }

                else //tell user nothing was added
                {
                    JOptionPane.showMessageDialog(frame1, "No stop words were added!");

                }
            }
        });


        //Opens stopwords.txt in the OS's default .txt application
        showWordButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {
                //Create file object
                File sword = new File("stopwords.txt");

                //Try open file
                try {
                    Desktop.getDesktop().open(sword);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame1,"Stopwords cannot be opened at this time");
                }
            }
        });

        //Clears the results file
        removeResults.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                //new file object with results txt file
                File fileToClear = new File("results.txt");
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter(fileToClear); //create printwriter
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                assert writer != null;

                //Print null to the txt file and cl;ose
                writer.print("");
                writer.close();
            }
        });
        //Opens results.txt in the OS's default .txt application
        openResults.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                //Create file object
                File sword = new File("results.txt");

                //Try open file
                try {
                    Desktop.getDesktop().open(sword);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame1,"Results cannot be opened at this time");
                }
            }
        });

        //Clears files and area
        clearfiles.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                //Removes text from tab 1
                //sets pathnames to null
                Area1.setText("The Files are:");
                pathname1 = null;
                pathname2 = null;
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
