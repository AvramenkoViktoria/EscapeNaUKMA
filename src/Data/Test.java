package Data;

import Glybovets.GlybovetsRoom;
import Menu.MainMenu;

import javax.swing.*;

public class Test {
    public static MainMenu mainMenu;

    public static void main(String[] args) {
        FileManager.retriveDataFromFiles();
        mainMenu = new MainMenu();




    }
}
