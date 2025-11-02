import java.awt.Image;

public class Player {
    private int posx;
    private int posy;
    private int width;
    private int height;
    private Image image;
    private int velocityY;

    public Player(int posx, int posy, int width, int height, Image image) {
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
        this.image = image;
        this.velocityY = 0;
    }

    // Getter dan Setter
    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
}