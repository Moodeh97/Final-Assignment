
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Desktop;

public class GUI extends Component implements ActionListener {

    //Class Attributes and instantiation of java swing frame
    JLabel label1,label2,label3;
    JButton fileButton1,fileButton2,addWordButton, showWordButton, compareButton;
    JPanel panel1;
    String[] stopWords;

    //creating scan objects for the two files
    Scanner myscan1 = null;
    Scanner myscan2 = null;

    //Instantiation of file choosers so the GUI can use a browser
    JFileChooser fc1 = new JFileChooser();
    JFileChooser fc2 = new JFileChooser();



    public GUI() {

        //Creating all buttons, panels, and the input box
        JButton fileButton1 = new JButton("Choose File 1");
        JButton fileButton2 = new JButton("Choose File 2");
        JButton addWordButton = new JButton("Add stop words");
        JButton showWordButton = new JButton("Show stop words");
        JButton compareButton = new JButton("Compare The Files");

        //Creating panel and label
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();



        //Creating a frame and setting Frame attributes
        JFrame frame1 = new JFrame();
        frame1.setVisible(true); //Lets it be seen
        frame1.setTitle("File Comparison"); //Names the window
        frame1.setSize(500, 500); //Sizes the window
        frame1.setLocationRelativeTo(null); //Centres the window on the screen
        frame1.add(panel1);


        //Adding file buttons to panel
        panel1.add(fileButton1);
        panel1.add(fileButton2);
        panel1.add(compareButton);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);



        panel1.setVisible(true);
        
        //Adding stopword buttons to panel
        panel1.add(addWordButton);
        panel1.add(showWordButton);

        //Methods

        //Action listener for button 1, which chooses the first file and implements scanner 1
        fileButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                int returnVal = fc1.showOpenDialog(frame1);
                File chosenfile1 = fc1.getSelectedFile();

                if(chosenfile1 != null) {
                    FileHandler fh1 = new FileHandler(chosenfile1.getAbsolutePath());
                    label2.setText("File 1 is: " + chosenfile1.getName() + "\n");
                    Scanner myscan2 = fh1.openFile();
                }

            }
        });

        //Action listener for button 2, which chooses the second file and implements scanner 2
        fileButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                int returnVal = fc2.showOpenDialog(null);
                File chosenfile2 = fc2.getSelectedFile();

                if(chosenfile2 != null) {
                    FileHandler fh2 = new FileHandler(chosenfile2.getAbsolutePath());
                    label3.setText("File 2 is: " + chosenfile2.getName() + "\n");
                    Scanner myscan1 = fh2.openFile();
                }

            }
        });

        //Actually calls comparison and files etc
        compareButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {
                fileComparison comparEM = new fileComparison(myscan1, myscan2, stopWords);

            }
        });

        //Action listener for adding words to stopwords.txt
        addWordButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                String m = " ";
                m = JOptionPane.showInputDialog("Enter stop words seperated by a space");

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
                        JOptionPane.showMessageDialog(frame1, "One of these words exists, please enter a new word");
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

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
