package math;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class RungeKutta2 implements DiffSolver {
    
    private double h;
    
    private double nextY(DoubleBinaryOperator f, double x, double y) {
        double k1 = h * f.applyAsDouble(x, y);
        double k2 = h * f.applyAsDouble(x + (2d / 3d) * h, y + (2d / 3d) * k1);
        
        return y + (k1 + k2) / 2;
    }
    
    private double nextX(double x) {
        return x + h;
    }
    
    public RungeKutta2(double h) {
        this.h = h;
    }
    
    @Override
    public List<PointDouble> solve(DoubleBinaryOperator function, double x, double y, double b) {
        List<PointDouble> list = new ArrayList<>();
        double val = y;
        double arg = x;
        list.add(new PointDouble(arg, val));
        int n = (int) Math.abs((b - x) / h);
        for (int i = 0; i < n; i++) {
            val = nextY(function, arg, val);
            arg = nextX(arg);
            list.add(new PointDouble(arg, val));
        }
        return list;
    }
}
