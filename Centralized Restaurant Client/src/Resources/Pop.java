package Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pop extends JFrame implements ActionListener {
    public Pop(String msg)
    {
        // create a frame
        setTitle("Message Window");
        JPanel content =  new JPanel();
        content.setLayout(new BorderLayout());
        // create a label
        JLabel str = new JLabel(msg);
        JPanel align =  new JPanel(new FlowLayout(FlowLayout.CENTER));
        align.add(str);
        // create a panel
        JPanel command = new JPanel(new FlowLayout(FlowLayout.CENTER));
        content.add(align,BorderLayout.CENTER);
        // create a button
        JButton b = new JButton("OK");
        // add action listener
        b.addActionListener(this);
        command.add(b);
        content.add(command,BorderLayout.SOUTH);
        setContentPane(content);
        setSize(150,150);
        setVisible(true);
    }

    // if the button is pressed
    public void actionPerformed(ActionEvent e)
    {
        setVisible(false); //you can't see me!
        dispose(); //Destroy the JFrame object
    }
}
