package Menu;

import Enums.Status;
import SuperSwing.ImageButton;

public class RoomOption extends ImageButton {
    private Status status;
    public RoomOption(Status status) {
        super(pickImage(status));
        this.status = status;
    }

    private static String pickImage(Status status) {
        String path = null;
        switch (status) {
            case BLOCKED -> path = "Images\\Blocked.jpg";
            case CURRENT -> path = "Images\\Current.jpg";
            case PECHKUROVA -> path = "Images\\Pechkurova.jpg";
            case VOZNIUK -> path = "Images\\VozniukA.jpg";
            case GLYBOVETS -> path = "Images\\Glybovets.jpg";
        }

        return path;
     }

    public void setStatus(Status status) {
        this.status = status;
    }

     private Status getStatus() {
        return status;
     }
}
