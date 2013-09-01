package algorithms;
import java.util.BitSet;
class UniformBitmap extends UniformDistinctBase {
    @Override public void configure() {
        prepare();
    }
    @Override public int[] generate() {
        BitSet bs = new BitSet(nLimit);
        int cardinality = 0;
        while (cardinality < nCount) {
            int v = r.nextInt(nLimit);
            if (!bs.get(v)) {
                bs.set(v);
                cardinality++;
            }
        }
        int pos = 0;
        for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i + 1)) {
            aOut[pos++] = i;
        }
        return aOut;
    }
}