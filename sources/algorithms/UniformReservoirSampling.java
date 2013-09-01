package algorithms;
import java.util.Arrays;
public class UniformReservoirSampling extends UniformDistinctBase {
    @Override public void configure() {
        prepare();
    }
    @Override public int[] generate() {
        for (int k = 0; k < nCount; ++k) {
            aOut[k] = k;
        }
        for (int k = nCount; k < nLimit; ++k) {
            int v = r.nextInt(k + 1);
            if (v < nCount) {
                aOut[v] = k;
            }
        }
        Arrays.sort(aOut, 0, nCount);
        return aOut;
    }
}
