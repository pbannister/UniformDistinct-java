package algorithms;
import java.util.Random;
public abstract class UniformDistinctBase implements UniformDistinct {
    protected int[] aOut = new int[0];
    public int nCount;
    public int nLimit;
    protected Random r = new Random();
    protected void prepare() {
        if (nLimit <= nCount) {
            throw new RuntimeException("not possible");
        }
        if (aOut.length < nCount) {
            aOut = new int[nCount];
        }
    }
}