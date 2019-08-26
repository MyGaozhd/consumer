package com.servi.cloud.consumer.gbus.test;

import com.servi.cloud.consumer.gbus.IResponseAdapter;

public class GBusTestResponse implements IResponseAdapter {
    @Override
    public <T, R> T getResponse(R r) {
        return (T) r;
    }
}
