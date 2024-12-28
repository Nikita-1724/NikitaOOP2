package cs.vsu.oop1;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DominoBoardPanel extends JPanel {
    private final List<Domino> field;

    public DominoBoardPanel(List<Domino> field) {
        this.field = field;
        setLayout(new FlowLayout());
        setBackground(Color.LIGHT_GRAY);
    }

    public void updateBoard() {
        removeAll();
        for (Domino domino : field) {
            add(new JLabel(domino.toString()));
        }
        revalidate();
        repaint();
    }
}



