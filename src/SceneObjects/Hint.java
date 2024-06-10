package SceneObjects;

import Enums.SpeakerType;
import Pechkurova.IDE;
import Pechkurova.PechkurovaMonologue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hint extends DialogWindow {

    public Hint(int x, int y, String textToType) {
        super(x, y, textToType, SpeakerType.FRIEND);

        // Override OKButton action to remove window on click
        if (OKButton != null) {
            OKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Container parent = getParent();
                    if (parent != null) {
                        parent.remove(Hint.this);
                        parent.repaint();
                        parent.revalidate();
                        PechkurovaMonologue.sceneFrame.setVisible(false);
                        IDE ide = new IDE();
                    }
                    disappearanceTimer.stop();
                }
            });
        }

        // Start the disappearance timer
        startDisappearanceTimer();
    }


}
