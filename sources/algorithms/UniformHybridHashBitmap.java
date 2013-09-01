package algorithms;
public class UniformHybridHashBitmap extends UniformDistinctBase {
    UniformHash o1 = new UniformHash();
    UniformBitmap o2 = new UniformBitmap();
    public int T;
    @Override public void configure() {
        o1.nLimit = o2.nLimit = nLimit;
        o1.nCount = o2.nCount = nCount;
        o1.configure();
        o2.configure();
    }
    @Override public int[] generate() {
        return ((nLimit < (T * nCount)) ? o2.generate() : o1.generate());
    }
}
