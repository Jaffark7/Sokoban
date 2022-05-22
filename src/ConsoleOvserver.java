import java.util.Arrays;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConsoleOvserver implements ChangeListener {
    private GameModel gameModel;

    public ConsoleOvserver(GameModel gameModel){
        this.gameModel = gameModel;
    }

    public void printModel(){
        
        String [][] arr = gameModel.getPictures();
        /*
        for (int i = 0; i < arr.length;i++) {
            for (int j = 0; j < 8; j++) {
                
            }
        }
        */
        System.out.println(Arrays.deepToString(arr).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        System.out.println("\n\n");

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        printModel();
    }
}
