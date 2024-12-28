package cs.vsu.oop1;

public class Main {
    public static void main(String[] args) {
        DominoGame game = DominoGame.getInstance();
        DominoGameFrame frame = new DominoGameFrame(game);
        frame.setVisible(true);
    }
}