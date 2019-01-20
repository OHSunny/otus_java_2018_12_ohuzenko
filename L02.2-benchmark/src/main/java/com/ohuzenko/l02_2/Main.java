package com.ohuzenko.l02_2;


//VM options -Xmx512m -Xms512m
public class Main {

    public static void main(String... args) {

        Benchmark benchmark = new Benchmark();

        benchmark.measure(Object::new, "Object");
        benchmark.measure(String::new, "String with pool");
        benchmark.measure(() -> new String(new byte[0]), "String");
        benchmark.measure(() -> new Benchmark(10), "Benchmark(10)");
    }
}