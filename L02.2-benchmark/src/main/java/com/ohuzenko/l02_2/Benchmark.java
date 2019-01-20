package com.ohuzenko.l02_2;

import java.util.function.Supplier;


@SuppressWarnings("MismatchedReadAndWriteOfArray")
class Benchmark {
    private Object[] array;
    private final int size;

    Benchmark() {
        this(10 * 1000 * 1000);
    }

    Benchmark(int size) {
        this.size = size;
        array = new Object[size];
    }


    Benchmark measure(Supplier<Object> supplier, String name) {
        clean();
        System.out.print(name + " size measuring: ");
        long memChanges = getMemChanges(() -> {
            int i = 0;
            while (i < size) {
                array[i] = supplier.get();
                i++;
            }
        });
        System.out.println( Math.round((double) memChanges / size));
        return this;
    }

    void clean() {
        System.out.println("Waiting for GS collect is done... ");
        array = new Object[size];
        BlockingGC.collect();
    }


    private static long getMemChanges(Runnable create) {
        Runtime runtime = Runtime.getRuntime();
        BlockingGC.collect();
        long memBefore = runtime.totalMemory() - runtime.freeMemory();
        create.run();
        BlockingGC.collect();
        long memAfter = runtime.totalMemory() - runtime.freeMemory();
        return memAfter - memBefore;
    }
}