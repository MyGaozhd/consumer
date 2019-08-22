package com.servi.cloud.consumer.zk;

import org.I0Itec.zkclient.ZkClient;

public class ZClient {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("172.16.86.6:2181",5000);
        System.out.println("ZK 成功建立连接！");
    }
}
