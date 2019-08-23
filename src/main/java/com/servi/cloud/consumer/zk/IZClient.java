package com.servi.cloud.consumer.zk;

public interface IZClient {

    /**
     * 创建持久节点
     *
     * @param path 路径
     * @param t
     * @param <T>
     */
    public <T> void createPersistent(String path, T t);

    /**
     * 创建临时节点
     *
     * @param path 路径
     * @param t
     * @param <T>
     */
    public <T> void createEphemeral(String path, T t);

    /**
     * 删除路径
     *
     * @param path 路径
     */
    public void delete(String path);

    /**
     * 递归删除路径
     *
     * @param path 路径
     */
    public void deleteRecursive(String path);

    /**
     * 读取节点数据
     *
     * @param path 路径
     * @return
     */
    public String readData(String path);

    /**
     * 读取节点数据,返回指定数据类型
     *
     * @param path 路径
     * @return
     */
    public <T> T readData(String path, Class<T> clazz);

    /**
     * 写数据
     *
     * @param path
     * @param t
     * @param <T>
     */
    public <T> void writeData(String path, T t);

    /**
     * 指定节点是否存在
     *
     * @param path
     * @return
     */
    public boolean exists(String path);

    /**
     * 关闭连接
     */
    public void close();

    /**
     * 节点变更监听
     *
     * @param path
     * @param listener
     */
    public void addNodeChangeListener(String path, NodeChangeListener listener);

    /**
     * 节点数据变更监听
     *
     * @param path
     * @param listener
     */
    public void addNodeDataChangeListener(String path, NodeDataChangeListener listener);

    /**
     * 节点变更监听
     */
    public interface NodeChangeListener {

        public void onChange(String path);
    }

    /**
     * 节点数据变更监听
     */
    public interface NodeDataChangeListener {

        public void onChange(String path);

        public void onDelete(String path);
    }


}
