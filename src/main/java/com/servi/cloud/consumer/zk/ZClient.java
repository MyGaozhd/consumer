package com.servi.cloud.consumer.zk;

import com.servi.cloud.consumer.util.log.ServiLogger;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ZClient implements IZClient {

    public static void main(String[] args) {
        IZClient zClient = new ZClient();
        String path = "/servi";
        //1、创建servi节点
        if (!zClient.exists(path)) {
            ServiLogger.log("创建持久节点：" + path);
            zClient.createPersistent(path, "server");
        }
        //2、打印节点信息
        ServiLogger.log("持久节点：" +zClient.readData(path));

        //3、创建临时节点
        String ePath = path + "/temp";
        if (!zClient.exists(ePath)) {
            ServiLogger.log("创建临时节点：" + ePath);
            zClient.createEphemeral(ePath, ePath);
        }
        ServiLogger.log("临时节点：" + zClient.readData(ePath));

        zClient.close();
    }

    private ZkClient zkClient = null;

    public ZClient() {
        zkClient = new ZkClient("172.16.86.6:2181", 5000);
        ServiLogger.log("zk 连接成功");
    }

    @Override
    public <T> void createPersistent(String path, T t) {
        this.zkClient.createPersistent(path, t);
    }

    @Override
    public <T> void createEphemeral(String path, T t) {
        this.zkClient.createEphemeral(path, t);
    }

    @Override
    public void delete(String path) {
        this.zkClient.delete(path);
    }

    @Override
    public void deleteRecursive(String path) {
        this.zkClient.deleteRecursive(path);
    }

    @Override
    public String readData(String path) {
        return this.zkClient.readData(path);
    }

    @Override
    public <T> T readData(String path, Class<T> clazz) {
        return this.zkClient.readData(path, true);
    }

    @Override
    public boolean exists(String path) {
        return this.zkClient.exists(path);
    }

    @Override
    public void close() {
        this.zkClient.close();
    }
}
