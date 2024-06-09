package SceneObjects;

import Enums.SpeakerType;

import javax.swing.*;

public class Thought extends DialogWindow {

    public Thought(int x, int y, String textToType, SpeakerType type) {
        super(x, y, textToType, type);

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
}
