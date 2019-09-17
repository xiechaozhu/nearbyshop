package com.shop.nearby.nearbyshop.utils;

import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DoPostUtil {
    public static String doHttpPost(String xmlInfo, String muurl)throws Exception {
        System.out.println("发起的数据:" + xmlInfo);
        byte[] xmlData = xmlInfo.getBytes();
        InputStream instr = null;
        String ResponseString = null;
        java.io.ByteArrayOutputStream out = null;
        URL url = new URL(muurl);
        URLConnection urlCon = url.openConnection();
        urlCon.setDoOutput(true);
        urlCon.setDoInput(true);
        urlCon.setUseCaches(false);
        urlCon.setRequestProperty("charset", "utf-8");
        urlCon.setRequestProperty("Content-length",
                String.valueOf(xmlData.length));
            System.out.println(String.valueOf(xmlData.length));
        DataOutputStream printout = new DataOutputStream(
                urlCon.getOutputStream());
        printout.write(xmlData);
        printout.flush();
        printout.close();
        instr = urlCon.getInputStream();
        byte[] bis = IOUtils.toByteArray(instr);
        ResponseString = new String(bis, "UTF-8");
        if ((ResponseString == null) || ("".equals(ResponseString.trim()))) {
            System.out.println("返回空");
        }
        System.out.println("返回数据为:" + ResponseString);
        return ResponseString;
    }
}
