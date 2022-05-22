import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PictureComponent extends JComponent {
    /**
     *
     */
    //private static final long serialVersionUID = 1L;

    private File file;

    public PictureComponent(File f) {
        file = f;
    }

    public void setFile(File f) {
        file = f;
        repaint();
    }

    public File getFile() {
        return file;
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        try {
            g2.drawImage(ImageIO.read(file), null, 0, 0);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
