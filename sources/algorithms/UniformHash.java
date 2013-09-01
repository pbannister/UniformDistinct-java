package algorithms;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
public class UniformHash extends UniformDistinctBase {
    @Override public void configure() {
        prepare();
    }
    @Override public int[] generate() {
        HashSet<Integer> s = new HashSet<Integer>();
        while (s.size() < nCount) {
            s.add(r.nextInt(nLimit));
        }
        Iterator<Integer> i = s.iterator();
        for (int k = 0; k < nCount; ++k) {
            aOut[k] = i.next();
        }
        Arrays.sort(aOut, 0, nCount);
        return aOut;
    }
}
