package com.servi.cloud.consumer.gbus;

import com.servi.cloud.consumer.gbus.annotation.GBus;
import com.servi.cloud.consumer.gbus.test.GBusTest;
import com.servi.cloud.consumer.gbus.test.IGBusTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GBusHandler implements InvocationHandler {
    private Object target = null;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        GBus gBus = method.getAnnotation(GBus.class);
        Class<?> clazz = gBus.clazz();

        if (clazz == IGBusTest.class) {
            target = new GBusTest();
        }
        String methodName = gBus.method();

        Method operateMethod = target.getClass().getDeclaredMethod(methodName, method.getParameterTypes());
        IRequestAdapter requestAdapter = gBus.requestAdapter().newInstance();
        Object[] operateArgs = requestAdapter.getParam(args);
        Object result = operateMethod.invoke(target, operateArgs);
        IResponseAdapter responseAdapter = gBus.responseAdapter().newInstance();
        return responseAdapter.getResponse(result);
    }
}
