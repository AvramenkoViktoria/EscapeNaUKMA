package SceneObjects;

import Enums.SpeakerType;

import javax.swing.*;
import java.awt.*;

public class Thought extends DialogWindow {

    public Thought(int x, int y, String textToType, SpeakerType type, int size) {
        super(x, y, textToType, type, size);

        // Ensure the type is either PECHKUROVA or USER
        if (type != SpeakerType.PECHKUROVA && type != SpeakerType.USER) {
            throw new IllegalArgumentException("Thoughts can only be from PECHKUROVA or USER");
        }

        // Start the disappearance timer (no need for OK button in Thought)
        startDisappearanceTimer();
    }

    @Override
    public void addOKButton() {
        // Override and do not add an OK button for thoughts
    }
    public void bringToFront() {
        Container parent = getParent();
        if (parent != null) {
            parent.setComponentZOrder(this, 0); // Bring this DialogWindow to the front
            parent.repaint();
            parent.revalidate();
        }
    }
}
