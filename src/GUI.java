
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Desktop;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class GUI extends JFrame implements ActionListener {

    //Class Attributes and instantiation of java swing frame
    JLabel label2, label3, label4, labelPanel;
    JButton addFileButton, addWordButton, showWordButton, compareButton;
    JPanel panel1,panel2,panel3;
    String[] stopWords;

    //creating scan objects for the two files
    Scanner myscan = null;

    //Instantiation of file choosers so the GUI can use a browser
    JFileChooser fc1 = new JFileChooser();

    Icon icon = null;
    JTabbedPane tabbedPane = new JTabbedPane();


    public GUI() {

        //Creating all buttons, panels, and the input box

        //Buttons in first tab
        JButton addFileButton = new JButton("Choose a file");

        //Buttons in second tab
        JButton addWordButton = new JButton("Add Stop words");
        JButton showWordButton = new JButton("Show Stop words");
        JButton compareButton = new JButton("Compare The Files");

        //Buttons in second tab
        JButton removeResults = new JButton("Wipe all Results");

        //Creating panel and label
        JPanel panel1 = new JPanel();

        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JLabel label2 = new JLabel("",SwingConstants.CENTER);

        JTabbedPane tp=new JTabbedPane();
        tp.setBounds(50,50,200,200);
        tp.add("Files",panel1);
        tp.add("Stop Words",panel2);
        tp.add("Results",panel3);

        //Creating a frame and setting Frame attributes
        JFrame frame1 = new JFrame();
        frame1.setVisible(true); //Lets it be seen
        frame1.setTitle("File Comparison"); //Names the window
        frame1.setSize(450, 250); //Sizes the window
        frame1.setLocationRelativeTo(null); //Centres the window on the screen
        frame1.add(tp);
        frame1.setResizable(false);

        //Adding file buttons to panel
        panel1.add(addFileButton);
        panel1.add(compareButton);
        panel1.add(label2);
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));

        //Adding stopword buttons to panel
        panel2.add(addWordButton);
        panel2.add(showWordButton);
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));

        panel3.add(removeResults);
        ///////////////////////////////////////////////
        //////////////////////////////////////////////

        //From here I implement the action listeners for all buttons

        //Action listener for add file button which inputs the filepath into an array
        addFileButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                int returnVal = fc1.showOpenDialog(frame1);
                File chosenfile1 = fc1.getSelectedFile();

                if(chosenfile1 != null) {
                    FileHandler fh = new FileHandler(chosenfile1.getAbsolutePath());
                    label2.setText(label2.getText() + "<html><br/></html>" + chosenfile1.getAbsolutePath());

                }

            }
        });

        //Actually calls comparison and files etc
        compareButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

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

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
