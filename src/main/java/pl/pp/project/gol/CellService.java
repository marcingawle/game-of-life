package pl.pp.project.gol;

import java.util.ArrayList;
import java.util.List;

import static pl.pp.project.gol.Config.COLS;
import static pl.pp.project.gol.Config.ROWS;

public class CellService {
    private final List<Cell> cells = new ArrayList<>();

    private static final CellService INSTANCE = new CellService();

    public static CellService getInstance() {
        return INSTANCE;
    }

    private CellService() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                cells.add(new Cell(j, i));
            }
        }

        getByCords(3, 3).setLife(true);
        getByCords(3, 4).setLife(true);
        getByCords(3, 5).setLife(true);

        getByCords(10, 4).setLife(true);
        getByCords(11, 4).setLife(true);
        getByCords(12, 4).setLife(true);

        getByCords(10, 10).setLife(true);
        getByCords(10, 11).setLife(true);
        getByCords(11, 10).setLife(true);
        getByCords(11, 11).setLife(true);
    }

    public Cell getByCords(int x, int y) {
        return cells.stream()
                .filter(cell -> cell.getX() == x && cell.getY() == y)
                .findFirst().orElse(null);
    }

    public List<Cell> getAllCells() {
        return cells;
    }

    public void update() {
        List<Cell> newCells = new ArrayList<>();
        List<Cell> deathCells = new ArrayList<>();

        for (Cell cell : cells) {
            long lifeNeighbors = cells.stream()
                    .filter(cell::isNeighbor)
                    .filter(Cell::isLife)
                    .count();

            if (cell.isLife()) {
                if (lifeNeighbors < 2 || lifeNeighbors > 3) {
                    deathCells.add(cell);
                }
            } else {
                if (lifeNeighbors == 3) {
                    newCells.add(cell);
                }
            }
        }

        for (Cell cell : deathCells) {
            cell.setLife(false);
        }

        for (Cell cell : newCells) {
            cell.setLife(true);
        }
    }
}
