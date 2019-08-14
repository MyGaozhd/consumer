package com.servi.cloud.consumer.annotation;

import com.servi.cloud.consumer.util.log.ServiLogger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AnnotationHandler implements InvocationHandler {

    private final Object target;

    public AnnotationHandler(Object tatarget) {
        this.target = tatarget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        checkTarget(target);
        checkMethod(method);
        Method[] methods = target.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            checkMethod(methods[i]);
        }

        Field[] fields = target.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            checkField(fields[i]);
        }
        Object result = method.invoke(target, args);
        return result;
    }

    private void checkTarget(Object target) {
        if (target.getClass().isAnnotationPresent(ClassCheck.class)) {
            ClassCheck classCheck = target.getClass().getAnnotation(ClassCheck.class);
            ServiLogger.log("checkTarget:" + target.getClass().getCanonicalName() + "-" + classCheck.check());
            ServiLogger.log("checkTarget:" + target.getClass().getCanonicalName() + "-" + classCheck.value());
        }
    }

    private void checkMethod(Method method) {
        method.isAccessible();
        if (method.isAnnotationPresent(MethodCheck.class)) {
            MethodCheck methodCheck = method.getAnnotation(MethodCheck.class);
            ServiLogger.log("checkMethod:" + method.getName() + "-" + methodCheck.check());
            ServiLogger.log("checkMethod:" + method.getName() + "-" + methodCheck.value());
        } else {
            ServiLogger.log("checkMethod:" + method.getName() + "- 未使用注解");
        }
    }

    private void checkField(Field field) throws IllegalAccessException {
        field.setAccessible(true);
        if (!field.isAnnotationPresent(FieldCheck.class)) {
            ServiLogger.log("checkField:" + field.getName() + "- 未使用注解");
            return;
        }

        FieldCheck fieldCheck = field.getAnnotation(FieldCheck.class);
        ServiLogger.log("checkField:" + field.getName() + "-" + fieldCheck.check());
        ServiLogger.log("checkField:" + field.getName() + "-" + fieldCheck.value());


        if (fieldCheck.check() && null == field.get(target)) {
            field.set(target, fieldCheck.value());
        }
        ServiLogger.log(field.getName() + ":" + field.get(target));
    }
}
