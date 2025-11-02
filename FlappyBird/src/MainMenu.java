import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Kelas MainMenu, sekarang menjadi entry point program.
 * Menampilkan JFrame dengan dua tombol.
 */
public class MainMenu extends JFrame implements ActionListener {

    JButton playButton;
    JButton closeButton;

    public MainMenu() {
        // --- Setup JFrame Main Menu ---
        setTitle("Flappy Bird - Main Menu");
        setSize(360, 640); // Samakan dengan ukuran game
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // --- Setup Panel untuk Tombol ---
        // Kita pakai GridBagLayout agar tombol bisa rapi di tengah
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 10, 0); // Jarak antar tombol (atas, kiri, bawah, kanan)

        // --- Judul ---
        JLabel titleLabel = new JLabel("Flappy Bird");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        add(titleLabel, gbc);

        // --- Tombol Mulai (Play) ---
        playButton = new JButton("Mulai Mainkan");
        playButton.setFont(new Font("Arial", Font.PLAIN, 24));
        playButton.addActionListener(this); // Daftarkan listener
        add(playButton, gbc);

        // --- Tombol Tutup (Close) ---
        closeButton = new JButton("Tutup Program");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 24));
        closeButton.addActionListener(this); // Daftarkan listener
        add(closeButton, gbc);

        // Tampilkan JFrame
        setVisible(true);
    }

    /**
     * Menangani semua klik tombol
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            // --- Mulai Game ---
            // 1. Buat instance dari game App
            new App();
            // 2. Tutup window main menu ini
            this.dispose();
        } else if (e.getSource() == closeButton) {
            // --- Tutup Program ---
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
