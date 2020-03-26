package com.servi.cloud.consumer.javabase.jvm;


public class ClassLoaderTest06 {
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);

        while (classLoader!=null){
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }

        String[] strs = new String[0];
        System.out.println(strs.getClass().getClassLoader());

        ClassLoaderTest06[] ClassLoaderTest06s = new ClassLoaderTest06[0];
        System.out.println(ClassLoaderTest06s.getClass().getClassLoader());

        int[] ints = new int[0];
        System.out.println(ints.getClass().getClassLoader());

        System.out.println(ints.getClass());
    }
}
