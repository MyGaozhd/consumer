package com.servi.cloud.consumer.xfire;

import com.servi.cloud.consumer.util.log.ServiLogger;

public class XfireTest {

    static String request = "<?xml version='1.0' encoding='UTF-8'?><params><factorycode>tchzt2</factorycode><servername>getOrgs</servername><datasource>mrd_k8s</datasource></params>";

    public static void main(String[] args) {
        String result = WSUtil.getWebserviceResponse("http://www.nc-cloud.com/uapws/service/nc.imag.itf.ws.IImageInterfaceService?wsdl", "imageInterfaceService", request);
        ServiLogger.log("nc-cloud:" + result);

      //  result = WSUtil.getWebserviceResponse("http://172.20.4.121/uapws/service/nc.imag.itf.ws.IImageInterfaceService?wsdl", "imageInterfaceService", request);
       // ServiLogger.log("172.20.4.121:" + result);
    }
}
