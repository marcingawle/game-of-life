package pl.pp.project.gol;

import javax.swing.*;

public class MainTimer {
    private final CellService cellService = CellService.getInstance();
    private final MainCanvas mainCanvas = MainCanvas.getInstance();

    private Timer timer;

    public MainTimer() {
        timer = new Timer(500, e -> {
            cellService.update();
            mainCanvas.repaint();
        });
    }

    public void start() {
        timer.start();
    }
}
