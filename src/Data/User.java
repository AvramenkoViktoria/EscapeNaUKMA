package Data;

import Enums.Level;

public class User {
    private Level level;
    private int heartsNum;

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
}
