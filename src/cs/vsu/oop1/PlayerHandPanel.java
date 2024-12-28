package cs.vsu.oop1;
import javax.swing.*;
import java.awt.*;

public class PlayerHandPanel extends JPanel {
    private final Player player;
    private final String playerName;
    private final boolean isTopPlayer; // Добавляем флаг для определения, верхний ли игрок

    public PlayerHandPanel(Player player, String playerName, boolean isTopPlayer) {
        this.player = player;
        this.playerName = playerName;
        this.isTopPlayer = isTopPlayer;  // true если игрок сверху, false если снизу
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }

    public void updateHand() {
        removeAll();

        // Панель с костяшками игрока
        JPanel handPanel = new JPanel(new FlowLayout());
        for (Domino domino : player.getHand()) {
            JButton button = new JButton(domino.toString());
            button.addActionListener(e -> handleDominoClick(domino));
            handPanel.add(button);
        }

        // Кнопка для взятия из резерва (находится внизу для нижнего игрока и сверху для верхнего)
        if (player == DominoGame.getInstance().getCurrentPlayer()) {
            JButton reserveButton = new JButton("Take from Reserve");
            reserveButton.addActionListener(e -> takeFromReserve());
            handPanel.add(reserveButton, isTopPlayer ? BorderLayout.NORTH : BorderLayout.SOUTH);
        }

        add(handPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void handleDominoClick(Domino domino) {
        String[] options = {"Left", "Right"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Place domino " + domino + " on which side?",
                playerName,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0 || choice == 1) {
            String side = (choice == 0) ? "left" : "right";

            if (DominoGame.getInstance().makeMove(domino, side)) {
                player.removeDomino(domino);
                JOptionPane.showMessageDialog(this, "Move successful!");
                updateUI();
                if (DominoGame.getInstance().checkForWinner()) {
                    JOptionPane.showMessageDialog(this, playerName + " wins!");
                    // Закрытие игры
                    System.exit(0);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid move!");
            }
        }
    }

    private void takeFromReserve() {
        if (DominoGame.getInstance().takeFromReserve(player)) {
            JOptionPane.showMessageDialog(this, "You took a domino from the reserve.");
            updateUI();
            DominoGame.getInstance().nextTurn();
        } else {
            JOptionPane.showMessageDialog(this, "Reserve is empty!");
        }
    }
}

