package Pechkurova;

import Enums.SpeakerType;

public class InteractiveObject {
    private boolean ableToMove;
    private boolean isDoor;
    private String message;
    private SpeakerType speakerType;
    public InteractiveObject(boolean ableToMove,boolean isDoor, String message, SpeakerType speakerType) {
        this.ableToMove = ableToMove;
        this.isDoor = isDoor;
        this.message = message;
        this.speakerType = speakerType;
    }

    public boolean isAbleToMove() {
        return ableToMove;
    }

    public String getMessage() {
        return message;
    }

    public SpeakerType getSpeakerType() {
        return speakerType;
    }

    public boolean IsDoor() {
        return isDoor;
    }
}
