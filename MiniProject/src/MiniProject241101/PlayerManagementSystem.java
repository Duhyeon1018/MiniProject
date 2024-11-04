package MiniProject241101;

//PlayerManagementSystem.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class PlayerManagementSystem {
 private JFrame frame;
 private JTextField nameField, nicknameField, ageField, teamField;
 private JTextArea playerListArea;
 private PlayerDAO playerDAO;

 public PlayerManagementSystem() {
     playerDAO = new PlayerDAO(); // DAO 인스턴스 생성

     frame = new JFrame("Player Management System");
     frame.setSize(400, 400);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setLayout(new FlowLayout());

     // UI Components
     frame.add(new JLabel("Name:"));
     nameField = new JTextField(15);
     frame.add(nameField);

     frame.add(new JLabel("Nickname:"));
     nicknameField = new JTextField(15);
     frame.add(nicknameField);

     frame.add(new JLabel("Age:"));
     ageField = new JTextField(5);
     frame.add(ageField);

     frame.add(new JLabel("Team:"));
     teamField = new JTextField(15);
     frame.add(teamField);

     JButton registerButton = new JButton("Register Player");
     frame.add(registerButton);

     playerListArea = new JTextArea(10, 30);
     playerListArea.setEditable(false);
     frame.add(new JScrollPane(playerListArea));

     registerButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             registerPlayer();
         }
     });

     frame.setVisible(true);
 }

 private void registerPlayer() {
     String name = nameField.getText();
     String nickname = nicknameField.getText();
     String ageText = ageField.getText();
     String team = teamField.getText();

     if (name.isEmpty() || nickname.isEmpty() || ageText.isEmpty() || team.isEmpty()) {
         JOptionPane.showMessageDialog(frame, "All fields are required!");
         return;
     }

     try {
         int age = Integer.parseInt(ageText);
         Player player = new Player(name, nickname, age, team);
         playerDAO.insertPlayer(player);

         // Clear input fields
         nameField.setText("");
         nicknameField.setText("");
         ageField.setText("");
         teamField.setText("");

         // Update player list
         updatePlayerList();

     } catch (NumberFormatException ex) {
         JOptionPane.showMessageDialog(frame, "Age must be a number!");
     } catch (SQLException ex) {
         ex.printStackTrace();
         JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage());
     }
 }

 private void updatePlayerList() {
     playerListArea.setText("");
     try {
         List<Player> players = playerDAO.getAllPlayers();
         for (Player player : players) {
             playerListArea.append(player.toString() + "\n");
         }
     } catch (SQLException ex) {
         ex.printStackTrace();
     }
 }

 public static void main(String[] args) {
     SwingUtilities.invokeLater(PlayerManagementSystem::new);
 }
}
