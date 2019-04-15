package com.csp.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handle a server-side channel
 * <p>
 * The most simplistic protocol: DISCARD.
 * It's a protocol that discards any received data without any response.
 *
 * @author CSP
 * @since 2019/3/31
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * This method is called with the received message, whenever new data is received from a client.
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        // Discard the received data silently.
//        ((ByteBuf) msg).release();

        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) {
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }

        // ByteBuf is a reference-counted object which has to be released explicitly via the release() method.
        // It is the handler's responsibility to release any reference-counted object passed to the handler.
    }

    /**
     * It is called with a Throwable when an exception was raised by Netty due to an I/O error
     * or by a handler implementation due to the exception thrown while processing events.
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();

        // In most cases, the caught exception should be logged and its associated channel should be closed here.
    }
}
