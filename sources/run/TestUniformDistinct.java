package run;
import algorithms.UniformCheat;
import algorithms.UniformShuffle;
import algorithms.UniformHash;
import algorithms.UniformHashWithNegate;
import algorithms.UniformHybridHashBitmap;
import algorithms.UniformReservoirSampling;
import algorithms.UniformTree;
public class TestUniformDistinct {
    abstract class Bench {
        abstract int[] generate();
        abstract void configure();
        long run() {
            configure();
            long t1 = System.nanoTime();
            for (int i = 0; i < nTimes; ++i) {
                @SuppressWarnings("unused")
                int[] x = generate();
            }
            long t2 = System.nanoTime();
            return t2 - t1;
        }
    }
    int nbLimit, nTimes;
    int nCount;
    int nbCount;
    private double rate(long t) {
        return ((nCount * 1000.0 * nTimes) / t);
    }
    void run() {
        final int nLimit = 1 << nbLimit;
        Bench b1 = new Bench() {
            UniformHash o = new UniformHash();
            @Override int[] generate() {
                return o.generate();
            }
            @Override void configure() {
                o.nLimit = nLimit;
                o.nCount = nCount;
                o.configure();
            }
        };
        Bench b2 = new Bench() {
            UniformHashWithNegate o = new UniformHashWithNegate();
            @Override void configure() {
                o.nLimit = nLimit;
                o.nCount = nCount;
                o.configure();
            }
            @Override int[] generate() {
                return o.generate();
            }
        };
        Bench b3 = new Bench() {
            UniformHybridHashBitmap o = new UniformHybridHashBitmap();
            @Override void configure() {
                o.nLimit = nLimit;
                o.nCount = nCount;
                o.T = 2048;
                o.configure();
            }
            @Override int[] generate() {
                return o.generate();
            }
        };
        Bench b4 = new Bench() {
            UniformReservoirSampling o = new UniformReservoirSampling();
            @Override void configure() {
                o.nLimit = nLimit;
                o.nCount = nCount;
                o.configure();
            }
            @Override int[] generate() {
                return o.generate();
            }
        };
        Bench b5 = new Bench() {
            UniformTree o = new UniformTree();
            @Override void configure() {
                o.nLimit = nLimit;
                o.nCount = nCount;
                o.configure();
            }
            @Override int[] generate() {
                return o.generate();
            }
        };
        Bench b6 = new Bench() {
            UniformShuffle o = new UniformShuffle();
            @Override void configure() {
                o.nLimit = nLimit;
                o.nCount = nCount;
                o.configure();
            }
            @Override int[] generate() {
                return o.generate();
            }
        };
        Bench b7 = new Bench() {
            UniformCheat o = new UniformCheat();
            @Override void configure() {
                o.nbLimit = nbLimit;
                o.nbCount = nbCount;
                o.configure();
            }
            @Override int[] generate() {
                return o.generate();
            }
        };
        System.out.println("reporting speed Max: " + nLimit);
        // .................1234567812345678901234567890123456789012345678901234567890123456789012345678901234567890
        System.out.println("              hash  hash/neg    hybrid reservoir      tree   shuffle     cheat          ");
        // From 256 generated to limit/2 generated.
        for (int k = 8; k < nbLimit; ++k) {
            nbCount = k;
            nCount = 1 << k;
            long t1 = b1.run();
            long t2 = b2.run();
            long t3 = b3.run();
            long t4 = b4.run();
            long t5 = b5.run();
            long t6 = b6.run();
            long t7 = b7.run();
            System.out.println(String.format("%6d  %10.2f%10.2f%10.2f%10.2f%10.2f%10.2f%10.2f",//
                    (nLimit / nCount), //
                    rate(t1), //
                    rate(t2), //
                    rate(t3), //
                    rate(t4), //
                    rate(t5), //
                    rate(t6), //
                    rate(t7), //
                    0));
        }
    }
    public static void main(String[] args) {
        new TestUniformDistinct() {
            {
                nbLimit = 20;
                nTimes = 10;
            }
        }.run();
        new TestUniformDistinct() {
            {
                nbLimit = 18;
                nTimes = 10;
            }
        }.run();
        new TestUniformDistinct() {
            {
                nbLimit = 16;
                nTimes = 10;
            }
        }.run();
    }
}
