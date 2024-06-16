package Vozniuk;

import Data.Test;

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
        setTitle("Cisco account");
        generatePassword();
        ImageIcon icon = new ImageIcon("Images\\VozniukA.jpg");
        Image image = icon.getImage();
        setIconImage(image);
        addAccountPicture();
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
        ImageIcon imageIcon = new ImageIcon("Images\\VozniukA.jpg");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel label = new JLabel(scaledIcon);
        label.setBounds(450, 150, 150, 150);
        add(label);
    }

    private void addAccountName() {
        JLabel name = new JLabel("yarik_vznk");
        name.setBounds(440, 320, 200, 40);
        //name.setFont();
        add(name);
    }

    private void addPasswordField() {
        passwordField = new JPasswordField();
        passwordField.setBounds(400, 350, 250, 40);
        passwordField.setEchoChar('*');
        //passwordField.setFont();
        add(passwordField);
    }

    private void addPasswordLabel() {
        passwordLabel = new JLabel("password");
        passwordLabel.setBounds(400, 394, 100, 15);
        //passwordLabel.setFont();
        add(passwordLabel);
    }

    private void addSubmitButton() {
        JButton submit = new JButton("Submit");
        submit.setBounds(470, 430, 100, 40);
        //submit.setFont();
        submit.addActionListener(e -> {
            if (!passwordCorrect(new String(passwordField.getPassword()))) {
                passwordLabel.setText("Wrong password");
                passwordLabel.setForeground(Color.RED);
            } else {
                setVisible(false);
                passwordLabel.setText("password");
                passwordLabel.setForeground(Color.BLACK);
                base = new DoorBase();
            }
        });
        add(submit);
    }

    private void addChangePasswordButton() {
        // Text should be underlined and button should be transparent
        changePasswordButton = new JButton("Change network password");
        changePasswordButton.setBounds(700, 650, 300, 40);
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
