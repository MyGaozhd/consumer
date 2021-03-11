package com.servi.cloud.consumer.algorithm;

/**
 * 一致性 Hash 环
 * 多用于缓存定位
 * <p>
 * 定义一组token ,比如 1,2,3,5。
 * 当缓存进来时，计算key的hash值，应该落在1-5之间。加入计算结果是3.则缓存命中在token=3，所在的节点。如果计算结果是4，则落在5所在的节点。
 * 目标结果落在大于等于计算结果的节点上。
 */
public class _07 {


}
