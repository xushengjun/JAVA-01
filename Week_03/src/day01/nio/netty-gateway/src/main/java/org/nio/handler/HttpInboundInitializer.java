package org.nio.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    private List<String> proxyServers;

    public HttpInboundInitializer(List<String> proxyServers) {
        this.proxyServers = proxyServers;
    }

    @Override
    public void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline ch = socketChannel.pipeline();
        ch.addLast(new HttpServerCodec());
        ch.addLast(new HttpObjectAggregator(1024*1024));
        ch.addLast(new HttpInboundHandler(proxyServers));

    }
}
