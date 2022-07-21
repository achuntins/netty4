package com.tc.million;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

    public static final int BEGIN_PORT = 8000;
    public static final int END_PORT = 8100;

    public static void main(String[] args) {
        new Server().start(Server.BEGIN_PORT, Server.END_PORT);
    }

    public void start(int beginPort, int nPort) {
        System.out.println("服务端启动中。。。");
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boosGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);
        bootstrap.childHandler(new ConnectionCountHandler());
        for (int i = 0; i <= (nPort - beginPort); i++) {
            final  int port = beginPort + i;
            bootstrap.bind(port).addListener(new ChannelFutureListener() {

                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    System.out.println("成功绑定监听端口：" + port);
                }
            });
        }
        System.out.println("服务端已启动！");




    }
}
