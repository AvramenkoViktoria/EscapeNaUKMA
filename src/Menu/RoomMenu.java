package Menu;

import Data.FileManager;
import Data.Test;
import Enums.Status;
import Glybovets.GlybovetsRoom;
import Pechkurova.PechkurovaMonologue;
import SceneObjects.Hall;
import SuperSwing.ImageBackground;
import SuperSwing.RoundedButton;
import SuperSwing.RoundedPanel;
import SuperSwing.Warning;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class RoomMenu extends JFrame {
    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 800;
    private LinkedList<RoomOption> rooms = new LinkedList<>();
    private RoundedPanel clarificationPanel;
    public static PechkurovaMonologue monologue;
    Clip backgroundMusicClip;
    private ImageBackground background;
    MainMenu mainmenu = Test.mainMenu;

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
        background = new ImageBackground(new Color(148, 168, 179));
        background.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        background.setLayout(null);
        addRoomText();
        addRooms(new Status[]{Status.CURRENT, Status.BLOCKED, Status.BLOCKED});
        revalidate();
        repaint();
        add(background);
    }

    private void addRoomText() {
        JLabel label = new JLabel("CHOOSE LEVEL:");
        label.setForeground(Color.WHITE);
        Font font = new Font("Baskerville Old Face", Font.BOLD, 50);
        label.setFont(font);
        label.setBounds((FRAME_WIDTH - 450) / 2, 60, 450, 80);
        background.add(label);
        background.revalidate();
        background.repaint();
    }

    public void addRooms(Status[] statuses) {
        removeRoomOptions();
        addRoom(statuses[0], (FRAME_WIDTH - 300) / 2 + 350, 200, 300, 300);
        addRoom(statuses[1], (FRAME_WIDTH - 300) / 2, 200, 300, 300);
        addRoom(statuses[2], (FRAME_WIDTH - 300) / 2 - 350, 200, 300, 300);
    }

    private void removeRoomOptions() {
        Iterator<RoomOption> iterator = rooms.iterator();
        while (iterator.hasNext()) {
            RoomOption room = iterator.next();
            if (room != null) {
                background.remove(room);
                iterator.remove();
            }
        }
        background.revalidate();
        background.repaint();
    }

    public static JFrame monologueFrame;
    public JFrame hallFrame;
    public Hall hall;
    public JFrame glybovetsFrame;
    public GlybovetsRoom glybovetsRoom;

    private void addRoom(Status status, int x, int y, int width, int height) {
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
                    if (mainmenu != null) {
                        mainmenu.stopBackgroundMusic(); // Stop the music
                    }
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
                    mainmenu.stopBackgroundMusic(); // Stop the music
                    switch (FileManager.user.getStatus()) {
                        case PECHKUROVA:
                            monologueFrame = new JFrame();
                            monologueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            monologueFrame.setLayout(null);
                            monologueFrame.setSize(1214, 890);
                            monologueFrame.setLocationRelativeTo(null);
                            MainMenu mainMenu = Test.mainMenu; // Get the main menu instance
                            if (mainMenu != null) {
                                mainMenu.stopBackgroundMusic(); // Stop the music
                            }
                            monologue = new PechkurovaMonologue("Images\\PechkurovaRoom.png");
                            monologue.setBounds(0, 0, 1200, 853);
                            monologueFrame.add(monologue);
                            monologueFrame.setLocationRelativeTo(null);
                            monologueFrame.setVisible(true);
                            break;
                        case VOZNIUK:
                            hallFrame = new JFrame();
                            hallFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                            hallFrame.setLayout(null);
                            hallFrame.setResizable(false);
                            hallFrame.setSize(814, 435);
                            hallFrame.setLocationRelativeTo(null);
                            hall = new Hall("Images\\Hall.png");
                            hall.setBounds(0, 0, 800, 400);
                            hallFrame.add(hall);
                            hallFrame.setVisible(true);
                            break;
                        case GLYBOVETS:
                            glybovetsFrame = new JFrame();
                            glybovetsFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                            glybovetsFrame.setLayout(null);
                            glybovetsFrame.setResizable(false);
                            glybovetsFrame.setSize(1000, 800);
                            glybovetsFrame.setLocationRelativeTo(null);
                            glybovetsRoom = new GlybovetsRoom("Images\\noComp.png");
                            glybovetsRoom.setBounds(0, 0, 1000, 800);
                            glybovetsFrame.add(glybovetsRoom);
                            glybovetsFrame.setVisible(true);
                    }
                    break;
            }
        });
        background.add(room);
        background.revalidate();
        background.repaint();
    }

    private void addClarification() {
        clarificationPanel = new RoundedPanel(30, Color.WHITE);
        clarificationPanel.setLayout(null);
        clarificationPanel.setBounds((FRAME_WIDTH - 340) / 2, 560, 340, 160);
        JLabel text1 = new JLabel("Are you sure you want to continue?");
        Font font = new Font("Baskerville Old Face", Font.BOLD, 20);
        text1.setFont(font);
        text1.setBounds(20, 20, 340, 60);
        JLabel text2 = new JLabel("Progress will be rewritten");
        text2.setFont(font);
        text2.setBounds(20, 80, 340, 60);
        RoundedButton button = new RoundedButton("Yes");
        button.setBounds(240, 90, 80, 40);
        button.addActionListener(e -> {
            setVisible(false);
            monologueFrame = new JFrame();
            monologueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            monologueFrame.setLayout(null);
            monologueFrame.setSize(1214, 890);
            monologueFrame.setLocationRelativeTo(null);
            monologue = new PechkurovaMonologue("Images\\PechkurovaRoom.png");
            monologue.setBounds(0, 0, 1200, 853);
            monologueFrame.add(monologue);
            monologueFrame.setLocationRelativeTo(null);
            monologueFrame.setVisible(true);
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
    private void playBackgroundMusic(String filePath) {
        try {
            // Open the audio file as a stream
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Get the clip resource
            backgroundMusicClip = AudioSystem.getClip();

            // Open the clip and load the audio data from the audio input stream
            backgroundMusicClip.open(audioStream);

            // Loop the clip continuously
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    // Method to stop the background music
    public void stopBackgroundMusic() {
        if (backgroundMusicClip != null) {
            backgroundMusicClip.stop();
            backgroundMusicClip.close();
        }
    }
}
