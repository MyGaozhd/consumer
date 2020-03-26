package com.servi.cloud.consumer.javabase.jvm;

import java.io.*;

public class ServiClassLoader extends ClassLoader {

    private String classLoaderName;
    private static final String classExtention = ".class";

    public ServiClassLoader() {
        super();
        this.classLoaderName = "com.servi.cloud.consumer.javabase.jvm.ServiClassLoader";
    }

    @Override
    public String toString() {
        return "[" + this.classLoaderName + "]";
    }

    private byte[] loadClassData(String className) {

        className = className.replace(".", "/");
        InputStream in = null;
        ByteArrayOutputStream baos = null;
        try {
            in = new FileInputStream(new File(className + classExtention));
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
            Class<?> clazz = classLoader.loadClass("com.servi.cloud.consumer.javabase.jvm.ClassLoaderTest");
            Object o = clazz.newInstance();
            System.out.println(o);
            System.out.println(o.getClass().getClassLoader());
            ClassLoaderTest classLoaderTest = new ClassLoaderTest();
            System.out.println(classLoaderTest.equals(o));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
