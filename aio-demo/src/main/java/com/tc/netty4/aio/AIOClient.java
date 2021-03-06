package com.tc.netty4.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @description:
 * @author: TC
 * @date: 2022-07-14 16:53
 **/
public class AIOClient {

    private final AsynchronousSocketChannel client;

    public AIOClient() throws IOException {
        client = AsynchronousSocketChannel.open();
    }


    public void connect(String host, int port) throws Exception{
        client.connect(new InetSocketAddress(host, port), null, new CompletionHandler<Void, Object>() {
            @Override
            public void completed(Void result, Object attachment) {
                try {
                    client.write(ByteBuffer.wrap("这是一条测试数据".getBytes())).get();
                    System.out.println("已发送至服务器");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }
        });

//        不等待会报错NotYetConnectedException
        Thread.sleep(1000);

        final ByteBuffer bb = ByteBuffer.allocate(1024);
        client.read(bb, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("I/O操作完成" + result);
                System.out.println("获取反馈结果" + new String(bb.array()));
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }
        });


        try {
            Thread.sleep(Integer.MAX_VALUE);
        }
        catch (InterruptedException ex){
            System.out.println(ex);
        }
    }

    public static void main(String[] args) throws Exception {
        new AIOClient().connect("127.0.0.1", 8000);
    }

}
