package lecture.ch10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArrListTest {
    public static void main(String[] args) {
        ArrayList cities = new ArrayList();
        cities.add("newyork");
        cities.add("seoul");
        System.out.println(cities.toString());
        String cities_2[] = new String[2];
        cities.toArray(cities_2);
        for(String el : cities_2){
            System.out.print(el + " ");
        }
        System.out.println();
        ArrayList cities_3 = new ArrayList(Arrays.asList(cities_2));
        System.out.println(cities_3.toString());

        System.out.println();

        ArrayList numAL = new ArrayList();
        numAL.add(7); numAL.add(1); numAL.add(5);

        Object temp = Collections.max(numAL);
        System.out.println(temp);

    }
}
