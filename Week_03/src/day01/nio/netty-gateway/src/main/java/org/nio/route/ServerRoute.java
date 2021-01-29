package org.nio.route;

import java.util.List;
import java.util.Random;

public class ServerRoute {

    public String route(List<String> proxyServers){
        Random random = new Random();
        int randInt = random.nextInt(proxyServers.size());
        return proxyServers.get(randInt);
    }
}
