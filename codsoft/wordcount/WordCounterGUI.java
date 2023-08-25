package wordcount;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCounterGUI {

    private JFrame frame;
    private JTextArea textField;
    private JTextField textField_1;

    private static final Set<String> COMMON_WORDS = new HashSet<>(Arrays.asList(
            "the", "and", "is", "of", "in", "to", "a", "for", "with", "on", "as", "an", "if", "that", "this"));

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                WordCounterGUI window = new WordCounterGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @wbp.parser.entryPoint
     */
    public WordCounterGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 228, 196));
        frame.setBounds(100, 100, 600, 670);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.controlHighlight);
        panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel.setBounds(63, 50, 439, 79);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("WORD COUNTER");
        lblNewLabel.setBounds(110, 24, 300, 33);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("TEXT :");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel_1.setBounds(63, 161, 161, 34);
        frame.getContentPane().add(lblNewLabel_1);

        textField = new JTextArea();
        textField.setBounds(63, 218, 439, 143);
        frame.getContentPane().add(textField);

        JLabel lblNewLabel_2 = new JLabel("FILE PATH :");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel_2.setBounds(63, 387, 172, 34);
        frame.getContentPane().add(lblNewLabel_2);

        textField_1 = new JTextField();
        textField_1.setBackground(new Color(255, 255, 255));
        textField_1.setBounds(63, 444, 439, 61);
        frame.getContentPane().add(textField_1);

        JButton countButton = new JButton("COUNT");
        countButton.setBackground(SystemColor.controlHighlight);
        countButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        countButton.setBounds(189, 543, 179, 45);
        frame.getContentPane().add(countButton);

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                String filePath = textField_1.getText();
                String text;

                if (!inputText.isEmpty()) {
                    text = inputText;
                } else if (!filePath.isEmpty()) {
                    try {
                        text = readTextFromFile(filePath);
                    } catch (IOException ex) {
                        text = "";
                        JOptionPane.showMessageDialog(frame, "Error reading the file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please provide either text or file path.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String[] words = text.split("[\\s\\p{Punct}]+");
                java.util.List<String> validWords = new ArrayList<>();

                for (String word : words) {
                    if (!word.isEmpty() && !COMMON_WORDS.contains(word.toLowerCase())) {
                        validWords.add(word.toLowerCase());
                    }
                }

                int wordCount = validWords.size();

                String result = "Total words: " + wordCount + "\n";
                result += "Number of unique words: " + getUniqueWordCount(validWords) + "\n\n";
                result += "Word Frequency:\n";

                Map<String, Integer> wordFrequency = new HashMap<>();
                for (String word : validWords) {
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }

                for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                    result += entry.getKey() + ": " + entry.getValue() + "\n";
                }

                JOptionPane.showMessageDialog(frame, result, "Word Count and Frequency", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private String readTextFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private int getUniqueWordCount(java.util.List<String> words) {
        Set<String> uniqueWords = new HashSet<>(words);
        return uniqueWords.size();
    }
}
