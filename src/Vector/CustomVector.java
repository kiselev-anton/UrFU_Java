package Vector; /**
 * Created by kiselev on 05.03.15.
 */
import java.util.Arrays;

public final class CustomVector implements AbstractVector {

    private double[] values;

    public CustomVector(double[] newValues) {
        values = newValues.clone();
    }

    public int dim() {
        return values.length;
    }

    public AbstractVector add(AbstractVector that) throws Exception {
        if (that instanceof CustomVector) {
            double[] newValues = values.clone();
            for (int i = 0; i < dim(); i++) {
                newValues[i] += ((CustomVector) that).values[i];
            }
            return new CustomVector(newValues);
        } else throw new Exception("Dimensions doesn't match.");
    }

    public AbstractVector multiply(double alpha) {
        double[] newValues = values.clone();
        for (int i = 0; i < dim(); i++) {
            newValues[i] *= alpha;
        }
        return new CustomVector(newValues);
    }

    public AbstractVector sub(AbstractVector that) throws Exception {
        return add(that.multiply(-1));
    }

    public double scalar(AbstractVector that) throws Exception {
        if (that instanceof CustomVector) {
            double result = 0;
            for (int i = 0; i < dim(); i++) {
                result += values[i]*((CustomVector) that).values[i];
            }
            return result;
        } else throw new Exception("Dimensions doesn't match");
    }

    public double length() {
        try {
            return Math.sqrt(scalar(this));
        } catch (Exception e) {
            return 42; // unreachable
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomVector that = (CustomVector) o;

        if (!Arrays.equals(values, that.values)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }
}
