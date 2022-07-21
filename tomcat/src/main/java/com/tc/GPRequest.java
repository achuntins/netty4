package com.tc;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author tc
 * @Date 2022/7/21 09:44
 **/
public class GPRequest {


    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public GPRequest(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public String getUrl() {
        return req.uri();
    }

    public String getMethod() {
        return req.method().name();
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
        return decoder.parameters();
    }

    public String getParameter(String name) {
        Map<String, List<String>> params = getParameters();
        List<String> param = params.get(name);
        if (null == param) {
            return null;
        }
        else {
            return param.get(0);
        }
    }
    //    private String method;
//    private String url;
//
//    public GPRequest(InputStream in) {
//        try{
//            String content = "";
//            byte[] buff = new byte[1024];
//            int len = 0;
//            if ((len = in.read(buff)) > 0) {
//                content = new String(buff, 0 , len);
//            }
//            String line = content.split("\\n")[0];
//            String [] arr = line.split("\\s");
//
//            this.method = arr[0];
//            this.url = arr[1].split("\\?")[0];
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public String getUrl() {
//        return url;
//    }
//
//    public String getMethod() {
//        return method;
//    }
}
