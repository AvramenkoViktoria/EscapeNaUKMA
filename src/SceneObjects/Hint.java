package SceneObjects;

import Data.Test;
import Menu.RoomMenu;
import Enums.SpeakerType;
import Pechkurova.IDE;
import Pechkurova.PechkurovaMonologue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hint extends DialogWindow {

    public Hint(int x, int y, String textToType, SpeakerType speakerType, int size) {
        super(x, y, textToType, speakerType, size);

        // Override OKButton action to remove window on click
        if (OKButton != null) {
            OKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (speakerType.equals(SpeakerType.FRIEND)) {
                        Container parent = getParent();
                        if (parent != null) {
                            PechkurovaMonologue monologue = RoomMenu.monologue;
                            if (monologue != null) {
                                System.out.println("hihihi");
                                monologue.stopBackgroundMusic(); // Stop the music
                            }
                            parent.remove(Hint.this);
                            parent.repaint();
                            parent.revalidate();
                            PechkurovaMonologue.sceneFrame.setVisible(false);
                            IDE ide = new IDE();
                        }
                    } else {
                        Container parent = getParent();
                        parent.remove(Hint.this);
                        parent.repaint();
                        parent.revalidate();
                    }
                    disappearanceTimer.stop();
                }
            });
        }

        // Start the disappearance timer
        startDisappearanceTimer();
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
