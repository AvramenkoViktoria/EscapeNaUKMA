package Data;

import Glybovets.GlybovetsRoom;
import Menu.MainMenu;

import javax.swing.*;

public class Test {
    public static MainMenu mainMenu;

    public static void main(String[] args) {
        FileManager.retriveDataFromFiles();
        //mainMenu = new MainMenu();
        GlybovetsRoom room = new GlybovetsRoom("Images\\noComp.png");
        JFrame frame = new JFrame();
        frame.setSize(1000,800);
        frame.setLocationRelativeTo(null);
        frame.add(room);
        frame.setVisible(true);




    }
}
