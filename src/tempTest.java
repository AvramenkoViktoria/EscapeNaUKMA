
import javax.swing.*;


public class tempTest{

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1350, 1100);
        frame.setLocationRelativeTo(null);
        testPanel testPanel = new testPanel("Images\\PechkurovaRoom.png");
        testPanel.setBounds(0, 0, 1300, 1000);
        frame.add(testPanel);
        frame.setVisible(true);
    }
}
