package Pechkurova;

import Data.FileManager;
import Enums.Level;
import Pechkurova.BattleScene;

import javax.swing.*;


public class tempTest{
    public static BattleScene testPanel;

    public static void main(String[] args) {
        FileManager.retriveDataFromFiles();
        FileManager.user.setLevel(Level.CONTRACT);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1214, 890);
        frame.setLocationRelativeTo(null);
        testPanel = new BattleScene("Images\\PechkurovaRoom.png");
        testPanel.setBounds(0, 0, 1200, 853);
        frame.add(testPanel);
        testPanel.addRuleWindow();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
