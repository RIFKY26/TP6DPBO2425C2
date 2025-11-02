import javax.swing.*;
import java.awt.*;

public class App {
    private Logic logic;
    private View view;
    private JLabel scoreLabel;

    public App() {
        // 1. Setup Frame
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // 2. Set Layout
        frame.setLayout(new BorderLayout());

        // 3. Buat Label Skor
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 32));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(scoreLabel, BorderLayout.NORTH);

        // 4. Inisialisasi Logic
        logic = new Logic(this);

        // 5. Inisialisasi View
        view = new View(logic);
        logic.setView(view);

        // 6. Tambahkan View ke Frame
        frame.add(view, BorderLayout.CENTER);

        // 7. Selesaikan Frame
        frame.pack();
        frame.setVisible(true);

        // 8. Minta Fokus Keyboard
        view.requestFocusInWindow();
    }

    public void updateScore(int score) {
        // Menggunakan variabel instance 'scoreLabel'
        scoreLabel.setText("Score: " + score);
    }

}

