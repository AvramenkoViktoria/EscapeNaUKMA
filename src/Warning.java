import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Warning extends JFrame {
    public static final int FRAME_WIDTH = 500;
    public static final int FRAME_HEIGHT = 400;

    public Warning(String text, JFrame menu) {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                menu.setVisible(true);
            }
        });

        addWarningText(text);
        addOKButton(menu);
        setVisible(true);
    }

    private void addWarningText(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(40, 40, 400, 200);
        JLabel warningText = new JLabel(text);
        //warningText.setFont();
        warningText.setBounds(10, 10, 200, 100);
        panel.add(warningText);
        panel.revalidate();
        panel.repaint();
        add(panel);
    }

    private void addOKButton(JFrame menu) {
        JButton ok = new JButton("OK");
        ok.setBounds(100, 300, 100, 30);
        //ok.setFont();
        ok.addActionListener(e -> {
            menu.setVisible(true);
            dispose();
        });

        add(ok);
    }
}
