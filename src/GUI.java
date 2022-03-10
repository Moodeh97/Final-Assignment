
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

////////////////////////////////


public class GUI extends Component implements ActionListener {

    //Class Attributes and instantiation of java swing frame
    JLabel label1;
    JButton button1;
    JPanel panel1;
    JTextField input;

    JFileChooser fc1 = new JFileChooser();
    JFileChooser fc2 = new JFileChooser();


    public GUI() {


        JButton button1 = new JButton("Choose File 1");
        JButton button2 = new JButton("Choose File 2");
        JPanel panel1 = new JPanel();
        JTextField input = new JTextField(20);

        //Creating a frame and setting Frame attributes
        JFrame frame1 = new JFrame();
        frame1.setVisible(true);
        frame1.setTitle("File Comparison");
        frame1.setSize(200, 200);

        frame1.add(panel1);
        panel1.add(button1);
        panel1.add(button2);

        //Methods

        /*
        This method is the action performed by the button when the button is pressed.
        I have added an actionlistener to the button.
         */
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                int returnVal = fc1.showOpenDialog(null);
                File file = fc1.getSelectedFile();
                System.out.println("Opening: " + file.getName());
            }
        });

        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent a) {

                int returnVal = fc2.showOpenDialog(null);
                File file = fc2.getSelectedFile();
                System.out.println("Opening: " + file.getName());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
