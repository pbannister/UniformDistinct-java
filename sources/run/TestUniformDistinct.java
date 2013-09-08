package run;
import algorithms.UniformCheat;
import algorithms.UniformDivide0;
import algorithms.UniformShuffle;
import algorithms.UniformHash;
import algorithms.UniformHashWithNegate;
import algorithms.UniformHybridHashBitmap;
import algorithms.UniformReservoirSampling;
import algorithms.UniformTree;
public class TestUniformDistinct {
    abstract class Bench {
        abstract void configure();
        abstract int[] generate();
        long run() {
            configure();
            verify();
            long t1 = System.nanoTime();
            for (int i = 0; i < nTimes; ++i) {
                @SuppressWarnings("unused")
                int[] x = generate();
            }
            long t2 = System.nanoTime();
            return t2 - t1;
        }
        abstract void verify();
    }
    public static void main(String[] args) {
        new TestUniformDistinct() {
            {
                nbLimit = 20;
                nTimes = 20;
            }
        }.run();
        new TestUniformDistinct() {
            {
                nbLimit = 18;
                nTimes = 20;
            }
        }.run();
        new TestUniformDistinct() {
            {
                nbLimit = 16;
                nTimes = 20;
            }
        }.run();
    }
    int nbLimit, nLimit, nTimes, nCount, nbCount;
    void check(int[] a) {
        int v1 = -1;
        for (int i = 0; i < nCount; ++i) {
            int v2 = a[i];
            if (v2 <= v1) {
                throw new RuntimeException("Value too small!");
            }
            if (nLimit <= v2) {
                throw new RuntimeException("Value too big!");
            }
        }
    }
    private double rate(long t) {
        return ((nCount * 1000.0 * nTimes) / t);
    }
    void run() {
        nLimit = 1 << nbLimit;
        Bench b1 = new Bench() {
            UniformHash o = new UniformHash();
            @Override void configure() {
                o.nLimit = nLimit;
                o.nCount = nCount;
                o.configure();
            }
            @Override int[] generate() {
                return o.generate();
            }
            @Override void verify() {
                check(generate());
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
            @Override void verify() {
                check(generate());
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
            @Override void verify() {
                check(generate());
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
            @Override void verify() {
                check(generate());
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
            @Override void verify() {
                check(generate());
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
            @Override void verify() {
                check(generate());
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
            @Override void verify() {
                check(generate());
            }
        };
        Bench b8 = new Bench() {
            UniformDivide0 o = new UniformDivide0();
            @Override void configure() {
                o.nLimit = nLimit;
                o.nCount = nCount;
                o.configure();
            }
            @Override int[] generate() {
                return o.generate();
            }
            @Override void verify() {
                check(generate());
            }
        };
        System.out.println("Reporting speed as millions of values per second.  Max: " + nLimit);
        // .................12345678123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
        System.out.println("              hash  hash/neg    hybrid reservoir      tree   shuffle     cheat   divide0         ");
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
            long t8 = b8.run();
            System.out.println(String.format("%6d  %10.2f%10.2f%10.2f%10.2f%10.2f%10.2f%10.2f%10.2f",//
                    (nLimit / nCount), //
                    rate(t1), //
                    rate(t2), //
                    rate(t3), //
                    rate(t4), //
                    rate(t5), //
                    rate(t6), //
                    rate(t7), //
                    rate(t8), //
                    0));
        }
    }
}
