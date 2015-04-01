package Vector;

/**
 * Created by kiselev on 05.03.15.
 */
public class Segment<TVector extends AbstractVector> {
    private TVector start, end;

    public Segment(TVector end, TVector start) throws Exception {
        if (end.dim() == start.dim()) {
            this.end = end;
            this.start = start;
        } else throw new Exception("Dimensions doesn't match.");
    }

    private static double distanceFromPointToPoint(AbstractVector vec1, AbstractVector vec2) {
        try {
            return vec1.sub(vec2).length();
        } catch (Exception e) {
            return 42; // unreachable within this method
        }
    }

    public TVector getEnd() {
        return end;
    }

    public TVector getStart() {
        return start;
    }

    public double len() {
        return Segment.distanceFromPointToPoint(start, end);
    }

    public double distanceTo(TVector point) {
        try {
            TVector lineVector = (TVector) end.sub(start);
            TVector pointToLineVector = (TVector) point.sub(start);
            if (pointToLineVector.scalar(lineVector) <= 0)
                return Segment.distanceFromPointToPoint(point, start);
            else if (lineVector.scalar(lineVector) <= pointToLineVector.scalar(lineVector))
                return Segment.distanceFromPointToPoint(point, end);
            else {
                double b = lineVector.scalar(pointToLineVector) / lineVector.scalar(lineVector);
                AbstractVector bv = start.add(lineVector.multiply(b));
                return distanceFromPointToPoint(point, bv);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 42; // unreachable
        }
    }

}
