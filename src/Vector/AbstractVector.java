package Vector;

/**
 * Created by kiselev on 05.03.15.
 */
public interface AbstractVector {
    public int dim();
    public AbstractVector add(AbstractVector that) throws Exception;
    public AbstractVector multiply(double alpha);
    public AbstractVector sub(AbstractVector that) throws Exception;
    public double scalar(AbstractVector that) throws Exception;
    public double length();
}