package com.servi.cloud.consumer.gbus.test;

import com.servi.cloud.consumer.gbus.GBusRouter;
import com.servi.cloud.consumer.util.log.ServiLogger;

public class GBusTest implements IGBusTest {
    public static void main(String[] args) {
        String a = GBusRouter.operate(GBusInterface.class).test("a", "b");
        ServiLogger.log(a);

        String b = GBusRouter.operate(GBusInterface.class).test1("a", "b");
        ServiLogger.log(b);

        String c = GBusRouter.operate(GBusInterface.class).test2("a", "b");
        ServiLogger.log(c);
    }

    @Override
    public String test(String a, String b) {
        ServiLogger.log(a + b);
        return a + b;
    }
}
