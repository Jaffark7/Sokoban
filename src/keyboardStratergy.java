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
                if (e.getKeyCode() == 39){gameModel.moveRIGHT();}
                if (e.getKeyCode() == 37){gameModel.moveLEFT();}
                if (e.getKeyCode() == 38){gameModel.moveUP();}
                if (e.getKeyCode() == 40){gameModel.moveDOWN();}
            }
        };
        gameFrame.addKeyListener(keyListener);
    }

    void removeListener(){
        gameFrame.removeKeyListener(keyListener);
    }
}
