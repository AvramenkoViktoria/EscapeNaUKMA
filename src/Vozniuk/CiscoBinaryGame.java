package Vozniuk;

import Data.FileManager;
import Data.Test;
import Enums.Level;
import SceneObjects.Hearts;
import SuperSwing.GameOver;
import SuperSwing.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.SecureRandom;

public class CiscoBinaryGame extends JFrame {
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 800;
    private String firstNumber;
    private String secondNumber;
    private String thirdNumber;
    private int firstNumValue;
    private int secNumValue;
    private int thirdNumValue;
    private JTextField firstTextField;
    private JTextField secTextField;
    private JTextField thirdTextField;
    private JLabel consoleText;
    private JLabel firstNumLabel;
    private JLabel secNumLabel;
    private JLabel thirdNumLabel;
    private int timeRemaining;
    private JButton check;
    private JLabel timeLabel;
    private Timer timer;
    private boolean done;
    private Hearts hearts;

    public CiscoBinaryGame(VozniukAccount vozniukAccount) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        addHeartsPicture();
        addSetupPanel();
        addInstructionLabel();
        generateNumbersToConvert();
        addNumberPanel(vozniukAccount);
        addCiscoLabel();
        addConsole();
        addTimerPanel();
        addRetryButton();
        addCheckButton(vozniukAccount);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (done) {
                    dispose();
                    Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoomFrame.setVisible(true);
                }
            }
        });
    }


    private void addHeartsPicture() {
        switch (FileManager.user.getLevel()) {
            case CONTRACT:
                switch (FileManager.user.getHeartsNum()) {
                    case 3:
                        FileManager.user.setHeartsNum(3);
                        addHeartsPanel(new Hearts("Images\\Contract\\fullHearts.png", Level.CONTRACT, 800, 0));
                        break;
                    case 2:
                        FileManager.user.setHeartsNum(2);
                        addHeartsPanel(new Hearts("Images\\Contract\\twoHearts.png", Level.CONTRACT, 800, 0));
                        break;
                    case 1:
                        FileManager.user.setHeartsNum(1);
                        addHeartsPanel(new Hearts("Images\\Contract\\oneHeart.png", Level.CONTRACT, 800, 0));
                }
                break;
            case BUDGET:
                switch (FileManager.user.getHeartsNum()) {
                    case 2:
                        FileManager.user.setHeartsNum(2);
                        addHeartsPanel(new Hearts("Images\\Budget\\twoHearts.png", Level.BUDGET, 800, 0));
                        break;
                    case 1:
                        FileManager.user.setHeartsNum(1);
                        addHeartsPanel(new Hearts("Images\\Budget\\twoHearts.png", Level.BUDGET, 800, 0));
                }
                break;
            case GRANT:
                FileManager.user.setHeartsNum(1);
                addHeartsPanel(new Hearts("Images\\Grant\\oneHeart.png", Level.GRANT, 800, 0));
        }
    }



    private void addSetupPanel() {
        RoundedPanel panel = new RoundedPanel(null,30, new Color(10, 44, 86));
        panel.setBounds(300, 120, 500, 80);
        addSetupLabel(panel);
        add(panel);
    }

    private void addSetupLabel(JPanel panel) {
        JLabel label = new JLabel("Set up new password");
        label.setFont(new Font("Baskerville Old Face", Font.BOLD, 30)); // Example font settings
        label.setForeground(Color.WHITE);
        label.setBounds(115, 15, 270, 50);
        panel.add(label);
    }
    private void addCiscoLabel() {
        ImageIcon imageIcon = new ImageIcon("Images\\Binary.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(500, 90, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel label = new JLabel(scaledIcon);
        label.setBounds(300, 35, 500, 90);
        add(label);
    }

    private void addInstructionLabel() {
        JLabel label = new JLabel("To get new password convert from binary to decimal");
        label.setBounds(215, 200, 700, 80);
        label.setFont(new Font("Baskerville Old Face", Font.BOLD, 30)); // Example font settings
        add(label);
    }

    private String generateBinaryNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            int randomBit = random.nextInt(2);
            stringBuilder.append(randomBit);
        }

        return stringBuilder.toString();
    }

    private int convertFromBinaryToDecimal(String binaryNumber) {
        return Integer.parseInt(binaryNumber, 2);
    }

    private void generateNumbersToConvert() {
        firstNumber = generateBinaryNumber();
        secondNumber = generateBinaryNumber();
        thirdNumber = generateBinaryNumber();
        firstNumValue = convertFromBinaryToDecimal(firstNumber);
        secNumValue = convertFromBinaryToDecimal(secondNumber);
        thirdNumValue = convertFromBinaryToDecimal(thirdNumber);
        System.out.println(firstNumValue);
        System.out.println(secNumValue);
        System.out.println(thirdNumValue);
    }

    private String generateNewPassword(VozniukAccount vozniukAccount) {
        done = true;
        String newPassword = String.valueOf(firstNumValue) + String.valueOf(secNumValue) + String.valueOf(thirdNumValue);
        System.out.println(newPassword);
        vozniukAccount.setPassword(newPassword);
        return newPassword;
    }
    private void addNumberPanel(VozniukAccount vozniukAccount) {
        // Create a new JPanel
        JPanel numberPanel = new RoundedPanel(null, 30, new Color(10, 44, 86));

        // Set layout to null for absolute positioning
        numberPanel.setLayout(null);

        // Set the bounds of the panel
        numberPanel.setBounds(0, 280, 1085, 350); // Adjust the size and position as needed

        // Create and configure the first number label
        firstNumLabel = new JLabel(firstNumber);
        firstNumLabel.setBounds(300, 30, 250, 50);
        firstNumLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 30)); // Example font settings
        firstNumLabel.setForeground(Color.WHITE);
        numberPanel.add(firstNumLabel);

        // Create and configure the first text field
        firstTextField = new JTextField();
        firstTextField.setBounds(650, 30, 150, 50);
        firstTextField.setFont(new Font("Baskerville Old Face", Font.BOLD, 30)); // Example font settings
        numberPanel.add(firstTextField);

        // Create and configure the second number label
        secNumLabel = new JLabel(secondNumber);
        secNumLabel.setBounds(300, 100, 250, 50);
        secNumLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 30)); // Example font settings
        secNumLabel.setForeground(Color.WHITE);
        numberPanel.add(secNumLabel);

        // Create and configure the second text field
        secTextField = new JTextField();
        secTextField.setBounds(650, 100, 150, 50);
        secTextField.setFont(new Font("Baskerville Old Face", Font.BOLD, 30)); // Example font settings
        numberPanel.add(secTextField);

        // Create and configure the third number label
        thirdNumLabel = new JLabel(thirdNumber);
        thirdNumLabel.setBounds(300, 170, 250, 50);
        thirdNumLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 30)); // Example font settings
        thirdNumLabel.setForeground(Color.WHITE);
        numberPanel.add(thirdNumLabel);

        // Create and configure the third text field
        thirdTextField = new JTextField();
        thirdTextField.setBounds(650, 170, 150, 50);
        thirdTextField.setFont(new Font("Baskerville Old Face", Font.BOLD, 30)); // Example font settings
        numberPanel.add(thirdTextField);
        addCheckButton(vozniukAccount);
        addRetryButton();
        // Add the panel to the main frame
        add(numberPanel);
    }

    private void addConsole() {
        JPanel console = new JPanel();
        console.setBackground(new Color(0, 194, 235));
        console.setBounds(0, 660, 450, 300); // Ensure this size is sufficient
        console.setLayout(null); // Using null layout for absolute positioning
        addConsoleLabel(console);
        addConsoleTextLabel(console);
        add(console);

        // Ensure the console panel is refreshed to display added components
        console.revalidate();
        console.repaint();
    }

    private void addConsoleLabel(JPanel console) {
        JPanel panel = new RoundedPanel(null, 30, Color.WHITE);
        panel.setBounds(20, 30, 150, 50); // Adjust size and positioning as needed
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        JLabel label = new JLabel("Console");
        label.setBounds(15, 0, 150, 50); // Adjust size and positioning as needed
        label.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
        panel.add(label);
        console.add(panel);
    }

    private void addConsoleTextLabel(JPanel console) {
        consoleText = new JLabel("RESULT"); // Initial content for visibility
        consoleText.setBounds(200, 38, 430, 40); // Adjust size and positioning for better visibility
        consoleText.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
        consoleText.setForeground(Color.WHITE); // Ensure the text color contrasts with the background
        console.add(consoleText);
    }


    private void addTimerPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(10, 44, 86));
        panel.setLayout(null);
        panel.setBounds(450, 660, 700, 300);
        addCiscoPicture(panel);
        addTimerLabel(panel);
        addTimer(panel);
        add(panel);
    }

    private void addTimerLabel(JPanel panel) {
        JLabel label = new JLabel("Time left:");
        label.setForeground(Color.WHITE);
        label.setBounds(70, 20, 250, 70);
        label.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
        panel.add(label);
    }
    private void addTimer(JPanel panel) {
        timeLabel = new JLabel();
        timeLabel.setBounds(220, 15, 250, 80);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
        timeLabel.setFont(timeLabel.getFont().deriveFont(30.0f));
        timeRemaining = 120;
        timer = new Timer(1000, e -> {
            if (timeRemaining > 0) {
                timeRemaining--;
                int minutes = timeRemaining / 60;
                int seconds = timeRemaining % 60;
                timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
            } else if (!lost()){
                ((Timer) e.getSource()).stop();
                timeLabel.setText("Time is out!");
                timeLabel.setForeground(Color.RED);
                check.setVisible(false);
                consoleText.setText("Time is out. Press retry button to get new password");
                consoleText.setForeground(Color.RED);
                addRetryButton();
            } else {

                ((Timer) e.getSource()).stop();
                Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoom.ciscoBinaryGame.setVisible(false);
                Test.mainMenu.levelMenu.roomMenu.hall.thoughtCounter = 0;
                GameOver gameOver = new GameOver();
            }

        });

        panel.add(timeLabel);
        timer.setInitialDelay(0);
        timer.start();
    }

   private void addHeartsPanel(Hearts hearts) {
        if (this.hearts != null) {
            remove(this.hearts);
        }
        System.out.println(FileManager.user.getHeartsNum());
        System.out.println(hearts.getPath());
        this.hearts = hearts;
        add(hearts);
        hearts.revalidate();
        hearts.repaint();
        revalidate();
        repaint();
    }


 private boolean lost() {
        switch (FileManager.user.getLevel()) {
            case CONTRACT:
                switch (FileManager.user.getHeartsNum()) {
                    case 3:
                        FileManager.user.setHeartsNum(2);
                        addHeartsPanel(new Hearts("Images\\Contract\\twoHearts.png", Level.CONTRACT, 800, 0));
                        break;
                    case 2:
                        FileManager.user.setHeartsNum(1);
                        addHeartsPanel(new Hearts("Images\\Contract\\oneHeart.png", Level.CONTRACT, 800, 0));
                        break;
                    case 1:
                        return true;
                }
                break;
            case BUDGET:
                switch (FileManager.user.getHeartsNum()) {
                    case 2:
                        FileManager.user.setHeartsNum(1);
                        addHeartsPanel(new Hearts("Images\\Budget\\oneHeart.png", Level.BUDGET, 800, 0));
                        break;
                    case 1:
                        return true;
                }
                break;
            case GRANT:
                return true;
        }
        return false;
    }




    private void addCheckButton(VozniukAccount vozniukAccount) {
        check = new JButton("Check");
        check.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
        check.setBounds(350, 545, 150, 50);
        check.setContentAreaFilled(false); // Make the button background transparent
        check.setFocusPainted(false); // Remove focus painting
        check.setForeground(Color.WHITE); // Set text color to white
        check.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor on hover
        check.addActionListener(e -> {
            if (!answersCorrect()) {
                consoleText.setForeground(Color.WHITE);
                consoleText.setText("Your answer is incorrect");
            } else {
                check.setVisible(false);
                timer.stop();
                consoleText.setForeground(Color.WHITE);
                //consoleText.setText("Correct. Your new password is: " + generateNewPassword(vozniukAccount));
            }
        });
        add(check);
    }

    private void addRetryButton() {
        JButton retry = new JButton("Retry");
        retry.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
        retry.setBounds(600, 545, 150, 50);
        retry.setContentAreaFilled(false); // Make the button background transparent
        retry.setFocusPainted(false); // Remove focus painting
        retry.setForeground(Color.WHITE); // Set text color to white
        retry.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor on hover
        retry.addActionListener(e -> {
            retry.setVisible(false);
            check.setVisible(true);
            generateNumbersToConvert();
            firstNumLabel.setText(firstNumber);
            secNumLabel.setText(secondNumber);
            thirdNumLabel.setText(thirdNumber);
            repaint();
            consoleText.setText("");
            timeLabel.setForeground(Color.BLACK);
            timeRemaining = 120;
            timer.start();
        });
        add(retry);
    }
    private void addCiscoPicture(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon("Images\\CISCO.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(150, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel label = new JLabel(scaledIcon);
        label.setBounds(400, 15, 150, 80);
        panel.add(label);
    }

    private boolean answersCorrect() {
        try {
            if (firstNumValue == Integer.parseInt(firstTextField.getText()) &&
                    secNumValue == Integer.parseInt(secTextField.getText()) &&
                    thirdNumValue == Integer.parseInt(thirdTextField.getText()))
                return true;
        } catch (NumberFormatException ignored) {}
        return false;
    }
}
