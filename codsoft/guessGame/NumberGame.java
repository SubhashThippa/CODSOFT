package guessGame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;

public class NumberGame{

    private JFrame frame;
    private JTextField textField_N;
    private int minRange = 1;
    private int maxRange = 50;
    private int maxAttempts = 10;
    public int score = 0;

    private Random random = new Random();
    private int targetNumber;
    private int attempts;
    private JTextField textField_NA;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	NumberGame window = new NumberGame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public NumberGame() {
        initialize();
        generateNewNumber();
    }

    private void generateNewNumber() {
        targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
        attempts = 0;
        textField_N.setText("");
        updateAttemptsLeft();
    }
    private void updateAttemptsLeft() {
        int attemptsLeft = maxAttempts - attempts;
        textField_NA.setText(Integer.toString(attemptsLeft));
    }


    private void checkGuess(int userGuess) {
        attempts++;
        if (userGuess == targetNumber) {
            JOptionPane.showMessageDialog(frame, "Congratulations! You guessed the correct number in " + attempts + " attempts.");
            score++;
            generateNewNumber();
        } else if (userGuess < targetNumber) {
            JOptionPane.showMessageDialog(frame, "Too low! Try again. \n\n Attempts left: " + (maxAttempts - attempts));
        } else {
            JOptionPane.showMessageDialog(frame, "Too high! Try again. \n\n Attempts left: " + (maxAttempts - attempts));
        }

        if (attempts >= maxAttempts) {
            int option = JOptionPane.showConfirmDialog(frame, "Sorry, you've reached the maximum number of attempts. The correct number was: " + targetNumber + "\nDo you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                score = 0;
                generateNewNumber();
            } else {
                System.exit(0);
            }
        }
    }


    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(135, 206, 235));
        frame.setBounds(100, 100, 600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 0));
        panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel.setBounds(43, 45, 485, 90);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Number Guessing Game");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel.setBounds(113, 28, 336, 35);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Enter Number :");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel_1.setBounds(43, 154, 243, 44);
        frame.getContentPane().add(lblNewLabel_1);

        textField_N = new JTextField();
        textField_N.setFont(new Font("Tahoma", Font.BOLD, 17));
        textField_N.setBackground(new Color(255, 255, 0));
        textField_N.setBounds(43, 209, 485, 38);
        frame.getContentPane().add(textField_N);
        textField_N.setColumns(10);

        JToggleButton tglbtnGuess = new JToggleButton("Guess");
        tglbtnGuess.setBackground(new Color(255, 255, 0));
        tglbtnGuess.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        tglbtnGuess.setBounds(211, 343, 139, 44);
        frame.getContentPane().add(tglbtnGuess);
        
        JLabel lblAttemps = new JLabel("Maximum Number of Attemps :");
        lblAttemps.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        lblAttemps.setBounds(43, 281, 429, 38);
        frame.getContentPane().add(lblAttemps);
        
        textField_NA = new JTextField();
        textField_NA.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        textField_NA.setBackground(new Color(255, 255, 0));
        textField_NA.setBounds(315, 281, 35, 38);
        frame.getContentPane().add(textField_NA);
        textField_NA.setColumns(10);
        
        tglbtnGuess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int userGuess = Integer.parseInt(textField_N.getText());
                    checkGuess(userGuess);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input! Enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
