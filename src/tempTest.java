
import javax.swing.*;


public class tempTest{

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(900, 900);
        frame.setLocationRelativeTo(null);
        testPanel testPanel = new testPanel();
        testPanel.setBounds(0, 0, 900, 900);
        frame.add(testPanel);
        frame.setVisible(true);
    }
}
