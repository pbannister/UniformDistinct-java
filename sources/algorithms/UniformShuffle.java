package algorithms;
import java.util.Arrays;
public class UniformShuffle extends UniformDistinctBase {
    private int[] aDeck;
    @Override public void configure() {
        aDeck = new int[nLimit];
        for (int i = 0; i < nLimit; ++i) {
            aDeck[i] = i;
        }
    }
    @Override public int[] generate() {
        prepare();
        for (int i1 = 0; i1 < nCount; ++i1) {
            int i2 = r.nextInt(nLimit);
            int v = aDeck[i1];
            aDeck[i1] = aDeck[i2];
            aDeck[i2] = v;
        }
        for (int i = 0; i < nCount; ++i) {
            aOut[i] = aDeck[i];
        }
        Arrays.sort(aOut, 0, nCount); // this makes the algorithm unsuited.
        return aOut;
    }
}