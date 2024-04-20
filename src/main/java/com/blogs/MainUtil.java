package com.blogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class MainUtil {
    public static void main(String[] args) {

        Movie m1 = new Movie();
        m1.setName("bbb");
        m1.setRating(8);
        m1.setYear(1999);

        Movie m2 = new Movie();
        m2.setName("aaa");
        m2.setRating(6);
        m2.setYear(1986);


        Movie m3 = new Movie();
        m3.setName("ccc");
        m3.setRating(9);
        m3.setYear(2000);

        //Add object to ArrayList
        ArrayList<Movie> list = new ArrayList<Movie>();
        list.add(m1);
        list.add(m2);
        list.add(m3);

        MovieName movieName = new MovieName();
        Collections.sort(list,movieName);
        for (Movie m:list
             ) {
            System.out.println(m.getName());
            System.out.println(m.getRating());
            System.out.println(m.getYear());

        }
    }

    }
