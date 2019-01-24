package com.ohuzenko.l03_2;



import java.util.ArrayList;
import java.util.List;


import static java.util.Collections.*;

public class Main {
    public static void main(String[] args) {

        MyArrayList<Double> list  = new MyArrayList<>(20);
        list.add(10.0);
        list.add(15.0);
        list.add(11.0);

        printMe(list,"My array list was created and elements were added ");

        ArrayList<Double> lst2 = new ArrayList<>();
        lst2.add(60.0);
        lst2.add(70.0);

        addAll(list, 40.0, 50.5);
        printMe(list,"checked work with Collections addAll(Collection<? super T> c, T... elements)");

        copy(list, lst2);
        printMe(list,"checked work with Collections static <T> void copy(List<? super T> dest, List<? extends T> src) ");


        sort(list, Double::compare);
        printMe(list,"checked work with Collections static <T> void sort(List<T> list, Comparator<? super T> c) " );

    }


    private static void printMe(List lst, String eventDescription) {
        System.out.println(eventDescription + "inner array state: ");
        System.out.println(lst.toString());
        System.out.println("\n-----------------------");

    }

}
