package Pechkurova;

import Pechkurova.BattleScene;

import javax.swing.*;


public class tempTest{
    public static PechkurovaMonologue testPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1214, 890);
        frame.setLocationRelativeTo(null);
        //testPanel = new BattleScene("Images\\PechkurovaRoom.png");
        testPanel = new PechkurovaMonologue("Images\\PechkurovaRoom.png");
        testPanel.setBounds(0, 0, 1200, 853);
        frame.add(testPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}