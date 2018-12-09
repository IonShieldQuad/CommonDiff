package core;

import graphics.GraphDisplay;
import math.DiffSolver;
import math.PointDouble;
import math.RungeKutta2;
import org.mariuszgromada.math.mxparser.Function;

import javax.swing.*;
import java.util.List;

public class MainWindow {
    private JPanel rootPanel;
    private JTextArea log;
    private JTextField lowerX;
    private JTextField upperX;
    private JButton calculateButton;
    private JTextField functionField;
    private JTextField lowerY;
    private JTextField upperY;
    private GraphDisplay graph;
    
    private MainWindow() {
        initComponents();
    }
    
    private void initComponents() {
        calculateButton.addActionListener(e -> calculate());
    }
    
    private void calculate() {
        try {
            log.setText("");
            DiffSolver solver = new RungeKutta2(Double.parseDouble(upperY.getText()));
            Function function = new Function(functionField.getText());
            List<PointDouble> res = solver.solve(function::calculate, Double.parseDouble(lowerX.getText()), Double.parseDouble(lowerY.getText()), Double.parseDouble(upperX.getText()));
            updateGraph(res);
            log.append("\nResult:");
            res.forEach(p -> log.append("\nx = " + p.getX() + ", y = " + p.getY()));
        }
        catch (NumberFormatException e) {
            log.append("\nInvalid input format");
        }
    }
    
    private void updateGraph(List<PointDouble> points) {
        graph.setPoints(points);
        graph.repaint();
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("CommonDiff");
        MainWindow gui = new MainWindow();
        frame.setContentPane(gui.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
