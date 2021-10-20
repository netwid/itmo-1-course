import java.util.ArrayList;
import java.lang.Math;

public class Laba1 {
    private static short[] firstTask() {
        return shortRange(4, 19, 2);
    }

    private static float[] secondTask() {
        float[] x = new float[19];

        for (int i = 0; i < 19; i++)
            x[i] = randFloat(-8, 13);

        return x;
    }

    private static double[][] thirdTask(short[] a, float[] x) {
        double[][] b = new double[8][19];

        for (int i = 0; i < b.length; i++)
            for (int j = 0; j < b[0].length; j++) {
                if (a[i] == 8)
                    b[i][j] = 1.0 / 2 * Math.pow(Math.asin((x[j] + 2.5) / 21), Math.sin(x[j]) / 2);
                else if (a[i] == 6 || a[i] == 10 || a[i] == 12 || a[i] == 14)
                    b[i][j] = Math.asin(Math.pow(Math.sin(x[j]), 2));
                else
                    b[i][j] = Math.cos(Math.pow(
                        0.5 - Math.PI / Math.tan(x[j]),
                        Math.cos(Math.log(Math.abs(x[j])))
                    ));
            }

        return b;
    }

    private static short[] shortRange(int start, int end, int step) {
        ArrayList<Short> res = new ArrayList<>();

        for (int i = start; i < end; i += step)
            res.add((short) i);

        short[] primitiveRes = new short[res.size()];
        for (int i = 0; i < res.size(); i++)
            primitiveRes[i] = res.get(i);

        return primitiveRes;
    }

    private static float randFloat(float min, float max) {
        return (float) (Math.random() * (max - min) + min);
    }

    private static void print2DDouble(double[][] array) {
        for (double[] row : array) {
            for (double cell : row)
                System.out.format("%-8.3f", cell);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        short[] a = firstTask();
        float[] x = secondTask();
        double[][] b = thirdTask(a, x);
        print2DDouble(b);
    }
}
