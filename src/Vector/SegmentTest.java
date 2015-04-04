package Vector;

import org.junit.Test;

public class SegmentTest {

    @Test
    public void testLen() throws Exception {
        CustomVector start = new CustomVector(new double[]{0,0,0});
        CustomVector end = new CustomVector(new double[]{1,1,1});
        Segment<CustomVector> seg = new Segment<>(start, end);
        assert Double.compare(seg.len(), Math.sqrt(3)) == 0;
    }

    @Test
    public void testDistanceTo() throws Exception {
        CustomVector start = new CustomVector(new double[]{0,0,0});
        CustomVector end = new CustomVector(new double[]{1,1,0});
        CustomVector test = new CustomVector(new double[]{0,0,1});
        Segment<CustomVector> seg = new Segment<>(start, end);
        assert Double.compare(seg.distanceTo(test), 1) == 0;

        CustomVector test2 = new CustomVector(new double[]{1,0,0});
        System.out.println(seg.distanceTo(test2));
        assert Double.compare(seg.distanceTo(test2), Math.sqrt(2)/2) == 0;

    }
}