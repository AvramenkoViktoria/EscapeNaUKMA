import Enums.Level;
import SuperSwing.ImageBackground;
import SuperSwing.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class LevelMenu extends JFrame {

    public static final int FRAME_WIDTH = 1300;
    public static final int FRAME_HEIGHT = 900;
    private String contractDescription = "Contract description";
    private String budgetDescription = "Budget description";
    private String grantDescription = "Grant description";


    public LevelMenu() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addBackground();
        setVisible(true);
    }

    private void addBackground() {
        ImageBackground background = new ImageBackground("C:\\Users\\ACER\\IdeaProjects\\EscapeNaUKMA\\Images\\NaUKMA_background.jpg");
        background.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        background.setLayout(null);
        addChooseLevelLabel(background);
        addContractLevelOption(background);
        addBudgetLevelOption(background);
        addGrantLevelOption(background);
        revalidate();
        repaint();
        add(background);
    }

    private void addChooseLevelLabel(ImageBackground background) {
        JPanel nameBackground = new JPanel();
        nameBackground.setLayout(null);
        nameBackground.setBounds(30, 30, 400, 100);
        //nameBackground.setBackground();
        JLabel label = new JLabel("Choose level");
        //label.setFont();
        //label.setForeground();
        label.setBounds(30, 20, 250, 80);
        nameBackground.add(label);
        nameBackground.revalidate();
        nameBackground.repaint();
        background.add(nameBackground);
    }

    private void addContractLevelOption(ImageBackground background) {
        addLevelButton("Контракт", 200, Level.CONTRACT, background);
        addLevelImage("C:\\Users\\ACER\\IdeaProjects\\EscapeNaUKMA\\Images\\Contract.jpg", 200, background);
        addLevelDescription(contractDescription, 200, background);
    }

    private void addBudgetLevelOption(ImageBackground background) {
        addLevelButton("Бюджет", 360, Level.BUDGET, background);
        addLevelImage("C:\\Users\\ACER\\IdeaProjects\\EscapeNaUKMA\\Images\\Budget.jpg", 300, background);
        addLevelDescription(budgetDescription, 360, background);
    }

    private void addGrantLevelOption(ImageBackground background) {
        addLevelButton("Стипендія", 470, Level.GRANT, background);
        addLevelImage("C:\\Users\\ACER\\IdeaProjects\\EscapeNaUKMA\\Images\\Grant.jpg", 400, background);
        addLevelDescription(grantDescription, 470, background);
    }

    private void addLevelButton(String name, int y, Level level, ImageBackground background) {
        RoundedButton button = new RoundedButton(name);
        button.setBounds(30, y, 200, 50);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        //button.setFont();
        button.addActionListener(e -> {
            setVisible(false);
            FileManager.saveLevelData(level);
            RoomMenu menu = new RoomMenu();
        });
        background.add(button);
    }

    private void addLevelImage(String path, int y, ImageBackground background) {
        ImageBackground levelImage = new ImageBackground(path);
        levelImage.setBounds(260, y, 100, 100);
        background.add(levelImage);
    }

    private void addLevelDescription(String description, int y, ImageBackground background) {
        JPanel descBackground = new JPanel();
        descBackground.setLayout(null);
        descBackground.setBounds(400, y, 400, 100);
        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setBounds(10, 10, 380, 80);
        //descriptionLabel.setFont();
        descBackground.add(descriptionLabel);
        descBackground.revalidate();
        descBackground.repaint();
        background.add(descBackground);
    }
}
