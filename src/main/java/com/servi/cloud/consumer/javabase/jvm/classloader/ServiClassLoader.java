package com.servi.cloud.consumer.javabase.jvm.classloader;

import java.io.*;
import java.lang.reflect.Field;

public class ServiClassLoader extends ClassLoader {

    private String classLoaderName;
    private static final String classExtention = ".class";
    private String path = "extend";


    public ServiClassLoader() {
        super();
        this.classLoaderName = "com.servi.cloud.consumer.javabase.jvm.classloader.ServiClassLoader";
    }

    public ServiClassLoader(ClassLoader parent) {
        super(parent);
        System.out.println(parent);
        this.classLoaderName = "com.servi.cloud.consumer.javabase.jvm.classloader.ServiClassLoader";
    }

    @Override
    public String toString() {
        return "[" + this.classLoaderName + "]";
    }

    private byte[] loadClassData(String className) {

        className = className.replace(".", "\\");
        InputStream in = null;
        ByteArrayOutputStream baos = null;
        try {
            in = new FileInputStream(new File(path + "\\"+ className + classExtention));
            byte[] bytes = null;
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while ((ch = in.read()) != -1) {
                baos.write(ch);
            }
            bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);
        return super.defineClass(name, data, 0, data.length);
    }

    public static void main(String[] args) {
        ServiClassLoader classLoader = new ServiClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass("Main");
            System.out.println(clazz.hashCode());
            Object o = clazz.newInstance();
            System.out.println(o);
            System.out.println(o.getClass().getClassLoader());
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                System.out.println(fields[i].getName());
            }
            ServiClassLoader classLoader2 = new ServiClassLoader();
            Class<?> clazz2 = classLoader2.loadClass("Main");
            System.out.println(clazz2.hashCode());

            ServiClassLoader classLoader3 = new ServiClassLoader(classLoader2);
            Class<?> clazz3 = classLoader3.loadClass("Main");
            System.out.println(clazz3.hashCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
