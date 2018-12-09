package math;

import java.util.List;
import java.util.function.DoubleBinaryOperator;

public interface DiffSolver {
    List<PointDouble> solve(DoubleBinaryOperator function, double x, double y, double b);
}
