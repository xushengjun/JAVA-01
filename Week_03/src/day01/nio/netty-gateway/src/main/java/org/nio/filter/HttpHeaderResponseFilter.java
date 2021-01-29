package org.nio.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HttpHeaderResponseFilter implements HttpResponseFilter{
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("responser","Alice");
    }
}
