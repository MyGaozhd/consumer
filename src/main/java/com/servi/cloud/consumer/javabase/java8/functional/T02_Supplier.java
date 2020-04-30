package com.servi.cloud.consumer.javabase.java8.functional;

/**
 * 提供一个接口只有get方发 返回需要的值.
 * 生产一个数据
 */
public class T02_Supplier {
    public static void main(String[] args) {
        System.out.println(new T02_Supplier().one(() -> "one"));
        new T02_Supplier().two(() -> "two");

        int[] counts = new int[]{1, 2, 3, 4, 5, 6};

        new T02_Supplier().max(() -> {
            int max = counts[0];
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] > max) max = counts[i];
            }
            return max;
        });
    }

    <T> T one(java.util.function.Supplier<T> s) {
        return s.get();
    }

    void two(java.util.function.Supplier s) {
        System.out.println(s.get());
    }

    void max(java.util.function.Supplier<Integer> s) {
        System.out.println(s.get() + 1);
    }
}
