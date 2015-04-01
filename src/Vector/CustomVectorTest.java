package Vector;

import org.junit.Test;

public class CustomVectorTest {

    @Test
    public void testAdd() throws Exception {
        CustomVector vector1 = new CustomVector(new double[]{1,2,3,4,5,6});
        CustomVector vector2 = new CustomVector(new double[]{-1,-2,-3,-4,-5,-6});
        assert vector1.add(vector2).equals(new CustomVector(new double[]{0,0,0,0,0,0}));
        CustomVector vectorError = new CustomVector(new double[]{1});
        try {
            AbstractVector test = vector1.add(vectorError);
            assert false;
        } catch (Exception e) {
            assert true;
        }
    }

    @Test
    public void testMult() throws Exception {
        CustomVector vector1 = new CustomVector(new double[]{1,2,3,4,5,6});
        assert vector1.multiply(-1).equals(new CustomVector(new double[]{-1, -2, -3, -4, -5, -6}));
    }

    @Test
    public void testSub() throws Exception {
        CustomVector vector1 = new CustomVector(new double[]{1,2,3,4,5,6});
        assert vector1.sub(vector1).equals(new CustomVector(new double[]{0,0,0,0,0,0}));
    }

    @Test
    public void testScalar() throws Exception {
        CustomVector vector1 = new CustomVector(new double[]{1,-1,1,-1,1,-1});
        CustomVector vector2 = new CustomVector(new double[]{1,1,1,1,1,1});
        assert Double.compare(vector1.scalar(vector2),0) == 0;
    }

    @Test
    public void testLength() throws Exception {
        CustomVector vector1 = new CustomVector(new double[]{1});
        assert Double.compare(vector1.length(), 1) == 0;
    }

}