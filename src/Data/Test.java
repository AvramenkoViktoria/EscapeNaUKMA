package Data;

import Menu.MainMenu;

public class Test {
    public static MainMenu mainMenu;

    public static void main(String[] args) {
        FileManager.retriveDataFromFiles();
        mainMenu = new MainMenu();
    }
}
