package org.nio.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HttpHeaderRequestFilter implements HttpRequestFilter {
    @Override
    public void filter(ChannelHandlerContext ctx, FullHttpRequest request) {
        request.headers().set("requester","jack");
    }
}
