package Data;

import Menu.MainMenu;
import SuperSwing.Win;

//Please handle clarification panel for Vozniuk

public class Test {
    public static MainMenu mainMenu;

    public static void main(String[] args) {
        FileManager.retriveDataFromFiles();
        mainMenu = new MainMenu();
    }
}
