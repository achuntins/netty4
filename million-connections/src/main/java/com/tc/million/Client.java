package com.tc.million;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

    private static final String SERVER_HOST = "127.0.0.1";

    public static void main(String[] args) {
        new Client().start(Server.BEGIN_PORT, Server.END_PORT);
    }

    public void start(final int beginPort, int nPort) {
        System.out.printf("客户端已启动。。。");
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        Bootstrap group = bootstrap.group(eventLoopGroup);
        Bootstrap channel = bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {

            }
        });

        int index = 0;
        int port;
        while (!Thread.interrupted()) {
            port = beginPort + index;

            try {
                ChannelFuture channelFuture = bootstrap.connect(SERVER_HOST, port);
                channelFuture.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
//                        Thread.sleep(1000);
                        if (!future.isSuccess()) {
                            System.out.println(" 连接失败，程序关闭");
                            System.exit(0);
                        }
                    }
                });
                channelFuture.get();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            if (port == nPort) {
                index = 0;
            }
            else {
                index ++;
            }
        }


    }
}
