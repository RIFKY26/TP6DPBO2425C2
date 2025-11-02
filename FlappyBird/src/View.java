import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    int width = 360;
    int height = 640;
    private Logic logic;

    public View(Logic logic) {
        this.logic = logic;
        setPreferredSize(new Dimension(width, height));
        //setBackground(Color.cyan);

        // Membuat panel ini bisa fokus menerima input
        setFocusable(true);
        // Mendaftarkan kelas Logic sebagai pendengar keyboard
        addKeyListener(logic);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(logic.getBackgroundImage(), 0, 0, width, height, null);
        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosx(), player.getPosy(),
                    player.getWidth(), player.getHeight(), null);
        }

        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null){
            for (int i = 0; i < pipes.size(); i++){
                Pipe pipe = pipes.get(i);
                // --- TYPO DIPERBAIKI DI SINI ---
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }
    }
}