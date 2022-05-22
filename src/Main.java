public class Main {
    public static void main(String[] args) {
        GameModel gameModel = new GameModel();
        GameFrame gameFrame = new GameFrame(gameModel);
        ConsoleOvserver consoleOvserver = new ConsoleOvserver(gameModel);
        gameModel.attach(gameFrame);
        gameModel.attach(consoleOvserver);

        keyboardStratergy s = new keyboardStratergy(gameModel, gameFrame);
        mouseStratergy m = new mouseStratergy(gameModel, gameFrame);
    }
}
