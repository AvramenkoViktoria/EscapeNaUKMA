
import javax.swing.*;


public class tempTest{
    public static testPanel testPanel; 

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1214, 890);
        frame.setLocationRelativeTo(null);
        PechkurovaMonologue monologue = new PechkurovaMonologue("Images\\PechkurovaRoom.png");
        monologue.setBounds(0, 0, 1200, 853);
        frame.add(monologue);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        /*
        testPanel = new testPanel("Images\\PechkurovaRoom.png");
        testPanel.setBounds(0, 0, 1200, 853);
        frame.add(testPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
         */
    }
}
