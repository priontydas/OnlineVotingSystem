# VotingSystem
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("ONLINE VOTING SYSTEM"); 
        frame.setSize(800, 500);             
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(240, 248, 255)); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);   

        // JLabel  title দেখানো
        JLabel label = new JLabel("ONLINE VOTING SYSTEM", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setBounds(0, 50, 800, 50); 
        frame.add(label);

        frame.setVisible(true);              
    }
}

