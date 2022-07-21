package com.tc;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.nio.charset.StandardCharsets;

/**
 * @Description
 * @Author tc
 * @Date 2022/7/21 10:03
 **/
public class GPResponse {

    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public GPResponse(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }


    public void write(String out) throws Exception {
        try {
            if (out == null || out.length() == 0) {
                return;
            }
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(out.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "text/html;");

            ctx.write(response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ctx.flush();
            ctx.close();
        }
    }

    //    private OutputStream out;
//
//    public GPResponse(OutputStream out) {
//        this.out = out;
//    }
//
//    public void write(String s) throws Exception{
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("HTTP/1.1 200 OK\n")
//                .append("Content-Type: text/html;\n")
//                .append("\r\n")
//                .append(s);
//        out.write(sb.toString().getBytes());
//
//    }


}
