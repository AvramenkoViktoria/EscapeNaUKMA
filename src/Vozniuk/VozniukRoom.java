package Vozniuk;

import SceneObjects.Decoration;
import SceneObjects.Desk;
import SceneObjects.PortalDesk;
import SuperSwing.ImageBackground;

import java.awt.*;


public class VozniukRoom extends ImageBackground {
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    private Decoration[] decorations;
    public VozniukRoom(String imagePath) {
        super(imagePath);
        setLayout(null);
        setBounds(0, 0, WIDTH, HEIGHT);
        initialiazeDecorationsList();
    }

    private void initialiazeDecorationsList() {
        decorations = new Decoration[23];
        decorations[0] = new Desk(-30, -50, 45, 900, null);
        decorations[1]= new Desk(-50, -30, 1200, 47, null);
        decorations[2] = new Desk(-30, 747, 1000, 50, null);
        decorations[3] = new Desk(970, -30, 50, 900, null);
        //walls
        decorations[4] = new Desk(-30, 452, 60, 265, null);
        //whiteboard
        decorations[5] = new Desk(-30, 508, 175, 196, null);
        //machine near whiteboard
        decorations[6] = new PortalDesk(172, 728, 150, 65, null);
        //door
        decorations[7] = new Desk(905, 574, 100, 200, null);
        //altar cisco
        decorations[8] = new PortalDesk(905, 100, 100, 380, null);
        //shafa cisco
        decorations[9] = new Desk(123, 37, 138, 357, null);
        //Vozniuk's desk
        decorations[10] = new Desk(94, 92, 100, 128, null);
        //Vozniuk's chair
        decorations[11] = new Desk(372, 127, 121, 133, null);
        decorations[12] = new Desk(372, 150, 139, 85, null);
        //desk1
        decorations[13] = new Desk(370, 369, 123, 136, null);
        decorations[14] = new Desk(372, 393, 139, 85, null);
        //desk2
        decorations[15] = new Desk(370, 609, 125, 140, null);
        decorations[16] = new Desk(372, 634, 138, 85, null);
        //desk3
        decorations[17] = new Desk(615, 15, 126, 138, null);
        decorations[18] = new Desk(615, 45, 141, 81, null);
        //desk4
        decorations[19] = new Desk(617, 260, 123, 136, null);
        decorations[20] = new Desk(617, 284, 140, 85, null);
        //desk5
        decorations[21] = new Desk(616, 503, 123, 136, null);
        decorations[22] = new Desk(616, 527, 140, 85, null);
        //desk6



    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Decoration decoration : decorations) {
            decoration.draw(g);
        }
    }
}
