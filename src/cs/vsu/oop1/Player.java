package cs.vsu.oop1;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Domino> hand;

    public Player(List<Domino> hand) {
        this.hand = new ArrayList<>(hand);
    }

    public List<Domino> getHand() {
        return hand;
    }

    public void addDomino(Domino domino) {
        hand.add(domino);
    }

    public void removeDomino(Domino domino) {
        hand.remove(domino);
    }

    @Override
    public String toString() {
        return hand.toString();
    }
}
