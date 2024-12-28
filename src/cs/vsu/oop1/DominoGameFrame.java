package cs.vsu.oop1;


import javax.swing.*;
import java.awt.*;

public class DominoGameFrame extends JFrame {
    private final DominoGame game;
    private final DominoBoardPanel boardPanel;
    private final PlayerHandPanel player1Panel;
    private final PlayerHandPanel player2Panel;

    public DominoGameFrame(DominoGame game) {
        this.game = game;

        setTitle("Domino Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Панель для поля
        boardPanel = new DominoBoardPanel(game.getField());
        add(boardPanel, BorderLayout.CENTER);

        // Панель для игрока 2 сверху
        player2Panel = new PlayerHandPanel(game.getPlayer2(), "Player 2", true); // true - игрок сверху
        add(player2Panel, BorderLayout.NORTH);

        // Панель для игрока 1 снизу
        player1Panel = new PlayerHandPanel(game.getPlayer1(), "Player 1", false); // false - игрок снизу
        add(player1Panel, BorderLayout.SOUTH);

        // Кнопка следующего хода
        JButton nextTurnButton = new JButton("Next Turn");
        nextTurnButton.addActionListener(e -> handleNextTurn());
        add(nextTurnButton, BorderLayout.EAST);

        updateUI();
    }

    private void handleNextTurn() {
        game.nextTurn();
        updateUI();
    }

    public void updateUI() {
        boardPanel.updateBoard();
        player1Panel.updateHand();
        player2Panel.updateHand();
    }
}

