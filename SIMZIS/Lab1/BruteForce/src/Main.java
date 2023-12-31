import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Пример с JLabel и координатами");

        // Устанавливаем null layout
        frame.setLayout(null);

        JLabel label = new JLabel("\u2713");

        // Устанавливаем координаты и размеры компоненты
        label.setBounds(50, 50, 50, 50); // (x, y, width, height)

        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}
