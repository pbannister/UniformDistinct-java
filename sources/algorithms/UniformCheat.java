package algorithms;
import java.util.Random;
public class UniformCheat {
    private byte[] aJitter;
    private int[] aOut;
    public int nbCount;
    public int nbLimit;
    private int nCount;
    private int nMask;
    private int nStride;
    private Random r = new Random();
    public void configure() {
        if ((0 == nbLimit) || (nbLimit <= nbCount)) {
            throw new RuntimeException("not allowed");
        }
        // int nLimit = 1 << nbLimit;
        nCount = 1 << nbCount;
        aJitter = new byte[nCount];
        aOut = new int[nCount];
        nStride = nbLimit - nbCount;
        nMask = (1 << nStride) - 1;
    }
    public int[] generate() {
        r.nextBytes(aJitter);
        for (int i = 0; i < nCount; ++i) {
            aOut[i] = (i << nStride) + (nMask & aJitter[i]);
        }
        return aOut;
    }
}
