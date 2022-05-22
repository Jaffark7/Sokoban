import java.awt.event.*;
import javax.swing.event.*;

public class mouseStratergy{
    private GameModel gameModel;
    private GameFrame gameFrame;
    private MouseAdapter Listener;
    
    public mouseStratergy(GameModel gameModel, GameFrame gameFrame){
        this.gameModel = gameModel;
        this.gameFrame = gameFrame;
        addListener();
    }

    public void addListener(){
        Listener = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                x /= 32;
                y /= 32;

                if (x > 0 && x < 7 && y > 0 && y < 7){
                    if(gameModel.getPictures()[y][x-1] == "player"){gameModel.moveRIGHT();}
                    if(gameModel.getPictures()[y][x+1] == "player"){gameModel.moveLEFT();}
                    if(gameModel.getPictures()[y+1][x] == "player"){gameModel.moveUP();}
                    if(gameModel.getPictures()[y-1][x] == "player"){gameModel.moveDOWN();}
                }
            }
        };
        gameFrame.addMouseListener(Listener);
    }

    void removeListener(){
        gameFrame.removeMouseListener(Listener);
    }
}