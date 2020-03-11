package com.servi.cloud.consumer.javabase.innerclass;

public class OuterClass {
    public static void main(String[] args) {
        new OuterClass().test("aa");
    }

    private void test(final String s) {
        new InnerClass() {
            @Override
            public void in() {
                System.out.println(s);
            }
        }.in();
    }

    abstract class InnerClass {
        public abstract void in();
    }
}
