package SceneObjects;

import Data.Test;
import Enums.RuleOption;
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

    public Rule(int x, int y, String textToType, RuleOption ruleOption, int size) {
        super(x, y, textToType, SpeakerType.FRIEND, size);

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

                        switch (ruleOption) {
                            case IDE -> IDE.startTimer();
                            case PECHKUROVA -> {
                                IDE.testPanel.playBackgroundMusic("Audio\\Mortal.wav");
                                IDE.testPanel.addKeyListeners();
                                IDE.testPanel.startTimer();
                            }
                            case INDIANS -> Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoom.startTimer();
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
    public void bringToFront() {
        Container parent = getParent();
        if (parent != null) {
            parent.setComponentZOrder(this, 0); // Bring this DialogWindow to the front
            parent.repaint();
            parent.revalidate();
        }
    }
}
