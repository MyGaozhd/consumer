package com.servi.cloud.consumer.xfire;

import java.net.MalformedURLException;
import java.net.URL;

import com.servi.cloud.consumer.util.log.ServiLogger;
import org.apache.log4j.Logger;
import org.codehaus.xfire.client.Client;

/**
 * webservice调用方法工具类
 *
 */
public class WSUtil {

    /**
     * webservice调用方法通用方法
     *
     * @param url
     * @param method
     * @param request
     * @return
     */
    public static String getWebserviceResponse(String url, String method, String request) {
        long start = System.currentTimeMillis();
        Client client = null;
        Object[] resultObjects = null;
        try {
//			WSClient.trustAllHttpsCertificates();//添加对https的支持
            client = new Client(new URL(url));
            resultObjects = client.invoke(method, new Object[]{request});
      //      ServiLogger.log("返回报文：" + (String) resultObjects[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            ServiLogger.log(e.getMessage());
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            ServiLogger.log(e.getMessage());
            return "false";
        }
        return (String) resultObjects[0];
    }

}
