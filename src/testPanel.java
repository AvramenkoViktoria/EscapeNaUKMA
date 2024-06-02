import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Random;

public class testPanel extends JPanel implements ActionListener {
    private Timer timer;
    private LinkedList<Pechkurova> pechkurovas = new LinkedList<>();
    private Decoration[] decorations = new Decoration[8];
    private int collisionCounter = 0;  // Счетчик столкновений первой Pechkurova
    private final Random random = new Random();

    public testPanel() {
        timer = new Timer(18, this);
        Pechkurova pechkurova = new Pechkurova("Images\\Pechkurova.png", 100, 100);
        pechkurovas.add(pechkurova);
        pechkurova.setxVelocity(3);
        pechkurova.setyVelocity(3);

        decorations[0] = new Decoration(850, 5, 5, 890);
        decorations[1] = new Decoration(5, 5, 10, 890);
        decorations[2] = new Decoration(5, 10, 890, 5);
        decorations[3] = new Decoration(5, 800, 890, 5);
        decorations[4] = new Decoration(200, 200, 100, 100);
        decorations[5] = new Decoration(600, 200, 100, 100);
        decorations[6] = new Decoration(600, 450, 100, 100);
        decorations[7] = new Decoration(200, 450, 100, 100);

        add(pechkurova);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePress(e);
            }
        });

        timer.start();
    }

    private void handleMousePress(MouseEvent e) {
        if (pechkurovas.size() <= 1) {
            return;  // Не удаляем первую Pechkurova
        }

        for (int i = 1; i < pechkurovas.size(); i++) { // Начинаем с 1, чтобы не удалить первую Pechkurova
            Pechkurova pechkurova = pechkurovas.get(i);
            if (pechkurova.getBounds().contains(e.getPoint())) {
                pechkurovas.remove(i);
                remove(pechkurova);
                repaint();
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LinkedList<Pechkurova> newPechkurovas = new LinkedList<>();
        boolean firstPechkurovaCollided = false;

        for (int i = 0; i < pechkurovas.size(); i++) {
            Pechkurova pechkurova = pechkurovas.get(i);
            pechkurova.move(decorations);

            // Проверяем только для первого объекта в списке
            if (i == 0 && pechkurova.collides) {
                firstPechkurovaCollided = true;
                collisionCounter++;
                pechkurova.collides = false; // сбрасываем флаг столкновения
            }

            pechkurova.setBounds(pechkurova.getXForMove(), pechkurova.getYForMove(), Pechkurova.SIZE, Pechkurova.SIZE);
        }

        if (firstPechkurovaCollided && collisionCounter >= 3) {
            collisionCounter = 0;  // Сбрасываем счетчик после создания новой Pechkurova
            Pechkurova firstPechkurova = pechkurovas.get(0);
            Pechkurova newPechkurova = new Pechkurova("Images\\Pechkurova.png", firstPechkurova.getXForMove(), firstPechkurova.getYForMove());
            newPechkurova.setxVelocity(random.nextInt(4) + 1);  // Рандомное значение от 1 до 4
            newPechkurova.setyVelocity(random.nextInt(4) + 1);  // Рандомное значение от 1 до 4
            newPechkurovas.add(newPechkurova);
            add(newPechkurova);
        }

        pechkurovas.addAll(newPechkurovas);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Decoration decoration : decorations) {
            decoration.draw(g);
        }
    }
}
