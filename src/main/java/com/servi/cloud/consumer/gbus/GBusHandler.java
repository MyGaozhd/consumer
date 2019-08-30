package com.servi.cloud.consumer.gbus;

import com.servi.cloud.consumer.gbus.annotation.GBusInterface;
import com.servi.cloud.consumer.gbus.annotation.GBusMethod;
import com.servi.cloud.consumer.gbus.test.GBusTest;
import com.servi.cloud.consumer.gbus.test.IGBusTest;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GBusHandler implements InvocationHandler {
    private Object target = null;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        GBusInterface gi = null;
        if (method.isAnnotationPresent(GBusInterface.class)) {
            gi = method.getAnnotation(GBusInterface.class);
        }else {
            Class<?>[] is = proxy.getClass().getInterfaces();
            for (int i = 0; i < is.length; i++) {
                if (is[i].isAnnotationPresent(GBusInterface.class)) {
                    gi = is[i].getAnnotation(GBusInterface.class);
                }
            }
        }
        GBusMethod gBus = method.getAnnotation(GBusMethod.class);
        Class<?> clazz = gi.clazz();

        if (clazz == IGBusTest.class) {
            target = new GBusTest();
        }
        //1、如果没有设置方法名称，使用代理接口定义的代理方法名称
        String methodName = StringUtils.isEmpty(gBus.method()) ? method.getName() : gBus.method();

        //2、根据是都自动翻译参数类型，寻找对应的实际接口的实际方法
        Method operateMethod = null;
        if (gBus.autoParameterType()) {
            operateMethod = target.getClass().getDeclaredMethod(methodName, method.getParameterTypes());
        } else {
            operateMethod = target.getClass().getDeclaredMethod(methodName, gBus.parameterTypes());
        }
        //3、调用请求参数转换器，转换请求参数
        IRequestAdapter requestAdapter = gBus.requestAdapter().newInstance();
        Object[] operateArgs = requestAdapter.getParam(args);

        //4、执行请求
        Object result = operateMethod.invoke(target, operateArgs);
        //5、调用返回值转换器，转换返回值
        IResponseAdapter responseAdapter = gBus.responseAdapter().newInstance();
        return responseAdapter.getResponse(result);
    }
}
