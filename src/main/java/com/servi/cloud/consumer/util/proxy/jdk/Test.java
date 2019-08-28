package com.servi.cloud.consumer.util.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {

    /**
     * 保存 JDK 动态代理生产的类
     * @param filePath 保存路径，默认在项目路径下生成 $Proxy0.class 文件
     */
    private static void saveProxyFile(String... filePath) {
        if (filePath.length != 0) {
            System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        } else {
            FileOutputStream out = null;
            try {
                byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", RealHandlerInterface.class.getInterfaces());
                out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\IHandlerInterface" + "$Proxy0.class");
                out.write(classFile);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        saveProxyFile();

        Object target = new RealHandlerInterface();

        /**
         * loader：业务对象的类加载器
         * interfaces：业务对象实现的所有接口
         * public static Class<?> getProxyClass(ClassLoader loader, Class<?>... interfaces)
         */
        Class<?> proxyClass = Proxy.getProxyClass(RealHandlerInterface.class.getClassLoader(), RealHandlerInterface.class.getInterfaces());
        InvocationHandler handler = new Handler(target);
        IHandlerInterface userDao = (IHandlerInterface) proxyClass.getConstructor(InvocationHandler.class).newInstance(handler);
        userDao.test();
    }
}
