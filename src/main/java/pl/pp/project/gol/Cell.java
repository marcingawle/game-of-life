package pl.pp.project.gol;

public class Cell {
    private final int x;
    private final int y;
    private boolean life;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isNeighbor(Cell cell) {
        if (cell == this) {
            return false;
        }

        return cell.getX() <= x + 1 && cell.getX() >= x - 1 &&
                cell.getY() <= y + 1 && cell.getY() >= y - 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }
}
