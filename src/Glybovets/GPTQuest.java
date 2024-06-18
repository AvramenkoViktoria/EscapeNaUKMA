package Glybovets;

import javax.swing.*;

public class GPTQuest extends JFrame {
    public ChatGPT chat;
    public GPTQuestRoom room;

    public GPTQuest() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(null);
        setSize(1447, 707);
        setLocationRelativeTo(null);
        chat = new ChatGPT("Images\\ChatGPT.png");
        add(chat);
        room = new GPTQuestRoom("Images\\ComputerRoom.png");
        add(room);
        repaint();
        revalidate();
        setVisible(true);
    }
    public static void main(String[] args) {
        new GPTQuest();
    }
}
