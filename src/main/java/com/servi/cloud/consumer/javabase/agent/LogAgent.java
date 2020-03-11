package com.servi.cloud.consumer.javabase.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * 打印日志
 */
public class LogAgent {
    public static void premain(String agentOps, Instrumentation inst) {
        instrument(agentOps, inst);
    }

    public static void agentmain(String agentOps, Instrumentation inst) {
        instrument(agentOps, inst);
    }

    private static void instrument(String agentOps, Instrumentation inst) {
        System.out.println(agentOps);
        inst.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                System.out.println(className);
                return classfileBuffer;
            }
        });
    }
}
