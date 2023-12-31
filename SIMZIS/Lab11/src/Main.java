import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Main implements ActionListener {

    public static void main(String[] args) {
        new Main().start();
    }
    static JFrame frame = new JFrame("Laba1");
    static JButton button = new JButton("Calculate");

    public void start() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(this);
        frame.getContentPane().add(button, BorderLayout.SOUTH);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pressing(button);
    }

    public static void pressing(JButton button) {
        final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int len = 6;
        int number = 100000000;
        int[] sizes = new int[len];
        int[] times = new int[len];
        int size = 1;
        while (len > 0) {
            Map<Character, Integer> frequencyMap = new HashMap<>();
            System.out.println("Количество символов в пароле: " + size);
            String str = new String();
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                str += ALPHABET[random.nextInt(26)];
            }
            for (char c: str.toCharArray()){
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
            for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("Текущий пароль: " + str);
            boolean key = true;
            long time = System.currentTimeMillis();
            for (int q = 0; key && q < number; q++) {
                String str1 = "";
                for (int i = 0; i < size; i++) {
                    str1 += String.valueOf(ALPHABET[random.nextInt(26)]);
                }
                if (str.equals(str1)) {
                    System.out.println("Сгенерированный пароль: " + str1);
                    key = false;
                }
            }
            int ans = (int) (System.currentTimeMillis() - time);
            sizes[size - 1] = size;
            times[size - 1] = ans;
            System.out.println("Потраченное время: " + ans);
            size++;
            len--;
        }
        final int sz = size - 1;
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(5));
                g2d.fillRect(20, 20, 550, 500);
                g2d.setColor(Color.RED);
                g2d.drawLine(20, 520, 570, 520);
                g2d.drawLine(20, 520, 20, 20);
                g2d.setColor(Color.CYAN);
                g2d.setStroke(new BasicStroke(7));
                g2d.drawLine(20, 520, sizes[0] * 100 + 20, 520 - times[0] / 3);
                for (int i = 0; i < sz - 1; i++){
                    int y = 520 - times[i + 1] / 3;
                    if (y < 20) y = 20;
                    g2d.drawLine(sizes[i] * 100 + 20, 520 - times[i] / 3, sizes[i + 1] * 100 + 20, y);
                }
                g.setFont(new Font("Arial", Font.PLAIN, 24));
                g.setColor(Color.RED);
                g2d.drawString("0", 30, 510);
                g2d.drawString("X", 545, 510);
                g2d.drawString("Y", 30, 50);
            }
        };
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}