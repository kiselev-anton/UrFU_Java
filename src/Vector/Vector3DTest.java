package Vector;

import org.junit.Test;

public class Vector3DTest {

    @Test
    public void testAdd() throws Exception {
        Vector3D vector1 = new Vector3D(1,2,3);
        Vector3D vector2 = new Vector3D(-1,-2,-3);
        assert vector1.add(vector2).equals(new Vector3D(0,0,0));
        assert vector1.add(vector1).equals(new Vector3D(2,4,6));
    }

    @Test
    public void testMul() throws Exception {
        Vector3D vector = (Vector3D) new Vector3D(1,2,3).multiply(0);
        assert vector.equals(new Vector3D(0,0,0));
        Vector3D vector1 = (Vector3D) new Vector3D(5,6,7).multiply(2);
        assert vector1.equals(new Vector3D(10,12,14));
    }

    @Test
    public void testSub() throws Exception {
        Vector3D vector1 = new Vector3D(1,2,3);
        Vector3D vector2 = new Vector3D(1,2,3);
        assert vector1.sub(vector2).equals(new Vector3D(0,0,0));
    }

    @Test
    public void testScalar() throws Exception {
        Vector3D vector1 = new Vector3D(1,2,1);
        Vector3D vector2 = new Vector3D(3,-2,1);
        assert vector1.scalar(vector2) == 0;
    }

    @Test
    public void testLength() throws Exception {
        Vector3D vector = new Vector3D(1,1,1);
        assert vector.length() == Math.sqrt(3);
    }

    @Test
    public void testCross() throws Exception {
        Vector3D vector1 = new Vector3D(1,0,0);
        Vector3D vector2 = new Vector3D(0,1,0);
        assert vector1.cross(vector2).equals(new Vector3D(0,0,1));
    }

    @Test
    public void testDistanceFromPointToLine() throws Exception {
        assert Vector3D.distanceFromPointToLine(new Vector3D(0,0,1),
                new Vector3D(1,0,0), new Vector3D(-1,0,0)) == 1;
    }

    @Test
    public void testFindFourthSquareCorner() throws Exception {
        assert Vector3D.findFourthSquareCorner(new Vector3D(0,0,0), new Vector3D(1999567899,0,0), new Vector3D(0,1999567899,0))
                .equals(new Vector3D(1999567899,1999567899,0));
        assert Vector3D.findFourthSquareCorner(new Vector3D(0,0,0), new Vector3D(0,0,-1), new Vector3D(0,-1,0))
                .equals(new Vector3D(0, -1, -1));

    }

}