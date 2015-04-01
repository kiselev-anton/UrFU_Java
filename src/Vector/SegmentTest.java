package Vector;

import org.junit.Test;

public class SegmentTest {

    @Test
    public void testLen() throws Exception {
        CustomVector start = new CustomVector(new double[]{0,0,0});
        CustomVector end = new CustomVector(new double[]{1,1,1});
        Segment seg = new Segment<>(start, end);
        assert Double.compare(seg.len(), Math.sqrt(3)) == 0;
    }

    @Test
    public void testDistanceTo() throws Exception {
        CustomVector start = new CustomVector(new double[]{0,0,0});
        CustomVector end = new CustomVector(new double[]{1,1,0});
        CustomVector test = new CustomVector(new double[]{0,0,1});
        Segment seg = new Segment<>(start, end);
        assert Double.compare(seg.distanceTo(test), 1) == 0;
    }
}