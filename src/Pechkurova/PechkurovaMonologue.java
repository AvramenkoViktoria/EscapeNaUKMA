package Pechkurova;

import Enums.SpeakerType;
import Menu.RoomMenu;
import Pechkurova.Pechkurova;
import SceneObjects.DialogWindow;
import SceneObjects.Thought;
import SuperSwing.ImageBackground;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PechkurovaMonologue extends ImageBackground {
    private MainCharacter mainCharacter;
    private Pechkurova pechkurova;

    private int startX = 960; // Starting X-coordinate
    private int startY = 20;  // Starting Y-coordinate
    private int endX = 890;   // Ending X-coordinate
    private int endY = 220;   // Ending Y-coordinate

    private Timer timer;
    private double progress = 0; // Progress along the line (0 to 1)

    public PechkurovaMonologue(String imagePath) {
        super(imagePath);
        setLayout(null);

        // Initialize characters
        pechkurova = new Pechkurova("Images\\Pechkurova.jpg", startX, startY);
        mainCharacter = new MainCharacter("Images\\MainCharUp.png", 990, 250, 80, 80);

        // Add characters to the scene
        add(pechkurova);
        add(mainCharacter);

        // Start the scene
        startScene();
    }

    private void startScene() {
        timer = new Timer(10, this::pechkurovaGoIn);
        timer.start();
    }

    private void pechkurovaGoIn(ActionEvent e) {
        // Calculate the current position based on progress
        int currentX = (int) (startX + (endX - startX) * progress);
        int currentY = (int) (startY + (endY - startY) * progress);

        // Update Pechkurova's position
        pechkurova.setLocation(currentX, currentY);

        // Increment progress
        progress += 0.005; // Adjust speed here

        // Check if Pechkurova has reached the destination
        if (progress >= 1) {
            progress = 1;
            timer.stop();
            speak(); // Initiate speech when movement ends
        }
    }

    private void swapCoordinates() {
        int tempX = startX;
        int tempY = startY;
        startX = endX;
        startY = endY;
        endX = tempX;
        endY = tempY;
    }

    public static JFrame sceneFrame;
    private void pechkurovaGoOut(ActionEvent e) {
        // Calculate the current position based on progress
        int currentX = (int) (startX + (endX - startX) * progress);
        int currentY = (int) (startY + (endY - startY) * progress);

        // Update Pechkurova's position
        pechkurova.setLocation(currentX, currentY);

        // Increment progress
        progress += 0.005; // Adjust speed here

        // Check if Pechkurova has reached the starting point
        if (progress >= 1) {
            progress = 1;
            timer.stop();
            remove(pechkurova);
            sceneFrame = new JFrame();
            sceneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sceneFrame.setLayout(null);
            sceneFrame.setSize(1214, 890);
            sceneFrame.setLocationRelativeTo(null);
            PechkurovaScene scene = new PechkurovaScene("Images\\PechkurovaRoom.png");
            scene.setBounds(0, 0, 1200, 853);
            sceneFrame.add(scene);
            sceneFrame.setLocationRelativeTo(null);
            sceneFrame.setVisible(true);
            RoomMenu.monologueFrame.setVisible(false);
        }
    }

    private void speak() {
        int x = 0;
        int y = getHeight() - DialogWindow.HEIGHT; // Позиція внизу
        Thought thought = new Thought(x, y, "I am going to buy coffee! Prepare your works!", SpeakerType.PECHKUROVA);
        add(thought);
        thought.bringToFront();

        // Створення таймера для приховування діалогу та початку виходу через 3 секунди
        Timer dialogTimer = new Timer(3000, e -> {
            thought.setVisible(false);
            swapCoordinates(); // Обмін координатами для руху назад
            progress = 0; // Скидання прогресу для вихідної анімації

            // Створення та запуск таймера для вихідної анімації
            timer = new Timer(10, this::pechkurovaGoOut);
            timer.start();
        });
        dialogTimer.setRepeats(false); // Виконати тільки один раз
        dialogTimer.start();
    }
}
