package Data;

import Enums.Level;
import Enums.Status;

public class FileManager {

    public static User user = new User();
    public static void saveLevelData(Level level) {
        user.setLevel(level);
    }

    public static void saveRoomData() {

    }

    public static void retriveDataFromFiles() {
        user.setLevel(Level.NONE);
    }

    public static void setRoomStatus(int roomNumber, Status status) {

    }
}
