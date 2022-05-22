import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyboardStratergy{

    private GameModel gameModel;
    private GameFrame gameFrame;
    KeyListener keyListener;


    public keyboardStratergy(GameModel gameModel, GameFrame gameFrame){
        this.gameModel = gameModel;
        this.gameFrame = gameFrame;
        addListener();
    }

    public void addListener(){
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == 'r'){gameModel.createLevel();}
                if (e.getKeyChar() == 'd'){gameModel.moveRIGHT();}
                if (e.getKeyChar() == 'a'){gameModel.moveLEFT();}
                if (e.getKeyChar() == 'w'){gameModel.moveUP();}
                if (e.getKeyChar() == 's'){gameModel.moveDOWN();}
            }
        };
        gameFrame.addKeyListener(keyListener);
    }

    void removeListener(){
        gameFrame.removeKeyListener(keyListener);
    }
}
