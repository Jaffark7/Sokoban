import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.File;


public class GameFrame extends JFrame implements ChangeListener {

    private GameModel gameModel;
    private JPanel panel = new JPanel();
    private PictureComponent [][] Pictures = new PictureComponent[8][8];

    public GameFrame(GameModel gameModel){
        this.gameModel = gameModel;

        panel.setLayout(new GridLayout(8,8));
        panel.setPreferredSize(new Dimension(256, 256));

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Pictures[i][j] = new PictureComponent(new File(gameModel.getPictures()[i][j]+".png"));
                panel.add(Pictures[i][j]);
            }
        }

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(256, 256));
        setTitle("sokoban");
        add(panel);
        setVisible(true);
    }

    public void updateBoard(){

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Pictures[i][j].setFile(new File(gameModel.getPictures()[i][j]+".png"));

            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(gameModel.getState() == "victory"){
            JOptionPane.showMessageDialog(null, "Victory");
        }
        updateBoard();
        repaint();
    }
}
