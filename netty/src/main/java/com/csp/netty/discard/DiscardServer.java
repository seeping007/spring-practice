package com.csp.netty.discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Server: discard any incoming data
 *
 * @author CSP
 * @since 2019/3/31
 */
public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {

        // accept an incoming connection
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // handle the traffic of the accepted connection once the boss accepts
        // the connection and registers the accepted connection to the worker
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // How many Threads are used and how they are mapped to the created Channels
        // depends on the EventLoopGroup implementation and may be even configurable via a constructor.

        try {

            // a helper class that sets up a server
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // instantiate a new Channel to accept incoming connections
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // configure the ChannelPipeline of the new Channel by adding some handlers
                            socketChannel.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128) // it is for the NioServerSocketChannel that accepts incoming connections
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // it is for the Channels accepted by the parent ServerChannel

            // Bind and start to accept incoming connections
            ChannelFuture f = b.bind(port).sync();

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully shut down your server.
            f.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {

        // test: telnet localhost 8080
        int port = 8080;
        new DiscardServer(port).run();
    }
}
