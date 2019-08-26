package com.servi.cloud.consumer.gbus;

public interface IResponseAdapter {

    public <T, R> T getResponse(R r);
}
