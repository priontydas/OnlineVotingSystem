                                                                                                                                 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    static int voteCount = 0;
    static final int MAX_VOTES = 10;

    public static void main(String[] args) {

        JFrame frame = new JFrame("ONLINE VOTING SYSTEM");
        frame.setSize(850, 650);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // background color
        frame.getContentPane().setBackground(new Color(225, 240, 255));

        // title
        JLabel title = new JLabel("ONLINE VOTING SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 36));
        title.setForeground(new Color(70, 30, 120));
        title.setBounds(0, 40, 850, 50);
        frame.add(title);

        // subtitle
        JLabel subtitle = new JLabel("Cast Your Vote Wisely!", SwingConstants.CENTER);
        subtitle.setFont(new Font("Arial", Font.ITALIC, 20));
        subtitle.setForeground(new Color(80, 80, 130));
        subtitle.setBounds(0, 95, 850, 30);
        frame.add(subtitle);

        // name label & field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setBounds(180, 160, 100, 30);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(280, 160, 200, 30);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(nameField);

        // voter id label & field
        JLabel idLabel = new JLabel("Voter ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 18));
        idLabel.setBounds(180, 210, 100, 30);
        frame.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(280, 210, 200, 30);
        idField.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(idField);

        // choose party label
        JLabel chooseLabel = new JLabel("Select Your Party:");
        chooseLabel.setFont(new Font("Arial", Font.BOLD, 20));
        chooseLabel.setBounds(180, 270, 300, 30);
        frame.add(chooseLabel);

        Color bgColor = new Color(225, 240, 255);

        // radio buttons
        JRadioButton bnp = new JRadioButton("BNP");
        bnp.setBounds(200, 320, 150, 40);
        bnp.setBackground(bgColor);
        bnp.setFont(new Font("Arial", Font.PLAIN, 16));

        JRadioButton awami = new JRadioButton("Awami League");
        awami.setBounds(200, 380, 150, 40);
        awami.setBackground(bgColor);
        awami.setFont(new Font("Arial", Font.PLAIN, 16));

        JRadioButton ncp = new JRadioButton("NCP");
        ncp.setBounds(200, 440, 150, 40);
        ncp.setBackground(bgColor);
        ncp.setFont(new Font("Arial", Font.PLAIN, 16));

        ButtonGroup group = new ButtonGroup();
        group.add(bnp);
        group.add(awami);
        group.add(ncp);

        frame.add(bnp);
        frame.add(awami);
        frame.add(ncp);

        // buttons
        JButton submitBtn = new JButton("Submit Vote");
        submitBtn.setBounds(320, 520, 200, 40);
        submitBtn.setBackground(new Color(180, 210, 255));
        submitBtn.setForeground(new Color(50, 40, 100));
        submitBtn.setFont(new Font("Verdana", Font.BOLD, 16));
        frame.add(submitBtn);

        JButton resultBtn = new JButton("See Result");
        resultBtn.setBounds(320, 570, 200, 40);
        resultBtn.setBackground(new Color(255, 182, 193));
        resultBtn.setForeground(new Color(80, 30, 60));
        resultBtn.setFont(new Font("Verdana", Font.BOLD, 16));
        frame.add(resultBtn);

        // submit button action
        submitBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String voterId = idField.getText().trim();

            if (name.isEmpty() || voterId.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter both Name and Voter ID!");
                return;
            }

            if (!voterId.matches("\\d{12}")) {
                JOptionPane.showMessageDialog(frame, "Invalid ID!", "Invalid ID", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (voteCount >= MAX_VOTES) {
                JOptionPane.showMessageDialog(frame, "Voting limit reached (10 voters only)!", "Limit Reached", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!bnp.isSelected() && !awami.isSelected() && !ncp.isSelected()) {
                JOptionPane.showMessageDialog(frame, "Please select a party to vote!");
                return;
            }

            voteCount++;
            JOptionPane.showMessageDialog(frame, "Vote Submitted Successfully!\nThank you, " + name + "!");
            nameField.setText("");
            idField.setText("");
            group.clearSelection();
        });

        // result button action
        resultBtn.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "Voting ongoing...\nTotal Votes: " + voteCount + "/" + MAX_VOTES));

        frame.setVisible(true);
    }
}
