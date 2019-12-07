package com.servi.cloud.consumer.javabase.serializable;

import java.io.*;

public class Test {

    public static void main(String[] args) throws Throwable {
        A b = new B();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("d:/a.txt")));
        oos.writeObject(b);
        System.out.println("B 序列化成功！");
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("d:/a.txt")));
        A person = (A) ois.readObject();
        System.out.println("B反序列化成功！");
    }
}
