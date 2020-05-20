package com.example.hello.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yanglu
 */
public class FileUtil {


    public static JSONObject readRequestParams(HttpServletRequest request) {
        JSONObject params = new JSONObject();
        try {
            Map requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
//                logger.info(valueStr);
//                try {
//                    valueStr = JSONObject.parseObject(valueStr.toString());
//                } catch (Exception e) {
//
//                }
                params.put(name, valueStr);
            }
            if (params.isEmpty()) {
                InputStream inputStream = request.getInputStream();
                ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];

                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    outSteam.write(buffer, 0, len);
                }
                String paramStr = new String(outSteam.toByteArray(), "UTF-8");
/*
                logger.info("paramStr=" + paramStr);
*/
                params = JSON.parseObject(paramStr);
                outSteam.close();
                inputStream.close();
            }
            //logger.info("params=" + params);

        } catch (Exception e) {
        }
        return params;
    }


}
