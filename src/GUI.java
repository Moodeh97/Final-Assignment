
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

    //Class Attributes and instantiation of java swing frame
    JLabel label2, label3;
    JButton addFile1,addFile2, clearfiles, addWordButton, showWordButton, compareButton, removeResults, openResults;
    JPanel panel1,panel2,panel3;
    String[] stopWords;

    ArrayList<File> files = new ArrayList<File>();

    //creating scan objects for the two files
    String pathname1 = null;
    String pathname2 = null;

    //Instantiation of file choosers so the GUI can use a browser

    JFileChooser fc1 = new JFileChooser();

    Icon icon = null;
    JTabbedPane tabbedPane = new JTabbedPane();


    public GUI() {

        //Creating all buttons, panels, and the input box

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
        * Addfile1:
        */

        //Action listener for add file 1
        addFile1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                if(pathname1 == null){
                    int returnVal = fc1.showOpenDialog(frame1);
                    File chosenfile1 = fc1.getSelectedFile();
                    pathname1 = chosenfile1.getPath();
                    Area1.append("\n" + chosenfile1.getName());
                }
                else{JOptionPane.showMessageDialog(frame1,"This file has been chosen already");}
            }

        });

        //Action listener for add file 2
        addFile2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {
                if(pathname2 == null) {
                    int returnVal = fc1.showOpenDialog(frame1);
                    File chosenfile2 = fc1.getSelectedFile();
                    pathname2 = chosenfile2.getPath();
                    Area1.append("\n" + chosenfile2.getName());
                }
                else{JOptionPane.showMessageDialog(frame1,"This file has been chosen already");}
            }

        });


        //Actually calls comparison class
        compareButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                if(pathname1 != null && pathname2 != null) {
                    comparer thisCompares = null;
                    try {
                        thisCompares = new comparer(pathname1, pathname2);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    assert thisCompares != null;
                    try {
                        thisCompares.results();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame1,"You have not selected both files");
                }
            }
        });

        //Action listener for adding words to stopwords.txt
        addWordButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

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
                File sword = new File("stopwords.txt");
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
                File fileToClear = new File("C:\\Users\\daram\\OneDrive - Technological University Dublin\\Computer Science Year 2\\Semester 2\\OOP Java\\Labs\\Final Assignment\\results.txt");
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter(fileToClear);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                assert writer != null;
                writer.print("");
                writer.close();
            }
        });
        //Opens results.txt in the OS's default .txt application
        openResults.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {
                File sword = new File("results.txt");
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
