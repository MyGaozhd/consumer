package com.servi.cloud.consumer.gbus;

public interface IRequestAdapter {

    public Object[] getParam(Object[] args);

    public static class DefaultRequestAdapter implements IRequestAdapter {

        @Override
        public Object[] getParam(Object[] args) {
            return args;
        }
    }
}
