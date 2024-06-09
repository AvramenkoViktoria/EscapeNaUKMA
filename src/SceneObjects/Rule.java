package SceneObjects;

import Enums.SpeakerType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rule extends DialogWindow {

    public Rule(int x, int y, String textToType) {
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
