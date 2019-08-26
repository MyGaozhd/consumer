package com.servi.cloud.consumer.gbus.test;

import com.servi.cloud.consumer.gbus.IRequestAdapter;

public class GBusTestRequest implements IRequestAdapter {
    @Override
    public Object[] getParam(Object[] args) {
        return args;
    }
}
