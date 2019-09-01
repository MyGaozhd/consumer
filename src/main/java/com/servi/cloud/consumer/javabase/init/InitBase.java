package com.servi.cloud.consumer.javabase.init;

import com.servi.cloud.consumer.gbus.GBusRouter;
import com.servi.cloud.consumer.util.log.ServiLogger;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;

public class InitBase {
    private static String a = "这是一个静态字段aaaa";
    private String b = "这是一个普通字段bbbbbb";

    static {
        ServiLogger.log("这是一个static方法-static");
    }

    {
        ServiLogger.log("这是一个非static方法");
    }

    public InitBase() {
        ServiLogger.log(this.getClass().getName());
        ServiLogger.log("这是无参构造方法");

        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (field.isAnnotationPresent(Autowired.class)) {
                ServiLogger.log(field.getName());
                try {
                    field.set(this, GBusRouter.operate(field.getType()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    ServiLogger.log("这是无参构造方法");
                }
            }
        }
    }

//    public InitBase(String kk) {
//        this();
//        ServiLogger.log("这是构造方法");
//    }
}
