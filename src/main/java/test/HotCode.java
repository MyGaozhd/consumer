package test;

public class HotCode {
    private static volatile int n = 0;

    public static void main(String[] args) {
        int i = 0;
        while (i++ < 100_0000) {
            m();
            n();
        }
    }

    public static synchronized void m() {

    }

    public static void n() {
        n++;
    }
}
