package cs.vsu.oop1;



public class Domino {
    private final int left;
    private final int right;

    public Domino(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public Domino flip() {
        return new Domino(right, left);
    }

    @Override
    public String toString() {
        return "(" + left + "|" + right + ")";
    }
}
