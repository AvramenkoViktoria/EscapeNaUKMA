import Enums.Status;
import SuperSwing.ImageBackground;
import SuperSwing.RoundedButton;
import SuperSwing.RoundedPanel;
import SuperSwing.Warning;

import javax.swing.*;

import java.awt.*;
import java.util.LinkedList;

public class RoomMenu extends JFrame {
    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 800;
    private LinkedList<RoomOption> rooms = new LinkedList<>();
    private RoundedPanel clarificationPanel;
    public RoomMenu() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        addBackground();
        setVisible(true);
    }

    private void addBackground() {
        ImageBackground background = new ImageBackground(new Color(148,168,179 ));
        background.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        background.setLayout(null);
        addRoomText(background);
        addRooms(background);
        revalidate();
        repaint();
        add(background);
    }

    private void addRoomText(ImageBackground background) {
        JLabel label = new JLabel("CHOOSE LEVEL:");
        label.setForeground(Color.WHITE);
        Font font = new Font("Baskerville Old Face", Font.BOLD, 50);
        label.setFont(font);
        label.setBounds((FRAME_WIDTH-450)/2, 60, 450, 80);
        background.add(label);
        background.revalidate();
        background.repaint();
    }

    private void addRooms(ImageBackground background) {
        addRoom(Status.PECHKUROVA, (FRAME_WIDTH-300)/2+350, 200, 300, 300, background);
        addRoom(Status.CURRENT, (FRAME_WIDTH-300)/2, 200, 300, 300, background);
        addRoom(Status.BLOCKED, (FRAME_WIDTH-300)/2-350,200, 300, 300, background);
    }

    private void addRoom(Status status, int x, int y, int width, int height, ImageBackground background) {
        RoomOption room = new RoomOption(status);
        room.setBounds(x, y, width, height);
        rooms.add(room);
        room.addActionListener(e -> {
            switch (status) {
                case BLOCKED:
                    if (clarificationPanel != null)
                        clarificationPanel.setVisible(false);
                    Warning warning = new Warning("Previous room wasn't passed", 230, this);
                    break;
                case PECHKUROVA, VOZNIUK, GLYBOVETS:
                    if (clarificationPanel != null) {
                        clarificationPanel.setVisible(true);
                    } else {
                        addClarification(background);
                    }
                    break;
                case CURRENT:
                    if (clarificationPanel != null)
                        clarificationPanel.setVisible(false);
                    setVisible(false);
                    PechkurovaLevel level = new PechkurovaLevel();
            }
        });
        add(room);
    }

    private void addClarification(ImageBackground background) {
        clarificationPanel = new RoundedPanel(30, Color.WHITE);
        clarificationPanel.setLayout(null);
        clarificationPanel.setBounds((FRAME_WIDTH-340)/2, 560, 340, 160);
        JLabel text1 = new JLabel("Are you sure you want to continue?");
        Font font = new Font("Baskerville Old Face", Font.BOLD, 20);
        text1.setFont(font);
        text1.setBounds(20, 20, 340, 60);
        JLabel text2 = new JLabel("Progress will be rewritten");
        text2.setFont(font);
        text2.setBounds(20, 80, 340, 60);
        RoundedButton button = new RoundedButton("Yes");
        //button.setFont();
        button.setBounds(240, 90, 80, 40);
        button.addActionListener(e -> {
            setVisible(false);
            PechkurovaLevel level = new PechkurovaLevel();
        });
        clarificationPanel.add(text1);
        clarificationPanel.add(text2);
        clarificationPanel.add(button);
        clarificationPanel.revalidate();
        clarificationPanel.repaint();
        background.add(clarificationPanel);
        revalidate();
        repaint();
    }
}
