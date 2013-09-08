package algorithms;
import java.util.Random;
public class UniformDivide0 {
    public int nLimit;
    public int nCount;
    int[] aOut;
    int iOut;
    Random r = new Random();
    public void configure() {
        if (nLimit <= nCount) {
            throw new RuntimeException("not possible");
        }
        aOut = new int[nCount];
    }
    public int[] generate() {
        iOut = 0;
        fill(0, nLimit, nCount);
        if (iOut != nCount) {
            throw new RuntimeException("less than wanted!!!");
        }
        return aOut;
    }
    private void fill(int vMin, int vMax, int nValues) {
        if (0 == nValues) {
            return;
        }
        int dv = vMax - vMin;
        int v1 = vMin;
        int v2 = v1 + (dv >> 1);
        int v3 = vMax;
        int dv1 = v2 - v1;
        int dv2 = v3 - v2;
        int n1 = r.nextInt(nValues);
        int n2 = nValues - n1;
        if (dv1 <= n1) {
            for (int v = v1; v < v2; ++v) {
                aOut[iOut++] = v;
            }
            n1 = dv1;
            n2 = nValues - n1;
            fill(v2, v3, n2);
        } else if (dv2 <= n2) {
            n2 = dv2;
            n1 = nValues - n2;
            fill(v1, v2, n1);
            for (int v = v2; v < v3; ++v) {
                aOut[iOut++] = v;
            }
        } else {
            fill(v1, v2, n1);
            fill(v2, v3, n2);
        }
    }
}
