package com.tgtiger.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    public static String post(String urlPath, String json){
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", "UTF-8");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("utf-8"));
            os.flush();
            os.close();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                StringBuilder sb = new StringBuilder();
                int len = 0;
                byte[] buf = new byte[128];
                while((len = is.read(buf)) != -1) {
                    sb.append(new String(buf, 0, len));
                }
                return sb.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


//    public static String getResult(String request_url) {
//        //用JAVA发起http请求，并返回json格式的结果
//        StringBuffer result = new StringBuffer();
//        try {
//            URL url = new URL(request_url);
//            URLConnection conn = url.openConnection();
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//            String line;
//            while((line = in.readLine()) != null){
//                result.append(line);
//            }
//            in.close();
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result.toString();
//    }
}
