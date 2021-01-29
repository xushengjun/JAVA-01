package org.nio;

import org.nio.netty.HttpInboundServer;

import java.util.Arrays;
import java.util.List;

public class NettyApplication {

    public static void main(String[] args)  {
        List<String> urls = Arrays.asList("http://localhost:8801","http://localhost:8802");
        int port = 8888;

        HttpInboundServer server = new HttpInboundServer(port,urls);
        server.run();

    }

}

