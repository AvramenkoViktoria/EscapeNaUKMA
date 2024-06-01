package SuperSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Warning extends JFrame {
    public static final int FRAME_WIDTH = 450;
    public static final int FRAME_HEIGHT = 550;

    public Warning(String text, int width, JFrame menu) {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                menu.setVisible(true);
            }
        });
        addPhoto(440,400);
        addWarningText(text, width);
        addOKButton(menu);
        setVisible(true);
    }


    private void addPhoto(int width, int height) {
        ImageIcon originalIcon = new ImageIcon("Images\\KvitWarning.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(0, 115, width, height);
        add(imageLabel);
    }

    private void addWarningText(String text, int width) {
        JLabel warningText = new JLabel(text);
        Font font = new Font("Baskerville Old Face", Font.PLAIN, 20);
        warningText.setFont(font);
        warningText.setBounds((FRAME_WIDTH-width)/2, 10, width, 50);
        add(warningText);
    }

    private void addOKButton(JFrame menu) {
        RoundedButton ok = new RoundedButton("OK");
        ok.setBounds((FRAME_WIDTH-100)/2, 70, 80, 30);
        Font font = new Font("Baskerville Old Face", Font.BOLD, 20);
        ok.setFont(font);
        ok.setHorizontalAlignment(SwingConstants.CENTER);
        ok.setVerticalAlignment(SwingConstants.CENTER);
        ok.addActionListener(e -> {
            menu.setVisible(true);
            dispose();
        });
        add(ok);
    }
}
