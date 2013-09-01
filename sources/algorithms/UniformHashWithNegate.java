package algorithms;
public class UniformHashWithNegate extends UniformDistinctBase {
    UniformHash o = new UniformHash();
    @Override public void configure() {
        o.nLimit = nLimit;
        if ((2 * nCount) < nLimit) {
            o.nCount = nCount;
        } else {
            o.nCount = nLimit - nCount;
        }
        o.configure();
    }
    @Override public int[] generate() {
        if (nCount == o.nCount) {
            return o.generate();
        }
        int[] a1 = o.generate();
        int i = 0;
        int c = 0;
        for (int j = 0; j < o.nCount; ++j) {
            int v = a1[j];
            for (; i < v; ++i) {
                aOut[c++] = i;
            }
            ++i;
        }
        while (c < aOut.length) {
            aOut[c++] = i++;
        }
        return aOut;
    }
}
