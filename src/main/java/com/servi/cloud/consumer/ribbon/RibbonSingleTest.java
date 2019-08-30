package com.servi.cloud.consumer.ribbon;

import com.google.common.collect.Lists;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import com.servi.cloud.consumer.util.log.ServiLogger;
import rx.Observable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RibbonSingleTest {

    List<Server> serverList = Lists.newArrayList(new Server("localhost", 9003), new Server("localhost", 9004));

    ILoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        RibbonSingleTest test = RibbonSingleTest.class.newInstance();
        for (int i = 0; i < 10; i++) {
            test.request();
        }
    }

    public void request() {
        LoadBalancerCommand.<String>builder().withLoadBalancer(loadBalancer).build().submit((server) -> {
            ServiLogger.log(server.getHost() + ":" + server.getPort());
            return Observable.just(server.getHost() + ":" + server.getPort());
        }).toBlocking().first();
    }
}
