package algorithms;
import java.util.Iterator;
import java.util.TreeSet;
public class UniformTree extends UniformDistinctBase {
    @Override public void configure() {
        prepare();
    }
    @Override public int[] generate() {
        TreeSet<Integer> s = new TreeSet<Integer>();
        while (s.size() < nCount) {
            s.add(new Integer(r.nextInt(nLimit)));
        }
        Iterator<Integer> i = s.iterator();
        for (int k = 0; k < nCount; ++k) {
            aOut[k] = i.next().intValue();
        }
        return aOut;
    }
}
