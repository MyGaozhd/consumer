package com.servi.cloud.consumer.javabase.list;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.ArrayList;

@ComponentScan(includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION)})
public class ArrayTest {
    public static void main(String[] args) {
        ArrayList list = null;
        list.add("");
        list.addAll(null);
        list.trimToSize();
        int a = 10;
        int b = 9 >> 1;

        System.out.println(b);
    }
}
