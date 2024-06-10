package Data;

import Enums.Level;
import Enums.Status;

public class FileManager {

    public static User user = new User();
    public static void saveLevelData(Level level) {
        user.setLevel(level);
        switch (level) {
            case CONTRACT -> user.setHeartsNum(3);
            case BUDGET -> user.setHeartsNum(2);
            case GRANT -> user.setHeartsNum(1);
        }
    }

    public static void saveRoomData() {

    }

    public static void retriveDataFromFiles() {
        user.setLevel(Level.NONE);
    }

    public static void setRoomStatus(int roomNumber, Status status) {

    }
}
