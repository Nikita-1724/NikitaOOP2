package cs.vsu.oop1;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DominoGame {
    private final List<Domino> field;
    private final List<Domino> reserve;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    private static DominoGame instance;

    public static DominoGame getInstance() {
        if (instance == null) {
            instance = new DominoGame();
        }
        return instance;
    }

    private DominoGame() {
        // Генерация костяшек
        List<Domino> dominoes = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                dominoes.add(new Domino(i, j));
            }
        }

        // Перемешивание костяшек
        Random rand = new Random();
        for (int i = dominoes.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Domino temp = dominoes.get(i);
            dominoes.set(i, dominoes.get(j));
            dominoes.set(j, temp);
        }

        // Раздача костяшек
        player1 = new Player(dominoes.subList(0, 7));
        player2 = new Player(dominoes.subList(7, 14));
        reserve = new ArrayList<>(dominoes.subList(14, dominoes.size()));
        field = new ArrayList<>();

        // Устанавливаем стартовую костяшку
        field.add(new Domino(6, 6));

        // Устанавливаем текущего игрока
        currentPlayer = player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public List<Domino> getField() {
        return field;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean makeMove(Domino domino, String side) {
        Domino first = field.get(0);
        Domino last = field.get(field.size() - 1);

        if (side.equalsIgnoreCase("left")) {
            if (domino.getRight() == first.getLeft()) {
                field.add(0, domino);
                return true;
            } else if (domino.getLeft() == first.getLeft()) {
                field.add(0, domino.flip());
                return true;
            }
        } else if (side.equalsIgnoreCase("right")) {
            if (domino.getLeft() == last.getRight()) {
                field.add(domino);
                return true;
            } else if (domino.getRight() == last.getRight()) {
                field.add(domino.flip());
                return true;
            }
        }
        return false;
    }

    public boolean takeFromReserve(Player player) {
        if (!reserve.isEmpty()) {
            Domino taken = reserve.remove(0);
            player.addDomino(taken);
            return true;
        }
        return false;
    }

    public void nextTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean checkForWinner() {
        return player1.getHand().isEmpty() || player2.getHand().isEmpty();
    }
}

