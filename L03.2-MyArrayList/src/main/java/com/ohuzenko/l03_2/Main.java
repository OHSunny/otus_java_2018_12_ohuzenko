package com.ohuzenko.l03_2;

import java.util.ArrayList;

import static java.util.Collections.*;

public class Main {
    public static void main(String[] args) {

        MyArrayList<Double> list  = new MyArrayList<>(20);
        list.add(10.0);
        list.add(15.0);
        list.add(11.0);

        list.printMe("My array list was created and elements were added ");

        ArrayList<Double> lst2 = new ArrayList<>();
        lst2.add(60.0);
        lst2.add(70.0);

        addAll(list, 40.0, 50.5);
        list.printMe("checked work with Collections addAll(Collection<? super T> c, T... elements)");

        copy(list, lst2);
        list.printMe("checked work with Collections static <T> void copy(List<? super T> dest, List<? extends T> src) ");


        sort(list, Main::compare);
        list.printMe("checked work with Collections static <T> void sort(List<T> list, Comparator<? super T> c) " );

    }

    private static int compare(Double a, Double b) {
        return a.compareTo(b);
    }
}
