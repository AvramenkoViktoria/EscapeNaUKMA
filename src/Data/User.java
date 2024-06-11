package Data;

import Enums.Level;
import Enums.Status;

public class User {
    private Level level;
    private int heartsNum;
    private Status status;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getHeartsNum() {
        return heartsNum;
    }

    public void setHeartsNum(int heartsNum) {
        this.heartsNum = heartsNum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
