package math;

import java.util.function.DoubleUnaryOperator;

public class M {
    public static final double EPS = 10e-3;
    
    public static double d(DoubleUnaryOperator f, double x, int o) {
        if (o < 0) {
            throw new IllegalArgumentException("" + o);
        }
        if (o == 0) {
            return f.applyAsDouble(x);
        }
        return (d(f, x + EPS, o - 1) - d(f, x, o - 1)) / EPS;
    }
}
