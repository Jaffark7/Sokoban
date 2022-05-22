import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class GameModel {

    private String state;
    private String wall =  "wall";
    private String player = "player";
    private String blank = "blank";
    private String blankmarked = "blankmarked";
    private String crate = "crate";
    private String cratemarked = "cratemarked";
    private String previous = blank;

    private int PlayerRowLocation;
    private int PlayerColLocation;

    private String [][] Pictures;
    private List<ChangeListener> observers = new ArrayList<>();

    public GameModel(){
        Pictures = new String[8][8];
        PlayerRowLocation = 6;
        PlayerColLocation = 6;
        createLevel();
    }

    public void createLevel(){
        state = "running";
        /**********WALLS**********/
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Pictures[i][j] = wall;
            }
        }
        /**********BLANK**********/
        Pictures[1][1] = blank;
        Pictures[1][2] = blank;
        Pictures[1][4] = blank;
        Pictures[1][5] = blank;
        Pictures[1][6] = blank;
        Pictures[1][2] = blank;
        Pictures[2][1] = blank;
        Pictures[2][2] = blank;
        Pictures[2][3] = blank;
        Pictures[2][5] = blank;
        Pictures[2][6] = blank;
        Pictures[3][1] = blank;
        Pictures[3][2] = blank;
        Pictures[3][6] = blank;
        Pictures[4][2] = blank;
        Pictures[4][4] = blank;
        Pictures[4][5] = blank;
        Pictures[4][6] = blank;
        Pictures[5][3] = blank;
        Pictures[5][6] = blank;
        Pictures[6][3] = blank;
        Pictures[6][4] = blank;
        Pictures[6][5] = blank;
        /**********BLANK_MARKED**********/
        Pictures[5][1] = blankmarked;
        Pictures[5][2] = blankmarked;
        Pictures[6][1] = blankmarked;
        Pictures[6][2] = blankmarked;
        /**********CRATE**********/
        Pictures[2][4] = crate;
        Pictures[3][4] = crate;
        Pictures[3][5] = crate;
        Pictures[5][4] = crate;
        /**********PLAYER**********/
        Pictures[6][6] = player;

        PlayerRowLocation = 6;
        PlayerColLocation = 6;

        notifyListeners();
    }

    public void attach(ChangeListener ob){
        observers.add(ob);
    }

    public void notifyListeners(){
        for (ChangeListener l : observers){
            l.stateChanged(new ChangeEvent(this));
        }
    }

    public void updateState(){
 
        if(Pictures[5][1] == cratemarked && Pictures[5][2] == cratemarked && Pictures[6][1] == cratemarked && Pictures[6][2] == cratemarked){
        state = "victory";
        notifyListeners();
        createLevel();
        }
    }    

    public String getState() {
        return state;
    }

    public String[][] getPictures() {
        return Pictures;
    }

    public void moveUP(){
        String nextBlock = Pictures[PlayerRowLocation-1][PlayerColLocation];
        String nextNextBlock = null;
        if (PlayerRowLocation > 1){
            nextNextBlock = Pictures[PlayerRowLocation-2][PlayerColLocation];
        }
        if ( nextBlock != wall && nextBlock != crate && nextBlock != cratemarked) {
            Pictures[PlayerRowLocation][PlayerColLocation] = previous;
            previous = Pictures[PlayerRowLocation - 1][PlayerColLocation];
            Pictures[PlayerRowLocation - 1][PlayerColLocation] = player;
            PlayerRowLocation--;
        }
        if ((nextBlock == crate || nextBlock == cratemarked) && nextNextBlock!= null && nextNextBlock != wall && nextNextBlock != crate && nextNextBlock != cratemarked){
            Pictures[PlayerRowLocation][PlayerColLocation] = previous;
            if (nextBlock == crate)
                previous = blank;
            else if (nextBlock == cratemarked)
                previous = blankmarked;

            if(nextNextBlock == blankmarked)
                Pictures[PlayerRowLocation-2][PlayerColLocation] = cratemarked;
            else
                Pictures[PlayerRowLocation-2][PlayerColLocation] = crate;

            Pictures[PlayerRowLocation-1][PlayerColLocation] = player;
            PlayerRowLocation--;
        }
        notifyListeners();
        updateState();
    }
    public void moveDOWN(){
        String nextBlock = Pictures[PlayerRowLocation+1][PlayerColLocation];
        String nextNextBlock = null;
        if (PlayerRowLocation < 6){
            nextNextBlock = Pictures[PlayerRowLocation+2][PlayerColLocation];
        }

        if ( nextBlock != wall && nextBlock != crate && nextBlock != cratemarked){
            Pictures[PlayerRowLocation][PlayerColLocation] = previous;
            previous = Pictures[PlayerRowLocation+1][PlayerColLocation];
            Pictures[PlayerRowLocation+1][PlayerColLocation] = player;
            PlayerRowLocation++;
        }
        if ((nextBlock == crate || nextBlock == cratemarked) && nextNextBlock!= null && nextNextBlock != wall && nextNextBlock != crate && nextNextBlock != cratemarked){
            Pictures[PlayerRowLocation][PlayerColLocation] = previous;
            if (nextBlock == crate)
                previous = blank;
            else if (nextBlock == cratemarked)
                previous = blankmarked;

            if (nextNextBlock == blankmarked)
                Pictures[PlayerRowLocation+2][PlayerColLocation] = cratemarked;
            else
                Pictures[PlayerRowLocation+2][PlayerColLocation] = crate;

            Pictures[PlayerRowLocation+1][PlayerColLocation] = player;
            PlayerRowLocation++;
        }
        notifyListeners();
        updateState();
    }
    public void moveRIGHT(){
        String nextBlock = Pictures[PlayerRowLocation][PlayerColLocation+1];
        String nextNextBlock = null;
        if (PlayerColLocation < 6){
            nextNextBlock = Pictures[PlayerRowLocation][PlayerColLocation+2];
        }
        if ( nextBlock != wall && nextBlock != crate && nextBlock != cratemarked){
            Pictures[PlayerRowLocation][PlayerColLocation] = previous;
            previous = Pictures[PlayerRowLocation][PlayerColLocation+1];
            Pictures[PlayerRowLocation][PlayerColLocation+1] = player;
            PlayerColLocation++;
        }
        if ((nextBlock == crate || nextBlock == cratemarked) && nextNextBlock!= null && nextNextBlock != wall && nextNextBlock != crate && nextNextBlock != cratemarked){
            Pictures[PlayerRowLocation][PlayerColLocation] = previous;
            if (nextBlock == crate)
                previous = blank;
            else if (nextBlock == cratemarked)
                previous = blankmarked;

            if (nextNextBlock == blankmarked)
                Pictures[PlayerRowLocation][PlayerColLocation+2] = cratemarked;
            else
                Pictures[PlayerRowLocation][PlayerColLocation+2] = crate;

            Pictures[PlayerRowLocation][PlayerColLocation+1] = player;
            PlayerColLocation++;
        }
        notifyListeners();
        updateState();
    }
    public void moveLEFT(){
        String nextBlock = Pictures[PlayerRowLocation][PlayerColLocation-1];
        String nextNextBlock = null;
        if (PlayerColLocation > 1){
            nextNextBlock = Pictures[PlayerRowLocation][PlayerColLocation-2];
        }
        if ( nextBlock != wall && nextBlock != crate && nextBlock != cratemarked){
            Pictures[PlayerRowLocation][PlayerColLocation] = previous;
            previous = Pictures[PlayerRowLocation][PlayerColLocation-1];
            Pictures[PlayerRowLocation][PlayerColLocation-1] = player;
            PlayerColLocation--;
        }
        if ((nextBlock == crate || nextBlock == cratemarked) && nextNextBlock!= null && nextNextBlock != wall && nextNextBlock != crate && nextNextBlock != cratemarked){
            Pictures[PlayerRowLocation][PlayerColLocation] = previous;
            if (nextBlock == crate)
                previous = blank;
            else if (nextBlock == cratemarked)
                previous = blankmarked;

            if (nextNextBlock == blankmarked)
                Pictures[PlayerRowLocation][PlayerColLocation-2] = cratemarked;
            else
                Pictures[PlayerRowLocation][PlayerColLocation-2] = crate;

            Pictures[PlayerRowLocation][PlayerColLocation-1] = player;
            PlayerColLocation--;
        }
        notifyListeners();
        updateState();
    }
}
