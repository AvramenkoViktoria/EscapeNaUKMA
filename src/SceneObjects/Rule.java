package SceneObjects;

import Enums.SpeakerType;
import Pechkurova.IDE;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Rule extends DialogWindow {
   Clip backgroundMusicClip;

    public Rule(int x, int y, String textToType, boolean ide) {
        super(x, y, textToType, SpeakerType.FRIEND);

        // Override OKButton action to remove window on click
        if (OKButton != null) {
            OKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Container parent = getParent();
                    if (parent != null) {
                        parent.remove(Rule.this);
                        parent.repaint();
                        parent.revalidate();
                        if (ide) {
                            IDE.startTimer();
                        } else {
                            IDE.testPanel.playBackgroundMusic("Audio\\Mortal.wav");
                            IDE.testPanel.addKeyListeners();
                            IDE.testPanel.startTimer();
                        }
                    }
                }
            });
        }
    }

    @Override
    public void startDisappearanceTimer() {
        // Disable automatic disappearance for rules
    }
}
