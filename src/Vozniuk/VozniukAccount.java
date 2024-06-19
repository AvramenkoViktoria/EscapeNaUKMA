package Vozniuk;

import Data.Test;
import SuperSwing.GameOver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.SecureRandom;
import java.util.Arrays;

public class VozniukAccount extends JFrame {
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 800;
    private String password;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JButton changePasswordButton;
    public DoorBase base;

    public VozniukAccount() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(10, 44, 86));
        setTitle("Cisco account");
        generatePassword();
        ImageIcon icon = new ImageIcon("Images\\VozniukA.jpg");
        Image image = icon.getImage();
        setIconImage(image);
        addAccountPicture();
        addCiscoPicture();
        addAccountName();
        addPasswordField();
        addPasswordLabel();
        addSubmitButton();
        addChangePasswordButton();
        setVisible(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                passwordLabel.setText("password");
                passwordLabel.setForeground(Color.BLACK);
                Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoomFrame.setVisible(true);
            }
        });
    }

    private void addAccountPicture() {
        ImageIcon imageIcon = new ImageIcon("Images\\VozniukR.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel label = new JLabel(scaledIcon);
        label.setBounds(225, 100, 300, 300);
        add(label);
    }

    private void addCiscoPicture() {
        ImageIcon imageIcon = new ImageIcon("Images\\CISCO.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(300, 160, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel label = new JLabel(scaledIcon);
        label.setBounds(575, 50, 300, 300);
        add(label);
    }

    private void addAccountName() {
        JLabel name = new JLabel("yarik_vznk");
        name.setForeground(Color.WHITE); // Set text color to white
        name.setFont(new Font("Baskerville Old Face", Font.BOLD, 20)); // Example font settings
        name.setBounds(335, 410, 110, 40);
        //name.setFont();
        add(name);
    }

    private void addPasswordField() {
        passwordField = new JPasswordField();
        passwordField.setBounds(600, 300, 250, 40);
        passwordField.setFont(new Font("Baskerville Old Face", Font.BOLD, 16)); // Example font settings
        passwordField.setEchoChar('*');
        //passwordField.setFont();
        add(passwordField);
    }

    private void addPasswordLabel() {
        passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setForeground(Color.WHITE); // Set text color to white
        passwordLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 16)); // Example font settings
        passwordLabel.setBounds(600, 365, 100, 15);
        //passwordLabel.setFont();
        add(passwordLabel);
    }

    private void addSubmitButton() {
        JButton submit = new JButton("SUBMIT");
        submit.setFont(new Font("Baskerville Old Face", Font.BOLD, 16)); // Example font settings
        submit.setContentAreaFilled(false); // Make the button background transparent
        submit.setFocusPainted(false); // Remove focus painting
        submit.setForeground(Color.WHITE); // Set text color to white
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor on hover
        submit.setBounds(750, 350, 100, 40);
        //submit.setFont();
        submit.addActionListener(e -> {
            if (!passwordCorrect(new String(passwordField.getPassword()))) {
                passwordLabel.setText("WRONG");
                passwordLabel.setForeground(Color.RED);
            } else {
                setVisible(false);
                passwordLabel.setText("PASSWORD");
                passwordLabel.setForeground(Color.WHITE);
                base = new DoorBase();
            }
        });
        add(submit);
    }

    private void addChangePasswordButton() {
        // Text should be underlined and button should be transparent
        changePasswordButton = new JButton("Change network password");
        changePasswordButton.setBounds(400, 600, 300, 40);
        changePasswordButton.setFont(new Font("Baskerville Old Face", Font.BOLD, 20)); // Example font settings
        changePasswordButton.setContentAreaFilled(false); // Make the button background transparent
        changePasswordButton.setFocusPainted(false); // Remove focus painting
        changePasswordButton.setForeground(Color.WHITE); // Set text color to white
        changePasswordButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor on hover
        //button.setFont();
        changePasswordButton.addActionListener(e -> {
            setVisible(false);
            PersonalIdentification personalIdentification = new PersonalIdentification(this);
        });
        add(changePasswordButton);
    }

    public void removeChangePasswordButton() {
        remove(changePasswordButton);
        revalidate();
        repaint();
    }

    private void generatePassword() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }

        password = stringBuilder.toString();
    }

    private boolean passwordCorrect(String requestedPassword) {
        System.out.println(requestedPassword);
        System.out.println(password);
        return requestedPassword.equals(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

