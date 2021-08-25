package pl.pp.project.gol;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        try {
            populate();
        } catch (IOException e) {
            System.out.println("Problem with reading from file");
        }
    }

    private void populate() throws IOException {
        String fileName = "dart.cells";

        int y = 85;

        for (String line : Files.readAllLines(Paths.get(fileName))) {
            for (int x = 0; x<line.length(); x++) {
                if (line.charAt(x) == 'O') {
                    getByCords(x, y).setLife(true);
                }
            }

            y++;
        }


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
