package com.servi.cloud.consumer.xfire;

import com.google.gson.Gson;
import com.servi.cloud.consumer.util.log.ServiLogger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XfireTest {

    public static void main(String[] args) {
        StringBuffer request = new StringBuffer();
        request.append("<?xml version='1.0' encoding='GBK' ?>");
        request.append("<CMDATA>");
        request.append("<TRADETYPE>10</TRADETYPE>");
        request.append("<CIP></CIP>");
        request.append("<SYSTEM_CODE></SYSTEM_CODE>");
        request.append("<BRANCH_NO></BRANCH_NO>");
        request.append("<USER_NO></USER_NO>");
        request.append("<BUSI_SERIAL_NO>" + "1002111000000110CM2Z"
                + "</BUSI_SERIAL_NO>");

        request.append("<BATCH>");
        request.append("<BATCHID></BATCHID>");
        request.append("<DOCUMENTS>");
        request.append("<DOCUMENT>");
        request.append("<DOCNAME></DOCNAME>");
        request.append("<DESC></DESC>");
        request.append("<FILES>");
        request.append("<FILE>");
        request.append("<VERSION></VERSION>");
        request.append("<FILE_SEQ></FILE_SEQ>");
        request.append("<FILE_TYPE></FILE_TYPE>");
        request.append("<FILE_NAME></FILE_NAME>");
        request.append("<FILE_FORMAT></FILE_FORMAT>");
        request.append("<FILE_SIZE></FILE_SIZE>  ");
        request.append("<FILE_MD5></FILE_MD5>");
        request.append("</FILE>");
        request.append("</FILES>");
        request.append("</DOCUMENT>");
        request.append("</DOCUMENTS>");
        request.append("</BATCH>");
        request.append("</CMDATA>");

        String result = WSUtil.getWebserviceResponse("http://10.16.226.252:8399/TIMS-Server/services/ContentInfoService?wsdl",
                "CMForMobile_OnePicQuery", request.toString());

        Gson gson = new Gson();

        ServiLogger.log("nc-cloud:" + result);

        ServiLogger.log("\r\n ======================");

        List<Map<String, Object>> fileStructList = getFileStruct(result, "1002111000000110CM2Z");

        Map fileMap = new HashMap();
        fileMap.put("count", Integer.valueOf(fileStructList.size()));
        fileMap.put("attachmentgrouplist", fileStructList);

        ServiLogger.log(gson.toJson(fileMap));

    }

    private static List<Map<String, Object>> getFileStruct(String xml, String taskid) {
        List fileStructList = new ArrayList();
        String prefix;
        try {
            Reader in = new StringReader(xml);
            Document doc = new SAXBuilder().build(in);
            Element root = doc.getRootElement();
            List<Element> fileElements = null;

            Element docnameElement = root.getChild("BATCH")
                    .getChild("DOCUMENTS").getChild("DOCUMENT")
                    .getChild("DOCNAME");

            String docname = docnameElement.getValue();
            prefix = "CM&#%" + taskid + "&#%" + docname + "&#%";

            fileElements = root.getChild("BATCH").getChild("DOCUMENTS")
                    .getChild("DOCUMENT").getChild("FILES").getChildren("FILE");

            if ((fileElements != null) && (fileElements.size() > 0))
                for (Element fileElement : fileElements) {
                    Map fileMap = new HashMap();
                    String filename = fileElement.getChild("FILE_NAME")
                            .getValue();

                    String fileid = prefix + filename;
                    String filesize = fileElement.getChild("FILE_SIZE")
                            .getValue();

                    if ((filesize == null) || (filesize.length() == 0)) {
                        filesize = "0";
                    }
                    filesize = getFileSize(Integer.parseInt(filesize));
                    fileMap.put("fileid", fileid);
                    fileMap.put("filename", filename);
                    fileMap.put("filesize", filesize);
                    fileMap.put("downflag", "1");
                    fileStructList.add(fileMap);
                }
        } catch (Exception e) {
            return fileStructList;
        }

        return fileStructList;
    }

    public static String getFileSize(int filesize) {
        if (filesize < 1024)
            return filesize + "B";
        if ((filesize >= 1024) && (filesize < 1048576)) {
            return (filesize / 1024) + "KB";
        }
        return (filesize / 1048576) + "MB";
    }
}
