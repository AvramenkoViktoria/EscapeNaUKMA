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
            case BLOCKED -> path = "C:\\Users\\ACER\\IdeaProjects\\EscapeNaUKMA\\Images\\Blocked.jpg";
            case CURRENT -> path = "C:\\Users\\ACER\\IdeaProjects\\EscapeNaUKMA\\Images\\Current.jpg";
            case PECHKUROVA -> path = "C:\\Users\\ACER\\IdeaProjects\\EscapeNaUKMA\\Images\\Pechkurova.jpg";
            case VOZNIUK -> path = "C:\\Users\\ACER\\IdeaProjects\\EscapeNaUKMA\\Images\\Vozniuk.jpg";
            case GLYBOVETS -> path = "C:\\Users\\ACER\\IdeaProjects\\EscapeNaUKMA\\Images\\Glybovets.jpg";
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
