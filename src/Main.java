import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class Main {

    static int voteCount = 0;
    static final int MAX_VOTES = 10;

    static int bnpVotes = 0;
    static int awamiVotes = 0;
    static int ncpVotes = 0;

    static HashSet<String> usedIds = new HashSet<>();

    public static void main(String[] args) {

        JFrame frame = new JFrame("ONLINE VOTING SYSTEM!");
        frame.setSize(850, 670);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(225, 240, 255));

        JLabel title = new JLabel("ONLINE VOTING SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 36));
        title.setForeground(new Color(70, 30, 120));
        title.setBounds(0, 40, 850, 50);
        frame.add(title);

        JLabel subtitle = new JLabel("Cast Your Vote Wisely!", SwingConstants.CENTER);
        subtitle.setFont(new Font("Arial", Font.ITALIC, 20));
        subtitle.setBounds(0, 95, 850, 30);
        frame.add(subtitle);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setBounds(180, 160, 100, 30);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(280, 160, 200, 30);
        frame.add(nameField);

        JLabel idLabel = new JLabel("Voter ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 18));
        idLabel.setBounds(180, 210, 100, 30);
        frame.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(280, 210, 200, 30);
        frame.add(idField);

        JLabel chooseLabel = new JLabel("Select Your Party:");
        chooseLabel.setFont(new Font("Arial", Font.BOLD, 20));
        chooseLabel.setBounds(180, 275, 300, 30);
        frame.add(chooseLabel);

        Color bgColor = new Color(225, 240, 255);

        ImageIcon bnpLogo = new ImageIcon("src/images/bnp.png");
        ImageIcon awamiLogo = new ImageIcon("src/images/awamiLeague.png");
        ImageIcon ncpLogo = new ImageIcon("src/images/ncp.png");

        bnpLogo = new ImageIcon(bnpLogo.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        awamiLogo = new ImageIcon(awamiLogo.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ncpLogo = new ImageIcon(ncpLogo.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        JRadioButton bnp = new JRadioButton("BNP");
        bnp.setFont(new Font("Arial", Font.BOLD, 22));
        bnp.setBackground(bgColor);
        bnp.setBounds(200, 325, 80, 40);
        frame.add(bnp);

        JLabel bnpIconLabel = new JLabel(bnpLogo);
        bnpIconLabel.setBounds(290, 325, 40, 40);
        frame.add(bnpIconLabel);

        JRadioButton awami = new JRadioButton("Awami League");
        awami.setFont(new Font("Arial", Font.BOLD, 22));
        awami.setBackground(bgColor);
        awami.setBounds(200, 385, 180, 40);
        frame.add(awami);

        JLabel awamiIconLabel = new JLabel(awamiLogo);
        awamiIconLabel.setBounds(390, 385, 40, 40);
        frame.add(awamiIconLabel);

        JRadioButton ncp = new JRadioButton("NCP");
        ncp.setFont(new Font("Arial", Font.BOLD, 22));
        ncp.setBackground(bgColor);
        ncp.setBounds(200, 445, 70, 40);
        frame.add(ncp);

        JLabel ncpIconLabel = new JLabel(ncpLogo);
        ncpIconLabel.setBounds(280, 445, 40, 40);
        frame.add(ncpIconLabel);

        ButtonGroup group = new ButtonGroup();
        group.add(bnp);
        group.add(awami);
        group.add(ncp);

        // buttons 
        JButton submitBtn = new JButton("Submit Vote");
        submitBtn.setBounds(320, 520, 200, 40);
        submitBtn.setBackground(new Color(180, 210, 255));
        submitBtn.setForeground(new Color(50, 40, 100));
        frame.add(submitBtn);

        JButton resultBtn = new JButton("See Result");
        resultBtn.setBounds(320, 570, 200, 40);
        resultBtn.setBackground(new Color(255, 182, 193));
        resultBtn.setForeground(new Color(80, 30, 60));
        frame.add(resultBtn);

        // vote submit action
        submitBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String voterId = idField.getText().trim();

            if (name.isEmpty() || voterId.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Enter Name & ID!");
                return;
            }

            if (!voterId.matches("\\d{12}")) {
                JOptionPane.showMessageDialog(frame, "Invalid ID!");
                return;
            }

            if (usedIds.contains(voterId)) {
                JOptionPane.showMessageDialog(frame, "Already Voted!");
                return;
            }

            if (voteCount >= MAX_VOTES) {
                JOptionPane.showMessageDialog(frame, "Vote Closed!");
                return;
            }

            if (!bnp.isSelected() && !awami.isSelected() && !ncp.isSelected()) {
                JOptionPane.showMessageDialog(frame, "Please, Select a Party!");
                return;
            }

            usedIds.add(voterId);
            voteCount++;

            if (bnp.isSelected()) bnpVotes++;
            if (awami.isSelected()) awamiVotes++;
            if (ncp.isSelected()) ncpVotes++;

       
            JOptionPane.showMessageDialog(frame,"Vote Submitted Successfully!\nThank you, " + name + "!" );

            nameField.setText("");
            idField.setText("");
            group.clearSelection();

            // after 10 votes, disable Submit Vote, make See Result green
            if (voteCount == MAX_VOTES) {
                submitBtn.setEnabled(false);
                resultBtn.setBackground(new Color(0, 255, 0)); // green
                resultBtn.setOpaque(true);
                resultBtn.setEnabled(true); // keep clickable to show result
            }
        });

        // see result action
        resultBtn.addActionListener(e -> {
            if (voteCount < MAX_VOTES) {
                JOptionPane.showMessageDialog(frame, "Vote Ongoing " + voteCount + "/" + MAX_VOTES);
                return;
            }

            String winner;
            if (bnpVotes > awamiVotes && bnpVotes > ncpVotes) winner = "BNP wins";
            else if (awamiVotes > bnpVotes && awamiVotes > ncpVotes) winner = "Awami League wins";
            else if (ncpVotes > bnpVotes && ncpVotes > awamiVotes) winner = "NCP wins";
            else winner = "It's a Draw!";

            JOptionPane.showMessageDialog(frame, "Result:\nBNP: " + bnpVotes +
                    "\nAwami: " + awamiVotes +
                    "\nNCP: " + ncpVotes +
                    "\n\n" + winner);
        });

        frame.setVisible(true);
    }
}
