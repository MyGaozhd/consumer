package com.servi.cloud.consumer.zk;

import com.servi.cloud.consumer.util.log.ServiLogger;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ZClient implements IZClient {

    public static void main(String[] args) {
        ZooKeeper zl;
        IZClient zClient = new ZClient();
        String path = "/servi";
        //0、设置监听事件
        zClient.addNodeChangeListener(path, (nodePath) -> {
            ServiLogger.log("节点变更listener:" + nodePath);
        });
        //1、创建servi节点
        if (!zClient.exists(path)) {
            ServiLogger.log("创建持久节点：" + path);
            zClient.createPersistent(path, "server");
        }
        //2、打印节点信息
        ServiLogger.log("持久节点：" + zClient.readData(path));

        //3、创建临时节点
        String ePath = path + "/temp";
        if (!zClient.exists(ePath)) {
            ServiLogger.log("创建临时节点：" + ePath);
            zClient.createEphemeral(ePath, ePath);
        }
        ServiLogger.log("临时节点：" + zClient.readData(ePath));

        zClient.addNodeDataChangeListener(ePath, new NodeDataChangeListener() {
            @Override
            public void onChange(String path) {
                ServiLogger.log("节点数据变更listener:" + path);
            }

            @Override
            public void onDelete(String path) {
                ServiLogger.log("节点数据删除listener:" + path);
            }
        });

        //4、修改节点数据
        if (zClient.exists(ePath)) {
            ServiLogger.log("修改临时节点数据：" + ePath);
            zClient.writeData(ePath, "kkk:" + ePath);
        }
        //5、删除临时节点
        if (zClient.exists(ePath)) {
            ServiLogger.log("删除临时节点：" + ePath);
            zClient.delete(ePath);
        }

        if (zClient.exists(ePath)) {
            ServiLogger.log("临时节点：" + zClient.readData(ePath));
        }
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
    public <T> void writeData(String path, T t) {
        this.zkClient.writeData(path, t);
    }

    @Override
    public boolean exists(String path) {
        return this.zkClient.exists(path);
    }

    @Override
    public void close() {
        this.zkClient.close();
    }

    @Override
    public void addNodeChangeListener(String path, NodeChangeListener listener) {
        this.zkClient.subscribeChildChanges(path, (pPath, list) -> {
            ServiLogger.log(pPath);
            for (int i = 0; i < list.size(); i++) {
                ServiLogger.log(list.get(i));
            }
            listener.onChange(pPath);
        });
    }

    @Override
    public void addNodeDataChangeListener(String path, NodeDataChangeListener listener) {
        this.zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                listener.onChange(dataPath);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                listener.onDelete(dataPath);
            }
        });
    }
}
