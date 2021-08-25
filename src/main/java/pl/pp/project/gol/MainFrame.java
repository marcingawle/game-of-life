package pl.pp.project.gol;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        setTitle("Gra w życie");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        add(MainCanvas.getInstance());
        pack();
        setLocationRelativeTo(null);
    }
}
