package Vector;

public class Vector3D implements AbstractVector {

    private double x,y,z;

    public int dim() { return 3; }

    public Vector3D(double a, double b, double c) {
        x = a; y = b; z = c;
    }

    public AbstractVector add(AbstractVector that) throws Exception {
        if (that.dim() == 3) {
            Vector3D that3D = (Vector3D) that;
            return new Vector3D(x + that3D.x, y + that3D.y, z + that3D.z);
        }
        else throw new Exception("Dimensions doesn't match.");
    }

    public AbstractVector multiply(double alpha) {
        return new Vector3D(x * alpha, y * alpha, z * alpha);
    }

    public AbstractVector sub(AbstractVector that) throws Exception {
        return add(that.multiply(-1));
    }

    public double scalar(AbstractVector that) throws Exception {
        if (that.dim() == 3) {
            Vector3D that3D = (Vector3D) that;
            return (x * that3D.x + y * that3D.y + z * that3D.z);
        } else {
            throw new Exception("Dimensions doesn't match.");
        }
    }

    public double length() {
        try {
            return Math.sqrt(scalar(this));
        } catch (Exception e) {
            return 42; // Unreachable statement.
        }
    }

    public AbstractVector cross(AbstractVector that) {
        Vector3D that3D = (Vector3D) that;
        return new Vector3D(
                y * that3D.z - z * that3D.y,
                z * that3D.x - x * that3D.z,
                x * that3D.y - y * that3D.x
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3D vector = (Vector3D) o;

        if (Double.compare(vector.x, x) != 0) return false;
        if (Double.compare(vector.y, y) != 0) return false;
        if (Double.compare(vector.z, z) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public static double distanceFromPointToLine(Vector3D point,
                                           Vector3D pointFromLine1, Vector3D pointFromLine2) throws Exception {
        Vector3D lineVector = (Vector3D) pointFromLine1.sub(pointFromLine2);
        return ((Vector3D) point.sub(pointFromLine1)).cross(lineVector).length() / lineVector.length();
    }

    public static AbstractVector findFourthSquareCorner(AbstractVector point1, AbstractVector point2, AbstractVector point3) throws Exception {
        double distance12 = point1.sub(point2).length();
        double distance13 = point1.sub(point3).length();
        double distance23 = point2.sub(point3).length();
        if (distance12 == distance13 && Double.compare(distance23, Math.sqrt(2)*distance12) == 0) {
            return point1.add(point2).add(point3);
        }
        else if (distance12 == distance23 && Double.compare(distance13, Math.sqrt(2)*distance12) == 0) {
            return point2.add(point1).add(point3);
        }
        else if (distance13 == distance23 && Double.compare(distance12, Math.sqrt(2)*distance13) == 0) {
            return point3.add(point1).add(point2);
        } else
            return null;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(
                Vector3D.distanceFromPointToLine(new Vector3D(0,0,1),
                        new Vector3D(1,0,0), new Vector3D(-1,0,0)) +
        " == " + 1);


    }
}