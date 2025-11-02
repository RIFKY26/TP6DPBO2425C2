import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {

    // --- KELOMPOK ATRIBUT ---
    int frameWidth = 360;
    int frameHeight = 640;
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeStartPosX = frameWidth;
    int getPipeStartPosY = 0; 
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view;
    Image birdImage;
    Player player;
    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    int pipeVelocityX = -2;
    boolean gameOver;
    Image backgroundImage;

    // --- ATRIBUT BARU UNTUK SKOR ---
    private App app;    
    private int score; 
    // --- CONSTRUCTOR ---
    public Logic(App app) {
        this.app = app;
        this.score = 0;

        birdImage = new ImageIcon(getClass().getResource("./assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        lowerPipeImage = new ImageIcon(getClass().getResource("./assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("./assets/upperPipe.png")).getImage();
        backgroundImage = new ImageIcon(getClass().getResource("./assets/background.png")).getImage();
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

        gameOver = false;
    }

    // --- KELOMPOK METHOD ---
    public void move() {
        // Terapkan gravitasi
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosy(player.getPosy() + player.getVelocityY());

        // Cek batas atas layar
        player.setPosy(Math.max(player.getPosy(), 0));

        // Gerakkan pipa
        for (int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            // --- TAMBAHAN: LOGIKA SKOR ---
            // Cek jika pipa belum dihitung (passed) DAN player sudah melewatinya
            if (!pipe.isPassed() && player.getPosx() > pipe.getPosX() + pipe.getWidth()) {

                // Tambah skor hanya sekali per pipa
                // (Kita asumsikan pipa atas adalah index genap: 0, 2, 4, ...)
                if (i % 2 == 0) {
                    score++;
                    app.updateScore(score); // Panggil method di App untuk update JLabel
                }
                pipe.setPassed(true); // Tandai pipa ini sudah dilewati
            }
            // --- AKHIR LOGIKA SKOR ---
        }

        // Panggil metode terpisah untuk mengecek semua tabrakan
        checkCollisions();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            gameLoop.stop();
            pipesCooldown.stop();
            return;
        }

        move();
        if (view != null) {
            view.repaint();
        }
    }

    public void setView(View view) {
        this.view = view;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void placePipes(){
        int randomPosY = (int) (getPipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);

    }

    public void checkCollisions() {
       
        Rectangle playerBounds = new Rectangle(player.getPosx(), player.getPosy(), player.getWidth(), player.getHeight());

        // 1. Cek tabrakan dengan pipa
        for (Pipe pipe : pipes) {
            Rectangle pipeBounds = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
            if (playerBounds.intersects(pipeBounds)) {
                gameOver = true;
                return;
            }
        }

        // 2. Cek tabrakan dengan batas bawah (jatuh)
        if (player.getPosy() + player.getHeight() >= frameHeight) {
            gameOver = true; // Cukup set flag.
        }
    }

    public void restartGame() {
        // 1. Kembalikan player ke posisi awal
        player.setPosy(playerStartPosY);
        player.setVelocityY(0);

        // 2. Kosongkan semua pipa dari layar
        pipes.clear();

        // 3. Set status game kembali berjalan
        gameOver = false;

        // --- TAMBAHAN: RESET SKOR ---
        score = 0;
        app.updateScore(score); // Update JLabel kembali ke 0

        // 4. Mulai ulang kedua timer
        gameLoop.start();
        pipesCooldown.start();
    }

    // --- METHOD DARI KEYLISTENER ---
    @Override
    public void keyTyped(KeyEvent e) {
        // Tidak digunakan
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameLoop.isRunning()) {
                player.setVelocityY(-10); // Melompat
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {
            // Hanya restart jika game sudah berakhir
            if (gameOver) {
                restartGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Tidak digunakan
    }

}
