package Data;

import Menu.MainMenu;
import Vozniuk.VozniukRoom;

import javax.swing.*;

public class Test {
    public static MainMenu mainMenu;

    public static void main(String[] args) {
        FileManager.retriveDataFromFiles();
        //mainMenu = new MainMenu();
        JFrame frameVozniuk = new JFrame();
        frameVozniuk.setSize(1000,800);
        frameVozniuk.setLocationRelativeTo(null);
        frameVozniuk.setResizable(false);
        frameVozniuk.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        VozniukRoom room = new VozniukRoom("Images\\VozniukRoom.png");
        frameVozniuk.add(room);
        frameVozniuk.setVisible(true);


    }
}
