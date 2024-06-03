package Menu;

import Data.FileManager;
import Enums.Level;
import SuperSwing.ImageBackground;
import SuperSwing.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class LevelMenu extends JFrame {

    public static final int FRAME_WIDTH = 1200;
    public static final int FRAME_HEIGHT = 800;
    private String contractDescription = "Contract - the most easy level to pass:, - You have three hearts for all levels, - Professors are moving slow and lazy, - Some characteristics of tasks are safer";
    private String budgetDescription = "Budget - more challenging level:, - You have just one heart for all levels, - Professors are moving faster, - Characteristics of tasks are harder ";
    private String grantDescription = "Grant - the hardest level of all:, - You do not have additional lifes, - Professors are moving very fast, - Characteristics of tasks are crazy";


    public LevelMenu() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        addBackground();
        setVisible(true);
    }

    private void addBackground() {
        ImageBackground background = new ImageBackground(new Color(148,168,179));
        background.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        background.setLayout(null);
        addChooseLevelLabel(background);
        addContractLevelOption(background);
        addBudgetLevelOption(background);
        addGrantLevelOption(background);
        addKvitImage("Images\\KvitLevel.png", background);
        revalidate();
        repaint();
        add(background);
    }

    private void addChooseLevelLabel(ImageBackground background) {
        JLabel label = new JLabel("CHOOSE LEVEL:");
        label.setForeground(Color.WHITE);
        Font font = new Font("Baskerville Old Face", Font.BOLD, 50);
        label.setFont(font);
        label.setBounds((FRAME_WIDTH-450)/2, 20, 450, 80);
      background.add(label);
        background.revalidate();
       background.repaint();
    }

    private void addContractLevelOption(ImageBackground background) {
        addLevelImage("Images\\Contract.png", 110,150, 150, 0,background);
        addLevelButton("Контракт", 270, Level.CONTRACT, background);
        addLevelDescription(contractDescription, 120, background);
    }

    private void addBudgetLevelOption(ImageBackground background) {
        addLevelImage("Images\\Budget.png", 330,150,150, 0,background);
        addLevelButton("Бюджет", 490, Level.BUDGET, background);
        addLevelDescription(budgetDescription, 340, background);
    }

    private void addGrantLevelOption(ImageBackground background) {
        addLevelImage("Images\\Grand.png", 550,130,130,0, background);
        addLevelButton("Стипендія", 690, Level.GRANT, background);
        addLevelDescription(grantDescription, 560, background);
    }

    private void addLevelButton(String name, int y, Level level, ImageBackground background) {
        RoundedButton button = new RoundedButton(name);
        button.setBounds(420, y, 200, 50);
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        button.setFont(font);
        button.setBackground(new Color(225, 226, 233 ));
        button.setForeground(Color.BLACK);
        button.addActionListener(e -> {
            setVisible(false);
            FileManager.saveLevelData(level);
            RoomMenu menu = new RoomMenu();
        });
        background.add(button);
    }

    private void addLevelImage(String path, int y, int width, int heigh, int pohibka, JPanel background) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, heigh, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(445+pohibka, y, width, heigh);
        background.add(imageLabel);
    }
    private void addKvitImage(String path, JPanel background) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(550, 800, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(-70, 50, 550, 800);
        background.add(imageLabel);
    }


    private void addLevelDescription(String description, int y, ImageBackground background) {
        JPanel descBackground = new JPanel();
        descBackground.setLayout(null);
        descBackground.setBackground(new Color(148,168,179 ));
        descBackground.setBounds(700, y, 350, 165);
        JLabel descriptionLabel = new JLabel("<html>" + description.replaceAll(",", "<br>") + "</html>");
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setBounds(0, 0, 350, 165);
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        descriptionLabel.setFont(font);
        descBackground.add(descriptionLabel);
        descBackground.revalidate();
        descBackground.repaint();
        background.add(descBackground);
    }
}
