package Vozniuk;

import javax.swing.*;
import java.awt.*;
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

    public CiscoBinaryGame(VozniukAccount vozniukAccount) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        addSetupPanel();
        addInstructionLabel();
        generateNumbersToConvert();
        addFirstNumLabel();
        addSecNumLabel();
        addThirdNumLabel();
        addFirstNumTextField();
        addSecNumTextField();
        addThirdNumTextField();
        addConsole();
        addTimerPanel();
        addCheckButton(vozniukAccount);
        setVisible(true);
    }

    private void addSetupPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(300, 10, 500, 80);
        panel.setBackground(Color.LIGHT_GRAY);
        addSetupLabel(panel);
        add(panel);
    }

    private void addSetupLabel(JPanel panel) {
        JLabel label = new JLabel("Set up new password");
        label.setBounds(50, 10, 400, 50);
        //label.setFont();
        panel.add(label);
    }

    private void addInstructionLabel() {
        JLabel label = new JLabel("To get new password convert from binary to decimal");
        label.setBounds(380, 80, 500, 80);
        //label.setFont();
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
    }

    private String generateNewPassword(VozniukAccount vozniukAccount) {
        String newPassword = String.valueOf(firstNumValue) + String.valueOf(secNumValue) + String.valueOf(thirdNumValue);
        vozniukAccount.setPassword(newPassword);
        return newPassword;
    }

    private void addFirstNumLabel() {
        firstNumLabel = new JLabel(firstNumber);
        firstNumLabel.setBounds(300, 180, 250, 50);
        //firstNumLabel.setFont();
        add(firstNumLabel);
    }

    private void addSecNumLabel() {
        secNumLabel = new JLabel(secondNumber);
        secNumLabel.setBounds(300, 240, 250, 50);
        //secNumLabel.setFont();
        add(secNumLabel);
    }

    private void addThirdNumLabel() {
        thirdNumLabel = new JLabel(thirdNumber);
        thirdNumLabel.setBounds(300, 300, 250, 50);
        //thirdNumLabel.setFont();
        add(thirdNumLabel);
    }

    private void addFirstNumTextField() {
        firstTextField = new JTextField();
        firstTextField.setBounds(600, 180, 150, 50);
        //firstTextField.setFont();
        add(firstTextField);
    }

    private void addSecNumTextField() {
        secTextField = new JTextField();
        secTextField.setBounds(600, 240, 150, 50);
        //secTextField.setFont();
        add(secTextField);
    }

    private void addThirdNumTextField() {
        thirdTextField = new JTextField();
        thirdTextField.setBounds(600, 300, 150, 50);
        //thirdTextField.setFont();
        add(thirdTextField);
    }

    private void addConsole() {
        JPanel console = new JPanel();
        console.setBackground(Color.GRAY);
        console.setBounds(0, 480, 650, 300);
        console.setLayout(null);
        addConsoleLabel(console);
        addConsoleTextLabel(console);
        add(console);
    }

    private void addConsoleLabel(JPanel console) {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 100, 30);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        JLabel label = new JLabel("Console");
        label.setBounds(0, 0, 100, 30);
        //label.setFont();
        panel.add(label);
        console.add(panel);
    }

    private void addConsoleTextLabel(JPanel console) {
        consoleText = new JLabel();
        consoleText.setBounds(0, 40, 620, 200);
        //consoleText.setFont();
        console.add(consoleText);
    }

    private void addTimerPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        panel.setBounds(650, 480, 440, 300);
        addTimerLabel(panel);
        addTimer(panel);
        add(panel);
    }

    private void addTimerLabel(JPanel panel) {
        JLabel label = new JLabel("Time left:");
        label.setBounds(30, 5, 250, 70);
        //label.setFont();
        panel.add(label);
    }

    private void addTimer(JPanel panel) {
        timeLabel = new JLabel();
        timeLabel.setBounds(30, 100, 250, 80);
        timeLabel.setFont(timeLabel.getFont().deriveFont(30.0f));
        timeRemaining = 10;
        timer = new Timer(1000, e -> {
            if (timeRemaining > 0) {
                timeRemaining--;
                int minutes = timeRemaining / 60;
                int seconds = timeRemaining % 60;
                timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
            } else {
                ((Timer) e.getSource()).stop();
                timeLabel.setText("Time is out!");
                timeLabel.setForeground(Color.RED);
                check.setVisible(false);
                consoleText.setText("Time is out. Press retry button to get new password");
                consoleText.setForeground(Color.RED);
                addRetryButton();
            }
        });

        panel.add(timeLabel);
        timer.setInitialDelay(0);
        timer.start();
    }

    private void addCheckButton(VozniukAccount vozniukAccount) {
        check = new JButton("Check");
        check.setBounds(800, 240, 150, 50);
        check.addActionListener(e -> {
            if (!answersCorrect()) {
                consoleText.setForeground(Color.RED);
                consoleText.setText("Your answer is incorrect");
            } else {
                consoleText.setForeground(Color.WHITE);
                consoleText.setText("Correct. Your new password is: " + generateNewPassword(vozniukAccount));
            }
        });
        add(check);
    }

    private void addRetryButton() {
        JButton retry = new JButton("Retry");
        retry.setBounds(800, 240, 150, 50);
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
            timeRemaining = 10;
            timer.start();
        });
        add(retry);
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
