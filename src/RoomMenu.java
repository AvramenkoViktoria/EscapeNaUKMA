import Enums.Status;
import SuperSwing.RoundedButton;

import javax.swing.*;

import java.util.LinkedList;

public class RoomMenu extends JFrame {
    private static final int FRAME_WIDTH = 1300;
    private static final int FRAME_HEIGHT = 900;
    private LinkedList<RoomOption> rooms = new LinkedList<>();
    private JPanel clarificationPanel;
    public RoomMenu() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addRoomText();
        addRooms();
        setVisible(true);
    }

    private void addRoomText() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(40, 40, 200, 100);
        JLabel label = new JLabel("Оберіть кімнату");
        label.setBounds(10, 10, 150, 50);
        //label.setFont();
        panel.add(label);
        panel.revalidate();
        panel.repaint();
        add(panel);
    }

    private void addRooms() {
        addRoom(Status.BLOCKED, 40, 200, 300, 300);
        addRoom(Status.VOZNIUK, 350, 200, 300, 300);
        addRoom(Status.CURRENT, 660, 200, 300, 300);
    }

    private void addRoom(Status status, int x, int y, int width, int height) {
        RoomOption room = new RoomOption(status);
        room.setBounds(x, y, width, height);
        rooms.add(room);
        room.addActionListener(e -> {
            switch (status) {
                case BLOCKED:
                    if (clarificationPanel != null)
                        clarificationPanel.setVisible(false);
                    Warning warning = new Warning("You cunt", this);
                    break;
                case PECHKUROVA, VOZNIUK, GLYBOVETS:
                    if (clarificationPanel != null) {
                        clarificationPanel.setVisible(true);
                    } else {
                        addClarification();
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

    private void addClarification() {
        clarificationPanel = new JPanel();
        clarificationPanel.setLayout(null);
        clarificationPanel.setBounds(100, 700, 400, 100);
        JLabel text = new JLabel("Ю шур?");
        //text.setFont();
        text.setBounds(20, 20, 200, 60);
        RoundedButton button = new RoundedButton("Yes");
        //button.setFont();
        button.setBounds(300, 40, 100, 40);
        button.addActionListener(e -> {
            setVisible(false);
            PechkurovaLevel level = new PechkurovaLevel();
        });
        clarificationPanel.add(text);
        clarificationPanel.add(button);
        clarificationPanel.revalidate();
        clarificationPanel.repaint();
        add(clarificationPanel);
        revalidate();
        repaint();
    }
}
